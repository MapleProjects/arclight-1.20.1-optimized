/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

public abstract class ChunkEvent
extends WorldEvent {
    protected Chunk chunk;

    protected ChunkEvent(@NotNull Chunk chunk) {
        super(chunk.getWorld());
        this.chunk = chunk;
    }

    @NotNull
    public Chunk getChunk() {
        return this.chunk;
    }
}

