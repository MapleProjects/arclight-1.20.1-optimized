/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.block.BlockState;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface BlockStateMeta
extends ItemMeta {
    public boolean hasBlockState();

    @NotNull
    public BlockState getBlockState();

    public void setBlockState(@NotNull BlockState var1);
}

