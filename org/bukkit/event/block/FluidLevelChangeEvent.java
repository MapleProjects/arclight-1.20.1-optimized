/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class FluidLevelChangeEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private BlockData newData;

    public FluidLevelChangeEvent(@NotNull Block theBlock, @NotNull BlockData newData) {
        super(theBlock);
        this.newData = newData;
    }

    @NotNull
    public BlockData getNewData() {
        return this.newData;
    }

    public void setNewData(@NotNull BlockData newData) {
        Preconditions.checkArgument((newData != null ? 1 : 0) != 0, (Object)"newData null");
        Preconditions.checkArgument((boolean)this.newData.getMaterial().equals(newData.getMaterial()), (Object)"Cannot change fluid type");
        this.newData = newData;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

