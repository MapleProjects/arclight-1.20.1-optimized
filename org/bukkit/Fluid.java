/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import java.util.Locale;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public enum Fluid implements Keyed
{
    WATER,
    FLOWING_WATER,
    LAVA,
    FLOWING_LAVA;

    private final NamespacedKey key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }
}

