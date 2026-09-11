/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.ShulkerBullet
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.projectiles.ProjectileSource;

public class CraftShulkerBullet
extends AbstractProjectile
implements ShulkerBullet {
    public CraftShulkerBullet(CraftServer server, net.minecraft.world.entity.projectile.ShulkerBullet entity) {
        super(server, (net.minecraft.world.entity.Entity)entity);
    }

    @Override
    public ProjectileSource getShooter() {
        return this.getHandle().projectileSource;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof Entity) {
            this.getHandle().m_5602_(((CraftEntity)((Object)shooter)).getHandle());
        } else {
            this.getHandle().m_5602_(null);
        }
        this.getHandle().projectileSource = shooter;
    }

    @Override
    public Entity getTarget() {
        return this.getHandle().getTarget() != null ? this.getHandle().getTarget().getBukkitEntity() : null;
    }

    @Override
    public void setTarget(Entity target) {
        Preconditions.checkState((!this.getHandle().generation ? 1 : 0) != 0, (Object)"Cannot set target during world generation");
        this.getHandle().setTarget(target == null ? null : ((CraftEntity)target).getHandle());
    }

    @Override
    public String toString() {
        return "CraftShulkerBullet";
    }

    public net.minecraft.world.entity.projectile.ShulkerBullet getHandle() {
        return (net.minecraft.world.entity.projectile.ShulkerBullet)this.entity;
    }
}

