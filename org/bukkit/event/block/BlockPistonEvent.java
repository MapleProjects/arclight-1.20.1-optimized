/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public abstract class BlockPistonEvent
extends BlockEvent
implements Cancellable {
    private boolean cancelled;
    private final BlockFace direction;

    public BlockPistonEvent(@NotNull Block block, @NotNull BlockFace direction) {
        super(block);
        this.direction = direction;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isSticky() {
        return this.block.getType() == Material.STICKY_PISTON || this.block.getType() == Material.MOVING_PISTON;
    }

    @NotNull
    public BlockFace getDirection() {
        return this.direction;
    }
}

