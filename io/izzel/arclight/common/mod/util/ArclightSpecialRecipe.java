/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.util;

import io.izzel.arclight.common.bridge.core.item.crafting.RecipeManagerBridge;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftComplexRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArclightSpecialRecipe
extends CraftComplexRecipe {
    private final Recipe<?> recipe;

    public ArclightSpecialRecipe(Recipe<?> recipe) {
        super(null);
        this.recipe = recipe;
    }

    @Override
    @NotNull
    public ItemStack getResult() {
        return CraftItemStack.asCraftMirror(this.recipe.m_8043_((RegistryAccess)ServerLifecycleHooks.getCurrentServer().m_206579_()));
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(this.recipe.m_6423_());
    }

    @Override
    public void addToCraftingManager() {
        ((RecipeManagerBridge)ServerLifecycleHooks.getCurrentServer().m_129894_()).bridge$addRecipe(this.recipe);
    }
}

