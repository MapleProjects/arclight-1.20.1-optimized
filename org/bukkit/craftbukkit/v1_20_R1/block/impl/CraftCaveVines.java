/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CaveVinesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.CaveVines;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCaveVines
extends CraftBlockData
implements CaveVines,
Ageable,
CaveVinesPlant {
    private static final IntegerProperty AGE = CraftCaveVines.getInteger(CaveVinesBlock.class, "age");
    private static final BooleanProperty BERRIES = CraftCaveVines.getBoolean(CaveVinesBlock.class, "berries");

    public CraftCaveVines() {
    }

    public CraftCaveVines(BlockState state) {
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
        return CraftCaveVines.getMax(AGE);
    }

    @Override
    public boolean isBerries() {
        return (Boolean)this.get(BERRIES);
    }

    @Override
    public void setBerries(boolean berries) {
        this.set(BERRIES, berries);
    }
}

