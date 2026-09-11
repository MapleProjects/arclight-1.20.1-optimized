/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.PinkPetalsBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPinkPetals
extends CraftBlockData
implements PinkPetals,
Directional {
    private static final IntegerProperty FLOWER_AMOUNT = CraftPinkPetals.getInteger(PinkPetalsBlock.class, "flower_amount");
    private static final EnumProperty<?> FACING = CraftPinkPetals.getEnum(PinkPetalsBlock.class, "facing");

    public CraftPinkPetals() {
    }

    public CraftPinkPetals(BlockState state) {
        super(state);
    }

    @Override
    public int getFlowerAmount() {
        return (Integer)this.get(FLOWER_AMOUNT);
    }

    @Override
    public void setFlowerAmount(int flower_amount) {
        this.set(FLOWER_AMOUNT, flower_amount);
    }

    @Override
    public int getMaximumFlowerAmount() {
        return CraftPinkPetals.getMax(FLOWER_AMOUNT);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }
}

