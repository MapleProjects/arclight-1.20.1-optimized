/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CraftingInventory
extends Inventory {
    @Nullable
    public ItemStack getResult();

    @NotNull
    public ItemStack[] getMatrix();

    public void setResult(@Nullable ItemStack var1);

    public void setMatrix(@NotNull ItemStack[] var1);

    @Nullable
    public Recipe getRecipe();
}

