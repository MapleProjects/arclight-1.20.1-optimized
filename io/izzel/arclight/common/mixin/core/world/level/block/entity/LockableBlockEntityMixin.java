/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BaseContainerBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={BaseContainerBlockEntity.class})
public abstract class LockableBlockEntityMixin
extends BlockEntityMixin
implements IInventoryBridge,
Container {
    @Override
    public Location getLocation() {
        return CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_).getLocation();
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

