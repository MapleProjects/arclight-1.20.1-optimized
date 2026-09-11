/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

public interface SculkSensor
extends AnaloguePowerable,
Waterlogged {
    @NotNull
    public Phase getPhase();

    public void setPhase(@NotNull Phase var1);

    public static enum Phase {
        INACTIVE,
        ACTIVE,
        COOLDOWN;

    }
}

