/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Function
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.collect.MapMaker
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Lifecycle
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  jline.console.ConsoleReader
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.DefaultedRegistry
 *  net.minecraft.core.HolderLookup$Provider
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.resources.RegistryOps
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.ConsoleInput
 *  net.minecraft.server.Main
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.MinecraftServer$ServerResourcePackInfo
 *  net.minecraft.server.WorldLoader$DataLoadContext
 *  net.minecraft.server.bossevents.CustomBossEvent
 *  net.minecraft.server.bossevents.CustomBossEvents
 *  net.minecraft.server.commands.ReloadCommand
 *  net.minecraft.server.dedicated.DedicatedPlayerList
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.server.dedicated.DedicatedServerProperties
 *  net.minecraft.server.dedicated.DedicatedServerProperties$WorldDimensionData
 *  net.minecraft.server.dedicated.DedicatedServerSettings
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.server.players.ServerOpListEntry
 *  net.minecraft.server.players.StoredUserEntry
 *  net.minecraft.server.players.UserBanListEntry
 *  net.minecraft.server.players.UserWhiteListEntry
 *  net.minecraft.tags.TagKey
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.datafix.DataFixers
 *  net.minecraft.world.Container
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ai.village.VillageSiege
 *  net.minecraft.world.entity.npc.CatSpawner
 *  net.minecraft.world.entity.npc.WanderingTraderSpawner
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.inventory.CraftingMenu
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.inventory.TransientCraftingContainer
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.MapItem
 *  net.minecraft.world.item.crafting.CraftingRecipe
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.RepairItemRecipe
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelSettings
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.levelgen.PatrolSpawner
 *  net.minecraft.world.level.levelgen.PhantomSpawner
 *  net.minecraft.world.level.levelgen.WorldDimensions
 *  net.minecraft.world.level.levelgen.WorldDimensions$Complete
 *  net.minecraft.world.level.levelgen.WorldOptions
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.saveddata.maps.MapDecoration$Type
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.level.storage.LevelStorageSource
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  net.minecraft.world.level.storage.PlayerDataStorage
 *  net.minecraft.world.level.storage.PrimaryLevelData
 *  net.minecraft.world.level.storage.ServerLevelData
 *  net.minecraft.world.level.storage.WorldData
 *  net.minecraft.world.level.storage.loot.LootDataManager
 *  net.minecraft.world.level.validation.ContentValidationException
 *  net.minecraft.world.phys.Vec3
 *  org.yaml.snakeyaml.LoaderOptions
 *  org.yaml.snakeyaml.Yaml
 *  org.yaml.snakeyaml.constructor.BaseConstructor
 *  org.yaml.snakeyaml.constructor.SafeConstructor
 *  org.yaml.snakeyaml.error.MarkedYAMLException
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import jline.console.ConsoleReader;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.advancements.Advancement;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ConsoleInput;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.bossevents.CustomBossEvents;
import net.minecraft.server.commands.ReloadCommand;
import net.minecraft.server.dedicated.DedicatedPlayerList;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.server.dedicated.DedicatedServerSettings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.players.PlayerList;
import net.minecraft.server.players.ServerOpListEntry;
import net.minecraft.server.players.StoredUserEntry;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.server.players.UserWhiteListEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.world.Container;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.VillageSiege;
import net.minecraft.world.entity.npc.CatSpawner;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.PlayerDataStorage;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.validation.ContentValidationException;
import net.minecraft.world.phys.Vec3;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Fluid;
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
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.conversations.Conversable;
import org.bukkit.craftbukkit.Main;
import org.bukkit.craftbukkit.v1_20_R1.CraftLootTable;
import org.bukkit.craftbukkit.v1_20_R1.CraftOfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegistry;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorldBorder;
import org.bukkit.craftbukkit.v1_20_R1.ban.CraftIpBanList;
import org.bukkit.craftbukkit.v1_20_R1.ban.CraftProfileBanList;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftBossBar;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftKeyedBossbar;
import org.bukkit.craftbukkit.v1_20_R1.command.BukkitCommandWrapper;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftCommandMap;
import org.bukkit.craftbukkit.v1_20_R1.command.VanillaCommandWrapper;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.generator.CraftWorldInfo;
import org.bukkit.craftbukkit.v1_20_R1.generator.OldCraftChunkData;
import org.bukkit.craftbukkit.v1_20_R1.help.SimpleHelpMap;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftBlastingRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftCampfireRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftFurnaceRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchantCustom;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftShapedRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftShapelessRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftSmithingTransformRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftSmithingTrimRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftSmokingRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftStonecuttingRecipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.RecipeIterator;
import org.bukkit.craftbukkit.v1_20_R1.inventory.util.CraftInventoryCreator;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapColorCache;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapView;
import org.bukkit.craftbukkit.v1_20_R1.metadata.EntityMetadataStore;
import org.bukkit.craftbukkit.v1_20_R1.metadata.PlayerMetadataStore;
import org.bukkit.craftbukkit.v1_20_R1.metadata.WorldMetadataStore;
import org.bukkit.craftbukkit.v1_20_R1.packs.CraftDataPackManager;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionBrewer;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.craftbukkit.v1_20_R1.scheduler.CraftScheduler;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftCriteria;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardManager;
import org.bukkit.craftbukkit.v1_20_R1.structure.CraftStructureManager;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftBlockTag;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftEntityTag;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftFluidTag;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftItemTag;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftIconCache;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.craftbukkit.v1_20_R1.util.DatFileFilter;
import org.bukkit.craftbukkit.v1_20_R1.util.Versioning;
import org.bukkit.craftbukkit.v1_20_R1.util.permissions.CraftDefaultPermissions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.server.BroadcastMessageEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.inventory.SmithingTrimRecipe;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapView;
import org.bukkit.packs.DataPackManager;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.SimpleServicesManager;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitWorker;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.StringUtil;
import org.bukkit.util.permissions.DefaultPermissions;
import org.spigotmc.AsyncCatcher;
import org.spigotmc.RestartCommand;
import org.spigotmc.SpigotConfig;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.MarkedYAMLException;

