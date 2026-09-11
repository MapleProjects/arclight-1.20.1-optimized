/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.block.state.BlockState
 */
package io.izzel.arclight.common.bridge.core.world.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;

public interface ChunkBridge {
    public Chunk bridge$getBukkitChunk();

    public BlockState bridge$setType(BlockPos var1, BlockState var2, boolean var3, boolean var4);

    public boolean bridge$isMustNotSave();

    public void bridge$setMustNotSave(boolean var1);

    public boolean bridge$isNeedsDecoration();

    public void bridge$loadCallback();

    public void bridge$unloadCallback();

    public CraftPersistentDataContainer bridge$getPersistentContainer();
}

