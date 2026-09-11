/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import org.bukkit.block.Jukebox;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;

public class CraftInventoryJukebox
extends CraftInventory
implements JukeboxInventory {
    public CraftInventoryJukebox(Container inventory) {
        super(inventory);
    }

    @Override
    public void setRecord(ItemStack item) {
        if (item == null) {
            this.inventory.m_7407_(0, 0);
        } else {
            this.setItem(0, item);
        }
    }

    @Override
    public ItemStack getRecord() {
        return this.getItem(0);
    }

    @Override
    public Jukebox getHolder() {
        return (Jukebox)this.inventory.getOwner();
    }
}

