/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.Collections;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public class StonecuttingRecipe
implements Recipe,
Keyed {
    private final NamespacedKey key;
    private ItemStack output;
    private RecipeChoice ingredient;
    private String group = "";

    public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source) {
        this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)));
    }

    public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input) {
        Preconditions.checkArgument((result.getType() != Material.AIR ? 1 : 0) != 0, (Object)"Recipe must have non-AIR result.");
        this.key = key;
        this.output = new ItemStack(result);
        this.ingredient = input;
    }

    @NotNull
    public StonecuttingRecipe setInput(@NotNull Material input) {
        this.ingredient = new RecipeChoice.MaterialChoice(Collections.singletonList(input));
        return this;
    }

    @NotNull
    public ItemStack getInput() {
        return this.ingredient.getItemStack();
    }

    @NotNull
    public StonecuttingRecipe setInputChoice(@NotNull RecipeChoice input) {
        this.ingredient = input;
        return this;
    }

    @NotNull
    public RecipeChoice getInputChoice() {
        return this.ingredient.clone();
    }

    @Override
    @NotNull
    public ItemStack getResult() {
        return this.output.clone();
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @NotNull
    public String getGroup() {
        return this.group;
    }

    public void setGroup(@NotNull String group) {
        Preconditions.checkArgument((group != null ? 1 : 0) != 0, (Object)"group cannot be null");
        this.group = group;
    }
}

