/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.MoveToBlockGoal
 *  net.minecraft.world.entity.animal.Turtle
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.TurtleEggBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.passive.TurtleEntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net.minecraft.world.entity.animal.Turtle$TurtleLayEggGoal"})
public abstract class Turtle_LayEggGoalMixin
extends MoveToBlockGoal {
    @Shadow
    @Final
    private Turtle f_30274_;

    public Turtle_LayEggGoalMixin(PathfinderMob creature, double speedIn, int length) {
        super(creature, speedIn, length);
    }

    @Overwrite
    public void m_8037_() {
        super.m_8037_();
        BlockPos blockpos = this.f_30274_.m_20183_();
        if (!this.f_30274_.m_20069_() && this.m_25625_()) {
            if (((TurtleEntityBridge)this.f_30274_).bridge$getDigging() < 1) {
                ((TurtleEntityBridge)this.f_30274_).bridge$setDigging(true);
            } else if (((TurtleEntityBridge)this.f_30274_).bridge$getDigging() > 200) {
                Level world = this.f_30274_.m_9236_();
                if (CraftEventFactory.callEntityChangeBlockEvent((Entity)this.f_30274_, this.f_25602_.m_7494_(), (BlockState)Blocks.f_50578_.m_49966_().m_61124_((Property)TurtleEggBlock.f_57754_, (Comparable)Integer.valueOf(this.f_30274_.m_217043_().m_188503_(4) + 1)))) {
                    BlockPos blockpos1 = this.f_25602_.m_7494_();
                    BlockState blockstate = (BlockState)Blocks.f_50578_.m_49966_().m_61124_((Property)TurtleEggBlock.f_57754_, (Comparable)Integer.valueOf(this.f_30274_.m_217043_().m_188503_(4) + 1));
                    world.m_7731_(blockpos1, blockstate, 3);
                    world.m_220407_(GameEvent.f_157797_, blockpos1, GameEvent.Context.m_223719_((Entity)this.f_30274_, (BlockState)blockstate));
                }
                ((TurtleEntityBridge)this.f_30274_).bridge$setHasEgg(false);
                ((TurtleEntityBridge)this.f_30274_).bridge$setDigging(false);
                this.f_30274_.m_27601_(600);
            }
            if (this.f_30274_.m_30206_()) {
                ((TurtleEntityBridge)this.f_30274_).bridge$setDigging(((TurtleEntityBridge)this.f_30274_).bridge$getDigging() + 1);
            }
        }
    }
}

