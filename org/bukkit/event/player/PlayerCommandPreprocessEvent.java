/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerCommandPreprocessEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;
    private final Set<Player> recipients;

    public PlayerCommandPreprocessEvent(@NotNull Player player, @NotNull String message) {
        super(player);
        this.recipients = new HashSet<Player>(player.getServer().getOnlinePlayers());
        this.message = message;
    }

    public PlayerCommandPreprocessEvent(@NotNull Player player, @NotNull String message, @NotNull Set<Player> recipients) {
        super(player);
        this.recipients = recipients;
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public String getMessage() {
        return this.message;
    }

    public void setMessage(@NotNull String command) throws IllegalArgumentException {
        Preconditions.checkArgument((command != null ? 1 : 0) != 0, (Object)"Command cannot be null");
        Preconditions.checkArgument((!command.isEmpty() ? 1 : 0) != 0, (Object)"Command cannot be empty");
        this.message = command;
    }

    public void setPlayer(@NotNull Player player) throws IllegalArgumentException {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"Player cannot be null");
        this.player = player;
    }

    @Deprecated
    @NotNull
    public Set<Player> getRecipients() {
        return this.recipients;
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

