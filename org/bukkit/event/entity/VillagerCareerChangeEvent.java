/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class VillagerCareerChangeEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Villager.Profession profession;
    private final ChangeReason reason;

    public VillagerCareerChangeEvent(@NotNull Villager what, @NotNull Villager.Profession profession, @NotNull ChangeReason reason) {
        super(what);
        this.profession = profession;
        this.reason = reason;
    }

    @Override
    @NotNull
    public Villager getEntity() {
        return (Villager)super.getEntity();
    }

    @NotNull
    public Villager.Profession getProfession() {
        return this.profession;
    }

    public void setProfession(@NotNull Villager.Profession profession) {
        this.profession = profession;
    }

    @NotNull
    public ChangeReason getReason() {
        return this.reason;
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

    public static enum ChangeReason {
        LOSING_JOB,
        EMPLOYED;

    }
}

