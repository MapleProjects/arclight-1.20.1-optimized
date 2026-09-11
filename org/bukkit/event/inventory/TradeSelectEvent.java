/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.jetbrains.annotations.NotNull;

public class TradeSelectEvent
extends InventoryInteractEvent {
    private static final HandlerList handlers = new HandlerList();
    private final int index;

    public TradeSelectEvent(@NotNull InventoryView transaction, int newIndex) {
        super(transaction);
        this.index = newIndex;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    @NotNull
    public MerchantInventory getInventory() {
        return (MerchantInventory)super.getInventory();
    }

    @NotNull
    public Merchant getMerchant() {
        return this.getInventory().getMerchant();
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

