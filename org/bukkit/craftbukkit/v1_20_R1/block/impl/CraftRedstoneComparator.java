/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.ComparatorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRedstoneComparator
extends CraftBlockData
implements Comparator,
Directional,
Powerable {
    private static final EnumProperty<?> MODE = CraftRedstoneComparator.getEnum(ComparatorBlock.class, "mode");
    private static final EnumProperty<?> FACING = CraftRedstoneComparator.getEnum(ComparatorBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftRedstoneComparator.getBoolean(ComparatorBlock.class, "powered");

    public CraftRedstoneComparator() {
    }

    public CraftRedstoneComparator(BlockState state) {
        super(state);
    }

    @Override
    public Comparator.Mode getMode() {
        return this.get(MODE, Comparator.Mode.class);
    }

    @Override
    public void setMode(Comparator.Mode mode) {
        this.set(MODE, mode);
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

