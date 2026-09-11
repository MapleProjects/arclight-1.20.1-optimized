/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityExhaustionEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ExhaustionReason exhaustionReason;
    private float exhaustion;
    private boolean cancel;

    public EntityExhaustionEvent(@NotNull HumanEntity who, @NotNull ExhaustionReason exhaustionReason, float exhaustion) {
        super(who);
        this.exhaustionReason = exhaustionReason;
        this.exhaustion = exhaustion;
    }

    @NotNull
    public ExhaustionReason getExhaustionReason() {
        return this.exhaustionReason;
    }

    public float getExhaustion() {
        return this.exhaustion;
    }

    public void setExhaustion(float exhaustion) {
        this.exhaustion = exhaustion;
    }

    @Override
    @NotNull
    public HumanEntity getEntity() {
        return (HumanEntity)super.getEntity();
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

    public static enum ExhaustionReason {
        BLOCK_MINED,
        HUNGER_EFFECT,
        DAMAGED,
        ATTACK,
        JUMP_SPRINT,
        JUMP,
        SWIM,
        WALK_UNDERWATER,
        WALK_ON_WATER,
        SPRINT,
        CROUCH,
        WALK,
        REGEN,
        UNKNOWN;

    }
}

