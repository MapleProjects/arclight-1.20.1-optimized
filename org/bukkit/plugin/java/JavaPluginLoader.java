/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.yaml.snakeyaml.error.YAMLException
 */
package org.bukkit.plugin.java;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.bukkit.Server;
import org.bukkit.Warning;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.LibraryLoader;
import org.bukkit.plugin.java.PluginClassLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.CustomTimingsHandler;
import org.yaml.snakeyaml.error.YAMLException;

public final class JavaPluginLoader
implements PluginLoader {
    final Server server;
    private final Pattern[] fileFilters = new Pattern[]{Pattern.compile("\\.jar$")};
    private final List<PluginClassLoader> loaders = new CopyOnWriteArrayList<PluginClassLoader>();
    private final LibraryLoader libraryLoader;
    public static final CustomTimingsHandler pluginParentTimer = new CustomTimingsHandler("** Plugins");

    @Deprecated
    public JavaPluginLoader(@NotNull Server instance) {
        Preconditions.checkArgument((instance != null ? 1 : 0) != 0, (Object)"Server cannot be null");
        this.server = instance;
        LibraryLoader libraryLoader = null;
        try {
            libraryLoader = new LibraryLoader(this.server.getLogger());
        }
        catch (NoClassDefFoundError ex) {
            this.server.getLogger().warning("Could not initialize LibraryLoader (missing dependencies?)");
        }
        this.libraryLoader = libraryLoader;
    }

    @Override
    @NotNull
    public Plugin loadPlugin(@NotNull File file) throws InvalidPluginException {
        PluginClassLoader loader;
        PluginDescriptionFile description;
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        if (!file.exists()) {
            throw new InvalidPluginException(new FileNotFoundException(String.valueOf(file.getPath()) + " does not exist"));
        }
        try {
            description = this.getPluginDescription(file);
        }
        catch (InvalidDescriptionException ex) {
            throw new InvalidPluginException(ex);
        }
        File parentFile = file.getParentFile();
        File dataFolder = new File(parentFile, description.getName());
        File oldDataFolder = new File(parentFile, description.getRawName());
        if (!dataFolder.equals(oldDataFolder)) {
            if (dataFolder.isDirectory() && oldDataFolder.isDirectory()) {
                this.server.getLogger().warning(String.format("While loading %s (%s) found old-data folder: `%s' next to the new one `%s'", description.getFullName(), file, oldDataFolder, dataFolder));
            } else if (oldDataFolder.isDirectory() && !dataFolder.exists()) {
                if (!oldDataFolder.renameTo(dataFolder)) {
                    throw new InvalidPluginException("Unable to rename old data folder: `" + oldDataFolder + "' to: `" + dataFolder + "'");
                }
                this.server.getLogger().log(Level.INFO, String.format("While loading %s (%s) renamed data folder: `%s' to `%s'", description.getFullName(), file, oldDataFolder, dataFolder));
            }
        }
        if (dataFolder.exists() && !dataFolder.isDirectory()) {
            throw new InvalidPluginException(String.format("Projected datafolder: `%s' for %s (%s) exists and is not a directory", dataFolder, description.getFullName(), file));
        }
        for (String pluginName : description.getDepend()) {
            Plugin current = this.server.getPluginManager().getPlugin(pluginName);
            if (current != null) continue;
            throw new UnknownDependencyException("Unknown dependency " + pluginName + ". Please download and install " + pluginName + " to run this plugin.");
        }
        this.server.getUnsafe().checkSupported(description);
        try {
            loader = new PluginClassLoader(this, this.getClass().getClassLoader(), description, dataFolder, file, this.libraryLoader != null ? this.libraryLoader.createLoader(description) : null);
        }
        catch (InvalidPluginException ex) {
            throw ex;
        }
        catch (Throwable ex) {
            throw new InvalidPluginException(ex);
        }
        this.loaders.add(loader);
        return loader.plugin;
    }

    @Override
    @NotNull
    public PluginDescriptionFile getPluginDescription(@NotNull File file) throws InvalidDescriptionException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        JarFile jar = null;
        InputStream stream = null;
        try {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain plugin.yml"));
            }
            stream = jar.getInputStream(entry);
            PluginDescriptionFile pluginDescriptionFile = new PluginDescriptionFile(stream);
            return pluginDescriptionFile;
        }
        catch (IOException ex) {
            throw new InvalidDescriptionException(ex);
        }
        catch (YAMLException ex) {
            throw new InvalidDescriptionException(ex);
        }
        finally {
            if (jar != null) {
                try {
                    jar.close();
                }
                catch (IOException iOException) {}
            }
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException iOException) {}
            }
        }
    }

    @Override
    @NotNull
    public Pattern[] getPluginFileFilters() {
        return (Pattern[])this.fileFilters.clone();
    }

    @Nullable
    Class<?> getClassByName(String name, boolean resolve, PluginDescriptionFile description) {
        for (PluginClassLoader loader : this.loaders) {
            try {
                return loader.loadClass0(name, resolve, false, ((SimplePluginManager)this.server.getPluginManager()).isTransitiveDepend(description, loader.plugin.getDescription()));
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return null;
    }

    void setClass(@NotNull String name, @NotNull Class<?> clazz) {
        if (ConfigurationSerializable.class.isAssignableFrom(clazz)) {
            Class<ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
            ConfigurationSerialization.registerClass(serializable);
        }
    }

    private void removeClass(@NotNull Class<?> clazz) {
        if (ConfigurationSerializable.class.isAssignableFrom(clazz)) {
            Class<ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
            ConfigurationSerialization.unregisterClass(serializable);
        }
    }

    @Override
    @NotNull
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener, @NotNull Plugin plugin) {
        HashSet<Method> methods;
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin can not be null");
        Preconditions.checkArgument((listener != null ? 1 : 0) != 0, (Object)"Listener can not be null");
        boolean useTimings = this.server.getPluginManager().useTimings();
        HashMap<Class<? extends Event>, Set<RegisteredListener>> ret = new HashMap<Class<? extends Event>, Set<RegisteredListener>>();
        try {
            Method method;
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet<Method>(publicMethods.length + privateMethods.length, 1.0f);
            Method[] methodArray = publicMethods;
            int n = publicMethods.length;
            int n2 = 0;
            while (n2 < n) {
                method = methodArray[n2];
                methods.add(method);
                ++n2;
            }
            methodArray = privateMethods;
            n = privateMethods.length;
            n2 = 0;
            while (n2 < n) {
                method = methodArray[n2];
                methods.add(method);
                ++n2;
            }
        }
        catch (NoClassDefFoundError e) {
            plugin.getLogger().severe("Plugin " + plugin.getDescription().getFullName() + " has failed to register events for " + listener.getClass() + " because " + e.getMessage() + " does not exist.");
            return ret;
        }
        for (final Method method : methods) {
            Class<?> checkClass;
            EventHandler eh = method.getAnnotation(EventHandler.class);
            if (eh == null || method.isBridge() || method.isSynthetic()) continue;
            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                plugin.getLogger().severe(String.valueOf(plugin.getDescription().getFullName()) + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + listener.getClass());
                continue;
            }
            final Class<Event> eventClass = checkClass.asSubclass(Event.class);
            method.setAccessible(true);
            HashSet<RegisteredListener> eventSet = (HashSet<RegisteredListener>)ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet<RegisteredListener>();
                ret.put(eventClass, eventSet);
            }
            Class<Event> clazz = eventClass;
            while (Event.class.isAssignableFrom(clazz)) {
                if (clazz.getAnnotation(Deprecated.class) != null) {
                    Warning warning = clazz.getAnnotation(Warning.class);
                    Warning.WarningState warningState = this.server.getWarningState();
                    if (!warningState.printFor(warning)) break;
                    plugin.getLogger().log(Level.WARNING, String.format("\"%s\" has registered a listener for %s on method \"%s\", but the event is Deprecated. \"%s\"; please notify the authors %s.", plugin.getDescription().getFullName(), clazz.getName(), method.toGenericString(), warning != null && warning.reason().length() != 0 ? warning.reason() : "Server performance will be affected", Arrays.toString(plugin.getDescription().getAuthors().toArray())), warningState == Warning.WarningState.ON ? new AuthorNagException(null) : null);
                    break;
                }
                clazz = clazz.getSuperclass();
            }
            final CustomTimingsHandler timings = new CustomTimingsHandler("Plugin: " + plugin.getDescription().getFullName() + " Event: " + listener.getClass().getName() + "::" + method.getName() + "(" + eventClass.getSimpleName() + ")", pluginParentTimer);
            EventExecutor executor = new EventExecutor(){

                @Override
                public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
                    try {
                        if (!eventClass.isAssignableFrom(event.getClass())) {
                            return;
                        }
                        boolean isAsync = event.isAsynchronous();
                        if (!isAsync) {
                            timings.startTiming();
                        }
                        method.invoke(listener, event);
                        if (!isAsync) {
                            timings.stopTiming();
                        }
                    }
                    catch (InvocationTargetException ex) {
                        throw new EventException(ex.getCause());
                    }
                    catch (Throwable t) {
                        throw new EventException(t);
                    }
                }
            };
            eventSet.add(new RegisteredListener(listener, executor, eh.priority(), plugin, eh.ignoreCancelled()));
        }
        return ret;
    }

    @Override
    public void enablePlugin(@NotNull Plugin plugin) {
        Preconditions.checkArgument((boolean)(plugin instanceof JavaPlugin), (Object)"Plugin is not associated with this PluginLoader");
        if (!plugin.isEnabled()) {
            plugin.getLogger().info("Enabling " + plugin.getDescription().getFullName());
            JavaPlugin jPlugin = (JavaPlugin)plugin;
            PluginClassLoader pluginLoader = (PluginClassLoader)jPlugin.getClassLoader();
            if (!this.loaders.contains(pluginLoader)) {
                this.loaders.add(pluginLoader);
                this.server.getLogger().log(Level.WARNING, "Enabled plugin with unregistered PluginClassLoader " + plugin.getDescription().getFullName());
            }
            try {
                jPlugin.setEnabled(true);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            this.server.getPluginManager().callEvent(new PluginEnableEvent(plugin));
        }
    }

    @Override
    public void disablePlugin(@NotNull Plugin plugin) {
        Preconditions.checkArgument((boolean)(plugin instanceof JavaPlugin), (Object)"Plugin is not associated with this PluginLoader");
        if (plugin.isEnabled()) {
            String message = String.format("Disabling %s", plugin.getDescription().getFullName());
            plugin.getLogger().info(message);
            this.server.getPluginManager().callEvent(new PluginDisableEvent(plugin));
            JavaPlugin jPlugin = (JavaPlugin)plugin;
            ClassLoader cloader = jPlugin.getClassLoader();
            try {
                jPlugin.setEnabled(false);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            if (cloader instanceof PluginClassLoader) {
                PluginClassLoader loader = (PluginClassLoader)cloader;
                this.loaders.remove(loader);
                Collection<Class<?>> classes = loader.getClasses();
                for (Class<?> clazz : classes) {
                    this.removeClass(clazz);
                }
                try {
                    loader.close();
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
    }
}

