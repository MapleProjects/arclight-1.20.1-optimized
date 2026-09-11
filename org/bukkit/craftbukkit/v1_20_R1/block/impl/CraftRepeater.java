/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.RepeaterBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRepeater
extends CraftBlockData
implements Repeater,
Directional,
Powerable {
    private static final IntegerProperty DELAY = CraftRepeater.getInteger(RepeaterBlock.class, "delay");
    private static final BooleanProperty LOCKED = CraftRepeater.getBoolean(RepeaterBlock.class, "locked");
    private static final EnumProperty<?> FACING = CraftRepeater.getEnum(RepeaterBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftRepeater.getBoolean(RepeaterBlock.class, "powered");

    public CraftRepeater() {
    }

    public CraftRepeater(BlockState state) {
        super(state);
    }

    @Override
    public int getDelay() {
        return (Integer)this.get(DELAY);
    }

    @Override
    public void setDelay(int delay) {
        this.set(DELAY, delay);
    }

    @Override
    public int getMinimumDelay() {
        return CraftRepeater.getMin(DELAY);
    }

    @Override
    public int getMaximumDelay() {
        return CraftRepeater.getMax(DELAY);
    }

    @Override
    public boolean isLocked() {
        return (Boolean)this.get(LOCKED);
    }

    @Override
    public void setLocked(boolean locked) {
        this.set(LOCKED, locked);
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

