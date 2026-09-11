/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.horse.AbstractChestedHorse
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.entity.ChestedHorse;

public abstract class CraftChestedHorse
extends CraftAbstractHorse
implements ChestedHorse {
    public CraftChestedHorse(CraftServer server, AbstractChestedHorse entity) {
        super(server, (AbstractHorse)entity);
    }

    public AbstractChestedHorse getHandle() {
        return (AbstractChestedHorse)super.getHandle();
    }

    @Override
    public boolean isCarryingChest() {
        return this.getHandle().m_30502_();
    }

    @Override
    public void setCarryingChest(boolean chest) {
        if (chest == this.isCarryingChest()) {
            return;
        }
        this.getHandle().m_30504_(chest);
        this.getHandle().m_30625_();
    }
}

