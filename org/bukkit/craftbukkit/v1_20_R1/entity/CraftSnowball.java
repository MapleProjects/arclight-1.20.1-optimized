/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;
import org.bukkit.entity.Snowball;

public class CraftSnowball
extends CraftThrowableProjectile
implements Snowball {
    public CraftSnowball(CraftServer server, net.minecraft.world.entity.projectile.Snowball entity) {
        super(server, (ThrowableItemProjectile)entity);
    }

    public net.minecraft.world.entity.projectile.Snowball getHandle() {
        return (net.minecraft.world.entity.projectile.Snowball)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSnowball";
    }
}

