/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerEggThrowEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Egg egg;
    private boolean hatching;
    private EntityType hatchType;
    private byte numHatches;

    public PlayerEggThrowEvent(@NotNull Player player, @NotNull Egg egg, boolean hatching, byte numHatches, @NotNull EntityType hatchingType) {
        super(player);
        this.egg = egg;
        this.hatching = hatching;
        this.numHatches = numHatches;
        this.hatchType = hatchingType;
    }

    @NotNull
    public Egg getEgg() {
        return this.egg;
    }

    public boolean isHatching() {
        return this.hatching;
    }

    public void setHatching(boolean hatching) {
        this.hatching = hatching;
    }

    @NotNull
    public EntityType getHatchingType() {
        return this.hatchType;
    }

    public void setHatchingType(@NotNull EntityType hatchType) {
        if (!hatchType.isSpawnable()) {
            throw new IllegalArgumentException("Can't spawn that entity type from an egg!");
        }
        this.hatchType = hatchType;
    }

    public byte getNumHatches() {
        return this.numHatches;
    }

    public void setNumHatches(byte numHatches) {
        this.numHatches = numHatches;
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

