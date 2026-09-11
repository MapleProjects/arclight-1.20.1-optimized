/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.SpectralArrow
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.AbstractArrow;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArrow;
import org.bukkit.entity.SpectralArrow;

public class CraftSpectralArrow
extends CraftArrow
implements SpectralArrow {
    public CraftSpectralArrow(CraftServer server, net.minecraft.world.entity.projectile.SpectralArrow entity) {
        super(server, (AbstractArrow)entity);
    }

    public net.minecraft.world.entity.projectile.SpectralArrow getHandle() {
        return (net.minecraft.world.entity.projectile.SpectralArrow)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSpectralArrow";
    }

    @Override
    public int getGlowingTicks() {
        return this.getHandle().f_37409_;
    }

    @Override
    public void setGlowingTicks(int duration) {
        this.getHandle().f_37409_ = duration;
    }
}

