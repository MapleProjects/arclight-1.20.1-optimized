/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.util.IllegalFormatException;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class AsyncPlayerChatEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    public AsyncPlayerChatEvent(boolean async, @NotNull Player who, @NotNull String message, @NotNull Set<Player> players) {
        super(who, async);
        this.message = message;
        this.recipients = players;
    }

    @NotNull
    public String getMessage() {
        return this.message;
    }

    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    @NotNull
    public String getFormat() {
        return this.format;
    }

    public void setFormat(@NotNull String format) throws IllegalFormatException, NullPointerException {
        try {
            String.format(format, this.player, this.message);
        }
        catch (RuntimeException ex) {
            ex.fillInStackTrace();
            throw ex;
        }
        this.format = format;
    }

    @NotNull
    public Set<Player> getRecipients() {
        return this.recipients;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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

