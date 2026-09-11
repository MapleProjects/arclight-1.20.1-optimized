/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.Nullable;

public interface Projectile
extends Entity {
    @Nullable
    public ProjectileSource getShooter();

    public void setShooter(@Nullable ProjectileSource var1);

    public boolean doesBounce();

    public void setBounce(boolean var1);
}

