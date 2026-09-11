/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.server;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.NotNull;

public class ServerCommandEvent
extends ServerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private String command;
    private final CommandSender sender;
    private boolean cancel = false;

    public ServerCommandEvent(@NotNull CommandSender sender, @NotNull String command) {
        this.command = command;
        this.sender = sender;
    }

    @NotNull
    public String getCommand() {
        return this.command;
    }

    public void setCommand(@NotNull String message) {
        this.command = message;
    }

    @NotNull
    public CommandSender getSender() {
        return this.sender;
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

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}

