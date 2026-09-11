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
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public class ShapedRecipe
extends CraftingRecipe {
    private String[] rows;
    private Map<Character, RecipeChoice> ingredients = new HashMap<Character, RecipeChoice>();

    @Deprecated
    public ShapedRecipe(@NotNull ItemStack result) {
        super(NamespacedKey.randomKey(), result);
    }

    public ShapedRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        super(key, result);
    }

    @NotNull
    public ShapedRecipe shape(String ... shape) {
        Preconditions.checkArgument((shape != null ? 1 : 0) != 0, (Object)"Must provide a shape");
        Preconditions.checkArgument((shape.length > 0 && shape.length < 4 ? 1 : 0) != 0, (String)"Crafting recipes should be 1, 2 or 3 rows, not ", (int)shape.length);
        int lastLen = -1;
        String[] stringArray = shape;
        int n = shape.length;
        int n2 = 0;
        while (n2 < n) {
            String row = stringArray[n2];
            Preconditions.checkArgument((row != null ? 1 : 0) != 0, (Object)"Shape cannot have null rows");
            Preconditions.checkArgument((row.length() > 0 && row.length() < 4 ? 1 : 0) != 0, (String)"Crafting rows should be 1, 2, or 3 characters, not ", (int)row.length());
            Preconditions.checkArgument((lastLen == -1 || lastLen == row.length() ? 1 : 0) != 0, (Object)"Crafting recipes must be rectangular");
            lastLen = row.length();
            ++n2;
        }
        this.rows = new String[shape.length];
        int i = 0;
        while (i < shape.length) {
            this.rows[i] = shape[i];
            ++i;
        }
        HashMap<Character, RecipeChoice> newIngredients = new HashMap<Character, RecipeChoice>();
        String[] stringArray2 = shape;
        int n3 = shape.length;
        n = 0;
        while (n < n3) {
            String row = stringArray2[n];
            char[] cArray = row.toCharArray();
            int n4 = cArray.length;
            int n5 = 0;
            while (n5 < n4) {
                Character c = Character.valueOf(cArray[n5]);
                newIngredients.put(c, this.ingredients.get(c));
                ++n5;
            }
            ++n;
        }
        this.ingredients = newIngredients;
        return this;
    }

    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull MaterialData ingredient) {
        return this.setIngredient(key, ingredient.getItemType(), ingredient.getData());
    }

    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull Material ingredient) {
        return this.setIngredient(key, ingredient, 0);
    }

    @Deprecated
    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull Material ingredient, int raw) {
        Preconditions.checkArgument((boolean)this.ingredients.containsKey(Character.valueOf(key)), (String)"Symbol does not appear in the shape:", (char)key);
        if (raw == -1) {
            raw = Short.MAX_VALUE;
        }
        this.ingredients.put(Character.valueOf(key), new RecipeChoice.MaterialChoice(Collections.singletonList(ingredient)));
        return this;
    }

    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull RecipeChoice ingredient) {
        Preconditions.checkArgument((boolean)this.ingredients.containsKey(Character.valueOf(key)), (String)"Symbol does not appear in the shape:", (char)key);
        this.ingredients.put(Character.valueOf(key), ingredient);
        return this;
    }

    @NotNull
    public Map<Character, ItemStack> getIngredientMap() {
        HashMap<Character, ItemStack> result = new HashMap<Character, ItemStack>();
        for (Map.Entry<Character, RecipeChoice> ingredient : this.ingredients.entrySet()) {
            if (ingredient.getValue() == null) {
                result.put(ingredient.getKey(), null);
                continue;
            }
            result.put(ingredient.getKey(), ingredient.getValue().getItemStack().clone());
        }
        return result;
    }

    @NotNull
    public Map<Character, RecipeChoice> getChoiceMap() {
        HashMap<Character, RecipeChoice> result = new HashMap<Character, RecipeChoice>();
        for (Map.Entry<Character, RecipeChoice> ingredient : this.ingredients.entrySet()) {
            if (ingredient.getValue() == null) {
                result.put(ingredient.getKey(), null);
                continue;
            }
            result.put(ingredient.getKey(), ingredient.getValue().clone());
        }
        return result;
    }

    @NotNull
    public String[] getShape() {
        return (String[])this.rows.clone();
    }
}

