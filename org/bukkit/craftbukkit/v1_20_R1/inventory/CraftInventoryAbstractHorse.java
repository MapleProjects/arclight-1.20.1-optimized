/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryAbstractHorse
extends CraftInventory
implements AbstractHorseInventory {
    public CraftInventoryAbstractHorse(Container inventory) {
        super(inventory);
    }

    @Override
    public ItemStack getSaddle() {
        return this.getItem(0);
    }

    @Override
    public void setSaddle(ItemStack stack) {
        this.setItem(0, stack);
    }
}

