/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import org.bukkit.util.BlockVector;

public final class CraftBlockVector {
    private CraftBlockVector() {
    }

    public static BlockPos toBlockPosition(BlockVector blockVector) {
        return new BlockPos(blockVector.getBlockX(), blockVector.getBlockY(), blockVector.getBlockZ());
    }

    public static BlockVector toBukkit(Vec3i baseBlockPosition) {
        return new BlockVector(baseBlockPosition.m_123341_(), baseBlockPosition.m_123342_(), baseBlockPosition.m_123343_());
    }
}

