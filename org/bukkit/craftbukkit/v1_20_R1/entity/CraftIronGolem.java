/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.animal.IronGolem
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractGolem;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGolem;
import org.bukkit.entity.IronGolem;

public class CraftIronGolem
extends CraftGolem
implements IronGolem {
    public CraftIronGolem(CraftServer server, net.minecraft.world.entity.animal.IronGolem entity) {
        super(server, (AbstractGolem)entity);
    }

    public net.minecraft.world.entity.animal.IronGolem getHandle() {
        return (net.minecraft.world.entity.animal.IronGolem)this.entity;
    }

    @Override
    public String toString() {
        return "CraftIronGolem";
    }

    @Override
    public boolean isPlayerCreated() {
        return this.getHandle().m_28876_();
    }

    @Override
    public void setPlayerCreated(boolean playerCreated) {
        this.getHandle().m_28887_(playerCreated);
    }
}

