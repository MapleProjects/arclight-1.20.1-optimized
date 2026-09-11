/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CreeperPowerEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final PowerCause cause;
    private LightningStrike bolt;

    public CreeperPowerEvent(@NotNull Creeper creeper, @NotNull LightningStrike bolt, @NotNull PowerCause cause) {
        this(creeper, cause);
        this.bolt = bolt;
    }

    public CreeperPowerEvent(@NotNull Creeper creeper, @NotNull PowerCause cause) {
        super(creeper);
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

    @Override
    @NotNull
    public Creeper getEntity() {
        return (Creeper)this.entity;
    }

    @Nullable
    public LightningStrike getLightning() {
        return this.bolt;
    }

    @NotNull
    public PowerCause getCause() {
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

    public static enum PowerCause {
        LIGHTNING,
        SET_ON,
        SET_OFF;

    }
}

