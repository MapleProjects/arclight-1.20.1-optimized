/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.FlyingMob
 *  net.minecraft.world.entity.Mob
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
import org.bukkit.entity.Flying;

public class CraftFlying
extends CraftMob
implements Flying {
    public CraftFlying(CraftServer server, FlyingMob entity) {
        super(server, (Mob)entity);
    }

    public FlyingMob getHandle() {
        return (FlyingMob)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFlying";
    }
}

