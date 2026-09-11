/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.IdMap
 *  net.minecraft.core.Registry
 *  net.minecraft.core.SectionPos
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.thread.ProcessorMailbox
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.Biomes
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.DataLayer
 *  net.minecraft.world.level.chunk.ImposterProtoChunk
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.PalettedContainer
 *  net.minecraft.world.level.chunk.PalettedContainer$Strategy
 *  net.minecraft.world.level.chunk.PalettedContainerRO
 *  net.minecraft.world.level.chunk.storage.ChunkSerializer
 *  net.minecraft.world.level.chunk.storage.EntityStorage
 *  net.minecraft.world.level.entity.PersistentEntitySectionManager
 *  net.minecraft.world.level.levelgen.Heightmap
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.WorldgenRandom
 *  net.minecraft.world.level.lighting.LevelLightEngine
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.thread.ProcessorMailbox;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.DataLayer;
import net.minecraft.world.level.chunk.ImposterProtoChunk;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import net.minecraft.world.level.chunk.storage.EntityStorage;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.lighting.LevelLightEngine;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftChunkSnapshot;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

public class CraftChunk
implements Chunk {
    private final ServerLevel worldServer;
    private final int x;
    private final int z;
    private static final PalettedContainer<net.minecraft.world.level.block.state.BlockState> emptyBlockIDs = new PalettedContainer((IdMap)Block.f_49791_, (Object)Blocks.f_50016_.m_49966_(), PalettedContainer.Strategy.f_188137_);
    private static final byte[] emptyLight = new byte[2048];

    static {
        Arrays.fill(emptyLight, (byte)-1);
    }

    public CraftChunk(LevelChunk chunk) {
        this.worldServer = chunk.r;
        this.x = chunk.m_7697_().f_45578_;
        this.z = chunk.m_7697_().f_45579_;
    }

    public CraftChunk(ServerLevel worldServer, int x, int z) {
        this.worldServer = worldServer;
        this.x = x;
        this.z = z;
    }

    @Override
    public World getWorld() {
        return this.worldServer.getWorld();
    }

    public CraftWorld getCraftWorld() {
        return (CraftWorld)this.getWorld();
    }

    public ChunkAccess getHandle(ChunkStatus chunkStatus) {
        ChunkAccess chunkAccess = this.worldServer.m_46819_(this.x, this.z, chunkStatus);
        if (chunkAccess instanceof ImposterProtoChunk var3_4) {
            return extension.m_62768_();
        }
        return chunkAccess;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getZ() {
        return this.z;
    }

    public String toString() {
        return "CraftChunk{x=" + this.getX() + "z=" + this.getZ() + '}';
    }

    @Override
    public org.bukkit.block.Block getBlock(int x, int y, int z) {
        CraftChunk.validateChunkCoordinates(this.worldServer.m_141937_(), this.worldServer.m_151558_(), x, y, z);
        return new CraftBlock((LevelAccessor)this.worldServer, new BlockPos(this.x << 4 | x, y, this.z << 4 | z));
    }

    @Override
    public boolean isEntitiesLoaded() {
        return this.getCraftWorld().getHandle().f_143244_.m_157507_(ChunkPos.m_45589_((int)this.x, (int)this.z));
    }

    @Override
    public Entity[] getEntities() {
        long pair;
        PersistentEntitySectionManager entityManager;
        if (!this.isLoaded()) {
            this.getWorld().getChunkAt(this.x, this.z);
        }
        if ((entityManager = this.getCraftWorld().getHandle().f_143244_).m_157507_(pair = ChunkPos.m_45589_((int)this.x, (int)this.z))) {
            return (Entity[])entityManager.getEntities(new ChunkPos(this.x, this.z)).stream().map(net.minecraft.world.entity.Entity::getBukkitEntity).filter(Objects::nonNull).toArray(Entity[]::new);
        }
        entityManager.m_157555_(pair);
        ProcessorMailbox mailbox = ((EntityStorage)entityManager.f_157493_).f_182485_;
        BooleanSupplier supplier = () -> {
            if (entityManager.m_157507_(pair)) {
                return true;
            }
            if (!entityManager.isPending(pair)) {
                entityManager.m_157555_(pair);
            }
            entityManager.m_157506_();
            return entityManager.m_157507_(pair);
        };
        while (!supplier.getAsBoolean()) {
            if (mailbox.m_146355_() != 0) {
                mailbox.run();
                continue;
            }
            Thread.yield();
            LockSupport.parkNanos("waiting for entity loading", 100000L);
        }
        return (Entity[])entityManager.getEntities(new ChunkPos(this.x, this.z)).stream().map(net.minecraft.world.entity.Entity::getBukkitEntity).filter(Objects::nonNull).toArray(Entity[]::new);
    }

    @Override
    public BlockState[] getTileEntities() {
        if (!this.isLoaded()) {
            this.getWorld().getChunkAt(this.x, this.z);
        }
        int index = 0;
        ChunkAccess chunk = this.getHandle(ChunkStatus.f_62326_);
        BlockState[] entities = new BlockState[chunk.f_187610_.size()];
        for (BlockPos position : chunk.f_187610_.keySet()) {
            entities[index++] = this.worldServer.getWorld().getBlockAt(position.m_123341_(), position.m_123342_(), position.m_123343_()).getState();
        }
        return entities;
    }

    @Override
    public boolean isGenerated() {
        ChunkAccess chunk = this.getHandle(ChunkStatus.f_62314_);
        return chunk.m_6415_().m_62427_(ChunkStatus.f_62326_);
    }

    @Override
    public boolean isLoaded() {
        return this.getWorld().isChunkLoaded(this);
    }

    @Override
    public boolean load() {
        return this.getWorld().loadChunk(this.getX(), this.getZ(), true);
    }

    @Override
    public boolean load(boolean generate) {
        return this.getWorld().loadChunk(this.getX(), this.getZ(), generate);
    }

    @Override
    public boolean unload() {
        return this.getWorld().unloadChunk(this.getX(), this.getZ());
    }

    @Override
    public boolean isSlimeChunk() {
        return WorldgenRandom.m_224681_((int)this.getX(), (int)this.getZ(), (long)this.getWorld().getSeed(), (long)this.worldServer.spigotConfig.slimeSeed).m_188503_(10) == 0;
    }

    @Override
    public boolean unload(boolean save) {
        return this.getWorld().unloadChunk(this.getX(), this.getZ(), save);
    }

    @Override
    public boolean isForceLoaded() {
        return this.getWorld().isChunkForceLoaded(this.getX(), this.getZ());
    }

    @Override
    public void setForceLoaded(boolean forced) {
        this.getWorld().setChunkForceLoaded(this.getX(), this.getZ(), forced);
    }

    @Override
    public boolean addPluginChunkTicket(Plugin plugin) {
        return this.getWorld().addPluginChunkTicket(this.getX(), this.getZ(), plugin);
    }

    @Override
    public boolean removePluginChunkTicket(Plugin plugin) {
        return this.getWorld().removePluginChunkTicket(this.getX(), this.getZ(), plugin);
    }

    @Override
    public Collection<Plugin> getPluginChunkTickets() {
        return this.getWorld().getPluginChunkTickets(this.getX(), this.getZ());
    }

    @Override
    public long getInhabitedTime() {
        return this.getHandle(ChunkStatus.f_62314_).m_6319_();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        Preconditions.checkArgument((ticks >= 0L ? 1 : 0) != 0, (Object)"ticks cannot be negative");
        this.getHandle(ChunkStatus.f_62315_).m_6141_(ticks);
    }

    @Override
    public boolean contains(BlockData block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"Block cannot be null");
        com.google.common.base.Predicate nms = Predicates.equalTo((Object)((CraftBlockData)block).getState());
        LevelChunkSection[] levelChunkSectionArray = this.getHandle(ChunkStatus.f_62326_).m_7103_();
        int n = levelChunkSectionArray.length;
        int n2 = 0;
        while (n2 < n) {
            LevelChunkSection section = levelChunkSectionArray[n2];
            if (section != null && section.m_63019_().m_63109_((Predicate)nms)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean contains(Biome biome) {
        Preconditions.checkArgument((biome != null ? 1 : 0) != 0, (Object)"Biome cannot be null");
        ChunkAccess chunk = this.getHandle(ChunkStatus.f_62317_);
        com.google.common.base.Predicate nms = Predicates.equalTo(CraftBlock.biomeToBiomeBase((Registry<net.minecraft.world.level.biome.Biome>)chunk.biomeRegistry, biome));
        LevelChunkSection[] levelChunkSectionArray = chunk.m_7103_();
        int n = levelChunkSectionArray.length;
        int n2 = 0;
        while (n2 < n) {
            LevelChunkSection section = levelChunkSectionArray[n2];
            if (section != null && section.m_187996_().m_63109_((Predicate)nms)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return this.getChunkSnapshot(true, false, false);
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain) {
        ChunkAccess chunk = this.getHandle(ChunkStatus.f_62326_);
        LevelChunkSection[] cs = chunk.m_7103_();
        PalettedContainer[] sectionBlockIDs = new PalettedContainer[cs.length];
        byte[][] sectionSkyLights = new byte[cs.length][];
        byte[][] sectionEmitLights = new byte[cs.length][];
        boolean[] sectionEmpty = new boolean[cs.length];
        PalettedContainer[] biome = includeBiome || includeBiomeTempRain ? new PalettedContainer[cs.length] : null;
        Registry iregistry = this.worldServer.m_9598_().m_175515_(Registries.f_256952_);
        Codec biomeCodec = PalettedContainer.m_238418_((IdMap)iregistry.m_206115_(), (Codec)iregistry.m_206110_(), (PalettedContainer.Strategy)PalettedContainer.Strategy.f_188138_, (Object)iregistry.m_246971_(Biomes.f_48202_));
        int i = 0;
        while (i < cs.length) {
            CompoundTag data = new CompoundTag();
            data.m_128365_("block_states", (Tag)ChunkSerializer.f_188227_.encodeStart((DynamicOps)NbtOps.f_128958_, (Object)cs[i].m_63019_()).get().left().get());
            sectionBlockIDs[i] = (PalettedContainer)ChunkSerializer.f_188227_.parse((DynamicOps)NbtOps.f_128958_, (Object)data.m_128469_("block_states")).get().left().get();
            LevelLightEngine lightengine = this.worldServer.m_5518_();
            DataLayer skyLightArray = lightengine.m_75814_(LightLayer.SKY).m_8079_(SectionPos.m_123173_((int)this.x, (int)i, (int)this.z));
            if (skyLightArray == null) {
                sectionSkyLights[i] = emptyLight;
            } else {
                sectionSkyLights[i] = new byte[2048];
                System.arraycopy(skyLightArray.m_7877_(), 0, sectionSkyLights[i], 0, 2048);
            }
            DataLayer emitLightArray = lightengine.m_75814_(LightLayer.BLOCK).m_8079_(SectionPos.m_123173_((int)this.x, (int)i, (int)this.z));
            if (emitLightArray == null) {
                sectionEmitLights[i] = emptyLight;
            } else {
                sectionEmitLights[i] = new byte[2048];
                System.arraycopy(emitLightArray.m_7877_(), 0, sectionEmitLights[i], 0, 2048);
            }
            if (biome != null) {
                data.m_128365_("biomes", (Tag)biomeCodec.encodeStart((DynamicOps)NbtOps.f_128958_, (Object)cs[i].m_187996_()).get().left().get());
                biome[i] = (PalettedContainerRO)biomeCodec.parse((DynamicOps)NbtOps.f_128958_, (Object)data.m_128469_("biomes")).get().left().get();
            }
            ++i;
        }
        Heightmap hmap = null;
        if (includeMaxBlockY) {
            hmap = new Heightmap(chunk, Heightmap.Types.MOTION_BLOCKING);
            hmap.m_158364_(chunk, Heightmap.Types.MOTION_BLOCKING, ((Heightmap)chunk.f_187608_.get(Heightmap.Types.MOTION_BLOCKING)).m_64239_());
        }
        World world = this.getWorld();
        return new CraftChunkSnapshot(this.getX(), this.getZ(), chunk.m_141937_(), chunk.m_151558_(), world.getName(), world.getFullTime(), (PalettedContainer<net.minecraft.world.level.block.state.BlockState>[])sectionBlockIDs, sectionSkyLights, sectionEmitLights, sectionEmpty, hmap, (Registry<net.minecraft.world.level.biome.Biome>)iregistry, (PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[])biome);
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return this.getHandle((ChunkStatus)ChunkStatus.f_62315_).persistentDataContainer;
    }

    @Override
    public Chunk.LoadLevel getLoadLevel() {
        LevelChunk chunk = this.worldServer.getChunkIfLoaded(this.getX(), this.getZ());
        if (chunk == null) {
            return Chunk.LoadLevel.UNLOADED;
        }
        return Chunk.LoadLevel.values()[chunk.m_287138_().ordinal()];
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CraftChunk that = (CraftChunk)o;
        if (this.x != that.x) {
            return false;
        }
        if (this.z != that.z) {
            return false;
        }
        return this.worldServer.equals(that.worldServer);
    }

    public int hashCode() {
        int result = this.worldServer.hashCode();
        result = 31 * result + this.x;
        result = 31 * result + this.z;
        return result;
    }

    public static ChunkSnapshot getEmptyChunkSnapshot(int x, int z, CraftWorld world, boolean includeBiome, boolean includeBiomeTempRain) {
        ChunkAccess actual = world.getHandle().m_46819_(x, z, includeBiome || includeBiomeTempRain ? ChunkStatus.f_62317_ : ChunkStatus.f_62314_);
        int hSection = actual.m_151559_();
        PalettedContainer[] blockIDs = new PalettedContainer[hSection];
        byte[][] skyLight = new byte[hSection][];
        byte[][] emitLight = new byte[hSection][];
        boolean[] empty = new boolean[hSection];
        Registry iregistry = world.getHandle().m_9598_().m_175515_(Registries.f_256952_);
        PalettedContainer[] biome = includeBiome || includeBiomeTempRain ? new PalettedContainer[hSection] : null;
        Codec biomeCodec = PalettedContainer.m_238418_((IdMap)iregistry.m_206115_(), (Codec)iregistry.m_206110_(), (PalettedContainer.Strategy)PalettedContainer.Strategy.f_188138_, (Object)iregistry.m_246971_(Biomes.f_48202_));
        int i = 0;
        while (i < hSection) {
            blockIDs[i] = emptyBlockIDs;
            skyLight[i] = emptyLight;
            emitLight[i] = emptyLight;
            empty[i] = true;
            if (biome != null) {
                biome[i] = (PalettedContainer)biomeCodec.parse((DynamicOps)NbtOps.f_128958_, (Object)((Tag)biomeCodec.encodeStart((DynamicOps)NbtOps.f_128958_, (Object)actual.m_183278_(i).m_187996_()).get().left().get())).get().left().get();
            }
            ++i;
        }
        return new CraftChunkSnapshot(x, z, world.getMinHeight(), world.getMaxHeight(), world.getName(), world.getFullTime(), (PalettedContainer<net.minecraft.world.level.block.state.BlockState>[])blockIDs, skyLight, emitLight, empty, new Heightmap(actual, Heightmap.Types.MOTION_BLOCKING), (Registry<net.minecraft.world.level.biome.Biome>)iregistry, (PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[])biome);
    }

    static void validateChunkCoordinates(int minY, int maxY, int x, int y, int z) {
        Preconditions.checkArgument((x >= 0 && x <= 15 ? 1 : 0) != 0, (String)"x out of range (expected 0-15, got %s)", (int)x);
        Preconditions.checkArgument((minY <= y && y <= maxY ? 1 : 0) != 0, (String)"y out of range (expected %s-%s, got %s)", (Object)minY, (Object)maxY, (Object)y);
        Preconditions.checkArgument((z >= 0 && z <= 15 ? 1 : 0) != 0, (String)"z out of range (expected 0-15, got %s)", (int)z);
    }
}

