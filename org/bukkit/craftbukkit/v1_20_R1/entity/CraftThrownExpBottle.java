/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftThrowableProjectile;
import org.bukkit.entity.ThrownExpBottle;

public class CraftThrownExpBottle
extends CraftThrowableProjectile
implements ThrownExpBottle {
    public CraftThrownExpBottle(CraftServer server, ThrownExperienceBottle entity) {
        super(server, (ThrowableItemProjectile)entity);
    }

    public ThrownExperienceBottle getHandle() {
        return (ThrownExperienceBottle)this.entity;
    }

    @Override
    public String toString() {
        return "EntityThrownExpBottle";
    }
}

