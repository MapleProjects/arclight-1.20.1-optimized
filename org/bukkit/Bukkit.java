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
import org.bukkit.BanList;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.Server;
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
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.CustomTimingsHandler;

public final class Bukkit {
    private static Server server;

    private Bukkit() {
    }

    @NotNull
    public static Server getServer() {
        return server;
    }

    public static void setServer(@NotNull Server server) {
        if (Bukkit.server != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        }
        Bukkit.server = server;
        server.getLogger().info("This server is running " + Bukkit.getName() + " version " + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")");
    }

    @NotNull
    public static String getName() {
        return server.getName();
    }

    @NotNull
    public static String getVersion() {
        return server.getVersion();
    }

    @NotNull
    public static String getBukkitVersion() {
        return server.getBukkitVersion();
    }

    @NotNull
    public static Collection<? extends Player> getOnlinePlayers() {
        return server.getOnlinePlayers();
    }

    public static int getMaxPlayers() {
        return server.getMaxPlayers();
    }

    public static void setMaxPlayers(int maxPlayers) {
        server.setMaxPlayers(maxPlayers);
    }

    public static int getPort() {
        return server.getPort();
    }

    public static int getViewDistance() {
        return server.getViewDistance();
    }

    public static int getSimulationDistance() {
        return server.getSimulationDistance();
    }

    @NotNull
    public static String getIp() {
        return server.getIp();
    }

    @NotNull
    public static String getWorldType() {
        return server.getWorldType();
    }

    public static boolean getGenerateStructures() {
        return server.getGenerateStructures();
    }

    public static int getMaxWorldSize() {
        return server.getMaxWorldSize();
    }

    public static boolean getAllowEnd() {
        return server.getAllowEnd();
    }

    public static boolean getAllowNether() {
        return server.getAllowNether();
    }

    @NotNull
    public static List<String> getInitialEnabledPacks() {
        return server.getInitialEnabledPacks();
    }

    @NotNull
    public static List<String> getInitialDisabledPacks() {
        return server.getInitialDisabledPacks();
    }

    @NotNull
    public static DataPackManager getDataPackManager() {
        return server.getDataPackManager();
    }

    @NotNull
    public static String getResourcePack() {
        return server.getResourcePack();
    }

    @NotNull
    public static String getResourcePackHash() {
        return server.getResourcePackHash();
    }

    @NotNull
    public static String getResourcePackPrompt() {
        return server.getResourcePackPrompt();
    }

    public static boolean isResourcePackRequired() {
        return server.isResourcePackRequired();
    }

    public static boolean hasWhitelist() {
        return server.hasWhitelist();
    }

    public static void setWhitelist(boolean value) {
        server.setWhitelist(value);
    }

    public static boolean isWhitelistEnforced() {
        return server.isWhitelistEnforced();
    }

    public static void setWhitelistEnforced(boolean value) {
        server.setWhitelistEnforced(value);
    }

    @NotNull
    public static Set<OfflinePlayer> getWhitelistedPlayers() {
        return server.getWhitelistedPlayers();
    }

    public static void reloadWhitelist() {
        server.reloadWhitelist();
    }

    public static int broadcastMessage(@NotNull String message) {
        return server.broadcastMessage(message);
    }

    @NotNull
    public static String getUpdateFolder() {
        return server.getUpdateFolder();
    }

    @NotNull
    public static File getUpdateFolderFile() {
        return server.getUpdateFolderFile();
    }

    public static long getConnectionThrottle() {
        return server.getConnectionThrottle();
    }

    @Deprecated
    public static int getTicksPerAnimalSpawns() {
        return server.getTicksPerAnimalSpawns();
    }

    @Deprecated
    public static int getTicksPerMonsterSpawns() {
        return server.getTicksPerMonsterSpawns();
    }

    @Deprecated
    public static int getTicksPerWaterSpawns() {
        return server.getTicksPerWaterSpawns();
    }

    @Deprecated
    public static int getTicksPerAmbientSpawns() {
        return server.getTicksPerAmbientSpawns();
    }

    @Deprecated
    public static int getTicksPerWaterAmbientSpawns() {
        return server.getTicksPerAmbientSpawns();
    }

