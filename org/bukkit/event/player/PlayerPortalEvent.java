/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPortalEvent
extends PlayerTeleportEvent {
    private static final HandlerList handlers = new HandlerList();
    private int getSearchRadius = 128;
    private boolean canCreatePortal = true;
    private int creationRadius = 16;

    public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to) {
        super(player, from, to);
    }

    public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to, @NotNull PlayerTeleportEvent.TeleportCause cause) {
        super(player, from, to, cause);
    }

    public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to, @NotNull PlayerTeleportEvent.TeleportCause cause, int getSearchRadius, boolean canCreatePortal, int creationRadius) {
        super(player, from, to, cause);
        this.getSearchRadius = getSearchRadius;
        this.canCreatePortal = canCreatePortal;
        this.creationRadius = creationRadius;
    }

    public void setSearchRadius(int searchRadius) {
        this.getSearchRadius = searchRadius;
    }

    public int getSearchRadius() {
        return this.getSearchRadius;
    }

    public boolean getCanCreatePortal() {
        return this.canCreatePortal;
    }

    public void setCanCreatePortal(boolean canCreatePortal) {
        this.canCreatePortal = canCreatePortal;
    }

    public void setCreationRadius(int creationRadius) {
        this.creationRadius = creationRadius;
    }

    public int getCreationRadius() {
        return this.creationRadius;
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

