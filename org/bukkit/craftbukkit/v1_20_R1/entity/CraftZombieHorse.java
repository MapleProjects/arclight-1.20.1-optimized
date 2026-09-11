/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.animal.horse.ZombieHorse
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.ZombieHorse;

public class CraftZombieHorse
extends CraftAbstractHorse
implements ZombieHorse {
    public CraftZombieHorse(CraftServer server, net.minecraft.world.entity.animal.horse.ZombieHorse entity) {
        super(server, (AbstractHorse)entity);
    }

    @Override
    public String toString() {
        return "CraftZombieHorse";
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.UNDEAD_HORSE;
    }
}

