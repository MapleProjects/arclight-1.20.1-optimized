/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Ravager
 *  net.minecraft.world.entity.raid.Raider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRaider;
import org.bukkit.entity.Ravager;

public class CraftRavager
extends CraftRaider
implements Ravager {
    public CraftRavager(CraftServer server, net.minecraft.world.entity.monster.Ravager entity) {
        super(server, (Raider)entity);
    }

    public net.minecraft.world.entity.monster.Ravager getHandle() {
        return (net.minecraft.world.entity.monster.Ravager)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftRavager";
    }
}

