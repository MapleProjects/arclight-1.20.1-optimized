/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

public class PlayerRecipeBookClickEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Recipe originalRecipe;
    private Recipe recipe;
    private boolean shiftClick;

    public PlayerRecipeBookClickEvent(@NotNull Player player, @NotNull Recipe recipe, boolean shiftClick) {
        super(player);
        this.originalRecipe = recipe;
        this.recipe = recipe;
        this.shiftClick = shiftClick;
    }

    @NotNull
    public Recipe getOriginalRecipe() {
        return this.originalRecipe;
    }

    @NotNull
    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(@NotNull Recipe recipe) {
        Preconditions.checkArgument((recipe != null ? 1 : 0) != 0, (Object)"recipe cannot be null");
        if (this.originalRecipe instanceof CraftingRecipe) {
            Preconditions.checkArgument((boolean)(recipe instanceof CraftingRecipe), (Object)"provided recipe must be a crafting recipe");
        } else {
            Preconditions.checkArgument((this.originalRecipe.getClass() == recipe.getClass() ? 1 : 0) != 0, (Object)"provided recipe must be of the same type as original recipe");
        }
        this.recipe = recipe;
    }

    public boolean isShiftClick() {
        return this.shiftClick;
    }

    public void setShiftClick(boolean shiftClick) {
        this.shiftClick = shiftClick;
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

