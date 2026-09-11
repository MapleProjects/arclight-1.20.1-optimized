/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CakeBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Cake;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCake
extends CraftBlockData
implements Cake {
    private static final IntegerProperty BITES = CraftCake.getInteger(CakeBlock.class, "bites");

    public CraftCake() {
    }

    public CraftCake(BlockState state) {
        super(state);
    }

    @Override
    public int getBites() {
        return (Integer)this.get(BITES);
    }

    @Override
    public void setBites(int bites) {
        this.set(BITES, bites);
    }

    @Override
    public int getMaximumBites() {
        return CraftCake.getMax(BITES);
    }
}

