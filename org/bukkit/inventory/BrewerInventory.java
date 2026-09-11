/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface BrewerInventory
extends Inventory {
    @Nullable
    public ItemStack getIngredient();

    public void setIngredient(@Nullable ItemStack var1);

    @Nullable
    public ItemStack getFuel();

    public void setFuel(@Nullable ItemStack var1);

    @Override
    @Nullable
    public BrewingStand getHolder();
}

