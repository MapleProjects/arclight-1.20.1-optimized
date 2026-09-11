/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrepareItemCraftEvent
extends InventoryEvent {
    private static final HandlerList handlers = new HandlerList();
    private boolean repair;
    private CraftingInventory matrix;

    public PrepareItemCraftEvent(@NotNull CraftingInventory what, @NotNull InventoryView view, boolean isRepair) {
        super(view);
        this.matrix = what;
        this.repair = isRepair;
    }

    @Nullable
    public Recipe getRecipe() {
        return this.matrix.getRecipe();
    }

    @Override
    @NotNull
    public CraftingInventory getInventory() {
        return this.matrix;
    }

    public boolean isRepair() {
        return this.repair;
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

