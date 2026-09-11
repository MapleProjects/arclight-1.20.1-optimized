/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.bukkit.BlockChangeDelegate;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.FeatureFlag;
import org.bukkit.FluidCollisionMode;
import org.bukkit.GameRule;
import org.bukkit.HeightMap;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Raid;
import org.bukkit.RegionAccessor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.StructureType;
import org.bukkit.TreeType;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface World
extends RegionAccessor,
WorldInfo,
PluginMessageRecipient,
Metadatable,
PersistentDataHolder,
Keyed {
    @NotNull
    public Block getBlockAt(int var1, int var2, int var3);

    @NotNull
    public Block getBlockAt(@NotNull Location var1);

    @NotNull
    public Block getHighestBlockAt(int var1, int var2);

    @NotNull
    public Block getHighestBlockAt(@NotNull Location var1);

    @NotNull
    public Block getHighestBlockAt(int var1, int var2, @NotNull HeightMap var3);

    @NotNull
    public Block getHighestBlockAt(@NotNull Location var1, @NotNull HeightMap var2);

    @NotNull
    public Chunk getChunkAt(int var1, int var2);

    @NotNull
    public Chunk getChunkAt(int var1, int var2, boolean var3);

    @NotNull
    public Chunk getChunkAt(@NotNull Location var1);

    @NotNull
    public Chunk getChunkAt(@NotNull Block var1);

    public boolean isChunkLoaded(@NotNull Chunk var1);

    @NotNull
    public Chunk[] getLoadedChunks();

    public void loadChunk(@NotNull Chunk var1);

    public boolean isChunkLoaded(int var1, int var2);

    public boolean isChunkGenerated(int var1, int var2);

    @Deprecated
    public boolean isChunkInUse(int var1, int var2);

    public void loadChunk(int var1, int var2);

    public boolean loadChunk(int var1, int var2, boolean var3);

    public boolean unloadChunk(@NotNull Chunk var1);

    public boolean unloadChunk(int var1, int var2);

    public boolean unloadChunk(int var1, int var2, boolean var3);

    public boolean unloadChunkRequest(int var1, int var2);

    @Deprecated
    public boolean regenerateChunk(int var1, int var2);

    @Deprecated
    public boolean refreshChunk(int var1, int var2);

    public boolean isChunkForceLoaded(int var1, int var2);

    public void setChunkForceLoaded(int var1, int var2, boolean var3);

    @NotNull
    public Collection<Chunk> getForceLoadedChunks();

    public boolean addPluginChunkTicket(int var1, int var2, @NotNull Plugin var3);

    public boolean removePluginChunkTicket(int var1, int var2, @NotNull Plugin var3);

    public void removePluginChunkTickets(@NotNull Plugin var1);

    @NotNull
    public Collection<Plugin> getPluginChunkTickets(int var1, int var2);

    @NotNull
    public Map<Plugin, Collection<Chunk>> getPluginChunkTickets();

    @NotNull
    public Item dropItem(@NotNull Location var1, @NotNull ItemStack var2);

    @NotNull
    public Item dropItem(@NotNull Location var1, @NotNull ItemStack var2, @Nullable Consumer<Item> var3);

    @NotNull
    public Item dropItemNaturally(@NotNull Location var1, @NotNull ItemStack var2);

    @NotNull
    public Item dropItemNaturally(@NotNull Location var1, @NotNull ItemStack var2, @Nullable Consumer<Item> var3);

    @NotNull
    public Arrow spawnArrow(@NotNull Location var1, @NotNull Vector var2, float var3, float var4);

    @NotNull
    public <T extends AbstractArrow> T spawnArrow(@NotNull Location var1, @NotNull Vector var2, float var3, float var4, @NotNull Class<T> var5);

    public boolean generateTree(@NotNull Location var1, @NotNull TreeType var2);

    @Deprecated
    public boolean generateTree(@NotNull Location var1, @NotNull TreeType var2, @NotNull BlockChangeDelegate var3);

    @NotNull
    public LightningStrike strikeLightning(@NotNull Location var1);

    @NotNull
    public LightningStrike strikeLightningEffect(@NotNull Location var1);

    @Override
    @NotNull
    public List<Entity> getEntities();

    @Override
    @NotNull
    public List<LivingEntity> getLivingEntities();

    @Deprecated
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> ... var1);

    @Override
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> var1);

    @Override
    @NotNull
    public Collection<Entity> getEntitiesByClasses(Class<?> ... var1);

    @NotNull
    public List<Player> getPlayers();

    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull Location var1, double var2, double var4, double var6);

    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull Location var1, double var2, double var4, double var6, @Nullable Predicate<Entity> var8);

    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull BoundingBox var1);

    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull BoundingBox var1, @Nullable Predicate<Entity> var2);

    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3);

    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, double var5);

    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, @Nullable Predicate<Entity> var5);

    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, double var5, @Nullable Predicate<Entity> var7);

    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3);

    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5);

    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5, boolean var6);

    @Nullable
    public RayTraceResult rayTrace(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5, boolean var6, double var7, @Nullable Predicate<Entity> var9);

    @NotNull
    public Location getSpawnLocation();

    public boolean setSpawnLocation(@NotNull Location var1);

    public boolean setSpawnLocation(int var1, int var2, int var3, float var4);

    public boolean setSpawnLocation(int var1, int var2, int var3);

    public long getTime();

    public void setTime(long var1);

    public long getFullTime();

    public void setFullTime(long var1);

    public long getGameTime();

    public boolean hasStorm();

    public void setStorm(boolean var1);

    public int getWeatherDuration();

    public void setWeatherDuration(int var1);

    public boolean isThundering();

    public void setThundering(boolean var1);

    public int getThunderDuration();

    public void setThunderDuration(int var1);

    public boolean isClearWeather();

    public void setClearWeatherDuration(int var1);

    public int getClearWeatherDuration();

    public boolean createExplosion(double var1, double var3, double var5, float var7);

    public boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8);

    public boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8, boolean var9);

    public boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8, boolean var9, @Nullable Entity var10);

    public boolean createExplosion(@NotNull Location var1, float var2);

    public boolean createExplosion(@NotNull Location var1, float var2, boolean var3);

    public boolean createExplosion(@NotNull Location var1, float var2, boolean var3, boolean var4);

    public boolean createExplosion(@NotNull Location var1, float var2, boolean var3, boolean var4, @Nullable Entity var5);

    public boolean getPVP();

    public void setPVP(boolean var1);

    @Nullable
    public ChunkGenerator getGenerator();

    @Nullable
    public BiomeProvider getBiomeProvider();

    public void save();

    @NotNull
    public List<BlockPopulator> getPopulators();

    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull MaterialData var2) throws IllegalArgumentException;

    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull BlockData var2) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull Material var2, byte var3) throws IllegalArgumentException;

    public void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3);

    public void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3, int var4);

    public <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3);

    public <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3, int var4);

    @NotNull
    public ChunkSnapshot getEmptyChunkSnapshot(int var1, int var2, boolean var3, boolean var4);

    public void setSpawnFlags(boolean var1, boolean var2);

    public boolean getAllowAnimals();

    public boolean getAllowMonsters();

    @Deprecated
    @NotNull
    public Biome getBiome(int var1, int var2);

    @Deprecated
    public void setBiome(int var1, int var2, @NotNull Biome var3);

    @Deprecated
    public double getTemperature(int var1, int var2);

    public double getTemperature(int var1, int var2, int var3);

    @Deprecated
    public double getHumidity(int var1, int var2);

    public double getHumidity(int var1, int var2, int var3);

    public int getLogicalHeight();

    public boolean isNatural();

    public boolean isBedWorks();

    public boolean hasSkyLight();

    public boolean hasCeiling();

    public boolean isPiglinSafe();

    public boolean isRespawnAnchorWorks();

    public boolean hasRaids();

    public boolean isUltraWarm();

    public int getSeaLevel();

    public boolean getKeepSpawnInMemory();

    public void setKeepSpawnInMemory(boolean var1);

    public boolean isAutoSave();

    public void setAutoSave(boolean var1);

    public void setDifficulty(@NotNull Difficulty var1);

    @NotNull
    public Difficulty getDifficulty();

    @NotNull
    public File getWorldFolder();

    @Deprecated
    @Nullable
    public WorldType getWorldType();

    public boolean canGenerateStructures();

    public boolean isHardcore();

    public void setHardcore(boolean var1);

    @Deprecated
    public long getTicksPerAnimalSpawns();

    @Deprecated
    public void setTicksPerAnimalSpawns(int var1);

    @Deprecated
    public long getTicksPerMonsterSpawns();

    @Deprecated
    public void setTicksPerMonsterSpawns(int var1);

    @Deprecated
    public long getTicksPerWaterSpawns();

    @Deprecated
    public void setTicksPerWaterSpawns(int var1);

    @Deprecated
    public long getTicksPerWaterAmbientSpawns();

    @Deprecated
    public void setTicksPerWaterAmbientSpawns(int var1);

    @Deprecated
    public long getTicksPerWaterUndergroundCreatureSpawns();

    @Deprecated
    public void setTicksPerWaterUndergroundCreatureSpawns(int var1);

    @Deprecated
    public long getTicksPerAmbientSpawns();

    @Deprecated
    public void setTicksPerAmbientSpawns(int var1);

    public long getTicksPerSpawns(@NotNull SpawnCategory var1);

    public void setTicksPerSpawns(@NotNull SpawnCategory var1, int var2);

    @Deprecated
    public int getMonsterSpawnLimit();

    @Deprecated
    public void setMonsterSpawnLimit(int var1);

    @Deprecated
    public int getAnimalSpawnLimit();

    @Deprecated
    public void setAnimalSpawnLimit(int var1);

    @Deprecated
    public int getWaterAnimalSpawnLimit();

    @Deprecated
    public void setWaterAnimalSpawnLimit(int var1);

    @Deprecated
    public int getWaterUndergroundCreatureSpawnLimit();

    @Deprecated
    public void setWaterUndergroundCreatureSpawnLimit(int var1);

    @Deprecated
    public int getWaterAmbientSpawnLimit();

    @Deprecated
    public void setWaterAmbientSpawnLimit(int var1);

    @Deprecated
    public int getAmbientSpawnLimit();

    @Deprecated
    public void setAmbientSpawnLimit(int var1);

    public int getSpawnLimit(@NotNull SpawnCategory var1);

    public void setSpawnLimit(@NotNull SpawnCategory var1, int var2);

    public void playSound(@NotNull Location var1, @NotNull Sound var2, float var3, float var4);

    public void playSound(@NotNull Location var1, @NotNull String var2, float var3, float var4);

    public void playSound(@NotNull Location var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Location var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Entity var1, @NotNull Sound var2, float var3, float var4);

    public void playSound(@NotNull Entity var1, @NotNull String var2, float var3, float var4);

    public void playSound(@NotNull Entity var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    public void playSound(@NotNull Entity var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    @NotNull
    public String[] getGameRules();

    @Deprecated
    @Contract(value="null -> null; !null -> !null")
    @Nullable
    public String getGameRuleValue(@Nullable String var1);

    @Deprecated
    public boolean setGameRuleValue(@NotNull String var1, @NotNull String var2);

    public boolean isGameRule(@NotNull String var1);

    @Nullable
    public <T> T getGameRuleValue(@NotNull GameRule<T> var1);

    @Nullable
    public <T> T getGameRuleDefault(@NotNull GameRule<T> var1);

    public <T> boolean setGameRule(@NotNull GameRule<T> var1, @NotNull T var2);

    @NotNull
    public WorldBorder getWorldBorder();

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, @Nullable T var4);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, @Nullable T var9);

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, @Nullable T var10);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, @Nullable T var15);

    public void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10);

    public void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10, @Nullable T var12);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, @Nullable T var17);

    public <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10, @Nullable T var12, boolean var13);

    public <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, @Nullable T var17, boolean var18);

    @Deprecated
    @Nullable
    public Location locateNearestStructure(@NotNull Location var1, @NotNull StructureType var2, int var3, boolean var4);

    @Nullable
    public StructureSearchResult locateNearestStructure(@NotNull Location var1, @NotNull org.bukkit.generator.structure.StructureType var2, int var3, boolean var4);

    @Nullable
    public StructureSearchResult locateNearestStructure(@NotNull Location var1, @NotNull Structure var2, int var3, boolean var4);

    public int getViewDistance();

    public int getSimulationDistance();

    @NotNull
    public Spigot spigot();

    @Nullable
    public Raid locateNearestRaid(@NotNull Location var1, int var2);

    @NotNull
    public List<Raid> getRaids();

    @Nullable
    public DragonBattle getEnderDragonBattle();

    @NotNull
    public Set<FeatureFlag> getFeatureFlags();

    public static enum Environment {
        NORMAL(0),
        NETHER(-1),
        THE_END(1),
        CUSTOM(-999);

        private final int id;
        private static final Map<Integer, Environment> lookup;

        static {
            lookup = new HashMap<Integer, Environment>();
            Environment[] environmentArray = Environment.values();
            int n = environmentArray.length;
            int n2 = 0;
            while (n2 < n) {
                Environment env = environmentArray[n2];
                lookup.put(env.getId(), env);
                ++n2;
            }
        }

        private Environment(int id) {
            this.id = id;
        }

        @Deprecated
        public int getId() {
            return this.id;
        }

        @Deprecated
        @Nullable
        public static Environment getEnvironment(int id) {
            return lookup.get(id);
        }
    }

    public static class Spigot {
        @NotNull
        public LightningStrike strikeLightning(@NotNull Location loc, boolean isSilent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @NotNull
        public LightningStrike strikeLightningEffect(@NotNull Location loc, boolean isSilent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

