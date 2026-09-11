/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BubbleColumnBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBubbleColumn
extends CraftBlockData
implements BubbleColumn {
    private static final BooleanProperty DRAG = CraftBubbleColumn.getBoolean(BubbleColumnBlock.class, "drag");

    public CraftBubbleColumn() {
    }

    public CraftBubbleColumn(BlockState state) {
        super(state);
    }

    @Override
    public boolean isDrag() {
        return (Boolean)this.get(DRAG);
    }

    @Override
    public void setDrag(boolean drag) {
        this.set(DRAG, drag);
    }
}

