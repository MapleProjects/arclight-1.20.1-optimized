/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.ZombieVillager
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombieVillager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

public class CraftZombie
extends CraftMonster
implements Zombie {
    public CraftZombie(CraftServer server, net.minecraft.world.entity.monster.Zombie entity) {
        super(server, (Monster)entity);
    }

    public net.minecraft.world.entity.monster.Zombie getHandle() {
        return (net.minecraft.world.entity.monster.Zombie)this.entity;
    }

    @Override
    public String toString() {
        return "CraftZombie";
    }

    @Override
    public boolean isBaby() {
        return this.getHandle().m_6162_();
    }

    @Override
    public void setBaby(boolean flag) {
        this.getHandle().m_6863_(flag);
    }

    @Override
    public boolean isVillager() {
        return this.getHandle() instanceof ZombieVillager;
    }

    @Override
    public void setVillager(boolean flag) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Villager.Profession getVillagerProfession() {
        return null;
    }

    @Override
    public boolean isConverting() {
        return this.getHandle().m_34329_();
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState((boolean)this.isConverting(), (Object)"Entity not converting");
        return this.getHandle().f_34266_;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().f_34266_ = -1;
            this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.monster.Zombie.f_34261_, (Object)false);
        } else {
            this.getHandle().m_34278_(time);
        }
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

    @Override
    public boolean canBreakDoors() {
        return this.getHandle().m_34330_();
    }

    @Override
    public void setCanBreakDoors(boolean flag) {
        this.getHandle().m_34336_(flag);
    }
}

