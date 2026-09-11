/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.animal.horse.SkeletonHorse
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.SkeletonHorse;

public class CraftSkeletonHorse
extends CraftAbstractHorse
implements SkeletonHorse {
    public CraftSkeletonHorse(CraftServer server, net.minecraft.world.entity.animal.horse.SkeletonHorse entity) {
        super(server, (AbstractHorse)entity);
    }

    @Override
    public String toString() {
        return "CraftSkeletonHorse";
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.SKELETON_HORSE;
    }

    public net.minecraft.world.entity.animal.horse.SkeletonHorse getHandle() {
        return (net.minecraft.world.entity.animal.horse.SkeletonHorse)this.entity;
    }

    @Override
    public boolean isTrapped() {
        return this.getHandle().m_30919_();
    }

    @Override
    public void setTrapped(boolean trapped) {
        this.getHandle().m_30923_(trapped);
    }

    @Override
    public int getTrapTime() {
        return this.getHandle().f_30892_;
    }

    @Override
    public void setTrapTime(int trapTime) {
        this.getHandle().f_30892_ = trapTime;
    }
}

