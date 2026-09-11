/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeType
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.bukkit.craftbukkit.v1_20_R1.inventory.RecipeIterator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RecipeIterator.class}, remap=false)
public class RecipeIteratorMixin {
    @Shadow
    private Iterator<Recipe<?>> current;
    @Shadow
    @Final
    private Iterator<Map.Entry<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>> recipes;

    @Overwrite
    public boolean hasNext() {
        if (this.current != null && this.current.hasNext()) {
            return true;
        }
        if (this.recipes.hasNext()) {
            this.current = this.recipes.next().getValue().values().iterator();
            return this.hasNext();
        }
        return false;
    }

    @Overwrite
    public org.bukkit.inventory.Recipe next() {
        if (this.current == null || !this.current.hasNext()) {
            this.current = this.recipes.next().getValue().values().iterator();
            return this.next();
        }
        Recipe<?> recipe = this.current.next();
        try {
            return ((IRecipeBridge)recipe).bridge$toBukkitRecipe();
        }
        catch (Throwable e) {
            throw new RuntimeException("Error converting recipe " + String.valueOf(recipe.m_6423_()), e);
        }
    }
}

