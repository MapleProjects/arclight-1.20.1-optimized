/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 */
package io.izzel.arclight.common.bridge.core.inventory;

import io.izzel.arclight.common.mod.util.WrappedContents;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface IInventoryBridge {
    public static final int MAX_STACK = 64;

    default public List<ItemStack> getContents() {
        return new WrappedContents((Container)this);
    }

    public void onOpen(CraftHumanEntity var1);

    public void onClose(CraftHumanEntity var1);

    public List<HumanEntity> getViewers();

    public InventoryHolder getOwner();

    public void setOwner(InventoryHolder var1);

    public void setMaxStackSize(int var1);

    public Location getLocation();

    default public Recipe<?> getCurrentRecipe() {
        return null;
    }

    default public void setCurrentRecipe(Recipe<?> recipe) {
    }

    default public Inventory getOwnerInventory() {
        InventoryHolder owner = this.getOwner();
        if (owner != null) {
            return owner.getInventory();
        }
        return new CraftInventory((Container)this);
    }
}

