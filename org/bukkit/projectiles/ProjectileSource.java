/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.projectiles;

import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProjectileSource {
    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> var1);

    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> var1, @Nullable Vector var2);
}

