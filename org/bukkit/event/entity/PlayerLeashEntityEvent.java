/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class PlayerLeashEntityEvent
extends Event
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Entity leashHolder;
    private final Entity entity;
    private boolean cancelled = false;
    private final Player player;
    private final EquipmentSlot hand;

    public PlayerLeashEntityEvent(@NotNull Entity what, @NotNull Entity leashHolder, @NotNull Player leasher, @NotNull EquipmentSlot hand) {
        this.leashHolder = leashHolder;
        this.entity = what;
        this.player = leasher;
        this.hand = hand;
    }

    @Deprecated
    public PlayerLeashEntityEvent(@NotNull Entity what, @NotNull Entity leashHolder, @NotNull Player leasher) {
        this(what, leashHolder, leasher, EquipmentSlot.HAND);
    }

    @NotNull
    public Entity getLeashHolder() {
        return this.leashHolder;
    }

    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    @NotNull
    public final Player getPlayer() {
        return this.player;
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}

