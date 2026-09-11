/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Spider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;

public class CraftSpider
extends CraftMonster
implements org.bukkit.entity.Spider {
    public CraftSpider(CraftServer server, Spider entity) {
        super(server, (Monster)entity);
    }

    public Spider getHandle() {
        return (Spider)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSpider";
    }
}

