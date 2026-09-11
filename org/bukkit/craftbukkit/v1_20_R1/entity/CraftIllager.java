/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.AbstractIllager
 *  net.minecraft.world.entity.raid.Raider
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftRaider;
import org.bukkit.entity.Illager;

public class CraftIllager
extends CraftRaider
implements Illager {
    public CraftIllager(CraftServer server, AbstractIllager entity) {
        super(server, (Raider)entity);
    }

    public AbstractIllager getHandle() {
        return (AbstractIllager)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftIllager";
    }
}

