/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerMoveEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Location from;
    private Location to;

    public PlayerMoveEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to) {
        super(player);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public Location getFrom() {
        return this.from;
    }

    public void setFrom(@NotNull Location from) {
        this.validateLocation(from);
        this.from = from;
    }

    @Nullable
    public Location getTo() {
        return this.to;
    }

    public void setTo(@NotNull Location to) {
        this.validateLocation(to);
        this.to = to;
    }

    private void validateLocation(@NotNull Location loc) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Cannot use null location!");
        Preconditions.checkArgument((loc.getWorld() != null ? 1 : 0) != 0, (Object)"Cannot use null location with null world!");
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

