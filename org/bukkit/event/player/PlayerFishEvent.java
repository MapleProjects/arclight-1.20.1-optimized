/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerFishEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Entity entity;
    private boolean cancel = false;
    private int exp;
    private final State state;
    private final FishHook hookEntity;
    private final EquipmentSlot hand;

    public PlayerFishEvent(@NotNull Player player, @Nullable Entity entity, @NotNull FishHook hookEntity, @Nullable EquipmentSlot hand, @NotNull State state) {
        super(player);
        this.entity = entity;
        this.hookEntity = hookEntity;
        this.hand = hand;
        this.state = state;
    }

    public PlayerFishEvent(@NotNull Player player, @Nullable Entity entity, @NotNull FishHook hookEntity, @NotNull State state) {
        this(player, entity, hookEntity, null, state);
    }

    @Nullable
    public Entity getCaught() {
        return this.entity;
    }

    @NotNull
    public FishHook getHook() {
        return this.hookEntity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public int getExpToDrop() {
        return this.exp;
    }

    public void setExpToDrop(int amount) {
        this.exp = amount;
    }

    @Nullable
    public EquipmentSlot getHand() {
        return this.hand;
    }

    @NotNull
    public State getState() {
        return this.state;
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

    public static enum State {
        FISHING,
        CAUGHT_FISH,
        CAUGHT_ENTITY,
        IN_GROUND,
        FAILED_ATTEMPT,
        REEL_IN,
        BITE;

    }
}

