/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.piston.PistonHeadBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPistonExtension
extends CraftBlockData
implements PistonHead,
TechnicalPiston,
Directional {
    private static final BooleanProperty SHORT = CraftPistonExtension.getBoolean(PistonHeadBlock.class, "short");
    private static final EnumProperty<?> TYPE = CraftPistonExtension.getEnum(PistonHeadBlock.class, "type");
    private static final EnumProperty<?> FACING = CraftPistonExtension.getEnum(PistonHeadBlock.class, "facing");

    public CraftPistonExtension() {
    }

    public CraftPistonExtension(BlockState state) {
        super(state);
    }

    @Override
    public boolean isShort() {
        return (Boolean)this.get(SHORT);
    }

    @Override
    public void setShort(boolean _short) {
        this.set(SHORT, _short);
    }

    @Override
    public TechnicalPiston.Type getType() {
        return this.get(TYPE, TechnicalPiston.Type.class);
    }

    @Override
    public void setType(TechnicalPiston.Type type) {
        this.set(TYPE, type);
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
}

