/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityCombustByBlockEvent
extends EntityCombustEvent {
    private final Block combuster;

    public EntityCombustByBlockEvent(@Nullable Block combuster, @NotNull Entity combustee, int duration) {
        super(combustee, duration);
        this.combuster = combuster;
    }

    @Nullable
    public Block getCombuster() {
        return this.combuster;
    }
}

