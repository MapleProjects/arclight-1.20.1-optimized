/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Bed;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBed
extends CraftBlockData
implements Bed,
Directional {
    private static final EnumProperty<?> PART = CraftBed.getEnum(BedBlock.class, "part");
    private static final BooleanProperty OCCUPIED = CraftBed.getBoolean(BedBlock.class, "occupied");
    private static final EnumProperty<?> FACING = CraftBed.getEnum(BedBlock.class, "facing");

    public CraftBed() {
    }

    public CraftBed(BlockState state) {
        super(state);
    }

    @Override
    public Bed.Part getPart() {
        return this.get(PART, Bed.Part.class);
    }

    @Override
    public void setPart(Bed.Part part) {
        this.set(PART, part);
    }

    @Override
    public boolean isOccupied() {
        return (Boolean)this.get(OCCUPIED);
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

