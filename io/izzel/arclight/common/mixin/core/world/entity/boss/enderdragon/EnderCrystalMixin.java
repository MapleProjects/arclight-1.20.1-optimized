/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.enderdragon.EndCrystal
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.ExplosionDamageCalculator
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.boss.enderdragon;

import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EndCrystal.class})
public abstract class EnderCrystalMixin
extends EntityMixin {
    @Inject(method={"tick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z")})
    private void arclight$blockIgnite(CallbackInfo ci) {
        if (CraftEventFactory.callBlockIgniteEvent(this.m_9236_(), this.m_20183_(), (Entity)((EndCrystal)this)).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V")})
    private void arclight$entityDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((EndCrystal)this), source, amount)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"hurt"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"))
    private Explosion arclight$blockPrime(Level world, Entity entityIn, DamageSource damageSource, ExplosionDamageCalculator calculator, double xIn, double yIn, double zIn, float explosionRadius, boolean fire, Level.ExplosionInteraction interaction) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), explosionRadius, fire);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            this.m_146912_();
            return null;
        }
        return world.m_254877_(entityIn, damageSource, calculator, xIn, yIn, zIn, event.getRadius(), event.getFire(), interaction);
    }
}

