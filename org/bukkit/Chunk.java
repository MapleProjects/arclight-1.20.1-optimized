/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import java.util.Collection;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface Chunk
extends PersistentDataHolder {
    public int getX();

    public int getZ();

    @NotNull
    public World getWorld();

    @NotNull
    public Block getBlock(int var1, int var2, int var3);

    @NotNull
    public ChunkSnapshot getChunkSnapshot();

    @NotNull
    public ChunkSnapshot getChunkSnapshot(boolean var1, boolean var2, boolean var3);

    public boolean isEntitiesLoaded();

    @NotNull
    public Entity[] getEntities();

    @NotNull
    public BlockState[] getTileEntities();

    public boolean isGenerated();

    public boolean isLoaded();

    public boolean load(boolean var1);

    public boolean load();

    public boolean unload(boolean var1);

    public boolean unload();

    public boolean isSlimeChunk();

    public boolean isForceLoaded();

    public void setForceLoaded(boolean var1);

    public boolean addPluginChunkTicket(@NotNull Plugin var1);

    public boolean removePluginChunkTicket(@NotNull Plugin var1);

    @NotNull
    public Collection<Plugin> getPluginChunkTickets();

    public long getInhabitedTime();

    public void setInhabitedTime(long var1);

    public boolean contains(@NotNull BlockData var1);

    public boolean contains(@NotNull Biome var1);

    @NotNull
    public LoadLevel getLoadLevel();

    public static enum LoadLevel {
        INACCESSIBLE,
        BORDER,
        TICKING,
        ENTITY_TICKING,
        UNLOADED;

    }
}

