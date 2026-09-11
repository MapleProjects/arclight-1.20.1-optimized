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

public interface Directional
extends BlockData {
    @NotNull
    public BlockFace getFacing();

    public void setFacing(@NotNull BlockFace var1);

    @NotNull
    public Set<BlockFace> getFaces();
}

