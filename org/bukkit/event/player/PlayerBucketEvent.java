/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PlayerBucketEvent
extends PlayerEvent
implements Cancellable {
    private ItemStack itemStack;
    private boolean cancelled = false;
    private final Block block;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final Material bucket;
    private final EquipmentSlot hand;

    @Deprecated
    public PlayerBucketEvent(@NotNull Player who, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
        this(who, null, blockClicked.getRelative(blockFace), blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    @Deprecated
    public PlayerBucketEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
        this(who, block, blockClicked, blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    public PlayerBucketEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @NotNull EquipmentSlot hand) {
        super(who);
        this.block = block;
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.itemStack = itemInHand;
        this.bucket = bucket;
        this.hand = hand;
    }

    @NotNull
    public Material getBucket() {
        return this.bucket;
    }

    @Nullable
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @NotNull
    public final Block getBlock() {
        return this.block;
    }

    @NotNull
    public Block getBlockClicked() {
        return this.blockClicked;
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
}

