/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

public interface Wall
extends Waterlogged {
    public boolean isUp();

    public void setUp(boolean var1);

    @NotNull
    public Height getHeight(@NotNull BlockFace var1);

    public void setHeight(@NotNull BlockFace var1, @NotNull Height var2);

    public static enum Height {
        NONE,
        LOW,
        TALL;

    }
}

