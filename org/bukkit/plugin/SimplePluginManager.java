/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.graph.GraphBuilder
 *  com.google.common.graph.Graphs
 *  com.google.common.graph.MutableGraph
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableGraph;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.TimingsCommand;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.TimedRegisteredListener;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.util.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SimplePluginManager
implements PluginManager {
    private final Server server;
    private final Map<Pattern, PluginLoader> fileAssociations = new HashMap<Pattern, PluginLoader>();
    private final List<Plugin> plugins = new ArrayList<Plugin>();
    private final Map<String, Plugin> lookupNames = new HashMap<String, Plugin>();
    private MutableGraph<String> dependencyGraph = GraphBuilder.directed().build();
    private File updateDirectory;
    private final SimpleCommandMap commandMap;
    private final Map<String, Permission> permissions = new HashMap<String, Permission>();
    private final Map<Boolean, Set<Permission>> defaultPerms = new LinkedHashMap<Boolean, Set<Permission>>();
    private final Map<String, Map<Permissible, Boolean>> permSubs = new HashMap<String, Map<Permissible, Boolean>>();
    private final Map<Boolean, Map<Permissible, Boolean>> defSubs = new HashMap<Boolean, Map<Permissible, Boolean>>();
    private boolean useTimings = false;

    public SimplePluginManager(@NotNull Server instance, @NotNull SimpleCommandMap commandMap) {
        this.server = instance;
        this.commandMap = commandMap;
        this.defaultPerms.put(true, new LinkedHashSet());
        this.defaultPerms.put(false, new LinkedHashSet());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void registerInterface(@NotNull Class<? extends PluginLoader> loader) throws IllegalArgumentException {
        PluginLoader instance;
        if (PluginLoader.class.isAssignableFrom(loader)) {
            try {
                Constructor<? extends PluginLoader> constructor = loader.getConstructor(Server.class);
                instance = constructor.newInstance(this.server);
            }
            catch (NoSuchMethodException ex) {
                String className = loader.getName();
                throw new IllegalArgumentException(String.format("Class %s does not have a public %s(Server) constructor", className, className), ex);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException(String.format("Unexpected exception %s while attempting to construct a new instance of %s", ex.getClass().getName(), loader.getName()), ex);
            }
        } else {
            throw new IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", loader.getName()));
        }
        Pattern[] patterns = instance.getPluginFileFilters();
        SimplePluginManager simplePluginManager = this;
        synchronized (simplePluginManager) {
            Pattern[] patternArray = patterns;
            int n = patterns.length;
            int n2 = 0;
            while (n2 < n) {
                Pattern pattern = patternArray[n2];
                this.fileAssociations.put(pattern, instance);
                ++n2;
            }
        }
    }

    @Override
    @NotNull
    public Plugin[] loadPlugins(@NotNull File directory) {
        Preconditions.checkArgument((directory != null ? 1 : 0) != 0, (Object)"Directory cannot be null");
        Preconditions.checkArgument((boolean)directory.isDirectory(), (Object)"Directory must be a directory");
        ArrayList<Plugin> result = new ArrayList<Plugin>();
        Set<Pattern> filters = this.fileAssociations.keySet();
        if (!this.server.getUpdateFolder().equals("")) {
            this.updateDirectory = new File(directory, this.server.getUpdateFolder());
        }
        HashMap<String, File> plugins = new HashMap<String, File>();
        HashSet<String> loadedPlugins = new HashSet<String>();
        HashMap<String, String> pluginsProvided = new HashMap<String, String>();
        HashMap<String, LinkedList<String>> dependencies = new HashMap<String, LinkedList<String>>();
        HashMap softDependencies = new HashMap();
        File[] fileArray = directory.listFiles();
        int n = fileArray.length;
        int n2 = 0;
        while (n2 < n) {
            block38: {
                List<String> loadBeforeSet;
                List<String> dependencySet;
                String removedProvided;
                PluginDescriptionFile description;
                File file;
                block39: {
                    file = fileArray[n2];
                    PluginLoader loader = null;
                    for (Pattern filter : filters) {
                        Matcher match = filter.matcher(file.getName());
                        if (!match.find()) continue;
                        loader = this.fileAssociations.get(filter);
                    }
                    if (loader == null) break block38;
                    description = null;
                    try {
                        description = loader.getPluginDescription(file);
                        String name = description.getName();
                        if (name.equalsIgnoreCase("bukkit") || name.equalsIgnoreCase("minecraft") || name.equalsIgnoreCase("mojang")) {
                            this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': Restricted Name");
                            break block38;
                        }
                        if (description.rawName.indexOf(32) != -1) {
                            this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': uses the space-character (0x20) in its name");
                        }
                        break block39;
                    }
                    catch (InvalidDescriptionException ex) {
                        this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex);
                    }
                    break block38;
                }
                File replacedFile = plugins.put(description.getName(), file);
                if (replacedFile != null) {
                    this.server.getLogger().severe(String.format("Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'", description.getName(), file.getPath(), replacedFile.getPath(), directory.getPath()));
                }
                if ((removedProvided = (String)pluginsProvided.remove(description.getName())) != null) {
                    this.server.getLogger().warning(String.format("Ambiguous plugin name `%s'. It is also provided by `%s'", description.getName(), removedProvided));
                }
                for (String provided : description.getProvides()) {
                    File pluginFile = (File)plugins.get(provided);
                    if (pluginFile != null) {
                        this.server.getLogger().warning(String.format("`%s provides `%s' while this is also the name of `%s' in `%s'", file.getPath(), provided, pluginFile.getPath(), directory.getPath()));
                        continue;
                    }
                    String replacedPlugin = pluginsProvided.put(provided, description.getName());
                    if (replacedPlugin == null) continue;
                    this.server.getLogger().warning(String.format("`%s' is provided by both `%s' and `%s'", provided, description.getName(), replacedPlugin));
                }
                List<String> softDependencySet = description.getSoftDepend();
                if (softDependencySet != null && !softDependencySet.isEmpty()) {
                    if (softDependencies.containsKey(description.getName())) {
                        ((Collection)softDependencies.get(description.getName())).addAll(softDependencySet);
                    } else {
                        softDependencies.put(description.getName(), new LinkedList<String>(softDependencySet));
                    }
                    for (String depend : softDependencySet) {
                        this.dependencyGraph.putEdge(description.getName(), depend);
                    }
                }
                if ((dependencySet = description.getDepend()) != null && !dependencySet.isEmpty()) {
                    dependencies.put(description.getName(), new LinkedList<String>(dependencySet));
                    for (String depend : dependencySet) {
                        this.dependencyGraph.putEdge(description.getName(), depend);
                    }
                }
                if ((loadBeforeSet = description.getLoadBefore()) != null && !loadBeforeSet.isEmpty()) {
                    for (String loadBeforeTarget : loadBeforeSet) {
                        if (softDependencies.containsKey(loadBeforeTarget)) {
                            ((Collection)softDependencies.get(loadBeforeTarget)).add(description.getName());
                        } else {
                            LinkedList<String> shortSoftDependency = new LinkedList<String>();
                            shortSoftDependency.add(description.getName());
                            softDependencies.put(loadBeforeTarget, shortSoftDependency);
                        }
                        this.dependencyGraph.putEdge(loadBeforeTarget, description.getName());
                    }
                }
            }
            ++n2;
        }
        while (!plugins.isEmpty()) {
            Plugin loadedPlugin;
            File file;
            String plugin;
            boolean missingDependency = true;
            Iterator<Map.Entry<String, File>> pluginIterator = plugins.entrySet().iterator();
            while (pluginIterator.hasNext()) {
                Map.Entry<String, File> entry = pluginIterator.next();
                plugin = entry.getKey();
                if (dependencies.containsKey(plugin)) {
                    Iterator dependencyIterator = ((Collection)dependencies.get(plugin)).iterator();
                    while (dependencyIterator.hasNext()) {
                        String dependency = (String)dependencyIterator.next();
                        if (loadedPlugins.contains(dependency)) {
                            dependencyIterator.remove();
                            continue;
                        }
                        if (plugins.containsKey(dependency) || pluginsProvided.containsKey(dependency)) continue;
                        missingDependency = false;
                        pluginIterator.remove();
                        softDependencies.remove(plugin);
                        dependencies.remove(plugin);
                        this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)entry.getValue()).getPath() + "' in folder '" + directory.getPath() + "'", new UnknownDependencyException("Unknown dependency " + dependency + ". Please download and install " + dependency + " to run this plugin."));
                        break;
                    }
                    if (dependencies.containsKey(plugin) && ((Collection)dependencies.get(plugin)).isEmpty()) {
                        dependencies.remove(plugin);
                    }
                }
                if (softDependencies.containsKey(plugin)) {
                    Iterator softDependencyIterator = ((Collection)softDependencies.get(plugin)).iterator();
                    while (softDependencyIterator.hasNext()) {
                        String softDependency = (String)softDependencyIterator.next();
                        if (plugins.containsKey(softDependency) || pluginsProvided.containsKey(softDependency)) continue;
                        softDependencyIterator.remove();
                    }
                    if (((Collection)softDependencies.get(plugin)).isEmpty()) {
                        softDependencies.remove(plugin);
                    }
                }
                if (dependencies.containsKey(plugin) || softDependencies.containsKey(plugin) || !plugins.containsKey(plugin)) continue;
                file = (File)plugins.get(plugin);
                pluginIterator.remove();
                missingDependency = false;
                try {
                    loadedPlugin = this.loadPlugin(file);
                    if (loadedPlugin != null) {
                        result.add(loadedPlugin);
                        loadedPlugins.add(loadedPlugin.getName());
                        loadedPlugins.addAll(loadedPlugin.getDescription().getProvides());
                        continue;
                    }
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'");
                }
                catch (InvalidPluginException ex) {
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex);
                }
            }
            if (!missingDependency) continue;
            pluginIterator = plugins.entrySet().iterator();
            while (pluginIterator.hasNext()) {
                Map.Entry<String, File> entry = pluginIterator.next();
                plugin = entry.getKey();
                if (dependencies.containsKey(plugin)) continue;
                softDependencies.remove(plugin);
                missingDependency = false;
                file = (File)entry.getValue();
                pluginIterator.remove();
                try {
                    loadedPlugin = this.loadPlugin(file);
                    if (loadedPlugin != null) {
                        result.add(loadedPlugin);
                        loadedPlugins.add(loadedPlugin.getName());
                        loadedPlugins.addAll(loadedPlugin.getDescription().getProvides());
                        break;
                    }
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'");
                    break;
                }
                catch (InvalidPluginException ex) {
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex);
                }
            }
            if (!missingDependency) continue;
            softDependencies.clear();
            dependencies.clear();
            Iterator failedPluginIterator = plugins.values().iterator();
            while (failedPluginIterator.hasNext()) {
                File file2 = (File)failedPluginIterator.next();
                failedPluginIterator.remove();
                this.server.getLogger().log(Level.SEVERE, "Could not load '" + file2.getPath() + "' in folder '" + directory.getPath() + "': circular dependency detected");
            }
        }
        TimingsCommand.timingStart = System.nanoTime();
        return result.toArray(new Plugin[result.size()]);
    }

    @Override
    @Nullable
    public synchronized Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        this.checkUpdate(file);
        Set<Pattern> filters = this.fileAssociations.keySet();
        Plugin result = null;
        for (Pattern filter : filters) {
            String name;
            Matcher match = filter.matcher(name = file.getName());
            if (!match.find()) continue;
            PluginLoader loader = this.fileAssociations.get(filter);
            result = loader.loadPlugin(file);
        }
        if (result != null) {
            this.plugins.add(result);
            this.lookupNames.put(result.getDescription().getName(), result);
            for (String provided : result.getDescription().getProvides()) {
                this.lookupNames.putIfAbsent(provided, result);
            }
        }
        return result;
    }

    private void checkUpdate(@NotNull File file) {
        if (this.updateDirectory == null || !this.updateDirectory.isDirectory()) {
            return;
        }
        File updateFile = new File(this.updateDirectory, file.getName());
        if (updateFile.isFile() && FileUtil.copy(updateFile, file)) {
            updateFile.delete();
        }
    }

    @Override
    @Nullable
    public synchronized Plugin getPlugin(@NotNull String name) {
        return this.lookupNames.get(name.replace(' ', '_'));
    }

    @Override
    @NotNull
    public synchronized Plugin[] getPlugins() {
        return this.plugins.toArray(new Plugin[this.plugins.size()]);
    }

    @Override
    public boolean isPluginEnabled(@NotNull String name) {
        Plugin plugin = this.getPlugin(name);
        return this.isPluginEnabled(plugin);
    }

    @Override
    public boolean isPluginEnabled(@Nullable Plugin plugin) {
        if (plugin != null && this.plugins.contains(plugin)) {
            return plugin.isEnabled();
        }
        return false;
    }

    @Override
    public void enablePlugin(@NotNull Plugin plugin) {
        if (!plugin.isEnabled()) {
            List<Command> pluginCommands = PluginCommandYamlParser.parse(plugin);
            if (!pluginCommands.isEmpty()) {
                this.commandMap.registerAll(plugin.getDescription().getName(), pluginCommands);
            }
            try {
                plugin.getPluginLoader().enablePlugin(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            HandlerList.bakeAll();
        }
    }

    @Override
    public void disablePlugins() {
        Plugin[] plugins = this.getPlugins();
        int i = plugins.length - 1;
        while (i >= 0) {
            this.disablePlugin(plugins[i]);
            --i;
        }
    }

    @Override
    public void disablePlugin(@NotNull Plugin plugin) {
        if (plugin.isEnabled()) {
            try {
                plugin.getPluginLoader().disablePlugin(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            try {
                this.server.getScheduler().cancelTasks(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while cancelling tasks for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            try {
                this.server.getServicesManager().unregisterAll(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering services for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            try {
                HandlerList.unregisterAll(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering events for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            try {
                this.server.getMessenger().unregisterIncomingPluginChannel(plugin);
                this.server.getMessenger().unregisterOutgoingPluginChannel(plugin);
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering plugin channels for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
            try {
                for (World world : this.server.getWorlds()) {
                    world.removePluginChunkTickets(plugin);
                }
            }
            catch (Throwable ex) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while removing chunk tickets for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearPlugins() {
        SimplePluginManager simplePluginManager = this;
        synchronized (simplePluginManager) {
            this.disablePlugins();
            this.plugins.clear();
            this.lookupNames.clear();
            this.dependencyGraph = GraphBuilder.directed().build();
            HandlerList.unregisterAll();
            this.fileAssociations.clear();
            this.permissions.clear();
            this.defaultPerms.get(true).clear();
            this.defaultPerms.get(false).clear();
        }
    }

    @Override
    public void callEvent(@NotNull Event event) {
        if (this.plugins.isEmpty()) {
            return;
        }
        if (!event.isAsynchronous() && !this.server.isPrimaryThread()) {
            return;
        }
        HandlerList handlers = event.getHandlers();
        RegisteredListener[] listeners = handlers.getRegisteredListeners();
        if (listeners.length == 0) {
            return;
        }
        this.fireEvent(event, listeners);
    }

    private void fireEvent(@NotNull Event event, RegisteredListener[] listeners) {
        for (RegisteredListener registration : listeners) {
            if (registration.getPlugin().isEnabled()) {
                try {
                    registration.callEvent(event);
                }
                catch (AuthorNagException ex) {
                    Plugin plugin = registration.getPlugin();
                    if (plugin.isNaggable()) {
                        plugin.setNaggable(false);
                        this.server.getLogger().log(Level.SEVERE, String.format("Nag author(s): '%s' of '%s' about the following: %s", plugin.getDescription().getAuthors(), plugin.getDescription().getFullName(), ex.getMessage()));
                    }
                }
                catch (Throwable ex) {
                    this.server.getLogger().log(Level.SEVERE, "Could not pass event " + event.getEventName() + " to " + registration.getPlugin().getDescription().getFullName(), ex);
                }
            }
        }
    }

    @Override
    public void registerEvents(@NotNull Listener listener, @NotNull Plugin plugin) {
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register " + listener + " while not enabled");
        }
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : plugin.getPluginLoader().createRegisteredListeners(listener, plugin).entrySet()) {
            this.getEventListeners(this.getRegistrationClass(entry.getKey())).registerAll((Collection<RegisteredListener>)entry.getValue());
        }
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin) {
        this.registerEvent(event, listener, priority, executor, plugin, false);
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin, boolean ignoreCancelled) {
        Preconditions.checkArgument((listener != null ? 1 : 0) != 0, (Object)"Listener cannot be null");
        Preconditions.checkArgument((priority != null ? 1 : 0) != 0, (Object)"Priority cannot be null");
        Preconditions.checkArgument((executor != null ? 1 : 0) != 0, (Object)"Executor cannot be null");
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register " + event + " while not enabled");
        }
        if (this.useTimings) {
            this.getEventListeners(event).register(new TimedRegisteredListener(listener, executor, priority, plugin, ignoreCancelled));
        } else {
            this.getEventListeners(event).register(new RegisteredListener(listener, executor, priority, plugin, ignoreCancelled));
        }
    }

    @NotNull
    private HandlerList getEventListeners(@NotNull Class<? extends Event> type) {
        try {
            Method method = this.getRegistrationClass(type).getDeclaredMethod("getHandlerList", new Class[0]);
            method.setAccessible(true);
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new IllegalAccessException("getHandlerList must be static");
            }
            return (HandlerList)method.invoke(null, new Object[0]);
        }
        catch (Exception e) {
            throw new IllegalPluginAccessException("Error while registering listener for event type " + type.toString() + ": " + e.toString());
        }
    }

    @NotNull
    private Class<? extends Event> getRegistrationClass(@NotNull Class<? extends Event> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlerList", new Class[0]);
            return clazz;
        }
        catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Event.class) && Event.class.isAssignableFrom(clazz.getSuperclass())) {
                return this.getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
            }
            throw new IllegalPluginAccessException("Unable to find handler list for event " + clazz.getName() + ". Static getHandlerList method required!");
        }
    }

    @Override
    @Nullable
    public Permission getPermission(@NotNull String name) {
        return this.permissions.get(name.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void addPermission(@NotNull Permission perm) {
        this.addPermission(perm, true);
    }

    @Deprecated
    public void addPermission(@NotNull Permission perm, boolean dirty) {
        String name = perm.getName().toLowerCase(Locale.ENGLISH);
        if (this.permissions.containsKey(name)) {
            throw new IllegalArgumentException("The permission " + name + " is already defined!");
        }
        this.permissions.put(name, perm);
        this.calculatePermissionDefault(perm, dirty);
    }

    @Override
    @NotNull
    public Set<Permission> getDefaultPermissions(boolean op) {
        return ImmutableSet.copyOf((Collection)this.defaultPerms.get(op));
    }

    @Override
    public void removePermission(@NotNull Permission perm) {
        this.removePermission(perm.getName());
    }

    @Override
    public void removePermission(@NotNull String name) {
        this.permissions.remove(name.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void recalculatePermissionDefaults(@NotNull Permission perm) {
        if (perm != null && this.permissions.containsKey(perm.getName().toLowerCase(Locale.ENGLISH))) {
            this.defaultPerms.get(true).remove(perm);
            this.defaultPerms.get(false).remove(perm);
            this.calculatePermissionDefault(perm, true);
        }
    }

    private void calculatePermissionDefault(@NotNull Permission perm, boolean dirty) {
        if (perm.getDefault() == PermissionDefault.OP || perm.getDefault() == PermissionDefault.TRUE) {
            this.defaultPerms.get(true).add(perm);
            if (dirty) {
                this.dirtyPermissibles(true);
            }
        }
        if (perm.getDefault() == PermissionDefault.NOT_OP || perm.getDefault() == PermissionDefault.TRUE) {
            this.defaultPerms.get(false).add(perm);
            if (dirty) {
                this.dirtyPermissibles(false);
            }
        }
    }

    @Deprecated
    public void dirtyPermissibles() {
        this.dirtyPermissibles(true);
        this.dirtyPermissibles(false);
    }

    private void dirtyPermissibles(boolean op) {
        Set<Permissible> permissibles = this.getDefaultPermSubscriptions(op);
        for (Permissible p : permissibles) {
            p.recalculatePermissions();
        }
    }

    @Override
    public void subscribeToPermission(@NotNull String permission, @NotNull Permissible permissible) {
        String name = permission.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(name);
        if (map == null) {
            map = new WeakHashMap<Permissible, Boolean>();
            this.permSubs.put(name, map);
        }
        map.put(permissible, true);
    }

    @Override
    public void unsubscribeFromPermission(@NotNull String permission, @NotNull Permissible permissible) {
        String name = permission.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(name);
        if (map != null) {
            map.remove(permissible);
            if (map.isEmpty()) {
                this.permSubs.remove(name);
            }
        }
    }

    @Override
    @NotNull
    public Set<Permissible> getPermissionSubscriptions(@NotNull String permission) {
        String name = permission.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(name);
        if (map == null) {
            return ImmutableSet.of();
        }
        return ImmutableSet.copyOf(map.keySet());
    }

    @Override
    public void subscribeToDefaultPerms(boolean op, @NotNull Permissible permissible) {
        Map<Permissible, Boolean> map = this.defSubs.get(op);
        if (map == null) {
            map = new WeakHashMap<Permissible, Boolean>();
            this.defSubs.put(op, map);
        }
        map.put(permissible, true);
    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean op, @NotNull Permissible permissible) {
        Map<Permissible, Boolean> map = this.defSubs.get(op);
        if (map != null) {
            map.remove(permissible);
            if (map.isEmpty()) {
                this.defSubs.remove(op);
            }
        }
    }

    @Override
    @NotNull
    public Set<Permissible> getDefaultPermSubscriptions(boolean op) {
        Map<Permissible, Boolean> map = this.defSubs.get(op);
        if (map == null) {
            return ImmutableSet.of();
        }
        return ImmutableSet.copyOf(map.keySet());
    }

    @Override
    @NotNull
    public Set<Permission> getPermissions() {
        return new HashSet<Permission>(this.permissions.values());
    }

    public boolean isTransitiveDepend(@NotNull PluginDescriptionFile plugin, @NotNull PluginDescriptionFile depend) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"plugin");
        Preconditions.checkArgument((depend != null ? 1 : 0) != 0, (Object)"depend");
        if (this.dependencyGraph.nodes().contains(plugin.getName())) {
            Set<String> reachableNodes = Graphs.reachableNodes(this.dependencyGraph, plugin.getName());
            if (reachableNodes.contains(depend.getName())) {
                return true;
            }
            for (String provided : depend.getProvides()) {
                if (!reachableNodes.contains(provided)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean useTimings() {
        return this.useTimings;
    }

    public void useTimings(boolean use) {
        this.useTimings = use;
        org.spigotmc.CustomTimingsHandler.timingsEnabled = use;
    }
}

