/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public class ShapelessRecipe
extends CraftingRecipe {
    private final List<RecipeChoice> ingredients = new ArrayList<RecipeChoice>();

    @Deprecated
    public ShapelessRecipe(@NotNull ItemStack result) {
        super(NamespacedKey.randomKey(), result);
    }

    public ShapelessRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        super(key, result);
    }

    @NotNull
    public ShapelessRecipe addIngredient(@NotNull MaterialData ingredient) {
        return this.addIngredient(1, ingredient);
    }

    @NotNull
    public ShapelessRecipe addIngredient(@NotNull Material ingredient) {
        return this.addIngredient(1, ingredient, 0);
    }

    @Deprecated
    @NotNull
    public ShapelessRecipe addIngredient(@NotNull Material ingredient, int rawdata) {
        return this.addIngredient(1, ingredient, rawdata);
    }

    @NotNull
    public ShapelessRecipe addIngredient(int count, @NotNull MaterialData ingredient) {
        return this.addIngredient(count, ingredient.getItemType(), ingredient.getData());
    }

    @NotNull
    public ShapelessRecipe addIngredient(int count, @NotNull Material ingredient) {
        return this.addIngredient(count, ingredient, 0);
    }

    @Deprecated
    @NotNull
    public ShapelessRecipe addIngredient(int count, @NotNull Material ingredient, int rawdata) {
        Preconditions.checkArgument((this.ingredients.size() + count <= 9 ? 1 : 0) != 0, (Object)"Shapeless recipes cannot have more than 9 ingredients");
        if (rawdata == -1) {
            rawdata = Short.MAX_VALUE;
        }
        while (count-- > 0) {
            this.ingredients.add(new RecipeChoice.MaterialChoice(Collections.singletonList(ingredient)));
        }
        return this;
    }

    @NotNull
    public ShapelessRecipe addIngredient(@NotNull RecipeChoice ingredient) {
        Preconditions.checkArgument((this.ingredients.size() + 1 <= 9 ? 1 : 0) != 0, (Object)"Shapeless recipes cannot have more than 9 ingredients");
        this.ingredients.add(ingredient);
        return this;
    }

    @NotNull
    public ShapelessRecipe removeIngredient(@NotNull RecipeChoice ingredient) {
        this.ingredients.remove(ingredient);
        return this;
    }

    @NotNull
    public ShapelessRecipe removeIngredient(@NotNull Material ingredient) {
        return this.removeIngredient(ingredient, 0);
    }

    @NotNull
    public ShapelessRecipe removeIngredient(@NotNull MaterialData ingredient) {
        return this.removeIngredient(ingredient.getItemType(), ingredient.getData());
    }

    @NotNull
    public ShapelessRecipe removeIngredient(int count, @NotNull Material ingredient) {
        return this.removeIngredient(count, ingredient, 0);
    }

    @NotNull
    public ShapelessRecipe removeIngredient(int count, @NotNull MaterialData ingredient) {
        return this.removeIngredient(count, ingredient.getItemType(), ingredient.getData());
    }

    @Deprecated
    @NotNull
    public ShapelessRecipe removeIngredient(@NotNull Material ingredient, int rawdata) {
        return this.removeIngredient(1, ingredient, rawdata);
    }

    @Deprecated
    @NotNull
    public ShapelessRecipe removeIngredient(int count, @NotNull Material ingredient, int rawdata) {
        Iterator<RecipeChoice> iterator = this.ingredients.iterator();
        while (count > 0 && iterator.hasNext()) {
            ItemStack stack = iterator.next().getItemStack();
            if (stack.getType() != ingredient || stack.getDurability() != rawdata) continue;
            iterator.remove();
            --count;
        }
        return this;
    }

    @NotNull
    public List<ItemStack> getIngredientList() {
        ArrayList<ItemStack> result = new ArrayList<ItemStack>(this.ingredients.size());
        for (RecipeChoice ingredient : this.ingredients) {
            result.add(ingredient.getItemStack().clone());
        }
        return result;
    }

    @NotNull
    public List<RecipeChoice> getChoiceList() {
        ArrayList<RecipeChoice> result = new ArrayList<RecipeChoice>(this.ingredients.size());
        for (RecipeChoice ingredient : this.ingredients) {
            result.add(ingredient.clone());
        }
        return result;
    }
}

