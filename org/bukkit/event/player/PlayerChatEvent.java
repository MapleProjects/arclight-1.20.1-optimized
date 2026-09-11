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
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(reason="Listening to this event forces chat to wait for the main thread, delaying chat messages.")
public class PlayerChatEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private String message;
    private String format;
    private final Set<Player> recipients;

    public PlayerChatEvent(@NotNull Player player, @NotNull String message) {
        super(player);
        this.message = message;
        this.format = "<%1$s> %2$s";
        this.recipients = new HashSet<Player>(player.getServer().getOnlinePlayers());
    }

    public PlayerChatEvent(@NotNull Player player, @NotNull String message, @NotNull String format, @NotNull Set<Player> recipients) {
        super(player);
        this.message = message;
        this.format = format;
        this.recipients = recipients;
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

    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    public void setPlayer(@NotNull Player player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"Player cannot be null");
        this.player = player;
    }

    @NotNull
    public String getFormat() {
        return this.format;
    }

    public void setFormat(@NotNull String format) {
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
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

