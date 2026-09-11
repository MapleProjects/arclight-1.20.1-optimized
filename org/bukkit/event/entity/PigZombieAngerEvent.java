/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PigZombieAngerEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final Entity target;
    private int newAnger;

    public PigZombieAngerEvent(@NotNull PigZombie pigZombie, @Nullable Entity target, int newAnger) {
        super(pigZombie);
        this.target = target;
        this.newAnger = newAnger;
    }

    @Nullable
    public Entity getTarget() {
        return this.target;
    }

    public int getNewAnger() {
        return this.newAnger;
    }

    public void setNewAnger(int newAnger) {
        this.newAnger = newAnger;
    }

    @Override
    @NotNull
    public PigZombie getEntity() {
        return (PigZombie)this.entity;
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
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

