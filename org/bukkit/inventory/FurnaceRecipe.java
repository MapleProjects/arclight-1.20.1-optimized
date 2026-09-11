/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import java.util.Collections;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public class FurnaceRecipe
extends CookingRecipe<FurnaceRecipe> {
    @Deprecated
    public FurnaceRecipe(@NotNull ItemStack result, @NotNull Material source) {
        this(NamespacedKey.randomKey(), result, source, 0, 0.0f, 200);
    }

    @Deprecated
    public FurnaceRecipe(@NotNull ItemStack result, @NotNull MaterialData source) {
        this(NamespacedKey.randomKey(), result, source.getItemType(), source.getData(), 0.0f, 200);
    }

    @Deprecated
    public FurnaceRecipe(@NotNull ItemStack result, @NotNull MaterialData source, float experience) {
        this(NamespacedKey.randomKey(), result, source.getItemType(), source.getData(), experience, 200);
    }

    @Deprecated
    public FurnaceRecipe(@NotNull ItemStack result, @NotNull Material source, int data) {
        this(NamespacedKey.randomKey(), result, source, data, 0.0f, 200);
    }

    public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
        this(key, result, source, 0, experience, cookingTime);
    }

    @Deprecated
    public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, int data, float experience, int cookingTime) {
        this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)), experience, cookingTime);
    }

    public FurnaceRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
        super(key, result, input, experience, cookingTime);
    }

    @NotNull
    public FurnaceRecipe setInput(@NotNull MaterialData input) {
        return this.setInput(input.getItemType(), input.getData());
    }

    @Override
    @NotNull
    public FurnaceRecipe setInput(@NotNull Material input) {
        return (FurnaceRecipe)super.setInput(input);
    }

    @Deprecated
    public FurnaceRecipe setInput(@NotNull Material input, int data) {
        return this.setInputChoice(new RecipeChoice.MaterialChoice(Collections.singletonList(input)));
    }

    @Override
    @NotNull
    public FurnaceRecipe setInputChoice(@NotNull RecipeChoice input) {
        return (FurnaceRecipe)super.setInputChoice(input);
    }
}

