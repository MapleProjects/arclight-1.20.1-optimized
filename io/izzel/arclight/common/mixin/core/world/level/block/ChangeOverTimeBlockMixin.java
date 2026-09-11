/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.ChangeOverTimeBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import java.util.Iterator;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ChangeOverTimeBlock.class})
public interface ChangeOverTimeBlockMixin<T extends Enum<T>> {
    @Shadow
    public T m_142297_();

    @Shadow
    public float m_142377_();

    @Shadow
    public Optional<BlockState> m_142123_(BlockState var1);

    @Overwrite
    default public void m_220952_(BlockState p_220953_, ServerLevel level, BlockPos pos, RandomSource p_220956_) {
        BlockPos blockpos;
        int l;
        int i = ((Enum)this.m_142297_()).ordinal();
        int j = 0;
        int k = 0;
        Iterator iterator = BlockPos.m_121925_((BlockPos)pos, (int)4, (int)4, (int)4).iterator();
        while (iterator.hasNext() && (l = (blockpos = (BlockPos)iterator.next()).m_123333_((Vec3i)pos)) <= 4) {
            BlockState blockstate;
            Block block;
            if (blockpos.equals((Object)pos) || !((block = (blockstate = level.m_8055_(blockpos)).m_60734_()) instanceof ChangeOverTimeBlock)) continue;
            Enum oenum = ((ChangeOverTimeBlock)block).m_142297_();
            if (this.m_142297_().getClass() != oenum.getClass()) continue;
            int i1 = oenum.ordinal();
            if (i1 < i) {
                return;
            }
            if (i1 > i) {
                ++k;
                continue;
            }
            ++j;
        }
        float f = (float)(k + 1) / (float)(k + j + 1);
        float f1 = f * f * this.m_142377_();
        if (p_220956_.m_188501_() < f1) {
            this.m_142123_(p_220953_).ifPresent(newState -> CraftEventFactory.handleBlockFormEvent((Level)level, pos, newState));
        }
    }
}

