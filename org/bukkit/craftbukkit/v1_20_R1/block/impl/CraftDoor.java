/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Door;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftDoor
extends CraftBlockData
implements Door,
Bisected,
Directional,
Openable,
Powerable {
    private static final EnumProperty<?> HINGE = CraftDoor.getEnum(DoorBlock.class, "hinge");
    private static final EnumProperty<?> HALF = CraftDoor.getEnum(DoorBlock.class, "half");
    private static final EnumProperty<?> FACING = CraftDoor.getEnum(DoorBlock.class, "facing");
    private static final BooleanProperty OPEN = CraftDoor.getBoolean(DoorBlock.class, "open");
    private static final BooleanProperty POWERED = CraftDoor.getBoolean(DoorBlock.class, "powered");

    public CraftDoor() {
    }

    public CraftDoor(BlockState state) {
        super(state);
    }

    @Override
    public Door.Hinge getHinge() {
        return this.get(HINGE, Door.Hinge.class);
    }

    @Override
    public void setHinge(Door.Hinge hinge) {
        this.set(HINGE, hinge);
    }

    @Override
    public Bisected.Half getHalf() {
        return this.get(HALF, Bisected.Half.class);
    }

    @Override
    public void setHalf(Bisected.Half half) {
        this.set(HALF, half);
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

    @Override
    public boolean isOpen() {
        return (Boolean)this.get(OPEN);
    }

    @Override
    public void setOpen(boolean open) {
        this.set(OPEN, open);
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

