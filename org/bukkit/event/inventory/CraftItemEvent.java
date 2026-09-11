/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

public class CraftItemEvent
extends InventoryClickEvent {
    private Recipe recipe;

    public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action) {
        super(what, type, slot, click, action);
        this.recipe = recipe;
    }

    public CraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView what, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action, int key) {
        super(what, type, slot, click, action, key);
        this.recipe = recipe;
    }

    @NotNull
    public Recipe getRecipe() {
        return this.recipe;
    }

    @Override
    @NotNull
    public CraftingInventory getInventory() {
        return (CraftingInventory)super.getInventory();
    }
}

