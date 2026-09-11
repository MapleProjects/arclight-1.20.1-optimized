/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;

public interface BlockDisplay
extends Display {
    @NotNull
    public BlockData getBlock();

    public void setBlock(@NotNull BlockData var1);
}

