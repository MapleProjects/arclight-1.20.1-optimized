/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.HopperBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Hopper;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftHopper
extends CraftBlockData
implements Hopper,
Directional {
    private static final BooleanProperty ENABLED = CraftHopper.getBoolean(HopperBlock.class, "enabled");
    private static final EnumProperty<?> FACING = CraftHopper.getEnum(HopperBlock.class, "facing");

    public CraftHopper() {
    }

    public CraftHopper(BlockState state) {
        super(state);
    }

    @Override
    public boolean isEnabled() {
        return (Boolean)this.get(ENABLED);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.set(ENABLED, enabled);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }
}

