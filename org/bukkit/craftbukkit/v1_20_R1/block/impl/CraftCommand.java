/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CommandBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCommand
extends CraftBlockData
implements CommandBlock,
Directional {
    private static final BooleanProperty CONDITIONAL = CraftCommand.getBoolean(net.minecraft.world.level.block.CommandBlock.class, "conditional");
    private static final EnumProperty<?> FACING = CraftCommand.getEnum(net.minecraft.world.level.block.CommandBlock.class, "facing");

    public CraftCommand() {
    }

    public CraftCommand(BlockState state) {
        super(state);
    }

    @Override
    public boolean isConditional() {
        return (Boolean)this.get(CONDITIONAL);
    }

    @Override
    public void setConditional(boolean conditional) {
        this.set(CONDITIONAL, conditional);
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

