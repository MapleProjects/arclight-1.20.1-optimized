/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnderDragonChangePhaseEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private final EnderDragon.Phase currentPhase;
    private EnderDragon.Phase newPhase;

    public EnderDragonChangePhaseEvent(@NotNull EnderDragon enderDragon, @Nullable EnderDragon.Phase currentPhase, @NotNull EnderDragon.Phase newPhase) {
        super(enderDragon);
        this.currentPhase = currentPhase;
        this.setNewPhase(newPhase);
    }

    @Override
    @NotNull
    public EnderDragon getEntity() {
        return (EnderDragon)this.entity;
    }

    @Nullable
    public EnderDragon.Phase getCurrentPhase() {
        return this.currentPhase;
    }

    @NotNull
    public EnderDragon.Phase getNewPhase() {
        return this.newPhase;
    }

    public void setNewPhase(@NotNull EnderDragon.Phase newPhase) {
        Preconditions.checkArgument((newPhase != null ? 1 : 0) != 0, (Object)"New dragon phase cannot be null");
        this.newPhase = newPhase;
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
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

