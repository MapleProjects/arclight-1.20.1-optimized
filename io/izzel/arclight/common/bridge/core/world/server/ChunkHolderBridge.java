/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.chunk.LevelChunk
 */
package io.izzel.arclight.common.bridge.core.world.server;

import net.minecraft.world.level.chunk.LevelChunk;

public interface ChunkHolderBridge {
    public int bridge$getOldTicketLevel();

    public LevelChunk bridge$getFullChunk();

    public LevelChunk bridge$getFullChunkUnchecked();
}

