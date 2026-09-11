/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AreaEffectCloud
extends Entity {
    public int getDuration();

    public void setDuration(int var1);

    public int getWaitTime();

    public void setWaitTime(int var1);

    public int getReapplicationDelay();

    public void setReapplicationDelay(int var1);

    public int getDurationOnUse();

    public void setDurationOnUse(int var1);

    public float getRadius();

    public void setRadius(float var1);

    public float getRadiusOnUse();

    public void setRadiusOnUse(float var1);

    public float getRadiusPerTick();

    public void setRadiusPerTick(float var1);

    @NotNull
    public Particle getParticle();

    public void setParticle(@NotNull Particle var1);

    public <T> void setParticle(@NotNull Particle var1, @Nullable T var2);

    public void setBasePotionData(@NotNull PotionData var1);

    @NotNull
    public PotionData getBasePotionData();

    public boolean hasCustomEffects();

    @NotNull
    public List<PotionEffect> getCustomEffects();

    public boolean addCustomEffect(@NotNull PotionEffect var1, boolean var2);

    public boolean removeCustomEffect(@NotNull PotionEffectType var1);

    public boolean hasCustomEffect(@Nullable PotionEffectType var1);

    public void clearCustomEffects();

    @NotNull
    public Color getColor();

    public void setColor(@NotNull Color var1);

    @Nullable
    public ProjectileSource getSource();

    public void setSource(@Nullable ProjectileSource var1);
}

