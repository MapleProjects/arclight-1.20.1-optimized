/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeType
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class RecipeIterator
implements Iterator<org.bukkit.inventory.Recipe> {
    private final Iterator<Map.Entry<RecipeType<?>, Object2ObjectLinkedOpenHashMap<ResourceLocation, Recipe<?>>>> recipes;
    private Iterator<Recipe<?>> current;

    public RecipeIterator() {
        this.recipes = MinecraftServer.getServer().m_129894_().f_44007_.entrySet().iterator();
    }

    @Override
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

    @Override
    public org.bukkit.inventory.Recipe next() {
        if (this.current == null || !this.current.hasNext()) {
            this.current = this.recipes.next().getValue().values().iterator();
            return this.next();
        }
        return this.current.next().toBukkitRecipe();
    }

    @Override
    public void remove() {
        Preconditions.checkState((this.current != null ? 1 : 0) != 0, (Object)"next() not yet called");
        this.current.remove();
    }
}

