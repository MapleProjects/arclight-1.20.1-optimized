/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CookingBookCategory
 *  net.minecraft.world.item.crafting.CraftingBookCategory
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$ItemValue
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.recipe.CookingBookCategory;

public interface CraftRecipe
extends Recipe {
    public void addToCraftingManager();

    default public Ingredient toNMS(RecipeChoice bukkit, boolean requireNotEmpty) {
        Ingredient stack;
        if (bukkit == null) {
            stack = Ingredient.f_43901_;
        } else if (bukkit instanceof RecipeChoice.MaterialChoice) {
            stack = new Ingredient(((RecipeChoice.MaterialChoice)bukkit).getChoices().stream().map(mat -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(new ItemStack((Material)mat)))));
        } else if (bukkit instanceof RecipeChoice.ExactChoice) {
            stack = new Ingredient(((RecipeChoice.ExactChoice)bukkit).getChoices().stream().map(mat -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(mat))));
            stack.exact = true;
        } else {
            throw new IllegalArgumentException("Unknown recipe stack instance " + bukkit);
        }
        stack.m_43908_();
        if (requireNotEmpty) {
            Preconditions.checkArgument((stack.f_43903_.length != 0 ? 1 : 0) != 0, (Object)"Recipe requires at least one non-air choice");
        }
        return stack;
    }

    public static RecipeChoice toBukkit(Ingredient list) {
        list.m_43908_();
        if (list.f_43903_.length == 0) {
            return null;
        }
        if (list.exact) {
            ArrayList<ItemStack> choices = new ArrayList<ItemStack>(list.f_43903_.length);
            net.minecraft.world.item.ItemStack[] itemStackArray = list.f_43903_;
            int n = list.f_43903_.length;
            int n2 = 0;
            while (n2 < n) {
                net.minecraft.world.item.ItemStack i = itemStackArray[n2];
                choices.add(CraftItemStack.asBukkitCopy(i));
                ++n2;
            }
            return new RecipeChoice.ExactChoice(choices);
        }
        ArrayList<Material> choices = new ArrayList<Material>(list.f_43903_.length);
        net.minecraft.world.item.ItemStack[] itemStackArray = list.f_43903_;
        int n = list.f_43903_.length;
        int n3 = 0;
        while (n3 < n) {
            net.minecraft.world.item.ItemStack i = itemStackArray[n3];
            choices.add(CraftMagicNumbers.getMaterial(i.m_41720_()));
            ++n3;
        }
        return new RecipeChoice.MaterialChoice(choices);
    }

    public static CraftingBookCategory getCategory(org.bukkit.inventory.recipe.CraftingBookCategory bukkit) {
        return CraftingBookCategory.valueOf((String)bukkit.name());
    }

    public static org.bukkit.inventory.recipe.CraftingBookCategory getCategory(CraftingBookCategory nms) {
        return org.bukkit.inventory.recipe.CraftingBookCategory.valueOf(nms.name());
    }

    public static net.minecraft.world.item.crafting.CookingBookCategory getCategory(CookingBookCategory bukkit) {
        return net.minecraft.world.item.crafting.CookingBookCategory.valueOf((String)bukkit.name());
    }

    public static CookingBookCategory getCategory(net.minecraft.world.item.crafting.CookingBookCategory nms) {
        return CookingBookCategory.valueOf(nms.name());
    }
}

