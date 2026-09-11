/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerBedEnterEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block bed;
    private final BedEnterResult bedEnterResult;
    private Event.Result useBed = Event.Result.DEFAULT;

    public PlayerBedEnterEvent(@NotNull Player who, @NotNull Block bed, @NotNull BedEnterResult bedEnterResult) {
        super(who);
        this.bed = bed;
        this.bedEnterResult = bedEnterResult;
    }

    @Deprecated
    public PlayerBedEnterEvent(@NotNull Player who, @NotNull Block bed) {
        this(who, bed, BedEnterResult.OK);
    }

    @NotNull
    public BedEnterResult getBedEnterResult() {
        return this.bedEnterResult;
    }

    @NotNull
    public Event.Result useBed() {
        return this.useBed;
    }

    public void setUseBed(@NotNull Event.Result useBed) {
        this.useBed = useBed;
    }

    @Override
    public boolean isCancelled() {
        return this.useBed == Event.Result.DENY || this.useBed == Event.Result.DEFAULT && this.bedEnterResult != BedEnterResult.OK;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.setUseBed(cancel ? Event.Result.DENY : (this.useBed() == Event.Result.DENY ? Event.Result.DEFAULT : this.useBed()));
    }

    @NotNull
    public Block getBed() {
        return this.bed;
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

    public static enum BedEnterResult {
        OK,
        NOT_POSSIBLE_HERE,
        NOT_POSSIBLE_NOW,
        TOO_FAR_AWAY,
        NOT_SAFE,
        OTHER_PROBLEM;

    }
}

