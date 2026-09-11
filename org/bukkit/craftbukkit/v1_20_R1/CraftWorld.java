/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Predicates
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.HolderSet
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundSetTimePacket
 *  net.minecraft.network.protocol.game.ClientboundSoundEntityPacket
 *  net.minecraft.network.protocol.game.ClientboundSoundPacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ChunkMap$DistanceManager
 *  net.minecraft.server.level.ChunkMap$TrackedEntity
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.Ticket
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.SortedArraySet
 *  net.minecraft.util.Unit
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.item.FallingBlockEntity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.raid.Raid
 *  net.minecraft.world.entity.raid.Raids
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameRules$BooleanValue
 *  net.minecraft.world.level.GameRules$GameRuleTypeVisitor
 *  net.minecraft.world.level.GameRules$IntegerValue
 *  net.minecraft.world.level.GameRules$Key
 *  net.minecraft.world.level.GameRules$Type
 *  net.minecraft.world.level.GameRules$Value
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.ImposterProtoChunk
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.storage.LevelResource
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.Ticket;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.SortedArraySet;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raids;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.ImposterProtoChunk;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.BlockChangeDelegate;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.FeatureFlag;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameRule;
import org.bukkit.HeightMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Raid;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.StructureType;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.craftbukkit.v1_20_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_20_R1.CraftEffect;
import org.bukkit.craftbukkit.v1_20_R1.CraftFeatureFlag;
import org.bukkit.craftbukkit.v1_20_R1.CraftFluidCollisionMode;
import org.bukkit.craftbukkit.v1_20_R1.CraftHeightMap;
import org.bukkit.craftbukkit.v1_20_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R1.CraftRaid;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegionAccessor;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorldBorder;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftDragonBattle;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.generator.structure.CraftStructure;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.metadata.BlockMetadataStore;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftRayTraceResult;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftStructureSearchResult;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.spigotmc.AsyncCatcher;

