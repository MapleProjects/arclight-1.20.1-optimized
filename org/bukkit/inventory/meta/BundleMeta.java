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

public interface BundleMeta
extends ItemMeta {
    public boolean hasItems();

    @NotNull
    public List<ItemStack> getItems();

    public void setItems(@Nullable List<ItemStack> var1);

    public void addItem(@NotNull ItemStack var1);
}

