/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.Nullable;

public interface ShulkerBullet
extends Projectile {
    @Nullable
    public Entity getTarget();

    public void setTarget(@Nullable Entity var1);
}

