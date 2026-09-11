/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.PitcherCropBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.PitcherCrop;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPitcherCrop
extends CraftBlockData
implements PitcherCrop,
Ageable,
Bisected {
    private static final IntegerProperty AGE = CraftPitcherCrop.getInteger(PitcherCropBlock.class, "age");
    private static final EnumProperty<?> HALF = CraftPitcherCrop.getEnum(PitcherCropBlock.class, "half");

    public CraftPitcherCrop() {
    }

    public CraftPitcherCrop(BlockState state) {
        super(state);
    }

    @Override
    public int getAge() {
        return (Integer)this.get(AGE);
    }

    @Override
    public void setAge(int age) {
        this.set(AGE, age);
    }

    @Override
    public int getMaximumAge() {
        return CraftPitcherCrop.getMax(AGE);
    }

    @Override
    public Bisected.Half getHalf() {
        return this.get(HALF, Bisected.Half.class);
    }

    @Override
    public void setHalf(Bisected.Half half) {
        this.set(HALF, half);
    }
}

