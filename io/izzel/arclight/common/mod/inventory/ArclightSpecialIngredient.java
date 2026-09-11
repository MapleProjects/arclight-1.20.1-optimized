/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.inventory;

import java.util.Objects;
import net.minecraft.world.item.crafting.Ingredient;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

public class ArclightSpecialIngredient
implements RecipeChoice {
    private final Ingredient ingredient;

    public ArclightSpecialIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @Override
    @NotNull
    public ItemStack getItemStack() {
        net.minecraft.world.item.ItemStack[] items = this.ingredient.m_43908_();
        return items.length > 0 ? CraftItemStack.asCraftMirror(items[0]) : new ItemStack(Material.AIR, 0);
    }

    @Override
    @NotNull
    public RecipeChoice clone() {
        try {
            return (RecipeChoice)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError((Object)e);
        }
    }

    @Override
    public boolean test(@NotNull ItemStack itemStack) {
        return this.ingredient.test(CraftItemStack.asNMSCopy(itemStack));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ArclightSpecialIngredient that = (ArclightSpecialIngredient)o;
        return Objects.equals(this.ingredient, that.ingredient);
    }

    public int hashCode() {
        return Objects.hash(this.ingredient);
    }
}

