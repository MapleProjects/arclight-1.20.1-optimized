/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.RespawnAnchorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRespawnAnchor
extends CraftBlockData
implements RespawnAnchor {
    private static final IntegerProperty CHARGES = CraftRespawnAnchor.getInteger(RespawnAnchorBlock.class, "charges");

    public CraftRespawnAnchor() {
    }

    public CraftRespawnAnchor(BlockState state) {
        super(state);
    }

    @Override
    public int getCharges() {
        return (Integer)this.get(CHARGES);
    }

    @Override
    public void setCharges(int charges) {
        this.set(CHARGES, charges);
    }

    @Override
    public int getMaximumCharges() {
        return CraftRespawnAnchor.getMax(CHARGES);
    }
}

