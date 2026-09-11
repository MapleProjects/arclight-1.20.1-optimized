/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.piston.MovingPistonBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPistonMoving
extends CraftBlockData
implements TechnicalPiston,
Directional {
    private static final EnumProperty<?> TYPE = CraftPistonMoving.getEnum(MovingPistonBlock.class, "type");
    private static final EnumProperty<?> FACING = CraftPistonMoving.getEnum(MovingPistonBlock.class, "facing");

    public CraftPistonMoving() {
    }

    public CraftPistonMoving(BlockState state) {
        super(state);
    }

    @Override
    public TechnicalPiston.Type getType() {
        return this.get(TYPE, TechnicalPiston.Type.class);
    }

    @Override
    public void setType(TechnicalPiston.Type type) {
        this.set(TYPE, type);
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

