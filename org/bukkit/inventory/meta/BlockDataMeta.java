/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface BlockDataMeta
extends ItemMeta {
    public boolean hasBlockData();

    @NotNull
    public BlockData getBlockData(@NotNull Material var1);

    public void setBlockData(@NotNull BlockData var1);
}

