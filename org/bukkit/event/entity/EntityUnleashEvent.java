/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityUnleashEvent
extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final UnleashReason reason;

    public EntityUnleashEvent(@NotNull Entity entity, @NotNull UnleashReason reason) {
        super(entity);
        this.reason = reason;
    }

    @NotNull
    public UnleashReason getReason() {
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

    public static enum UnleashReason {
        HOLDER_GONE,
        PLAYER_UNLEASH,
        DISTANCE,
        UNKNOWN;

    }
}

