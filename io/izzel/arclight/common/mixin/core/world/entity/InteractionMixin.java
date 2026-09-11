/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.critereon.PlayerHurtEntityTrigger
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Interaction
 *  net.minecraft.world.entity.player.Player
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import net.minecraft.advancements.critereon.PlayerHurtEntityTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityDamageEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Interaction.class})
public class InteractionMixin {
    private double arclight$finalDamage;

    @Inject(method={"skipAttackInteraction"}, cancellable=true, at={@At(value="FIELD", opcode=181, target="Lnet/minecraft/world/entity/Interaction;attack:Lnet/minecraft/world/entity/Interaction$PlayerAction;")})
    private void arclight$onDamage(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        DamageSource source = entity.m_269291_().m_269075_((Player)entity);
        EntityDamageEvent event = CraftEventFactory.callNonLivingEntityDamageEvent((Entity)this, source, 1.0, false);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)true);
        } else {
            this.arclight$finalDamage = event.getFinalDamage();
        }
    }

    @Redirect(method={"skipAttackInteraction"}, at=@At(value="INVOKE", target="Lnet/minecraft/advancements/critereon/PlayerHurtEntityTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;FFZ)V"))
    private void arclight$setDamage(PlayerHurtEntityTrigger instance, ServerPlayer p_60113_, Entity p_60114_, DamageSource p_60115_, float p_60116_, float p_60117_, boolean p_60118_) {
        instance.m_60112_(p_60113_, p_60114_, p_60115_, (float)this.arclight$finalDamage, p_60117_, p_60118_);
    }
}

