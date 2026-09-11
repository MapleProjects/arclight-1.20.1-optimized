/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

public interface Rail
extends Waterlogged {
    @NotNull
    public Shape getShape();

    public void setShape(@NotNull Shape var1);

    @NotNull
    public Set<Shape> getShapes();

    public static enum Shape {
        NORTH_SOUTH,
        EAST_WEST,
        ASCENDING_EAST,
        ASCENDING_WEST,
        ASCENDING_NORTH,
        ASCENDING_SOUTH,
        SOUTH_EAST,
        SOUTH_WEST,
        NORTH_WEST,
        NORTH_EAST;

    }
}

