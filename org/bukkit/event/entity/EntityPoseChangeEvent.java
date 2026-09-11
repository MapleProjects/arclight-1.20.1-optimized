/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityPoseChangeEvent
extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Pose pose;

    public EntityPoseChangeEvent(@NotNull Entity who, @NotNull Pose pose) {
        super(who);
        this.pose = pose;
    }

    @NotNull
    public Pose getPose() {
        return this.pose;
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

