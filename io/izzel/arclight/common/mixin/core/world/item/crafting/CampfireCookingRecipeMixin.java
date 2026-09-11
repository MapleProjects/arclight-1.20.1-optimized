/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.AbstractCookingRecipe
 *  net.minecraft.world.item.crafting.CampfireCookingRecipe
 *  net.minecraft.world.item.crafting.CookingBookCategory
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeType
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mod.util.ArclightSpecialRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftCampfireRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={CampfireCookingRecipe.class})
public abstract class CampfireCookingRecipeMixin
extends AbstractCookingRecipe
implements IRecipeBridge {
    public CampfireCookingRecipeMixin(RecipeType<?> p_250197_, ResourceLocation p_249379_, String p_249518_, CookingBookCategory p_250891_, Ingredient p_251354_, net.minecraft.world.item.ItemStack p_252185_, float p_252165_, int p_250256_) {
        super(p_250197_, p_249379_, p_249518_, p_250891_, p_251354_, p_252185_, p_252165_, p_250256_);
    }

    @Override
    public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        if (this.f_43730_.m_41619_()) {
            return new ArclightSpecialRecipe((Recipe<?>)this);
        }
        CraftItemStack result = CraftItemStack.asCraftMirror(this.f_43730_);
        CraftCampfireRecipe recipe = new CraftCampfireRecipe(CraftNamespacedKey.fromMinecraft(this.m_6423_()), (ItemStack)result, CraftRecipe.toBukkit(this.f_43729_), this.f_43731_, this.f_43732_);
        recipe.setGroup(this.f_43728_);
        recipe.setCategory(CraftRecipe.getCategory(this.m_245534_()));
        return recipe;
    }
}

