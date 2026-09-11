/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.StructureBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.StructureBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftStructure
extends CraftBlockData
implements org.bukkit.block.data.type.StructureBlock {
    private static final EnumProperty<?> MODE = CraftStructure.getEnum(StructureBlock.class, "mode");

    public CraftStructure() {
    }

    public CraftStructure(BlockState state) {
        super(state);
    }

    @Override
    public StructureBlock.Mode getMode() {
        return this.get(MODE, StructureBlock.Mode.class);
    }

    @Override
    public void setMode(StructureBlock.Mode mode) {
        this.set(MODE, mode);
    }
}

