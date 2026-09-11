/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.armortrim.TrimMaterial
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory.trim;

import net.minecraft.world.item.armortrim.TrimMaterial;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public class CraftTrimMaterial
implements org.bukkit.inventory.meta.trim.TrimMaterial {
    private final NamespacedKey key;
    private final TrimMaterial handle;

    public CraftTrimMaterial(NamespacedKey key, TrimMaterial handle) {
        this.key = key;
        this.handle = handle;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    public TrimMaterial getHandle() {
        return this.handle;
    }
}

