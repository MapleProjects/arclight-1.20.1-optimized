/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.Cod
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Cod;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFish;

public class CraftCod
extends CraftFish
implements org.bukkit.entity.Cod {
    public CraftCod(CraftServer server, Cod entity) {
        super(server, (AbstractFish)entity);
    }

    public Cod getHandle() {
        return (Cod)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftCod";
    }
}

