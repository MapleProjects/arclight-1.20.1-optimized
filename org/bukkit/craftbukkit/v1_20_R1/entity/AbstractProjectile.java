/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Projectile;

public abstract class AbstractProjectile
extends CraftEntity
implements Projectile {
    private boolean doesBounce = false;

    public AbstractProjectile(CraftServer server, Entity entity) {
        super(server, entity);
    }

    @Override
    public boolean doesBounce() {
        return this.doesBounce;
    }

    @Override
    public void setBounce(boolean doesBounce) {
        this.doesBounce = doesBounce;
    }
}