public class CraftWorld
extends CraftRegionAccessor
implements World {
    public static final int CUSTOM_DIMENSION_OFFSET = 10;
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    private final ServerLevel world;
    private WorldBorder worldBorder;
    private World.Environment environment;
    private final CraftServer server = (CraftServer)Bukkit.getServer();
    private final ChunkGenerator generator;
    private final BiomeProvider biomeProvider;
    private final List<BlockPopulator> populators = new ArrayList<BlockPopulator>();
    private final BlockMetadataStore blockMetadata = new BlockMetadataStore(this);
    private final Object2IntOpenHashMap<SpawnCategory> spawnCategoryLimit = new Object2IntOpenHashMap();
    private final CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
    private static final Random rand = new Random();
    private static Map<String, GameRules.Key<?>> gamerules;
    private static Map<String, GameRules.Type<?>> gameruleDefinitions;
    private final World.Spigot spigot = new World.Spigot(){

        @Override
        public LightningStrike strikeLightning(Location loc, boolean isSilent) {
            LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_((Level)CraftWorld.this.world);
            lightning.m_6027_(loc.getX(), loc.getY(), loc.getZ());
            lightning.isSilent = isSilent;
            CraftWorld.this.world.strikeLightning((net.minecraft.world.entity.Entity)lightning, LightningStrikeEvent.Cause.CUSTOM);
            return (LightningStrike)((Object)lightning.getBukkitEntity());
        }

        @Override
        public LightningStrike strikeLightningEffect(Location loc, boolean isSilent) {
            LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_((Level)CraftWorld.this.world);
            lightning.m_6027_(loc.getX(), loc.getY(), loc.getZ());
            lightning.f_20862_ = true;
            lightning.isSilent = isSilent;
            CraftWorld.this.world.strikeLightning((net.minecraft.world.entity.Entity)lightning, LightningStrikeEvent.Cause.CUSTOM);
            return (LightningStrike)((Object)lightning.getBukkitEntity());
        }
    };

    public CraftWorld(ServerLevel world, ChunkGenerator gen, BiomeProvider biomeProvider, World.Environment env) {
        this.world = world;
        this.generator = gen;
        this.biomeProvider = biomeProvider;
        this.environment = env;
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return CraftBlock.at((LevelAccessor)this.world, new BlockPos(x, y, z));
    }

    @Override
    public Location getSpawnLocation() {
        BlockPos spawn = this.world.m_220360_();
        float yaw = this.world.m_220361_();
        return CraftLocation.toBukkit(spawn, (World)this, yaw, 0.0f);
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location");
        return this.equals(location.getWorld()) ? this.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw()) : false;
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z, float angle) {
        try {
            Location previousLocation = this.getSpawnLocation();
            this.world.f_46442_.m_7250_(new BlockPos(x, y, z), angle);
            SpawnChangeEvent event = new SpawnChangeEvent((World)this, previousLocation);
            this.server.getPluginManager().callEvent(event);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z) {
        return this.setSpawnLocation(x, y, z, 0.0f);
    }

    @Override
    public Chunk getChunkAt(int x, int z) {
        LevelChunk chunk = (LevelChunk)this.world.m_6522_(x, z, ChunkStatus.f_62326_, true);
        return new CraftChunk(chunk);
    }

    @Override
    @NotNull
    public Chunk getChunkAt(int x, int z, boolean generate) {
        if (generate) {
            return this.getChunkAt(x, z);
        }
        return new CraftChunk(this.getHandle(), x, z);
    }

    @Override
    public Chunk getChunkAt(Block block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"null block");
        return this.getChunkAt(block.getX() >> 4, block.getZ() >> 4);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return this.world.m_7726_().isChunkLoaded(x, z);
    }

    @Override
    public boolean isChunkGenerated(int x, int z) {
        try {
            return this.isChunkLoaded(x, z) || ((Optional)this.world.m_7726_().f_8325_.m_223454_(new ChunkPos(x, z)).get()).isPresent();
        }
        catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Chunk[] getLoadedChunks() {
        Long2ObjectLinkedOpenHashMap chunks = this.world.m_7726_().f_8325_.f_140130_;
        return (Chunk[])chunks.values().stream().map(ChunkHolder::getFullChunkNow).filter(Objects::nonNull).map(CraftChunk::new).toArray(Chunk[]::new);
    }

    @Override
    public void loadChunk(int x, int z) {
        this.loadChunk(x, z, true);
    }

    @Override
    public boolean unloadChunk(Chunk chunk) {
        return this.unloadChunk(chunk.getX(), chunk.getZ());
    }

    @Override
    public boolean unloadChunk(int x, int z) {
        return this.unloadChunk(x, z, true);
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save) {
        return this.unloadChunk0(x, z, save);
    }

    @Override
    public boolean unloadChunkRequest(int x, int z) {
        AsyncCatcher.catchOp("chunk unload");
        if (this.isChunkLoaded(x, z)) {
            this.world.m_7726_().m_8438_(TicketType.PLUGIN, new ChunkPos(x, z), 1, (Object)Unit.INSTANCE);
        }
        return true;
    }

    private boolean unloadChunk0(int x, int z, boolean save) {
        AsyncCatcher.catchOp("chunk unload");
        if (!this.isChunkLoaded(x, z)) {
            return true;
        }
        LevelChunk chunk = this.world.m_6325_(x, z);
        chunk.m_8092_(!save);
        this.unloadChunkRequest(x, z);
        this.world.m_7726_().purgeUnload();
        return !this.isChunkLoaded(x, z);
    }

    @Override
    public boolean regenerateChunk(int x, int z) {
        AsyncCatcher.catchOp("chunk regenerate");
        throw new UnsupportedOperationException("Not supported in this Minecraft version! Unless you can fix it, this is not a bug :)");
    }

    @Override
    public boolean refreshChunk(int x, int z) {
        ChunkHolder playerChunk = (ChunkHolder)this.world.m_7726_().f_8325_.f_140130_.get(ChunkPos.m_45589_((int)x, (int)z));
        if (playerChunk == null) {
            return false;
        }
        playerChunk.m_140026_().thenAccept(either -> either.left().ifPresent(chunk -> {
            List playersInRange = chunkHolder.f_140016_.m_183262_(playerChunk.m_140092_(), false);
            if (playersInRange.isEmpty()) {
                return;
            }
            ClientboundLevelChunkWithLightPacket refreshPacket = new ClientboundLevelChunkWithLightPacket(chunk, this.world.m_5518_(), null, null);
            for (ServerPlayer player : playersInRange) {
                if (player.f_8906_ == null) continue;
                player.f_8906_.m_9829_((Packet)refreshPacket);
            }
        }));
        return true;
    }

    @Override
    public boolean isChunkInUse(int x, int z) {
        return this.isChunkLoaded(x, z);
    }

    @Override
    public boolean loadChunk(int x, int z, boolean generate) {
        AsyncCatcher.catchOp("chunk load");
        ChunkAccess chunk = this.world.m_7726_().m_7587_(x, z, generate ? ChunkStatus.f_62326_ : ChunkStatus.f_62314_, true);
        if (chunk instanceof ImposterProtoChunk) {
            chunk = this.world.m_7726_().m_7587_(x, z, ChunkStatus.f_62326_, true);
        }
        if (chunk instanceof LevelChunk) {
            this.world.m_7726_().m_8387_(TicketType.PLUGIN, new ChunkPos(x, z), 1, (Object)Unit.INSTANCE);
            return true;
        }
        return false;
    }

    @Override
    public boolean isChunkLoaded(Chunk chunk) {
        Preconditions.checkArgument((chunk != null ? 1 : 0) != 0, (Object)"null chunk");
        return this.isChunkLoaded(chunk.getX(), chunk.getZ());
    }

    @Override
    public void loadChunk(Chunk chunk) {
        Preconditions.checkArgument((chunk != null ? 1 : 0) != 0, (Object)"null chunk");
        this.loadChunk(chunk.getX(), chunk.getZ());
    }

    @Override
    public boolean addPluginChunkTicket(int x, int z, Plugin plugin) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"null plugin");
        Preconditions.checkArgument((boolean)plugin.isEnabled(), (Object)"plugin is not enabled");
        ChunkMap.DistanceManager chunkDistanceManager = this.world.m_7726_().f_8325_.f_140145_;
        if (chunkDistanceManager.addRegionTicketAtDistance(TicketType.PLUGIN_TICKET, new ChunkPos(x, z), 2, (Object)plugin)) {
            this.getChunkAt(x, z);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePluginChunkTicket(int x, int z, Plugin plugin) {
        Preconditions.checkNotNull((Object)plugin, (Object)"null plugin");
        ChunkMap.DistanceManager chunkDistanceManager = this.world.m_7726_().f_8325_.f_140145_;
        return chunkDistanceManager.removeRegionTicketAtDistance(TicketType.PLUGIN_TICKET, new ChunkPos(x, z), 2, (Object)plugin);
    }

    @Override
    public void removePluginChunkTickets(Plugin plugin) {
        Preconditions.checkNotNull((Object)plugin, (Object)"null plugin");
        ChunkMap.DistanceManager chunkDistanceManager = this.world.m_7726_().f_8325_.f_140145_;
        chunkDistanceManager.removeAllTicketsFor(TicketType.PLUGIN_TICKET, 31, (Object)plugin);
    }

    @Override
    public Collection<Plugin> getPluginChunkTickets(int x, int z) {
        ChunkMap.DistanceManager chunkDistanceManager = this.world.m_7726_().f_8325_.f_140145_;
        SortedArraySet tickets = (SortedArraySet)chunkDistanceManager.f_140761_.get(ChunkPos.m_45589_((int)x, (int)z));
        if (tickets == null) {
            return Collections.emptyList();
        }
        ImmutableList.Builder ret = ImmutableList.builder();
        for (Ticket ticket : tickets) {
            if (ticket.m_9428_() != TicketType.PLUGIN_TICKET) continue;
            ret.add((Object)((Plugin)ticket.f_9422_));
        }
        return ret.build();
    }

    @Override
    public Map<Plugin, Collection<Chunk>> getPluginChunkTickets() {
        HashMap<Plugin, ImmutableList.Builder> ret = new HashMap<Plugin, ImmutableList.Builder>();
        ChunkMap.DistanceManager chunkDistanceManager = this.world.m_7726_().f_8325_.f_140145_;
        for (Long2ObjectMap.Entry chunkTickets : chunkDistanceManager.f_140761_.long2ObjectEntrySet()) {
            long chunkKey = chunkTickets.getLongKey();
            SortedArraySet tickets = (SortedArraySet)chunkTickets.getValue();
            Chunk chunk = null;
            for (Ticket ticket : tickets) {
                if (ticket.m_9428_() != TicketType.PLUGIN_TICKET) continue;
                if (chunk == null) {
                    chunk = this.getChunkAt(ChunkPos.m_45592_((long)chunkKey), ChunkPos.m_45602_((long)chunkKey));
                }
                ret.computeIfAbsent((Plugin)ticket.f_9422_, key -> ImmutableList.builder()).add((Object)chunk);
            }
        }
        return (Map)ret.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((ImmutableList.Builder)entry.getValue()).build()));
    }

    @Override
    public boolean isChunkForceLoaded(int x, int z) {
        return this.getHandle().m_8902_().contains(ChunkPos.m_45589_((int)x, (int)z));
    }

    @Override
    public void setChunkForceLoaded(int x, int z, boolean forced) {
        this.getHandle().m_8602_(x, z, forced);
    }

    @Override
    public Collection<Chunk> getForceLoadedChunks() {
        HashSet<Chunk> chunks = new HashSet<Chunk>();
        Iterator iterator = this.getHandle().m_8902_().iterator();
        while (iterator.hasNext()) {
            long coord = (Long)iterator.next();
            chunks.add(this.getChunkAt(ChunkPos.m_45592_((long)coord), ChunkPos.m_45602_((long)coord)));
        }
        return Collections.unmodifiableCollection(chunks);
    }

    public ServerLevel getHandle() {
        return this.world;
    }

    @Override
    public Item dropItem(Location loc, ItemStack item) {
        return this.dropItem(loc, item, null);
    }

    @Override
    public Item dropItem(Location loc, ItemStack item, Consumer<Item> function) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        ItemEntity entity = new ItemEntity((Level)this.world, loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(item));
        Item itemEntity = (Item)((Object)entity.getBukkitEntity());
        entity.f_31986_ = 10;
        if (function != null) {
            function.accept(itemEntity);
        }
        this.world.addFreshEntity((net.minecraft.world.entity.Entity)entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return itemEntity;
    }

    @Override
    public Item dropItemNaturally(Location loc, ItemStack item) {
        return this.dropItemNaturally(loc, item, null);
    }

    @Override
    public Item dropItemNaturally(Location loc, ItemStack item, Consumer<Item> function) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        double xs = (double)(this.world.f_46441_.m_188501_() * 0.5f) + 0.25;
        double ys = (double)(this.world.f_46441_.m_188501_() * 0.5f) + 0.25;
        double zs = (double)(this.world.f_46441_.m_188501_() * 0.5f) + 0.25;
        loc = loc.clone().add(xs, ys, zs);
        return this.dropItem(loc, item, function);
    }

    @Override
    public Arrow spawnArrow(Location loc, Vector velocity, float speed, float spread) {
        return this.spawnArrow(loc, velocity, speed, spread, Arrow.class);
    }

    @Override
    public <T extends AbstractArrow> T spawnArrow(Location loc, Vector velocity, float speed, float spread, Class<T> clazz) {
        net.minecraft.world.entity.projectile.AbstractArrow arrow;
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((velocity != null ? 1 : 0) != 0, (Object)"Vector cannot be null");
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"clazz Entity for the arrow cannot be null");
        if (TippedArrow.class.isAssignableFrom(clazz)) {
            arrow = (net.minecraft.world.entity.projectile.AbstractArrow)EntityType.f_20548_.m_20615_((Level)this.world);
            ((Arrow)((Object)arrow.getBukkitEntity())).setBasePotionData(new PotionData(PotionType.WATER, false, false));
        } else {
            arrow = SpectralArrow.class.isAssignableFrom(clazz) ? (net.minecraft.world.entity.projectile.AbstractArrow)EntityType.f_20478_.m_20615_((Level)this.world) : (Trident.class.isAssignableFrom(clazz) ? (net.minecraft.world.entity.projectile.AbstractArrow)EntityType.f_20487_.m_20615_((Level)this.world) : (net.minecraft.world.entity.projectile.AbstractArrow)EntityType.f_20548_.m_20615_((Level)this.world));
        }
        arrow.m_7678_(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        arrow.m_6686_(velocity.getX(), velocity.getY(), velocity.getZ(), speed, spread);
        this.world.m_7967_((net.minecraft.world.entity.Entity)arrow);
        return (T)((AbstractArrow)((Object)arrow.getBukkitEntity()));
    }

    @Override
    public LightningStrike strikeLightning(Location loc) {
        return this.strikeLightning0(loc, false);
    }

    @Override
    public LightningStrike strikeLightningEffect(Location loc) {
        return this.strikeLightning0(loc, true);
    }

    private LightningStrike strikeLightning0(Location loc, boolean isVisual) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_((Level)this.world);
        lightning.m_6027_(loc.getX(), loc.getY(), loc.getZ());
        lightning.m_20874_(isVisual);
        this.world.strikeLightning((net.minecraft.world.entity.Entity)lightning, LightningStrikeEvent.Cause.CUSTOM);
        return (LightningStrike)((Object)lightning.getBukkitEntity());
    }

    @Override
    public boolean generateTree(Location loc, TreeType type) {
        return this.generateTree(loc, rand, type);
    }

    @Override
    public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate) {
        this.world.captureTreeGeneration = true;
        this.world.captureBlockStates = true;
        boolean grownTree = this.generateTree(loc, type);
        this.world.captureBlockStates = false;
        this.world.captureTreeGeneration = false;
        if (grownTree) {
            for (org.bukkit.block.BlockState blockstate : this.world.capturedBlockStates.values()) {
                BlockPos position = ((CraftBlockState)blockstate).getPosition();
                BlockState oldBlock = this.world.m_8055_(position);
                int flag = ((CraftBlockState)blockstate).getFlag();
                delegate.setBlockData(blockstate.getX(), blockstate.getY(), blockstate.getZ(), blockstate.getBlockData());
                BlockState newBlock = this.world.m_8055_(position);
                this.world.notifyAndUpdatePhysics(position, null, oldBlock, newBlock, newBlock, flag, 512);
            }
            this.world.capturedBlockStates.clear();
            return true;
        }
        this.world.capturedBlockStates.clear();
        return false;
    }

    @Override
    public String getName() {
        return this.world.K.m_5462_();
    }

    @Override
    public UUID getUID() {
        return this.world.uuid;
    }

    @Override
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(this.world.m_46472_().m_135782_());
    }

    public String toString() {
        return "CraftWorld{name=" + this.getName() + '}';
    }

    @Override
    public long getTime() {
        long time = this.getFullTime() % 24000L;
        if (time < 0L) {
            time += 24000L;
        }
        return time;
    }

    @Override
    public void setTime(long time) {
        long margin = (time - this.getFullTime()) % 24000L;
        if (margin < 0L) {
            margin += 24000L;
        }
        this.setFullTime(this.getFullTime() + margin);
    }

    @Override
    public long getFullTime() {
        return this.world.m_46468_();
    }

    @Override
    public void setFullTime(long time) {
        TimeSkipEvent event = new TimeSkipEvent(this, TimeSkipEvent.SkipReason.CUSTOM, time - this.world.m_46468_());
        this.server.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        this.world.m_8615_(this.world.m_46468_() + event.getSkipAmount());
        for (org.bukkit.entity.Player p : this.getPlayers()) {
            CraftPlayer cp = (CraftPlayer)p;
            if (cp.getHandle().f_8906_ == null) continue;
            cp.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetTimePacket(cp.getHandle().m_9236_().m_46467_(), cp.getHandle().getPlayerTime(), cp.getHandle().m_9236_().m_46469_().m_46207_(GameRules.f_46140_)));
        }
    }

    @Override
    public long getGameTime() {
        return this.world.f_46442_.m_6793_();
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power) {
        return this.createExplosion(x, y, z, power, false, true);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
        return this.createExplosion(x, y, z, power, setFire, true);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        return this.createExplosion(x, y, z, power, setFire, breakBlocks, null);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks, Entity source) {
        return !this.world.m_255391_((net.minecraft.world.entity.Entity)(source == null ? null : ((CraftEntity)source).getHandle()), (double)x, (double)y, (double)z, (float)power, (boolean)setFire, (Level.ExplosionInteraction)(breakBlocks ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE)).wasCanceled;
    }

    @Override
    public boolean createExplosion(Location loc, float power) {
        return this.createExplosion(loc, power, false);
    }

    @Override
    public boolean createExplosion(Location loc, float power, boolean setFire) {
        return this.createExplosion(loc, power, setFire, true);
    }

    @Override
    public boolean createExplosion(Location loc, float power, boolean setFire, boolean breakBlocks) {
        return this.createExplosion(loc, power, setFire, breakBlocks, null);
    }

    @Override
    public boolean createExplosion(Location loc, float power, boolean setFire, boolean breakBlocks, Entity source) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location is null");
        Preconditions.checkArgument((boolean)this.equals(loc.getWorld()), (Object)"Location not in world");
        return this.createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, setFire, breakBlocks, source);
    }

    @Override
    public World.Environment getEnvironment() {
        return this.environment;
    }

    @Override
    public Block getBlockAt(Location location) {
        return this.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @Override
    public Chunk getChunkAt(Location location) {
        return this.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    @Override
    public ChunkGenerator getGenerator() {
        return this.generator;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return this.biomeProvider;
    }

    @Override
    public List<BlockPopulator> getPopulators() {
        return this.populators;
    }

    @Override
    public Block getHighestBlockAt(int x, int z) {
        return this.getBlockAt(x, this.getHighestBlockYAt(x, z), z);
    }

    @Override
    public Block getHighestBlockAt(Location location) {
        return this.getHighestBlockAt(location.getBlockX(), location.getBlockZ());
    }

    @Override
    public int getHighestBlockYAt(int x, int z, HeightMap heightMap) {
        return this.world.m_6325_(x >> 4, z >> 4).m_5885_(CraftHeightMap.toNMS(heightMap), x, z);
    }

    @Override
    public Block getHighestBlockAt(int x, int z, HeightMap heightMap) {
        return this.getBlockAt(x, this.getHighestBlockYAt(x, z, heightMap), z);
    }

    @Override
    public Block getHighestBlockAt(Location location, HeightMap heightMap) {
        return this.getHighestBlockAt(location.getBlockX(), location.getBlockZ(), heightMap);
    }

    @Override
    public org.bukkit.block.Biome getBiome(int x, int z) {
        return this.getBiome(x, 0, z);
    }

    @Override
    public void setBiome(int x, int z, org.bukkit.block.Biome bio) {
        int y = this.getMinHeight();
        while (y < this.getMaxHeight()) {
            this.setBiome(x, y, z, bio);
            ++y;
        }
    }

    @Override
    public void setBiome(int x, int y, int z, Holder<Biome> bb) {
        LevelChunk chunk;
        BlockPos pos = new BlockPos(x, 0, z);
        if (this.world.m_46805_(pos) && (chunk = this.world.m_46745_(pos)) != null) {
            chunk.setBiome(x >> 2, y >> 2, z >> 2, bb);
            chunk.m_8092_(true);
        }
    }

    @Override
    public double getTemperature(int x, int z) {
        return this.getTemperature(x, 0, z);
    }

    @Override
    public double getTemperature(int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        return ((Biome)this.world.m_203495_(x >> 2, y >> 2, z >> 2).m_203334_()).m_47505_(pos);
    }

    @Override
    public double getHumidity(int x, int z) {
        return this.getHumidity(x, 0, z);
    }

    @Override
    public double getHumidity(int x, int y, int z) {
        return ((Biome)this.world.m_203495_((int)(x >> 2), (int)(y >> 2), (int)(z >> 2)).m_203334_()).f_47437_.f_47683_();
    }

    @Override
    @Deprecated
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> ... classes) {
        return this.getEntitiesByClasses(classes);
    }

    @Override
    public Iterable<net.minecraft.world.entity.Entity> getNMSEntities() {
        return this.getHandle().m_142646_().m_142273_();
    }

    @Override
    public void addEntityToWorld(net.minecraft.world.entity.Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        this.getHandle().addFreshEntity(entity, reason);
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
        return this.getNearbyEntities(location, x, y, z, null);
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z, Predicate<Entity> filter) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((boolean)this.equals(location.getWorld()), (Object)"Location cannot be in a different world");
        BoundingBox aabb = BoundingBox.of(location, x, y, z);
        return this.getNearbyEntities(aabb, filter);
    }

    @Override
    public Collection<Entity> getNearbyEntities(BoundingBox boundingBox) {
        return this.getNearbyEntities(boundingBox, null);
    }

    @Override
    public Collection<Entity> getNearbyEntities(BoundingBox boundingBox, Predicate<Entity> filter) {
        AsyncCatcher.catchOp("getNearbyEntities");
        Preconditions.checkArgument((boundingBox != null ? 1 : 0) != 0, (Object)"BoundingBox cannot be null");
        AABB bb = new AABB(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ(), boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ());
        List entityList = this.getHandle().m_6249_(null, bb, (Predicate)Predicates.alwaysTrue());
        ArrayList<Entity> bukkitEntityList = new ArrayList<Entity>(entityList.size());
        for (net.minecraft.world.entity.Entity entity : entityList) {
            CraftEntity bukkitEntity = entity.getBukkitEntity();
            if (filter != null && !filter.test(bukkitEntity)) continue;
            bukkitEntityList.add(bukkitEntity);
        }
        return bukkitEntityList;
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance) {
        return this.rayTraceEntities(start, direction, maxDistance, null);
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize) {
        return this.rayTraceEntities(start, direction, maxDistance, raySize, null);
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, Predicate<Entity> filter) {
        return this.rayTraceEntities(start, direction, maxDistance, 0.0, filter);
    }

    @Override
    public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize, Predicate<Entity> filter) {
        Preconditions.checkArgument((start != null ? 1 : 0) != 0, (Object)"Location start cannot be null");
        Preconditions.checkArgument((boolean)this.equals(start.getWorld()), (Object)"Location start cannot be in a different world");
        start.checkFinite();
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Vector direction cannot be null");
        direction.checkFinite();
        Preconditions.checkArgument((direction.lengthSquared() > 0.0 ? 1 : 0) != 0, (String)"Direction's magnitude (%s) need to be greater than 0", (Object)direction.lengthSquared());
        if (maxDistance < 0.0) {
            return null;
        }
        Vector startPos = start.toVector();
        Vector dir = direction.clone().normalize().multiply(maxDistance);
        BoundingBox aabb = BoundingBox.of(startPos, startPos).expandDirectional(dir).expand(raySize);
        Collection<Entity> entities = this.getNearbyEntities(aabb, filter);
        Entity nearestHitEntity = null;
        RayTraceResult nearestHitResult = null;
        double nearestDistanceSq = Double.MAX_VALUE;
        for (Entity entity : entities) {
            double distanceSq;
            BoundingBox boundingBox = entity.getBoundingBox().expand(raySize);
            RayTraceResult hitResult = boundingBox.rayTrace(startPos, direction, maxDistance);
            if (hitResult == null || !((distanceSq = startPos.distanceSquared(hitResult.getHitPosition())) < nearestDistanceSq)) continue;
            nearestHitEntity = entity;
            nearestHitResult = hitResult;
            nearestDistanceSq = distanceSq;
        }
        return nearestHitEntity == null ? null : new RayTraceResult(nearestHitResult.getHitPosition(), nearestHitEntity, nearestHitResult.getHitBlockFace());
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance) {
        return this.rayTraceBlocks(start, direction, maxDistance, FluidCollisionMode.NEVER, false);
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
        return this.rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, false);
    }

    @Override
    public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks) {
        Preconditions.checkArgument((start != null ? 1 : 0) != 0, (Object)"Location start cannot be null");
        Preconditions.checkArgument((boolean)this.equals(start.getWorld()), (Object)"Location start cannot be in a different world");
        start.checkFinite();
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Vector direction cannot be null");
        direction.checkFinite();
        Preconditions.checkArgument((direction.lengthSquared() > 0.0 ? 1 : 0) != 0, (String)"Direction's magnitude (%s) need to be greater than 0", (Object)direction.lengthSquared());
        Preconditions.checkArgument((fluidCollisionMode != null ? 1 : 0) != 0, (Object)"FluidCollisionMode cannot be null");
        if (maxDistance < 0.0) {
            return null;
        }
        Vector dir = direction.clone().normalize().multiply(maxDistance);
        Vec3 startPos = CraftLocation.toVec3D(start);
        Vec3 endPos = startPos.m_82520_(dir.getX(), dir.getY(), dir.getZ());
        BlockHitResult nmsHitResult = this.getHandle().m_45547_(new ClipContext(startPos, endPos, ignorePassableBlocks ? ClipContext.Block.COLLIDER : ClipContext.Block.OUTLINE, CraftFluidCollisionMode.toNMS(fluidCollisionMode), null));
        return CraftRayTraceResult.fromNMS(this, (HitResult)nmsHitResult);
    }

    @Override
    public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, Predicate<Entity> filter) {
        RayTraceResult blockHit = this.rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, ignorePassableBlocks);
        Vector startVec = null;
        double blockHitDistance = maxDistance;
        if (blockHit != null) {
            startVec = start.toVector();
            blockHitDistance = startVec.distance(blockHit.getHitPosition());
        }
        RayTraceResult entityHit = this.rayTraceEntities(start, direction, blockHitDistance, raySize, filter);
        if (blockHit == null) {
            return entityHit;
        }
        if (entityHit == null) {
            return blockHit;
        }
        double entityHitDistanceSquared = startVec.distanceSquared(entityHit.getHitPosition());
        if (entityHitDistanceSquared < blockHitDistance * blockHitDistance) {
            return entityHit;
        }
        return blockHit;
    }

    @Override
    public List<org.bukkit.entity.Player> getPlayers() {
        ArrayList<org.bukkit.entity.Player> list = new ArrayList<org.bukkit.entity.Player>(this.world.m_6907_().size());
        for (Player human : this.world.m_6907_()) {
            CraftHumanEntity bukkitEntity = human.getBukkitEntity();
            if (bukkitEntity == null || !(bukkitEntity instanceof org.bukkit.entity.Player)) continue;
            list.add((org.bukkit.entity.Player)((Object)bukkitEntity));
        }
        return list;
    }

    @Override
    public void save() {
        AsyncCatcher.catchOp("world save");
        this.server.checkSaveState();
        boolean oldSave = this.world.f_8564_;
        this.world.f_8564_ = false;
        this.world.m_8643_(null, false, false);
        this.world.f_8564_ = oldSave;
    }

    @Override
    public boolean isAutoSave() {
        return !this.world.f_8564_;
    }

    @Override
    public void setAutoSave(boolean value) {
        this.world.f_8564_ = !value;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        this.getHandle().K.m_6166_(net.minecraft.world.Difficulty.m_19029_((int)difficulty.getValue()));
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.getByValue(this.getHandle().m_46791_().ordinal());
    }

    public BlockMetadataStore getBlockMetadata() {
        return this.blockMetadata;
    }

    @Override
    public boolean hasStorm() {
        return this.world.f_46442_.m_6533_();
    }

    @Override
    public void setStorm(boolean hasStorm) {
        this.world.f_46442_.m_5565_(hasStorm);
        this.setWeatherDuration(0);
        this.setClearWeatherDuration(0);
    }

    @Override
    public int getWeatherDuration() {
        return this.world.K.m_6531_();
    }

    @Override
    public void setWeatherDuration(int duration) {
        this.world.K.m_6399_(duration);
    }

    @Override
    public boolean isThundering() {
        return this.world.f_46442_.m_6534_();
    }

    @Override
    public void setThundering(boolean thundering) {
        this.world.K.m_5557_(thundering);
        this.setThunderDuration(0);
        this.setClearWeatherDuration(0);
    }

    @Override
    public int getThunderDuration() {
        return this.world.K.m_6558_();
    }

    @Override
    public void setThunderDuration(int duration) {
        this.world.K.m_6398_(duration);
    }

    @Override
    public boolean isClearWeather() {
        return !this.hasStorm() && !this.isThundering();
    }

    @Override
    public void setClearWeatherDuration(int duration) {
        this.world.K.m_6393_(duration);
    }

    @Override
    public int getClearWeatherDuration() {
        return this.world.K.m_6537_();
    }

    @Override
    public long getSeed() {
        return this.world.m_7328_();
    }

    @Override
    public boolean getPVP() {
        return this.world.pvpMode;
    }

    @Override
    public void setPVP(boolean pvp) {
        this.world.pvpMode = pvp;
    }

    public void playEffect(org.bukkit.entity.Player player, Effect effect, int data) {
        this.playEffect(player.getLocation(), effect, data, 0);
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {
        this.playEffect(location, effect, data, 64);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        this.playEffect(loc, effect, data, 64);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data, int radius) {
        if (data != null) {
            Preconditions.checkArgument((effect.getData() != null ? 1 : 0) != 0, (String)"Effect.%s does not have a valid Data", (Object)((Object)effect));
            Preconditions.checkArgument((boolean)effect.getData().isAssignableFrom(data.getClass()), (String)"%s data cannot be used for the %s effect", (Object)data.getClass().getName(), (Object)((Object)effect));
        } else {
            Preconditions.checkArgument((effect.getData() == null || effect == Effect.ELECTRIC_SPARK ? 1 : 0) != 0, (String)"Wrong kind of data for the %s effect", (Object)((Object)effect));
        }
        int datavalue = CraftEffect.getDataValue(effect, data);
        this.playEffect(loc, effect, datavalue, radius);
    }

    @Override
    public void playEffect(Location location, Effect effect, int data, int radius) {
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"Effect cannot be null");
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((location.getWorld() != null ? 1 : 0) != 0, (Object)"World of Location cannot be null");
        int packetData = effect.getId();
        ClientboundLevelEventPacket packet = new ClientboundLevelEventPacket(packetData, CraftLocation.toBlockPosition(location), data, false);
        radius *= radius;
        for (org.bukkit.entity.Player player : this.getPlayers()) {
            int distance;
            if (((CraftPlayer)player).getHandle().f_8906_ == null || !location.getWorld().equals(player.getWorld()) || (distance = (int)player.getLocation().distanceSquared(location)) > radius) continue;
            ((CraftPlayer)player).getHandle().f_8906_.m_9829_((Packet)packet);
        }
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, MaterialData data) throws IllegalArgumentException {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"MaterialData cannot be null");
        return this.spawnFallingBlock(location, data.getItemType(), data.getData());
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte data) throws IllegalArgumentException {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)material.isBlock(), (String)"Material.%s must be a block", (Object)material);
        FallingBlockEntity entity = FallingBlockEntity.fall((Level)this.world, (BlockPos)BlockPos.m_274561_((double)location.getX(), (double)location.getY(), (double)location.getZ()), (BlockState)CraftMagicNumbers.getBlock(material).m_49966_(), (CreatureSpawnEvent.SpawnReason)CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (FallingBlock)((Object)entity.getBukkitEntity());
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, BlockData data) throws IllegalArgumentException {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"BlockData cannot be null");
        FallingBlockEntity entity = FallingBlockEntity.fall((Level)this.world, (BlockPos)BlockPos.m_274561_((double)location.getX(), (double)location.getY(), (double)location.getZ()), (BlockState)((CraftBlockData)data).getState(), (CreatureSpawnEvent.SpawnReason)CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (FallingBlock)((Object)entity.getBukkitEntity());
    }

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
        return CraftChunk.getEmptyChunkSnapshot(x, z, this, includeBiome, includeBiomeTempRain);
    }

    @Override
    public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {
        this.world.m_46703_(allowMonsters, allowAnimals);
    }

    @Override
    public boolean getAllowAnimals() {
        return this.world.m_7726_().f_8336_;
    }

    @Override
    public boolean getAllowMonsters() {
        return this.world.m_7726_().f_8335_;
    }

    @Override
    public int getMinHeight() {
        return this.world.m_141937_();
    }

    @Override
    public int getMaxHeight() {
        return this.world.m_151558_();
    }

    @Override
    public int getLogicalHeight() {
        return this.world.m_6042_().f_63865_();
    }

    @Override
    public boolean isNatural() {
        return this.world.m_6042_().f_63858_();
    }

    @Override
    public boolean isBedWorks() {
        return this.world.m_6042_().f_63862_();
    }

    @Override
    public boolean hasSkyLight() {
        return this.world.m_6042_().f_223549_();
    }

    @Override
    public boolean hasCeiling() {
        return this.world.m_6042_().f_63856_();
    }

    @Override
    public boolean isPiglinSafe() {
        return this.world.m_6042_().m_63960_();
    }

    @Override
    public boolean isRespawnAnchorWorks() {
        return this.world.m_6042_().f_63863_();
    }

    @Override
    public boolean hasRaids() {
        return this.world.m_6042_().m_63963_();
    }

    @Override
    public boolean isUltraWarm() {
        return this.world.m_6042_().f_63857_();
    }

    @Override
    public int getSeaLevel() {
        return this.world.m_5736_();
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return this.world.keepSpawnInMemory;
    }

    @Override
    public void setKeepSpawnInMemory(boolean keepLoaded) {
        this.world.keepSpawnInMemory = keepLoaded;
        BlockPos chunkcoordinates = this.world.m_220360_();
        if (keepLoaded) {
            this.world.m_7726_().m_8387_(TicketType.f_9442_, new ChunkPos(chunkcoordinates), 11, (Object)Unit.INSTANCE);
        } else {
            this.world.m_7726_().m_8438_(TicketType.f_9442_, new ChunkPos(chunkcoordinates), 11, (Object)Unit.INSTANCE);
        }
    }

    public int hashCode() {
        return this.getUID().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftWorld other = (CraftWorld)obj;
        return this.getUID() == other.getUID();
    }

    @Override
    public File getWorldFolder() {
        return this.world.convertable.m_78283_(LevelResource.f_78182_).toFile().getParentFile();
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message);
        for (org.bukkit.entity.Player player : this.getPlayers()) {
            player.sendPluginMessage(source, channel, message);
        }
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        HashSet<String> result = new HashSet<String>();
        for (org.bukkit.entity.Player player : this.getPlayers()) {
            result.addAll(player.getListeningPluginChannels());
        }
        return result;
    }

    @Override
    public WorldType getWorldType() {
        return this.world.m_8584_() ? WorldType.FLAT : WorldType.NORMAL;
    }

    @Override
    public boolean canGenerateStructures() {
        return this.world.K.m_246337_().m_247749_();
    }

    @Override
    public boolean isHardcore() {
        return this.world.m_6106_().m_5466_();
    }

    @Override
    public void setHardcore(boolean hardcore) {
        this.world.K.f_78443_.f_46904_ = hardcore;
    }

    @Override
    @Deprecated
    public long getTicksPerAnimalSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.ANIMAL);
    }

    @Override
    @Deprecated
    public void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {
        this.setTicksPerSpawns(SpawnCategory.ANIMAL, ticksPerAnimalSpawns);
    }

    @Override
    @Deprecated
    public long getTicksPerMonsterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.MONSTER);
    }

    @Override
    @Deprecated
    public void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {
        this.setTicksPerSpawns(SpawnCategory.MONSTER, ticksPerMonsterSpawns);
    }

    @Override
    @Deprecated
    public long getTicksPerWaterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_ANIMAL);
    }

    @Override
    @Deprecated
    public void setTicksPerWaterSpawns(int ticksPerWaterSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_ANIMAL, ticksPerWaterSpawns);
    }

    @Override
    @Deprecated
    public long getTicksPerWaterAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_AMBIENT);
    }

    @Override
    @Deprecated
    public void setTicksPerWaterAmbientSpawns(int ticksPerWaterAmbientSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_AMBIENT, ticksPerWaterAmbientSpawns);
    }

    @Override
    @Deprecated
    public long getTicksPerWaterUndergroundCreatureSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    @Override
    @Deprecated
    public void setTicksPerWaterUndergroundCreatureSpawns(int ticksPerWaterUndergroundCreatureSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE, ticksPerWaterUndergroundCreatureSpawns);
    }

    @Override
    @Deprecated
    public long getTicksPerAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.AMBIENT);
    }

    @Override
    @Deprecated
    public void setTicksPerAmbientSpawns(int ticksPerAmbientSpawns) {
        this.setTicksPerSpawns(SpawnCategory.AMBIENT, ticksPerAmbientSpawns);
    }

    @Override
    public void setTicksPerSpawns(SpawnCategory spawnCategory, int ticksPerCategorySpawn) {
        Preconditions.checkArgument((spawnCategory != null ? 1 : 0) != 0, (Object)"SpawnCategory cannot be null");
        Preconditions.checkArgument((boolean)CraftSpawnCategory.isValidForLimits(spawnCategory), (String)"SpawnCategory.%s are not supported", (Object)((Object)spawnCategory));
        this.world.ticksPerSpawnCategory.put((Object)spawnCategory, (long)ticksPerCategorySpawn);
    }

    @Override
    public long getTicksPerSpawns(SpawnCategory spawnCategory) {
        Preconditions.checkArgument((spawnCategory != null ? 1 : 0) != 0, (Object)"SpawnCategory cannot be null");
        Preconditions.checkArgument((boolean)CraftSpawnCategory.isValidForLimits(spawnCategory), (String)"SpawnCategory.%s are not supported", (Object)((Object)spawnCategory));
        return this.world.ticksPerSpawnCategory.getLong((Object)spawnCategory);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.server.getWorldMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.server.getWorldMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.server.getWorldMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.server.getWorldMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    @Deprecated
    public int getMonsterSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.MONSTER);
    }

    @Override
    @Deprecated
    public void setMonsterSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.MONSTER, limit);
    }

    @Override
    @Deprecated
    public int getAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.ANIMAL);
    }

    @Override
    @Deprecated
    public void setAnimalSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.ANIMAL, limit);
    }

    @Override
    @Deprecated
    public int getWaterAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_ANIMAL);
    }

    @Override
    @Deprecated
    public void setWaterAnimalSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_ANIMAL, limit);
    }

    @Override
    @Deprecated
    public int getWaterAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_AMBIENT);
    }

    @Override
    @Deprecated
    public void setWaterAmbientSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_AMBIENT, limit);
    }

    @Override
    @Deprecated
    public int getWaterUndergroundCreatureSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    @Override
    @Deprecated
    public void setWaterUndergroundCreatureSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE, limit);
    }

    @Override
    @Deprecated
    public int getAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.AMBIENT);
    }

    @Override
    @Deprecated
    public void setAmbientSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.AMBIENT, limit);
    }

    @Override
    public int getSpawnLimit(SpawnCategory spawnCategory) {
        Preconditions.checkArgument((spawnCategory != null ? 1 : 0) != 0, (Object)"SpawnCategory cannot be null");
        Preconditions.checkArgument((boolean)CraftSpawnCategory.isValidForLimits(spawnCategory), (String)"SpawnCategory.%s are not supported", (Object)((Object)spawnCategory));
        int limit = this.spawnCategoryLimit.getOrDefault((Object)spawnCategory, -1);
        if (limit < 0) {
            limit = this.server.getSpawnLimit(spawnCategory);
        }
        return limit;
    }

    @Override
    public void setSpawnLimit(SpawnCategory spawnCategory, int limit) {
        Preconditions.checkArgument((spawnCategory != null ? 1 : 0) != 0, (Object)"SpawnCategory cannot be null");
        Preconditions.checkArgument((boolean)CraftSpawnCategory.isValidForLimits(spawnCategory), (String)"SpawnCategory.%s are not supported", (Object)((Object)spawnCategory));
        this.spawnCategoryLimit.put((Object)spawnCategory, limit);
    }

    @Override
    public void playSound(Location loc, Sound sound, float volume, float pitch) {
        this.playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Location loc, String sound, float volume, float pitch) {
        this.playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Location loc, Sound sound, SoundCategory category, float volume, float pitch) {
        if (loc == null || sound == null || category == null) {
            return;
        }
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        this.getHandle().m_6263_(null, x, y, z, CraftSound.getSoundEffect(sound), SoundSource.valueOf((String)category.name()), volume, pitch);
    }

    @Override
    public void playSound(Location loc, String sound, SoundCategory category, float volume, float pitch) {
        if (loc == null || sound == null || category == null) {
            return;
        }
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        ClientboundSoundPacket packet = new ClientboundSoundPacket(Holder.m_205709_((Object)SoundEvent.m_262824_((ResourceLocation)new ResourceLocation(sound))), SoundSource.valueOf((String)category.name()), x, y, z, volume, pitch, this.getHandle().m_213780_().m_188505_());
        this.world.m_7654_().m_6846_().m_11241_(null, x, y, z, volume > 1.0f ? (double)(16.0f * volume) : 16.0, this.world.m_46472_(), (Packet)packet);
    }

    @Override
    public void playSound(Entity entity, Sound sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Entity entity, String sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch) {
        void craftEntity;
        CraftEntity craftEntity2;
        Entity entity2 = entity;
        if (!(entity2 instanceof CraftEntity) || (craftEntity2 = (CraftEntity)entity2) != (CraftEntity)entity2 || entity.getWorld() != this || sound == null || category == null) {
            return;
        }
        ClientboundSoundEntityPacket packet = new ClientboundSoundEntityPacket(BuiltInRegistries.f_256894_.m_263177_((Object)CraftSound.getSoundEffect(sound)), SoundSource.valueOf((String)category.name()), craftEntity.getHandle(), volume, pitch, this.getHandle().m_213780_().m_188505_());
        ChunkMap.TrackedEntity entityTracker = (ChunkMap.TrackedEntity)this.getHandle().m_7726_().f_8325_.f_140150_.get(entity.getEntityId());
        if (entityTracker != null) {
            entityTracker.m_140499_((Packet)packet);
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch) {
        void craftEntity;
        CraftEntity craftEntity2;
        Entity entity2 = entity;
        if (!(entity2 instanceof CraftEntity) || (craftEntity2 = (CraftEntity)entity2) != (CraftEntity)entity2 || entity.getWorld() != this || sound == null || category == null) {
            return;
        }
        ClientboundSoundEntityPacket packet = new ClientboundSoundEntityPacket(Holder.m_205709_((Object)SoundEvent.m_262824_((ResourceLocation)new ResourceLocation(sound))), SoundSource.valueOf((String)category.name()), craftEntity.getHandle(), volume, pitch, this.getHandle().m_213780_().m_188505_());
        ChunkMap.TrackedEntity entityTracker = (ChunkMap.TrackedEntity)this.getHandle().m_7726_().f_8325_.f_140150_.get(entity.getEntityId());
        if (entityTracker != null) {
            entityTracker.m_140499_((Packet)packet);
        }
    }

    public static synchronized Map<String, GameRules.Key<?>> getGameRulesNMS() {
        if (gamerules != null) {
            return gamerules;
        }
        final HashMap gamerules = new HashMap();
        GameRules.m_46164_((GameRules.GameRuleTypeVisitor)new GameRules.GameRuleTypeVisitor(){

            public <T extends GameRules.Value<T>> void m_6889_(GameRules.Key<T> gamerules_gamerulekey, GameRules.Type<T> gamerules_gameruledefinition) {
                gamerules.put(gamerules_gamerulekey.m_46328_(), gamerules_gamerulekey);
            }
        });
        CraftWorld.gamerules = gamerules;
        return CraftWorld.gamerules;
    }

    public static synchronized Map<String, GameRules.Type<?>> getGameRuleDefinitions() {
        if (gameruleDefinitions != null) {
            return gameruleDefinitions;
        }
        final HashMap gameruleDefinitions = new HashMap();
        GameRules.m_46164_((GameRules.GameRuleTypeVisitor)new GameRules.GameRuleTypeVisitor(){

            public <T extends GameRules.Value<T>> void m_6889_(GameRules.Key<T> gamerules_gamerulekey, GameRules.Type<T> gamerules_gameruledefinition) {
                gameruleDefinitions.put(gamerules_gamerulekey.m_46328_(), gamerules_gameruledefinition);
            }
        });
        CraftWorld.gameruleDefinitions = gameruleDefinitions;
        return CraftWorld.gameruleDefinitions;
    }

    @Override
    public String getGameRuleValue(String rule) {
        if (rule == null) {
            return null;
        }
        GameRules.Value value = this.getHandle().m_46469_().m_46170_(CraftWorld.getGameRulesNMS().get(rule));
        return value != null ? value.toString() : "";
    }

    @Override
    public boolean setGameRuleValue(String rule, String value) {
        if (rule == null || value == null) {
            return false;
        }
        if (!this.isGameRule(rule)) {
            return false;
        }
        GameRules.Value handle = this.getHandle().m_46469_().m_46170_(CraftWorld.getGameRulesNMS().get(rule));
        handle.m_7377_(value);
        handle.m_46368_(this.getHandle().m_7654_());
        return true;
    }

    @Override
    public String[] getGameRules() {
        return CraftWorld.getGameRulesNMS().keySet().toArray(new String[CraftWorld.getGameRulesNMS().size()]);
    }

    @Override
    public boolean isGameRule(String rule) {
        Preconditions.checkArgument((rule != null ? 1 : 0) != 0, (Object)"String rule cannot be null");
        Preconditions.checkArgument((!rule.isEmpty() ? 1 : 0) != 0, (Object)"String rule cannot be empty");
        return CraftWorld.getGameRulesNMS().containsKey(rule);
    }

    @Override
    public <T> T getGameRuleValue(GameRule<T> rule) {
        Preconditions.checkArgument((rule != null ? 1 : 0) != 0, (Object)"GameRule cannot be null");
        return this.convert(rule, this.getHandle().m_46469_().m_46170_(CraftWorld.getGameRulesNMS().get(rule.getName())));
    }

    @Override
    public <T> T getGameRuleDefault(GameRule<T> rule) {
        Preconditions.checkArgument((rule != null ? 1 : 0) != 0, (Object)"GameRule cannot be null");
        return this.convert(rule, CraftWorld.getGameRuleDefinitions().get(rule.getName()).m_46352_());
    }

    @Override
    public <T> boolean setGameRule(GameRule<T> rule, T newValue) {
        Preconditions.checkArgument((rule != null ? 1 : 0) != 0, (Object)"GameRule cannot be null");
        Preconditions.checkArgument((newValue != null ? 1 : 0) != 0, (Object)"GameRule value cannot be null");
        if (!this.isGameRule(rule.getName())) {
            return false;
        }
        GameRules.Value handle = this.getHandle().m_46469_().m_46170_(CraftWorld.getGameRulesNMS().get(rule.getName()));
        handle.m_7377_(newValue.toString());
        handle.m_46368_(this.getHandle().m_7654_());
        return true;
    }

    private <T> T convert(GameRule<T> rule, GameRules.Value<?> value) {
        if (value == null) {
            return null;
        }
        if (value instanceof GameRules.BooleanValue) {
            return rule.getType().cast(((GameRules.BooleanValue)value).m_46223_());
        }
        if (value instanceof GameRules.IntegerValue) {
            return rule.getType().cast(value.m_6855_());
        }
        throw new IllegalArgumentException("Invalid GameRule type (" + value + ") for GameRule " + rule.getName());
    }

    @Override
    public WorldBorder getWorldBorder() {
        if (this.worldBorder == null) {
            this.worldBorder = new CraftWorldBorder(this);
        }
        return this.worldBorder;
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
        this.spawnParticle(particle, x, y, z, count, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
        this.spawnParticle(particle, x, y, z, count, 0.0, 0.0, 0.0, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1.0, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, false);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data, force);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
        if (data != null) {
            Preconditions.checkArgument((boolean)particle.getDataType().isInstance(data), (String)"data (%s) should be %s", data.getClass(), particle.getDataType());
        }
        this.getHandle().sendParticles(null, CraftParticle.toNMS(particle, data), x, y, z, count, offsetX, offsetY, offsetZ, extra, force);
    }

    @Override
    @Deprecated
    public Location locateNearestStructure(Location origin, StructureType structureType, int radius, boolean findUnexplored) {
        StructureSearchResult result = null;
        if (StructureType.MINESHAFT == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.MINESHAFT, radius, findUnexplored);
        } else if (StructureType.VILLAGE == structureType) {
            result = this.locateNearestStructure(origin, List.of(Structure.VILLAGE_DESERT, Structure.VILLAGE_PLAINS, Structure.VILLAGE_SAVANNA, Structure.VILLAGE_SNOWY, Structure.VILLAGE_TAIGA), radius, findUnexplored);
        } else if (StructureType.NETHER_FORTRESS == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.FORTRESS, radius, findUnexplored);
        } else if (StructureType.STRONGHOLD == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.STRONGHOLD, radius, findUnexplored);
        } else if (StructureType.JUNGLE_PYRAMID == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.JUNGLE_TEMPLE, radius, findUnexplored);
        } else if (StructureType.OCEAN_RUIN == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.OCEAN_RUIN, radius, findUnexplored);
        } else if (StructureType.DESERT_PYRAMID == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.DESERT_PYRAMID, radius, findUnexplored);
        } else if (StructureType.IGLOO == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.IGLOO, radius, findUnexplored);
        } else if (StructureType.SWAMP_HUT == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.SWAMP_HUT, radius, findUnexplored);
        } else if (StructureType.OCEAN_MONUMENT == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.OCEAN_MONUMENT, radius, findUnexplored);
        } else if (StructureType.END_CITY == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.END_CITY, radius, findUnexplored);
        } else if (StructureType.WOODLAND_MANSION == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.WOODLAND_MANSION, radius, findUnexplored);
        } else if (StructureType.BURIED_TREASURE == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.BURIED_TREASURE, radius, findUnexplored);
        } else if (StructureType.SHIPWRECK == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.SHIPWRECK, radius, findUnexplored);
        } else if (StructureType.PILLAGER_OUTPOST == structureType) {
            result = this.locateNearestStructure(origin, Structure.PILLAGER_OUTPOST, radius, findUnexplored);
        } else if (StructureType.NETHER_FOSSIL == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.NETHER_FOSSIL, radius, findUnexplored);
        } else if (StructureType.RUINED_PORTAL == structureType) {
            result = this.locateNearestStructure(origin, org.bukkit.generator.structure.StructureType.RUINED_PORTAL, radius, findUnexplored);
        } else if (StructureType.BASTION_REMNANT == structureType) {
            result = this.locateNearestStructure(origin, Structure.BASTION_REMNANT, radius, findUnexplored);
        }
        return result == null ? null : result.getLocation();
    }

    @Override
    public StructureSearchResult locateNearestStructure(Location origin, org.bukkit.generator.structure.StructureType structureType, int radius, boolean findUnexplored) {
        ArrayList<Structure> structures = new ArrayList<Structure>();
        for (Structure structure : Registry.STRUCTURE) {
            if (structure.getStructureType() != structureType) continue;
            structures.add(structure);
        }
        return this.locateNearestStructure(origin, structures, radius, findUnexplored);
    }

    @Override
    public StructureSearchResult locateNearestStructure(Location origin, Structure structure, int radius, boolean findUnexplored) {
        return this.locateNearestStructure(origin, List.of(structure), radius, findUnexplored);
    }

    public StructureSearchResult locateNearestStructure(Location origin, List<Structure> structures, int radius, boolean findUnexplored) {
        BlockPos originPos = BlockPos.m_274561_((double)origin.getX(), (double)origin.getY(), (double)origin.getZ());
        ArrayList<Holder> holders = new ArrayList<Holder>();
        for (Structure structure : structures) {
            holders.add(Holder.m_205709_((Object)CraftStructure.bukkitToMinecraft(structure)));
        }
        Pair found = this.getHandle().m_7726_().m_8481_().m_223037_(this.getHandle(), (HolderSet)HolderSet.m_205800_(holders), originPos, radius, findUnexplored);
        if (found == null) {
            return null;
        }
        return new CraftStructureSearchResult(CraftStructure.minecraftToBukkit((net.minecraft.world.level.levelgen.structure.Structure)((Holder)found.getSecond()).m_203334_(), this.getHandle().m_9598_()), CraftLocation.toBukkit((BlockPos)found.getFirst(), (World)this));
    }

    @Override
    public Raid locateNearestRaid(Location location, int radius) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((radius >= 0 ? 1 : 0) != 0, (String)"Radius value (%s) cannot be negative", (int)radius);
        Raids persistentRaid = this.world.m_8905_();
        net.minecraft.world.entity.raid.Raid raid = persistentRaid.m_37970_(CraftLocation.toBlockPosition(location), radius * radius);
        return raid == null ? null : new CraftRaid(raid);
    }

    @Override
    public List<Raid> getRaids() {
        Raids persistentRaid = this.world.m_8905_();
        return persistentRaid.f_37951_.values().stream().map(CraftRaid::new).collect(Collectors.toList());
    }

    @Override
    public DragonBattle getEnderDragonBattle() {
        return this.getHandle().m_8586_() == null ? null : new CraftDragonBattle(this.getHandle().m_8586_());
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return this.persistentDataContainer;
    }

    @Override
    public Set<FeatureFlag> getFeatureFlags() {
        return CraftFeatureFlag.getFromNMS(this.getHandle().m_246046_()).stream().map(FeatureFlag.class::cast).collect(Collectors.toUnmodifiableSet());
    }

    public void storeBukkitValues(CompoundTag c) {
        if (!this.persistentDataContainer.isEmpty()) {
            c.m_128365_("BukkitValues", (Tag)this.persistentDataContainer.toTagCompound());
        }
    }

    public void readBukkitValues(Tag c) {
        if (c instanceof CompoundTag) {
            this.persistentDataContainer.putAll((CompoundTag)c);
        }
    }

    @Override
    public int getViewDistance() {
        return this.world.spigotConfig.viewDistance;
    }

    @Override
    public int getSimulationDistance() {
        return this.world.spigotConfig.simulationDistance;
    }

    @Override
    public World.Spigot spigot() {
        return this.spigot;
    }
}

