/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.CaveSpider
 *  net.minecraft.world.entity.monster.Spider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Spider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSpider;
import org.bukkit.entity.CaveSpider;

public class CraftCaveSpider
extends CraftSpider
implements CaveSpider {
    public CraftCaveSpider(CraftServer server, net.minecraft.world.entity.monster.CaveSpider entity) {
        super(server, (Spider)entity);
    }

    public net.minecraft.world.entity.monster.CaveSpider getHandle() {
        return (net.minecraft.world.entity.monster.CaveSpider)this.entity;
    }

    @Override
    public String toString() {
        return "CraftCaveSpider";
    }
}

