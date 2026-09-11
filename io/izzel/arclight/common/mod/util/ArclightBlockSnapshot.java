/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.common.util.BlockSnapshot
 */
package io.izzel.arclight.common.mod.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.BlockSnapshot;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;

public class ArclightBlockSnapshot
extends CraftBlock {
    private final BlockState blockState;

    public ArclightBlockSnapshot(BlockSnapshot blockSnapshot, boolean current) {
        super(blockSnapshot.getLevel(), blockSnapshot.getPos());
        this.blockState = current ? blockSnapshot.getCurrentBlock() : blockSnapshot.getReplacedBlock();
    }

    @Override
    public BlockState getNMS() {
        return this.blockState;
    }

    public static ArclightBlockSnapshot fromBlockSnapshot(BlockSnapshot blockSnapshot, boolean current) {
        return new ArclightBlockSnapshot(blockSnapshot, current);
    }
}

