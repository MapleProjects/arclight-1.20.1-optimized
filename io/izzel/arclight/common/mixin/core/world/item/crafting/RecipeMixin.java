/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import io.izzel.arclight.common.mod.util.ArclightSpecialRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={Recipe.class})
public interface RecipeMixin
extends IRecipeBridge {
    default public org.bukkit.inventory.Recipe toBukkitRecipe() {
        return this.bridge$toBukkitRecipe();
    }

    @Override
    default public org.bukkit.inventory.Recipe bridge$toBukkitRecipe() {
        return new ArclightSpecialRecipe((Recipe)this);
    }
}

