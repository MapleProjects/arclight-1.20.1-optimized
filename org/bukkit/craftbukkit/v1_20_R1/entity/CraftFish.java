/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.WaterAnimal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWaterMob;
import org.bukkit.entity.Fish;

public class CraftFish
extends CraftWaterMob
implements Fish {
    public CraftFish(CraftServer server, AbstractFish entity) {
        super(server, (WaterAnimal)entity);
    }

    public AbstractFish getHandle() {
        return (AbstractFish)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFish";
    }
}

