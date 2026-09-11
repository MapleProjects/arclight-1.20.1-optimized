/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.LargeFireball
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.mixin.core.world.entity.projectile.AbstractHurtingProjectileMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Explosive;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LargeFireball.class})
public abstract class LargeFireballMixin
extends AbstractHurtingProjectileMixin {
    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends LargeFireball> p_i50163_1_, Level worldIn, CallbackInfo ci) {
        this.isIncendiary = worldIn.m_46469_().m_46207_(GameRules.f_46132_);
    }

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;DDDI)V"}, at={@At(value="RETURN")})
    private void arclight$init(Level level, LivingEntity p_181152_, double p_181153_, double p_181154_, double p_181155_, int p_181156_, CallbackInfo ci) {
        this.isIncendiary = level.m_46469_().m_46207_(GameRules.f_46132_);
    }

    @Redirect(method={"onHit"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)Lnet/minecraft/world/level/Explosion;"))
    private Explosion arclight$explodePrime(Level world, Entity entityIn, double xIn, double yIn, double zIn, float explosionRadius, boolean causesFire, Level.ExplosionInteraction interaction) {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)((Object)this.getBukkitEntity()));
        event.setRadius(explosionRadius);
        event.setFire(causesFire);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            return this.m_9236_().m_255391_((Entity)((LargeFireball)this), xIn, yIn, zIn, event.getRadius(), event.getFire(), interaction);
        }
        return null;
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="INVOKE", target="Lnet/minecraft/nbt/CompoundTag;getByte(Ljava/lang/String;)B")})
    private void arclight$setYield(CompoundTag compound, CallbackInfo ci) {
        this.bukkitYield = compound.m_128451_("ExplosionPower");
    }
}

