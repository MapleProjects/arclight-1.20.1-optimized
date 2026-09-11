/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface BeaconInventory
extends Inventory {
    public void setItem(@Nullable ItemStack var1);

    @Nullable
    public ItemStack getItem();
}

