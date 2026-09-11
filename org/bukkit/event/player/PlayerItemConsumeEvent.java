/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerItemConsumeEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;
    private ItemStack item;
    private final EquipmentSlot hand;

    public PlayerItemConsumeEvent(@NotNull Player player, @NotNull ItemStack item, @NotNull EquipmentSlot hand) {
        super(player);
        this.item = item;
        this.hand = hand;
    }

    @Deprecated
    public PlayerItemConsumeEvent(@NotNull Player player, @NotNull ItemStack item) {
        this(player, item, EquipmentSlot.HAND);
    }

    @NotNull
    public ItemStack getItem() {
        return this.item.clone();
    }

    public void setItem(@Nullable ItemStack item) {
        this.item = item == null ? new ItemStack(Material.AIR) : item;
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
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

