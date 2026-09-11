/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public interface SuspiciousStewMeta
extends ItemMeta {
    public boolean hasCustomEffects();

    @NotNull
    public List<PotionEffect> getCustomEffects();

    public boolean addCustomEffect(@NotNull PotionEffect var1, boolean var2);

    public boolean removeCustomEffect(@NotNull PotionEffectType var1);

    public boolean hasCustomEffect(@NotNull PotionEffectType var1);

    public boolean clearCustomEffects();

    @Override
    public SuspiciousStewMeta clone();
}

