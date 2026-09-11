/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

public class TimeSkipEvent
extends WorldEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final SkipReason skipReason;
    private long skipAmount;

    public TimeSkipEvent(@NotNull World world, @NotNull SkipReason skipReason, long skipAmount) {
        super(world);
        this.skipReason = skipReason;
        this.skipAmount = skipAmount;
    }

    @NotNull
    public SkipReason getSkipReason() {
        return this.skipReason;
    }

    public long getSkipAmount() {
        return this.skipAmount;
    }

    public void setSkipAmount(long skipAmount) {
        this.skipAmount = skipAmount;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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

    public static enum SkipReason {
        COMMAND,
        CUSTOM,
        NIGHT_SKIP;

    }
}

