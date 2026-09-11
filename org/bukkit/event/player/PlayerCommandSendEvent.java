/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerCommandSendEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Collection<String> commands;

    public PlayerCommandSendEvent(@NotNull Player player, @NotNull Collection<String> commands) {
        super(player);
        this.commands = commands;
    }

    @NotNull
    public Collection<String> getCommands() {
        return this.commands;
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

