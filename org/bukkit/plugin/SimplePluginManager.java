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
import org.spigotmc.CustomTimingsHandler;

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

    public SimplePluginManager(@NotNull Server server, @NotNull SimpleCommandMap simpleCommandMap) {
        this.server = server;
        this.commandMap = simpleCommandMap;
        this.defaultPerms.put(true, new LinkedHashSet());
        this.defaultPerms.put(false, new LinkedHashSet());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void registerInterface(@NotNull Class<? extends PluginLoader> clazz) throws IllegalArgumentException {
        SimplePluginManager simplePluginManager;
        PluginLoader pluginLoader;
        Pattern[] patternArray;
        if (PluginLoader.class.isAssignableFrom(clazz)) {
            try {
                patternArray = clazz.getConstructor(Server.class);
                pluginLoader = patternArray.newInstance(this.server);
            }
            catch (NoSuchMethodException noSuchMethodException) {
                String string = clazz.getName();
                throw new IllegalArgumentException(String.format("Class %s does not have a public %s(Server) constructor", string, string), noSuchMethodException);
            }
            catch (Exception exception) {
                throw new IllegalArgumentException(String.format("Unexpected exception %s while attempting to construct a new instance of %s", exception.getClass().getName(), clazz.getName()), exception);
            }
        } else {
            throw new IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", clazz.getName()));
        }
        patternArray = pluginLoader.getPluginFileFilters();
        SimplePluginManager simplePluginManager2 = simplePluginManager = this;
        synchronized (simplePluginManager2) {
            Pattern[] patternArray2 = patternArray;
            int n = patternArray.length;
            for (int i = 0; i < n; ++i) {
                Pattern pattern = patternArray2[i];
                this.fileAssociations.put(pattern, pluginLoader);
            }
        }
    }

    @Override
    @NotNull
    public Plugin[] loadPlugins(@NotNull File file) {
        Object object;
        Object object2;
        String string;
        List<String> invalidPluginException;
        Map.Entry entry;
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"Directory cannot be null");
        Preconditions.checkArgument((boolean)file.isDirectory(), (Object)"Directory must be a directory");
        ArrayList<Plugin> arrayList = new ArrayList<Plugin>();
        Set<Pattern> set = this.fileAssociations.keySet();
        if (!this.server.getUpdateFolder().equals("")) {
            this.updateDirectory = new File(file, this.server.getUpdateFolder());
        }
        HashMap<String, File> hashMap = new HashMap<String, File>();
        HashSet<String> hashSet = new HashSet<String>();
        HashMap<String, String> hashMap2 = new HashMap<String, String>();
        HashMap hashMap3 = new HashMap();
        HashMap<Object, Object> hashMap4 = new HashMap<Object, Object>();
        for (File iterator : file.listFiles()) {
            Object object3;
            PluginDescriptionFile pluginDescriptionFile;
            Object object42;
            block36: {
                entry = null;
                for (Pattern pattern : set) {
                    object42 = pattern.matcher(iterator.getName());
                    if (!((Matcher)object42).find()) continue;
                    entry = this.fileAssociations.get(pattern);
                }
                if (entry == null) continue;
                pluginDescriptionFile = null;
                try {
                    pluginDescriptionFile = entry.getPluginDescription(iterator);
                    invalidPluginException = pluginDescriptionFile.getName();
                    if (((String)((Object)invalidPluginException)).equalsIgnoreCase("bukkit") || ((String)((Object)invalidPluginException)).equalsIgnoreCase("minecraft") || ((String)((Object)invalidPluginException)).equalsIgnoreCase("mojang")) {
                        this.server.getLogger().log(Level.SEVERE, "Could not load '" + iterator.getPath() + "' in folder '" + file.getPath() + "': Restricted Name");
                        continue;
                    }
                    if (pluginDescriptionFile.rawName.indexOf(32) == -1) break block36;
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + iterator.getPath() + "' in folder '" + file.getPath() + "': uses the space-character (0x20) in its name");
                }
                catch (InvalidDescriptionException invalidDescriptionException) {
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + iterator.getPath() + "' in folder '" + file.getPath() + "'", invalidDescriptionException);
                    continue;
                }
            }
            entry = hashMap.put(pluginDescriptionFile.getName(), iterator);
            if (entry != null) {
                this.server.getLogger().severe(String.format("Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'", pluginDescriptionFile.getName(), iterator.getPath(), ((File)((Object)entry)).getPath(), file.getPath()));
            }
            if ((string = (String)hashMap2.remove(pluginDescriptionFile.getName())) != null) {
                this.server.getLogger().warning(String.format("Ambiguous plugin name `%s'. It is also provided by `%s'", pluginDescriptionFile.getName(), string));
            }
            for (String string2 : pluginDescriptionFile.getProvides()) {
                object42 = (File)hashMap.get(string2);
                if (object42 != null) {
                    this.server.getLogger().warning(String.format("`%s provides `%s' while this is also the name of `%s' in `%s'", iterator.getPath(), string2, ((File)object42).getPath(), file.getPath()));
                    continue;
                }
                object3 = hashMap2.put(string2, pluginDescriptionFile.getName());
                if (object3 == null) continue;
                this.server.getLogger().warning(String.format("`%s' is provided by both `%s' and `%s'", string2, pluginDescriptionFile.getName(), object3));
            }
            invalidPluginException = pluginDescriptionFile.getSoftDepend();
            if (invalidPluginException != null && !invalidPluginException.isEmpty()) {
                if (hashMap4.containsKey(pluginDescriptionFile.getName())) {
                    ((Collection)hashMap4.get(pluginDescriptionFile.getName())).addAll(invalidPluginException);
                } else {
                    hashMap4.put(pluginDescriptionFile.getName(), new LinkedList(invalidPluginException));
                }
                for (Object object42 : invalidPluginException) {
                    this.dependencyGraph.putEdge((Object)pluginDescriptionFile.getName(), object42);
                }
            }
            if ((object2 = pluginDescriptionFile.getDepend()) != null && !object2.isEmpty()) {
                hashMap3.put(pluginDescriptionFile.getName(), new LinkedList(object2));
                Iterator iterator2 = object2.iterator();
                while (iterator2.hasNext()) {
                    object42 = (String)iterator2.next();
                    this.dependencyGraph.putEdge((Object)pluginDescriptionFile.getName(), object42);
                }
            }
            if ((object = pluginDescriptionFile.getLoadBefore()) == null || object.isEmpty()) continue;
            Iterator iterator3 = object.iterator();
            while (iterator3.hasNext()) {
                object42 = (String)iterator3.next();
                if (hashMap4.containsKey(object42)) {
                    ((Collection)hashMap4.get(object42)).add(pluginDescriptionFile.getName());
                } else {
                    object3 = new LinkedList();
                    ((LinkedList)object3).add(pluginDescriptionFile.getName());
                    hashMap4.put(object42, object3);
                }
                this.dependencyGraph.putEdge(object42, (Object)pluginDescriptionFile.getName());
            }
        }
        while (!hashMap.isEmpty()) {
            boolean bl = true;
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                string = (String)entry.getKey();
                if (hashMap3.containsKey(string)) {
                    invalidPluginException = ((Collection)hashMap3.get(string)).iterator();
                    while (invalidPluginException.hasNext()) {
                        String string3 = (String)invalidPluginException.next();
                        if (hashSet.contains(string3)) {
                            invalidPluginException.remove();
                            continue;
                        }
                        if (hashMap.containsKey(string3) || hashMap2.containsKey(string3)) continue;
                        bl = false;
                        iterator.remove();
                        hashMap4.remove(string);
                        hashMap3.remove(string);
                        this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)entry.getValue()).getPath() + "' in folder '" + file.getPath() + "'", new UnknownDependencyException("Unknown dependency " + string3 + ". Please download and install " + string3 + " to run this plugin."));
                        break;
                    }
                    if (hashMap3.containsKey(string) && ((Collection)hashMap3.get(string)).isEmpty()) {
                        hashMap3.remove(string);
                    }
                }
                if (hashMap4.containsKey(string)) {
                    invalidPluginException = ((Collection)hashMap4.get(string)).iterator();
                    while (invalidPluginException.hasNext()) {
                        String string4 = (String)invalidPluginException.next();
                        if (hashMap.containsKey(string4) || hashMap2.containsKey(string4)) continue;
                        invalidPluginException.remove();
                    }
                    if (((Collection)hashMap4.get(string)).isEmpty()) {
                        hashMap4.remove(string);
                    }
                }
                if (hashMap3.containsKey(string) || hashMap4.containsKey(string) || !hashMap.containsKey(string)) continue;
                object2 = (File)hashMap.get(string);
                iterator.remove();
                bl = false;
                try {
                    object = this.loadPlugin((File)object2);
                    if (object != null) {
                        arrayList.add((Plugin)object);
                        hashSet.add(object.getName());
                        hashSet.addAll(object.getDescription().getProvides());
                        continue;
                    }
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)object2).getPath() + "' in folder '" + file.getPath() + "'");
                }
                catch (InvalidPluginException invalidPluginException2) {
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)object2).getPath() + "' in folder '" + file.getPath() + "'", invalidPluginException2);
                }
            }
            if (!bl) continue;
            Iterator iterator4 = hashMap.entrySet().iterator();
            while (iterator4.hasNext()) {
                entry = iterator4.next();
                string = (String)entry.getKey();
                if (hashMap3.containsKey(string)) continue;
                hashMap4.remove(string);
                bl = false;
                object2 = (File)entry.getValue();
                iterator4.remove();
                try {
                    object = this.loadPlugin((File)object2);
                    if (object != null) {
                        arrayList.add((Plugin)object);
                        hashSet.add(object.getName());
                        hashSet.addAll(object.getDescription().getProvides());
                        break;
                    }
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)object2).getPath() + "' in folder '" + file.getPath() + "'");
                    break;
                }
                catch (InvalidPluginException invalidPluginException3) {
                    this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)object2).getPath() + "' in folder '" + file.getPath() + "'", invalidPluginException3);
                }
            }
            if (!bl) continue;
            hashMap4.clear();
            hashMap3.clear();
            entry = hashMap.values().iterator();
            while (entry.hasNext()) {
                invalidPluginException = (File)entry.next();
                entry.remove();
                this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)((Object)invalidPluginException)).getPath() + "' in folder '" + file.getPath() + "': circular dependency detected");
            }
        }
        TimingsCommand.timingStart = System.nanoTime();
        return arrayList.toArray(new Plugin[arrayList.size()]);
    }

    @Override
    @Nullable
    public synchronized Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        this.checkUpdate(file);
        Set<Pattern> set = this.fileAssociations.keySet();
        Plugin plugin = null;
        for (Pattern object : set) {
            String string;
            Matcher matcher = object.matcher(string = file.getName());
            if (!matcher.find()) continue;
            PluginLoader pluginLoader = this.fileAssociations.get(object);
            plugin = pluginLoader.loadPlugin(file);
        }
        if (plugin != null) {
            this.plugins.add(plugin);
            this.lookupNames.put(plugin.getDescription().getName(), plugin);
            for (String string : plugin.getDescription().getProvides()) {
                this.lookupNames.putIfAbsent(string, plugin);
            }
        }
        return plugin;
    }

    private void checkUpdate(@NotNull File file) {
        if (this.updateDirectory == null || !this.updateDirectory.isDirectory()) {
            return;
        }
        File file2 = new File(this.updateDirectory, file.getName());
        if (file2.isFile() && FileUtil.copy(file2, file)) {
            file2.delete();
        }
    }

    @Override
    @Nullable
    public synchronized Plugin getPlugin(@NotNull String string) {
        return this.lookupNames.get(string.replace(' ', '_'));
    }

    @Override
    @NotNull
    public synchronized Plugin[] getPlugins() {
        return this.plugins.toArray(new Plugin[this.plugins.size()]);
    }

    @Override
    public boolean isPluginEnabled(@NotNull String string) {
        Plugin plugin = this.getPlugin(string);
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
            List<Command> list = PluginCommandYamlParser.parse(plugin);
            if (!list.isEmpty()) {
                this.commandMap.registerAll(plugin.getDescription().getName(), list);
            }
            try {
                plugin.getPluginLoader().enablePlugin(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            HandlerList.bakeAll();
        }
    }

    @Override
    public void disablePlugins() {
        Plugin[] pluginArray = this.getPlugins();
        for (int i = pluginArray.length - 1; i >= 0; --i) {
            this.disablePlugin(pluginArray[i]);
        }
    }

    @Override
    public void disablePlugin(@NotNull Plugin plugin) {
        if (plugin.isEnabled()) {
            try {
                plugin.getPluginLoader().disablePlugin(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            try {
                this.server.getScheduler().cancelTasks(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while cancelling tasks for " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            try {
                this.server.getServicesManager().unregisterAll(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering services for " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            try {
                HandlerList.unregisterAll(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering events for " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            try {
                this.server.getMessenger().unregisterIncomingPluginChannel(plugin);
                this.server.getMessenger().unregisterOutgoingPluginChannel(plugin);
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while unregistering plugin channels for " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
            try {
                for (World world : this.server.getWorlds()) {
                    world.removePluginChunkTickets(plugin);
                }
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while removing chunk tickets for " + plugin.getDescription().getFullName() + " (Is it up to date?)", throwable);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearPlugins() {
        SimplePluginManager simplePluginManager;
        SimplePluginManager simplePluginManager2 = simplePluginManager = this;
        synchronized (simplePluginManager2) {
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
        HandlerList handlerList = event.getHandlers();
        RegisteredListener[] registeredListenerArray = handlerList.getRegisteredListeners();
        if (registeredListenerArray.length == 0) {
            return;
        }
        this.fireEvent(event, registeredListenerArray);
    }

    private void fireEvent(@NotNull Event event, RegisteredListener[] registeredListenerArray) {
        for (RegisteredListener registeredListener : registeredListenerArray) {
            if (!registeredListener.getPlugin().isEnabled()) continue;
            try {
                registeredListener.callEvent(event);
            }
            catch (AuthorNagException authorNagException) {
                Plugin plugin = registeredListener.getPlugin();
                if (!plugin.isNaggable()) continue;
                plugin.setNaggable(false);
                this.server.getLogger().log(Level.SEVERE, String.format("Nag author(s): '%s' of '%s' about the following: %s", plugin.getDescription().getAuthors(), plugin.getDescription().getFullName(), authorNagException.getMessage()));
            }
            catch (Throwable throwable) {
                this.server.getLogger().log(Level.SEVERE, "Could not pass event " + event.getEventName() + " to " + registeredListener.getPlugin().getDescription().getFullName(), throwable);
            }
        }
    }

    @Override
    public void registerEvents(@NotNull Listener listener, @NotNull Plugin plugin) {
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register " + String.valueOf(listener) + " while not enabled");
        }
        for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : plugin.getPluginLoader().createRegisteredListeners(listener, plugin).entrySet()) {
            this.getEventListeners(this.getRegistrationClass(entry.getKey())).registerAll((Collection<RegisteredListener>)entry.getValue());
        }
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> clazz, @NotNull Listener listener, @NotNull EventPriority eventPriority, @NotNull EventExecutor eventExecutor, @NotNull Plugin plugin) {
        this.registerEvent(clazz, listener, eventPriority, eventExecutor, plugin, false);
    }

    @Override
    public void registerEvent(@NotNull Class<? extends Event> clazz, @NotNull Listener listener, @NotNull EventPriority eventPriority, @NotNull EventExecutor eventExecutor, @NotNull Plugin plugin, boolean bl) {
        Preconditions.checkArgument((listener != null ? 1 : 0) != 0, (Object)"Listener cannot be null");
        Preconditions.checkArgument((eventPriority != null ? 1 : 0) != 0, (Object)"Priority cannot be null");
        Preconditions.checkArgument((eventExecutor != null ? 1 : 0) != 0, (Object)"Executor cannot be null");
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register " + String.valueOf(clazz) + " while not enabled");
        }
        if (this.useTimings) {
            this.getEventListeners(clazz).register(new TimedRegisteredListener(listener, eventExecutor, eventPriority, plugin, bl));
        } else {
            this.getEventListeners(clazz).register(new RegisteredListener(listener, eventExecutor, eventPriority, plugin, bl));
        }
    }

    @NotNull
    private HandlerList getEventListeners(@NotNull Class<? extends Event> clazz) {
        try {
            Method method = this.getRegistrationClass(clazz).getDeclaredMethod("getHandlerList", new Class[0]);
            method.setAccessible(true);
            if (!Modifier.isStatic(method.getModifiers())) {
                throw new IllegalAccessException("getHandlerList must be static");
            }
            return (HandlerList)method.invoke(null, new Object[0]);
        }
        catch (Exception exception) {
            throw new IllegalPluginAccessException("Error while registering listener for event type " + clazz.toString() + ": " + exception.toString());
        }
    }

    @NotNull
    private Class<? extends Event> getRegistrationClass(@NotNull Class<? extends Event> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlerList", new Class[0]);
            return clazz;
        }
        catch (NoSuchMethodException noSuchMethodException) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Event.class) && Event.class.isAssignableFrom(clazz.getSuperclass())) {
                return this.getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
            }
            throw new IllegalPluginAccessException("Unable to find handler list for event " + clazz.getName() + ". Static getHandlerList method required!");
        }
    }

    @Override
    @Nullable
    public Permission getPermission(@NotNull String string) {
        return this.permissions.get(string.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void addPermission(@NotNull Permission permission) {
        this.addPermission(permission, true);
    }

    @Deprecated
    public void addPermission(@NotNull Permission permission, boolean bl) {
        String string = permission.getName().toLowerCase(Locale.ENGLISH);
        if (this.permissions.containsKey(string)) {
            throw new IllegalArgumentException("The permission " + string + " is already defined!");
        }
        this.permissions.put(string, permission);
        this.calculatePermissionDefault(permission, bl);
    }

    @Override
    @NotNull
    public Set<Permission> getDefaultPermissions(boolean bl) {
        return ImmutableSet.copyOf((Collection)this.defaultPerms.get(bl));
    }

    @Override
    public void removePermission(@NotNull Permission permission) {
        this.removePermission(permission.getName());
    }

    @Override
    public void removePermission(@NotNull String string) {
        this.permissions.remove(string.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public void recalculatePermissionDefaults(@NotNull Permission permission) {
        if (permission != null && this.permissions.containsKey(permission.getName().toLowerCase(Locale.ENGLISH))) {
            this.defaultPerms.get(true).remove(permission);
            this.defaultPerms.get(false).remove(permission);
            this.calculatePermissionDefault(permission, true);
        }
    }

    private void calculatePermissionDefault(@NotNull Permission permission, boolean bl) {
        if (permission.getDefault() == PermissionDefault.OP || permission.getDefault() == PermissionDefault.TRUE) {
            this.defaultPerms.get(true).add(permission);
            if (bl) {
                this.dirtyPermissibles(true);
            }
        }
        if (permission.getDefault() == PermissionDefault.NOT_OP || permission.getDefault() == PermissionDefault.TRUE) {
            this.defaultPerms.get(false).add(permission);
            if (bl) {
                this.dirtyPermissibles(false);
            }
        }
    }

    @Deprecated
    public void dirtyPermissibles() {
        this.dirtyPermissibles(true);
        this.dirtyPermissibles(false);
    }

    private void dirtyPermissibles(boolean bl) {
        Set<Permissible> set = this.getDefaultPermSubscriptions(bl);
        for (Permissible permissible : set) {
            permissible.recalculatePermissions();
        }
    }

    @Override
    public void subscribeToPermission(@NotNull String string, @NotNull Permissible permissible) {
        String string2 = string.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(string2);
        if (map == null) {
            map = new WeakHashMap<Permissible, Boolean>();
            this.permSubs.put(string2, map);
        }
        map.put(permissible, true);
    }

    @Override
    public void unsubscribeFromPermission(@NotNull String string, @NotNull Permissible permissible) {
        String string2 = string.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(string2);
        if (map != null) {
            map.remove(permissible);
            if (map.isEmpty()) {
                this.permSubs.remove(string2);
            }
        }
    }

    @Override
    @NotNull
    public Set<Permissible> getPermissionSubscriptions(@NotNull String string) {
        String string2 = string.toLowerCase(Locale.ENGLISH);
        Map<Permissible, Boolean> map = this.permSubs.get(string2);
        if (map == null) {
            return ImmutableSet.of();
        }
        return ImmutableSet.copyOf(map.keySet());
    }

    @Override
    public void subscribeToDefaultPerms(boolean bl, @NotNull Permissible permissible) {
        Map<Permissible, Boolean> map = this.defSubs.get(bl);
        if (map == null) {
            map = new WeakHashMap<Permissible, Boolean>();
            this.defSubs.put(bl, map);
        }
        map.put(permissible, true);
    }

    @Override
    public void unsubscribeFromDefaultPerms(boolean bl, @NotNull Permissible permissible) {
        Map<Permissible, Boolean> map = this.defSubs.get(bl);
        if (map != null) {
            map.remove(permissible);
            if (map.isEmpty()) {
                this.defSubs.remove(bl);
            }
        }
    }

    @Override
    @NotNull
    public Set<Permissible> getDefaultPermSubscriptions(boolean bl) {
        Map<Permissible, Boolean> map = this.defSubs.get(bl);
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

    public boolean isTransitiveDepend(@NotNull PluginDescriptionFile pluginDescriptionFile, @NotNull PluginDescriptionFile pluginDescriptionFile2) {
        Preconditions.checkArgument((pluginDescriptionFile != null ? 1 : 0) != 0, (Object)"plugin");
        Preconditions.checkArgument((pluginDescriptionFile2 != null ? 1 : 0) != 0, (Object)"depend");
        if (this.dependencyGraph.nodes().contains(pluginDescriptionFile.getName())) {
            Set set = Graphs.reachableNodes(this.dependencyGraph, (Object)pluginDescriptionFile.getName());
            if (set.contains(pluginDescriptionFile2.getName())) {
                return true;
            }
            for (String string : pluginDescriptionFile2.getProvides()) {
                if (!set.contains(string)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean useTimings() {
        return this.useTimings;
    }

    public void useTimings(boolean bl) {
        this.useTimings = bl;
        CustomTimingsHandler.timingsEnabled = bl;
    }
}

