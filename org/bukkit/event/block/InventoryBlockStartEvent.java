/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Experimental
public class InventoryBlockStartEvent
extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack source;

    public InventoryBlockStartEvent(@NotNull Block block, @NotNull ItemStack source) {
        super(block);
        this.source = source;
    }

    @NotNull
    public ItemStack getSource() {
        return this.source;
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

