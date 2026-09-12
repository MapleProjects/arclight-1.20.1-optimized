/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.bukkit.block.Block
 *  org.bukkit.block.TileState
 *  org.bukkit.entity.Entity
 */
package io.izzel.arclight.api;

import javax.annotation.Nullable;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;

public interface TickingTracker {
    @Nullable
    public Object getTickingSource();

    @Nullable
    public Entity getTickingEntity();

    @Nullable
    public Block getTickingBlock();

    @Nullable
    public TileState getTickingBlockEntity();
}

