/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface BlockChangeDelegate {
    public boolean setBlockData(int var1, int var2, int var3, @NotNull BlockData var4);

    @NotNull
    public BlockData getBlockData(int var1, int var2, int var3);

    public int getHeight();

    public boolean isEmpty(int var1, int var2, int var3);
}

