/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.weather.WeatherEvent;
import org.jetbrains.annotations.NotNull;

public class LightningStrikeEvent
extends WeatherEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final LightningStrike bolt;
    private final Cause cause;

    @Deprecated
    public LightningStrikeEvent(@NotNull World world, @NotNull LightningStrike bolt) {
        this(world, bolt, Cause.UNKNOWN);
    }

    public LightningStrikeEvent(@NotNull World world, @NotNull LightningStrike bolt, @NotNull Cause cause) {
        super(world);
        this.bolt = bolt;
        this.cause = cause;
    }

    @Override
    public boolean isCancelled() {
        return this.canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    @NotNull
    public LightningStrike getLightning() {
        return this.bolt;
    }

    @NotNull
    public Cause getCause() {
        return this.cause;
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

    public static enum Cause {
        COMMAND,
        CUSTOM,
        SPAWNER,
        TRIDENT,
        TRAP,
        WEATHER,
        UNKNOWN;

    }
}

