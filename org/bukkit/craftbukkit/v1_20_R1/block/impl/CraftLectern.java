/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLectern
extends CraftBlockData
implements Lectern,
Directional,
Powerable {
    private static final BooleanProperty HAS_BOOK = CraftLectern.getBoolean(LecternBlock.class, "has_book");
    private static final EnumProperty<?> FACING = CraftLectern.getEnum(LecternBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftLectern.getBoolean(LecternBlock.class, "powered");

    public CraftLectern() {
    }

    public CraftLectern(BlockState state) {
        super(state);
    }

    @Override
    public boolean hasBook() {
        return (Boolean)this.get(HAS_BOOK);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
    }
}

