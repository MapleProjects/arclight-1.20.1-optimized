/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.Locale;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Frog
extends Animals {
    @Nullable
    public Entity getTongueTarget();

    public void setTongueTarget(@Nullable Entity var1);

    @NotNull
    public Variant getVariant();

    public void setVariant(@NotNull Variant var1);

    public static enum Variant implements Keyed
    {
        TEMPERATE,
        WARM,
        COLD;

        private final NamespacedKey key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));

        @Override
        @NotNull
        public NamespacedKey getKey() {
            return this.key;
        }
    }
}

