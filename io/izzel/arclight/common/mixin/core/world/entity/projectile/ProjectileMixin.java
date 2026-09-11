/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Projectile.class})
public abstract class ProjectileMixin
extends EntityMixin {
    private boolean hitCancelled = false;

    @Shadow
    @Nullable
    public abstract Entity m_19749_();

    @Shadow
    protected void m_6532_(HitResult result) {
    }

    @Inject(method={"setOwner"}, at={@At(value="RETURN")})
    private void arclight$updateSource(Entity entityIn, CallbackInfo ci) {
        CraftEntity entity;
        if (entityIn != null && (entity = ((EntityBridge)entityIn).bridge$getBukkitEntity()) instanceof ProjectileSource) {
            this.projectileSource = (ProjectileSource)((Object)entity);
        }
    }

    @Inject(method={"onHitBlock"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$cancelBlockHit(BlockHitResult result, CallbackInfo ci) {
        if (this.hitCancelled) {
            ci.cancel();
        }
    }

    protected void preOnHit(HitResult hitResult) {
        ProjectileHitEvent event = CraftEventFactory.callProjectileHitEvent((Entity)((Projectile)this), hitResult);
        boolean bl = this.hitCancelled = event != null && event.isCancelled();
        if (hitResult.m_6662_() == HitResult.Type.BLOCK || !this.hitCancelled) {
            this.m_6532_(hitResult);
        }
    }
}

