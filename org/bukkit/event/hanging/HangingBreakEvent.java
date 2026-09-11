/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.hanging.HangingEvent;
import org.jetbrains.annotations.NotNull;

public class HangingBreakEvent
extends HangingEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final RemoveCause cause;

    public HangingBreakEvent(@NotNull Hanging hanging, @NotNull RemoveCause cause) {
        super(hanging);
        this.cause = cause;
    }

    @NotNull
    public RemoveCause getCause() {
        return this.cause;
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

    public static enum RemoveCause {
        ENTITY,
        EXPLOSION,
        OBSTRUCTION,
        PHYSICS,
        DEFAULT;

    }
}

