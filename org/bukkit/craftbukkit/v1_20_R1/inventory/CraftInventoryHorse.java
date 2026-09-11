/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAbstractHorse;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryHorse
extends CraftInventoryAbstractHorse
implements HorseInventory {
    public CraftInventoryHorse(Container inventory) {
        super(inventory);
    }

    @Override
    public ItemStack getArmor() {
        return this.getItem(1);
    }

    @Override
    public void setArmor(ItemStack stack) {
        this.setItem(1, stack);
    }
}

