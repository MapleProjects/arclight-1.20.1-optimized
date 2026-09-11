/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.PathfinderMob
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
import org.bukkit.entity.Creature;

public class CraftCreature
extends CraftMob
implements Creature {
    public CraftCreature(CraftServer server, PathfinderMob entity) {
        super(server, (Mob)entity);
    }

    public PathfinderMob getHandle() {
        return (PathfinderMob)this.entity;
    }

    @Override
    public String toString() {
        return "CraftCreature";
    }
}

