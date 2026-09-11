/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.BucketPickup
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.SpongeBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SpongeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.FluidState;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.util.BlockStateListPopulator;
import org.bukkit.event.block.SpongeAbsorbEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={SpongeBlock.class})
public class SpongeBlockMixin {
    @Shadow
    @Final
    private static Direction[] f_276425_;

    @Overwrite
    private boolean m_56807_(Level world, BlockPos blockposition) {
        BlockStateListPopulator blockList = new BlockStateListPopulator((LevelAccessor)world);
        BlockPos.m_276833_((BlockPos)blockposition, (int)6, (int)65, (blockposition1, consumer) -> {
            for (Direction enumdirection : f_276425_) {
                consumer.accept(blockposition1.m_121945_(enumdirection));
            }
        }, blockposition1 -> {
            BucketPickup ifluidsource;
            if (blockposition1.equals((Object)blockposition)) {
                return true;
            }
            net.minecraft.world.level.block.state.BlockState iblockdata = blockList.m_8055_((BlockPos)blockposition1);
            FluidState fluid = blockList.m_6425_((BlockPos)blockposition1);
            if (!fluid.m_205070_(FluidTags.f_13131_)) {
                return false;
            }
            Block block = iblockdata.m_60734_();
            if (block instanceof BucketPickup && !(ifluidsource = (BucketPickup)block).m_142598_((LevelAccessor)blockList, blockposition1, iblockdata).m_41619_()) {
                return true;
            }
            if (iblockdata.m_60734_() instanceof LiquidBlock) {
                blockList.m_7731_((BlockPos)blockposition1, Blocks.f_50016_.m_49966_(), 3);
            } else {
                if (!(iblockdata.m_60713_(Blocks.f_50575_) || iblockdata.m_60713_(Blocks.f_50576_) || iblockdata.m_60713_(Blocks.f_50037_) || iblockdata.m_60713_(Blocks.f_50038_))) {
                    return false;
                }
                blockList.m_7731_((BlockPos)blockposition1, Blocks.f_50016_.m_49966_(), 3);
            }
            return true;
        });
        List<BlockState> blocks = blockList.getList();
        if (!blocks.isEmpty()) {
            CraftBlock bblock = CraftBlock.at((LevelAccessor)world, blockposition);
            SpongeAbsorbEvent event = new SpongeAbsorbEvent(bblock, blocks);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return false;
            }
            for (CraftBlockState craftBlockState : blocks) {
                BlockPos blockposition12 = craftBlockState.getPosition();
                net.minecraft.world.level.block.state.BlockState iblockdata = world.m_8055_(blockposition12);
                FluidState fluid = world.m_6425_(blockposition12);
                if (fluid.m_205070_(FluidTags.f_13131_) && (!(iblockdata.m_60734_() instanceof BucketPickup) || ((BucketPickup)iblockdata.m_60734_()).m_142598_((LevelAccessor)blockList, blockposition12, iblockdata).m_41619_()) && !(iblockdata.m_60734_() instanceof LiquidBlock) && (iblockdata.m_60713_(Blocks.f_50575_) || iblockdata.m_60713_(Blocks.f_50576_) || iblockdata.m_60713_(Blocks.f_50037_) || iblockdata.m_60713_(Blocks.f_50038_))) {
                    BlockEntity tileentity = iblockdata.m_155947_() ? world.m_7702_(blockposition12) : null;
                    Block.m_49892_((net.minecraft.world.level.block.state.BlockState)iblockdata, (LevelAccessor)world, (BlockPos)blockposition12, (BlockEntity)tileentity);
                }
                world.m_7731_(blockposition12, craftBlockState.getHandle(), craftBlockState.getFlag());
            }
            return true;
        }
        return false;
    }
}

