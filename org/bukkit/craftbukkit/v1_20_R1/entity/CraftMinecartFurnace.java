/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.MinecartFurnace
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;

public class CraftMinecartFurnace
extends CraftMinecart
implements PoweredMinecart {
    public CraftMinecartFurnace(CraftServer server, MinecartFurnace entity) {
        super(server, (AbstractMinecart)entity);
    }

    public MinecartFurnace getHandle() {
        return (MinecartFurnace)this.entity;
    }

    @Override
    public int getFuel() {
        return this.getHandle().f_38548_;
    }

    @Override
    public void setFuel(int fuel) {
        Preconditions.checkArgument((fuel >= 0 ? 1 : 0) != 0, (Object)"ticks cannot be negative");
        this.getHandle().f_38548_ = fuel;
    }

    @Override
    public String toString() {
        return "CraftMinecartFurnace";
    }
}

