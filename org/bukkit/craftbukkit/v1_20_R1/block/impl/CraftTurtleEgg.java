/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.TurtleEggBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Hatchable;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftTurtleEgg
extends CraftBlockData
implements TurtleEgg,
Hatchable {
    private static final IntegerProperty EGGS = CraftTurtleEgg.getInteger(TurtleEggBlock.class, "eggs");
    private static final IntegerProperty HATCH = CraftTurtleEgg.getInteger(TurtleEggBlock.class, "hatch");

    public CraftTurtleEgg() {
    }

    public CraftTurtleEgg(BlockState state) {
        super(state);
    }

    @Override
    public int getEggs() {
        return (Integer)this.get(EGGS);
    }

    @Override
    public void setEggs(int eggs) {
        this.set(EGGS, eggs);
    }

    @Override
    public int getMinimumEggs() {
        return CraftTurtleEgg.getMin(EGGS);
    }

    @Override
    public int getMaximumEggs() {
        return CraftTurtleEgg.getMax(EGGS);
    }

    @Override
    public int getHatch() {
        return (Integer)this.get(HATCH);
    }

    @Override
    public void setHatch(int hatch) {
        this.set(HATCH, hatch);
    }

    @Override
    public int getMaximumHatch() {
        return CraftTurtleEgg.getMax(HATCH);
    }
}

