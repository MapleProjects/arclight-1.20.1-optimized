/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BellBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Bell;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBell
extends CraftBlockData
implements Bell,
Directional,
Powerable {
    private static final EnumProperty<?> ATTACHMENT = CraftBell.getEnum(BellBlock.class, "attachment");
    private static final EnumProperty<?> FACING = CraftBell.getEnum(BellBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftBell.getBoolean(BellBlock.class, "powered");

    public CraftBell() {
    }

    public CraftBell(BlockState state) {
        super(state);
    }

    @Override
    public Bell.Attachment getAttachment() {
        return this.get(ATTACHMENT, Bell.Attachment.class);
    }

    @Override
    public void setAttachment(Bell.Attachment leaves) {
        this.set(ATTACHMENT, leaves);
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

