/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CampfireStartEvent
extends InventoryBlockStartEvent {
    private static final HandlerList handlers = new HandlerList();
    private int cookingTime;
    private CampfireRecipe campfireRecipe;

    public CampfireStartEvent(@NotNull Block furnace, @NotNull ItemStack source, @NotNull CampfireRecipe recipe) {
        super(furnace, source);
        this.cookingTime = recipe.getCookingTime();
        this.campfireRecipe = recipe;
    }

    @NotNull
    public CampfireRecipe getRecipe() {
        return this.campfireRecipe;
    }

    public int getTotalCookTime() {
        return this.cookingTime;
    }

    public void setTotalCookTime(int cookTime) {
        this.cookingTime = cookTime;
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

