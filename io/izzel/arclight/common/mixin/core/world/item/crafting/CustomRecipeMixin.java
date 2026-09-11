/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.crafting.CustomRecipe
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IRecipeBridge;
import net.minecraft.world.item.crafting.CustomRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftComplexRecipe;
import org.bukkit.inventory.Recipe;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={CustomRecipe.class})
public class CustomRecipeMixin
implements IRecipeBridge {
    @Override
    public Recipe bridge$toBukkitRecipe() {
        return new CraftComplexRecipe((CustomRecipe)this);
    }
}

