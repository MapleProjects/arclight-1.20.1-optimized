/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.potion;

import java.util.Collection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

public interface PotionBrewer {
    @NotNull
    public PotionEffect createEffect(@NotNull PotionEffectType var1, int var2, int var3);

    @Deprecated
    @NotNull
    public Collection<PotionEffect> getEffectsFromDamage(int var1);

    @NotNull
    public Collection<PotionEffect> getEffects(@NotNull PotionType var1, boolean var2, boolean var3);
}

