/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.FlyingMob
 *  net.minecraft.world.entity.monster.Ghast
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.FlyingMob;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFlying;
import org.bukkit.entity.Ghast;

public class CraftGhast
extends CraftFlying
implements Ghast,
CraftEnemy {
    public CraftGhast(CraftServer server, net.minecraft.world.entity.monster.Ghast entity) {
        super(server, (FlyingMob)entity);
    }

    public net.minecraft.world.entity.monster.Ghast getHandle() {
        return (net.minecraft.world.entity.monster.Ghast)this.entity;
    }

    @Override
    public String toString() {
        return "CraftGhast";
    }

    @Override
    public boolean isCharging() {
        return this.getHandle().m_32756_();
    }

    @Override
    public void setCharging(boolean flag) {
        this.getHandle().m_32758_(flag);
    }
}

