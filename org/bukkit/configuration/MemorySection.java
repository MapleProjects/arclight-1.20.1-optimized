/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Strings
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.SectionPathData;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MemorySection
implements ConfigurationSection {
    protected final Map<String, SectionPathData> map = new LinkedHashMap<String, SectionPathData>();
    private final Configuration root;
    private final ConfigurationSection parent;
    private final String path;
    private final String fullPath;

    protected MemorySection() {
        if (!(this instanceof Configuration)) {
            throw new IllegalStateException("Cannot construct a root MemorySection when not a Configuration");
        }
        this.path = "";
        this.fullPath = "";
        this.parent = null;
        this.root = (Configuration)((Object)this);
    }

    protected MemorySection(@NotNull ConfigurationSection parent, @NotNull String path) {
        Preconditions.checkArgument((parent != null ? 1 : 0) != 0, (Object)"Parent cannot be null");
        Preconditions.checkArgument((path != null ? 1 : 0) != 0, (Object)"Path cannot be null");
        this.path = path;
        this.parent = parent;
        this.root = parent.getRoot();
        Preconditions.checkArgument((this.root != null ? 1 : 0) != 0, (Object)"Path cannot be orphaned");
        this.fullPath = MemorySection.createPath(parent, path);
    }

    @Override
    @NotNull
    public Set<String> getKeys(boolean deep) {
        ConfigurationSection defaults;
        LinkedHashSet<String> result = new LinkedHashSet<String>();
        Configuration root = this.getRoot();
        if (root != null && root.options().copyDefaults() && (defaults = this.getDefaultSection()) != null) {
            result.addAll(defaults.getKeys(deep));
        }
        this.mapChildrenKeys(result, this, deep);
        return result;
    }

    @Override
    @NotNull
    public Map<String, Object> getValues(boolean deep) {
        ConfigurationSection defaults;
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        Configuration root = this.getRoot();
        if (root != null && root.options().copyDefaults() && (defaults = this.getDefaultSection()) != null) {
            result.putAll(defaults.getValues(deep));
        }
        this.mapChildrenValues(result, this, deep);
        return result;
    }

    @Override
    public boolean contains(@NotNull String path) {
        return this.contains(path, false);
    }

    @Override
    public boolean contains(@NotNull String path, boolean ignoreDefault) {
        return (ignoreDefault ? this.get(path, null) : this.get(path)) != null;
    }

    @Override
    public boolean isSet(@NotNull String path) {
        Configuration root = this.getRoot();
        if (root == null) {
            return false;
        }
        if (root.options().copyDefaults()) {
            return this.contains(path);
        }
        return this.get(path, null) != null;
    }

    @Override
    @NotNull
    public String getCurrentPath() {
        return this.fullPath;
    }

    @Override
    @NotNull
    public String getName() {
        return this.path;
    }

    @Override
    @Nullable
    public Configuration getRoot() {
        return this.root;
    }

    @Override
    @Nullable
    public ConfigurationSection getParent() {
        return this.parent;
    }

    @Override
    public void addDefault(@NotNull String path, @Nullable Object value) {
        Preconditions.checkArgument((path != null ? 1 : 0) != 0, (Object)"Path cannot be null");
        Configuration root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot add default without root");
        }
        if (root == this) {
            throw new UnsupportedOperationException("Unsupported addDefault(String, Object) implementation");
        }
        root.addDefault(MemorySection.createPath(this, path), value);
    }

    @Override
    @Nullable
    public ConfigurationSection getDefaultSection() {
        Configuration defaults;
        Configuration root = this.getRoot();
        Configuration configuration = defaults = root == null ? null : root.getDefaults();
        if (defaults != null && defaults.isConfigurationSection(this.getCurrentPath())) {
            return defaults.getConfigurationSection(this.getCurrentPath());
        }
        return null;
    }

    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        int i2;
        Preconditions.checkArgument((!Strings.isNullOrEmpty((String)path) ? 1 : 0) != 0, (Object)"Cannot set to an empty path");
        Configuration root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot use section without a root");
        }
        char separator = root.options().pathSeparator();
        int i1 = -1;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigurationSection subSection = section.getConfigurationSection(node);
            if (subSection == null) {
                if (value == null) {
                    return;
                }
                section = section.createSection(node);
                continue;
            }
            section = subSection;
        }
        String key = path.substring(i2);
        if (section == this) {
            if (value == null) {
                this.map.remove(key);
            } else {
                SectionPathData entry = this.map.get(key);
                if (entry == null) {
                    this.map.put(key, new SectionPathData(value));
                } else {
                    entry.setData(value);
                }
            }
        } else {
            section.set(key, value);
        }
    }

    @Override
    @Nullable
    public Object get(@NotNull String path) {
        return this.get(path, this.getDefault(path));
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public Object get(@NotNull String path, @Nullable Object def) {
        int i2;
        Preconditions.checkArgument((path != null ? 1 : 0) != 0, (Object)"Path cannot be null");
        if (path.length() == 0) {
            return this;
        }
        Configuration root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot access section without a root");
        }
        char separator = root.options().pathSeparator();
        int i1 = -1;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String currentPath = path.substring(i2, i1);
            if (!section.contains(currentPath, true)) {
                return def;
            }
            if ((section = section.getConfigurationSection(currentPath)) != null) continue;
            return def;
        }
        String key = path.substring(i2);
        if (section == this) {
            SectionPathData result = this.map.get(key);
            return result == null ? def : result.getData();
        }
        return section.get(key, def);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String path) {
        int i2;
        Preconditions.checkArgument((!Strings.isNullOrEmpty((String)path) ? 1 : 0) != 0, (Object)"Cannot create section at empty path");
        Configuration root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create section without a root");
        }
        char separator = root.options().pathSeparator();
        int i1 = -1;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigurationSection subSection = section.getConfigurationSection(node);
            section = subSection == null ? section.createSection(node) : subSection;
        }
        String key = path.substring(i2);
        if (section == this) {
            MemorySection result = new MemorySection(this, key);
            this.map.put(key, new SectionPathData(result));
            return result;
        }
        return section.createSection(key);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
        ConfigurationSection section = this.createSection(path);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map) {
                section.createSection(entry.getKey().toString(), (Map)entry.getValue());
                continue;
            }
            section.set(entry.getKey().toString(), entry.getValue());
        }
        return section;
    }

    @Override
    @Nullable
    public String getString(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getString(path, def != null ? def.toString() : null);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public String getString(@NotNull String path, @Nullable String def) {
        Object val = this.get(path, def);
        return val != null ? val.toString() : def;
    }

    @Override
    public boolean isString(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof String;
    }

    @Override
    public int getInt(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getInt(path, def instanceof Number ? NumberConversions.toInt(def) : 0);
    }

    @Override
    public int getInt(@NotNull String path, int def) {
        Object val = this.get(path, def);
        return val instanceof Number ? NumberConversions.toInt(val) : def;
    }

    @Override
    public boolean isInt(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof Integer;
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getBoolean(path, def instanceof Boolean ? (Boolean)def : false);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        Object val = this.get(path, def);
        return val instanceof Boolean ? (Boolean)val : def;
    }

    @Override
    public boolean isBoolean(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof Boolean;
    }

    @Override
    public double getDouble(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getDouble(path, def instanceof Number ? NumberConversions.toDouble(def) : 0.0);
    }

    @Override
    public double getDouble(@NotNull String path, double def) {
        Object val = this.get(path, def);
        return val instanceof Number ? NumberConversions.toDouble(val) : def;
    }

    @Override
    public boolean isDouble(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof Double;
    }

    @Override
    public long getLong(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getLong(path, def instanceof Number ? NumberConversions.toLong(def) : 0L);
    }

    @Override
    public long getLong(@NotNull String path, long def) {
        Object val = this.get(path, def);
        return val instanceof Number ? NumberConversions.toLong(val) : def;
    }

    @Override
    public boolean isLong(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof Long;
    }

    @Override
    @Nullable
    public List<?> getList(@NotNull String path) {
        Object def = this.getDefault(path);
        return this.getList(path, def instanceof List ? (List)def : null);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public List<?> getList(@NotNull String path, @Nullable List<?> def) {
        List<?> val = this.get(path, def);
        return val instanceof List ? val : def;
    }

    @Override
    public boolean isList(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof List;
    }

    @Override
    @NotNull
    public List<String> getStringList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<String>(0);
        }
        ArrayList<String> result = new ArrayList<String>();
        for (Object object : list) {
            if (!(object instanceof String) && !this.isPrimitiveWrapper(object)) continue;
            result.add(String.valueOf(object));
        }
        return result;
    }

    @Override
    @NotNull
    public List<Integer> getIntegerList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Integer>(0);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Object object : list) {
            if (object instanceof Integer) {
                result.add((Integer)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Integer.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add(Integer.valueOf(((Character)object).charValue()));
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(((Number)object).intValue());
        }
        return result;
    }

    @Override
    @NotNull
    public List<Boolean> getBooleanList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Boolean>(0);
        }
        ArrayList<Boolean> result = new ArrayList<Boolean>();
        for (Object object : list) {
            if (object instanceof Boolean) {
                result.add((Boolean)object);
                continue;
            }
            if (!(object instanceof String)) continue;
            if (Boolean.TRUE.toString().equals(object)) {
                result.add(true);
                continue;
            }
            if (!Boolean.FALSE.toString().equals(object)) continue;
            result.add(false);
        }
        return result;
    }

    @Override
    @NotNull
    public List<Double> getDoubleList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Double>(0);
        }
        ArrayList<Double> result = new ArrayList<Double>();
        for (Object object : list) {
            if (object instanceof Double) {
                result.add((Double)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Double.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add(Double.valueOf(((Character)object).charValue()));
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(((Number)object).doubleValue());
        }
        return result;
    }

    @Override
    @NotNull
    public List<Float> getFloatList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Float>(0);
        }
        ArrayList<Float> result = new ArrayList<Float>();
        for (Object object : list) {
            if (object instanceof Float) {
                result.add((Float)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Float.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add(Float.valueOf(((Character)object).charValue()));
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(Float.valueOf(((Number)object).floatValue()));
        }
        return result;
    }

    @Override
    @NotNull
    public List<Long> getLongList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Long>(0);
        }
        ArrayList<Long> result = new ArrayList<Long>();
        for (Object object : list) {
            if (object instanceof Long) {
                result.add((Long)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Long.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add(Long.valueOf(((Character)object).charValue()));
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(((Number)object).longValue());
        }
        return result;
    }

    @Override
    @NotNull
    public List<Byte> getByteList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Byte>(0);
        }
        ArrayList<Byte> result = new ArrayList<Byte>();
        for (Object object : list) {
            if (object instanceof Byte) {
                result.add((Byte)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Byte.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add((byte)((Character)object).charValue());
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(((Number)object).byteValue());
        }
        return result;
    }

    @Override
    @NotNull
    public List<Character> getCharacterList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Character>(0);
        }
        ArrayList<Character> result = new ArrayList<Character>();
        for (Object object : list) {
            if (object instanceof Character) {
                result.add((Character)object);
                continue;
            }
            if (object instanceof String) {
                String str = (String)object;
                if (str.length() != 1) continue;
                result.add(Character.valueOf(str.charAt(0)));
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(Character.valueOf((char)((Number)object).intValue()));
        }
        return result;
    }

    @Override
    @NotNull
    public List<Short> getShortList(@NotNull String path) {
        List<?> list = this.getList(path);
        if (list == null) {
            return new ArrayList<Short>(0);
        }
        ArrayList<Short> result = new ArrayList<Short>();
        for (Object object : list) {
            if (object instanceof Short) {
                result.add((Short)object);
                continue;
            }
            if (object instanceof String) {
                try {
                    result.add(Short.valueOf((String)object));
                }
                catch (Exception exception) {}
                continue;
            }
            if (object instanceof Character) {
                result.add((short)((Character)object).charValue());
                continue;
            }
            if (!(object instanceof Number)) continue;
            result.add(((Number)object).shortValue());
        }
        return result;
    }

    @Override
    @NotNull
    public List<Map<?, ?>> getMapList(@NotNull String path) {
        List<?> list = this.getList(path);
        ArrayList result = new ArrayList();
        if (list == null) {
            return result;
        }
        for (Object object : list) {
            if (!(object instanceof Map)) continue;
            result.add((Map)object);
        }
        return result;
    }

    @Override
    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Class cannot be null");
        Object def = this.getDefault(path);
        return this.getObject(path, clazz, def != null && clazz.isInstance(def) ? (T)clazz.cast(def) : null);
    }

    @Override
    @Contract(value="_, _, !null -> !null")
    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        Preconditions.checkArgument((clazz != null ? 1 : 0) != 0, (Object)"Class cannot be null");
        Object val = this.get(path, def);
        return val != null && clazz.isInstance(val) ? clazz.cast(val) : def;
    }

    @Override
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
        return (T)((ConfigurationSerializable)this.getObject(path, clazz));
    }

    @Override
    @Contract(value="_, _, !null -> !null")
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return (T)((ConfigurationSerializable)this.getObject(path, clazz, def));
    }

    @Override
    @Nullable
    public Vector getVector(@NotNull String path) {
        return this.getSerializable(path, Vector.class);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public Vector getVector(@NotNull String path, @Nullable Vector def) {
        return this.getSerializable(path, Vector.class, def);
    }

    @Override
    public boolean isVector(@NotNull String path) {
        return this.getSerializable(path, Vector.class) != null;
    }

    @Override
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path) {
        return this.getSerializable(path, OfflinePlayer.class);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
        return this.getSerializable(path, OfflinePlayer.class, def);
    }

    @Override
    public boolean isOfflinePlayer(@NotNull String path) {
        return this.getSerializable(path, OfflinePlayer.class) != null;
    }

    @Override
    @Nullable
    public ItemStack getItemStack(@NotNull String path) {
        return this.getSerializable(path, ItemStack.class);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
        return this.getSerializable(path, ItemStack.class, def);
    }

    @Override
    public boolean isItemStack(@NotNull String path) {
        return this.getSerializable(path, ItemStack.class) != null;
    }

    @Override
    @Nullable
    public Color getColor(@NotNull String path) {
        return this.getSerializable(path, Color.class);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public Color getColor(@NotNull String path, @Nullable Color def) {
        return this.getSerializable(path, Color.class, def);
    }

    @Override
    public boolean isColor(@NotNull String path) {
        return this.getSerializable(path, Color.class) != null;
    }

    @Override
    @Nullable
    public Location getLocation(@NotNull String path) {
        return this.getSerializable(path, Location.class);
    }

    @Override
    @Contract(value="_, !null -> !null")
    @Nullable
    public Location getLocation(@NotNull String path, @Nullable Location def) {
        return this.getSerializable(path, Location.class, def);
    }

    @Override
    public boolean isLocation(@NotNull String path) {
        return this.getSerializable(path, Location.class) != null;
    }

    @Override
    @Nullable
    public ConfigurationSection getConfigurationSection(@NotNull String path) {
        Object val = this.get(path, null);
        if (val != null) {
            return val instanceof ConfigurationSection ? (ConfigurationSection)val : null;
        }
        val = this.get(path, this.getDefault(path));
        return val instanceof ConfigurationSection ? this.createSection(path) : null;
    }

    @Override
    public boolean isConfigurationSection(@NotNull String path) {
        Object val = this.get(path);
        return val instanceof ConfigurationSection;
    }

    protected boolean isPrimitiveWrapper(@Nullable Object input) {
        return input instanceof Integer || input instanceof Boolean || input instanceof Character || input instanceof Byte || input instanceof Short || input instanceof Double || input instanceof Long || input instanceof Float;
    }

    @Nullable
    protected Object getDefault(@NotNull String path) {
        Preconditions.checkArgument((path != null ? 1 : 0) != 0, (Object)"Path cannot be null");
        Configuration root = this.getRoot();
        Configuration defaults = root == null ? null : root.getDefaults();
        return defaults == null ? null : defaults.get(MemorySection.createPath(this, path));
    }

    protected void mapChildrenKeys(@NotNull Set<String> output, @NotNull ConfigurationSection section, boolean deep) {
        if (section instanceof MemorySection) {
            MemorySection sec = (MemorySection)section;
            for (Map.Entry<String, SectionPathData> entry : sec.map.entrySet()) {
                output.add(MemorySection.createPath(section, entry.getKey(), this));
                if (!deep || !(entry.getValue().getData() instanceof ConfigurationSection)) continue;
                ConfigurationSection subsection = (ConfigurationSection)entry.getValue().getData();
                this.mapChildrenKeys(output, subsection, deep);
            }
        } else {
            Set<String> keys = section.getKeys(deep);
            for (String key : keys) {
                output.add(MemorySection.createPath(section, key, this));
            }
        }
    }

    protected void mapChildrenValues(@NotNull Map<String, Object> output, @NotNull ConfigurationSection section, boolean deep) {
        if (section instanceof MemorySection) {
            MemorySection sec = (MemorySection)section;
            for (Map.Entry<String, SectionPathData> entry : sec.map.entrySet()) {
                String childPath = MemorySection.createPath(section, entry.getKey(), this);
                output.remove(childPath);
                output.put(childPath, entry.getValue().getData());
                if (!(entry.getValue().getData() instanceof ConfigurationSection) || !deep) continue;
                this.mapChildrenValues(output, (ConfigurationSection)entry.getValue().getData(), deep);
            }
        } else {
            Map<String, Object> values = section.getValues(deep);
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                output.put(MemorySection.createPath(section, entry.getKey(), this), entry.getValue());
            }
        }
    }

    @NotNull
    public static String createPath(@NotNull ConfigurationSection section, @Nullable String key) {
        return MemorySection.createPath(section, key, section == null ? null : section.getRoot());
    }

    @NotNull
    public static String createPath(@NotNull ConfigurationSection section, @Nullable String key, @Nullable ConfigurationSection relativeTo) {
        Preconditions.checkArgument((section != null ? 1 : 0) != 0, (Object)"Cannot create path without a section");
        Configuration root = section.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create path without a root");
        }
        char separator = root.options().pathSeparator();
        StringBuilder builder = new StringBuilder();
        ConfigurationSection parent = section;
        while (parent != null && parent != relativeTo) {
            if (builder.length() > 0) {
                builder.insert(0, separator);
            }
            builder.insert(0, parent.getName());
            parent = parent.getParent();
        }
        if (key != null && key.length() > 0) {
            if (builder.length() > 0) {
                builder.append(separator);
            }
            builder.append(key);
        }
        return builder.toString();
    }

    @Override
    @NotNull
    public List<String> getComments(@NotNull String path) {
        SectionPathData pathData = this.getSectionPathData(path);
        return pathData == null ? Collections.emptyList() : pathData.getComments();
    }

    @Override
    @NotNull
    public List<String> getInlineComments(@NotNull String path) {
        SectionPathData pathData = this.getSectionPathData(path);
        return pathData == null ? Collections.emptyList() : pathData.getInlineComments();
    }

    @Override
    public void setComments(@NotNull String path, @Nullable List<String> comments) {
        SectionPathData pathData = this.getSectionPathData(path);
        if (pathData != null) {
            pathData.setComments(comments);
        }
    }

    @Override
    public void setInlineComments(@NotNull String path, @Nullable List<String> comments) {
        SectionPathData pathData = this.getSectionPathData(path);
        if (pathData != null) {
            pathData.setInlineComments(comments);
        }
    }

    @Nullable
    private SectionPathData getSectionPathData(@NotNull String path) {
        int i2;
        Preconditions.checkArgument((path != null ? 1 : 0) != 0, (Object)"Path cannot be null");
        Configuration root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot access section without a root");
        }
        char separator = root.options().pathSeparator();
        int i1 = -1;
        ConfigurationSection section = this;
        while ((i1 = path.indexOf(separator, i2 = i1 + 1)) != -1) {
            if ((section = section.getConfigurationSection(path.substring(i2, i1))) != null) continue;
            return null;
        }
        String key = path.substring(i2);
        if (section == this) {
            SectionPathData entry = this.map.get(key);
            if (entry != null) {
                return entry;
            }
        } else if (section instanceof MemorySection) {
            return ((MemorySection)section).getSectionPathData(key);
        }
        return null;
    }

    public String toString() {
        Configuration root = this.getRoot();
        return this.getClass().getSimpleName() + "[path='" + this.getCurrentPath() + "', root='" + (root == null ? null : root.getClass().getSimpleName()) + "']";
    }
}

