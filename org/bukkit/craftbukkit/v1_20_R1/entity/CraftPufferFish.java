/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.Pufferfish
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Pufferfish;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFish;
import org.bukkit.entity.PufferFish;

public class CraftPufferFish
extends CraftFish
implements PufferFish {
    public CraftPufferFish(CraftServer server, Pufferfish entity) {
        super(server, (AbstractFish)entity);
    }

    public Pufferfish getHandle() {
        return (Pufferfish)super.getHandle();
    }

    @Override
    public int getPuffState() {
        return this.getHandle().m_29631_();
    }

    @Override
    public void setPuffState(int state) {
        this.getHandle().m_29618_(state);
    }

    @Override
    public String toString() {
        return "CraftPufferFish";
    }
}

