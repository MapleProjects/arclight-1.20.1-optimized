/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Drowned
 *  net.minecraft.world.entity.monster.Zombie
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZombie;

public class CraftDrowned
extends CraftZombie
implements org.bukkit.entity.Drowned {
    public CraftDrowned(CraftServer server, Drowned entity) {
        super(server, (Zombie)entity);
    }

    public Drowned getHandle() {
        return (Drowned)this.entity;
    }

    @Override
    public String toString() {
        return "CraftDrowned";
    }
}

