/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.TickingTracker
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.mod.server.api;

import io.izzel.arclight.api.TickingTracker;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class DefaultTickingTracker
implements TickingTracker {
    @Nullable
    public Object getTickingSource() {
        Entity entity = this.getTickingEntity();
        if (entity != null) {
            return entity;
        }
        TileState tileState = this.getTickingBlockEntity();
        if (tileState != null) {
            return tileState;
        }
        Block block = this.getTickingBlock();
        if (block != null) {
            return block;
        }
        return null;
    }

    @Nullable
    public Entity getTickingEntity() {
        net.minecraft.world.entity.Entity tickingEntity = ArclightCaptures.getTickingEntity();
        if (tickingEntity != null) {
            return ((EntityBridge)tickingEntity).bridge$getBukkitEntity();
        }
        return null;
    }

    @Nullable
    public Block getTickingBlock() {
        ServerLevel level = ArclightCaptures.getTickingLevel();
        BlockPos pos = ArclightCaptures.getTickingPosition();
        if (level != null && pos != null) {
            return CraftBlock.at((LevelAccessor)level, pos);
        }
        return null;
    }

    @Nullable
    public TileState getTickingBlockEntity() {
        CraftBlock block;
        BlockState state;
        Level level;
        Object blockEntity = ArclightCaptures.getTickingBlockEntity();
        if (blockEntity != null && (level = blockEntity.m_58904_()) != null && (state = (block = CraftBlock.at((LevelAccessor)level, blockEntity.m_58899_())).getState()) instanceof TileState) {
            return (TileState)state;
        }
        return null;
    }
}

