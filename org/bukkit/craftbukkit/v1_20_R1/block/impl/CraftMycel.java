/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.MyceliumBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Snowable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftMycel
extends CraftBlockData
implements Snowable {
    private static final BooleanProperty SNOWY = CraftMycel.getBoolean(MyceliumBlock.class, "snowy");

    public CraftMycel() {
    }

    public CraftMycel(BlockState state) {
        super(state);
    }

    @Override
    public boolean isSnowy() {
        return (Boolean)this.get(SNOWY);
    }

    @Override
    public void setSnowy(boolean snowy) {
        this.set(SNOWY, snowy);
    }
}

