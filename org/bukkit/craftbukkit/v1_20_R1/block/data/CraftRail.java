/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import java.util.Set;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.Rail;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftRail
extends CraftBlockData
implements Rail {
    private static final EnumProperty<?> SHAPE = CraftRail.getEnum("shape");

    @Override
    public Rail.Shape getShape() {
        return this.get(SHAPE, Rail.Shape.class);
    }

    @Override
    public void setShape(Rail.Shape shape) {
        this.set(SHAPE, shape);
    }

    @Override
    public Set<Rail.Shape> getShapes() {
        return this.getValues(SHAPE, Rail.Shape.class);
    }
}

