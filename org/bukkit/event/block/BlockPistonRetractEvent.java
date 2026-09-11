/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPistonEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPistonRetractEvent
extends BlockPistonEvent {
    private static final HandlerList handlers = new HandlerList();
    private List<Block> blocks;

    public BlockPistonRetractEvent(@NotNull Block block, @NotNull List<Block> blocks, @NotNull BlockFace direction) {
        super(block, direction);
        this.blocks = blocks;
    }

    @Deprecated
    @NotNull
    public Location getRetractLocation() {
        return this.getBlock().getRelative(this.getDirection(), 2).getLocation();
    }

    @NotNull
    public List<Block> getBlocks() {
        return this.blocks;
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

