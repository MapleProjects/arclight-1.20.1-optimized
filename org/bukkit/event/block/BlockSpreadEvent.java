/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockFormEvent;
import org.jetbrains.annotations.NotNull;

public class BlockSpreadEvent
extends BlockFormEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Block source;

    public BlockSpreadEvent(@NotNull Block block, @NotNull Block source, @NotNull BlockState newState) {
        super(block, newState);
        this.source = source;
    }

    @NotNull
    public Block getSource() {
        return this.source;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

