/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface Rotatable
extends BlockData {
    @NotNull
    public BlockFace getRotation();

    public void setRotation(@NotNull BlockFace var1);
}

