/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.jetbrains.annotations.NotNull;

public abstract class CraftingRecipe
implements Recipe,
Keyed {
    private final NamespacedKey key;
    private final ItemStack output;
    private String group = "";
    private CraftingBookCategory category = CraftingBookCategory.MISC;

    protected CraftingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"key cannot be null");
        Preconditions.checkArgument((result.getType() != Material.AIR ? 1 : 0) != 0, (Object)"Recipe must have non-AIR result.");
        this.key = key;
        this.output = new ItemStack(result);
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @Override
    @NotNull
    public ItemStack getResult() {
        return this.output.clone();
    }

    @NotNull
    public String getGroup() {
        return this.group;
    }

    public void setGroup(@NotNull String group) {
        Preconditions.checkArgument((group != null ? 1 : 0) != 0, (Object)"group cannot be null");
        this.group = group;
    }

    @NotNull
    public CraftingBookCategory getCategory() {
        return this.category;
    }

    public void setCategory(@NotNull CraftingBookCategory category) {
        Preconditions.checkArgument((category != null ? 1 : 0) != 0, (Object)"category cannot be null");
        this.category = category;
    }
}

