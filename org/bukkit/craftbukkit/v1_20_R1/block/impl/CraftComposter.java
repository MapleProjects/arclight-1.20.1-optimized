/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.ComposterBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Levelled;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftComposter
extends CraftBlockData
implements Levelled {
    private static final IntegerProperty LEVEL = CraftComposter.getInteger(ComposterBlock.class, "level");

    public CraftComposter() {
    }

    public CraftComposter(BlockState state) {
        super(state);
    }

    @Override
    public int getLevel() {
        return (Integer)this.get(LEVEL);
    }

    @Override
    public void setLevel(int level) {
        this.set(LEVEL, level);
    }

    @Override
    public int getMaximumLevel() {
        return CraftComposter.getMax(LEVEL);
    }
}

