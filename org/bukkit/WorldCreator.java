/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorldCreator {
    private final String name;
    private long seed;
    private World.Environment environment = World.Environment.NORMAL;
    private ChunkGenerator generator = null;
    private BiomeProvider biomeProvider = null;
    private WorldType type = WorldType.NORMAL;
    private boolean generateStructures = true;
    private String generatorSettings = "";
    private boolean hardcore = false;

    public WorldCreator(@NotNull String name) {
        if (name == null) {
            throw new IllegalArgumentException("World name cannot be null");
        }
        this.name = name;
        this.seed = new Random().nextLong();
    }

    @NotNull
    public WorldCreator copy(@NotNull World world) {
        if (world == null) {
            throw new IllegalArgumentException("World cannot be null");
        }
        this.seed = world.getSeed();
        this.environment = world.getEnvironment();
        this.generator = world.getGenerator();
        this.biomeProvider = world.getBiomeProvider();
        this.type = world.getWorldType();
        this.generateStructures = world.canGenerateStructures();
        this.hardcore = world.isHardcore();
        return this;
    }

    @NotNull
    public WorldCreator copy(@NotNull WorldCreator creator) {
        if (creator == null) {
            throw new IllegalArgumentException("Creator cannot be null");
        }
        this.seed = creator.seed();
        this.environment = creator.environment();
        this.generator = creator.generator();
        this.biomeProvider = creator.biomeProvider();
        this.type = creator.type();
        this.generateStructures = creator.generateStructures();
        this.generatorSettings = creator.generatorSettings();
        this.hardcore = creator.hardcore();
        return this;
    }

    @NotNull
    public String name() {
        return this.name;
    }

    public long seed() {
        return this.seed;
    }

    @NotNull
    public WorldCreator seed(long seed) {
        this.seed = seed;
        return this;
    }

    @NotNull
    public World.Environment environment() {
        return this.environment;
    }

    @NotNull
    public WorldCreator environment(@NotNull World.Environment env) {
        this.environment = env;
        return this;
    }

    @NotNull
    public WorldType type() {
        return this.type;
    }

    @NotNull
    public WorldCreator type(@NotNull WorldType type) {
        this.type = type;
        return this;
    }

    @Nullable
    public ChunkGenerator generator() {
        return this.generator;
    }

    @NotNull
    public WorldCreator generator(@Nullable ChunkGenerator generator) {
        this.generator = generator;
        return this;
    }

    @NotNull
    public WorldCreator generator(@Nullable String generator) {
        this.generator = WorldCreator.getGeneratorForName(this.name, generator, Bukkit.getConsoleSender());
        return this;
    }

    @NotNull
    public WorldCreator generator(@Nullable String generator, @Nullable CommandSender output) {
        this.generator = WorldCreator.getGeneratorForName(this.name, generator, output);
        return this;
    }

    @Nullable
    public BiomeProvider biomeProvider() {
        return this.biomeProvider;
    }

    @NotNull
    public WorldCreator biomeProvider(@Nullable BiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;
        return this;
    }

    @NotNull
    public WorldCreator biomeProvider(@Nullable String biomeProvider) {
        this.biomeProvider = WorldCreator.getBiomeProviderForName(this.name, biomeProvider, Bukkit.getConsoleSender());
        return this;
    }

    @NotNull
    public WorldCreator biomeProvider(@Nullable String biomeProvider, @Nullable CommandSender output) {
        this.biomeProvider = WorldCreator.getBiomeProviderForName(this.name, biomeProvider, output);
        return this;
    }

    @NotNull
    public WorldCreator generatorSettings(@NotNull String generatorSettings) {
        this.generatorSettings = generatorSettings;
        return this;
    }

    @NotNull
    public String generatorSettings() {
        return this.generatorSettings;
    }

    @NotNull
    public WorldCreator generateStructures(boolean generate) {
        this.generateStructures = generate;
        return this;
    }

    public boolean generateStructures() {
        return this.generateStructures;
    }

    @NotNull
    public WorldCreator hardcore(boolean hardcore) {
        this.hardcore = hardcore;
        return this;
    }

    public boolean hardcore() {
        return this.hardcore;
    }

    @Nullable
    public World createWorld() {
        return Bukkit.createWorld(this);
    }

    @NotNull
    public static WorldCreator name(@NotNull String name) {
        return new WorldCreator(name);
    }

    @Nullable
    public static ChunkGenerator getGeneratorForName(@NotNull String world, @Nullable String name, @Nullable CommandSender output) {
        ChunkGenerator result = null;
        if (world == null) {
            throw new IllegalArgumentException("World name must be specified");
        }
        if (output == null) {
            output = Bukkit.getConsoleSender();
        }
        if (name != null) {
            String[] split = name.split(":", 2);
            String id = split.length > 1 ? split[1] : null;
            Plugin plugin = Bukkit.getPluginManager().getPlugin(split[0]);
            if (plugin == null) {
                output.sendMessage("Could not set generator for world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                output.sendMessage("Could not set generator for world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled");
            } else {
                result = plugin.getDefaultWorldGenerator(world, id);
            }
        }
        return result;
    }

    @Nullable
    public static BiomeProvider getBiomeProviderForName(@NotNull String world, @Nullable String name, @Nullable CommandSender output) {
        BiomeProvider result = null;
        if (world == null) {
            throw new IllegalArgumentException("World name must be specified");
        }
        if (output == null) {
            output = Bukkit.getConsoleSender();
        }
        if (name != null) {
            String[] split = name.split(":", 2);
            String id = split.length > 1 ? split[1] : null;
            Plugin plugin = Bukkit.getPluginManager().getPlugin(split[0]);
            if (plugin == null) {
                output.sendMessage("Could not set biome provider for world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                output.sendMessage("Could not set set biome provider for world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled");
            } else {
                result = plugin.getDefaultBiomeProvider(world, id);
            }
        }
        return result;
    }
}

