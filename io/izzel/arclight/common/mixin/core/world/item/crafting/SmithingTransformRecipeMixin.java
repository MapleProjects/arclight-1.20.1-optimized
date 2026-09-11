/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.SmithingTransformRecipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mod.util.ArclightSpecialRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftSmithingTransformRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={SmithingTransformRecipe.class})
public class SmithingTransformRecipeMixin
implements IRecipeBridge {
    @Shadow
    @Final
    ItemStack f_266098_;
    @Shadow
    @Final
    private ResourceLocation f_265924_;
    @Shadow
    @Final
    Ingredient f_265949_;
    @Shadow
    @Final
    Ingredient f_265888_;
    @Shadow
    @Final
    Ingredient f_265907_;

    @Override
    public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        if (this.f_266098_.m_41619_()) {
            return new ArclightSpecialRecipe((Recipe<?>)((SmithingTransformRecipe)this));
        }
        CraftItemStack result = CraftItemStack.asCraftMirror(this.f_266098_);
        return new CraftSmithingTransformRecipe(CraftNamespacedKey.fromMinecraft(this.f_265924_), result, CraftRecipe.toBukkit(this.f_265949_), CraftRecipe.toBukkit(this.f_265888_), CraftRecipe.toBukkit(this.f_265907_));
    }
}

