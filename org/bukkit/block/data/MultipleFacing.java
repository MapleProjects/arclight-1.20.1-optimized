/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface MultipleFacing
extends BlockData {
    public boolean hasFace(@NotNull BlockFace var1);

    public void setFace(@NotNull BlockFace var1, boolean var2);

    @NotNull
    public Set<BlockFace> getFaces();

    @NotNull
    public Set<BlockFace> getAllowedFaces();
}

