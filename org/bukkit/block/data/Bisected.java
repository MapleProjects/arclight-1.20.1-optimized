/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface Bisected
extends BlockData {
    @NotNull
    public Half getHalf();

    public void setHalf(@NotNull Half var1);

    public static enum Half {
        TOP,
        BOTTOM;

    }
}

