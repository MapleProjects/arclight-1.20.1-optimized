/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;

public class CraftMonster
extends CraftCreature
implements org.bukkit.entity.Monster,
CraftEnemy {
    public CraftMonster(CraftServer server, Monster entity) {
        super(server, (PathfinderMob)entity);
    }

    public Monster getHandle() {
        return (Monster)this.entity;
    }

    @Override
    public String toString() {
        return "CraftMonster";
    }
}

