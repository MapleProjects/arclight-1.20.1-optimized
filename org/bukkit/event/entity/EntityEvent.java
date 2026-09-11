/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class EntityEvent
extends Event {
    protected Entity entity;

    public EntityEvent(@NotNull Entity what) {
        this.entity = what;
    }

    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    @NotNull
    public EntityType getEntityType() {
        return this.entity.getType();
    }
}

