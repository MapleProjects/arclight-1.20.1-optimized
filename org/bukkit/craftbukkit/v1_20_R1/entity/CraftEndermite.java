/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Endermite
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;

public class CraftEndermite
extends CraftMonster
implements org.bukkit.entity.Endermite {
    public CraftEndermite(CraftServer server, Endermite entity) {
        super(server, (Monster)entity);
    }

    public Endermite getHandle() {
        return (Endermite)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftEndermite";
    }

    @Override
    public boolean isPlayerSpawned() {
        return false;
    }

    @Override
    public void setPlayerSpawned(boolean playerSpawned) {
    }
}

