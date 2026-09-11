/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.animal.Animal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import java.util.UUID;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Tameable;

public class CraftTameableAnimal
extends CraftAnimals
implements Tameable,
Creature {
    public CraftTameableAnimal(CraftServer server, TamableAnimal entity) {
        super(server, (Animal)entity);
    }

    public TamableAnimal getHandle() {
        return (TamableAnimal)super.getHandle();
    }

    public UUID getOwnerUUID() {
        try {
            return this.getHandle().m_21805_();
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public void setOwnerUUID(UUID uuid) {
        this.getHandle().m_21816_(uuid);
    }

    @Override
    public AnimalTamer getOwner() {
        if (this.getOwnerUUID() == null) {
            return null;
        }
        OfflinePlayer owner = this.getServer().getPlayer(this.getOwnerUUID());
        if (owner == null) {
            owner = this.getServer().getOfflinePlayer(this.getOwnerUUID());
        }
        return owner;
    }

    @Override
    public boolean isTamed() {
        return this.getHandle().m_21824_();
    }

    @Override
    public void setOwner(AnimalTamer tamer) {
        if (tamer != null) {
            this.setTamed(true);
            this.getHandle().setTarget(null, null, false);
            this.setOwnerUUID(tamer.getUniqueId());
        } else {
            this.setTamed(false);
            this.setOwnerUUID(null);
        }
    }

    @Override
    public void setTamed(boolean tame) {
        this.getHandle().m_7105_(tame);
        if (!tame) {
            this.setOwnerUUID(null);
        }
    }

    public boolean isSitting() {
        return this.getHandle().m_21825_();
    }

    public void setSitting(boolean sitting) {
        this.getHandle().m_21837_(sitting);
        this.getHandle().m_21839_(sitting);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getClass().getSimpleName()) + "{owner=" + this.getOwner() + ",tamed=" + this.isTamed() + "}";
    }
}

