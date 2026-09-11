/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBubbleColumn
extends CraftBlockData
implements BubbleColumn {
    private static final BooleanProperty DRAG = CraftBubbleColumn.getBoolean("drag");

    @Override
    public boolean isDrag() {
        return (Boolean)this.get(DRAG);
    }

    @Override
    public void setDrag(boolean drag) {
        this.set(DRAG, drag);
    }
}

