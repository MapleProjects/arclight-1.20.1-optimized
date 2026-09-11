/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.ComposterBlock$EmptyContainer
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mixin.core.world.SimpleContainerMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftBlockInventoryHolder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={ComposterBlock.EmptyContainer.class})
public abstract class ComposterBlock_EmptyContainerMixin
extends SimpleContainerMixin {
    public void arclight$constructor() {
        throw new RuntimeException();
    }

    public void arclight$constructor(LevelAccessor world, BlockPos blockPos) {
        this.arclight$constructor();
        this.setOwner(new CraftBlockInventoryHolder(world, blockPos, this));
    }
}

