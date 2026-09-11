/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftJigsaw
extends CraftBlockData
implements Jigsaw {
    private static final EnumProperty<?> ORIENTATION = CraftJigsaw.getEnum("orientation");

    @Override
    public Jigsaw.Orientation getOrientation() {
        return this.get(ORIENTATION, Jigsaw.Orientation.class);
    }

    @Override
    public void setOrientation(Jigsaw.Orientation orientation) {
        this.set(ORIENTATION, orientation);
    }
}

