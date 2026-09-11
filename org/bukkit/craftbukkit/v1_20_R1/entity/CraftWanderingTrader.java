/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.npc.WanderingTrader
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.WanderingTrader;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractVillager;

public class CraftWanderingTrader
extends CraftAbstractVillager
implements org.bukkit.entity.WanderingTrader {
    public CraftWanderingTrader(CraftServer server, WanderingTrader entity) {
        super(server, (AbstractVillager)entity);
    }

    public WanderingTrader getHandle() {
        return (WanderingTrader)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWanderingTrader";
    }

    @Override
    public int getDespawnDelay() {
        return this.getHandle().m_35876_();
    }

    @Override
    public void setDespawnDelay(int despawnDelay) {
        this.getHandle().m_35891_(despawnDelay);
    }
}

