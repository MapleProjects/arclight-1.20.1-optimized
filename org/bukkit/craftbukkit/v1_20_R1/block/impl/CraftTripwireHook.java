/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.TripWireHookBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.TripWireHookBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.TripwireHook;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftTripwireHook
extends CraftBlockData
implements TripwireHook,
Attachable,
Directional,
Powerable {
    private static final BooleanProperty ATTACHED = CraftTripwireHook.getBoolean(TripWireHookBlock.class, "attached");
    private static final EnumProperty<?> FACING = CraftTripwireHook.getEnum(TripWireHookBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftTripwireHook.getBoolean(TripWireHookBlock.class, "powered");

    public CraftTripwireHook() {
    }

    public CraftTripwireHook(BlockState state) {
        super(state);
    }

    @Override
    public boolean isAttached() {
        return (Boolean)this.get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        this.set(ATTACHED, attached);
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

