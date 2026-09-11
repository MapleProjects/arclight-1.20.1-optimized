/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityTransformEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Entity converted;
    private final List<Entity> convertedList;
    private final TransformReason transformReason;

    public EntityTransformEvent(@NotNull Entity original, @NotNull List<Entity> convertedList, @NotNull TransformReason transformReason) {
        super(original);
        this.convertedList = Collections.unmodifiableList(convertedList);
        this.converted = convertedList.get(0);
        this.transformReason = transformReason;
    }

    @NotNull
    public Entity getTransformedEntity() {
        return this.converted;
    }

    @NotNull
    public List<Entity> getTransformedEntities() {
        return this.convertedList;
    }

    @NotNull
    public TransformReason getTransformReason() {
        return this.transformReason;
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

    public static enum TransformReason {
        CURED,
        FROZEN,
        INFECTION,
        DROWNED,
        SHEARED,
        LIGHTNING,
        SPLIT,
        PIGLIN_ZOMBIFIED,
        METAMORPHOSIS,
        UNKNOWN;

    }
}

