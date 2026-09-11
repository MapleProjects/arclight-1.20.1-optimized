/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.StructureBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftStructureBlock
extends CraftBlockData
implements StructureBlock {
    private static final EnumProperty<?> MODE = CraftStructureBlock.getEnum("mode");

    @Override
    public StructureBlock.Mode getMode() {
        return this.get(MODE, StructureBlock.Mode.class);
    }

    @Override
    public void setMode(StructureBlock.Mode mode) {
        this.set(MODE, mode);
    }
}

