/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Position
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.enchantment.FrostWalkerEnchantment
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FrostedIceBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraftforge.common.util.BlockSnapshot
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.item.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={FrostWalkerEnchantment.class})
public class FrostWalkerEnchantmentMixin {
    @Overwrite
    public static void m_45018_(LivingEntity living, Level worldIn, BlockPos pos, int level) {
        if (living.m_20096_()) {
            BlockState blockstate = Blocks.f_50449_.m_49966_();
            int f = Math.min(16, 2 + level);
            BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
            for (BlockPos blockpos : BlockPos.m_121940_((BlockPos)pos.m_7918_(-f, -1, -f), (BlockPos)pos.m_7918_(f, -1, f))) {
                BlockState blockstate2;
                if (!blockpos.m_203195_((Position)living.m_20182_(), (double)f)) continue;
                blockpos$mutable.m_122178_(blockpos.m_123341_(), blockpos.m_123342_() + 1, blockpos.m_123343_());
                BlockState blockstate1 = worldIn.m_8055_((BlockPos)blockpos$mutable);
                if (!blockstate1.m_60795_() || (blockstate2 = worldIn.m_8055_(blockpos)) != FrostedIceBlock.m_278844_() || !blockstate.m_60710_((LevelReader)worldIn, blockpos) || !worldIn.m_45752_(blockstate, blockpos, CollisionContext.m_82749_()) || ForgeEventFactory.onBlockPlace((Entity)living, (BlockSnapshot)BlockSnapshot.create((ResourceKey)worldIn.m_46472_(), (LevelAccessor)worldIn, (BlockPos)blockpos), (Direction)Direction.UP) || !CraftEventFactory.handleBlockFormEvent(worldIn, blockpos, blockstate, (Entity)living)) continue;
                worldIn.m_186460_(blockpos, Blocks.f_50449_, Mth.m_216271_((RandomSource)living.m_217043_(), (int)60, (int)120));
            }
        }
    }
}

