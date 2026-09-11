/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.LeverBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Switch;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLever
extends CraftBlockData
implements Switch,
Directional,
FaceAttachable,
Powerable {
    private static final EnumProperty<?> FACE = CraftLever.getEnum(LeverBlock.class, "face");
    private static final EnumProperty<?> FACING = CraftLever.getEnum(LeverBlock.class, "facing");
    private static final EnumProperty<?> ATTACH_FACE = CraftLever.getEnum(LeverBlock.class, "face");
    private static final BooleanProperty POWERED = CraftLever.getBoolean(LeverBlock.class, "powered");

    public CraftLever() {
    }

    public CraftLever(BlockState state) {
        super(state);
    }

    @Override
    public Switch.Face getFace() {
        return this.get(FACE, Switch.Face.class);
    }

    @Override
    public void setFace(Switch.Face face) {
        this.set(FACE, face);
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
    public FaceAttachable.AttachedFace getAttachedFace() {
        return this.get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
    }

    @Override
    public void setAttachedFace(FaceAttachable.AttachedFace face) {
        this.set(ATTACH_FACE, face);
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

