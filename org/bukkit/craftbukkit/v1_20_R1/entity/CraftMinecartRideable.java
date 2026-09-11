/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecart;
import org.bukkit.entity.minecart.RideableMinecart;

public class CraftMinecartRideable
extends CraftMinecart
implements RideableMinecart {
    public CraftMinecartRideable(CraftServer server, AbstractMinecart entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftMinecartRideable";
    }
}

