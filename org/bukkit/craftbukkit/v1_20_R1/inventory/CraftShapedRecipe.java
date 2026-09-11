/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.ShapedRecipe
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import java.util.Map;
import net.minecraft.core.NonNullList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

public class CraftShapedRecipe
extends org.bukkit.inventory.ShapedRecipe
implements CraftRecipe {
    private ShapedRecipe recipe;

    public CraftShapedRecipe(NamespacedKey key, ItemStack result) {
        super(key, result);
    }

    public CraftShapedRecipe(ItemStack result, ShapedRecipe recipe) {
        this(CraftNamespacedKey.fromMinecraft(recipe.m_6423_()), result);
        this.recipe = recipe;
    }

    public static CraftShapedRecipe fromBukkitRecipe(org.bukkit.inventory.ShapedRecipe recipe) {
        if (recipe instanceof CraftShapedRecipe) {
            return (CraftShapedRecipe)recipe;
        }
        CraftShapedRecipe ret = new CraftShapedRecipe(recipe.getKey(), recipe.getResult());
        ret.setGroup(recipe.getGroup());
        ret.setCategory(recipe.getCategory());
        String[] shape = recipe.getShape();
        ret.shape(shape);
        Map<Character, RecipeChoice> ingredientMap = recipe.getChoiceMap();
        for (char c : ingredientMap.keySet()) {
            RecipeChoice stack = ingredientMap.get(Character.valueOf(c));
            if (stack == null) continue;
            ret.setIngredient(c, stack);
        }
        return ret;
    }

    @Override
    public void addToCraftingManager() {
        String[] shape = this.getShape();
        Map<Character, RecipeChoice> ingred = this.getChoiceMap();
        int width = shape[0].length();
        NonNullList data = NonNullList.m_122780_((int)(shape.length * width), (Object)Ingredient.f_43901_);
        int i = 0;
        while (i < shape.length) {
            String row = shape[i];
            int j = 0;
            while (j < row.length()) {
                data.set(i * width + j, (Object)this.toNMS(ingred.get(Character.valueOf(row.charAt(j))), false));
                ++j;
            }
            ++i;
        }
        MinecraftServer.getServer().m_129894_().addRecipe((Recipe)new ShapedRecipe(CraftNamespacedKey.toMinecraft(this.getKey()), this.getGroup(), CraftRecipe.getCategory(this.getCategory()), width, shape.length, data, CraftItemStack.asNMSCopy(this.getResult())));
    }
}

