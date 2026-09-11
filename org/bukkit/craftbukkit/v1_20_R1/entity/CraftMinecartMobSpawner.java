/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.MinecartSpawner
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;

final class CraftMinecartMobSpawner
extends CraftMinecart
implements SpawnerMinecart {
    CraftMinecartMobSpawner(CraftServer server, MinecartSpawner entity) {
        super(server, (AbstractMinecart)entity);
    }

    @Override
    public String toString() {
        return "CraftMinecartMobSpawner";
    }
}

