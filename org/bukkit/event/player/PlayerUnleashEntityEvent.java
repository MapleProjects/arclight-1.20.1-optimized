/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class PlayerUnleashEntityEvent
extends EntityUnleashEvent
implements Cancellable {
    private boolean cancelled = false;
    private final Player player;
    private final EquipmentSlot hand;

    public PlayerUnleashEntityEvent(@NotNull Entity entity, @NotNull Player player, @NotNull EquipmentSlot hand) {
        super(entity, EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH);
        this.player = player;
        this.hand = hand;
    }

    @Deprecated
    public PlayerUnleashEntityEvent(@NotNull Entity entity, @NotNull Player player) {
        this(entity, player, EquipmentSlot.HAND);
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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

