/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.MinecartChest
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.Container;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;

public class CraftMinecartChest
extends CraftMinecartContainer
implements StorageMinecart {
    private final CraftInventory inventory;

    public CraftMinecartChest(CraftServer server, MinecartChest entity) {
        super(server, (AbstractMinecart)entity);
        this.inventory = new CraftInventory((Container)entity);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public String toString() {
        return "CraftMinecartChest{inventory=" + this.inventory + '}';
    }
}

