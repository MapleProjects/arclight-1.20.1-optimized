/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmithingRecipe;
import org.jetbrains.annotations.NotNull;

public class SmithingTrimRecipe
extends SmithingRecipe
implements ComplexRecipe {
    private final RecipeChoice template;

    public SmithingTrimRecipe(@NotNull NamespacedKey key, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) {
        super(key, new ItemStack(Material.AIR), base, addition);
        this.template = template;
    }

    @NotNull
    public RecipeChoice getTemplate() {
        return this.template.clone();
    }
}

