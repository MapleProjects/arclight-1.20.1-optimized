/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BambooStalkBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBamboo
extends CraftBlockData
implements Bamboo,
Ageable,
Sapling {
    private static final EnumProperty<?> LEAVES = CraftBamboo.getEnum(BambooStalkBlock.class, "leaves");
    private static final IntegerProperty AGE = CraftBamboo.getInteger(BambooStalkBlock.class, "age");
    private static final IntegerProperty STAGE = CraftBamboo.getInteger(BambooStalkBlock.class, "stage");

    public CraftBamboo() {
    }

    public CraftBamboo(BlockState state) {
        super(state);
    }

    @Override
    public Bamboo.Leaves getLeaves() {
        return this.get(LEAVES, Bamboo.Leaves.class);
    }

    @Override
    public void setLeaves(Bamboo.Leaves leaves) {
        this.set(LEAVES, leaves);
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
        return CraftBamboo.getMax(AGE);
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
        return CraftBamboo.getMax(STAGE);
    }
}

