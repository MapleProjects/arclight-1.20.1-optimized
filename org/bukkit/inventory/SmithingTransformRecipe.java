/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;
import org.jetbrains.annotations.NotNull;

public class SmithingTransformRecipe
extends SmithingRecipe {
    private final RecipeChoice template;

    public SmithingTransformRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) {
        super(key, result, base, addition);
        this.template = template;
    }

    @NotNull
    public RecipeChoice getTemplate() {
        return this.template.clone();
    }
}

