/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Squid
 *  net.minecraft.world.entity.animal.WaterAnimal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftWaterMob;

public class CraftSquid
extends CraftWaterMob
implements org.bukkit.entity.Squid {
    public CraftSquid(CraftServer server, Squid entity) {
        super(server, (WaterAnimal)entity);
    }

    public Squid getHandle() {
        return (Squid)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSquid";
    }
}

