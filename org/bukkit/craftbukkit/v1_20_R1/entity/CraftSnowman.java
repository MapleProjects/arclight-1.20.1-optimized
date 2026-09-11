/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.animal.SnowGolem
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGolem;
import org.bukkit.entity.Snowman;

public class CraftSnowman
extends CraftGolem
implements Snowman {
    public CraftSnowman(CraftServer server, SnowGolem entity) {
        super(server, (AbstractGolem)entity);
    }

    @Override
    public boolean isDerp() {
        return !this.getHandle().m_29930_();
    }

    @Override
    public void setDerp(boolean derpMode) {
        this.getHandle().m_29936_(!derpMode);
    }

    public SnowGolem getHandle() {
        return (SnowGolem)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSnowman";
    }
}

