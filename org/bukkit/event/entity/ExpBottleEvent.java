/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

public class ExpBottleEvent
extends ProjectileHitEvent {
    private static final HandlerList handlers = new HandlerList();
    private int exp;
    private boolean showEffect = true;

    public ExpBottleEvent(@NotNull ThrownExpBottle bottle, int exp) {
        super(bottle);
        this.exp = exp;
    }

    @Override
    @NotNull
    public ThrownExpBottle getEntity() {
        return (ThrownExpBottle)this.entity;
    }

    public boolean getShowEffect() {
        return this.showEffect;
    }

    public void setShowEffect(boolean showEffect) {
        this.showEffect = showEffect;
    }

    public int getExperience() {
        return this.exp;
    }

    public void setExperience(int exp) {
        this.exp = exp;
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

