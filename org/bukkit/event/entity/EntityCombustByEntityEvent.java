/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustEvent;
import org.jetbrains.annotations.NotNull;

public class EntityCombustByEntityEvent
extends EntityCombustEvent {
    private final Entity combuster;

    public EntityCombustByEntityEvent(@NotNull Entity combuster, @NotNull Entity combustee, int duration) {
        super(combustee, duration);
        this.combuster = combuster;
    }

    @NotNull
    public Entity getCombuster() {
        return this.combuster;
    }
}

