/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.PathfinderMob
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.PathfinderMob;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.entity.Ageable;

public class CraftAgeable
extends CraftCreature
implements Ageable {
    public CraftAgeable(CraftServer server, AgeableMob entity) {
        super(server, (PathfinderMob)entity);
    }

    @Override
    public int getAge() {
        return this.getHandle().m_146764_();
    }

    @Override
    public void setAge(int age) {
        this.getHandle().m_146762_(age);
    }

    @Override
    public void setAgeLock(boolean lock) {
        this.getHandle().ageLocked = lock;
    }

    @Override
    public boolean getAgeLock() {
        return this.getHandle().ageLocked;
    }

    @Override
    public void setBaby() {
        if (this.isAdult()) {
            this.setAge(-24000);
        }
    }

    @Override
    public void setAdult() {
        if (!this.isAdult()) {
            this.setAge(0);
        }
    }

    @Override
    public boolean isAdult() {
        return this.getAge() >= 0;
    }

    @Override
    public boolean canBreed() {
        return this.getAge() == 0;
    }

    @Override
    public void setBreed(boolean breed) {
        if (breed) {
            this.setAge(0);
        } else if (this.isAdult()) {
            this.setAge(6000);
        }
    }

    public AgeableMob getHandle() {
        return (AgeableMob)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAgeable";
    }
}

