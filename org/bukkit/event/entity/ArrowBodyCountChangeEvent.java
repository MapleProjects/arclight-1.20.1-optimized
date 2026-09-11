/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class ArrowBodyCountChangeEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final boolean isReset;
    private final int oldAmount;
    private int newAmount;

    public ArrowBodyCountChangeEvent(@NotNull LivingEntity entity, int oldAmount, int newAmount, boolean isReset) {
        super(entity);
        this.oldAmount = oldAmount;
        this.newAmount = newAmount;
        this.isReset = isReset;
    }

    public boolean isReset() {
        return this.isReset;
    }

    public int getOldAmount() {
        return this.oldAmount;
    }

    public int getNewAmount() {
        return this.newAmount;
    }

    public void setNewAmount(int newAmount) {
        Preconditions.checkArgument((newAmount >= 0 ? 1 : 0) != 0, (Object)"New arrow amount must be >= 0");
        this.newAmount = newAmount;
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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

