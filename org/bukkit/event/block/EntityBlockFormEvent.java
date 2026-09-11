/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockFormEvent;
import org.jetbrains.annotations.NotNull;

public class EntityBlockFormEvent
extends BlockFormEvent {
    private final Entity entity;

    public EntityBlockFormEvent(@NotNull Entity entity, @NotNull Block block, @NotNull BlockState blockstate) {
        super(block, blockstate);
        this.entity = entity;
    }

    @NotNull
    public Entity getEntity() {
        return this.entity;
    }
}

