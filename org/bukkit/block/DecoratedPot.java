/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.block.TileState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DecoratedPot
extends TileState {
    public void setSherd(@NotNull Side var1, @Nullable Material var2);

    @NotNull
    public Material getSherd(@NotNull Side var1);

    @NotNull
    public Map<Side, Material> getSherds();

    @Deprecated
    @NotNull
    public List<Material> getShards();

    public static enum Side {
        BACK,
        LEFT,
        RIGHT,
        FRONT;

    }
}

