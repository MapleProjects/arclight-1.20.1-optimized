/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.animal.Wolf
 *  net.minecraft.world.item.DyeColor
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Wolf;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTameableAnimal;

public class CraftWolf
extends CraftTameableAnimal
implements org.bukkit.entity.Wolf {
    public CraftWolf(CraftServer server, Wolf wolf) {
        super(server, (TamableAnimal)wolf);
    }

    @Override
    public boolean isAngry() {
        return this.getHandle().m_21660_();
    }

    @Override
    public void setAngry(boolean angry) {
        if (angry) {
            this.getHandle().m_6825_();
        } else {
            this.getHandle().m_21662_();
        }
    }

    public Wolf getHandle() {
        return (Wolf)this.entity;
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getByWoolData((byte)this.getHandle().m_30428_().m_41060_());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        this.getHandle().m_30397_(net.minecraft.world.item.DyeColor.m_41053_((int)color.getWoolData()));
    }

    @Override
    public boolean isWet() {
        return this.getHandle().m_30426_();
    }

    @Override
    public float getTailAngle() {
        return this.getHandle().m_30427_();
    }

    @Override
    public boolean isInterested() {
        return this.getHandle().m_30429_();
    }

    @Override
    public void setInterested(boolean flag) {
        this.getHandle().m_30444_(flag);
    }
}

