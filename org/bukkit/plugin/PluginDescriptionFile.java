/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableSet
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.yaml.snakeyaml.DumperOptions
 *  org.yaml.snakeyaml.LoaderOptions
 *  org.yaml.snakeyaml.Yaml
 *  org.yaml.snakeyaml.constructor.AbstractConstruct
 *  org.yaml.snakeyaml.constructor.BaseConstructor
 *  org.yaml.snakeyaml.constructor.SafeConstructor
 *  org.yaml.snakeyaml.nodes.Node
 *  org.yaml.snakeyaml.nodes.Tag
 *  org.yaml.snakeyaml.representer.Representer
 *  org.yaml.snakeyaml.resolver.Resolver
 */
package org.bukkit.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginAwareness;
import org.bukkit.plugin.PluginDescriptionResolver;
import org.bukkit.plugin.PluginLoadOrder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

public final class PluginDescriptionFile {
    private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");
    private static final ThreadLocal<Yaml> YAML = new ThreadLocal<Yaml>(){

        @Override
        @NotNull
        protected Yaml initialValue() {
            DumperOptions dumperOptions = new DumperOptions();
            return new Yaml((BaseConstructor)new SafeConstructor(new LoaderOptions()){
                {
                    this.yamlConstructors.put(null, new AbstractConstruct(){

                        @NotNull
                        public Object construct(final @NotNull Node node) {
                            if (!node.getTag().startsWith("!@")) {
                                return SafeConstructor.undefinedConstructor.construct(node);
                            }
                            return new PluginAwareness(){

                                public String toString() {
                                    return node.toString();
                                }
                            };
                        }
                    });
                    PluginAwareness.Flags[] flagsArray = PluginAwareness.Flags.values();
                    int n = flagsArray.length;
                    int n2 = 0;
                    while (n2 < n) {
                        final PluginAwareness.Flags flag = flagsArray[n2];
                        this.yamlConstructors.put(new Tag("!@" + flag.name()), new AbstractConstruct(){

                            @NotNull
                            public PluginAwareness.Flags construct(@NotNull Node node) {
                                return flag;
                            }
                        });
                        ++n2;
                    }
                }
            }, new Representer(dumperOptions), dumperOptions, (Resolver)new PluginDescriptionResolver());
        }
    };
    String rawName = null;
    private String name = null;
    private List<String> provides = ImmutableList.of();
    private String main = null;
    private String classLoaderOf = null;
    private List<String> depend = ImmutableList.of();
    private List<String> softDepend = ImmutableList.of();
    private List<String> loadBefore = ImmutableList.of();
    private String version = null;
    private Map<String, Map<String, Object>> commands = ImmutableMap.of();
    private String description = null;
    private List<String> authors = null;
    private List<String> contributors = null;
    private String website = null;
    private String prefix = null;
    private PluginLoadOrder order = PluginLoadOrder.POSTWORLD;
    private List<Permission> permissions = null;
    private Map<?, ?> lazyPermissions = null;
    private PermissionDefault defaultPerm = PermissionDefault.OP;
    private Set<PluginAwareness> awareness = ImmutableSet.of();
    private String apiVersion = null;
    private List<String> libraries = ImmutableList.of();

    public PluginDescriptionFile(@NotNull InputStream stream) throws InvalidDescriptionException {
        this.loadMap(this.asMap(YAML.get().load(stream)));
    }

    public PluginDescriptionFile(@NotNull Reader reader) throws InvalidDescriptionException {
        this.loadMap(this.asMap(YAML.get().load(reader)));
    }

