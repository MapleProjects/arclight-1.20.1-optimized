/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.animal.AbstractGolem
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.AbstractGolem;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCreature;
import org.bukkit.entity.Golem;

public class CraftGolem
extends CraftCreature
implements Golem {
    public CraftGolem(CraftServer server, AbstractGolem entity) {
        super(server, (PathfinderMob)entity);
    }

    public AbstractGolem getHandle() {
        return (AbstractGolem)this.entity;
    }

    @Override
    public String toString() {
        return "CraftGolem";
    }
}

