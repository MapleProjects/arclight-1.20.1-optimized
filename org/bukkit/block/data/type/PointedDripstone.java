/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

public interface PointedDripstone
extends Waterlogged {
    @NotNull
    public BlockFace getVerticalDirection();

    public void setVerticalDirection(@NotNull BlockFace var1);

    @NotNull
    public Set<BlockFace> getVerticalDirections();

    @NotNull
    public Thickness getThickness();

    public void setThickness(@NotNull Thickness var1);

    public static enum Thickness {
        TIP_MERGE,
        TIP,
        FRUSTUM,
        MIDDLE,
        BASE;

    }
}

