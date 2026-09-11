/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.entity.projectile.ThrownEnderpearl
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;
import org.bukkit.entity.EnderPearl;

public class CraftEnderPearl
extends CraftThrowableProjectile
implements EnderPearl {
    public CraftEnderPearl(CraftServer server, ThrownEnderpearl entity) {
        super(server, (ThrowableItemProjectile)entity);
    }

    public ThrownEnderpearl getHandle() {
        return (ThrownEnderpearl)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEnderPearl";
    }
}

