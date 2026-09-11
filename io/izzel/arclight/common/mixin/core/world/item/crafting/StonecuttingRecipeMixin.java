/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.SingleItemRecipe
 *  net.minecraft.world.item.crafting.StonecutterRecipe
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mod.util.ArclightSpecialRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftStonecuttingRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={StonecutterRecipe.class})
public abstract class StonecuttingRecipeMixin
extends SingleItemRecipe
implements IRecipeBridge {
    public StonecuttingRecipeMixin(RecipeType<?> type, RecipeSerializer<?> serializer, ResourceLocation id, String group, Ingredient ingredient, net.minecraft.world.item.ItemStack result) {
        super(type, serializer, id, group, ingredient, result);
    }

    @Override
    public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        if (this.f_44410_.m_41619_()) {
            return new ArclightSpecialRecipe((Recipe<?>)this);
        }
        CraftItemStack result = CraftItemStack.asCraftMirror(this.f_44410_);
        CraftStonecuttingRecipe recipe = new CraftStonecuttingRecipe(CraftNamespacedKey.fromMinecraft(this.m_6423_()), (ItemStack)result, CraftRecipe.toBukkit(this.f_44409_));
        recipe.setGroup(this.f_44412_);
        return recipe;
    }
}

