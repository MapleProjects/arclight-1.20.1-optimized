/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.WitherSkull
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.phys.EntityHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.AbstractHurtingProjectileMixin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={WitherSkull.class})
public abstract class WitherSkullMixin
extends AbstractHurtingProjectileMixin {
    @Inject(method={"onHitEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V")})
    private void arclight$heal(EntityHitResult result, CallbackInfo ci) {
        ((LivingEntityBridge)this.m_19749_()).bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.WITHER);
    }

    @Inject(method={"onHitEntity"}, require=0, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$effect(EntityHitResult result, CallbackInfo ci) {
        ((LivingEntityBridge)result.m_82443_()).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.ATTACK);
    }

    @Redirect(method={"onHit"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"))
    private Explosion arclight$explode(Level world, Entity entityIn, double xIn, double yIn, double zIn, float explosionRadius, boolean causesFire, Level.ExplosionInteraction interaction) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), explosionRadius, causesFire);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            return this.m_9236_().m_255391_((Entity)((WitherSkull)this), xIn, yIn, zIn, event.getRadius(), event.getFire(), interaction);
        }
        return null;
    }
}

