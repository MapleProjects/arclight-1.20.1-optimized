/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.piston.PistonBaseBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPiston
extends CraftBlockData
implements Piston,
Directional {
    private static final BooleanProperty EXTENDED = CraftPiston.getBoolean(PistonBaseBlock.class, "extended");
    private static final EnumProperty<?> FACING = CraftPiston.getEnum(PistonBaseBlock.class, "facing");

    public CraftPiston() {
    }

    public CraftPiston(BlockState state) {
        super(state);
    }

    @Override
    public boolean isExtended() {
        return (Boolean)this.get(EXTENDED);
    }

    @Override
    public void setExtended(boolean extended) {
        this.set(EXTENDED, extended);
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

