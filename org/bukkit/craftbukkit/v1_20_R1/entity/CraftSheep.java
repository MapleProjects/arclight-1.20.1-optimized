/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.item.DyeColor
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Animal;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Sheep;

public class CraftSheep
extends CraftAnimals
implements Sheep {
    public CraftSheep(CraftServer server, net.minecraft.world.entity.animal.Sheep entity) {
        super(server, (Animal)entity);
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.getByWoolData((byte)this.getHandle().m_29874_().m_41060_());
    }

    @Override
    public void setColor(DyeColor color) {
        this.getHandle().m_29855_(net.minecraft.world.item.DyeColor.m_41053_((int)color.getWoolData()));
    }

    @Override
    public boolean isSheared() {
        return this.getHandle().m_29875_();
    }

    @Override
    public void setSheared(boolean flag) {
        this.getHandle().m_29878_(flag);
    }

    public net.minecraft.world.entity.animal.Sheep getHandle() {
        return (net.minecraft.world.entity.animal.Sheep)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSheep";
    }
}

