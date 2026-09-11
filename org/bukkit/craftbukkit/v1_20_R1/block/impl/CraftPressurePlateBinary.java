/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.PressurePlateBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Powerable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPressurePlateBinary
extends CraftBlockData
implements Powerable {
    private static final BooleanProperty POWERED = CraftPressurePlateBinary.getBoolean(PressurePlateBlock.class, "powered");

    public CraftPressurePlateBinary() {
    }

    public CraftPressurePlateBinary(BlockState state) {
        super(state);
    }

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
    }
}

