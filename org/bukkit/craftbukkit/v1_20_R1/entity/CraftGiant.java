/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Giant
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Giant;

public class CraftGiant
extends CraftMonster
implements Giant {
    public CraftGiant(CraftServer server, net.minecraft.world.entity.monster.Giant entity) {
        super(server, (Monster)entity);
    }

    public net.minecraft.world.entity.monster.Giant getHandle() {
        return (net.minecraft.world.entity.monster.Giant)this.entity;
    }

    @Override
    public String toString() {
        return "CraftGiant";
    }
}

