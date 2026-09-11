/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public class SmithingRecipe
implements Recipe,
Keyed {
    private final NamespacedKey key;
    private final ItemStack result;
    private final RecipeChoice base;
    private final RecipeChoice addition;

    @Deprecated
    public SmithingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) {
        this.key = key;
        this.result = result;
        this.base = base;
        this.addition = addition;
    }

    @NotNull
    public RecipeChoice getBase() {
        return this.base.clone();
    }

    @NotNull
    public RecipeChoice getAddition() {
        return this.addition.clone();
    }

    @Override
    @NotNull
    public ItemStack getResult() {
        return this.result.clone();
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }
}

