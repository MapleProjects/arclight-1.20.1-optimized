/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.item.alchemy.Potions
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.projectile.ArrowEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.AbstractArrowMixin;
import java.util.Collection;
import java.util.Set;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Arrow.class})
public abstract class ArrowEntityMixin
extends AbstractArrowMixin
implements ArrowEntityBridge {
    @Shadow
    @Final
    private static EntityDataAccessor<Integer> f_36854_;
    @Shadow
    @Final
    public Set<MobEffectInstance> f_36852_;
    @Shadow
    private Potion f_36855_;

    @Inject(method={"doPostHurtEffects"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$arrowHit(LivingEntity living, CallbackInfo ci) {
        ((LivingEntityBridge)living).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.ARROW);
    }

    public void refreshEffects() {
        this.m_20088_().m_135381_(f_36854_, (Object)PotionUtils.m_43564_((Collection)PotionUtils.m_43561_((Potion)this.f_36855_, this.f_36852_)));
    }

    @Override
    public void bridge$refreshEffects() {
        this.refreshEffects();
    }

    public String getPotionType() {
        return BuiltInRegistries.f_256980_.m_7981_((Object)this.f_36855_).toString();
    }

    public void setPotionType(String string) {
        this.f_36855_ = (Potion)BuiltInRegistries.f_256980_.m_7745_(new ResourceLocation(string));
        this.m_20088_().m_135381_(f_36854_, (Object)PotionUtils.m_43564_((Collection)PotionUtils.m_43561_((Potion)this.f_36855_, this.f_36852_)));
    }

    public boolean isTipped() {
        return !this.f_36852_.isEmpty() || this.f_36855_ != Potions.f_43598_;
    }

    @Override
    public boolean bridge$isTipped() {
        return this.isTipped();
    }
}

