/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.MinecartTNT
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;

public final class CraftMinecartTNT
extends CraftMinecart
implements ExplosiveMinecart {
    CraftMinecartTNT(CraftServer server, MinecartTNT entity) {
        super(server, (AbstractMinecart)entity);
    }

    @Override
    public void setFuseTicks(int ticks) {
        this.getHandle().f_38647_ = ticks;
    }

    @Override
    public int getFuseTicks() {
        return this.getHandle().m_38694_();
    }

    @Override
    public void ignite() {
        this.getHandle().m_38693_();
    }

    @Override
    public boolean isIgnited() {
        return this.getHandle().m_38695_();
    }

    @Override
    public void explode() {
        this.getHandle().m_38688_(this.getHandle().m_20184_().m_165925_());
    }

    @Override
    public void explode(double power) {
        Preconditions.checkArgument((0.0 <= power && power <= 5.0 ? 1 : 0) != 0, (String)"Power must be in range [0, 5] (got %s)", (Object)power);
        this.getHandle().m_38688_(power);
    }

    public MinecartTNT getHandle() {
        return (MinecartTNT)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftMinecartTNT";
    }
}

