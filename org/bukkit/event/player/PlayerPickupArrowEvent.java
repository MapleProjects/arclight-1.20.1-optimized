/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerPickupArrowEvent
extends PlayerPickupItemEvent {
    private final AbstractArrow arrow;

    public PlayerPickupArrowEvent(@NotNull Player player, @NotNull Item item, @NotNull AbstractArrow arrow) {
        super(player, item, 0);
        this.arrow = arrow;
    }

    @NotNull
    public AbstractArrow getArrow() {
        return this.arrow;
    }
}

