/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryBeacon
extends CraftInventory
implements BeaconInventory {
    public CraftInventoryBeacon(Container beacon) {
        super(beacon);
    }

    @Override
    public void setItem(ItemStack item) {
        this.setItem(0, item);
    }

    @Override
    public ItemStack getItem() {
        return this.getItem(0);
    }
}

