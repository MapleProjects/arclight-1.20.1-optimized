/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BlockDamageAbortEvent
extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack itemstack;

    public BlockDamageAbortEvent(@NotNull Player player, @NotNull Block block, @NotNull ItemStack itemInHand) {
        super(block);
        this.player = player;
        this.itemstack = itemInHand;
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public ItemStack getItemInHand() {
        return this.itemstack;
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

