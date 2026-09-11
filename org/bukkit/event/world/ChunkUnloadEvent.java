/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.ChunkEvent;
import org.jetbrains.annotations.NotNull;

public class ChunkUnloadEvent
extends ChunkEvent {
    private static final HandlerList handlers = new HandlerList();
    private boolean saveChunk;

    public ChunkUnloadEvent(@NotNull Chunk chunk) {
        this(chunk, true);
    }

    public ChunkUnloadEvent(@NotNull Chunk chunk, boolean save) {
        super(chunk);
        this.saveChunk = save;
    }

    public boolean isSaveChunk() {
        return this.saveChunk;
    }

    public void setSaveChunk(boolean saveChunk) {
        this.saveChunk = saveChunk;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

