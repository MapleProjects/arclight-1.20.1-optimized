/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.goal.TemptGoal
 *  net.minecraft.world.entity.player.Player
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.goal;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={TemptGoal.class})
public abstract class TemptGoalMixin {
    @Shadow
    protected Player f_25925_;
    @Shadow
    @Final
    protected PathfinderMob f_25924_;

    @Inject(method={"canUse"}, cancellable=true, at={@At(value="FIELD", shift=At.Shift.AFTER, opcode=181, target="Lnet/minecraft/world/entity/ai/goal/TemptGoal;player:Lnet/minecraft/world/entity/player/Player;")})
    public void arclight$tempt(CallbackInfoReturnable<Boolean> cir) {
        boolean tempt;
        boolean bl = tempt = this.f_25925_ != null;
        if (tempt) {
            EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)this.f_25924_, (LivingEntity)this.f_25925_, EntityTargetEvent.TargetReason.TEMPT);
            if (event.isCancelled()) {
                cir.setReturnValue((Object)false);
                return;
            }
            this.f_25925_ = event.getTarget() == null ? null : ((CraftHumanEntity)event.getTarget()).getHandle();
        }
        cir.setReturnValue((Object)tempt);
    }
}

