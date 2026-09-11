/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Directional {
    public void setFacingDirection(@NotNull BlockFace var1);

    @NotNull
    public BlockFace getFacing();
}

