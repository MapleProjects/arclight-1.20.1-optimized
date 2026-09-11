/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.EndPortalFrameBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftEnderPortalFrame
extends CraftBlockData
implements EndPortalFrame,
Directional {
    private static final BooleanProperty EYE = CraftEnderPortalFrame.getBoolean(EndPortalFrameBlock.class, "eye");
    private static final EnumProperty<?> FACING = CraftEnderPortalFrame.getEnum(EndPortalFrameBlock.class, "facing");

    public CraftEnderPortalFrame() {
    }

    public CraftEnderPortalFrame(BlockState state) {
        super(state);
    }

    @Override
    public boolean hasEye() {
        return (Boolean)this.get(EYE);
    }

    @Override
    public void setEye(boolean eye) {
        this.set(EYE, eye);
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

