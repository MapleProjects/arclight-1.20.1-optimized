/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityPortalEvent
extends EntityTeleportEvent {
    private static final HandlerList handlers = new HandlerList();
    private int searchRadius = 128;

    public EntityPortalEvent(@NotNull Entity entity, @NotNull Location from, @Nullable Location to) {
        super(entity, from, to);
    }

    public EntityPortalEvent(@NotNull Entity entity, @NotNull Location from, @Nullable Location to, int searchRadius) {
        super(entity, from, to);
        this.searchRadius = searchRadius;
    }

    public void setSearchRadius(int searchRadius) {
        this.searchRadius = searchRadius;
    }

    public int getSearchRadius() {
        return this.searchRadius;
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

