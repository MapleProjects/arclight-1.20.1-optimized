/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util;

import java.util.Collection;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

public interface VoxelShape {
    @NotNull
    public Collection<BoundingBox> getBoundingBoxes();

    public boolean overlaps(@NotNull BoundingBox var1);
}

