/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.entity.projectile.ThrownEgg
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;
import org.bukkit.entity.Egg;

public class CraftEgg
extends CraftThrowableProjectile
implements Egg {
    public CraftEgg(CraftServer server, ThrownEgg entity) {
        super(server, (ThrowableItemProjectile)entity);
    }

    public ThrownEgg getHandle() {
        return (ThrownEgg)this.entity;
    }

    @Override
    public String toString() {
        return "CraftEgg";
    }
}

