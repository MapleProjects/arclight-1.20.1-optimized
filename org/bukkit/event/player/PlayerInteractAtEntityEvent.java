/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractAtEntityEvent
extends PlayerInteractEntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Vector position;

    public PlayerInteractAtEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity, @NotNull Vector position) {
        this(who, clickedEntity, position, EquipmentSlot.HAND);
    }

    public PlayerInteractAtEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity, @NotNull Vector position, @NotNull EquipmentSlot hand) {
        super(who, clickedEntity, hand);
        this.position = position;
    }

    @NotNull
    public Vector getClickedPosition() {
        return this.position.clone();
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

