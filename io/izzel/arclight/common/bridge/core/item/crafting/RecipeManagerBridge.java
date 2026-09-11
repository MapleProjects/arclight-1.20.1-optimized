/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.crafting.Recipe
 */
package io.izzel.arclight.common.bridge.core.item.crafting;

import net.minecraft.world.item.crafting.Recipe;

public interface RecipeManagerBridge {
    public void bridge$addRecipe(Recipe<?> var1);

    public void bridge$clearRecipes();
}