    @Deprecated
    public static int getTicksPerWaterUndergroundCreatureSpawns() {
        return server.getTicksPerWaterUndergroundCreatureSpawns();
    }

    public static int getTicksPerSpawns(@NotNull SpawnCategory spawnCategory) {
        return server.getTicksPerSpawns(spawnCategory);
    }

    @Nullable
    public static Player getPlayer(@NotNull String name) {
        return server.getPlayer(name);
    }

    @Nullable
    public static Player getPlayerExact(@NotNull String name) {
        return server.getPlayerExact(name);
    }

    @NotNull
    public static List<Player> matchPlayer(@NotNull String name) {
        return server.matchPlayer(name);
    }

    @Nullable
    public static Player getPlayer(@NotNull UUID id) {
        return server.getPlayer(id);
    }

    @NotNull
    public static PluginManager getPluginManager() {
        return server.getPluginManager();
    }

    @NotNull
    public static BukkitScheduler getScheduler() {
        return server.getScheduler();
    }

    @NotNull
    public static ServicesManager getServicesManager() {
        return server.getServicesManager();
    }

    @NotNull
    public static List<World> getWorlds() {
        return server.getWorlds();
    }

    @Nullable
    public static World createWorld(@NotNull WorldCreator creator) {
        return server.createWorld(creator);
    }

    public static boolean unloadWorld(@NotNull String name, boolean save) {
        return server.unloadWorld(name, save);
    }

    public static boolean unloadWorld(@NotNull World world, boolean save) {
        return server.unloadWorld(world, save);
    }

    @Nullable
    public static World getWorld(@NotNull String name) {
        return server.getWorld(name);
    }

    @Nullable
    public static World getWorld(@NotNull UUID uid) {
        return server.getWorld(uid);
    }

    @NotNull
    public static WorldBorder createWorldBorder() {
        return server.createWorldBorder();
    }

    @Deprecated
    @Nullable
    public static MapView getMap(int id) {
        return server.getMap(id);
    }

    @NotNull
    public static MapView createMap(@NotNull World world) {
        return server.createMap(world);
    }

