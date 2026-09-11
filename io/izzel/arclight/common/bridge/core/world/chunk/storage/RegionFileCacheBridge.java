/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.ChunkPos
 */
package io.izzel.arclight.common.bridge.core.world.chunk.storage;

import java.io.IOException;
import net.minecraft.world.level.ChunkPos;

public interface RegionFileCacheBridge {
    public boolean bridge$chunkExists(ChunkPos var1) throws IOException;
}

