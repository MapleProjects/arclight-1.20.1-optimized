/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.jetbrains.annotations.NotNull;

public interface RedstoneWire
extends AnaloguePowerable {
    @NotNull
    public Connection getFace(@NotNull BlockFace var1);

    public void setFace(@NotNull BlockFace var1, @NotNull Connection var2);

    @NotNull
    public Set<BlockFace> getAllowedFaces();

    public static enum Connection {
        UP,
        SIDE,
        NONE;

    }
}

