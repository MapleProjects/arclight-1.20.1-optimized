/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.BanList;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.StructureType;
import org.bukkit.Tag;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.packs.DataPackManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Server
extends PluginMessageRecipient {
    public static final String BROADCAST_CHANNEL_ADMINISTRATIVE = "bukkit.broadcast.admin";
    public static final String BROADCAST_CHANNEL_USERS = "bukkit.broadcast.user";

    @NotNull
    public String getName();

    @NotNull
    public String getVersion();

    @NotNull
    public String getBukkitVersion();

    @NotNull
    public Collection<? extends Player> getOnlinePlayers();

    public int getMaxPlayers();

    public void setMaxPlayers(int var1);

    public int getPort();

    public int getViewDistance();

    public int getSimulationDistance();

    @NotNull
    public String getIp();

    @NotNull
    public String getWorldType();

    public boolean getGenerateStructures();

    public int getMaxWorldSize();

    public boolean getAllowEnd();

    public boolean getAllowNether();

    @NotNull
    public List<String> getInitialEnabledPacks();

    @NotNull
    public List<String> getInitialDisabledPacks();

    @NotNull
    public DataPackManager getDataPackManager();

    @NotNull
    public String getResourcePack();

    @NotNull
    public String getResourcePackHash();

    @NotNull
    public String getResourcePackPrompt();

    public boolean isResourcePackRequired();

    public boolean hasWhitelist();

    public void setWhitelist(boolean var1);

    public boolean isWhitelistEnforced();

    public void setWhitelistEnforced(boolean var1);

    @NotNull
    public Set<OfflinePlayer> getWhitelistedPlayers();

    public void reloadWhitelist();

    public int broadcastMessage(@NotNull String var1);

    @NotNull
    public String getUpdateFolder();

    @NotNull
    public File getUpdateFolderFile();

    public long getConnectionThrottle();

    @Deprecated
    public int getTicksPerAnimalSpawns();

    @Deprecated
    public int getTicksPerMonsterSpawns();

    @Deprecated
    public int getTicksPerWaterSpawns();

    @Deprecated
    public int getTicksPerWaterAmbientSpawns();

    @Deprecated
    public int getTicksPerWaterUndergroundCreatureSpawns();

    @Deprecated
    public int getTicksPerAmbientSpawns();

    public int getTicksPerSpawns(@NotNull SpawnCategory var1);

    @Nullable
    public Player getPlayer(@NotNull String var1);

    @Nullable
    public Player getPlayerExact(@NotNull String var1);

    @NotNull
    public List<Player> matchPlayer(@NotNull String var1);

    @Nullable
    public Player getPlayer(@NotNull UUID var1);

    @NotNull
    public PluginManager getPluginManager();

    @NotNull
    public BukkitScheduler getScheduler();

    @NotNull
    public ServicesManager getServicesManager();

    @NotNull
    public List<World> getWorlds();

    @Nullable
    public World createWorld(@NotNull WorldCreator var1);

    public boolean unloadWorld(@NotNull String var1, boolean var2);

    public boolean unloadWorld(@NotNull World var1, boolean var2);

    @Nullable
    public World getWorld(@NotNull String var1);

    @Nullable
    public World getWorld(@NotNull UUID var1);

    @NotNull
    public WorldBorder createWorldBorder();

    @Deprecated
    @Nullable
    public MapView getMap(int var1);

    @NotNull
    public MapView createMap(@NotNull World var1);

    @NotNull
    public ItemStack createExplorerMap(@NotNull World var1, @NotNull Location var2, @NotNull StructureType var3);

    @NotNull
    public ItemStack createExplorerMap(@NotNull World var1, @NotNull Location var2, @NotNull StructureType var3, int var4, boolean var5);

    public void reload();

    public void reloadData();

    @NotNull
    public Logger getLogger();

    @Nullable
    public PluginCommand getPluginCommand(@NotNull String var1);

    public void savePlayers();

    public boolean dispatchCommand(@NotNull CommandSender var1, @NotNull String var2) throws CommandException;

    @Contract(value="null -> false")
    public boolean addRecipe(@Nullable Recipe var1);

    @NotNull
    public List<Recipe> getRecipesFor(@NotNull ItemStack var1);

    @Nullable
    public Recipe getRecipe(@NotNull NamespacedKey var1);

    @Nullable
    public Recipe getCraftingRecipe(@NotNull ItemStack[] var1, @NotNull World var2);

    @NotNull
    public ItemStack craftItem(@NotNull ItemStack[] var1, @NotNull World var2, @NotNull Player var3);

    @NotNull
    public Iterator<Recipe> recipeIterator();

    public void clearRecipes();

    public void resetRecipes();

    public boolean removeRecipe(@NotNull NamespacedKey var1);

    @NotNull
    public Map<String, String[]> getCommandAliases();

    public int getSpawnRadius();

    public void setSpawnRadius(int var1);

    @Deprecated
    public boolean shouldSendChatPreviews();

    public boolean isEnforcingSecureProfiles();

    public boolean getHideOnlinePlayers();

    public boolean getOnlineMode();

    public boolean getAllowFlight();

    public boolean isHardcore();

    public void shutdown();

    public int broadcast(@NotNull String var1, @NotNull String var2);

    @Deprecated
    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull String var1);

    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull UUID var1);

    @NotNull
    public PlayerProfile createPlayerProfile(@Nullable UUID var1, @Nullable String var2);

    @NotNull
    public PlayerProfile createPlayerProfile(@NotNull UUID var1);

    @NotNull
    public PlayerProfile createPlayerProfile(@NotNull String var1);

    @NotNull
    public Set<String> getIPBans();

    @Deprecated
    public void banIP(@NotNull String var1);

    @Deprecated
    public void unbanIP(@NotNull String var1);

    public void banIP(@NotNull InetAddress var1);

    public void unbanIP(@NotNull InetAddress var1);

    @NotNull
    public Set<OfflinePlayer> getBannedPlayers();

    @NotNull
    public <T extends BanList<?>> T getBanList(@NotNull BanList.Type var1);

    @NotNull
    public Set<OfflinePlayer> getOperators();

    @NotNull
    public GameMode getDefaultGameMode();

    public void setDefaultGameMode(@NotNull GameMode var1);

    @NotNull
    public ConsoleCommandSender getConsoleSender();

    @NotNull
    public File getWorldContainer();

    @NotNull
    public OfflinePlayer[] getOfflinePlayers();

    @NotNull
    public Messenger getMessenger();

    @NotNull
    public HelpMap getHelpMap();

    @NotNull
    public Inventory createInventory(@Nullable InventoryHolder var1, @NotNull InventoryType var2);

    @NotNull
    public Inventory createInventory(@Nullable InventoryHolder var1, @NotNull InventoryType var2, @NotNull String var3);

    @NotNull
    public Inventory createInventory(@Nullable InventoryHolder var1, int var2) throws IllegalArgumentException;

    @NotNull
    public Inventory createInventory(@Nullable InventoryHolder var1, int var2, @NotNull String var3) throws IllegalArgumentException;

    @NotNull
    public Merchant createMerchant(@Nullable String var1);

    public int getMaxChainedNeighborUpdates();

    @Deprecated
    public int getMonsterSpawnLimit();

    @Deprecated
    public int getAnimalSpawnLimit();

    @Deprecated
    public int getWaterAnimalSpawnLimit();

    @Deprecated
    public int getWaterAmbientSpawnLimit();

    @Deprecated
    public int getWaterUndergroundCreatureSpawnLimit();

    @Deprecated
    public int getAmbientSpawnLimit();

    public int getSpawnLimit(@NotNull SpawnCategory var1);

    public boolean isPrimaryThread();

    @NotNull
    public String getMotd();

    public void setMotd(@NotNull String var1);

    @Nullable
    public String getShutdownMessage();

    @NotNull
    public Warning.WarningState getWarningState();

    @NotNull
    public ItemFactory getItemFactory();

    @Nullable
    public ScoreboardManager getScoreboardManager();

    @NotNull
    public Criteria getScoreboardCriteria(@NotNull String var1);

    @Nullable
    public CachedServerIcon getServerIcon();

    @NotNull
    public CachedServerIcon loadServerIcon(@NotNull File var1) throws IllegalArgumentException, Exception;

    @NotNull
    public CachedServerIcon loadServerIcon(@NotNull BufferedImage var1) throws IllegalArgumentException, Exception;

    public void setIdleTimeout(int var1);

    public int getIdleTimeout();

    @NotNull
    public ChunkGenerator.ChunkData createChunkData(@NotNull World var1);

    @NotNull
    public BossBar createBossBar(@Nullable String var1, @NotNull BarColor var2, @NotNull BarStyle var3, BarFlag ... var4);

    @NotNull
    public KeyedBossBar createBossBar(@NotNull NamespacedKey var1, @Nullable String var2, @NotNull BarColor var3, @NotNull BarStyle var4, BarFlag ... var5);

    @NotNull
    public Iterator<KeyedBossBar> getBossBars();

    @Nullable
    public KeyedBossBar getBossBar(@NotNull NamespacedKey var1);

    public boolean removeBossBar(@NotNull NamespacedKey var1);

    @Nullable
    public Entity getEntity(@NotNull UUID var1);

    @Nullable
    public Advancement getAdvancement(@NotNull NamespacedKey var1);

    @NotNull
    public Iterator<Advancement> advancementIterator();

    @NotNull
    public BlockData createBlockData(@NotNull Material var1);

    @NotNull
    public BlockData createBlockData(@NotNull Material var1, @Nullable Consumer<BlockData> var2);

    @NotNull
    public BlockData createBlockData(@NotNull String var1) throws IllegalArgumentException;

    @NotNull
    @Contract(value="null, null -> fail")
    public BlockData createBlockData(@Nullable Material var1, @Nullable String var2) throws IllegalArgumentException;

    @Nullable
    public <T extends Keyed> Tag<T> getTag(@NotNull String var1, @NotNull NamespacedKey var2, @NotNull Class<T> var3);

    @NotNull
    public <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String var1, @NotNull Class<T> var2);

    @Nullable
    public LootTable getLootTable(@NotNull NamespacedKey var1);

    @NotNull
    public List<Entity> selectEntities(@NotNull CommandSender var1, @NotNull String var2) throws IllegalArgumentException;

    @NotNull
    public StructureManager getStructureManager();

    @Nullable
    public <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> var1);

    @Deprecated
    @NotNull
    public UnsafeValues getUnsafe();

    @NotNull
    public Spigot spigot();

    public static class Spigot {
        @NotNull
        public YamlConfiguration getConfig() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void broadcast(@NotNull BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void broadcast(BaseComponent ... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void restart() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

