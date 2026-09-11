/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.MagmaCube
 *  net.minecraft.world.entity.monster.Slime
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.Slime;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSlime;
import org.bukkit.entity.MagmaCube;

public class CraftMagmaCube
extends CraftSlime
implements MagmaCube {
    public CraftMagmaCube(CraftServer server, net.minecraft.world.entity.monster.MagmaCube entity) {
        super(server, (Slime)entity);
    }

    public net.minecraft.world.entity.monster.MagmaCube getHandle() {
        return (net.minecraft.world.entity.monster.MagmaCube)this.entity;
    }

    @Override
    public String toString() {
        return "CraftMagmaCube";
    }
}

