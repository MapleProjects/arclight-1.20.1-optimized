/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerHarvestBlockEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final Block harvestedBlock;
    private final EquipmentSlot hand;
    private final List<ItemStack> itemsHarvested;

    public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull EquipmentSlot hand, @NotNull List<ItemStack> itemsHarvested) {
        super(player);
        this.harvestedBlock = harvestedBlock;
        this.hand = hand;
        this.itemsHarvested = itemsHarvested;
    }

    @Deprecated
    public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull List<ItemStack> itemsHarvested) {
        this(player, harvestedBlock, EquipmentSlot.HAND, itemsHarvested);
    }

    @NotNull
    public Block getHarvestedBlock() {
        return this.harvestedBlock;
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    @NotNull
    public List<ItemStack> getItemsHarvested() {
        return this.itemsHarvested;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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

