/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.vehicle.MinecartTNT
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.ExplosionDamageCalculator
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.vehicle;

import io.izzel.arclight.common.mixin.core.world.entity.vehicle.AbstractMinecartMixin;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MinecartTNT.class})
public abstract class MinecartTNTMixin
extends AbstractMinecartMixin {
    @Shadow
    private int f_38647_;

    @Eject(method={"explode(Lnet/minecraft/world/damagesource/DamageSource;D)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"))
    private Explosion arclight$explode(Level level, Entity entity, DamageSource source, ExplosionDamageCalculator calculator, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction interaction, CallbackInfo ci) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), radius, fire);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            this.f_38647_ = -1;
            ci.cancel();
            return null;
        }
        return level.m_255391_((Entity)((MinecartTNT)this), x, y, z, event.getRadius(), event.getFire(), interaction);
    }
}

