/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.EntityHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ShulkerBullet.class})
public abstract class ShulkerBulletMixin
extends EntityMixin {
    @Shadow
    private Entity f_37312_;
    @Shadow
    @Nullable
    private Direction f_37313_;

    @Shadow
    protected abstract void m_37348_(@Nullable Direction.Axis var1);

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Direction$Axis;)V"}, at={@At(value="RETURN")})
    private void arclight$init(Level worldIn, LivingEntity ownerIn, Entity targetIn, Direction.Axis p_i46772_4_, CallbackInfo ci) {
        this.projectileSource = ((LivingEntityBridge)ownerIn).bridge$getBukkitEntity();
    }

    @Inject(method={"onHitEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$reason(EntityHitResult result, CallbackInfo ci) {
        ((LivingEntityBridge)result.m_82443_()).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.ATTACK);
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$damageBullet(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((ShulkerBullet)this), source, amount, false)) {
            cir.setReturnValue((Object)false);
        }
    }

    public Entity getTarget() {
        return this.f_37312_;
    }

    public void setTarget(Entity e) {
        this.f_37312_ = e;
        this.f_37313_ = Direction.UP;
        this.m_37348_(Direction.Axis.X);
    }
}

