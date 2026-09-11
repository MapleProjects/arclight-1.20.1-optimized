/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.border.WorldBorder
 */
package io.izzel.arclight.common.bridge.core.world;

import java.util.Optional;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.border.WorldBorder;

public interface TeleporterBridge {
    public Optional<BlockUtil.FoundRectangle> bridge$findPortal(BlockPos var1, WorldBorder var2, int var3);

    public Optional<BlockUtil.FoundRectangle> bridge$createPortal(BlockPos var1, Direction.Axis var2, Entity var3, int var4);
}

