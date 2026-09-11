/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.animal.WaterAnimal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.WaterAnimal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.entity.WaterMob;

public class CraftWaterMob
extends CraftCreature
implements WaterMob {
    public CraftWaterMob(CraftServer server, WaterAnimal entity) {
        super(server, (PathfinderMob)entity);
    }

    public WaterAnimal getHandle() {
        return (WaterAnimal)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWaterMob";
    }
}

