/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.ListIterator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryIterator
implements ListIterator<ItemStack> {
    private final Inventory inventory;
    private int nextIndex;
    private Boolean lastDirection;

    InventoryIterator(Inventory craftInventory) {
        this.inventory = craftInventory;
        this.nextIndex = 0;
    }

    InventoryIterator(Inventory craftInventory, int index) {
        this.inventory = craftInventory;
        this.nextIndex = index;
    }

    @Override
    public boolean hasNext() {
        return this.nextIndex < this.inventory.getSize();
    }

    @Override
    public ItemStack next() {
        this.lastDirection = true;
        return this.inventory.getItem(this.nextIndex++);
    }

    @Override
    public int nextIndex() {
        return this.nextIndex;
    }

    @Override
    public boolean hasPrevious() {
        return this.nextIndex > 0;
    }

    @Override
    public ItemStack previous() {
        this.lastDirection = false;
        return this.inventory.getItem(--this.nextIndex);
    }

    @Override
    public int previousIndex() {
        return this.nextIndex - 1;
    }

    @Override
    public void set(ItemStack item) {
        Preconditions.checkState((this.lastDirection != null ? 1 : 0) != 0, (Object)"No current item!");
        int i = this.lastDirection != false ? this.nextIndex - 1 : this.nextIndex;
        this.inventory.setItem(i, item);
    }

    @Override
    public void add(ItemStack item) {
        throw new UnsupportedOperationException("Can't change the size of an inventory!");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Can't change the size of an inventory!");
    }
}