    @NotNull
    public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        return server.createExplorerMap(world, location, structureType);
    }

    @NotNull
    public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored) {
        return server.createExplorerMap(world, location, structureType, radius, findUnexplored);
    }

    public static void reload() {
        server.reload();
        CustomTimingsHandler.reload();
    }

    public static void reloadData() {
        server.reloadData();
    }

    @NotNull
    public static Logger getLogger() {
        return server.getLogger();
    }

    @Nullable
    public static PluginCommand getPluginCommand(@NotNull String name) {
        return server.getPluginCommand(name);
    }

    public static void savePlayers() {
        server.savePlayers();
    }

    public static boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
        return server.dispatchCommand(sender, commandLine);
    }

    @Contract(value="null -> false")
    public static boolean addRecipe(@Nullable Recipe recipe) {
        return server.addRecipe(recipe);
    }

    @NotNull
    public static List<Recipe> getRecipesFor(@NotNull ItemStack result) {
        return server.getRecipesFor(result);
    }

    @Nullable
    public static Recipe getRecipe(@NotNull NamespacedKey recipeKey) {
        return server.getRecipe(recipeKey);
    }

    @Nullable
    public static Recipe getCraftingRecipe(@NotNull ItemStack[] craftingMatrix, @NotNull World world) {
        return server.getCraftingRecipe(craftingMatrix, world);
    }

    @NotNull
    public static ItemStack craftItem(@NotNull ItemStack[] craftingMatrix, @NotNull World world, @NotNull Player player) {
        return server.craftItem(craftingMatrix, world, player);
    }

    @NotNull
    public static Iterator<Recipe> recipeIterator() {
        return server.recipeIterator();
    }

    public static void clearRecipes() {
        server.clearRecipes();
    }

    public static void resetRecipes() {
        server.resetRecipes();
    }

    public static boolean removeRecipe(@NotNull NamespacedKey key) {
        return server.removeRecipe(key);
    }

    @NotNull
    public static Map<String, String[]> getCommandAliases() {
        return server.getCommandAliases();
    }

    public static int getSpawnRadius() {
        return server.getSpawnRadius();
    }

    public static void setSpawnRadius(int value) {
        server.setSpawnRadius(value);
    }

    @Deprecated
    public static boolean shouldSendChatPreviews() {
        return server.shouldSendChatPreviews();
    }

    public static boolean isEnforcingSecureProfiles() {
        return server.isEnforcingSecureProfiles();
    }

    public static boolean getHideOnlinePlayers() {
        return server.getHideOnlinePlayers();
    }

    public static boolean getOnlineMode() {
        return server.getOnlineMode();
    }

    public static boolean getAllowFlight() {
        return server.getAllowFlight();
    }

    public static boolean isHardcore() {
        return server.isHardcore();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static int broadcast(@NotNull String message, @NotNull String permission) {
        return server.broadcast(message, permission);
    }

    @Deprecated
    @NotNull
    public static OfflinePlayer getOfflinePlayer(@NotNull String name) {
        return server.getOfflinePlayer(name);
    }

    @NotNull
    public static OfflinePlayer getOfflinePlayer(@NotNull UUID id) {
        return server.getOfflinePlayer(id);
    }

    @NotNull
    public static PlayerProfile createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name) {
        return server.createPlayerProfile(uniqueId, name);
    }

    @NotNull
    public static PlayerProfile createPlayerProfile(@NotNull UUID uniqueId) {
        return server.createPlayerProfile(uniqueId);
    }

    @NotNull
    public static PlayerProfile createPlayerProfile(@NotNull String name) {
        return server.createPlayerProfile(name);
    }

    @NotNull
    public static Set<String> getIPBans() {
        return server.getIPBans();
    }

    @Deprecated
    public static void banIP(@NotNull String address) {
        server.banIP(address);
    }

    @Deprecated
    public static void unbanIP(@NotNull String address) {
        server.unbanIP(address);
    }

    public static void banIP(@NotNull InetAddress address) {
        server.banIP(address);
    }

    public static void unbanIP(@NotNull InetAddress address) {
        server.unbanIP(address);
    }

    @NotNull
    public static Set<OfflinePlayer> getBannedPlayers() {
        return server.getBannedPlayers();
    }

    @NotNull
    public static <T extends BanList<?>> T getBanList(@NotNull BanList.Type type) {
        return server.getBanList(type);
    }

    @NotNull
    public static Set<OfflinePlayer> getOperators() {
        return server.getOperators();
    }

    @NotNull
    public static GameMode getDefaultGameMode() {
        return server.getDefaultGameMode();
    }

    public static void setDefaultGameMode(@NotNull GameMode mode) {
        server.setDefaultGameMode(mode);
    }

    @NotNull
    public static ConsoleCommandSender getConsoleSender() {
        return server.getConsoleSender();
    }

    @NotNull
    public static File getWorldContainer() {
        return server.getWorldContainer();
    }

    @NotNull
    public static OfflinePlayer[] getOfflinePlayers() {
        return server.getOfflinePlayers();
    }

    @NotNull
    public static Messenger getMessenger() {
        return server.getMessenger();
    }

    @NotNull
    public static HelpMap getHelpMap() {
        return server.getHelpMap();
    }

    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type) {
        return server.createInventory(owner, type);
    }

    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title) {
        return server.createInventory(owner, type, title);
    }

    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException {
        return server.createInventory(owner, size);
    }

    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException {
        return server.createInventory(owner, size, title);
    }

    @NotNull
    public static Merchant createMerchant(@Nullable String title) {
        return server.createMerchant(title);
    }

    public static int getMaxChainedNeighborUpdates() {
        return server.getMaxChainedNeighborUpdates();
    }

    @Deprecated
    public static int getMonsterSpawnLimit() {
        return server.getMonsterSpawnLimit();
    }

    @Deprecated
    public static int getAnimalSpawnLimit() {
        return server.getAnimalSpawnLimit();
    }

    @Deprecated
    public static int getWaterAnimalSpawnLimit() {
        return server.getWaterAnimalSpawnLimit();
    }

    @Deprecated
    public static int getWaterAmbientSpawnLimit() {
        return server.getAmbientSpawnLimit();
    }

    @Deprecated
    public static int getWaterUndergroundCreatureSpawnLimit() {
        return server.getWaterUndergroundCreatureSpawnLimit();
    }

    @Deprecated
    public static int getAmbientSpawnLimit() {
        return server.getAmbientSpawnLimit();
    }

    public static int getSpawnLimit(@NotNull SpawnCategory spawnCategory) {
        return server.getSpawnLimit(spawnCategory);
    }

    public static boolean isPrimaryThread() {
        return server.isPrimaryThread();
    }

    @NotNull
    public static String getMotd() {
        return server.getMotd();
    }

    public static void setMotd(@NotNull String motd) {
        server.setMotd(motd);
    }

    @Nullable
    public static String getShutdownMessage() {
        return server.getShutdownMessage();
    }

    @NotNull
    public static Warning.WarningState getWarningState() {
        return server.getWarningState();
    }

    @NotNull
    public static ItemFactory getItemFactory() {
        return server.getItemFactory();
    }

    @Nullable
    public static ScoreboardManager getScoreboardManager() {
        return server.getScoreboardManager();
    }

    @NotNull
    public static Criteria getScoreboardCriteria(@NotNull String name) {
        return server.getScoreboardCriteria(name);
    }

    @Nullable
    public static CachedServerIcon getServerIcon() {
        return server.getServerIcon();
    }

    @NotNull
    public static CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception {
        return server.loadServerIcon(file);
    }

    @NotNull
    public static CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception {
        return server.loadServerIcon(image);
    }

    public static void setIdleTimeout(int threshold) {
        server.setIdleTimeout(threshold);
    }

    public static int getIdleTimeout() {
        return server.getIdleTimeout();
    }

    @NotNull
    public static ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
        return server.createChunkData(world);
    }

    @NotNull
    public static BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, BarFlag ... flags) {
        return server.createBossBar(title, color, style, flags);
    }

    @NotNull
    public static KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, BarFlag ... flags) {
        return server.createBossBar(key, title, color, style, flags);
    }

    @NotNull
    public static Iterator<KeyedBossBar> getBossBars() {
        return server.getBossBars();
    }

    @Nullable
    public static KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
        return server.getBossBar(key);
    }

    public static boolean removeBossBar(@NotNull NamespacedKey key) {
        return server.removeBossBar(key);
    }

    @Nullable
    public static Entity getEntity(@NotNull UUID uuid) {
        return server.getEntity(uuid);
    }

    @Nullable
    public static Advancement getAdvancement(@NotNull NamespacedKey key) {
        return server.getAdvancement(key);
    }

    @NotNull
    public static Iterator<Advancement> advancementIterator() {
        return server.advancementIterator();
    }

    @NotNull
    public static BlockData createBlockData(@NotNull Material material) {
        return server.createBlockData(material);
    }

    @NotNull
    public static BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer) {
        return server.createBlockData(material, consumer);
    }

    @NotNull
    public static BlockData createBlockData(@NotNull String data) throws IllegalArgumentException {
        return server.createBlockData(data);
    }

    @NotNull
    @Contract(value="null, null -> fail")
    public static BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException {
        return server.createBlockData(material, data);
    }

    @Nullable
    public static <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz) {
        return server.getTag(registry, tag, clazz);
    }

    @NotNull
    public static <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return server.getTags(registry, clazz);
    }

    @Nullable
    public static LootTable getLootTable(@NotNull NamespacedKey key) {
        return server.getLootTable(key);
    }

    @NotNull
    public static List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
        return server.selectEntities(sender, selector);
    }

    @NotNull
    public static StructureManager getStructureManager() {
        return server.getStructureManager();
    }

    @Nullable
    public static <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> tClass) {
        return server.getRegistry(tClass);
    }

    @Deprecated
    @NotNull
    public static UnsafeValues getUnsafe() {
        return server.getUnsafe();
    }

    @NotNull
    public static Server.Spigot spigot() {
        return server.spigot();
    }
}

