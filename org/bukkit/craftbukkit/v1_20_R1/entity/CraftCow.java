/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Cow
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Cow;

public class CraftCow
extends CraftAnimals
implements Cow {
    public CraftCow(CraftServer server, net.minecraft.world.entity.animal.Cow entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.Cow getHandle() {
        return (net.minecraft.world.entity.animal.Cow)this.entity;
    }

    @Override
    public String toString() {
        return "CraftCow";
    }
}

