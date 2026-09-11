/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.Nullable;

public interface Vex
extends Monster {
    public boolean isCharging();

    public void setCharging(boolean var1);

    @Nullable
    public Location getBound();

    public void setBound(@Nullable Location var1);

    public int getLifeTicks();

    public void setLifeTicks(int var1);

    public boolean hasLimitedLife();
}

