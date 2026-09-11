/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EntityPortalExitEvent
extends EntityTeleportEvent {
    private static final HandlerList handlers = new HandlerList();
    private Vector before;
    private Vector after;

    public EntityPortalExitEvent(@NotNull Entity entity, @NotNull Location from, @NotNull Location to, @NotNull Vector before, @NotNull Vector after) {
        super(entity, from, to);
        this.before = before;
        this.after = after;
    }

    @NotNull
    public Vector getBefore() {
        return this.before.clone();
    }

    @NotNull
    public Vector getAfter() {
        return this.after.clone();
    }

    public void setAfter(@NotNull Vector after) {
        this.after = after.clone();
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

