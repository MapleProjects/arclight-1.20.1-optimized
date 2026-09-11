/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.monster.Slime
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
import org.bukkit.entity.Slime;

public class CraftSlime
extends CraftMob
implements Slime,
CraftEnemy {
    public CraftSlime(CraftServer server, net.minecraft.world.entity.monster.Slime entity) {
        super(server, (Mob)entity);
    }

    @Override
    public int getSize() {
        return this.getHandle().m_33632_();
    }

    @Override
    public void setSize(int size) {
        this.getHandle().m_7839_(size, true);
    }

    public net.minecraft.world.entity.monster.Slime getHandle() {
        return (net.minecraft.world.entity.monster.Slime)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSlime";
    }
}

