/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import java.util.Map;
import org.bukkit.block.Container;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.FurnaceInventory;
import org.jetbrains.annotations.NotNull;

public interface Furnace
extends Container {
    public short getBurnTime();

    public void setBurnTime(short var1);

    public short getCookTime();

    public void setCookTime(short var1);

    public int getCookTimeTotal();

    public void setCookTimeTotal(int var1);

    @NotNull
    public Map<CookingRecipe<?>, Integer> getRecipesUsed();

    @Override
    @NotNull
    public FurnaceInventory getInventory();

    @Override
    @NotNull
    public FurnaceInventory getSnapshotInventory();
}

