/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.MangrovePropaguleBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Hangable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.MangrovePropagule;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftMangrovePropagule
extends CraftBlockData
implements MangrovePropagule,
Ageable,
Hangable,
Sapling,
Waterlogged {
    private static final IntegerProperty AGE = CraftMangrovePropagule.getInteger(MangrovePropaguleBlock.class, "age");
    private static final BooleanProperty HANGING = CraftMangrovePropagule.getBoolean(MangrovePropaguleBlock.class, "hanging");
    private static final IntegerProperty STAGE = CraftMangrovePropagule.getInteger(MangrovePropaguleBlock.class, "stage");
    private static final BooleanProperty WATERLOGGED = CraftMangrovePropagule.getBoolean(MangrovePropaguleBlock.class, "waterlogged");

    public CraftMangrovePropagule() {
    }

    public CraftMangrovePropagule(BlockState state) {
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
        return CraftMangrovePropagule.getMax(AGE);
    }

    @Override
    public boolean isHanging() {
        return (Boolean)this.get(HANGING);
    }

    @Override
    public void setHanging(boolean hanging) {
        this.set(HANGING, hanging);
    }

    @Override
    public int getStage() {
        return (Integer)this.get(STAGE);
    }

    @Override
    public void setStage(int stage) {
        this.set(STAGE, stage);
    }

    @Override
    public int getMaximumStage() {
        return CraftMangrovePropagule.getMax(STAGE);
    }

    @Override
    public boolean isWaterlogged() {
        return (Boolean)this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}

