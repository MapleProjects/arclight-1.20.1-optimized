/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.serialization.Codec
 *  net.minecraft.Util
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.WorldGenRegion
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.WeightedRandomList
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.NoiseColumn
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.biome.BiomeSource
 *  net.minecraft.world.level.biome.MobSpawnSettings$SpawnerData
 *  net.minecraft.world.level.block.EntityBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.chunk.ChunkGeneratorStructureState
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.levelgen.GenerationStep$Carving
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.LegacyRandomSource
 *  net.minecraft.world.level.levelgen.RandomState
 *  net.minecraft.world.level.levelgen.WorldgenRandom
 *  net.minecraft.world.level.levelgen.blending.Blender
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_20_R1.CraftHeightMap;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.generator.CraftChunkData;
import org.bukkit.craftbukkit.v1_20_R1.generator.InternalChunkGenerator;
import org.bukkit.craftbukkit.v1_20_R1.generator.OldCraftChunkData;
import org.bukkit.craftbukkit.v1_20_R1.util.RandomSourceWrapper;
import org.bukkit.generator.ChunkGenerator;

public class CustomChunkGenerator
extends InternalChunkGenerator {
    private final ChunkGenerator delegate;
    private final org.bukkit.generator.ChunkGenerator generator;
    private final ServerLevel world;
    private final Random random = new Random();
    private boolean newApi;
    private boolean implementBaseHeight = true;

    public CustomChunkGenerator(ServerLevel world, ChunkGenerator delegate, org.bukkit.generator.ChunkGenerator generator) {
        super(delegate.m_62218_(), delegate.f_223021_);
        this.world = world;
        this.delegate = delegate;
        this.generator = generator;
    }

    public ChunkGenerator getDelegate() {
        return this.delegate;
    }

    private static WorldgenRandom getSeededRandom() {
        return new WorldgenRandom((RandomSource)new LegacyRandomSource(0L));
    }

    public BiomeSource m_62218_() {
        return this.delegate.m_62218_();
    }

    public int m_142062_() {
        return this.delegate.m_142062_();
    }

    public int m_6337_() {
        return this.delegate.m_6337_();
    }

    public void m_255037_(RegistryAccess iregistrycustom, ChunkGeneratorStructureState chunkgeneratorstructurestate, StructureManager structuremanager, ChunkAccess ichunkaccess, StructureTemplateManager structuretemplatemanager) {
        WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
        int x = ichunkaccess.m_7697_().f_45578_;
        int z = ichunkaccess.m_7697_().f_45579_;
        random.m_188584_(Mth.m_14130_((int)x, (int)"should-structures".hashCode(), (int)z) ^ this.world.m_7328_());
        if (this.generator.shouldGenerateStructures(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z)) {
            super.m_255037_(iregistrycustom, chunkgeneratorstructurestate, structuremanager, ichunkaccess, structuretemplatemanager);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void m_214194_(WorldGenRegion regionlimitedworldaccess, StructureManager structuremanager, RandomState randomstate, ChunkAccess ichunkaccess) {
        ChunkGenerator.ChunkData data;
        int z;
        int x;
        block16: {
            WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
            x = ichunkaccess.m_7697_().f_45578_;
            z = ichunkaccess.m_7697_().f_45579_;
            random.m_188584_(Mth.m_14130_((int)x, (int)"should-surface".hashCode(), (int)z) ^ regionlimitedworldaccess.m_7328_());
            if (this.generator.shouldGenerateSurface(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z)) {
                this.delegate.m_214194_(regionlimitedworldaccess, structuremanager, randomstate, ichunkaccess);
            }
            CraftChunkData chunkData = new CraftChunkData(this.world.getWorld(), ichunkaccess);
            random.m_188584_((long)x * 341873128712L + (long)z * 132897987541L);
            this.generator.generateSurface(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z, chunkData);
            if (this.generator.shouldGenerateBedrock()) {
                random = CustomChunkGenerator.getSeededRandom();
                random.m_188584_((long)x * 341873128712L + (long)z * 132897987541L);
            }
            random = CustomChunkGenerator.getSeededRandom();
            random.m_188584_((long)x * 341873128712L + (long)z * 132897987541L);
            this.generator.generateBedrock(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z, chunkData);
            chunkData.breakLink();
            if (this.newApi) {
                return;
            }
            this.random.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
            CustomBiomeGrid biomegrid = new CustomBiomeGrid(ichunkaccess);
            try {
                if (this.generator.isParallelCapable()) {
                    data = this.generator.generateChunkData(this.world.getWorld(), this.random, x, z, biomegrid);
                    break block16;
                }
                CustomChunkGenerator customChunkGenerator = this;
                synchronized (customChunkGenerator) {
                    data = this.generator.generateChunkData(this.world.getWorld(), this.random, x, z, biomegrid);
                }
            }
            catch (UnsupportedOperationException exception) {
                this.newApi = true;
                return;
            }
        }
        Preconditions.checkArgument((boolean)(data instanceof OldCraftChunkData), (String)"Plugins must use createChunkData(World) rather than implementing ChunkData: %s", (Object)data);
        OldCraftChunkData craftData = (OldCraftChunkData)data;
        LevelChunkSection[] sections = craftData.getRawChunkData();
        LevelChunkSection[] csect = ichunkaccess.m_7103_();
        int scnt = Math.min(csect.length, sections.length);
        int sec = 0;
        while (sec < scnt) {
            if (sections[sec] != null) {
                LevelChunkSection section = sections[sec];
                LevelChunkSection oldSection = csect[sec];
                int biomeX = 0;
                while (biomeX < 4) {
                    int biomeY = 0;
                    while (biomeY < 4) {
                        int biomeZ = 0;
                        while (biomeZ < 4) {
                            section.setBiome(biomeX, biomeY, biomeZ, oldSection.m_204433_(biomeX, biomeY, biomeZ));
                            ++biomeZ;
                        }
                        ++biomeY;
                    }
                    ++biomeX;
                }
                csect[sec] = section;
            }
            ++sec;
        }
        if (craftData.getTiles() != null) {
            for (BlockPos pos : craftData.getTiles()) {
                int tz;
                int ty;
                int tx = pos.m_123341_();
                BlockState block = craftData.getTypeId(tx, ty = pos.m_123342_(), tz = pos.m_123343_());
                if (!block.m_155947_()) continue;
                BlockEntity tile = ((EntityBlock)block.m_60734_()).m_142194_(new BlockPos((x << 4) + tx, ty, (z << 4) + tz), block);
                ichunkaccess.m_142169_(tile);
            }
        }
    }

    public void m_213679_(WorldGenRegion regionlimitedworldaccess, long seed, RandomState randomstate, BiomeManager biomemanager, StructureManager structuremanager, ChunkAccess ichunkaccess, GenerationStep.Carving worldgenstage_features) {
        WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
        int x = ichunkaccess.m_7697_().f_45578_;
        int z = ichunkaccess.m_7697_().f_45579_;
        random.m_188584_(Mth.m_14130_((int)x, (int)"should-caves".hashCode(), (int)z) ^ regionlimitedworldaccess.m_7328_());
        if (this.generator.shouldGenerateCaves(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z)) {
            this.delegate.m_213679_(regionlimitedworldaccess, seed, randomstate, biomemanager, structuremanager, ichunkaccess, worldgenstage_features);
        }
        CraftChunkData chunkData = new CraftChunkData(this.world.getWorld(), ichunkaccess);
        random.m_64690_(seed, 0, 0);
        this.generator.generateCaves(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z, chunkData);
        chunkData.breakLink();
    }

    public CompletableFuture<ChunkAccess> m_213974_(Executor executor, Blender blender, RandomState randomstate, StructureManager structuremanager, ChunkAccess ichunkaccess) {
        CompletableFuture future = null;
        WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
        int x = ichunkaccess.m_7697_().f_45578_;
        int z = ichunkaccess.m_7697_().f_45579_;
        random.m_188584_(Mth.m_14130_((int)x, (int)"should-noise".hashCode(), (int)z) ^ this.world.m_7328_());
        if (this.generator.shouldGenerateNoise(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z)) {
            future = this.delegate.m_213974_(executor, blender, randomstate, structuremanager, ichunkaccess);
        }
        Function<ChunkAccess, ChunkAccess> function = ichunkaccess1 -> {
            CraftChunkData chunkData = new CraftChunkData(this.world.getWorld(), (ChunkAccess)ichunkaccess1);
            random.m_188584_((long)x * 341873128712L + (long)z * 132897987541L);
            this.generator.generateNoise(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z, chunkData);
            chunkData.breakLink();
            return ichunkaccess1;
        };
        return future == null ? CompletableFuture.supplyAsync(() -> (ChunkAccess)function.apply(ichunkaccess), Util.m_183991_()) : future.thenApply(function);
    }

    public int m_214096_(int i, int j, Heightmap.Types heightmap_type, LevelHeightAccessor levelheightaccessor, RandomState randomstate) {
        if (this.implementBaseHeight) {
            try {
                WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
                int xChunk = i >> 4;
                int zChunk = j >> 4;
                random.m_188584_((long)xChunk * 341873128712L + (long)zChunk * 132897987541L);
                return this.generator.getBaseHeight(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), i, j, CraftHeightMap.fromNMS(heightmap_type));
            }
            catch (UnsupportedOperationException exception) {
                this.implementBaseHeight = false;
            }
        }
        return this.delegate.m_214096_(i, j, heightmap_type, levelheightaccessor, randomstate);
    }

    public WeightedRandomList<MobSpawnSettings.SpawnerData> m_223133_(Holder<net.minecraft.world.level.biome.Biome> holder, StructureManager structuremanager, MobCategory enumcreaturetype, BlockPos blockposition) {
        return this.delegate.m_223133_(holder, structuremanager, enumcreaturetype, blockposition);
    }

    public void m_213609_(WorldGenLevel generatoraccessseed, ChunkAccess ichunkaccess, StructureManager structuremanager) {
        WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
        int x = ichunkaccess.m_7697_().f_45578_;
        int z = ichunkaccess.m_7697_().f_45579_;
        random.m_188584_(Mth.m_14130_((int)x, (int)"should-decoration".hashCode(), (int)z) ^ generatoraccessseed.m_7328_());
        super.applyBiomeDecoration(generatoraccessseed, ichunkaccess, structuremanager, this.generator.shouldGenerateDecorations(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z));
    }

    public void m_213600_(List<String> list, RandomState randomstate, BlockPos blockposition) {
        this.delegate.m_213600_(list, randomstate, blockposition);
    }

    public void m_6929_(WorldGenRegion regionlimitedworldaccess) {
        WorldgenRandom random = CustomChunkGenerator.getSeededRandom();
        int x = regionlimitedworldaccess.m_143488_().f_45578_;
        int z = regionlimitedworldaccess.m_143488_().f_45579_;
        random.m_188584_(Mth.m_14130_((int)x, (int)"should-mobs".hashCode(), (int)z) ^ regionlimitedworldaccess.m_7328_());
        if (this.generator.shouldGenerateMobs(this.world.getWorld(), new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z)) {
            this.delegate.m_6929_(regionlimitedworldaccess);
        }
    }

    public int m_142051_(LevelHeightAccessor levelheightaccessor) {
        return this.delegate.m_142051_(levelheightaccessor);
    }

    public int m_6331_() {
        return this.delegate.m_6331_();
    }

    public NoiseColumn m_214184_(int i, int j, LevelHeightAccessor levelheightaccessor, RandomState randomstate) {
        return this.delegate.m_214184_(i, j, levelheightaccessor, randomstate);
    }

    protected Codec<? extends ChunkGenerator> m_6909_() {
        return Codec.unit(null);
    }

    @Deprecated
    private class CustomBiomeGrid
    implements ChunkGenerator.BiomeGrid {
        private final ChunkAccess biome;

        public CustomBiomeGrid(ChunkAccess biome) {
            this.biome = biome;
        }

        @Override
        public Biome getBiome(int x, int z) {
            return this.getBiome(x, 0, z);
        }

        @Override
        public void setBiome(int x, int z, Biome bio) {
            int y = CustomChunkGenerator.this.world.getWorld().getMinHeight();
            while (y < CustomChunkGenerator.this.world.getWorld().getMaxHeight()) {
                this.setBiome(x, y, z, bio);
                y += 4;
            }
        }

        @Override
        public Biome getBiome(int x, int y, int z) {
            return CraftBlock.biomeBaseToBiome((Registry<net.minecraft.world.level.biome.Biome>)this.biome.biomeRegistry, (Holder<net.minecraft.world.level.biome.Biome>)this.biome.m_203495_(x >> 2, y >> 2, z >> 2));
        }

        @Override
        public void setBiome(int x, int y, int z, Biome bio) {
            Preconditions.checkArgument((bio != Biome.CUSTOM ? 1 : 0) != 0, (String)"Cannot set the biome to %s", (Object)bio);
            this.biome.setBiome(x >> 2, y >> 2, z >> 2, CraftBlock.biomeToBiomeBase((Registry<net.minecraft.world.level.biome.Biome>)this.biome.biomeRegistry, bio));
        }
    }
}

