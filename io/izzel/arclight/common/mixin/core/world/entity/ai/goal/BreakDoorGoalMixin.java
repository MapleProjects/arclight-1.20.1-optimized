/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.BreakDoorGoal
 *  net.minecraft.world.entity.ai.goal.DoorInteractGoal
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.DoorInteractGoal;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BreakDoorGoal.class})
public abstract class BreakDoorGoalMixin
extends DoorInteractGoal {
    public BreakDoorGoalMixin(Mob entityIn) {
        super(entityIn);
    }

    @Inject(method={"tick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z")})
    public void arclight$breakDoor(CallbackInfo ci) {
        if (CraftEventFactory.callEntityBreakDoorEvent((Entity)this.f_25189_, this.f_25190_).isCancelled()) {
            this.m_8056_();
            ci.cancel();
        }
    }
}

