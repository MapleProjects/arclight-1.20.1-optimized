/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.Fireball
 *  net.minecraft.world.entity.projectile.LargeFireball
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftSizedFireball;

public class CraftLargeFireball
extends CraftSizedFireball
implements org.bukkit.entity.LargeFireball {
    public CraftLargeFireball(CraftServer server, LargeFireball entity) {
        super(server, (Fireball)entity);
    }

    @Override
    public void setYield(float yield) {
        super.setYield(yield);
        this.getHandle().f_37197_ = (int)yield;
    }

    public LargeFireball getHandle() {
        return (LargeFireball)this.entity;
    }

    @Override
    public String toString() {
        return "CraftLargeFireball";
    }
}

