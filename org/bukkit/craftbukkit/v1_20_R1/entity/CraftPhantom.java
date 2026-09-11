/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.FlyingMob
 *  net.minecraft.world.entity.monster.Phantom
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.monster.Phantom;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFlying;

public class CraftPhantom
extends CraftFlying
implements org.bukkit.entity.Phantom,
CraftEnemy {
    public CraftPhantom(CraftServer server, Phantom entity) {
        super(server, (FlyingMob)entity);
    }

    public Phantom getHandle() {
        return (Phantom)super.getHandle();
    }

    @Override
    public int getSize() {
        return this.getHandle().m_33172_();
    }

    @Override
    public void setSize(int sz) {
        this.getHandle().m_33108_(sz);
    }

    @Override
    public String toString() {
        return "CraftPhantom";
    }
}

