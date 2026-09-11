/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.animal.horse.Horse
 *  net.minecraft.world.entity.animal.horse.Markings
 *  net.minecraft.world.entity.animal.horse.Variant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Markings;
import net.minecraft.world.entity.animal.horse.Variant;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryHorse;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.HorseInventory;

public class CraftHorse
extends CraftAbstractHorse
implements org.bukkit.entity.Horse {
    public CraftHorse(CraftServer server, Horse entity) {
        super(server, (AbstractHorse)entity);
    }

    public Horse getHandle() {
        return (Horse)super.getHandle();
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.HORSE;
    }

    @Override
    public Horse.Color getColor() {
        return Horse.Color.values()[this.getHandle().m_28554_().m_30985_()];
    }

    @Override
    public void setColor(Horse.Color color) {
        Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Color cannot be null");
        this.getHandle().m_30699_(Variant.m_30986_((int)color.ordinal()), this.getHandle().m_30724_());
    }

    @Override
    public Horse.Style getStyle() {
        return Horse.Style.values()[this.getHandle().m_30724_().m_30869_()];
    }

    @Override
    public void setStyle(Horse.Style style) {
        Preconditions.checkArgument((style != null ? 1 : 0) != 0, (Object)"Style cannot be null");
        this.getHandle().m_30699_(this.getHandle().m_28554_(), Markings.m_30870_((int)style.ordinal()));
    }

    @Override
    public boolean isCarryingChest() {
        return false;
    }

    @Override
    public void setCarryingChest(boolean chest) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public HorseInventory getInventory() {
        return new CraftInventoryHorse((Container)this.getHandle().f_30520_);
    }

    @Override
    public String toString() {
        return "CraftHorse{variant=" + (Object)((Object)this.getVariant()) + ", owner=" + this.getOwner() + '}';
    }
}

