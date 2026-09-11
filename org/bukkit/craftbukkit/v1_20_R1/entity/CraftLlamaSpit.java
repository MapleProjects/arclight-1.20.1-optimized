/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.LlamaSpit
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.LlamaSpit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.projectiles.ProjectileSource;

public class CraftLlamaSpit
extends AbstractProjectile
implements org.bukkit.entity.LlamaSpit {
    public CraftLlamaSpit(CraftServer server, LlamaSpit entity) {
        super(server, (Entity)entity);
    }

    public LlamaSpit getHandle() {
        return (LlamaSpit)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftLlamaSpit";
    }

    @Override
    public ProjectileSource getShooter() {
        return this.getHandle().m_19749_() != null ? (ProjectileSource)((Object)this.getHandle().m_19749_().getBukkitEntity()) : null;
    }

    @Override
    public void setShooter(ProjectileSource source) {
        this.getHandle().m_5602_(source != null ? ((CraftLivingEntity)source).getHandle() : null);
    }
}

