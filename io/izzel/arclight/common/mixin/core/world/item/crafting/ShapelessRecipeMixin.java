/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CraftingBookCategory
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.ShapelessRecipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mod.util.ArclightSpecialRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftShapelessRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ShapelessRecipe.class})
public abstract class ShapelessRecipeMixin
implements IRecipeBridge {
    @Shadow
    @Final
    ItemStack f_44243_;
    @Shadow
    @Final
    String f_44242_;
    @Shadow
    @Final
    NonNullList<Ingredient> f_44244_;

    @Shadow
    public abstract CraftingBookCategory m_245232_();

    @Override
    public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        if (this.f_44243_.m_41619_()) {
            return new ArclightSpecialRecipe((Recipe<?>)((ShapelessRecipe)this));
        }
        CraftItemStack result = CraftItemStack.asCraftMirror(this.f_44243_);
        CraftShapelessRecipe recipe = new CraftShapelessRecipe(result, (ShapelessRecipe)this);
        recipe.setGroup(this.f_44242_);
        recipe.setCategory(CraftRecipe.getCategory(this.m_245232_()));
        for (Ingredient list : this.f_44244_) {
            recipe.addIngredient(CraftRecipe.toBukkit(list));
        }
        return recipe;
    }
}

