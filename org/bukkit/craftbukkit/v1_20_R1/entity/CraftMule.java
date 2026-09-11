/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractChestedHorse
 *  net.minecraft.world.entity.animal.horse.Mule
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.Mule;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftChestedHorse;
import org.bukkit.entity.Horse;

public class CraftMule
extends CraftChestedHorse
implements org.bukkit.entity.Mule {
    public CraftMule(CraftServer server, Mule entity) {
        super(server, (AbstractChestedHorse)entity);
    }

    @Override
    public String toString() {
        return "CraftMule";
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.MULE;
    }
}

