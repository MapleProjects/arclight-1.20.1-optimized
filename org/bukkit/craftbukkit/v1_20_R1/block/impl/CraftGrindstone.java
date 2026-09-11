/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.GrindstoneBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftGrindstone
extends CraftBlockData
implements Grindstone,
Directional,
FaceAttachable {
    private static final EnumProperty<?> FACING = CraftGrindstone.getEnum(GrindstoneBlock.class, "facing");
    private static final EnumProperty<?> ATTACH_FACE = CraftGrindstone.getEnum(GrindstoneBlock.class, "face");

    public CraftGrindstone() {
    }

    public CraftGrindstone(BlockState state) {
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
    public FaceAttachable.AttachedFace getAttachedFace() {
        return this.get(ATTACH_FACE, FaceAttachable.AttachedFace.class);
    }

    @Override
    public void setAttachedFace(FaceAttachable.AttachedFace face) {
        this.set(ATTACH_FACE, face);
    }
}

