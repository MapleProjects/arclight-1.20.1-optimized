/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Vehicle;

public abstract class CraftVehicle
extends CraftEntity
implements Vehicle {
    public CraftVehicle(CraftServer server, Entity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftVehicle{passenger=" + this.getPassenger() + '}';
    }
}

