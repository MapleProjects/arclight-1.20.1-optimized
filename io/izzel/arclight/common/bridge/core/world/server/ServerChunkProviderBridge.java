/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ThreadedLevelLightEngine
 *  net.minecraft.world.level.chunk.ChunkGenerator
 */
package io.izzel.arclight.common.bridge.core.world.server;

import java.io.IOException;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import net.minecraft.world.level.chunk.ChunkGenerator;

public interface ServerChunkProviderBridge {
    public void bridge$close(boolean var1) throws IOException;

    public void bridge$purgeUnload();

    public boolean bridge$tickDistanceManager();

    public boolean bridge$isChunkLoaded(int var1, int var2);

    public ThreadedLevelLightEngine bridge$getLightManager();

    public void bridge$setChunkGenerator(ChunkGenerator var1);

    public void bridge$setViewDistance(int var1);
}

