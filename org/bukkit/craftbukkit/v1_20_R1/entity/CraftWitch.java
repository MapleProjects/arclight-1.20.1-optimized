/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Witch
 *  net.minecraft.world.entity.raid.Raider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRaider;

public class CraftWitch
extends CraftRaider
implements org.bukkit.entity.Witch {
    public CraftWitch(CraftServer server, Witch entity) {
        super(server, (Raider)entity);
    }

    public Witch getHandle() {
        return (Witch)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWitch";
    }

    @Override
    public boolean isDrinkingPotion() {
        return this.getHandle().m_34161_();
    }
}

