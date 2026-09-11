/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.Fireball
 *  net.minecraft.world.entity.projectile.SmallFireball
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.Fireball;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSizedFireball;
import org.bukkit.entity.SmallFireball;

public class CraftSmallFireball
extends CraftSizedFireball
implements SmallFireball {
    public CraftSmallFireball(CraftServer server, net.minecraft.world.entity.projectile.SmallFireball entity) {
        super(server, (Fireball)entity);
    }

    public net.minecraft.world.entity.projectile.SmallFireball getHandle() {
        return (net.minecraft.world.entity.projectile.SmallFireball)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSmallFireball";
    }
}

