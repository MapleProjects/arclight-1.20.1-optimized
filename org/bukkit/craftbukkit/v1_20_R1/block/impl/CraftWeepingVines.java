/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.WeepingVinesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.WeepingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftWeepingVines
extends CraftBlockData
implements Ageable {
    private static final IntegerProperty AGE = CraftWeepingVines.getInteger(WeepingVinesBlock.class, "age");

    public CraftWeepingVines() {
    }

    public CraftWeepingVines(BlockState state) {
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
        return CraftWeepingVines.getMax(AGE);
    }
}

