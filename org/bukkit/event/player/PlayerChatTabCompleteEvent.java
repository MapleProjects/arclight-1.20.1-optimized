/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Warning(reason="This event is no longer fired due to client changes")
public class PlayerChatTabCompleteEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final String message;
    private final String lastToken;
    private final Collection<String> completions;

    public PlayerChatTabCompleteEvent(@NotNull Player who, @NotNull String message, @NotNull Collection<String> completions) {
        super(who);
        Preconditions.checkArgument((message != null ? 1 : 0) != 0, (Object)"Message cannot be null");
        Preconditions.checkArgument((completions != null ? 1 : 0) != 0, (Object)"Completions cannot be null");
        this.message = message;
        int i = message.lastIndexOf(32);
        this.lastToken = i < 0 ? message : message.substring(i + 1);
        this.completions = completions;
    }

    @NotNull
    public String getChatMessage() {
        return this.message;
    }

    @NotNull
    public String getLastToken() {
        return this.lastToken;
    }

    @NotNull
    public Collection<String> getTabCompletions() {
        return this.completions;
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

