/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ambient.AmbientCreature
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMob;
import org.bukkit.entity.Ambient;

public class CraftAmbient
extends CraftMob
implements Ambient {
    public CraftAmbient(CraftServer server, AmbientCreature entity) {
        super(server, (Mob)entity);
    }

    public AmbientCreature getHandle() {
        return (AmbientCreature)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAmbient";
    }
}

