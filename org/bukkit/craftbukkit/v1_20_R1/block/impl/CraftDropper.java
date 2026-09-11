/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.DropperBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftDropper
extends CraftBlockData
implements Dispenser,
Directional {
    private static final BooleanProperty TRIGGERED = CraftDropper.getBoolean(DropperBlock.class, "triggered");
    private static final EnumProperty<?> FACING = CraftDropper.getEnum(DropperBlock.class, "facing");

    public CraftDropper() {
    }

    public CraftDropper(BlockState state) {
        super(state);
    }

    @Override
    public boolean isTriggered() {
        return (Boolean)this.get(TRIGGERED);
    }

    @Override
    public void setTriggered(boolean triggered) {
        this.set(TRIGGERED, triggered);
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

