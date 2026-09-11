/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ambient.AmbientCreature
 *  net.minecraft.world.entity.ambient.Bat
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.ambient.AmbientCreature;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAmbient;
import org.bukkit.entity.Bat;

public class CraftBat
extends CraftAmbient
implements Bat {
    public CraftBat(CraftServer server, net.minecraft.world.entity.ambient.Bat entity) {
        super(server, (AmbientCreature)entity);
    }

    public net.minecraft.world.entity.ambient.Bat getHandle() {
        return (net.minecraft.world.entity.ambient.Bat)this.entity;
    }

    @Override
    public String toString() {
        return "CraftBat";
    }

    @Override
    public boolean isAwake() {
        return !this.getHandle().m_27452_();
    }

    @Override
    public void setAwake(boolean state) {
        this.getHandle().m_27456_(!state);
    }
}

