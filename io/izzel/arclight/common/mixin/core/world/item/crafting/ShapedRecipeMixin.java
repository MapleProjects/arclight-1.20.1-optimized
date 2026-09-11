/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.CraftingBookCategory
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.ShapedRecipe
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
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftShapedRecipe;
import org.bukkit.inventory.RecipeChoice;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ShapedRecipe.class})
public abstract class ShapedRecipeMixin
implements IRecipeBridge {
    @Shadow
    @Final
    ItemStack f_44149_;
    @Shadow
    @Final
    String f_44151_;
    @Shadow
    @Final
    NonNullList<Ingredient> f_44148_;

    @Shadow
    public abstract int m_44221_();

    @Shadow
    public abstract int m_44220_();

    @Shadow
    public abstract CraftingBookCategory m_245232_();

    @Override
    public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        if (this.m_44220_() < 1 || this.m_44220_() > 3 || this.m_44221_() < 1 || this.m_44221_() > 3 || this.f_44149_.m_41619_()) {
            return new ArclightSpecialRecipe((Recipe)this);
        }
        CraftItemStack result = CraftItemStack.asCraftMirror(this.f_44149_);
        CraftShapedRecipe recipe = new CraftShapedRecipe(result, (ShapedRecipe)this);
        recipe.setGroup(this.f_44151_);
        recipe.setCategory(CraftRecipe.getCategory(this.m_245232_()));
        block0 : switch (this.m_44221_()) {
            case 1: {
                switch (this.m_44220_()) {
                    case 1: {
                        recipe.shape("a");
                        break;
                    }
                    case 2: {
                        recipe.shape("ab");
                        break;
                    }
                    case 3: {
                        recipe.shape("abc");
                    }
                }
                break;
            }
            case 2: {
                switch (this.m_44220_()) {
                    case 1: {
                        recipe.shape("a", "b");
                        break;
                    }
                    case 2: {
                        recipe.shape("ab", "cd");
                        break;
                    }
                    case 3: {
                        recipe.shape("abc", "def");
                    }
                }
                break;
            }
            case 3: {
                switch (this.m_44220_()) {
                    case 1: {
                        recipe.shape("a", "b", "c");
                        break block0;
                    }
                    case 2: {
                        recipe.shape("ab", "cd", "ef");
                        break block0;
                    }
                    case 3: {
                        recipe.shape("abc", "def", "ghi");
                    }
                }
            }
        }
        char c = 'a';
        for (Ingredient list : this.f_44148_) {
            RecipeChoice choice = CraftRecipe.toBukkit(list);
            if (choice != null) {
                recipe.setIngredient(c, choice);
            }
            c = (char)(c + 1);
        }
        return recipe;
    }
}

