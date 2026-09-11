/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Wall;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftWall
extends CraftBlockData
implements Wall {
    private static final BooleanProperty UP = CraftWall.getBoolean("up");
    private static final EnumProperty<?>[] HEIGHTS = new EnumProperty[]{CraftWall.getEnum("north"), CraftWall.getEnum("east"), CraftWall.getEnum("south"), CraftWall.getEnum("west")};

    @Override
    public boolean isUp() {
        return (Boolean)this.get(UP);
    }

    @Override
    public void setUp(boolean up) {
        this.set(UP, up);
    }

    @Override
    public Wall.Height getHeight(BlockFace face) {
        return this.get(HEIGHTS[face.ordinal()], Wall.Height.class);
    }

    @Override
    public void setHeight(BlockFace face, Wall.Height height) {
        this.set(HEIGHTS[face.ordinal()], height);
    }
}

