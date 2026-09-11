/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface SculkShrieker
extends TileState {
    public int getWarningLevel();

    public void setWarningLevel(int var1);

    public void tryShriek(@Nullable Player var1);
}

