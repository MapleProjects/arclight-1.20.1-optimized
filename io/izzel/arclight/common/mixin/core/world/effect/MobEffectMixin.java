/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetHealthPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageSources
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.food.FoodData
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.effect;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.util.DamageSourcesBridge;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MobEffect.class})
public class MobEffectMixin {
    @Inject(method={"applyEffectTick"}, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V")})
    public void arclight$healReason1(LivingEntity livingEntity, int amplifier, CallbackInfo ci) {
        ((LivingEntityBridge)livingEntity).bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.MAGIC_REGEN);
    }

    @Inject(method={"applyEffectTick"}, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V")})
    public void arclight$healReason2(LivingEntity livingEntity, int amplifier, CallbackInfo ci) {
        ((LivingEntityBridge)livingEntity).bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.MAGIC);
    }

    @Inject(method={"applyInstantenousEffect"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;heal(F)V")})
    public void arclight$healReason3(Entity source, Entity indirectSource, LivingEntity livingEntity, int amplifier, double health, CallbackInfo ci) {
        ((LivingEntityBridge)livingEntity).bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.MAGIC);
    }

    @Redirect(method={"applyEffectTick"}, at=@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/damagesource/DamageSources;magic()Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource arclight$redirectPoison(DamageSources instance) {
        return ((DamageSourcesBridge)instance).bridge$poison();
    }

    @Redirect(method={"applyEffectTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    public void arclight$foodLevelChange(FoodData foodStats, int foodLevelIn, float foodSaturationModifier, LivingEntity livingEntity, int amplifier) {
        Player playerEntity = (Player)livingEntity;
        int oldFoodLevel = playerEntity.m_36324_().m_38702_();
        FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(playerEntity, foodLevelIn + oldFoodLevel);
        if (!event.isCancelled()) {
            playerEntity.m_36324_().m_38707_(event.getFoodLevel() - oldFoodLevel, foodSaturationModifier);
        }
        ((ServerPlayer)playerEntity).f_8906_.m_9829_((Packet)new ClientboundSetHealthPacket(((ServerPlayerEntityBridge)playerEntity).bridge$getBukkitEntity().getScaledHealth(), playerEntity.m_36324_().m_38702_(), playerEntity.m_36324_().m_38722_()));
    }
}

