/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.ThrownPotion
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.level.block.AbstractCandleBlock
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.CampfireBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.ThrowableItemProjectileMixin;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ThrownPotion.class})
public abstract class ThrownPotionMixin
extends ThrowableItemProjectileMixin {
    @Redirect(method={"onHit"}, at=@At(value="INVOKE", remap=false, ordinal=1, target="Ljava/util/List;isEmpty()Z"))
    private boolean arclight$callEvent(List list) {
        return false;
    }

    @Overwrite
    private void m_37547_(List<MobEffectInstance> list, @Nullable Entity entity) {
        PotionSplashEvent event;
        double d2;
        AABB axisalignedbb = this.m_20191_().m_82377_(4.0, 2.0, 4.0);
        List list2 = this.m_9236_().m_45976_(net.minecraft.world.entity.LivingEntity.class, axisalignedbb);
        HashMap<LivingEntity, Double> affected = new HashMap<LivingEntity, Double>();
        if (!list2.isEmpty()) {
            for (net.minecraft.world.entity.LivingEntity entityliving : list2) {
                double d0;
                if (!entityliving.m_5801_() || (d0 = this.m_20280_((Entity)entityliving)) >= 16.0) continue;
                d2 = 1.0 - Math.sqrt(d0) / 4.0;
                if (entityliving == entity) {
                    d2 = 1.0;
                }
                affected.put(((LivingEntityBridge)entityliving).bridge$getBukkitEntity(), d2);
            }
        }
        if (!(event = CraftEventFactory.callPotionSplashEvent((ThrownPotion)this, affected)).isCancelled() && list != null && !list.isEmpty()) {
            for (LivingEntity victim : event.getAffectedEntities()) {
                if (!(victim instanceof CraftLivingEntity)) continue;
                net.minecraft.world.entity.LivingEntity entityliving2 = ((CraftLivingEntity)victim).getHandle();
                d2 = event.getIntensity(victim);
                for (MobEffectInstance mobeffect : list) {
                    int i;
                    MobEffect mobeffectlist = mobeffect.m_19544_();
                    if (!((WorldBridge)this.m_9236_()).bridge$isPvpMode() && this.m_19749_() instanceof ServerPlayer && entityliving2 instanceof ServerPlayer && entityliving2 != this.m_19749_() && ((i = MobEffect.m_19459_((MobEffect)mobeffectlist)) == 2 || i == 4 || i == 7 || i == 15 || i == 17 || i == 18 || i == 19)) continue;
                    if (mobeffectlist.m_8093_()) {
                        mobeffectlist.m_19461_((Entity)((ThrownPotion)this), this.m_19749_(), entityliving2, mobeffect.m_19564_(), d2);
                        continue;
                    }
                    i = (int)(d2 * (double)mobeffect.m_19557_() + 0.5);
                    if (i <= 20) continue;
                    ((LivingEntityBridge)entityliving2).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.POTION_SPLASH);
                    entityliving2.m_7292_(new MobEffectInstance(mobeffectlist, i, mobeffect.m_19564_(), mobeffect.m_19571_(), mobeffect.m_19572_()));
                }
            }
        }
    }

    @Inject(method={"makeAreaOfEffectCloud"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$makeCloud(ItemStack p_190542_1_, Potion p_190542_2_, CallbackInfo ci, AreaEffectCloud entity) {
        LingeringPotionSplashEvent event = CraftEventFactory.callLingeringPotionSplashEvent((ThrownPotion)this, entity);
        if (event.isCancelled() || entity.m_213877_()) {
            ci.cancel();
            entity.m_146870_();
        }
    }

    @Inject(method={"dowseFire"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z")})
    private void arclight$entityChangeBlock(BlockPos pos, CallbackInfo ci) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)((ThrownPotion)this), pos, Blocks.f_50016_.m_49966_())) {
            ci.cancel();
        }
    }

    @Inject(method={"dowseFire"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;levelEvent(Lnet/minecraft/world/entity/player/Player;ILnet/minecraft/core/BlockPos;I)V")})
    private void arclight$entityChangeBlock2(BlockPos pos, CallbackInfo ci, BlockState state) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)((ThrownPotion)this), pos, (BlockState)state.m_61124_((Property)CampfireBlock.f_51227_, (Comparable)Boolean.valueOf(false)))) {
            ci.cancel();
        }
    }

    @Inject(method={"dowseFire"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/AbstractCandleBlock;extinguish(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;)V")})
    private void arclight$entityChangeBlock3(BlockPos pos, CallbackInfo ci, BlockState state) {
        if (!CraftEventFactory.callEntityChangeBlockEvent((Entity)((ThrownPotion)this), pos, (BlockState)state.m_61124_((Property)AbstractCandleBlock.f_151895_, (Comparable)Boolean.valueOf(false)))) {
            ci.cancel();
        }
    }
}

