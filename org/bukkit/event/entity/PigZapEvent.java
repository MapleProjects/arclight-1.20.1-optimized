/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import java.util.Collections;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityTransformEvent;
import org.jetbrains.annotations.NotNull;

public class PigZapEvent
extends EntityTransformEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final PigZombie pigzombie;
    private final LightningStrike bolt;

    public PigZapEvent(@NotNull Pig pig, @NotNull LightningStrike bolt, @NotNull PigZombie pigzombie) {
        super(pig, Collections.singletonList(pigzombie), EntityTransformEvent.TransformReason.LIGHTNING);
        this.bolt = bolt;
        this.pigzombie = pigzombie;
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
    public Pig getEntity() {
        return (Pig)this.entity;
    }

    @NotNull
    public LightningStrike getLightning() {
        return this.bolt;
    }

    @Deprecated
    @NotNull
    public PigZombie getPigZombie() {
        return this.pigzombie;
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

