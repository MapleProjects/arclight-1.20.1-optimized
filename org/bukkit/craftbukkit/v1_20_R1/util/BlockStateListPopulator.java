/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.EntityBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.storage.LevelData
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.LevelData;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.util.DummyGeneratorAccess;

public class BlockStateListPopulator
extends DummyGeneratorAccess {
    private final LevelAccessor world;
    private final Map<BlockPos, net.minecraft.world.level.block.state.BlockState> dataMap = new HashMap<BlockPos, net.minecraft.world.level.block.state.BlockState>();
    private final Map<BlockPos, BlockEntity> entityMap = new HashMap<BlockPos, BlockEntity>();
    private final LinkedHashMap<BlockPos, CraftBlockState> list;

    public BlockStateListPopulator(LevelAccessor world) {
        this(world, new LinkedHashMap<BlockPos, CraftBlockState>());
    }

    private BlockStateListPopulator(LevelAccessor world, LinkedHashMap<BlockPos, CraftBlockState> list) {
        this.world = world;
        this.list = list;
    }

    @Override
    public net.minecraft.world.level.block.state.BlockState m_8055_(BlockPos bp) {
        net.minecraft.world.level.block.state.BlockState blockData = this.dataMap.get(bp);
        return blockData != null ? blockData : this.world.m_8055_(bp);
    }

    @Override
    public FluidState m_6425_(BlockPos bp) {
        net.minecraft.world.level.block.state.BlockState blockData = this.dataMap.get(bp);
        return blockData != null ? blockData.m_60819_() : this.world.m_6425_(bp);
    }

    @Override
    public BlockEntity m_7702_(BlockPos blockposition) {
        if (this.entityMap.containsKey(blockposition)) {
            return this.entityMap.get(blockposition);
        }
        return this.world.m_7702_(blockposition);
    }

    public boolean m_7731_(BlockPos position, net.minecraft.world.level.block.state.BlockState data, int flag) {
        position = position.m_7949_();
        this.list.remove(position);
        this.dataMap.put(position, data);
        if (data.m_155947_()) {
            this.entityMap.put(position, ((EntityBlock)data.m_60734_()).m_142194_(position, data));
        } else {
            this.entityMap.put(position, null);
        }
        CraftBlockState state = (CraftBlockState)CraftBlock.at((LevelAccessor)this, position).getState();
        state.setFlag(flag);
        state.setWorldHandle(this.world);
        this.list.put(position, state);
        return true;
    }

    public ServerLevel getMinecraftWorld() {
        return this.world.getMinecraftWorld();
    }

    public void refreshTiles() {
        for (CraftBlockState state : this.list.values()) {
            if (!(state instanceof CraftBlockEntityState)) continue;
            ((CraftBlockEntityState)state).refreshSnapshot();
        }
    }

    public void updateList() {
        for (BlockState blockState : this.list.values()) {
            blockState.update(true);
        }
    }

    public Set<BlockPos> getBlocks() {
        return this.list.keySet();
    }

    public List<CraftBlockState> getList() {
        return new ArrayList<CraftBlockState>(this.list.values());
    }

    public LevelAccessor getWorld() {
        return this.world;
    }

    public int m_141937_() {
        return this.getWorld().m_141937_();
    }

    public int m_141928_() {
        return this.getWorld().m_141928_();
    }

    @Override
    public boolean m_7433_(BlockPos blockposition, Predicate<net.minecraft.world.level.block.state.BlockState> predicate) {
        return predicate.test(this.m_8055_(blockposition));
    }

    @Override
    public boolean m_142433_(BlockPos bp, Predicate<FluidState> prdct) {
        return this.world.m_142433_(bp, prdct);
    }

    @Override
    public DimensionType m_6042_() {
        return this.world.m_6042_();
    }

    @Override
    public RegistryAccess m_9598_() {
        return this.world.m_9598_();
    }

    @Override
    public LevelData m_6106_() {
        return this.world.m_6106_();
    }

    @Override
    public long m_183596_() {
        return this.world.m_183596_();
    }
}

