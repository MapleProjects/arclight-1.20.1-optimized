/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerRiptideEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack item;

    public PlayerRiptideEvent(@NotNull Player who, @NotNull ItemStack item) {
        super(who);
        this.item = item;
    }

    @NotNull
    public ItemStack getItem() {
        return this.item;
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

