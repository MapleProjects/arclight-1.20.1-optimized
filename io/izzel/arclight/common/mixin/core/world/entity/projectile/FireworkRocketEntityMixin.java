/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FireworkRocketEntity.class})
public abstract class FireworkRocketEntityMixin
extends EntityMixin {
    @Inject(method={"explode"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$fireworksExplode(CallbackInfo ci) {
        if (CraftEventFactory.callFireworkExplodeEvent((FireworkRocketEntity)this).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"dealExplosionDamage"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z")})
    private void arclight$damageSource(CallbackInfo ci) {
        CraftEventFactory.entityDamage = (FireworkRocketEntity)this;
    }

    @Inject(method={"dealExplosionDamage"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z")})
    private void arclight$damageSourceReset(CallbackInfo ci) {
        CraftEventFactory.entityDamage = null;
    }
}

