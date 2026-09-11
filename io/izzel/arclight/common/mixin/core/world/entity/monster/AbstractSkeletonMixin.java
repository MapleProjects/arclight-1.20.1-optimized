/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={AbstractSkeleton.class})
public abstract class AbstractSkeletonMixin
extends PathfinderMobMixin {
    @Inject(method={"performRangedAttack"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/AbstractSkeleton;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V")})
    private void arclight$shootBow(LivingEntity target, float distanceFactor, CallbackInfo ci, ItemStack itemStack, AbstractArrow arrowEntity) {
        EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent((LivingEntity)((AbstractSkeleton)this), this.m_21205_(), null, (Entity)arrowEntity, InteractionHand.MAIN_HAND, 0.8f, true);
        if (event.isCancelled()) {
            event.getProjectile().remove();
            ci.cancel();
            return;
        }
        if (event.getProjectile() != ((EntityBridge)arrowEntity).bridge$getBukkitEntity()) {
            this.m_5496_(SoundEvents.f_12382_, 1.0f, 1.0f / (this.m_217043_().m_188501_() * 0.4f + 0.8f));
            ci.cancel();
        }
    }
}

