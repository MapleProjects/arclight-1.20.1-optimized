/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.entity.Piglin;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PiglinBarterEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final List<ItemStack> outcome;
    private final ItemStack input;

    public PiglinBarterEvent(@NotNull Piglin what, @NotNull ItemStack input, @NotNull List<ItemStack> outcome) {
        super(what);
        this.input = input;
        this.outcome = outcome;
    }

    @Override
    @NotNull
    public Piglin getEntity() {
        return (Piglin)super.getEntity();
    }

    @NotNull
    public ItemStack getInput() {
        return this.input.clone();
    }

    @NotNull
    public List<ItemStack> getOutcome() {
        return this.outcome;
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
}

