/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BrewingStartEvent
extends InventoryBlockStartEvent {
    private static final HandlerList handlers = new HandlerList();
    private int brewingTime;

    public BrewingStartEvent(@NotNull Block furnace, @NotNull ItemStack source, int brewingTime) {
        super(furnace, source);
        this.brewingTime = brewingTime;
    }

    public int getTotalBrewTime() {
        return this.brewingTime;
    }

    public void setTotalBrewTime(int brewTime) {
        this.brewingTime = brewTime;
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

