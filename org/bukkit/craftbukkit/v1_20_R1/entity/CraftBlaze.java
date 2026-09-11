/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Blaze
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Blaze;

public class CraftBlaze
extends CraftMonster
implements Blaze {
    public CraftBlaze(CraftServer server, net.minecraft.world.entity.monster.Blaze entity) {
        super(server, (Monster)entity);
    }

    public net.minecraft.world.entity.monster.Blaze getHandle() {
        return (net.minecraft.world.entity.monster.Blaze)this.entity;
    }

    @Override
    public String toString() {
        return "CraftBlaze";
    }
}

