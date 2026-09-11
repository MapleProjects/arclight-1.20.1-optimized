/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.world.level.block.RedStoneWireBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRedstoneWire
extends CraftBlockData
implements RedstoneWire,
AnaloguePowerable {
    private static final EnumProperty<?> NORTH = CraftRedstoneWire.getEnum(RedStoneWireBlock.class, "north");
    private static final EnumProperty<?> EAST = CraftRedstoneWire.getEnum(RedStoneWireBlock.class, "east");
    private static final EnumProperty<?> SOUTH = CraftRedstoneWire.getEnum(RedStoneWireBlock.class, "south");
    private static final EnumProperty<?> WEST = CraftRedstoneWire.getEnum(RedStoneWireBlock.class, "west");
    private static final IntegerProperty POWER = CraftRedstoneWire.getInteger(RedStoneWireBlock.class, "power");

    public CraftRedstoneWire() {
    }

    public CraftRedstoneWire(BlockState state) {
        super(state);
    }

    @Override
    public RedstoneWire.Connection getFace(BlockFace face) {
        switch (face) {
            case NORTH: {
                return this.get(NORTH, RedstoneWire.Connection.class);
            }
            case EAST: {
                return this.get(EAST, RedstoneWire.Connection.class);
            }
            case SOUTH: {
                return this.get(SOUTH, RedstoneWire.Connection.class);
            }
            case WEST: {
                return this.get(WEST, RedstoneWire.Connection.class);
            }
        }
        throw new IllegalArgumentException("Cannot have face " + (Object)((Object)face));
    }

    @Override
    public void setFace(BlockFace face, RedstoneWire.Connection connection) {
        switch (face) {
            case NORTH: {
                this.set(NORTH, connection);
                break;
            }
            case EAST: {
                this.set(EAST, connection);
                break;
            }
            case SOUTH: {
                this.set(SOUTH, connection);
                break;
            }
            case WEST: {
                this.set(WEST, connection);
                break;
            }
            default: {
                throw new IllegalArgumentException("Cannot have face " + (Object)((Object)face));
            }
        }
    }

    @Override
    public Set<BlockFace> getAllowedFaces() {
        return ImmutableSet.of((Object)((Object)BlockFace.NORTH), (Object)((Object)BlockFace.EAST), (Object)((Object)BlockFace.SOUTH), (Object)((Object)BlockFace.WEST));
    }

    @Override
    public int getPower() {
        return (Integer)this.get(POWER);
    }

    @Override
    public void setPower(int power) {
        this.set(POWER, power);
    }

    @Override
    public int getMaximumPower() {
        return CraftRedstoneWire.getMax(POWER);
    }
}

