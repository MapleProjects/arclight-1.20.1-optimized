/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.Arrow
 *  net.minecraft.world.item.alchemy.Potion
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.alchemy.Potion;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.entity.Arrow;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CraftTippedArrow
extends CraftArrow
implements Arrow {
    public CraftTippedArrow(CraftServer server, net.minecraft.world.entity.projectile.Arrow entity) {
        super(server, (AbstractArrow)entity);
    }

    public net.minecraft.world.entity.projectile.Arrow getHandle() {
        return (net.minecraft.world.entity.projectile.Arrow)this.entity;
    }

    @Override
    public String toString() {
        return "CraftTippedArrow";
    }

    @Override
    public boolean addCustomEffect(PotionEffect effect, boolean override) {
        int effectId = effect.getType().getId();
        MobEffectInstance existing = null;
        for (MobEffectInstance mobEffect : this.getHandle().f_36852_) {
            if (MobEffect.m_19459_((MobEffect)mobEffect.m_19544_()) != effectId) continue;
            existing = mobEffect;
        }
        if (existing != null) {
            if (!override) {
                return false;
            }
            this.getHandle().f_36852_.remove(existing);
        }
        this.getHandle().m_36870_(CraftPotionUtil.fromBukkit(effect));
        this.getHandle().m_36890_();
        return true;
    }

    @Override
    public void clearCustomEffects() {
        this.getHandle().f_36852_.clear();
        this.getHandle().m_36890_();
    }

    @Override
    public List<PotionEffect> getCustomEffects() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (MobEffectInstance effect : this.getHandle().f_36852_) {
            builder.add((Object)CraftPotionUtil.toBukkit(effect));
        }
        return builder.build();
    }

    @Override
    public boolean hasCustomEffect(PotionEffectType type) {
        for (MobEffectInstance effect : this.getHandle().f_36852_) {
            if (!CraftPotionUtil.equals(effect.m_19544_(), type)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCustomEffects() {
        return !this.getHandle().f_36852_.isEmpty();
    }

    @Override
    public boolean removeCustomEffect(PotionEffectType effect) {
        int effectId = effect.getId();
        MobEffectInstance existing = null;
        for (MobEffectInstance mobEffect : this.getHandle().f_36852_) {
            if (MobEffect.m_19459_((MobEffect)mobEffect.m_19544_()) != effectId) continue;
            existing = mobEffect;
        }
        if (existing == null) {
            return false;
        }
        this.getHandle().f_36852_.remove(existing);
        this.getHandle().m_36890_();
        return true;
    }

    @Override
    public void setBasePotionData(PotionData data) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"PotionData cannot be null");
        this.getHandle().f_36855_ = (Potion)BuiltInRegistries.f_256980_.m_7745_(new ResourceLocation(CraftPotionUtil.fromBukkit(data)));
    }

    @Override
    public PotionData getBasePotionData() {
        return CraftPotionUtil.toBukkit(BuiltInRegistries.f_256980_.m_7981_((Object)this.getHandle().f_36855_).toString());
    }

    @Override
    public void setColor(Color color) {
        int colorRGB = color == null ? -1 : color.asRGB();
        this.getHandle().m_36882_(colorRGB);
    }

    @Override
    public Color getColor() {
        if (this.getHandle().m_36889_() <= -1) {
            return null;
        }
        return Color.fromRGB(this.getHandle().m_36889_());
    }
}

