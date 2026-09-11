/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityTargetEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Entity target;
    private final TargetReason reason;

    public EntityTargetEvent(@NotNull Entity entity, @Nullable Entity target, @NotNull TargetReason reason) {
        super(entity);
        this.target = target;
        this.reason = reason;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public TargetReason getReason() {
        return this.reason;
    }

    @Nullable
    public Entity getTarget() {
        return this.target;
    }

    public void setTarget(@Nullable Entity target) {
        this.target = target;
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

    public static enum TargetReason {
        TARGET_DIED,
        CLOSEST_PLAYER,
        TARGET_ATTACKED_ENTITY,
        PIG_ZOMBIE_TARGET,
        FORGOT_TARGET,
        TARGET_ATTACKED_OWNER,
        OWNER_ATTACKED_TARGET,
        RANDOM_TARGET,
        DEFEND_VILLAGE,
        TARGET_ATTACKED_NEARBY_ENTITY,
        REINFORCEMENT_TARGET,
        COLLISION,
        CUSTOM,
        CLOSEST_ENTITY,
        FOLLOW_LEADER,
        TEMPT,
        UNKNOWN;

    }
}

