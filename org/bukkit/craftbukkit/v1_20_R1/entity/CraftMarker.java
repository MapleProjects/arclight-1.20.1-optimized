/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Marker
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Marker;

public class CraftMarker
extends CraftEntity
implements Marker {
    public CraftMarker(CraftServer server, net.minecraft.world.entity.Marker entity) {
        super(server, (Entity)entity);
    }

    public net.minecraft.world.entity.Marker getHandle() {
        return (net.minecraft.world.entity.Marker)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftMarker";
    }
}

