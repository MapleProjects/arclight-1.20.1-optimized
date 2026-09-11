/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.ArclightVersion
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.monster.Silverfish
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.InfestedBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.api.ArclightVersion;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net.minecraft.world.entity.monster.Silverfish$SilverfishWakeUpFriendsGoal"})
public abstract class Silverfish_WakeUpFriendsGoalMixin
extends Goal {
    @Shadow
    private int f_33563_;
    @Shadow
    @Final
    private Silverfish f_33562_;

    @Overwrite
    public void m_8037_() {
        --this.f_33563_;
        if (this.f_33563_ <= 0) {
            Level world = this.f_33562_.m_9236_();
            RandomSource random = this.f_33562_.m_217043_();
            BlockPos blockpos = this.f_33562_.m_20183_();
            int i = 0;
            while (i <= 5 && i >= -5) {
                int j = 0;
                while (j <= 10 && j >= -10) {
                    int k = 0;
                    while (k <= 10 && k >= -10) {
                        BlockPos blockpos1 = blockpos.m_7918_(j, i, k);
                        BlockState blockstate = world.m_8055_(blockpos1);
                        Block block = blockstate.m_60734_();
                        if (block instanceof InfestedBlock && CraftEventFactory.callEntityChangeBlockEvent((Entity)this.f_33562_, blockpos1, Blocks.f_50016_.m_49966_())) {
                            if (ForgeEventFactory.getMobGriefingEvent((Level)world, (Entity)this.f_33562_)) {
                                if (ArclightVersion.atLeast((ArclightVersion)ArclightVersion.v1_15)) {
                                    world.m_46953_(blockpos1, true, (Entity)this.f_33562_);
                                } else {
                                    world.m_46961_(blockpos1, true);
                                }
                            } else {
                                world.m_7731_(blockpos1, ((InfestedBlock)block).m_54192_().m_49966_(), 3);
                            }
                            if (random.m_188499_()) {
                                return;
                            }
                        }
                        k = (k <= 0 ? 1 : 0) - k;
                    }
                    j = (j <= 0 ? 1 : 0) - j;
                }
                i = (i <= 0 ? 1 : 0) - i;
            }
        }
    }
}

