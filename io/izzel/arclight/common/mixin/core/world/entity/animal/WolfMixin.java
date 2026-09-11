/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.animal.Wolf
 *  net.minecraft.world.entity.player.Player
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.mixin.core.world.entity.animal.TameableAnimalMixin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Wolf.class})
public abstract class WolfMixin
extends TameableAnimalMixin {
    @Redirect(method={"hurt"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Wolf;setOrderedToSit(Z)V"))
    private void arclight$handledBy(Wolf wolfEntity, boolean p_233687_1_) {
    }

    @Inject(method={"setTame"}, at={@At(value="RETURN")})
    private void arclight$healToMax(boolean tamed, CallbackInfo ci) {
        if (tamed) {
            this.m_21153_(this.m_21233_());
        }
    }

    @Inject(method={"mobInteract"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Wolf;heal(F)V")})
    private void arclight$healReason(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.EATING);
    }

    @Inject(method={"mobInteract"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Wolf;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V")})
    private void arclight$attackReason(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        this.bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
    }
}

