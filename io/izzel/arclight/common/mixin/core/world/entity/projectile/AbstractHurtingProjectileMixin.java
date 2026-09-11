/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.HitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.projectile.DamagingProjectileEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.ProjectileMixin;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={AbstractHurtingProjectile.class})
public abstract class AbstractHurtingProjectileMixin
extends ProjectileMixin
implements DamagingProjectileEntityBridge {
    @Shadow
    public double f_36813_;
    @Shadow
    public double f_36814_;
    @Shadow
    public double f_36815_;
    public float bukkitYield;
    public boolean isIncendiary;

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends AbstractHurtingProjectile> p_i50173_1_, Level p_i50173_2_, CallbackInfo ci) {
        this.bukkitYield = 1.0f;
        this.isIncendiary = true;
    }

    public void setDirection(double d0, double d1, double d2) {
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        this.f_36813_ = d0 / d3 * 0.1;
        this.f_36814_ = d1 / d3 * 0.1;
        this.f_36815_ = d2 / d3 * 0.1;
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/projectile/AbstractHurtingProjectile;onHit(Lnet/minecraft/world/phys/HitResult;)V"))
    private void arclight$preOnHit(AbstractHurtingProjectile abstractHurtingProjectile, HitResult hitResult) {
        this.preOnHit(hitResult);
    }

    @Inject(method={"tick"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/projectile/AbstractHurtingProjectile;onHit(Lnet/minecraft/world/phys/HitResult;)V")})
    private void arclight$projectileHit(CallbackInfo ci, Entity entity, HitResult rayTraceResult) {
        if (this.m_213877_()) {
            CraftEventFactory.callProjectileHitEvent((Entity)((AbstractHurtingProjectile)this), rayTraceResult);
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;getLookAngle()Lnet/minecraft/world/phys/Vec3;")})
    private void arclight$nonLivingAttack(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((AbstractHurtingProjectile)this), source, amount, false)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Override
    public void bridge$setBukkitYield(float yield) {
        this.bukkitYield = yield;
    }
}

