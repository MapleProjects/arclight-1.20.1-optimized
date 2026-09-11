/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Dolphin
 *  net.minecraft.world.entity.animal.WaterAnimal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWaterMob;

public class CraftDolphin
extends CraftWaterMob
implements org.bukkit.entity.Dolphin {
    public CraftDolphin(CraftServer server, Dolphin entity) {
        super(server, (WaterAnimal)entity);
    }

    public Dolphin getHandle() {
        return (Dolphin)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftDolphin";
    }
}

