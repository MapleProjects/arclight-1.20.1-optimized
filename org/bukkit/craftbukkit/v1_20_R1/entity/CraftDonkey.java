/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractChestedHorse
 *  net.minecraft.world.entity.animal.horse.Donkey
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Horse;

public class CraftDonkey
extends CraftChestedHorse
implements Donkey {
    public CraftDonkey(CraftServer server, net.minecraft.world.entity.animal.horse.Donkey entity) {
        super(server, (AbstractChestedHorse)entity);
    }

    @Override
    public String toString() {
        return "CraftDonkey";
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.DONKEY;
    }
}

