/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.MinecartHopper
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.Container;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.inventory.Inventory;

public final class CraftMinecartHopper
extends CraftMinecartContainer
implements HopperMinecart {
    private final CraftInventory inventory;

    public CraftMinecartHopper(CraftServer server, MinecartHopper entity) {
        super(server, (AbstractMinecart)entity);
        this.inventory = new CraftInventory((Container)entity);
    }

    @Override
    public String toString() {
        return "CraftMinecartHopper{inventory=" + this.inventory + '}';
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public boolean isEnabled() {
        return ((MinecartHopper)this.getHandle()).m_38617_();
    }

    @Override
    public void setEnabled(boolean enabled) {
        ((MinecartHopper)this.getHandle()).m_38613_(enabled);
    }
}

