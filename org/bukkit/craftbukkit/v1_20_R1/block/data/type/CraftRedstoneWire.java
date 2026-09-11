/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftRedstoneWire
extends CraftBlockData
implements RedstoneWire {
    private static final EnumProperty<?> NORTH = CraftRedstoneWire.getEnum("north");
    private static final EnumProperty<?> EAST = CraftRedstoneWire.getEnum("east");
    private static final EnumProperty<?> SOUTH = CraftRedstoneWire.getEnum("south");
    private static final EnumProperty<?> WEST = CraftRedstoneWire.getEnum("west");

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
}

