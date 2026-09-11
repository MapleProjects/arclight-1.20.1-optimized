/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;

public class CraftResultInventory
extends CraftInventory {
    private final Container resultInventory;

    public CraftResultInventory(Container inventory, Container resultInventory) {
        super(inventory);
        this.resultInventory = resultInventory;
    }

    public Container getResultInventory() {
        return this.resultInventory;
    }

    public Container getIngredientsInventory() {
        return this.inventory;
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int slot) {
        if (slot < this.getIngredientsInventory().m_6643_()) {
            ItemStack item = this.getIngredientsInventory().m_8020_(slot);
            return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
        }
        ItemStack item = this.getResultInventory().m_8020_(slot - this.getIngredientsInventory().m_6643_());
        return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
    }

    @Override
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
        if (index < this.getIngredientsInventory().m_6643_()) {
            this.getIngredientsInventory().m_6836_(index, CraftItemStack.asNMSCopy(item));
        } else {
            this.getResultInventory().m_6836_(index - this.getIngredientsInventory().m_6643_(), CraftItemStack.asNMSCopy(item));
        }
    }

    @Override
    public int getSize() {
        return this.getResultInventory().m_6643_() + this.getIngredientsInventory().m_6643_();
    }
}

