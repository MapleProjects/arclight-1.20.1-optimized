/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DoubleChest
implements InventoryHolder {
    private DoubleChestInventory inventory;

    public DoubleChest(@NotNull DoubleChestInventory chest) {
        this.inventory = chest;
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return this.inventory;
    }

    @Nullable
    public InventoryHolder getLeftSide() {
        return this.inventory.getLeftSide().getHolder();
    }

    @Nullable
    public InventoryHolder getRightSide() {
        return this.inventory.getRightSide().getHolder();
    }

    @NotNull
    public Location getLocation() {
        return this.getInventory().getLocation();
    }

    @Nullable
    public World getWorld() {
        return this.getLocation().getWorld();
    }

    public double getX() {
        return this.getLocation().getX();
    }

    public double getY() {
        return this.getLocation().getY();
    }

    public double getZ() {
        return this.getLocation().getZ();
    }
}

