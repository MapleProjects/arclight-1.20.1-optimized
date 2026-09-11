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
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
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
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.alchemy.Potion;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_20_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class CraftAreaEffectCloud
extends CraftEntity
implements org.bukkit.entity.AreaEffectCloud {
    public CraftAreaEffectCloud(CraftServer server, AreaEffectCloud entity) {
        super(server, (Entity)entity);
    }

    public AreaEffectCloud getHandle() {
        return (AreaEffectCloud)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftAreaEffectCloud";
    }

    @Override
    public int getDuration() {
        return this.getHandle().m_19748_();
    }

    @Override
    public void setDuration(int duration) {
        this.getHandle().m_19734_(duration);
    }

    @Override
    public int getWaitTime() {
        return this.getHandle().f_19688_;
    }

    @Override
    public void setWaitTime(int waitTime) {
        this.getHandle().m_19740_(waitTime);
    }

    @Override
    public int getReapplicationDelay() {
        return this.getHandle().f_19689_;
    }

    @Override
    public void setReapplicationDelay(int delay) {
        this.getHandle().f_19689_ = delay;
    }

    @Override
    public int getDurationOnUse() {
        return this.getHandle().f_19691_;
    }

    @Override
    public void setDurationOnUse(int duration) {
        this.getHandle().f_19691_ = duration;
    }

    @Override
    public float getRadius() {
        return this.getHandle().m_19743_();
    }

    @Override
    public void setRadius(float radius) {
        this.getHandle().m_19712_(radius);
    }

    @Override
    public float getRadiusOnUse() {
        return this.getHandle().f_19692_;
    }

    @Override
    public void setRadiusOnUse(float radius) {
        this.getHandle().m_19732_(radius);
    }

    @Override
    public float getRadiusPerTick() {
        return this.getHandle().f_19693_;
    }

    @Override
    public void setRadiusPerTick(float radius) {
        this.getHandle().m_19738_(radius);
    }

    @Override
    public Particle getParticle() {
        return CraftParticle.toBukkit(this.getHandle().m_19745_());
    }

    @Override
    public void setParticle(Particle particle) {
        this.setParticle(particle, null);
    }

    @Override
    public <T> void setParticle(Particle particle, T data) {
        this.getHandle().m_19724_(CraftParticle.toNMS(particle, data));
    }

    @Override
    public Color getColor() {
        return Color.fromRGB(this.getHandle().m_19744_());
    }

    @Override
    public void setColor(Color color) {
        this.getHandle().m_19714_(color.asRGB());
    }

    @Override
    public boolean addCustomEffect(PotionEffect effect, boolean override) {
        int effectId = effect.getType().getId();
        MobEffectInstance existing = null;
        for (MobEffectInstance mobEffect : this.getHandle().f_19685_) {
            if (MobEffect.m_19459_((MobEffect)mobEffect.m_19544_()) != effectId) continue;
            existing = mobEffect;
        }
        if (existing != null) {
            if (!override) {
                return false;
            }
            this.getHandle().f_19685_.remove(existing);
        }
        this.getHandle().m_19716_(CraftPotionUtil.fromBukkit(effect));
        this.getHandle().m_19750_();
        return true;
    }

    @Override
    public void clearCustomEffects() {
        this.getHandle().f_19685_.clear();
        this.getHandle().m_19750_();
    }

    @Override
    public List<PotionEffect> getCustomEffects() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (MobEffectInstance effect : this.getHandle().f_19685_) {
            builder.add((Object)CraftPotionUtil.toBukkit(effect));
        }
        return builder.build();
    }

    @Override
    public boolean hasCustomEffect(PotionEffectType type) {
        for (MobEffectInstance effect : this.getHandle().f_19685_) {
            if (!CraftPotionUtil.equals(effect.m_19544_(), type)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCustomEffects() {
        return !this.getHandle().f_19685_.isEmpty();
    }

    @Override
    public boolean removeCustomEffect(PotionEffectType effect) {
        int effectId = effect.getId();
        MobEffectInstance existing = null;
        for (MobEffectInstance mobEffect : this.getHandle().f_19685_) {
            if (MobEffect.m_19459_((MobEffect)mobEffect.m_19544_()) != effectId) continue;
            existing = mobEffect;
        }
        if (existing == null) {
            return false;
        }
        this.getHandle().f_19685_.remove(existing);
        this.getHandle().m_19750_();
        return true;
    }

    @Override
    public void setBasePotionData(PotionData data) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"PotionData cannot be null");
        this.getHandle().m_19722_((Potion)BuiltInRegistries.f_256980_.m_7745_(new ResourceLocation(CraftPotionUtil.fromBukkit(data))));
    }

    @Override
    public PotionData getBasePotionData() {
        return CraftPotionUtil.toBukkit(BuiltInRegistries.f_256980_.m_7981_((Object)this.getHandle().f_19701_).toString());
    }

    @Override
    public ProjectileSource getSource() {
        net.minecraft.world.entity.LivingEntity source = this.getHandle().m_19749_();
        return source == null ? null : (LivingEntity)((Object)source.getBukkitEntity());
    }

    @Override
    public void setSource(ProjectileSource shooter) {
        if (shooter instanceof CraftLivingEntity var2_3) {
            this.getHandle().m_19718_(craftLivingEntity.getHandle());
        } else {
            this.getHandle().m_19718_(null);
        }
    }
}

