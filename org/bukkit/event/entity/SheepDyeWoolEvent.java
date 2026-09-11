/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SheepDyeWoolEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private DyeColor color;
    private final Player player;

    @Deprecated
    public SheepDyeWoolEvent(@NotNull Sheep sheep, @NotNull DyeColor color) {
        this(sheep, color, null);
    }

    public SheepDyeWoolEvent(@NotNull Sheep sheep, @NotNull DyeColor color, @Nullable Player player) {
        super(sheep);
        this.color = color;
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    @NotNull
    public Sheep getEntity() {
        return (Sheep)this.entity;
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(@NotNull DyeColor color) {
        this.color = color;
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

