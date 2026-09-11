/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CeilingHangingSignBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.Rotatable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.HangingSign;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCeilingHangingSign
extends CraftBlockData
implements HangingSign,
Attachable,
Rotatable,
Waterlogged {
    private static final BooleanProperty ATTACHED = CraftCeilingHangingSign.getBoolean(CeilingHangingSignBlock.class, "attached");
    private static final IntegerProperty ROTATION = CraftCeilingHangingSign.getInteger(CeilingHangingSignBlock.class, "rotation");
    private static final BooleanProperty WATERLOGGED = CraftCeilingHangingSign.getBoolean(CeilingHangingSignBlock.class, "waterlogged");

    public CraftCeilingHangingSign() {
    }

    public CraftCeilingHangingSign(BlockState state) {
        super(state);
    }

    @Override
    public boolean isAttached() {
        return (Boolean)this.get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        this.set(ATTACHED, attached);
    }

    @Override
    public BlockFace getRotation() {
        int data = (Integer)this.get(ROTATION);
        switch (data) {
            case 0: {
                return BlockFace.SOUTH;
            }
            case 1: {
                return BlockFace.SOUTH_SOUTH_WEST;
            }
            case 2: {
                return BlockFace.SOUTH_WEST;
            }
            case 3: {
                return BlockFace.WEST_SOUTH_WEST;
            }
            case 4: {
                return BlockFace.WEST;
            }
            case 5: {
                return BlockFace.WEST_NORTH_WEST;
            }
            case 6: {
                return BlockFace.NORTH_WEST;
            }
            case 7: {
                return BlockFace.NORTH_NORTH_WEST;
            }
            case 8: {
                return BlockFace.NORTH;
            }
            case 9: {
                return BlockFace.NORTH_NORTH_EAST;
            }
            case 10: {
                return BlockFace.NORTH_EAST;
            }
            case 11: {
                return BlockFace.EAST_NORTH_EAST;
            }
            case 12: {
                return BlockFace.EAST;
            }
            case 13: {
                return BlockFace.EAST_SOUTH_EAST;
            }
            case 14: {
                return BlockFace.SOUTH_EAST;
            }
            case 15: {
                return BlockFace.SOUTH_SOUTH_EAST;
            }
        }
        throw new IllegalArgumentException("Unknown rotation " + data);
    }

    @Override
    public void setRotation(BlockFace rotation) {
        this.set(ROTATION, switch (rotation) {
            case BlockFace.SOUTH -> 0;
            case BlockFace.SOUTH_SOUTH_WEST -> 1;
            case BlockFace.SOUTH_WEST -> 2;
            case BlockFace.WEST_SOUTH_WEST -> 3;
            case BlockFace.WEST -> 4;
            case BlockFace.WEST_NORTH_WEST -> 5;
            case BlockFace.NORTH_WEST -> 6;
            case BlockFace.NORTH_NORTH_WEST -> 7;
            case BlockFace.NORTH -> 8;
            case BlockFace.NORTH_NORTH_EAST -> 9;
            case BlockFace.NORTH_EAST -> 10;
            case BlockFace.EAST_NORTH_EAST -> 11;
            case BlockFace.EAST -> 12;
            case BlockFace.EAST_SOUTH_EAST -> 13;
            case BlockFace.SOUTH_EAST -> 14;
            case BlockFace.SOUTH_SOUTH_EAST -> 15;
            default -> throw new IllegalArgumentException("Illegal rotation " + (Object)((Object)rotation));
        });
    }

    @Override
    public boolean isWaterlogged() {
        return (Boolean)this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}

