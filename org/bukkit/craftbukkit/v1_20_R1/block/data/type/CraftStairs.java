/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftStairs
extends CraftBlockData
implements Stairs {
    private static final EnumProperty<?> SHAPE = CraftStairs.getEnum("shape");

    @Override
    public Stairs.Shape getShape() {
        return this.get(SHAPE, Stairs.Shape.class);
    }

    @Override
    public void setShape(Stairs.Shape shape) {
        this.set(SHAPE, shape);
    }
}

