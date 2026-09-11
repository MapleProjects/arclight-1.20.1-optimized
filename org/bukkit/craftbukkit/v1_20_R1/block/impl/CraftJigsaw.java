/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.JigsawBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftJigsaw
extends CraftBlockData
implements Jigsaw {
    private static final EnumProperty<?> ORIENTATION = CraftJigsaw.getEnum(JigsawBlock.class, "orientation");

    public CraftJigsaw() {
    }

    public CraftJigsaw(BlockState state) {
        super(state);
    }

    @Override
    public Jigsaw.Orientation getOrientation() {
        return this.get(ORIENTATION, Jigsaw.Orientation.class);
    }

    @Override
    public void setOrientation(Jigsaw.Orientation orientation) {
        this.set(ORIENTATION, orientation);
    }
}

