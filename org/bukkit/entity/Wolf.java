/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public interface Wolf
extends Tameable,
Sittable {
    public boolean isAngry();

    public void setAngry(boolean var1);

    @NotNull
    public DyeColor getCollarColor();

    public void setCollarColor(@NotNull DyeColor var1);

    public boolean isWet();

    public float getTailAngle();

    public boolean isInterested();

    public void setInterested(boolean var1);
}

