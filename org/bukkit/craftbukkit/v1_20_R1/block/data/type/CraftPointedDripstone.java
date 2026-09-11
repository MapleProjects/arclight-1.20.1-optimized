/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import java.util.Set;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftPointedDripstone
extends CraftBlockData
implements PointedDripstone {
    private static final EnumProperty<?> VERTICAL_DIRECTION = CraftPointedDripstone.getEnum("vertical_direction");
    private static final EnumProperty<?> THICKNESS = CraftPointedDripstone.getEnum("thickness");

    @Override
    public BlockFace getVerticalDirection() {
        return this.get(VERTICAL_DIRECTION, BlockFace.class);
    }

    @Override
    public void setVerticalDirection(BlockFace direction) {
        this.set(VERTICAL_DIRECTION, direction);
    }

    @Override
    public Set<BlockFace> getVerticalDirections() {
        return this.getValues(VERTICAL_DIRECTION, BlockFace.class);
    }

    @Override
    public PointedDripstone.Thickness getThickness() {
        return this.get(THICKNESS, PointedDripstone.Thickness.class);
    }

    @Override
    public void setThickness(PointedDripstone.Thickness thickness) {
        this.set(THICKNESS, thickness);
    }
}

