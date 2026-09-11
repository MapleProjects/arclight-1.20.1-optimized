/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class PlayerEditBookEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final BookMeta previousBookMeta;
    private final int slot;
    private BookMeta newBookMeta;
    private boolean isSigning;
    private boolean cancel;

    public PlayerEditBookEvent(@NotNull Player who, int slot, @NotNull BookMeta previousBookMeta, @NotNull BookMeta newBookMeta, boolean isSigning) {
        super(who);
        Preconditions.checkArgument((slot >= -1 && slot <= 8 ? 1 : 0) != 0, (Object)"Slot must be in range (-1)-8 inclusive");
        Preconditions.checkArgument((previousBookMeta != null ? 1 : 0) != 0, (Object)"Previous book meta must not be null");
        Preconditions.checkArgument((newBookMeta != null ? 1 : 0) != 0, (Object)"New book meta must not be null");
        Bukkit.getItemFactory().equals(previousBookMeta, newBookMeta);
        this.previousBookMeta = previousBookMeta;
        this.newBookMeta = newBookMeta;
        this.slot = slot;
        this.isSigning = isSigning;
        this.cancel = false;
    }

    @NotNull
    public BookMeta getPreviousBookMeta() {
        return this.previousBookMeta.clone();
    }

    @NotNull
    public BookMeta getNewBookMeta() {
        return this.newBookMeta.clone();
    }

    @Deprecated
    public int getSlot() {
        return this.slot;
    }

    public void setNewBookMeta(@NotNull BookMeta newBookMeta) throws IllegalArgumentException {
        Preconditions.checkArgument((newBookMeta != null ? 1 : 0) != 0, (Object)"New book meta must not be null");
        Bukkit.getItemFactory().equals(newBookMeta, null);
        this.newBookMeta = newBookMeta.clone();
    }

    public boolean isSigning() {
        return this.isSigning;
    }

    public void setSigning(boolean signing) {
        this.isSigning = signing;
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

