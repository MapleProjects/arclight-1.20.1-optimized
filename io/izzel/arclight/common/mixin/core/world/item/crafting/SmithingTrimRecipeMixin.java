/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.SmithingTrimRecipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftSmithingTrimRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.Recipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={SmithingTrimRecipe.class})
public class SmithingTrimRecipeMixin
implements IRecipeBridge {
    @Shadow
    @Final
    private ResourceLocation f_265885_;
    @Shadow
    @Final
    Ingredient f_265958_;
    @Shadow
    @Final
    Ingredient f_266040_;
    @Shadow
    @Final
    Ingredient f_266053_;

    @Override
    public Recipe bridge$toBukkitRecipe() {
        return new CraftSmithingTrimRecipe(CraftNamespacedKey.fromMinecraft(this.f_265885_), CraftRecipe.toBukkit(this.f_265958_), CraftRecipe.toBukkit(this.f_266040_), CraftRecipe.toBukkit(this.f_266053_));
    }
}

