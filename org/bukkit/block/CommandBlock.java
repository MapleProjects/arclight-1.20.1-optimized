/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandBlock
extends TileState {
    @NotNull
    public String getCommand();

    public void setCommand(@Nullable String var1);

    @NotNull
    public String getName();

    public void setName(@Nullable String var1);
}

