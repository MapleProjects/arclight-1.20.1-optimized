/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.animal.camel.Camel
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractHorse;
import org.bukkit.entity.Horse;

public class CraftCamel
extends CraftAbstractHorse
implements org.bukkit.entity.Camel {
    public CraftCamel(CraftServer server, Camel entity) {
        super(server, (AbstractHorse)entity);
    }

    public Camel getHandle() {
        return (Camel)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftCamel";
    }

    @Override
    public Horse.Variant getVariant() {
        return Horse.Variant.CAMEL;
    }

    @Override
    public boolean isDashing() {
        return this.getHandle().m_245293_();
    }

    @Override
    public void setDashing(boolean dashing) {
        this.getHandle().m_246841_(dashing);
    }

    @Override
    public boolean isSitting() {
        return this.getHandle().m_20089_() == Pose.SITTING;
    }

    @Override
    public void setSitting(boolean sitting) {
        if (sitting) {
            this.getHandle().m_245138_();
        } else {
            this.getHandle().m_246761_();
        }
    }
}

