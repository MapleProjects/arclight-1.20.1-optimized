/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PotionMeta
extends ItemMeta {
    public void setBasePotionData(@NotNull PotionData var1);

    @NotNull
    public PotionData getBasePotionData();

    public boolean hasCustomEffects();

    @NotNull
    public List<PotionEffect> getCustomEffects();

    public boolean addCustomEffect(@NotNull PotionEffect var1, boolean var2);

    public boolean removeCustomEffect(@NotNull PotionEffectType var1);

    public boolean hasCustomEffect(@NotNull PotionEffectType var1);

    @Deprecated
    public boolean setMainEffect(@NotNull PotionEffectType var1);

    public boolean clearCustomEffects();

    public boolean hasColor();

    @Nullable
    public Color getColor();

    public void setColor(@Nullable Color var1);

    @Override
    public PotionMeta clone();
}

