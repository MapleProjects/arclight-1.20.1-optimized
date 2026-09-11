/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BlockDispenseEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private ItemStack item;
    private Vector velocity;

    public BlockDispenseEvent(@NotNull Block block, @NotNull ItemStack dispensed, @NotNull Vector velocity) {
        super(block);
        this.item = dispensed;
        this.velocity = velocity;
    }

    @NotNull
    public ItemStack getItem() {
        return this.item.clone();
    }

    public void setItem(@NotNull ItemStack item) {
        this.item = item;
    }

    @NotNull
    public Vector getVelocity() {
        return this.velocity.clone();
    }

    public void setVelocity(@NotNull Vector vel) {
        this.velocity = vel;
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

