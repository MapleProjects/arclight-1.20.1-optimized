/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.level.block.entity.LecternBlockEntity$1
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import org.bukkit.block.Lectern;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.LecternInventory;

public class CraftInventoryLectern
extends CraftInventory
implements LecternInventory {
    public MenuProvider tile;

    public CraftInventoryLectern(Container inventory) {
        super(inventory);
        if (inventory instanceof LecternBlockEntity.1) {
            this.tile = ((LecternBlockEntity.1)inventory).getLectern();
        }
    }

    @Override
    public Lectern getHolder() {
        return (Lectern)this.inventory.getOwner();
    }
}

