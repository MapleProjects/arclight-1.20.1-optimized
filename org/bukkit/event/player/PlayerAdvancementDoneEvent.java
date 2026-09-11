/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerAdvancementDoneEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Advancement advancement;

    public PlayerAdvancementDoneEvent(@NotNull Player who, @NotNull Advancement advancement) {
        super(who);
        this.advancement = advancement;
    }

    @NotNull
    public Advancement getAdvancement() {
        return this.advancement;
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

