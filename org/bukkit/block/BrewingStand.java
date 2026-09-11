/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.Container;
import org.bukkit.inventory.BrewerInventory;
import org.jetbrains.annotations.NotNull;

public interface BrewingStand
extends Container {
    public int getBrewingTime();

    public void setBrewingTime(int var1);

    public int getFuelLevel();

    public void setFuelLevel(int var1);

    @Override
    @NotNull
    public BrewerInventory getInventory();

    @Override
    @NotNull
    public BrewerInventory getSnapshotInventory();
}

