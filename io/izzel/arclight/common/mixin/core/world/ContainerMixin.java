/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.mod.inventory.SideViewingTracker;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={Container.class})
public interface ContainerMixin
extends IInventoryBridge {
    @Override
    default public void onOpen(CraftHumanEntity who) {
        SideViewingTracker.onOpen((Container)this, who);
    }

    @Override
    default public void onClose(CraftHumanEntity who) {
        SideViewingTracker.onClose((Container)this, who);
    }

    @Override
    default public List<HumanEntity> getViewers() {
        return SideViewingTracker.getViewers((Container)this);
    }

    @Override
    default public InventoryHolder getOwner() {
        return null;
    }

    @Override
    default public void setMaxStackSize(int size) {
    }

    @Override
    default public Location getLocation() {
        return null;
    }

    @Override
    default public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    default public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

