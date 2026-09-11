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
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerBedLeaveEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block bed;
    private boolean setBedSpawn;
    private boolean cancelled;

    public PlayerBedLeaveEvent(@NotNull Player who, @NotNull Block bed, boolean setBedSpawn) {
        super(who);
        this.bed = bed;
        this.setBedSpawn = setBedSpawn;
    }

    @NotNull
    public Block getBed() {
        return this.bed;
    }

    public boolean shouldSetSpawnLocation() {
        return this.setBedSpawn;
    }

    public void setSpawnLocation(boolean setBedSpawn) {
        this.setBedSpawn = setBedSpawn;
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

