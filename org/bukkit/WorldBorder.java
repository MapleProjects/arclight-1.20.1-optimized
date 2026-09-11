/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.util.concurrent.TimeUnit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WorldBorder {
    @Nullable
    public World getWorld();

    public void reset();

    public double getSize();

    public void setSize(double var1);

    public void setSize(double var1, long var3);

    public void setSize(double var1, @NotNull TimeUnit var3, long var4);

    @NotNull
    public Location getCenter();

    public void setCenter(double var1, double var3);

    public void setCenter(@NotNull Location var1);

    public double getDamageBuffer();

    public void setDamageBuffer(double var1);

    public double getDamageAmount();

    public void setDamageAmount(double var1);

    public int getWarningTime();

    public void setWarningTime(int var1);

    public int getWarningDistance();

    public void setWarningDistance(int var1);

    public boolean isInside(@NotNull Location var1);

    public double getMaxSize();

    public double getMaxCenterCoordinate();
}

