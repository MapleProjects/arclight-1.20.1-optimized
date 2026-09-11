/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.Projectile
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.projectiles.ProjectileSource;

public abstract class CraftProjectile
extends AbstractProjectile
implements org.bukkit.entity.Projectile {
    public CraftProjectile(CraftServer server, Projectile entity) {
        super(server, (Entity)entity);
    }

    @Override
    public ProjectileSource getShooter() {
        return this.getHandle().projectileSource;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof CraftLivingEntity) {
            this.getHandle().m_5602_((Entity)((LivingEntity)((CraftLivingEntity)shooter).entity));
        } else {
            this.getHandle().m_5602_(null);
        }
        this.getHandle().projectileSource = shooter;
    }

    public Projectile getHandle() {
        return (Projectile)this.entity;
    }

    @Override
    public String toString() {
        return "CraftProjectile";
    }
}

