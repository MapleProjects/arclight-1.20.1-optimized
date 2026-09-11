/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.UUID;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAbstractHorse;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;

public abstract class CraftAbstractHorse
extends CraftAnimals
implements AbstractHorse {
    public CraftAbstractHorse(CraftServer server, net.minecraft.world.entity.animal.horse.AbstractHorse entity) {
        super(server, (Animal)entity);
    }

    public net.minecraft.world.entity.animal.horse.AbstractHorse getHandle() {
        return (net.minecraft.world.entity.animal.horse.AbstractHorse)this.entity;
    }

    @Override
    public void setVariant(Horse.Variant variant) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getDomestication() {
        return this.getHandle().m_30624_();
    }

    @Override
    public void setDomestication(int value) {
        Preconditions.checkArgument((value >= 0 && value <= this.getMaxDomestication() ? 1 : 0) != 0, (String)"Domestication level (%s) need to be between %s and %s (max domestication)", (Object)value, (Object)0, (Object)this.getMaxDomestication());
        this.getHandle().m_30649_(value);
    }

    @Override
    public int getMaxDomestication() {
        return this.getHandle().m_7555_();
    }

    @Override
    public void setMaxDomestication(int value) {
        Preconditions.checkArgument((value > 0 ? 1 : 0) != 0, (String)"Max domestication (%s) cannot be zero or less", (int)value);
        this.getHandle().maxDomestication = value;
    }

    @Override
    public double getJumpStrength() {
        return this.getHandle().m_30626_();
    }

    @Override
    public void setJumpStrength(double strength) {
        Preconditions.checkArgument((strength >= 0.0 ? 1 : 0) != 0, (String)"Jump strength (%s) cannot be less than zero", (Object)strength);
        this.getHandle().m_21051_(Attributes.f_22288_).m_22100_(strength);
    }

    @Override
    public boolean isTamed() {
        return this.getHandle().m_30614_();
    }

    @Override
    public void setTamed(boolean tamed) {
        this.getHandle().m_30651_(tamed);
    }

    @Override
    public AnimalTamer getOwner() {
        if (this.getOwnerUUID() == null) {
            return null;
        }
        return this.getServer().getOfflinePlayer(this.getOwnerUUID());
    }

    @Override
    public void setOwner(AnimalTamer owner) {
        if (owner != null) {
            this.setTamed(true);
            this.getHandle().setTarget(null, null, false);
            this.setOwnerUUID(owner.getUniqueId());
        } else {
            this.setTamed(false);
            this.setOwnerUUID(null);
        }
    }

    public UUID getOwnerUUID() {
        return this.getHandle().m_21805_();
    }

    public void setOwnerUUID(UUID uuid) {
        this.getHandle().m_30586_(uuid);
    }

    @Override
    public boolean isEatingHaystack() {
        return this.getHandle().m_30617_();
    }

    @Override
    public void setEatingHaystack(boolean eatingHaystack) {
        this.getHandle().m_30661_(eatingHaystack);
    }

    @Override
    public AbstractHorseInventory getInventory() {
        return new CraftInventoryAbstractHorse((Container)this.getHandle().f_30520_);
    }
}

