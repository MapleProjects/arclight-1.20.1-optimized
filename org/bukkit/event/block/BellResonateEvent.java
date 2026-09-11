/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class BellResonateEvent
extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<LivingEntity> resonatedEntities;

    public BellResonateEvent(@NotNull Block theBlock, @NotNull List<LivingEntity> resonatedEntities) {
        super(theBlock);
        this.resonatedEntities = resonatedEntities;
    }

    @NotNull
    public List<LivingEntity> getResonatedEntities() {
        return this.resonatedEntities;
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

