/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.entity.Fireball;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class CraftFireball
extends AbstractProjectile
implements Fireball {
    public CraftFireball(CraftServer server, AbstractHurtingProjectile entity) {
        super(server, (Entity)entity);
    }

    @Override
    public float getYield() {
        return this.getHandle().bukkitYield;
    }

    @Override
    public boolean isIncendiary() {
        return this.getHandle().isIncendiary;
    }

    @Override
    public void setIsIncendiary(boolean isIncendiary) {
        this.getHandle().isIncendiary = isIncendiary;
    }

    @Override
    public void setYield(float yield) {
        this.getHandle().bukkitYield = yield;
    }

    @Override
    public ProjectileSource getShooter() {
        return this.getHandle().projectileSource;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof CraftLivingEntity) {
            this.getHandle().m_5602_((Entity)((CraftLivingEntity)shooter).getHandle());
        } else {
            this.getHandle().m_5602_(null);
        }
        this.getHandle().projectileSource = shooter;
    }

    @Override
    public Vector getDirection() {
        return new Vector(this.getHandle().f_36813_, this.getHandle().f_36814_, this.getHandle().f_36815_);
    }

    @Override
    public void setDirection(Vector direction) {
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Vector direction cannot be null");
        this.getHandle().setDirection(direction.getX(), direction.getY(), direction.getZ());
        this.update();
    }

    public AbstractHurtingProjectile getHandle() {
        return (AbstractHurtingProjectile)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFireball";
    }
}

