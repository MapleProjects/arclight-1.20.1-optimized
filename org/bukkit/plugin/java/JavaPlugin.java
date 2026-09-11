/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin.java;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.java.PluginClassLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class JavaPlugin
extends PluginBase {
    private boolean isEnabled = false;
    private PluginLoader loader = null;
    private Server server = null;
    private File file = null;
    private PluginDescriptionFile description = null;
    private File dataFolder = null;
    private ClassLoader classLoader = null;
    private boolean naggable = true;
    private FileConfiguration newConfig = null;
    private File configFile = null;
    private PluginLogger logger = null;

    public JavaPlugin() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        if (!(classLoader instanceof PluginClassLoader)) {
            throw new IllegalStateException("JavaPlugin requires " + PluginClassLoader.class.getName());
        }
        ((PluginClassLoader)classLoader).initialize(this);
    }

    protected JavaPlugin(@NotNull JavaPluginLoader loader, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        if (classLoader instanceof PluginClassLoader) {
            throw new IllegalStateException("Cannot use initialization constructor at runtime");
        }
        this.init(loader, loader.server, description, dataFolder, file, classLoader);
    }

    @Override
    @NotNull
    public final File getDataFolder() {
        return this.dataFolder;
    }

    @Override
    @NotNull
    public final PluginLoader getPluginLoader() {
        return this.loader;
    }

    @Override
    @NotNull
    public final Server getServer() {
        return this.server;
    }

    @Override
    public final boolean isEnabled() {
        return this.isEnabled;
    }

    @NotNull
    protected File getFile() {
        return this.file;
    }

    @Override
    @NotNull
    public final PluginDescriptionFile getDescription() {
        return this.description;
    }

    @Override
    @NotNull
    public FileConfiguration getConfig() {
        if (this.newConfig == null) {
            this.reloadConfig();
        }
        return this.newConfig;
    }

    @Nullable
    protected final Reader getTextResource(@NotNull String file) {
        InputStream in = this.getResource(file);
        return in == null ? null : new InputStreamReader(in, Charsets.UTF_8);
    }

    @Override
    public void reloadConfig() {
        this.newConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defConfigStream = this.getResource("config.yml");
        if (defConfigStream == null) {
            return;
        }
        this.newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
    }

    @Override
    public void saveConfig() {
        try {
            this.getConfig().save(this.configFile);
        }
        catch (IOException ex) {
            this.logger.log(Level.SEVERE, "Could not save config to " + this.configFile, ex);
        }
    }

    @Override
    public void saveDefaultConfig() {
        if (!this.configFile.exists()) {
            this.saveResource("config.yml", false);
        }
    }

    @Override
    public void saveResource(@NotNull String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }
        InputStream in = this.getResource(resourcePath = resourcePath.replace('\\', '/'));
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + this.file);
        }
        File outFile = new File(this.dataFolder, resourcePath);
        int lastIndex = resourcePath.lastIndexOf(47);
        File outDir = new File(this.dataFolder, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        try {
            if (!outFile.exists() || replace) {
                int len;
                FileOutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) > 0) {
                    ((OutputStream)out).write(buf, 0, len);
                }
                ((OutputStream)out).close();
                in.close();
            } else {
                this.logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        }
        catch (IOException ex) {
            this.logger.log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }

    @Override
    @Nullable
    public InputStream getResource(@NotNull String filename) {
        URL url;
        block4: {
            if (filename == null) {
                throw new IllegalArgumentException("Filename cannot be null");
            }
            try {
                url = this.getClassLoader().getResource(filename);
                if (url != null) break block4;
                return null;
            }
            catch (IOException ex) {
                return null;
            }
        }
        URLConnection connection = url.openConnection();
        connection.setUseCaches(false);
        return connection.getInputStream();
    }

    @NotNull
    protected final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    protected final void setEnabled(boolean enabled) {
        if (this.isEnabled != enabled) {
            this.isEnabled = enabled;
            if (this.isEnabled) {
                this.onEnable();
            } else {
                this.onDisable();
            }
        }
    }

    final void init(@NotNull PluginLoader loader, @NotNull Server server, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file, @NotNull ClassLoader classLoader) {
        this.loader = loader;
        this.server = server;
        this.file = file;
        this.description = description;
        this.dataFolder = dataFolder;
        this.classLoader = classLoader;
        this.configFile = new File(dataFolder, "config.yml");
        this.logger = new PluginLogger(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

    @Nullable
    public PluginCommand getCommand(@NotNull String name) {
        String alias = name.toLowerCase(Locale.ENGLISH);
        PluginCommand command = this.getServer().getPluginCommand(alias);
        if (command == null || command.getPlugin() != this) {
            command = this.getServer().getPluginCommand(String.valueOf(this.description.getName().toLowerCase(Locale.ENGLISH)) + ":" + alias);
        }
        if (command != null && command.getPlugin() == this) {
            return command;
        }
        return null;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    @Nullable
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        return null;
    }

    @Override
    @Nullable
    public BiomeProvider getDefaultBiomeProvider(@NotNull String worldName, @Nullable String id) {
        return null;
    }

    @Override
    public final boolean isNaggable() {
        return this.naggable;
    }

    @Override
    public final void setNaggable(boolean canNag) {
        this.naggable = canNag;
    }

    @Override
    @NotNull
    public Logger getLogger() {
        return this.logger;
    }

    @NotNull
    public String toString() {
        return this.description.getFullName();
    }

    @NotNull
    public static <T extends JavaPlugin> T getPlugin(@NotNull Class<T> clazz) {
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Null class cannot have a plugin");
        if (!JavaPlugin.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz + " does not extend " + JavaPlugin.class);
        }
        ClassLoader cl = clazz.getClassLoader();
        if (!(cl instanceof PluginClassLoader)) {
            throw new IllegalArgumentException(clazz + " is not initialized by " + PluginClassLoader.class);
        }
        JavaPlugin plugin = ((PluginClassLoader)cl).plugin;
        if (plugin == null) {
            throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
        }
        return (T)((JavaPlugin)clazz.cast(plugin));
    }

    @NotNull
    public static JavaPlugin getProvidingPlugin(@NotNull Class<?> clazz) {
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Null class cannot have a plugin");
        ClassLoader cl = clazz.getClassLoader();
        if (!(cl instanceof PluginClassLoader)) {
            throw new IllegalArgumentException(clazz + " is not provided by " + PluginClassLoader.class);
        }
        JavaPlugin plugin = ((PluginClassLoader)cl).plugin;
        if (plugin == null) {
            throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
        }
        return plugin;
    }
}

