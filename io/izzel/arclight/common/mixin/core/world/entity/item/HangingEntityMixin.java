/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.Vec3i
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.item;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.entity.Hanging;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={HangingEntity.class})
public abstract class HangingEntityMixin
extends EntityMixin {
    @Shadow
    public BlockPos f_31698_;

    @Inject(method={"tick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/HangingEntity;discard()V")})
    private void arclight$hangingBreak(CallbackInfo ci) {
        BlockState state = this.m_9236_().m_8055_(new BlockPos((Vec3i)this.m_20183_()));
        HangingBreakEvent.RemoveCause cause = !state.m_60795_() ? HangingBreakEvent.RemoveCause.OBSTRUCTION : HangingBreakEvent.RemoveCause.PHYSICS;
        HangingBreakEvent event = new HangingBreakEvent((Hanging)((Object)this.getBukkitEntity()), cause);
        Bukkit.getPluginManager().callEvent(event);
        if (this.m_213877_() || event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/HangingEntity;kill()V")})
    private void arclight$hangingBreakByAttack(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity damager;
        Entity entity = damager = source.m_269014_() ? source.m_7639_() : source.m_7640_();
        HangingBreakEvent event = damager != null ? new HangingBreakByEntityEvent((Hanging)((Object)this.getBukkitEntity()), ((EntityBridge)damager).bridge$getBukkitEntity(), source.m_269533_(DamageTypeTags.f_268415_) ? HangingBreakEvent.RemoveCause.EXPLOSION : HangingBreakEvent.RemoveCause.ENTITY) : new HangingBreakEvent((Hanging)((Object)this.getBukkitEntity()), source.m_269533_(DamageTypeTags.f_268415_) ? HangingBreakEvent.RemoveCause.EXPLOSION : HangingBreakEvent.RemoveCause.DEFAULT);
        Bukkit.getPluginManager().callEvent(event);
        if (this.m_213877_() || event.isCancelled()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"move"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/HangingEntity;kill()V")})
    private void arclight$hangingBreakByMove(MoverType typeIn, Vec3 pos, CallbackInfo ci) {
        if (this.m_213877_()) {
            ci.cancel();
            return;
        }
        HangingBreakEvent event = new HangingBreakEvent((Hanging)((Object)this.getBukkitEntity()), HangingBreakEvent.RemoveCause.PHYSICS);
        Bukkit.getPluginManager().callEvent(event);
        if (this.m_213877_() || event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"push"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$noVelocity(double x, double y, double z, CallbackInfo ci) {
        ci.cancel();
    }

    private static double a(int i) {
        return i % 32 == 0 ? 0.5 : 0.0;
    }

    private static AABB calculateBoundingBox(Entity entity, BlockPos blockPosition, Direction direction, int width, int height) {
        double d0 = (double)blockPosition.m_123341_() + 0.5;
        double d2 = (double)blockPosition.m_123342_() + 0.5;
        double d3 = (double)blockPosition.m_123343_() + 0.5;
        double d4 = 0.46875;
        double d5 = HangingEntityMixin.a(width);
        double d6 = HangingEntityMixin.a(height);
        d0 -= (double)direction.m_122429_() * 0.46875;
        d3 -= (double)direction.m_122431_() * 0.46875;
        d2 += d6;
        Direction enumdirection = direction.m_122428_();
        d0 += d5 * (double)enumdirection.m_122429_();
        d3 += d5 * (double)enumdirection.m_122431_();
        if (entity != null) {
            entity.m_20343_(d0, d2, d3);
        }
        double d7 = width;
        double d8 = height;
        double d9 = width;
        if (direction.m_122434_() == Direction.Axis.Z) {
            d9 = 1.0;
        } else {
            d7 = 1.0;
        }
        return new AABB(d0 - (d7 /= 32.0), d2 - (d8 /= 32.0), d3 - (d9 /= 32.0), d0 + d7, d2 + d8, d3 + d9);
    }
}

