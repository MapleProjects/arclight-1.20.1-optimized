/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Zoglin
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Zoglin;

public class CraftZoglin
extends CraftMonster
implements Zoglin {
    public CraftZoglin(CraftServer server, net.minecraft.world.entity.monster.Zoglin entity) {
        super(server, (Monster)entity);
    }

    @Override
    public boolean isBaby() {
        return this.getHandle().m_6162_();
    }

    @Override
    public void setBaby(boolean flag) {
        this.getHandle().m_6863_(flag);
    }

    public net.minecraft.world.entity.monster.Zoglin getHandle() {
        return (net.minecraft.world.entity.monster.Zoglin)this.entity;
    }

    @Override
    public String toString() {
        return "CraftZoglin";
    }

    @Override
    public int getAge() {
        return this.getHandle().m_6162_() ? -1 : 0;
    }

    @Override
    public void setAge(int i) {
        this.getHandle().m_6863_(i < 0);
    }

    @Override
    public void setAgeLock(boolean b) {
    }

    @Override
    public boolean getAgeLock() {
        return false;
    }

    @Override
    public void setBaby() {
        this.getHandle().m_6863_(true);
    }

    @Override
    public void setAdult() {
        this.getHandle().m_6863_(false);
    }

    @Override
    public boolean isAdult() {
        return !this.getHandle().m_6162_();
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public void setBreed(boolean b) {
    }
}

