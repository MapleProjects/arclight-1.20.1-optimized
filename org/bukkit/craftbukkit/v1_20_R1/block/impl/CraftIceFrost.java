/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.FrostedIceBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftIceFrost
extends CraftBlockData
implements Ageable {
    private static final IntegerProperty AGE = CraftIceFrost.getInteger(FrostedIceBlock.class, "age");

    public CraftIceFrost() {
    }

    public CraftIceFrost(BlockState state) {
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
        return CraftIceFrost.getMax(AGE);
    }
}

