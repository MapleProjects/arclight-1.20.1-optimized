/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.EvokerFangs
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.entity.EvokerFangs;

public class CraftEvokerFangs
extends CraftEntity
implements EvokerFangs {
    public CraftEvokerFangs(CraftServer server, net.minecraft.world.entity.projectile.EvokerFangs entity) {
        super(server, (Entity)entity);
    }

    public net.minecraft.world.entity.projectile.EvokerFangs getHandle() {
        return (net.minecraft.world.entity.projectile.EvokerFangs)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftEvokerFangs";
    }

    @Override
    public org.bukkit.entity.LivingEntity getOwner() {
        LivingEntity owner = this.getHandle().m_19749_();
        return owner == null ? null : (org.bukkit.entity.LivingEntity)((Object)owner.getBukkitEntity());
    }

    @Override
    public void setOwner(org.bukkit.entity.LivingEntity owner) {
        this.getHandle().m_36938_(owner == null ? null : ((CraftLivingEntity)owner).getHandle());
    }
}

