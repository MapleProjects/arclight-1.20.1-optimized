/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 */
package io.izzel.arclight.common.mod.server.entity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;

public class ArclightModThrowableProjectile
extends CraftThrowableProjectile {
    public ArclightModThrowableProjectile(CraftServer server, ThrowableItemProjectile entity) {
        super(server, entity);
    }
}

