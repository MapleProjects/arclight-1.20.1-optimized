/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.raid;

import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.event.raid.RaidEvent;
import org.jetbrains.annotations.NotNull;

public class RaidStopEvent
extends RaidEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Reason reason;

    public RaidStopEvent(@NotNull Raid raid, @NotNull World world, @NotNull Reason reason) {
        super(raid, world);
        this.reason = reason;
    }

    @NotNull
    public Reason getReason() {
        return this.reason;
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

    public static enum Reason {
        PEACE,
        TIMEOUT,
        FINISHED,
        UNSPAWNABLE,
        NOT_IN_VILLAGE;

    }
}

