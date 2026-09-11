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
import org.bukkit.event.block.BlockGrowEvent;
import org.jetbrains.annotations.NotNull;

public class BlockFormEvent
extends BlockGrowEvent {
    private static final HandlerList handlers = new HandlerList();

    public BlockFormEvent(@NotNull Block block, @NotNull BlockState newState) {
        super(block, newState);
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

