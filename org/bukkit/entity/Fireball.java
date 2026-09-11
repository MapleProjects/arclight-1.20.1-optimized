/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Explosive;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Fireball
extends Projectile,
Explosive {
    public void setDirection(@NotNull Vector var1);

    @NotNull
    public Vector getDirection();
}

