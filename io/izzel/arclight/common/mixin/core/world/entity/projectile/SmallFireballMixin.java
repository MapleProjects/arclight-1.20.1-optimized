/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.projectile.SmallFireball
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.FireballMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={SmallFireball.class})
public abstract class SmallFireballMixin
extends FireballMixin {
    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;DDD)V"}, at={@At(value="RETURN")})
    private void arclight$init(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ, CallbackInfo ci) {
        if (this.m_19749_() != null && this.m_19749_() instanceof Mob) {
            this.isIncendiary = this.m_9236_().m_46469_().m_46207_(GameRules.f_46132_);
        }
    }

    @Redirect(method={"onHitEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    private void arclight$entityCombust(Entity entity, int seconds) {
        if (this.isIncendiary) {
            EntityCombustByEntityEvent event = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), seconds);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                ((EntityBridge)entity).bridge$setOnFire(event.getDuration(), false);
            }
        }
    }

    @Inject(method={"onHitBlock"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z")})
    private void arclight$burnBlock(BlockHitResult result, CallbackInfo ci, Entity entity, BlockPos pos) {
        if (!this.isIncendiary || CraftEventFactory.callBlockIgniteEvent(this.m_9236_(), pos, (Entity)((SmallFireball)this)).isCancelled()) {
            ci.cancel();
        }
    }
}

