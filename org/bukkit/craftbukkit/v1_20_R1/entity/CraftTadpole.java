/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.frog.Tadpole
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractFish;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFish;
import org.bukkit.entity.Tadpole;

public class CraftTadpole
extends CraftFish
implements Tadpole {
    public CraftTadpole(CraftServer server, net.minecraft.world.entity.animal.frog.Tadpole entity) {
        super(server, (AbstractFish)entity);
    }

    public net.minecraft.world.entity.animal.frog.Tadpole getHandle() {
        return (net.minecraft.world.entity.animal.frog.Tadpole)this.entity;
    }

    @Override
    public String toString() {
        return "CraftTadpole";
    }

    @Override
    public int getAge() {
        return this.getHandle().f_218680_;
    }

    @Override
    public void setAge(int age) {
        this.getHandle().f_218680_ = age;
    }
}

