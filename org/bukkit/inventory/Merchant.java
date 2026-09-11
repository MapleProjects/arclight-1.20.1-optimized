/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import java.util.List;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Merchant {
    @NotNull
    public List<MerchantRecipe> getRecipes();

    public void setRecipes(@NotNull List<MerchantRecipe> var1);

    @NotNull
    public MerchantRecipe getRecipe(int var1) throws IndexOutOfBoundsException;

    public void setRecipe(int var1, @NotNull MerchantRecipe var2) throws IndexOutOfBoundsException;

    public int getRecipeCount();

    public boolean isTrading();

    @Nullable
    public HumanEntity getTrader();
}

