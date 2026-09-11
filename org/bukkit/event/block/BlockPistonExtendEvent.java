/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPistonEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPistonExtendEvent
extends BlockPistonEvent {
    private static final HandlerList handlers = new HandlerList();
    private final int length;
    private List<Block> blocks;

    @Deprecated
    public BlockPistonExtendEvent(@NotNull Block block, int length, @NotNull BlockFace direction) {
        super(block, direction);
        this.length = length;
    }

    public BlockPistonExtendEvent(@NotNull Block block, @NotNull List<Block> blocks, @NotNull BlockFace direction) {
        super(block, direction);
        this.length = blocks.size();
        this.blocks = blocks;
    }

    @Deprecated
    public int getLength() {
        return this.length;
    }

    @NotNull
    public List<Block> getBlocks() {
        if (this.blocks == null) {
            ArrayList<Block> tmp = new ArrayList<Block>();
            int i = 0;
            while (i < this.getLength()) {
                tmp.add(this.block.getRelative(this.getDirection(), i + 1));
                ++i;
            }
            this.blocks = Collections.unmodifiableList(tmp);
        }
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

