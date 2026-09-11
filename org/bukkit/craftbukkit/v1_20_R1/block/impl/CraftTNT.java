/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.TNT;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftTNT
extends CraftBlockData
implements TNT {
    private static final BooleanProperty UNSTABLE = CraftTNT.getBoolean(TntBlock.class, "unstable");

    public CraftTNT() {
    }

    public CraftTNT(BlockState state) {
        super(state);
    }

    @Override
    public boolean isUnstable() {
        return (Boolean)this.get(UNSTABLE);
    }

    @Override
    public void setUnstable(boolean unstable) {
        this.set(UNSTABLE, unstable);
    }
}

