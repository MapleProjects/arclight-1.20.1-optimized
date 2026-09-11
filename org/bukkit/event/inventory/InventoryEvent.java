/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import java.util.List;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

public class InventoryEvent
extends Event {
    private static final HandlerList handlers = new HandlerList();
    protected InventoryView transaction;

    public InventoryEvent(@NotNull InventoryView transaction) {
        this.transaction = transaction;
    }

    @NotNull
    public Inventory getInventory() {
        return this.transaction.getTopInventory();
    }

    @NotNull
    public List<HumanEntity> getViewers() {
        return this.transaction.getTopInventory().getViewers();
    }

    @NotNull
    public InventoryView getView() {
        return this.transaction;
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

