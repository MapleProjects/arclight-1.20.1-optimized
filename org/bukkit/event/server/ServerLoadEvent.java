/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.NotNull;

public class ServerLoadEvent
extends ServerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final LoadType type;

    public ServerLoadEvent(@NotNull LoadType type) {
        this.type = type;
    }

    @NotNull
    public LoadType getType() {
        return this.type;
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

    public static enum LoadType {
        STARTUP,
        RELOAD;

    }
}

