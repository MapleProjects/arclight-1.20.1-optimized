/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Vehicle;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Minecart
extends Vehicle {
    public void setDamage(double var1);

    public double getDamage();

    public double getMaxSpeed();

    public void setMaxSpeed(double var1);

    public boolean isSlowWhenEmpty();

    public void setSlowWhenEmpty(boolean var1);

    @NotNull
    public Vector getFlyingVelocityMod();

    public void setFlyingVelocityMod(@NotNull Vector var1);

    @NotNull
    public Vector getDerailedVelocityMod();

    public void setDerailedVelocityMod(@NotNull Vector var1);

    public void setDisplayBlock(@Nullable MaterialData var1);

    @NotNull
    public MaterialData getDisplayBlock();

    public void setDisplayBlockData(@Nullable BlockData var1);

    @NotNull
    public BlockData getDisplayBlockData();

    public void setDisplayBlockOffset(int var1);

    public int getDisplayBlockOffset();
}

