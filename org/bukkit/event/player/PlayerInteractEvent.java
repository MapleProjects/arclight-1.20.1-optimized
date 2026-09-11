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
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerInteractEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    protected ItemStack item;
    protected Action action;
    protected Block blockClicked;
    protected BlockFace blockFace;
    private Event.Result useClickedBlock;
    private Event.Result useItemInHand;
    private EquipmentSlot hand;
    private Vector clickedPosistion;

    public PlayerInteractEvent(@NotNull Player who, @NotNull Action action, @Nullable ItemStack item, @Nullable Block clickedBlock, @NotNull BlockFace clickedFace) {
        this(who, action, item, clickedBlock, clickedFace, EquipmentSlot.HAND);
    }

    public PlayerInteractEvent(@NotNull Player who, @NotNull Action action, @Nullable ItemStack item, @Nullable Block clickedBlock, @NotNull BlockFace clickedFace, @Nullable EquipmentSlot hand) {
        this(who, action, item, clickedBlock, clickedFace, hand, null);
    }

    public PlayerInteractEvent(@NotNull Player who, @NotNull Action action, @Nullable ItemStack item, @Nullable Block clickedBlock, @NotNull BlockFace clickedFace, @Nullable EquipmentSlot hand, @Nullable Vector clickedPosition) {
        super(who);
        this.action = action;
        this.item = item;
        this.blockClicked = clickedBlock;
        this.blockFace = clickedFace;
        this.hand = hand;
        this.clickedPosistion = clickedPosition;
        this.useItemInHand = Event.Result.DEFAULT;
        this.useClickedBlock = clickedBlock == null ? Event.Result.DENY : Event.Result.ALLOW;
    }

    @NotNull
    public Action getAction() {
        return this.action;
    }

    @Override
    @Deprecated
    public boolean isCancelled() {
        return this.useInteractedBlock() == Event.Result.DENY;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.setUseInteractedBlock(cancel ? Event.Result.DENY : (this.useInteractedBlock() == Event.Result.DENY ? Event.Result.DEFAULT : this.useInteractedBlock()));
        this.setUseItemInHand(cancel ? Event.Result.DENY : (this.useItemInHand() == Event.Result.DENY ? Event.Result.DEFAULT : this.useItemInHand()));
    }

    @Nullable
    public ItemStack getItem() {
        return this.item;
    }

    @NotNull
    public Material getMaterial() {
        if (!this.hasItem()) {
            return Material.AIR;
        }
        return this.item.getType();
    }

    public boolean hasBlock() {
        return this.blockClicked != null;
    }

    public boolean hasItem() {
        return this.item != null;
    }

    public boolean isBlockInHand() {
        if (!this.hasItem()) {
            return false;
        }
        return this.item.getType().isBlock();
    }

    @Nullable
    public Block getClickedBlock() {
        return this.blockClicked;
    }

    @NotNull
    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    @NotNull
    public Event.Result useInteractedBlock() {
        return this.useClickedBlock;
    }

    public void setUseInteractedBlock(@NotNull Event.Result useInteractedBlock) {
        this.useClickedBlock = useInteractedBlock;
    }

    @NotNull
    public Event.Result useItemInHand() {
        return this.useItemInHand;
    }

    public void setUseItemInHand(@NotNull Event.Result useItemInHand) {
        this.useItemInHand = useItemInHand;
    }

    @Nullable
    public EquipmentSlot getHand() {
        return this.hand;
    }

    @Nullable
    public Vector getClickedPosition() {
        return this.clickedPosistion;
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

