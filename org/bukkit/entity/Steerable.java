/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;

public interface Steerable
extends Animals {
    public boolean hasSaddle();

    public void setSaddle(boolean var1);

    public int getBoostTicks();

    public void setBoostTicks(int var1);

    public int getCurrentBoostTicks();

    public void setCurrentBoostTicks(int var1);

    @NotNull
    public Material getSteerMaterial();
}

