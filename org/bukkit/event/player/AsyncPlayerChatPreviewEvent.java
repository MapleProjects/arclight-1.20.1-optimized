/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.util.Set;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(value=false)
public class AsyncPlayerChatPreviewEvent
extends AsyncPlayerChatEvent {
    private static final HandlerList handlers = new HandlerList();

    public AsyncPlayerChatPreviewEvent(boolean async, @NotNull Player who, @NotNull String message, @NotNull Set<Player> players) {
        super(async, who, message, players);
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

