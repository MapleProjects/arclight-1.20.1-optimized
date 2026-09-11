/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPhysicsEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final BlockData changed;
    private final Block sourceBlock;
    private boolean cancel = false;

    public BlockPhysicsEvent(@NotNull Block block, @NotNull BlockData changed) {
        this(block, changed, block);
    }

    public BlockPhysicsEvent(@NotNull Block block, @NotNull BlockData changed, @NotNull Block sourceBlock) {
        super(block);
        this.changed = changed;
        this.sourceBlock = sourceBlock;
    }

    @NotNull
    public Block getSourceBlock() {
        return this.sourceBlock;
    }

    @NotNull
    public Material getChangedType() {
        return this.changed.getMaterial();
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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

