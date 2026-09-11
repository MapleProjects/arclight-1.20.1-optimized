/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Door;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftDoor
extends CraftBlockData
implements Door {
    private static final EnumProperty<?> HINGE = CraftDoor.getEnum("hinge");

    @Override
    public Door.Hinge getHinge() {
        return this.get(HINGE, Door.Hinge.class);
    }

    @Override
    public void setHinge(Door.Hinge hinge) {
        this.set(HINGE, hinge);
    }
}

