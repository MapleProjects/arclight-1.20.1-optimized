/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAbstractHorse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;

public class CraftInventoryLlama
extends CraftInventoryAbstractHorse
implements LlamaInventory {
    public CraftInventoryLlama(Container inventory) {
        super(inventory);
    }

    @Override
    public ItemStack getDecor() {
        return this.getItem(1);
    }

    @Override
    public void setDecor(ItemStack stack) {
        this.setItem(1, stack);
    }
}

