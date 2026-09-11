/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.flag.FeatureFlagSet
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkSource
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.entity.EntityTypeTest
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.lighting.LevelLightEngine
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraft.world.level.storage.LevelData
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.ticks.BlackholeTickAccess
 *  net.minecraft.world.ticks.LevelTickAccess
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.lighting.LevelLightEngine;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.BlackholeTickAccess;
import net.minecraft.world.ticks.LevelTickAccess;

public class DummyGeneratorAccess
implements WorldGenLevel {
    public static final WorldGenLevel INSTANCE = new DummyGeneratorAccess();

    protected DummyGeneratorAccess() {
    }

    public long m_7328_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServerLevel m_6018_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long m_183596_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LevelTickAccess<Block> m_183326_() {
        return BlackholeTickAccess.m_193145_();
    }

    public void m_186460_(BlockPos blockposition, Block block, int i) {
    }

    public LevelTickAccess<Fluid> m_183324_() {
        return BlackholeTickAccess.m_193145_();
    }

    public LevelData m_6106_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DifficultyInstance m_6436_(BlockPos blockposition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MinecraftServer m_7654_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ChunkSource m_7726_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RandomSource m_213780_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void m_5594_(Player entityhuman, BlockPos blockposition, SoundEvent soundeffect, SoundSource soundcategory, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void m_7106_(ParticleOptions particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void m_5898_(Player entityhuman, int i, BlockPos blockposition, int j) {
    }

    public void m_214171_(GameEvent gameevent, Vec3 vec3d, GameEvent.Context gameevent_a) {
    }

    public List<Entity> m_6249_(Entity entity, AABB aabb, Predicate<? super Entity> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T extends Entity> List<T> m_142425_(EntityTypeTest<Entity, T> ett, AABB aabb, Predicate<? super T> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<? extends Player> m_6907_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ChunkAccess m_6522_(int i, int i1, ChunkStatus cs, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int m_6924_(Heightmap.Types type, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int m_7445_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BiomeManager m_7062_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Holder<Biome> m_203675_(int i, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean m_5776_() {
        return false;
    }

    public int m_5736_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DimensionType m_6042_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RegistryAccess m_9598_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FeatureFlagSet m_246046_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float m_7717_(Direction ed, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LevelLightEngine m_5518_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BlockEntity m_7702_(BlockPos blockposition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BlockState m_8055_(BlockPos blockposition) {
        return Blocks.f_50016_.m_49966_();
    }

    public FluidState m_6425_(BlockPos blockposition) {
        return Fluids.f_76191_.m_76145_();
    }

    public WorldBorder m_6857_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean m_7433_(BlockPos bp, Predicate<BlockState> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean m_142433_(BlockPos bp, Predicate<FluidState> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean m_6933_(BlockPos blockposition, BlockState iblockdata, int i, int j) {
        return false;
    }

    public boolean m_7471_(BlockPos blockposition, boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean m_7740_(BlockPos blockposition, boolean flag, Entity entity, int i) {
        return false;
    }
}

