/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.item.crafting.Recipe
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftResultInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

public class CraftInventorySmithing
extends CraftResultInventory
implements SmithingInventory {
    private final Location location;

    public CraftInventorySmithing(Location location, Container inventory, ResultContainer resultInventory) {
        super(inventory, (Container)resultInventory);
        this.location = location;
    }

    public ResultContainer getResultInventory() {
        return (ResultContainer)super.getResultInventory();
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public ItemStack getResult() {
        return this.getItem(3);
    }

    @Override
    public void setResult(ItemStack item) {
        this.setItem(3, item);
    }

    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        Recipe recipe = this.getResultInventory().m_7928_();
        return recipe == null ? null : recipe.toBukkitRecipe();
    }
}

