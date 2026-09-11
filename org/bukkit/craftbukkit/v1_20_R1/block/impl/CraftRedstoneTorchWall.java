/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.RedstoneWallTorchBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRedstoneTorchWall
extends CraftBlockData
implements RedstoneWallTorch,
Directional,
Lightable {
    private static final EnumProperty<?> FACING = CraftRedstoneTorchWall.getEnum(RedstoneWallTorchBlock.class, "facing");
    private static final BooleanProperty LIT = CraftRedstoneTorchWall.getBoolean(RedstoneWallTorchBlock.class, "lit");

    public CraftRedstoneTorchWall() {
    }

    public CraftRedstoneTorchWall(BlockState state) {
        super(state);
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
    public boolean isLit() {
        return (Boolean)this.get(LIT);
    }

    @Override
    public void setLit(boolean lit) {
        this.set(LIT, lit);
    }
}

