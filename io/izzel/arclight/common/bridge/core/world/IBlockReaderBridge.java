/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.phys.BlockHitResult
 */
package io.izzel.arclight.common.bridge.core.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;

public interface IBlockReaderBridge {
    public BlockHitResult bridge$rayTraceBlock(ClipContext var1, BlockPos var2);
}

