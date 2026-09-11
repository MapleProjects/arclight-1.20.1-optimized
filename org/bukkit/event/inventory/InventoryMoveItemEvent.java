/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryMoveItemEvent
extends Event
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Inventory sourceInventory;
    private final Inventory destinationInventory;
    private ItemStack itemStack;
    private final boolean didSourceInitiate;

    public InventoryMoveItemEvent(@NotNull Inventory sourceInventory, @NotNull ItemStack itemStack, @NotNull Inventory destinationInventory, boolean didSourceInitiate) {
        Preconditions.checkArgument((itemStack != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        this.sourceInventory = sourceInventory;
        this.itemStack = itemStack;
        this.destinationInventory = destinationInventory;
        this.didSourceInitiate = didSourceInitiate;
    }

    @NotNull
    public Inventory getSource() {
        return this.sourceInventory;
    }

    @NotNull
    public ItemStack getItem() {
        return this.itemStack.clone();
    }

    public void setItem(@NotNull ItemStack itemStack) {
        Preconditions.checkArgument((itemStack != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null.  Cancel the event if you want nothing to be transferred.");
        this.itemStack = itemStack.clone();
    }

    @NotNull
    public Inventory getDestination() {
        return this.destinationInventory;
    }

    @NotNull
    public Inventory getInitiator() {
        return this.didSourceInitiate ? this.sourceInventory : this.destinationInventory;
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

