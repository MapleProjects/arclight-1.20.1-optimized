/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Husk
 *  net.minecraft.world.entity.monster.Zombie
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftZombie;
import org.bukkit.entity.Husk;

public class CraftHusk
extends CraftZombie
implements Husk {
    public CraftHusk(CraftServer server, net.minecraft.world.entity.monster.Husk entity) {
        super(server, (Zombie)entity);
    }

    @Override
    public String toString() {
        return "CraftHusk";
    }
}

