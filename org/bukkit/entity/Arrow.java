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
import org.bukkit.entity.AbstractArrow;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Arrow
extends AbstractArrow {
    public void setBasePotionData(@NotNull PotionData var1);

    @NotNull
    public PotionData getBasePotionData();

    @Nullable
    public Color getColor();

    public void setColor(@Nullable Color var1);

    public boolean hasCustomEffects();

    @NotNull
    public List<PotionEffect> getCustomEffects();

    public boolean addCustomEffect(@NotNull PotionEffect var1, boolean var2);

    public boolean removeCustomEffect(@NotNull PotionEffectType var1);

    public boolean hasCustomEffect(@Nullable PotionEffectType var1);

    public void clearCustomEffects();
}

