/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.SmithingTrimRecipe
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.RecipeChoice;

public class CraftSmithingTrimRecipe
extends org.bukkit.inventory.SmithingTrimRecipe
implements CraftRecipe {
    public CraftSmithingTrimRecipe(NamespacedKey key, RecipeChoice template, RecipeChoice base, RecipeChoice addition) {
        super(key, template, base, addition);
    }

    public static CraftSmithingTrimRecipe fromBukkitRecipe(org.bukkit.inventory.SmithingTrimRecipe recipe) {
        if (recipe instanceof CraftSmithingTrimRecipe) {
            return (CraftSmithingTrimRecipe)recipe;
        }
        CraftSmithingTrimRecipe ret = new CraftSmithingTrimRecipe(recipe.getKey(), recipe.getTemplate(), recipe.getBase(), recipe.getAddition());
        return ret;
    }

    @Override
    public void addToCraftingManager() {
        MinecraftServer.getServer().m_129894_().addRecipe((Recipe)new SmithingTrimRecipe(CraftNamespacedKey.toMinecraft(this.getKey()), this.toNMS(this.getTemplate(), true), this.toNMS(this.getBase(), true), this.toNMS(this.getAddition(), true)));
    }
}

