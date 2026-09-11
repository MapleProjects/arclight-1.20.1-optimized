/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Plugin
extends TabExecutor {
    @NotNull
    public File getDataFolder();

    @NotNull
    public PluginDescriptionFile getDescription();

    @NotNull
    public FileConfiguration getConfig();

    @Nullable
    public InputStream getResource(@NotNull String var1);

    public void saveConfig();

    public void saveDefaultConfig();

    public void saveResource(@NotNull String var1, boolean var2);

    public void reloadConfig();

    @NotNull
    public PluginLoader getPluginLoader();

    @NotNull
    public Server getServer();

    public boolean isEnabled();

    public void onDisable();

    public void onLoad();

    public void onEnable();

    public boolean isNaggable();

    public void setNaggable(boolean var1);

    @Nullable
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String var1, @Nullable String var2);

    @Nullable
    public BiomeProvider getDefaultBiomeProvider(@NotNull String var1, @Nullable String var2);

    @NotNull
    public Logger getLogger();

    @NotNull
    public String getName();
}