    public PluginDescriptionFile(@NotNull String pluginName, @NotNull String pluginVersion, @NotNull String mainClass) {
        this.name = this.rawName = pluginName;
        if (!VALID_NAME.matcher(this.name).matches()) {
            throw new IllegalArgumentException("name '" + this.name + "' contains invalid characters.");
        }
        this.name = this.name.replace(' ', '_');
        this.version = pluginVersion;
        this.main = mainClass;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public List<String> getProvides() {
        return this.provides;
    }

    @NotNull
    public String getVersion() {
        return this.version;
    }

    @NotNull
    public String getMain() {
        return this.main;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @NotNull
    public PluginLoadOrder getLoad() {
        return this.order;
    }

    @NotNull
    public List<String> getAuthors() {
        return this.authors;
    }

    @NotNull
    public List<String> getContributors() {
        return this.contributors;
    }

    @Nullable
    public String getWebsite() {
        return this.website;
    }

    @NotNull
    public List<String> getDepend() {
        return this.depend;
    }

    @NotNull
    public List<String> getSoftDepend() {
        return this.softDepend;
    }

    @NotNull
    public List<String> getLoadBefore() {
        return this.loadBefore;
    }

    @Nullable
    public String getPrefix() {
        return this.prefix;
    }

    @NotNull
    public Map<String, Map<String, Object>> getCommands() {
        return this.commands;
    }

    @NotNull
    public List<Permission> getPermissions() {
        if (this.permissions == null) {
            if (this.lazyPermissions == null) {
                this.permissions = ImmutableList.of();
            } else {
                this.permissions = ImmutableList.copyOf(Permission.loadPermissions(this.lazyPermissions, "Permission node '%s' in plugin description file for " + this.getFullName() + " is invalid", this.defaultPerm));
                this.lazyPermissions = null;
            }
        }
        return this.permissions;
    }

    @NotNull
    public PermissionDefault getPermissionDefault() {
        return this.defaultPerm;
    }

    @NotNull
    public Set<PluginAwareness> getAwareness() {
        return this.awareness;
    }

    @NotNull
    public String getFullName() {
        return String.valueOf(this.name) + " v" + this.version;
    }

    @Nullable
    public String getAPIVersion() {
        return this.apiVersion;
    }

    @NotNull
    public List<String> getLibraries() {
        return this.libraries;
    }

    @Deprecated
    @Nullable
    public String getClassLoaderOf() {
        return this.classLoaderOf;
    }

    public void save(@NotNull Writer writer) {
        YAML.get().dump(this.saveMap(), writer);
    }

    private void loadMap(@NotNull Map<?, ?> map) throws InvalidDescriptionException {
        ImmutableList.Builder contributorsBuilder;
        try {
            this.name = this.rawName = map.get("name").toString();
            if (!VALID_NAME.matcher(this.name).matches()) {
                throw new InvalidDescriptionException("name '" + this.name + "' contains invalid characters.");
            }
            this.name = this.name.replace(' ', '_');
        }
        catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "name is not defined");
        }
        catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "name is of wrong type");
        }
        this.provides = PluginDescriptionFile.makePluginNameList(map, "provides");
        try {
            this.version = map.get("version").toString();
        }
        catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "version is not defined");
        }
        catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "version is of wrong type");
        }
        try {
            this.main = map.get("main").toString();
            if (this.main.startsWith("org.bukkit.")) {
                throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
            }
        }
        catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "main is not defined");
        }
        catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "main is of wrong type");
        }
        if (map.get("commands") != null) {
            ImmutableMap.Builder commandsBuilder = ImmutableMap.builder();
            try {
                for (Map.Entry command : ((Map)map.get("commands")).entrySet()) {
                    ImmutableMap.Builder commandBuilder = ImmutableMap.builder();
                    if (command.getValue() != null) {
                        for (Map.Entry commandEntry : ((Map)command.getValue()).entrySet()) {
                            if (commandEntry.getValue() instanceof Iterable) {
                                ImmutableList.Builder commandSubList = ImmutableList.builder();
                                for (Object commandSubListItem : (Iterable)commandEntry.getValue()) {
                                    if (commandSubListItem == null) continue;
                                    commandSubList.add(commandSubListItem);
                                }
                                commandBuilder.put((Object)commandEntry.getKey().toString(), (Object)commandSubList.build());
                                continue;
                            }
                            if (commandEntry.getValue() == null) continue;
                            commandBuilder.put((Object)commandEntry.getKey().toString(), commandEntry.getValue());
                        }
                    }
                    commandsBuilder.put((Object)command.getKey().toString(), (Object)commandBuilder.build());
                }
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "commands are of wrong type");
            }
            this.commands = commandsBuilder.build();
        }
        if (map.get("class-loader-of") != null) {
            this.classLoaderOf = map.get("class-loader-of").toString();
        }
        this.depend = PluginDescriptionFile.makePluginNameList(map, "depend");
        this.softDepend = PluginDescriptionFile.makePluginNameList(map, "softdepend");
        this.loadBefore = PluginDescriptionFile.makePluginNameList(map, "loadbefore");
        if (map.get("website") != null) {
            this.website = map.get("website").toString();
        }
        if (map.get("description") != null) {
            this.description = map.get("description").toString();
        }
        if (map.get("load") != null) {
            try {
                this.order = PluginLoadOrder.valueOf(((String)map.get("load")).toUpperCase(Locale.ENGLISH).replaceAll("\\W", ""));
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "load is of wrong type");
            }
            catch (IllegalArgumentException ex) {
                throw new InvalidDescriptionException(ex, "load is not a valid choice");
            }
        }
        if (map.get("authors") != null) {
            ImmutableList.Builder authorsBuilder = ImmutableList.builder();
            if (map.get("author") != null) {
                authorsBuilder.add((Object)map.get("author").toString());
            }
            try {
                for (Map.Entry o : (Iterable)map.get("authors")) {
                    authorsBuilder.add((Object)o.toString());
                }
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "authors are of wrong type");
            }
            catch (NullPointerException ex) {
                throw new InvalidDescriptionException(ex, "authors are improperly defined");
            }
            this.authors = authorsBuilder.build();
        } else {
            this.authors = map.get("author") != null ? ImmutableList.of((Object)map.get("author").toString()) : ImmutableList.of();
        }
        if (map.get("contributors") != null) {
            contributorsBuilder = ImmutableList.builder();
            try {
                for (Map.Entry o : (Iterable)map.get("contributors")) {
                    contributorsBuilder.add((Object)o.toString());
                }
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "contributors are of wrong type");
            }
            this.contributors = contributorsBuilder.build();
        } else {
            this.contributors = ImmutableList.of();
        }
        if (map.get("default-permission") != null) {
            try {
                this.defaultPerm = PermissionDefault.getByName(map.get("default-permission").toString());
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "default-permission is of wrong type");
            }
            catch (IllegalArgumentException ex) {
                throw new InvalidDescriptionException(ex, "default-permission is not a valid choice");
            }
        }
        if (map.get("awareness") instanceof Iterable) {
            HashSet<PluginAwareness> awareness = new HashSet<PluginAwareness>();
            try {
                for (Map.Entry o : (Iterable)map.get("awareness")) {
                    awareness.add((PluginAwareness)((Object)o));
                }
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "awareness has wrong type");
            }
            this.awareness = ImmutableSet.copyOf(awareness);
        }
        if (map.get("api-version") != null) {
            this.apiVersion = map.get("api-version").toString();
        }
        if (map.get("libraries") != null) {
            contributorsBuilder = ImmutableList.builder();
            try {
                for (Map.Entry o : (Iterable)map.get("libraries")) {
                    contributorsBuilder.add((Object)o.toString());
                }
            }
            catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "libraries are of wrong type");
            }
            this.libraries = contributorsBuilder.build();
        } else {
            this.libraries = ImmutableList.of();
        }
        try {
            this.lazyPermissions = (Map)map.get("permissions");
        }
        catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "permissions are of the wrong type");
        }
        if (map.get("prefix") != null) {
            this.prefix = map.get("prefix").toString();
        }
    }

    @NotNull
    private static List<String> makePluginNameList(@NotNull Map<?, ?> map, @NotNull String key) throws InvalidDescriptionException {
        Object value = map.get(key);
        if (value == null) {
            return ImmutableList.of();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        try {
            for (Object entry : (Iterable)value) {
                builder.add((Object)entry.toString().replace(' ', '_'));
            }
        }
        catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, String.valueOf(key) + " is of wrong type");
        }
        catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "invalid " + key + " format");
        }
        return builder.build();
    }

    @NotNull
    private Map<String, Object> saveMap() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", this.name);
        if (this.provides != null) {
            map.put("provides", this.provides);
        }
        map.put("main", this.main);
        map.put("version", this.version);
        map.put("order", this.order.toString());
        map.put("default-permission", this.defaultPerm.toString());
        if (this.commands != null) {
            map.put("command", this.commands);
        }
        if (this.depend != null) {
            map.put("depend", this.depend);
        }
        if (this.softDepend != null) {
            map.put("softdepend", this.softDepend);
        }
        if (this.website != null) {
            map.put("website", this.website);
        }
        if (this.description != null) {
            map.put("description", this.description);
        }
        if (this.authors.size() == 1) {
            map.put("author", this.authors.get(0));
        } else if (this.authors.size() > 1) {
            map.put("authors", this.authors);
        }
        if (this.contributors != null) {
            map.put("contributors", this.contributors);
        }
        if (this.apiVersion != null) {
            map.put("api-version", this.apiVersion);
        }
        if (this.libraries != null) {
            map.put("libraries", this.libraries);
        }
        if (this.classLoaderOf != null) {
            map.put("class-loader-of", this.classLoaderOf);
        }
        if (this.prefix != null) {
            map.put("prefix", this.prefix);
        }
        return map;
    }

    @NotNull
    private Map<?, ?> asMap(@NotNull Object object) throws InvalidDescriptionException {
        if (object instanceof Map) {
            return (Map)object;
        }
        throw new InvalidDescriptionException("Plugin description file is empty or not properly structured. Is " + object + "but should be a map.");
    }

    @Deprecated
    @NotNull
    public String getRawName() {
        return this.rawName;
    }
}