public final class CraftServer
implements Server {
    private final String serverName = "CraftBukkit";
    private final String serverVersion;
    private final String bukkitVersion = Versioning.getBukkitVersion();
    private final Logger logger = Logger.getLogger("Minecraft");
    private final ServicesManager servicesManager = new SimpleServicesManager();
    private final CraftScheduler scheduler = new CraftScheduler();
    private final CraftCommandMap commandMap = new CraftCommandMap(this);
    private final SimpleHelpMap helpMap = new SimpleHelpMap(this);
    private final StandardMessenger messenger = new StandardMessenger();
    private final SimplePluginManager pluginManager = new SimplePluginManager(this, this.commandMap);
    private final StructureManager structureManager;
    protected final DedicatedServer console;
    protected final DedicatedPlayerList playerList;
    private final Map<String, World> worlds = new LinkedHashMap<String, World>();
    private final Map<Class<?>, Registry<?>> registries = new HashMap();
    private YamlConfiguration configuration;
    private YamlConfiguration commandsConfiguration;
    private final Yaml yaml = new Yaml((BaseConstructor)new SafeConstructor(new LoaderOptions()));
    private final Map<UUID, OfflinePlayer> offlinePlayers = new MapMaker().weakValues().makeMap();
    private final EntityMetadataStore entityMetadata = new EntityMetadataStore();
    private final PlayerMetadataStore playerMetadata = new PlayerMetadataStore();
    private final WorldMetadataStore worldMetadata = new WorldMetadataStore();
    private final Object2IntOpenHashMap<SpawnCategory> spawnCategoryLimit = new Object2IntOpenHashMap();
    private File container;
    private Warning.WarningState warningState = Warning.WarningState.DEFAULT;
    public String minimumAPI;
    public CraftScoreboardManager scoreboardManager;
    public CraftDataPackManager dataPackManager;
    public boolean playerCommandState;
    private boolean printSaveWarning;
    private CraftIconCache icon;
    private boolean overrideAllCommandBlockCommands = false;
    public boolean ignoreVanillaPermissions = false;
    private final List<CraftPlayer> playerView;
    public int reloadCount;
    private final Server.Spigot spigot = new Server.Spigot(){

        @Override
        public YamlConfiguration getConfig() {
            return SpigotConfig.config;
        }

        @Override
        public void restart() {
            RestartCommand.restart();
        }

        @Override
        public void broadcast(BaseComponent component) {
            for (org.bukkit.entity.Player player : CraftServer.this.getOnlinePlayers()) {
                player.spigot().sendMessage(component);
            }
        }

        @Override
        public void broadcast(BaseComponent ... components) {
            for (org.bukkit.entity.Player player : CraftServer.this.getOnlinePlayers()) {
                player.spigot().sendMessage(components);
            }
        }
    };

    static {
        ConfigurationSerialization.registerClass(CraftOfflinePlayer.class);
        ConfigurationSerialization.registerClass(CraftPlayerProfile.class);
        CraftItemFactory.instance();
    }

    public CraftServer(DedicatedServer console, PlayerList playerList) {
        this.console = console;
        this.playerList = (DedicatedPlayerList)playerList;
        this.playerView = Collections.unmodifiableList(Lists.transform((List)playerList.f_11196_, (Function)new Function<ServerPlayer, CraftPlayer>(){

            public CraftPlayer apply(ServerPlayer player) {
                return player.getBukkitEntity();
            }
        }));
        this.serverVersion = CraftServer.class.getPackage().getImplementationVersion();
        this.structureManager = new CraftStructureManager(console.m_236738_());
        this.dataPackManager = new CraftDataPackManager(this.getServer().m_129891_());
        Bukkit.setServer(this);
        CraftRegistry.setMinecraftRegistry((RegistryAccess)console.m_206579_());
        Enchantments.f_44977_.getClass();
        Enchantment.stopAcceptingRegistrations();
        Potion.setPotionBrewer(new CraftPotionBrewer());
        MobEffects.f_19610_.getClass();
        PotionEffectType.stopAcceptingRegistrations();
        if (!Main.useConsole) {
            this.getLogger().info("Console input is disabled due to --noconsole command argument");
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.getConfigFile());
        this.configuration.options().copyDefaults(true);
        this.configuration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("configurations/bukkit.yml"), Charsets.UTF_8)));
        ConfigurationSection legacyAlias = null;
        if (!this.configuration.isString("aliases")) {
            legacyAlias = this.configuration.getConfigurationSection("aliases");
            this.configuration.set("aliases", "now-in-commands.yml");
        }
        this.saveConfig();
        if (this.getCommandsConfigFile().isFile()) {
            legacyAlias = null;
        }
        this.commandsConfiguration = YamlConfiguration.loadConfiguration(this.getCommandsConfigFile());
        this.commandsConfiguration.options().copyDefaults(true);
        this.commandsConfiguration.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("configurations/commands.yml"), Charsets.UTF_8)));
        this.saveCommandsConfig();
        if (legacyAlias != null) {
            ConfigurationSection aliases = this.commandsConfiguration.createSection("aliases");
            for (String key : legacyAlias.getKeys(false)) {
                ArrayList<String> commands = new ArrayList<String>();
                if (legacyAlias.isList(key)) {
                    for (String command : legacyAlias.getStringList(key)) {
                        commands.add(String.valueOf(command) + " $1-");
                    }
                } else {
                    commands.add(String.valueOf(legacyAlias.getString(key)) + " $1-");
                }
                aliases.set(key, commands);
            }
        }
        this.saveCommandsConfig();
        this.overrideAllCommandBlockCommands = this.commandsConfiguration.getStringList("command-block-overrides").contains("*");
        this.ignoreVanillaPermissions = this.commandsConfiguration.getBoolean("ignore-vanilla-permissions");
        this.pluginManager.useTimings(this.configuration.getBoolean("settings.plugin-profiling"));
        this.overrideSpawnLimits();
        console.autosavePeriod = this.configuration.getInt("ticks-per.autosave");
        this.warningState = Warning.WarningState.value(this.configuration.getString("settings.deprecated-verbose"));
        TicketType.PLUGIN.f_9452_ = this.configuration.getInt("chunk-gc.period-in-ticks");
        this.minimumAPI = this.configuration.getString("settings.minimum-api");
        this.loadIcon();
        if (this.configuration.getBoolean("settings.use-map-color-cache")) {
            MapPalette.setMapColorCache(new CraftMapColorCache(this.logger));
        }
    }

    public boolean getCommandBlockOverride(String command) {
        return this.overrideAllCommandBlockCommands || this.commandsConfiguration.getStringList("command-block-overrides").contains(command);
    }

    private File getConfigFile() {
        return (File)this.console.options.valueOf("bukkit-settings");
    }

    private File getCommandsConfigFile() {
        return (File)this.console.options.valueOf("commands-settings");
    }

    private void overrideSpawnLimits() {
        SpawnCategory[] spawnCategoryArray = SpawnCategory.values();
        int n = spawnCategoryArray.length;
        int n2 = 0;
        while (n2 < n) {
            SpawnCategory spawnCategory = spawnCategoryArray[n2];
            if (CraftSpawnCategory.isValidForLimits(spawnCategory)) {
                this.spawnCategoryLimit.put((Object)spawnCategory, this.configuration.getInt(CraftSpawnCategory.getConfigNameSpawnLimit(spawnCategory)));
            }
            ++n2;
        }
    }

    private void saveConfig() {
        try {
            this.configuration.save(this.getConfigFile());
        }
        catch (IOException ex) {
            Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, "Could not save " + this.getConfigFile(), ex);
        }
    }

    private void saveCommandsConfig() {
        try {
            this.commandsConfiguration.save(this.getCommandsConfigFile());
        }
        catch (IOException ex) {
            Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, "Could not save " + this.getCommandsConfigFile(), ex);
        }
    }

    public void loadPlugins() {
        this.pluginManager.registerInterface(JavaPluginLoader.class);
        File pluginFolder = (File)this.console.options.valueOf("plugins");
        if (pluginFolder.exists()) {
            Plugin[] plugins;
            Plugin[] pluginArray = plugins = this.pluginManager.loadPlugins(pluginFolder);
            int n = plugins.length;
            int n2 = 0;
            while (n2 < n) {
                Plugin plugin = pluginArray[n2];
                try {
                    String message = String.format("Loading %s", plugin.getDescription().getFullName());
                    plugin.getLogger().info(message);
                    plugin.onLoad();
                }
                catch (Throwable ex) {
                    Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, String.valueOf(ex.getMessage()) + " initializing " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
                }
                ++n2;
            }
        } else {
            pluginFolder.mkdir();
        }
    }

    public void enablePlugins(PluginLoadOrder type) {
        Plugin[] plugins;
        if (type == PluginLoadOrder.STARTUP) {
            this.helpMap.clear();
            this.helpMap.initializeGeneralTopics();
        }
        Plugin[] pluginArray = plugins = this.pluginManager.getPlugins();
        int n = plugins.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (!plugin.isEnabled() && plugin.getDescription().getLoad() == type) {
                this.enablePlugin(plugin);
            }
            ++n2;
        }
        if (type == PluginLoadOrder.POSTWORLD) {
            this.setVanillaCommands(true);
            this.commandMap.setFallbackCommands();
            this.setVanillaCommands(false);
            this.commandMap.registerServerAliases();
            DefaultPermissions.registerCorePermissions();
            CraftDefaultPermissions.registerCorePermissions();
            this.loadCustomPermissions();
            this.helpMap.initializeCommands();
            this.syncCommands();
        }
    }

    public void disablePlugins() {
        this.pluginManager.disablePlugins();
    }

    private void setVanillaCommands(boolean first) {
        Commands dispatcher = this.console.vanillaCommandDispatcher;
        for (CommandNode cmd : dispatcher.m_82094_().getRoot().getChildren()) {
            VanillaCommandWrapper wrapper = new VanillaCommandWrapper(dispatcher, (CommandNode<CommandSourceStack>)cmd);
            if (SpigotConfig.replaceCommands.contains(wrapper.getName())) {
                if (!first) continue;
                this.commandMap.register("minecraft", wrapper);
                continue;
            }
            if (first) continue;
            this.commandMap.register("minecraft", wrapper);
        }
    }

    public void syncCommands() {
        Commands dispatcher = this.console.f_129740_.f_206585_().f_206847_ = new Commands();
        for (Map.Entry<String, Command> entry : this.commandMap.getKnownCommands().entrySet()) {
            String label = entry.getKey();
            Command command = entry.getValue();
            if (command instanceof VanillaCommandWrapper) {
                LiteralCommandNode node = (LiteralCommandNode)((VanillaCommandWrapper)command).vanillaCommand;
                if (!node.getLiteral().equals(label)) {
                    LiteralCommandNode clone = new LiteralCommandNode(label, node.getCommand(), node.getRequirement(), node.getRedirect(), node.getRedirectModifier(), node.isFork());
                    for (CommandNode child : node.getChildren()) {
                        clone.addChild(child);
                    }
                    node = clone;
                }
                dispatcher.m_82094_().getRoot().addChild((CommandNode)node);
                continue;
            }
            new BukkitCommandWrapper(this, entry.getValue()).register((CommandDispatcher<CommandSourceStack>)dispatcher.m_82094_(), label);
        }
        for (ServerPlayer player : this.getHandle().f_11196_) {
            dispatcher.m_82095_(player);
        }
    }

    private void enablePlugin(Plugin plugin) {
        try {
            List<Permission> perms = plugin.getDescription().getPermissions();
            for (Permission perm : perms) {
                try {
                    this.pluginManager.addPermission(perm, false);
                }
                catch (IllegalArgumentException ex) {
                    this.getLogger().log(Level.WARNING, "Plugin " + plugin.getDescription().getFullName() + " tried to register permission '" + perm.getName() + "' but it's already registered", ex);
                }
            }
            this.pluginManager.dirtyPermissibles();
            this.pluginManager.enablePlugin(plugin);
        }
        catch (Throwable ex) {
            Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, String.valueOf(ex.getMessage()) + " loading " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
        }
    }

    @Override
    public String getName() {
        return "CraftBukkit";
    }

    @Override
    public String getVersion() {
        return String.valueOf(this.serverVersion) + " (MC: " + this.console.m_7630_() + ")";
    }

    @Override
    public String getBukkitVersion() {
        return this.bukkitVersion;
    }

    public List<CraftPlayer> getOnlinePlayers() {
        return this.playerView;
    }

    @Override
    @Deprecated
    public org.bukkit.entity.Player getPlayer(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"name cannot be null");
        org.bukkit.entity.Player found = this.getPlayerExact(name);
        if (found != null) {
            return found;
        }
        String lowerName = name.toLowerCase(Locale.ENGLISH);
        int delta = Integer.MAX_VALUE;
        for (org.bukkit.entity.Player player : this.getOnlinePlayers()) {
            if (!player.getName().toLowerCase(Locale.ENGLISH).startsWith(lowerName)) continue;
            int curDelta = Math.abs(player.getName().length() - lowerName.length());
            if (curDelta < delta) {
                found = player;
                delta = curDelta;
            }
            if (curDelta == 0) break;
        }
        return found;
    }

    @Override
    @Deprecated
    public org.bukkit.entity.Player getPlayerExact(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"name cannot be null");
        ServerPlayer player = this.playerList.m_11255_(name);
        return player != null ? player.getBukkitEntity() : null;
    }

    @Override
    public org.bukkit.entity.Player getPlayer(UUID id) {
        Preconditions.checkArgument((id != null ? 1 : 0) != 0, (Object)"UUID id cannot be null");
        ServerPlayer player = this.playerList.m_11259_(id);
        if (player != null) {
            return player.getBukkitEntity();
        }
        return null;
    }

    @Override
    public int broadcastMessage(String message) {
        return this.broadcast(message, "bukkit.broadcast.user");
    }

    @Override
    @Deprecated
    public List<org.bukkit.entity.Player> matchPlayer(String partialName) {
        Preconditions.checkArgument((partialName != null ? 1 : 0) != 0, (Object)"partialName cannot be null");
        ArrayList<org.bukkit.entity.Player> matchedPlayers = new ArrayList<org.bukkit.entity.Player>();
        for (org.bukkit.entity.Player iterPlayer : this.getOnlinePlayers()) {
            String iterPlayerName = iterPlayer.getName();
            if (partialName.equalsIgnoreCase(iterPlayerName)) {
                matchedPlayers.clear();
                matchedPlayers.add(iterPlayer);
                break;
            }
            if (!iterPlayerName.toLowerCase(Locale.ENGLISH).contains(partialName.toLowerCase(Locale.ENGLISH))) continue;
            matchedPlayers.add(iterPlayer);
        }
        return matchedPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return this.playerList.m_11310_();
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        Preconditions.checkArgument((maxPlayers >= 0 ? 1 : 0) != 0, (Object)"maxPlayers must be >= 0");
        this.playerList.f_11193_ = maxPlayers;
    }

    @Override
    public int getPort() {
        return this.getServer().m_7010_();
    }

    @Override
    public int getViewDistance() {
        return this.getProperties().f_139714_;
    }

    @Override
    public int getSimulationDistance() {
        return this.getProperties().f_183715_;
    }

    @Override
    public String getIp() {
        return this.getServer().m_130009_();
    }

    @Override
    public String getWorldType() {
        return this.getProperties().f_139798_.getProperty("level-type");
    }

    @Override
    public boolean getGenerateStructures() {
        return this.getServer().m_129910_().m_246337_().m_247749_();
    }

    @Override
    public int getMaxWorldSize() {
        return this.getProperties().f_139719_;
    }

    @Override
    public boolean getAllowEnd() {
        return this.configuration.getBoolean("settings.allow-end");
    }

    @Override
    public boolean getAllowNether() {
        return this.getServer().m_7079_();
    }

    public boolean getWarnOnOverload() {
        return this.configuration.getBoolean("settings.warn-on-overload");
    }

    public boolean getQueryPlugins() {
        return this.configuration.getBoolean("settings.query-plugins");
    }

    @Override
    public List<String> getInitialEnabledPacks() {
        return Collections.unmodifiableList(this.getProperties().f_243997_.m_45850_());
    }

    @Override
    public List<String> getInitialDisabledPacks() {
        return Collections.unmodifiableList(this.getProperties().f_243997_.m_45855_());
    }

    @Override
    public DataPackManager getDataPackManager() {
        return this.dataPackManager;
    }

    @Override
    public String getResourcePack() {
        return this.getServer().m_214042_().map(MinecraftServer.ServerResourcePackInfo::f_236743_).orElse("");
    }

    @Override
    public String getResourcePackHash() {
        return this.getServer().m_214042_().map(MinecraftServer.ServerResourcePackInfo::f_236744_).orElse("").toUpperCase(Locale.ROOT);
    }

    @Override
    public String getResourcePackPrompt() {
        return this.getServer().m_214042_().map(MinecraftServer.ServerResourcePackInfo::f_236746_).map(CraftChatMessage::fromComponent).orElse("");
    }

    @Override
    public boolean isResourcePackRequired() {
        return this.getServer().m_142205_();
    }

    @Override
    public boolean hasWhitelist() {
        return (Boolean)this.getProperties().f_139726_.get();
    }

    private DedicatedServerProperties getProperties() {
        return this.console.m_7913_();
    }

    @Override
    public String getUpdateFolder() {
        return this.configuration.getString("settings.update-folder", "update");
    }

    @Override
    public File getUpdateFolderFile() {
        return new File((File)this.console.options.valueOf("plugins"), this.configuration.getString("settings.update-folder", "update"));
    }

    @Override
    public long getConnectionThrottle() {
        if (SpigotConfig.bungee) {
            return -1L;
        }
        return this.configuration.getInt("settings.connection-throttle");
    }

    @Override
    @Deprecated
    public int getTicksPerAnimalSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.ANIMAL);
    }

    @Override
    @Deprecated
    public int getTicksPerMonsterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.MONSTER);
    }

    @Override
    @Deprecated
    public int getTicksPerWaterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_ANIMAL);
    }

    @Override
    @Deprecated
    public int getTicksPerWaterAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_AMBIENT);
    }

    @Override
    @Deprecated
    public int getTicksPerWaterUndergroundCreatureSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    @Override
    @Deprecated
    public int getTicksPerAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.AMBIENT);
    }

    @Override
    public int getTicksPerSpawns(SpawnCategory spawnCategory) {
        Preconditions.checkArgument((spawnCategory != null ? 1 : 0) != 0, (Object)"SpawnCategory cannot be null");
        Preconditions.checkArgument((boolean)CraftSpawnCategory.isValidForLimits(spawnCategory), (String)"SpawnCategory.%s are not supported", (Object)((Object)spawnCategory));
        return this.configuration.getInt(CraftSpawnCategory.getConfigNameTicksPerSpawn(spawnCategory));
    }

    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public CraftScheduler getScheduler() {
        return this.scheduler;
    }

    @Override
    public ServicesManager getServicesManager() {
        return this.servicesManager;
    }

    @Override
    public List<World> getWorlds() {
        return new ArrayList<World>(this.worlds.values());
    }

    public DedicatedPlayerList getHandle() {
        return this.playerList;
    }

    public boolean dispatchServerCommand(CommandSender sender, ConsoleInput serverCommand) {
        Conversable conversable;
        if (sender instanceof Conversable && (conversable = (Conversable)((Object)sender)).isConversing()) {
            conversable.acceptConversationInput(serverCommand.f_135928_);
            return true;
        }
        try {
            this.playerCommandState = true;
            boolean bl = this.dispatchCommand(sender, serverCommand.f_135928_);
            return bl;
        }
        catch (Exception ex) {
            this.getLogger().log(Level.WARNING, "Unexpected exception while parsing console command \"" + serverCommand.f_135928_ + '\"', ex);
            return false;
        }
        finally {
            this.playerCommandState = false;
        }
    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) {
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"sender cannot be null");
        Preconditions.checkArgument((commandLine != null ? 1 : 0) != 0, (Object)"commandLine cannot be null");
        AsyncCatcher.catchOp("command dispatch");
        if (this.commandMap.dispatch(sender, commandLine)) {
            return true;
        }
        if (!SpigotConfig.unknownCommandMessage.isEmpty()) {
            sender.sendMessage(SpigotConfig.unknownCommandMessage);
        }
        return false;
    }

    @Override
    public void reload() {
        ++this.reloadCount;
        this.configuration = YamlConfiguration.loadConfiguration(this.getConfigFile());
        this.commandsConfiguration = YamlConfiguration.loadConfiguration(this.getCommandsConfigFile());
        this.console.f_139604_ = new DedicatedServerSettings(this.console.options);
        DedicatedServerProperties config = this.console.f_139604_.m_139777_();
        this.console.m_129997_(config.f_139733_);
        this.console.m_129999_(config.f_139734_);
        this.console.m_129989_(config.f_139736_);
        this.overrideSpawnLimits();
        this.warningState = Warning.WarningState.value(this.configuration.getString("settings.deprecated-verbose"));
        TicketType.PLUGIN.f_9452_ = this.configuration.getInt("chunk-gc.period-in-ticks");
        this.minimumAPI = this.configuration.getString("settings.minimum-api");
        this.printSaveWarning = false;
        this.console.autosavePeriod = this.configuration.getInt("ticks-per.autosave");
        this.loadIcon();
        try {
            this.playerList.m_11299_().m_11399_();
        }
        catch (IOException ex) {
            this.logger.log(Level.WARNING, "Failed to load banned-ips.json, " + ex.getMessage());
        }
        try {
            this.playerList.m_11295_().m_11399_();
        }
        catch (IOException ex) {
            this.logger.log(Level.WARNING, "Failed to load banned-players.json, " + ex.getMessage());
        }
        SpigotConfig.init((File)this.console.options.valueOf("spigot-settings"));
        for (ServerLevel world : this.console.m_129785_()) {
            world.K.m_6166_(config.f_139739_);
            world.m_46703_(config.f_139705_, config.f_139731_);
            SpawnCategory[] spawnCategoryArray = SpawnCategory.values();
            int n = spawnCategoryArray.length;
            int n2 = 0;
            while (n2 < n) {
                SpawnCategory spawnCategory = spawnCategoryArray[n2];
                if (CraftSpawnCategory.isValidForLimits(spawnCategory)) {
                    long ticksPerCategorySpawn = this.getTicksPerSpawns(spawnCategory);
                    if (ticksPerCategorySpawn < 0L) {
                        world.ticksPerSpawnCategory.put((Object)spawnCategory, CraftSpawnCategory.getDefaultTicksPerSpawn(spawnCategory));
                    } else {
                        world.ticksPerSpawnCategory.put((Object)spawnCategory, ticksPerCategorySpawn);
                    }
                }
                ++n2;
            }
            world.spigotConfig.init();
        }
        this.pluginManager.clearPlugins();
        this.commandMap.clearCommands();
        this.reloadData();
        SpigotConfig.registerCommands();
        this.overrideAllCommandBlockCommands = this.commandsConfiguration.getStringList("command-block-overrides").contains("*");
        this.ignoreVanillaPermissions = this.commandsConfiguration.getBoolean("ignore-vanilla-permissions");
        int pollCount = 0;
        while (pollCount < 50 && this.getScheduler().getActiveWorkers().size() > 0) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            ++pollCount;
        }
        List<BukkitWorker> overdueWorkers = this.getScheduler().getActiveWorkers();
        for (BukkitWorker worker : overdueWorkers) {
            Plugin plugin = worker.getOwner();
            this.getLogger().log(Level.SEVERE, String.format("Nag author(s): '%s' of '%s' about the following: %s", plugin.getDescription().getAuthors(), plugin.getDescription().getFullName(), "This plugin is not properly shutting down its async tasks when it is being reloaded.  This may cause conflicts with the newly loaded version of the plugin"));
        }
        this.loadPlugins();
        this.enablePlugins(PluginLoadOrder.STARTUP);
        this.enablePlugins(PluginLoadOrder.POSTWORLD);
        this.getPluginManager().callEvent(new ServerLoadEvent(ServerLoadEvent.LoadType.RELOAD));
    }

    @Override
    public void reloadData() {
        ReloadCommand.reload((MinecraftServer)this.console);
    }

    private void loadIcon() {
        this.icon = new CraftIconCache(null);
        try {
            File file = new File(new File("."), "server-icon.png");
            if (file.isFile()) {
                this.icon = CraftServer.loadServerIcon0(file);
            }
        }
        catch (Exception ex) {
            this.getLogger().log(Level.WARNING, "Couldn't load server icon", ex);
        }
    }

    private void loadCustomPermissions() {
        Map perms;
        FileInputStream stream;
        File file = new File(this.configuration.getString("settings.permissions-file"));
        try {
            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException ex) {
            try {
                file.createNewFile();
            }
            catch (Throwable throwable) {}
            return;
        }
        try {
            try {
                perms = (Map)this.yaml.load((InputStream)stream);
            }
            catch (MarkedYAMLException ex) {
                this.getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML: " + ex.toString());
                try {
                    stream.close();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                return;
            }
            catch (Throwable ex) {
                this.getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML.", ex);
                try {
                    stream.close();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
                return;
            }
        }
        finally {
            try {
                stream.close();
            }
            catch (IOException iOException) {}
        }
        if (perms == null) {
            this.getLogger().log(Level.INFO, "Server permissions file " + file + " is empty, ignoring it");
            return;
        }
        List<Permission> permsList = Permission.loadPermissions(perms, "Permission node '%s' in " + file + " is invalid", Permission.DEFAULT_PERMISSION);
        for (Permission perm : permsList) {
            try {
                this.pluginManager.addPermission(perm);
            }
            catch (IllegalArgumentException ex) {
                this.getLogger().log(Level.SEVERE, "Permission in " + file + " was already defined", ex);
            }
        }
    }

    public String toString() {
        return "CraftServer{serverName=CraftBukkit,serverVersion=" + this.serverVersion + ",minecraftVersion=" + this.console.m_7630_() + '}';
    }

    public World createWorld(String name, World.Environment environment) {
        return WorldCreator.name(name).environment(environment).createWorld();
    }

    public World createWorld(String name, World.Environment environment, long seed) {
        return WorldCreator.name(name).environment(environment).seed(seed).createWorld();
    }

    public World createWorld(String name, World.Environment environment, ChunkGenerator generator) {
        return WorldCreator.name(name).environment(environment).generator(generator).createWorld();
    }

    public World createWorld(String name, World.Environment environment, long seed, ChunkGenerator generator) {
        return WorldCreator.name(name).environment(environment).seed(seed).generator(generator).createWorld();
    }

    @Override
    public World createWorld(WorldCreator creator) {
        String levelName;
        PrimaryLevelData worlddata;
        LevelStorageSource.LevelStorageAccess worldSession;
        Preconditions.checkState((boolean)this.console.m_129785_().iterator().hasNext(), (Object)"Cannot create additional worlds on STARTUP");
        Preconditions.checkArgument((creator != null ? 1 : 0) != 0, (Object)"WorldCreator cannot be null");
        String name = creator.name();
        ChunkGenerator generator = creator.generator();
        BiomeProvider biomeProvider = creator.biomeProvider();
        File folder = new File(this.getWorldContainer(), name);
        World world = this.getWorld(name);
        if (world != null) {
            return world;
        }
        if (folder.exists()) {
            Preconditions.checkArgument((boolean)folder.isDirectory(), (String)"File (%s) exists and isn't a folder", (Object)name);
        }
        if (generator == null) {
            generator = this.getGenerator(name);
        }
        if (biomeProvider == null) {
            biomeProvider = this.getBiomeProvider(name);
        }
        ResourceKey actualDimension = switch (creator.environment()) {
            case World.Environment.NORMAL -> LevelStem.f_63971_;
            case World.Environment.NETHER -> LevelStem.f_63972_;
            case World.Environment.THE_END -> LevelStem.f_63973_;
            default -> throw new IllegalArgumentException("Illegal dimension (" + (Object)((Object)creator.environment()) + ")");
        };
        try {
            worldSession = LevelStorageSource.m_78242_((Path)this.getWorldContainer().toPath()).validateAndCreateAccess(name, actualDimension);
        }
        catch (IOException | ContentValidationException ex) {
            throw new RuntimeException(ex);
        }
        boolean hardcore = creator.hardcore();
        WorldLoader.DataLoadContext worldloader_a = this.console.worldLoader;
        net.minecraft.core.Registry iregistry = worldloader_a.f_243759_().m_175515_(Registries.f_256862_);
        RegistryOps dynamicops = RegistryOps.m_255058_((DynamicOps)NbtOps.f_128958_, (HolderLookup.Provider)worldloader_a.f_244104_());
        Pair pair = worldSession.m_246049_((DynamicOps)dynamicops, worldloader_a.f_244127_(), iregistry, worldloader_a.f_244104_().m_211816_());
        if (pair != null) {
            worlddata = (PrimaryLevelData)pair.getFirst();
            iregistry = ((WorldDimensions.Complete)pair.getSecond()).f_244049_();
        } else {
            WorldOptions worldoptions = new WorldOptions(creator.seed(), creator.generateStructures(), false);
            DedicatedServerProperties.WorldDimensionData properties = new DedicatedServerProperties.WorldDimensionData(GsonHelper.m_13864_((String)(creator.generatorSettings().isEmpty() ? "{}" : creator.generatorSettings())), creator.type().name().toLowerCase(Locale.ROOT));
            LevelSettings worldsettings = new LevelSettings(name, GameType.m_46393_((int)this.getDefaultGameMode().getValue()), hardcore, Difficulty.EASY, false, new GameRules(), worldloader_a.f_244127_());
            WorldDimensions worlddimensions = properties.m_247373_((RegistryAccess)worldloader_a.f_244104_());
            WorldDimensions.Complete worlddimensions_b = worlddimensions.m_245300_(iregistry);
            Lifecycle lifecycle = worlddimensions_b.m_245945_().add(worldloader_a.f_244104_().m_211816_());
            worlddata = new PrimaryLevelData(worldsettings, worldoptions, worlddimensions_b.f_244634_(), lifecycle);
            iregistry = worlddimensions_b.f_244049_();
        }
        worlddata.customDimensions = iregistry;
        worlddata.checkName(name);
        worlddata.m_7955_(this.console.getServerModName(), this.console.m_183471_().m_184597_());
        if (this.console.options.has("forceUpgrade")) {
            net.minecraft.server.Main.m_195488_((LevelStorageSource.LevelStorageAccess)worldSession, (DataFixer)DataFixers.m_14512_(), (boolean)this.console.options.has("eraseCache"), () -> true, (net.minecraft.core.Registry)iregistry);
        }
        long j = BiomeManager.m_47877_((long)creator.seed());
        ImmutableList list = ImmutableList.of((Object)new PhantomSpawner(), (Object)new PatrolSpawner(), (Object)new CatSpawner(), (Object)new VillageSiege(), (Object)new WanderingTraderSpawner((ServerLevelData)worlddata));
        LevelStem worlddimension = (LevelStem)iregistry.m_6246_(actualDimension);
        CraftWorldInfo worldInfo = new CraftWorldInfo((ServerLevelData)worlddata, worldSession, creator.environment(), (DimensionType)worlddimension.f_63975_().m_203334_());
        if (biomeProvider == null && generator != null) {
            biomeProvider = generator.getDefaultBiomeProvider(worldInfo);
        }
        ResourceKey worldKey = name.equals(String.valueOf(levelName = this.getServer().m_7913_().f_139741_) + "_nether") ? net.minecraft.world.level.Level.f_46429_ : (name.equals(String.valueOf(levelName) + "_the_end") ? net.minecraft.world.level.Level.f_46430_ : ResourceKey.m_135785_((ResourceKey)Registries.f_256858_, (ResourceLocation)new ResourceLocation(name.toLowerCase(Locale.ENGLISH))));
        ServerLevel internal = new ServerLevel((MinecraftServer)this.console, this.console.f_129738_, worldSession, worlddata, worldKey, worlddimension, this.getServer().f_129756_.m_9620_(11), worlddata.m_7513_(), j, (List)(creator.environment() == World.Environment.NORMAL ? list : ImmutableList.of()), true, this.console.m_129783_().m_288231_(), creator.environment(), generator, biomeProvider);
        if (!this.worlds.containsKey(name.toLowerCase(Locale.ENGLISH))) {
            return null;
        }
        this.console.initWorld(internal, (ServerLevelData)worlddata, (WorldData)worlddata, worlddata.m_246337_());
        internal.m_46703_(true, true);
        this.console.addLevel(internal);
        this.getServer().prepareLevels(internal.m_7726_().f_8325_.f_140144_, internal);
        internal.f_143244_.m_157506_();
        this.pluginManager.callEvent(new WorldLoadEvent(internal.getWorld()));
        return internal.getWorld();
    }

    @Override
    public boolean unloadWorld(String name, boolean save) {
        return this.unloadWorld(this.getWorld(name), save);
    }

    @Override
    public boolean unloadWorld(World world, boolean save) {
        if (world == null) {
            return false;
        }
        ServerLevel handle = ((CraftWorld)world).getHandle();
        if (this.console.m_129880_(handle.m_46472_()) == null) {
            return false;
        }
        if (handle.m_46472_() == net.minecraft.world.level.Level.f_46428_) {
            return false;
        }
        if (handle.m_6907_().size() > 0) {
            return false;
        }
        WorldUnloadEvent e = new WorldUnloadEvent(handle.getWorld());
        this.pluginManager.callEvent(e);
        if (e.isCancelled()) {
            return false;
        }
        try {
            if (save) {
                handle.m_8643_(null, true, true);
            }
            handle.m_7726_().close(save);
            handle.f_143244_.close(save);
            handle.convertable.close();
        }
        catch (Exception ex) {
            this.getLogger().log(Level.SEVERE, null, ex);
        }
        this.worlds.remove(world.getName().toLowerCase(Locale.ENGLISH));
        this.console.removeLevel(handle);
        return true;
    }

    public DedicatedServer getServer() {
        return this.console;
    }

    @Override
    public World getWorld(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"name cannot be null");
        return this.worlds.get(name.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public World getWorld(UUID uid) {
        for (World world : this.worlds.values()) {
            if (!world.getUID().equals(uid)) continue;
            return world;
        }
        return null;
    }

    public void addWorld(World world) {
        if (this.getWorld(world.getUID()) != null) {
            System.out.println("World " + world.getName() + " is a duplicate of another world and has been prevented from loading. Please delete the uid.dat file from " + world.getName() + "'s world directory if you want to be able to load the duplicate world.");
            return;
        }
        this.worlds.put(world.getName().toLowerCase(Locale.ENGLISH), world);
    }

    @Override
    public WorldBorder createWorldBorder() {
        return new CraftWorldBorder(new net.minecraft.world.level.border.WorldBorder());
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    public ConsoleReader getReader() {
        return this.console.reader;
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        Command command = this.commandMap.getCommand(name);
        if (command instanceof PluginCommand) {
            return (PluginCommand)command;
        }
        return null;
    }

    @Override
    public void savePlayers() {
        this.checkSaveState();
        this.playerList.m_11302_();
    }

    @Override
    public boolean addRecipe(org.bukkit.inventory.Recipe recipe) {
        CraftRecipe toAdd;
        if (recipe instanceof CraftRecipe) {
            toAdd = (CraftRecipe)recipe;
        } else if (recipe instanceof ShapedRecipe) {
            toAdd = CraftShapedRecipe.fromBukkitRecipe((ShapedRecipe)recipe);
        } else if (recipe instanceof ShapelessRecipe) {
            toAdd = CraftShapelessRecipe.fromBukkitRecipe((ShapelessRecipe)recipe);
        } else if (recipe instanceof FurnaceRecipe) {
            toAdd = CraftFurnaceRecipe.fromBukkitRecipe((FurnaceRecipe)recipe);
        } else if (recipe instanceof BlastingRecipe) {
            toAdd = CraftBlastingRecipe.fromBukkitRecipe((BlastingRecipe)recipe);
        } else if (recipe instanceof CampfireRecipe) {
            toAdd = CraftCampfireRecipe.fromBukkitRecipe((CampfireRecipe)recipe);
        } else if (recipe instanceof SmokingRecipe) {
            toAdd = CraftSmokingRecipe.fromBukkitRecipe((SmokingRecipe)recipe);
        } else if (recipe instanceof StonecuttingRecipe) {
            toAdd = CraftStonecuttingRecipe.fromBukkitRecipe((StonecuttingRecipe)recipe);
        } else if (recipe instanceof SmithingTransformRecipe) {
            toAdd = CraftSmithingTransformRecipe.fromBukkitRecipe((SmithingTransformRecipe)recipe);
        } else if (recipe instanceof SmithingTrimRecipe) {
            toAdd = CraftSmithingTrimRecipe.fromBukkitRecipe((SmithingTrimRecipe)recipe);
        } else {
            if (recipe instanceof ComplexRecipe) {
                throw new UnsupportedOperationException("Cannot add custom complex recipe");
            }
            return false;
        }
        toAdd.addToCraftingManager();
        return true;
    }

    @Override
    public List<org.bukkit.inventory.Recipe> getRecipesFor(org.bukkit.inventory.ItemStack result) {
        Preconditions.checkArgument((result != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        ArrayList<org.bukkit.inventory.Recipe> results = new ArrayList<org.bukkit.inventory.Recipe>();
        Iterator<org.bukkit.inventory.Recipe> iter = this.recipeIterator();
        while (iter.hasNext()) {
            org.bukkit.inventory.Recipe recipe = iter.next();
            org.bukkit.inventory.ItemStack stack = recipe.getResult();
            if (stack.getType() != result.getType() || result.getDurability() != -1 && result.getDurability() != stack.getDurability()) continue;
            results.add(recipe);
        }
        return results;
    }

    @Override
    public org.bukkit.inventory.Recipe getRecipe(NamespacedKey recipeKey) {
        Preconditions.checkArgument((recipeKey != null ? 1 : 0) != 0, (Object)"NamespacedKey recipeKey cannot be null");
        return this.getServer().m_129894_().m_44043_(CraftNamespacedKey.toMinecraft(recipeKey)).map(Recipe::toBukkitRecipe).orElse(null);
    }

    @Override
    public org.bukkit.inventory.Recipe getCraftingRecipe(org.bukkit.inventory.ItemStack[] craftingMatrix, World world) {
        AbstractContainerMenu container = new AbstractContainerMenu(null, -1){

            public InventoryView getBukkitView() {
                return null;
            }

            public boolean m_6875_(Player entityhuman) {
                return false;
            }

            public ItemStack m_7648_(Player entityhuman, int i) {
                return ItemStack.f_41583_;
            }
        };
        TransientCraftingContainer inventoryCrafting = new TransientCraftingContainer(container, 3, 3);
        return this.getNMSRecipe(craftingMatrix, (CraftingContainer)inventoryCrafting, (CraftWorld)world).map(Recipe::toBukkitRecipe).orElse(null);
    }

    @Override
    public org.bukkit.inventory.ItemStack craftItem(org.bukkit.inventory.ItemStack[] craftingMatrix, World world, org.bukkit.entity.Player player) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world cannot be null");
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"player cannot be null");
        CraftWorld craftWorld = (CraftWorld)world;
        CraftPlayer craftPlayer = (CraftPlayer)player;
        CraftingMenu container = new CraftingMenu(-1, craftPlayer.getHandle().m_150109_());
        TransientCraftingContainer inventoryCrafting = container.r;
        ResultContainer craftResult = container.f_39349_;
        Optional<CraftingRecipe> recipe = this.getNMSRecipe(craftingMatrix, (CraftingContainer)inventoryCrafting, craftWorld);
        ItemStack itemstack = ItemStack.f_41583_;
        if (recipe.isPresent()) {
            CraftingRecipe recipeCrafting = recipe.get();
            if (craftResult.m_40135_((net.minecraft.world.level.Level)craftWorld.getHandle(), craftPlayer.getHandle(), (Recipe)recipeCrafting)) {
                itemstack = recipeCrafting.m_5874_((Container)inventoryCrafting, craftWorld.getHandle().m_9598_());
            }
        }
        ItemStack result = CraftEventFactory.callPreCraftEvent((Container)inventoryCrafting, (Container)craftResult, itemstack, container.getBukkitView(), recipe.orElse(null) instanceof RepairItemRecipe);
        int i = 0;
        while (i < craftingMatrix.length) {
            Item remaining = ((ItemStack)inventoryCrafting.getContents().get(i)).m_41720_().m_41469_();
            craftingMatrix[i] = remaining != null ? CraftItemStack.asBukkitCopy(remaining.m_7968_()) : null;
            ++i;
        }
        return CraftItemStack.asBukkitCopy(result);
    }

    private Optional<CraftingRecipe> getNMSRecipe(org.bukkit.inventory.ItemStack[] craftingMatrix, CraftingContainer inventoryCrafting, CraftWorld world) {
        Preconditions.checkArgument((craftingMatrix != null ? 1 : 0) != 0, (Object)"craftingMatrix must not be null");
        Preconditions.checkArgument((craftingMatrix.length == 9 ? 1 : 0) != 0, (Object)"craftingMatrix must be an array of length 9");
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world must not be null");
        int i = 0;
        while (i < craftingMatrix.length) {
            inventoryCrafting.m_6836_(i, CraftItemStack.asNMSCopy(craftingMatrix[i]));
            ++i;
        }
        return this.getServer().m_129894_().m_44015_(RecipeType.f_44107_, (Container)inventoryCrafting, (net.minecraft.world.level.Level)world.getHandle());
    }

    @Override
    public Iterator<org.bukkit.inventory.Recipe> recipeIterator() {
        return new RecipeIterator();
    }

    @Override
    public void clearRecipes() {
        this.console.m_129894_().clearRecipes();
    }

    @Override
    public void resetRecipes() {
        this.reloadData();
    }

    @Override
    public boolean removeRecipe(NamespacedKey recipeKey) {
        Preconditions.checkArgument((recipeKey != null ? 1 : 0) != 0, (Object)"recipeKey == null");
        ResourceLocation mcKey = CraftNamespacedKey.toMinecraft(recipeKey);
        return this.getServer().m_129894_().removeRecipe(mcKey);
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        ConfigurationSection section = this.commandsConfiguration.getConfigurationSection("aliases");
        LinkedHashMap<String, String[]> result = new LinkedHashMap<String, String[]>();
        if (section != null) {
            for (String key : section.getKeys(false)) {
                ImmutableList commands = section.isList(key) ? section.getStringList(key) : ImmutableList.of((Object)section.getString(key));
                result.put(key, commands.toArray(new String[commands.size()]));
            }
        }
        return result;
    }

    public void removeBukkitSpawnRadius() {
        this.configuration.set("settings.spawn-radius", null);
        this.saveConfig();
    }

    public int getBukkitSpawnRadius() {
        return this.configuration.getInt("settings.spawn-radius", -1);
    }

    @Override
    public String getShutdownMessage() {
        return this.configuration.getString("settings.shutdown-message");
    }

    @Override
    public int getSpawnRadius() {
        return this.getServer().m_6396_();
    }

    @Override
    public void setSpawnRadius(int value) {
        this.configuration.set("settings.spawn-radius", value);
        this.saveConfig();
    }

    @Override
    public boolean shouldSendChatPreviews() {
        return false;
    }

    @Override
    public boolean isEnforcingSecureProfiles() {
        return this.getServer().m_214005_();
    }

    @Override
    public boolean getHideOnlinePlayers() {
        return this.console.m_183306_();
    }

    @Override
    public boolean getOnlineMode() {
        return this.console.m_129797_();
    }

    @Override
    public boolean getAllowFlight() {
        return this.console.m_129915_();
    }

    @Override
    public boolean isHardcore() {
        return this.console.m_7035_();
    }

    public ChunkGenerator getGenerator(String world) {
        String name;
        ConfigurationSection section = this.configuration.getConfigurationSection("worlds");
        ChunkGenerator result = null;
        if (section != null && (section = section.getConfigurationSection(world)) != null && (name = section.getString("generator")) != null && !name.equals("")) {
            String[] split = name.split(":", 2);
            String id = split.length > 1 ? split[1] : null;
            Plugin plugin = this.pluginManager.getPlugin(split[0]);
            if (plugin == null) {
                this.getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                this.getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled yet (is it load:STARTUP?)");
            } else {
                try {
                    result = plugin.getDefaultWorldGenerator(world, id);
                    if (result == null) {
                        this.getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' lacks a default world generator");
                    }
                }
                catch (Throwable t) {
                    plugin.getLogger().log(Level.SEVERE, "Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName(), t);
                }
            }
        }
        return result;
    }

    public BiomeProvider getBiomeProvider(String world) {
        String name;
        ConfigurationSection section = this.configuration.getConfigurationSection("worlds");
        BiomeProvider result = null;
        if (section != null && (section = section.getConfigurationSection(world)) != null && (name = section.getString("biome-provider")) != null && !name.equals("")) {
            String[] split = name.split(":", 2);
            String id = split.length > 1 ? split[1] : null;
            Plugin plugin = this.pluginManager.getPlugin(split[0]);
            if (plugin == null) {
                this.getLogger().severe("Could not set biome provider for default world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                this.getLogger().severe("Could not set biome provider for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled yet (is it load:STARTUP?)");
            } else {
                try {
                    result = plugin.getDefaultBiomeProvider(world, id);
                    if (result == null) {
                        this.getLogger().severe("Could not set biome provider for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' lacks a default world biome provider");
                    }
                }
                catch (Throwable t) {
                    plugin.getLogger().log(Level.SEVERE, "Could not set biome provider for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName(), t);
                }
            }
        }
        return result;
    }

    @Override
    @Deprecated
    public CraftMapView getMap(int id) {
        MapItemSavedData worldmap = this.console.m_129880_(net.minecraft.world.level.Level.f_46428_).m_7489_("map_" + id);
        if (worldmap == null) {
            return null;
        }
        return worldmap.mapView;
    }

    @Override
    public CraftMapView createMap(World world) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World cannot be null");
        ServerLevel minecraftWorld = ((CraftWorld)world).getHandle();
        int newId = MapItem.m_151120_((net.minecraft.world.level.Level)minecraftWorld, (int)minecraftWorld.m_6106_().m_6789_(), (int)minecraftWorld.m_6106_().m_6526_(), (int)3, (boolean)false, (boolean)false, (ResourceKey)minecraftWorld.m_46472_());
        return minecraftWorld.m_7489_((String)MapItem.m_42848_((int)newId)).mapView;
    }

    @Override
    public org.bukkit.inventory.ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
        return this.createExplorerMap(world, location, structureType, 100, true);
    }

    @Override
    public org.bukkit.inventory.ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius, boolean findUnexplored) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World cannot be null");
        Preconditions.checkArgument((structureType != null ? 1 : 0) != 0, (Object)"StructureType cannot be null");
        Preconditions.checkArgument((structureType.getMapIcon() != null ? 1 : 0) != 0, (String)"Cannot create explorer maps for StructureType %s", (Object)structureType.getName());
        ServerLevel worldServer = ((CraftWorld)world).getHandle();
        Location structureLocation = world.locateNearestStructure(location, structureType, radius, findUnexplored);
        BlockPos structurePosition = CraftLocation.toBlockPosition(structureLocation);
        ItemStack stack = MapItem.m_42886_((net.minecraft.world.level.Level)worldServer, (int)structurePosition.m_123341_(), (int)structurePosition.m_123343_(), (byte)MapView.Scale.NORMAL.getValue(), (boolean)true, (boolean)true);
        MapItem.m_42850_((ServerLevel)worldServer, (ItemStack)stack);
        MapItem.m_42853_((ItemStack)stack, (net.minecraft.world.level.Level)worldServer);
        MapItemSavedData.m_77925_((ItemStack)stack, (BlockPos)structurePosition, (String)"+", (MapDecoration.Type)MapDecoration.Type.m_77854_((byte)structureType.getMapIcon().getValue()));
        return CraftItemStack.asBukkitCopy(stack);
    }

    @Override
    public void shutdown() {
        this.console.m_7570_(false);
    }

    @Override
    public int broadcast(String message, String permission) {
        HashSet<CommandSender> recipients = new HashSet<CommandSender>();
        for (Permissible permissible : this.getPluginManager().getPermissionSubscriptions(permission)) {
            if (!(permissible instanceof CommandSender) || !permissible.hasPermission(permission)) continue;
            recipients.add((CommandSender)permissible);
        }
        BroadcastMessageEvent broadcastMessageEvent = new BroadcastMessageEvent(!Bukkit.isPrimaryThread(), message, recipients);
        this.getPluginManager().callEvent(broadcastMessageEvent);
        if (broadcastMessageEvent.isCancelled()) {
            return 0;
        }
        message = broadcastMessageEvent.getMessage();
        for (CommandSender recipient : recipients) {
            recipient.sendMessage(message);
        }
        return recipients.size();
    }

    @Override
    @Deprecated
    public OfflinePlayer getOfflinePlayer(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"name cannot be null");
        Preconditions.checkArgument((!name.isBlank() ? 1 : 0) != 0, (Object)"name cannot be empty");
        OfflinePlayer result = this.getPlayerExact(name);
        if (result == null) {
            GameProfile profile = null;
            if (this.getOnlineMode() || SpigotConfig.bungee) {
                profile = this.console.m_129927_().m_10996_(name).orElse(null);
            }
            result = profile == null ? this.getOfflinePlayer(new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name)) : this.getOfflinePlayer(profile);
        } else {
            this.offlinePlayers.remove(result.getUniqueId());
        }
        return result;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        Preconditions.checkArgument((id != null ? 1 : 0) != 0, (Object)"UUID id cannot be null");
        OfflinePlayer result = this.getPlayer(id);
        if (result == null) {
            result = this.offlinePlayers.get(id);
            if (result == null) {
                result = new CraftOfflinePlayer(this, new GameProfile(id, null));
                this.offlinePlayers.put(id, result);
            }
        } else {
            this.offlinePlayers.remove(id);
        }
        return result;
    }

    @Override
    public PlayerProfile createPlayerProfile(UUID uniqueId, String name) {
        return new CraftPlayerProfile(uniqueId, name);
    }

    @Override
    public PlayerProfile createPlayerProfile(UUID uniqueId) {
        return new CraftPlayerProfile(uniqueId, null);
    }

    @Override
    public PlayerProfile createPlayerProfile(String name) {
        return new CraftPlayerProfile(null, name);
    }

    public OfflinePlayer getOfflinePlayer(GameProfile profile) {
        CraftOfflinePlayer player = new CraftOfflinePlayer(this, profile);
        this.offlinePlayers.put(profile.getId(), player);
        return player;
    }

    @Override
    public Set<String> getIPBans() {
        return this.playerList.m_11299_().m_11395_().stream().map(StoredUserEntry::m_11373_).collect(Collectors.toSet());
    }

    @Override
    public void banIP(String address) {
        Preconditions.checkArgument((address != null && !address.isBlank() ? 1 : 0) != 0, (Object)"Address cannot be null or blank.");
        this.getBanList(BanList.Type.IP).addBan((String)address, (String)null, (Date)null, (String)null);
    }

    @Override
    public void unbanIP(String address) {
        Preconditions.checkArgument((address != null && !address.isBlank() ? 1 : 0) != 0, (Object)"Address cannot be null or blank.");
        this.getBanList(BanList.Type.IP).pardon(address);
    }

    @Override
    public void banIP(InetAddress address) {
        Preconditions.checkArgument((address != null ? 1 : 0) != 0, (Object)"Address cannot be null.");
        ((CraftIpBanList)this.getBanList(BanList.Type.IP)).addBan(address, (String)null, (Date)null, (String)null);
    }

    @Override
    public void unbanIP(InetAddress address) {
        Preconditions.checkArgument((address != null ? 1 : 0) != 0, (Object)"Address cannot be null.");
        ((CraftIpBanList)this.getBanList(BanList.Type.IP)).pardon(address);
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        HashSet<OfflinePlayer> result = new HashSet<OfflinePlayer>();
        for (UserBanListEntry entry : this.playerList.m_11295_().m_11395_()) {
            result.add(this.getOfflinePlayer((GameProfile)entry.m_11373_()));
        }
        return result;
    }

    @Override
    public <T extends BanList<?>> T getBanList(BanList.Type type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"BanList.Type cannot be null");
        return (T)(switch (type) {
            case BanList.Type.IP -> new CraftIpBanList(this.playerList.m_11299_());
            case BanList.Type.NAME, BanList.Type.PROFILE -> new CraftProfileBanList(this.playerList.m_11295_());
            default -> throw new IncompatibleClassChangeError();
        });
    }

    @Override
    public void setWhitelist(boolean value) {
        this.playerList.m_6628_(value);
        this.console.m_139688_(value);
    }

    @Override
    public boolean isWhitelistEnforced() {
        return this.console.m_129902_();
    }

    @Override
    public void setWhitelistEnforced(boolean value) {
        this.console.m_130004_(value);
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        LinkedHashSet<OfflinePlayer> result = new LinkedHashSet<OfflinePlayer>();
        for (UserWhiteListEntry entry : this.playerList.m_11305_().m_11395_()) {
            result.add(this.getOfflinePlayer((GameProfile)entry.m_11373_()));
        }
        return result;
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        HashSet<OfflinePlayer> result = new HashSet<OfflinePlayer>();
        for (ServerOpListEntry entry : this.playerList.m_11307_().m_11395_()) {
            result.add(this.getOfflinePlayer((GameProfile)entry.m_11373_()));
        }
        return result;
    }

    @Override
    public void reloadWhitelist() {
        this.playerList.m_7542_();
    }

    @Override
    public GameMode getDefaultGameMode() {
        return GameMode.getByValue(this.console.m_129880_((ResourceKey)net.minecraft.world.level.Level.f_46428_).K.m_5464_().m_46392_());
    }

    @Override
    public void setDefaultGameMode(GameMode mode) {
        Preconditions.checkArgument((mode != null ? 1 : 0) != 0, (Object)"GameMode cannot be null");
        for (World world : this.getWorlds()) {
            ((CraftWorld)world).getHandle().K.m_5458_(GameType.m_46393_((int)mode.getValue()));
        }
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return this.console.console;
    }

    public EntityMetadataStore getEntityMetadata() {
        return this.entityMetadata;
    }

    public PlayerMetadataStore getPlayerMetadata() {
        return this.playerMetadata;
    }

    public WorldMetadataStore getWorldMetadata() {
        return this.worldMetadata;
    }

    @Override
    public File getWorldContainer() {
        return this.getServer().f_129744_.m_197394_(net.minecraft.world.level.Level.f_46428_).getParent().toFile();
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        PlayerDataStorage storage = this.console.f_129745_;
        String[] files = storage.getPlayerDir().list(new DatFileFilter());
        HashSet<OfflinePlayer> players = new HashSet<OfflinePlayer>();
        String[] stringArray = files;
        int n = files.length;
        int n2 = 0;
        while (n2 < n) {
            String file = stringArray[n2];
            try {
                players.add(this.getOfflinePlayer(UUID.fromString(file.substring(0, file.length() - 4))));
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
            ++n2;
        }
        players.addAll(this.getOnlinePlayers());
        return players.toArray(new OfflinePlayer[players.size()]);
    }

    @Override
    public Messenger getMessenger() {
        return this.messenger;
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(this.getMessenger(), source, channel, message);
        for (org.bukkit.entity.Player player : this.getOnlinePlayers()) {
            player.sendPluginMessage(source, channel, message);
        }
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        HashSet<String> result = new HashSet<String>();
        for (org.bukkit.entity.Player player : this.getOnlinePlayers()) {
            result.addAll(player.getListeningPluginChannels());
        }
        return result;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"InventoryType cannot be null");
        Preconditions.checkArgument((boolean)type.isCreatable(), (String)"InventoryType.%s cannot be used to create a inventory", (Object)((Object)type));
        return CraftInventoryCreator.INSTANCE.createInventory(owner, type);
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"InventoryType cannot be null");
        Preconditions.checkArgument((boolean)type.isCreatable(), (String)"InventoryType.%s cannot be used to create a inventory", (Object)((Object)type));
        Preconditions.checkArgument((title != null ? 1 : 0) != 0, (Object)"title cannot be null");
        return CraftInventoryCreator.INSTANCE.createInventory(owner, type, title);
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
        Preconditions.checkArgument((9 <= size && size <= 54 && size % 9 == 0 ? 1 : 0) != 0, (String)"Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got %s)", (int)size);
        return CraftInventoryCreator.INSTANCE.createInventory(owner, size);
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
        Preconditions.checkArgument((9 <= size && size <= 54 && size % 9 == 0 ? 1 : 0) != 0, (String)"Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got %s)", (int)size);
        return CraftInventoryCreator.INSTANCE.createInventory(owner, size, title);
    }

    @Override
    public Merchant createMerchant(String title) {
        return new CraftMerchantCustom(title == null ? InventoryType.MERCHANT.getDefaultTitle() : title);
    }

    @Override
    public int getMaxChainedNeighborUpdates() {
        return this.getServer().m_213994_();
    }

    @Override
    public HelpMap getHelpMap() {
        return this.helpMap;
    }

    public SimpleCommandMap getCommandMap() {
        return this.commandMap;
    }

    @Override
    @Deprecated
    public int getMonsterSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.MONSTER);
    }

    @Override
    @Deprecated
    public int getAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.ANIMAL);
    }

    @Override
    @Deprecated
    public int getWaterAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_ANIMAL);
    }

    @Override
    @Deprecated
    public int getWaterAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_AMBIENT);
    }

    @Override
    @Deprecated
    public int getWaterUndergroundCreatureSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    @Override
    @Deprecated
    public int getAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.AMBIENT);
    }

    @Override
    public int getSpawnLimit(SpawnCategory spawnCategory) {
        return this.spawnCategoryLimit.getOrDefault((Object)spawnCategory, -1);
    }

    @Override
    public boolean isPrimaryThread() {
        return Thread.currentThread().equals(this.console.f_129725_) || this.console.hasStopped() || !AsyncCatcher.enabled;
    }

    @Override
    public String getMotd() {
        return this.console.m_129916_();
    }

    @Override
    public void setMotd(String motd) {
        this.console.m_129989_(motd);
    }

    @Override
    public Warning.WarningState getWarningState() {
        return this.warningState;
    }

    public List<String> tabComplete(CommandSender sender, String message, ServerLevel world, Vec3 pos, boolean forceCommand) {
        if (!(sender instanceof org.bukkit.entity.Player)) {
            return ImmutableList.of();
        }
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)sender;
        List<String> offers = message.startsWith("/") || forceCommand ? this.tabCompleteCommand(player, message, world, pos) : this.tabCompleteChat(player, message);
        TabCompleteEvent tabEvent = new TabCompleteEvent(player, message, offers);
        this.getPluginManager().callEvent(tabEvent);
        return tabEvent.isCancelled() ? Collections.EMPTY_LIST : tabEvent.getCompletions();
    }

    public List<String> tabCompleteCommand(org.bukkit.entity.Player player, String message, ServerLevel world, Vec3 pos) {
        if (!(SpigotConfig.tabComplete >= 0 && message.length() > SpigotConfig.tabComplete || message.contains(" "))) {
            return ImmutableList.of();
        }
        List<String> completions = null;
        try {
            if (message.startsWith("/")) {
                message = message.substring(1);
            }
            completions = pos == null ? this.getCommandMap().tabComplete(player, message) : this.getCommandMap().tabComplete(player, message, CraftLocation.toBukkit(pos, (World)world.getWorld()));
        }
        catch (CommandException ex) {
            player.sendMessage((Object)((Object)ChatColor.RED) + "An internal error occurred while attempting to tab-complete this command");
            this.getLogger().log(Level.SEVERE, "Exception when " + player.getName() + " attempted to tab complete " + message, ex);
        }
        return completions == null ? ImmutableList.of() : completions;
    }

    public List<String> tabCompleteChat(org.bukkit.entity.Player player, String message) {
        ArrayList<String> completions = new ArrayList<String>();
        PlayerChatTabCompleteEvent event = new PlayerChatTabCompleteEvent(player, message, completions);
        String token = event.getLastToken();
        for (org.bukkit.entity.Player p : this.getOnlinePlayers()) {
            if (!player.canSee(p) || !StringUtil.startsWithIgnoreCase(p.getName(), token)) continue;
            completions.add(p.getName());
        }
        this.pluginManager.callEvent(event);
        Iterator it = completions.iterator();
        while (it.hasNext()) {
            Object current = it.next();
            if (current instanceof String) continue;
            it.remove();
        }
        Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
        return completions;
    }

    @Override
    public CraftItemFactory getItemFactory() {
        return CraftItemFactory.instance();
    }

    @Override
    public CraftScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    @Override
    public Criteria getScoreboardCriteria(String name) {
        return CraftCriteria.getFromBukkit(name);
    }

    public void checkSaveState() {
        if (this.playerCommandState || this.printSaveWarning || this.console.autosavePeriod <= 0) {
            return;
        }
        this.printSaveWarning = true;
        this.getLogger().log(Level.WARNING, "A manual (plugin-induced) save has been detected while server is configured to auto-save. This may affect performance.", this.warningState == Warning.WarningState.ON ? new Throwable() : null);
    }

    @Override
    public CraftIconCache getServerIcon() {
        return this.icon;
    }

    @Override
    public CraftIconCache loadServerIcon(File file) throws Exception {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        Preconditions.checkArgument((boolean)file.isFile(), (String)"File (%s) is not a valid file", (Object)file);
        return CraftServer.loadServerIcon0(file);
    }

    static CraftIconCache loadServerIcon0(File file) throws Exception {
        return CraftServer.loadServerIcon0(ImageIO.read(file));
    }

    @Override
    public CraftIconCache loadServerIcon(BufferedImage image) throws Exception {
        Preconditions.checkArgument((image != null ? 1 : 0) != 0, (Object)"BufferedImage image cannot be null");
        return CraftServer.loadServerIcon0(image);
    }

    static CraftIconCache loadServerIcon0(BufferedImage image) throws Exception {
        Preconditions.checkArgument((image.getWidth() == 64 ? 1 : 0) != 0, (String)"BufferedImage must be 64 pixels wide (%s)", (int)image.getWidth());
        Preconditions.checkArgument((image.getHeight() == 64 ? 1 : 0) != 0, (String)"BufferedImage must be 64 pixels high (%s)", (int)image.getHeight());
        ByteArrayOutputStream bytebuf = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)image, "PNG", bytebuf);
        return new CraftIconCache(bytebuf.toByteArray());
    }

    @Override
    public void setIdleTimeout(int threshold) {
        this.console.m_7196_(threshold);
    }

    @Override
    public int getIdleTimeout() {
        return this.console.m_129924_();
    }

    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World cannot be null");
        ServerLevel handle = ((CraftWorld)world).getHandle();
        return new OldCraftChunkData(world.getMinHeight(), world.getMaxHeight(), (net.minecraft.core.Registry<Biome>)handle.m_9598_().m_175515_(Registries.f_256952_));
    }

    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag ... flags) {
        return new CraftBossBar(title, color, style, flags);
    }

    @Override
    public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor barColor, BarStyle barStyle, BarFlag ... barFlags) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"NamespacedKey key cannot be null");
        Preconditions.checkArgument((barColor != null ? 1 : 0) != 0, (Object)"BarColor key cannot be null");
        Preconditions.checkArgument((barStyle != null ? 1 : 0) != 0, (Object)"BarStyle key cannot be null");
        CustomBossEvent bossBattleCustom = this.getServer().m_129901_().m_136299_(CraftNamespacedKey.toMinecraft(key), CraftChatMessage.fromString(title, true)[0]);
        CraftKeyedBossbar craftKeyedBossbar = new CraftKeyedBossbar(bossBattleCustom);
        craftKeyedBossbar.setColor(barColor);
        craftKeyedBossbar.setStyle(barStyle);
        BarFlag[] barFlagArray = barFlags;
        int n = barFlags.length;
        int n2 = 0;
        while (n2 < n) {
            BarFlag flag = barFlagArray[n2];
            if (flag != null) {
                craftKeyedBossbar.addFlag(flag);
            }
            ++n2;
        }
        return craftKeyedBossbar;
    }

    @Override
    public Iterator<KeyedBossBar> getBossBars() {
        return Iterators.unmodifiableIterator((Iterator)Iterators.transform(this.getServer().m_129901_().m_136304_().iterator(), (Function)new Function<CustomBossEvent, KeyedBossBar>(){

            public KeyedBossBar apply(CustomBossEvent bossBattleCustom) {
                return bossBattleCustom.getBukkitEntity();
            }
        }));
    }

    @Override
    public KeyedBossBar getBossBar(NamespacedKey key) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"key");
        CustomBossEvent bossBattleCustom = this.getServer().m_129901_().m_136297_(CraftNamespacedKey.toMinecraft(key));
        return bossBattleCustom == null ? null : bossBattleCustom.getBukkitEntity();
    }

    @Override
    public boolean removeBossBar(NamespacedKey key) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"key");
        CustomBossEvents bossBattleCustomData = this.getServer().m_129901_();
        CustomBossEvent bossBattleCustom = bossBattleCustomData.m_136297_(CraftNamespacedKey.toMinecraft(key));
        if (bossBattleCustom != null) {
            bossBattleCustomData.m_136302_(bossBattleCustom);
            return true;
        }
        return false;
    }

    @Override
    public Entity getEntity(UUID uuid) {
        Preconditions.checkArgument((uuid != null ? 1 : 0) != 0, (Object)"UUID id cannot be null");
        for (ServerLevel world : this.getServer().m_129785_()) {
            net.minecraft.world.entity.Entity entity = world.m_8791_(uuid);
            if (entity == null) continue;
            return entity.getBukkitEntity();
        }
        return null;
    }

    @Override
    public org.bukkit.advancement.Advancement getAdvancement(NamespacedKey key) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"NamespacedKey key cannot be null");
        Advancement advancement = this.console.m_129889_().m_136041_(CraftNamespacedKey.toMinecraft(key));
        return advancement == null ? null : advancement.bukkit;
    }

    @Override
    public Iterator<org.bukkit.advancement.Advancement> advancementIterator() {
        return Iterators.unmodifiableIterator((Iterator)Iterators.transform(this.console.m_129889_().m_136028_().iterator(), (Function)new Function<Advancement, org.bukkit.advancement.Advancement>(){

            public org.bukkit.advancement.Advancement apply(Advancement advancement) {
                return advancement.bukkit;
            }
        }));
    }

    @Override
    public BlockData createBlockData(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        return this.createBlockData(material, (String)null);
    }

    @Override
    public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
        BlockData data = this.createBlockData(material);
        if (consumer != null) {
            consumer.accept(data);
        }
        return data;
    }

    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"data cannot be null");
        return this.createBlockData(null, data);
    }

    @Override
    public BlockData createBlockData(Material material, String data) {
        Preconditions.checkArgument((material != null || data != null ? 1 : 0) != 0, (Object)"Must provide one of material or data");
        return CraftBlockData.newData(material, data);
    }

    @Override
    public <T extends Keyed> Tag<T> getTag(String registry, NamespacedKey tag, Class<T> clazz) {
        Preconditions.checkArgument((registry != null ? 1 : 0) != 0, (Object)"registry cannot be null");
        Preconditions.checkArgument((tag != null ? 1 : 0) != 0, (Object)"NamespacedKey tag cannot be null");
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Class clazz cannot be null");
        ResourceLocation key = CraftNamespacedKey.toMinecraft(tag);
        switch (registry) {
            case "blocks": {
                Preconditions.checkArgument((clazz == Material.class ? 1 : 0) != 0, (String)"Block namespace (%s) must have material type", (Object)clazz.getName());
                TagKey blockTagKey = TagKey.m_203882_((ResourceKey)Registries.f_256747_, (ResourceLocation)key);
                if (!BuiltInRegistries.f_256975_.m_203431_(blockTagKey).isPresent()) break;
                return new CraftBlockTag((net.minecraft.core.Registry<Block>)BuiltInRegistries.f_256975_, (TagKey<Block>)blockTagKey);
            }
            case "items": {
                Preconditions.checkArgument((clazz == Material.class ? 1 : 0) != 0, (String)"Item namespace (%s) must have material type", (Object)clazz.getName());
                TagKey itemTagKey = TagKey.m_203882_((ResourceKey)Registries.f_256913_, (ResourceLocation)key);
                if (!BuiltInRegistries.f_257033_.m_203431_(itemTagKey).isPresent()) break;
                return new CraftItemTag((net.minecraft.core.Registry<Item>)BuiltInRegistries.f_257033_, (TagKey<Item>)itemTagKey);
            }
            case "fluids": {
                Preconditions.checkArgument((clazz == Fluid.class ? 1 : 0) != 0, (String)"Fluid namespace (%s) must have fluid type", (Object)clazz.getName());
                TagKey fluidTagKey = TagKey.m_203882_((ResourceKey)Registries.f_256808_, (ResourceLocation)key);
                if (!BuiltInRegistries.f_257020_.m_203431_(fluidTagKey).isPresent()) break;
                return new CraftFluidTag((net.minecraft.core.Registry<net.minecraft.world.level.material.Fluid>)BuiltInRegistries.f_257020_, (TagKey<net.minecraft.world.level.material.Fluid>)fluidTagKey);
            }
            case "entity_types": {
                Preconditions.checkArgument((clazz == org.bukkit.entity.EntityType.class ? 1 : 0) != 0, (String)"Entity type namespace (%s) must have entity type", (Object)clazz.getName());
                TagKey entityTagKey = TagKey.m_203882_((ResourceKey)Registries.f_256939_, (ResourceLocation)key);
                if (!BuiltInRegistries.f_256780_.m_203431_(entityTagKey).isPresent()) break;
                return new CraftEntityTag((net.minecraft.core.Registry<EntityType<?>>)BuiltInRegistries.f_256780_, (TagKey<EntityType<?>>)entityTagKey);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        return null;
    }

    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) {
        Preconditions.checkArgument((registry != null ? 1 : 0) != 0, (Object)"registry cannot be null");
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Class clazz cannot be null");
        switch (registry) {
            case "blocks": {
                Preconditions.checkArgument((clazz == Material.class ? 1 : 0) != 0, (String)"Block namespace (%s) must have material type", (Object)clazz.getName());
                DefaultedRegistry blockTags = BuiltInRegistries.f_256975_;
                return (Iterable)blockTags.m_203612_().map(arg_0 -> CraftServer.lambda$8((net.minecraft.core.Registry)blockTags, arg_0)).collect(ImmutableList.toImmutableList());
            }
            case "items": {
                Preconditions.checkArgument((clazz == Material.class ? 1 : 0) != 0, (String)"Item namespace (%s) must have material type", (Object)clazz.getName());
                DefaultedRegistry itemTags = BuiltInRegistries.f_257033_;
                return (Iterable)itemTags.m_203612_().map(arg_0 -> CraftServer.lambda$9((net.minecraft.core.Registry)itemTags, arg_0)).collect(ImmutableList.toImmutableList());
            }
            case "fluids": {
                Preconditions.checkArgument((clazz == Material.class ? 1 : 0) != 0, (String)"Fluid namespace (%s) must have fluid type", (Object)clazz.getName());
                DefaultedRegistry fluidTags = BuiltInRegistries.f_257020_;
                return (Iterable)fluidTags.m_203612_().map(arg_0 -> CraftServer.lambda$10((net.minecraft.core.Registry)fluidTags, arg_0)).collect(ImmutableList.toImmutableList());
            }
            case "entity_types": {
                Preconditions.checkArgument((clazz == org.bukkit.entity.EntityType.class ? 1 : 0) != 0, (String)"Entity type namespace (%s) must have entity type", (Object)clazz.getName());
                DefaultedRegistry entityTags = BuiltInRegistries.f_256780_;
                return (Iterable)entityTags.m_203612_().map(arg_0 -> CraftServer.lambda$11((net.minecraft.core.Registry)entityTags, arg_0)).collect(ImmutableList.toImmutableList());
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public LootTable getLootTable(NamespacedKey key) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"NamespacedKey key cannot be null");
        LootDataManager registry = this.getServer().m_278653_();
        return new CraftLootTable(key, registry.m_278676_(CraftNamespacedKey.toMinecraft(key)));
    }

    @Override
    public List<Entity> selectEntities(CommandSender sender, String selector) {
        List nms;
        Preconditions.checkArgument((selector != null ? 1 : 0) != 0, (Object)"selector cannot be null");
        Preconditions.checkArgument((sender != null ? 1 : 0) != 0, (Object)"CommandSender sender cannot be null");
        EntityArgument arg = EntityArgument.m_91460_();
        try {
            StringReader reader = new StringReader(selector);
            nms = arg.parse(reader, true).m_121160_(VanillaCommandWrapper.getListener(sender));
            Preconditions.checkArgument((!reader.canRead() ? 1 : 0) != 0, (String)"Spurious trailing data in selector: %s", (Object)selector);
        }
        catch (CommandSyntaxException ex) {
            throw new IllegalArgumentException("Could not parse selector: " + selector, ex);
        }
        return new ArrayList<Entity>(Lists.transform((List)nms, entity -> entity.getBukkitEntity()));
    }

    @Override
    public StructureManager getStructureManager() {
        return this.structureManager;
    }

    @Override
    public <T extends Keyed> Registry<T> getRegistry(Class<T> aClass) {
        return this.registries.computeIfAbsent(aClass, key -> CraftRegistry.createRegistry(aClass, (RegistryAccess)this.console.m_206579_()));
    }

    @Override
    @Deprecated
    public UnsafeValues getUnsafe() {
        return CraftMagicNumbers.INSTANCE;
    }

    @Override
    public Server.Spigot spigot() {
        return this.spigot;
    }

    private static /* synthetic */ Tag lambda$8(net.minecraft.core.Registry registry, Pair pair) {
        return new CraftBlockTag((net.minecraft.core.Registry<Block>)registry, (TagKey<Block>)((TagKey)pair.getFirst()));
    }

    private static /* synthetic */ Tag lambda$9(net.minecraft.core.Registry registry, Pair pair) {
        return new CraftItemTag((net.minecraft.core.Registry<Item>)registry, (TagKey<Item>)((TagKey)pair.getFirst()));
    }

    private static /* synthetic */ Tag lambda$10(net.minecraft.core.Registry registry, Pair pair) {
        return new CraftFluidTag((net.minecraft.core.Registry<net.minecraft.world.level.material.Fluid>)registry, (TagKey<net.minecraft.world.level.material.Fluid>)((TagKey)pair.getFirst()));
    }

    private static /* synthetic */ Tag lambda$11(net.minecraft.core.Registry registry, Pair pair) {
        return new CraftEntityTag((net.minecraft.core.Registry<EntityType<?>>)registry, (TagKey<EntityType<?>>)((TagKey)pair.getFirst()));
    }
}

