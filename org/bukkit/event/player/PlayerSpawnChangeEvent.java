/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.ApiStatus$Experimental
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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Experimental
public class PlayerSpawnChangeEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Cause cause;
    private Location newSpawn;
    private boolean forced;
    private boolean cancelled;

    public PlayerSpawnChangeEvent(@NotNull Player player, @Nullable Location newSpawn, boolean forced, @NotNull Cause cause) {
        super(player);
        this.newSpawn = newSpawn;
        this.cause = cause;
        this.forced = forced;
    }

    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    public boolean isForced() {
        return this.forced;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    @Nullable
    public Location getNewSpawn() {
        return this.newSpawn;
    }

    public void setNewSpawn(@Nullable Location newSpawn) {
        if (newSpawn != null) {
            Preconditions.checkArgument((newSpawn.getWorld() != null ? 1 : 0) != 0, (Object)"Spawn location must have a world set");
            this.newSpawn = newSpawn.clone();
        } else {
            this.newSpawn = null;
        }
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

    public static enum Cause {
        COMMAND,
        BED,
        RESPAWN_ANCHOR,
        PLUGIN,
        RESET,
        UNKNOWN;

    }
}

