/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.server;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TabCompleteEvent
extends Event
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final CommandSender sender;
    private final String buffer;
    private List<String> completions;
    private boolean cancelled;

    public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"sender");
        Preconditions.checkArgument((buffer != null ? 1 : 0) != 0, (Object)"buffer");
        Preconditions.checkArgument((completions != null ? 1 : 0) != 0, (Object)"completions");
        this.sender = sender;
        this.buffer = buffer;
        this.completions = completions;
    }

    @NotNull
    public CommandSender getSender() {
        return this.sender;
    }

    @NotNull
    public String getBuffer() {
        return this.buffer;
    }

    @NotNull
    public List<String> getCompletions() {
        return this.completions;
    }

    public void setCompletions(@NotNull List<String> completions) {
        Preconditions.checkArgument((completions != null ? 1 : 0) != 0);
        this.completions = completions;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

