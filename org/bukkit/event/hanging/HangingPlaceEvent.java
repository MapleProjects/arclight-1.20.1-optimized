/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.hanging;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HangingPlaceEvent
extends HangingEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final Block block;
    private final BlockFace blockFace;
    private final EquipmentSlot hand;
    private final ItemStack itemStack;

    @Deprecated
    public HangingPlaceEvent(@NotNull Hanging hanging, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace, @Nullable EquipmentSlot hand) {
        this(hanging, player, block, blockFace, hand, null);
    }

    public HangingPlaceEvent(@NotNull Hanging hanging, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace, @Nullable EquipmentSlot hand, @Nullable ItemStack itemStack) {
        super(hanging);
        this.player = player;
        this.block = block;
        this.blockFace = blockFace;
        this.hand = hand;
        this.itemStack = itemStack;
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public Block getBlock() {
        return this.block;
    }

    @NotNull
    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    @Nullable
    public EquipmentSlot getHand() {
        return this.hand;
    }

    @Nullable
    public ItemStack getItemStack() {
        return this.itemStack;
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

