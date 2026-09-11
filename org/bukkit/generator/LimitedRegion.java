/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.generator;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.RegionAccessor;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public interface LimitedRegion
extends RegionAccessor {
    public int getBuffer();

    public boolean isInRegion(@NotNull Location var1);

    public boolean isInRegion(int var1, int var2, int var3);

    @NotNull
    public List<BlockState> getTileEntities();
}

