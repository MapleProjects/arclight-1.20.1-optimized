/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FurnaceStartSmeltEvent
extends InventoryBlockStartEvent {
    private static final HandlerList handlers = new HandlerList();
    private final CookingRecipe<?> recipe;
    private int totalCookTime;

    public FurnaceStartSmeltEvent(@NotNull Block furnace, @NotNull ItemStack source, @NotNull CookingRecipe<?> recipe) {
        super(furnace, source);
        this.recipe = recipe;
        this.totalCookTime = recipe.getCookingTime();
    }

    @NotNull
    public CookingRecipe<?> getRecipe() {
        return this.recipe;
    }

    public int getTotalCookTime() {
        return this.totalCookTime;
    }

    public void setTotalCookTime(int cookTime) {
        this.totalCookTime = cookTime;
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

