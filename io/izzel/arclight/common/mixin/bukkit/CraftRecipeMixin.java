/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Ingredient$ItemValue
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.core.item.crafting.IngredientBridge;
import io.izzel.arclight.common.mod.inventory.ArclightSpecialIngredient;
import java.util.ArrayList;
import net.minecraft.world.item.crafting.Ingredient;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CraftRecipe.class}, remap=false)
public interface CraftRecipeMixin {
    @Overwrite
    default public Ingredient toNMS(RecipeChoice bukkit, boolean requireNotEmpty) {
        Ingredient stack;
        if (bukkit == null) {
            stack = Ingredient.f_43901_;
        } else if (bukkit instanceof RecipeChoice.MaterialChoice) {
            stack = new Ingredient(((RecipeChoice.MaterialChoice)bukkit).getChoices().stream().map(mat -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(new ItemStack((Material)mat)))));
        } else if (bukkit instanceof RecipeChoice.ExactChoice) {
            stack = new Ingredient(((RecipeChoice.ExactChoice)bukkit).getChoices().stream().map(mat -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(mat))));
            ((IngredientBridge)stack).bridge$setExact(true);
        } else if (bukkit instanceof ArclightSpecialIngredient) {
            stack = ((ArclightSpecialIngredient)bukkit).getIngredient();
        } else {
            throw new IllegalArgumentException("Unknown recipe stack instance " + String.valueOf(bukkit));
        }
        stack.m_43908_();
        if (stack.isVanilla() && requireNotEmpty && stack.m_43908_().length == 0) {
            throw new IllegalArgumentException("Recipe requires at least one non-air choice!");
        }
        return stack;
    }

    @Overwrite
    public static RecipeChoice toBukkit(Ingredient list) {
        list.m_43908_();
        if (!list.isVanilla()) {
            return new ArclightSpecialIngredient(list);
        }
        net.minecraft.world.item.ItemStack[] items = list.m_43908_();
        if (items.length == 0) {
            return null;
        }
        if (((IngredientBridge)list).bridge$isExact()) {
            ArrayList<ItemStack> choices = new ArrayList<ItemStack>(items.length);
            for (net.minecraft.world.item.ItemStack i : items) {
                choices.add(CraftItemStack.asBukkitCopy(i));
            }
            return new RecipeChoice.ExactChoice(choices);
        }
        ArrayList<Material> choices = new ArrayList<Material>(items.length);
        for (net.minecraft.world.item.ItemStack i : items) {
            choices.add(CraftMagicNumbers.getMaterial(i.m_41720_()));
        }
        return new RecipeChoice.MaterialChoice(choices);
    }
}

