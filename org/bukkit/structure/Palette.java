/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.structure;

import java.util.List;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public interface Palette {
    @NotNull
    public List<BlockState> getBlocks();

    public int getBlockCount();
}

