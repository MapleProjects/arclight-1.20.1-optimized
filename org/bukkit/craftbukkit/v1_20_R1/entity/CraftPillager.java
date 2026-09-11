/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.monster.Pillager
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.Container;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Pillager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftIllager;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftPillager
extends CraftIllager
implements org.bukkit.entity.Pillager {
    public CraftPillager(CraftServer server, Pillager entity) {
        super(server, (AbstractIllager)entity);
    }

    public Pillager getHandle() {
        return (Pillager)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftPillager";
    }

    @Override
    public Inventory getInventory() {
        return new CraftInventory((Container)this.getHandle().f_33259_);
    }
}

