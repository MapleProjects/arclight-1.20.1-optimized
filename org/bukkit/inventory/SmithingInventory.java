/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.Nullable;

public interface SmithingInventory
extends Inventory {
    @Nullable
    public ItemStack getResult();

    public void setResult(@Nullable ItemStack var1);

    @Nullable
    public Recipe getRecipe();
}

