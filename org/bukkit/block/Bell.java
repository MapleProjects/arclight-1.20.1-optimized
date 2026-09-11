/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface Bell
extends TileState {
    public boolean ring(@Nullable Entity var1, @Nullable BlockFace var2);

    public boolean ring(@Nullable Entity var1);

    public boolean ring(@Nullable BlockFace var1);

    public boolean ring();

    public boolean isShaking();

    public int getShakingTicks();

    public boolean isResonating();

    public int getResonatingTicks();
}

