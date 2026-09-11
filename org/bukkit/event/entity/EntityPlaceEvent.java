/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityPlaceEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final Block block;
    private final BlockFace blockFace;
    private final EquipmentSlot hand;

    public EntityPlaceEvent(@NotNull Entity entity, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace, @NotNull EquipmentSlot hand) {
        super(entity);
        this.player = player;
        this.block = block;
        this.blockFace = blockFace;
        this.hand = hand;
    }

    @Deprecated
    public EntityPlaceEvent(@NotNull Entity entity, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace) {
        this(entity, player, block, blockFace, EquipmentSlot.HAND);
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

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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

