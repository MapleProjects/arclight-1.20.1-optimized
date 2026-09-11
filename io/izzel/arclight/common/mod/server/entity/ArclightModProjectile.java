/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.Projectile
 */
package io.izzel.arclight.common.mod.server.entity;

import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftProjectile;

public class ArclightModProjectile
extends CraftProjectile {
    public ArclightModProjectile(CraftServer server, Projectile entity) {
        super(server, entity);
    }
}

