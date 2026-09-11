/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.world.level.chunk.ChunkGenerator
 */
package io.izzel.arclight.common.bridge.core.world.server;

import io.izzel.arclight.common.mod.util.ArclightCallbackExecutor;
import java.util.function.BooleanSupplier;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.level.chunk.ChunkGenerator;

public interface ChunkMapBridge {
    public void bridge$tick(BooleanSupplier var1);

    public Iterable<ChunkHolder> bridge$getLoadedChunksIterable();

    public void bridge$tickEntityTracker();

    public ArclightCallbackExecutor bridge$getCallbackExecutor();

    public ChunkHolder bridge$chunkHolderAt(long var1);

    public void bridge$setViewDistance(int var1);

    public void bridge$setChunkGenerator(ChunkGenerator var1);
}

