/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.goat.Goat
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Goat;

public class CraftGoat
extends CraftAnimals
implements Goat {
    public CraftGoat(CraftServer server, net.minecraft.world.entity.animal.goat.Goat entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.goat.Goat getHandle() {
        return (net.minecraft.world.entity.animal.goat.Goat)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftGoat";
    }

    @Override
    public boolean hasLeftHorn() {
        return this.getHandle().m_218758_();
    }

    @Override
    public void setLeftHorn(boolean hasHorn) {
        this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.animal.goat.Goat.f_218750_, (Object)hasHorn);
    }

    @Override
    public boolean hasRightHorn() {
        return this.getHandle().m_218759_();
    }

    @Override
    public void setRightHorn(boolean hasHorn) {
        this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.animal.goat.Goat.f_218751_, (Object)hasHorn);
    }

    @Override
    public boolean isScreaming() {
        return this.getHandle().m_149397_();
    }

    @Override
    public void setScreaming(boolean screaming) {
        this.getHandle().m_149405_(screaming);
    }
}

