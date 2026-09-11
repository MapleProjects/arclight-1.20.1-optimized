/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.level.block.entity.BeaconBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.tileentity.BeaconTileEntityBridge;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BeaconBlockEntity.class})
public abstract class BeaconTileEntityMixin
implements BeaconTileEntityBridge {
    @Shadow
    @Nullable
    public MobEffect f_58652_;
    @Shadow
    public int f_58650_;
    @Shadow
    @Nullable
    public MobEffect f_58653_;

    @Inject(method={"load"}, at={@At(value="RETURN")})
    public void arclight$level(CompoundTag compound, CallbackInfo ci) {
        this.f_58650_ = compound.m_128451_("Levels");
    }

    public PotionEffect getPrimaryEffect() {
        return this.f_58652_ != null ? CraftPotionUtil.toBukkit(new MobEffectInstance(this.f_58652_, this.getLevel(), (int)this.getAmplification(), true, true)) : null;
    }

    public PotionEffect getSecondaryEffect() {
        return this.hasSecondaryEffect() ? CraftPotionUtil.toBukkit(new MobEffectInstance(this.f_58653_, this.getLevel(), (int)this.getAmplification(), true, true)) : null;
    }

    private byte getAmplification() {
        byte b0 = 0;
        if (this.f_58650_ >= 4 && this.f_58652_ == this.f_58653_) {
            b0 = 1;
        }
        return b0;
    }

    private int getLevel() {
        int i = (9 + this.f_58650_ * 2) * 20;
        return i;
    }

    private boolean hasSecondaryEffect() {
        return this.f_58650_ >= 4 && this.f_58652_ != this.f_58653_ && this.f_58653_ != null;
    }

    @Override
    public PotionEffect bridge$getPrimaryEffect() {
        return this.getPrimaryEffect();
    }

    @Override
    public PotionEffect bridge$getSecondaryEffect() {
        return this.getSecondaryEffect();
    }
}

