/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CrossbowMeta
extends ItemMeta {
    public boolean hasChargedProjectiles();

    @NotNull
    public List<ItemStack> getChargedProjectiles();

    public void setChargedProjectiles(@Nullable List<ItemStack> var1);

    public void addChargedProjectile(@NotNull ItemStack var1);
}

