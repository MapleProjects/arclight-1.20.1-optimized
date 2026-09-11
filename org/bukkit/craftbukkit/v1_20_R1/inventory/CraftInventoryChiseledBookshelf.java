/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ChiseledBookshelfInventory;

public class CraftInventoryChiseledBookshelf
extends CraftInventory
implements ChiseledBookshelfInventory {
    public CraftInventoryChiseledBookshelf(ChiseledBookShelfBlockEntity inventory) {
        super((Container)inventory);
    }

    @Override
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
        ItemStack nms = CraftItemStack.asNMSCopy(item);
        if (nms.m_41619_()) {
            this.getInventory().m_8016_(index);
        } else {
            this.getInventory().m_6836_(index, nms);
        }
    }

    @Override
    public ChiseledBookshelf getHolder() {
        return (ChiseledBookshelf)this.inventory.getOwner();
    }
}

