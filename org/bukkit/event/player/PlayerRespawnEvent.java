/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerRespawnEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Location respawnLocation;
    private final boolean isBedSpawn;
    private final boolean isAnchorSpawn;
    private final RespawnReason respawnReason;

    @Deprecated
    public PlayerRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnLocation, boolean isBedSpawn) {
        this(respawnPlayer, respawnLocation, isBedSpawn, false);
    }

    @Deprecated
    public PlayerRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnLocation, boolean isBedSpawn, boolean isAnchorSpawn) {
        this(respawnPlayer, respawnLocation, isBedSpawn, false, RespawnReason.PLUGIN);
    }

    public PlayerRespawnEvent(@NotNull Player respawnPlayer, @NotNull Location respawnLocation, boolean isBedSpawn, boolean isAnchorSpawn, @NotNull RespawnReason respawnReason) {
        super(respawnPlayer);
        this.respawnLocation = respawnLocation;
        this.isBedSpawn = isBedSpawn;
        this.isAnchorSpawn = isAnchorSpawn;
        this.respawnReason = respawnReason;
    }

    @NotNull
    public Location getRespawnLocation() {
        return this.respawnLocation;
    }

    public void setRespawnLocation(@NotNull Location respawnLocation) {
        Preconditions.checkArgument((respawnLocation != null ? 1 : 0) != 0, (Object)"Respawn location can not be null");
        Preconditions.checkArgument((respawnLocation.getWorld() != null ? 1 : 0) != 0, (Object)"Respawn world can not be null");
        this.respawnLocation = respawnLocation;
    }

    public boolean isBedSpawn() {
        return this.isBedSpawn;
    }

    public boolean isAnchorSpawn() {
        return this.isAnchorSpawn;
    }

    @NotNull
    public RespawnReason getRespawnReason() {
        return this.respawnReason;
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

    public static enum RespawnReason {
        DEATH,
        END_PORTAL,
        PLUGIN;

    }
}

