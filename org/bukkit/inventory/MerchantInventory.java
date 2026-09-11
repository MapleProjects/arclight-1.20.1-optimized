/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MerchantInventory
extends Inventory {
    public int getSelectedRecipeIndex();

    @Nullable
    public MerchantRecipe getSelectedRecipe();

    @NotNull
    public Merchant getMerchant();
}

