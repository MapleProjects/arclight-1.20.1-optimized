/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface FurnaceInventory
extends Inventory {
    @Nullable
    public ItemStack getResult();

    @Nullable
    public ItemStack getFuel();

    @Nullable
    public ItemStack getSmelting();

    public void setFuel(@Nullable ItemStack var1);

    public void setResult(@Nullable ItemStack var1);

    public void setSmelting(@Nullable ItemStack var1);

    @Override
    @Nullable
    public Furnace getHolder();
}

