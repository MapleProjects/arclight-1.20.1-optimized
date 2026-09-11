/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.ShapelessRecipe
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftShapelessRecipe
extends ShapelessRecipe
implements CraftRecipe {
    private net.minecraft.world.item.crafting.ShapelessRecipe recipe;

    public CraftShapelessRecipe(NamespacedKey key, ItemStack result) {
        super(key, result);
    }

    public CraftShapelessRecipe(ItemStack result, net.minecraft.world.item.crafting.ShapelessRecipe recipe) {
        this(CraftNamespacedKey.fromMinecraft(recipe.m_6423_()), result);
        this.recipe = recipe;
    }

    public static CraftShapelessRecipe fromBukkitRecipe(ShapelessRecipe recipe) {
        if (recipe instanceof CraftShapelessRecipe) {
            return (CraftShapelessRecipe)recipe;
        }
        CraftShapelessRecipe ret = new CraftShapelessRecipe(recipe.getKey(), recipe.getResult());
        ret.setGroup(recipe.getGroup());
        ret.setCategory(recipe.getCategory());
        for (RecipeChoice ingred : recipe.getChoiceList()) {
            ret.addIngredient(ingred);
        }
        return ret;
    }

    @Override
    public void addToCraftingManager() {
        List<RecipeChoice> ingred = this.getChoiceList();
        NonNullList data = NonNullList.m_122780_((int)ingred.size(), (Object)Ingredient.f_43901_);
        int i = 0;
        while (i < ingred.size()) {
            data.set(i, (Object)this.toNMS(ingred.get(i), true));
            ++i;
        }
        MinecraftServer.getServer().m_129894_().addRecipe((Recipe)new net.minecraft.world.item.crafting.ShapelessRecipe(CraftNamespacedKey.toMinecraft(this.getKey()), this.getGroup(), CraftRecipe.getCategory(this.getCategory()), CraftItemStack.asNMSCopy(this.getResult()), data));
    }
}

