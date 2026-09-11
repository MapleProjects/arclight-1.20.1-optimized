/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;

public class CraftExperienceOrb
extends CraftEntity
implements org.bukkit.entity.ExperienceOrb {
    public CraftExperienceOrb(CraftServer server, ExperienceOrb entity) {
        super(server, (Entity)entity);
    }

    @Override
    public int getExperience() {
        return this.getHandle().f_20770_;
    }

    @Override
    public void setExperience(int value) {
        this.getHandle().f_20770_ = value;
    }

    public ExperienceOrb getHandle() {
        return (ExperienceOrb)this.entity;
    }

    @Override
    public String toString() {
        return "CraftExperienceOrb";
    }
}

