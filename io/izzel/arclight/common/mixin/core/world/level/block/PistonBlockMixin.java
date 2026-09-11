/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.piston.PistonBaseBlock
 *  net.minecraft.world.level.block.piston.PistonStructureResolver
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import com.google.common.collect.ImmutableList;
import java.util.AbstractList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={PistonBaseBlock.class})
public class PistonBlockMixin {
    @Shadow
    @Final
    private boolean f_60160_;

    @Inject(method={"checkIfExtend"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;blockEvent(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;II)V")})
    public void arclight$pistonRetract(Level worldIn, BlockPos pos, BlockState state, CallbackInfo ci, Direction direction) {
        if (!this.f_60160_) {
            CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
            BlockPistonRetractEvent event = new BlockPistonRetractEvent(block, (List<Block>)ImmutableList.of(), CraftBlock.notchToBlockFace(direction));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"moveBlocks"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE_ASSIGN", target="Lnet/minecraft/world/level/block/piston/PistonStructureResolver;getToDestroy()Ljava/util/List;")})
    public void arclight$pistonAction(Level worldIn, BlockPos pos, Direction directionIn, boolean extending, CallbackInfoReturnable<Boolean> cir, BlockPos blockPos, PistonStructureResolver helper) {
        final CraftBlock craftBlock = CraftBlock.at((LevelAccessor)worldIn, pos);
        final List moved = helper.m_60436_();
        final List broken = helper.m_60437_();
        class BlockList
        extends AbstractList<Block> {
            BlockList() {
            }

            @Override
            public int size() {
                return moved.size() + broken.size();
            }

            @Override
            public Block get(int index) {
                if (index >= this.size() || index < 0) {
                    throw new ArrayIndexOutOfBoundsException(index);
                }
                BlockPos pos = index < moved.size() ? (BlockPos)moved.get(index) : (BlockPos)broken.get(index - moved.size());
                return craftBlock.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
            }
        }
        BlockList blocks = new BlockList();
        Direction direction = extending ? directionIn : directionIn.m_122424_();
        BlockPistonEvent event = extending ? new BlockPistonExtendEvent((Block)craftBlock, blocks, CraftBlock.notchToBlockFace(direction)) : new BlockPistonRetractEvent(craftBlock, blocks, CraftBlock.notchToBlockFace(direction));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            for (BlockPos b : broken) {
                worldIn.m_7260_(b, Blocks.f_50016_.m_49966_(), worldIn.m_8055_(b), 3);
            }
            for (BlockPos b : moved) {
                worldIn.m_7260_(b, Blocks.f_50016_.m_49966_(), worldIn.m_8055_(b), 3);
                b = b.m_121945_(direction);
                worldIn.m_7260_(b, Blocks.f_50016_.m_49966_(), worldIn.m_8055_(b), 3);
            }
            cir.setReturnValue((Object)false);
        }
    }
}

