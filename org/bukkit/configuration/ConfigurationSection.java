/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ConfigurationSection {
    @NotNull
    public Set<String> getKeys(boolean var1);

    @NotNull
    public Map<String, Object> getValues(boolean var1);

    public boolean contains(@NotNull String var1);

    public boolean contains(@NotNull String var1, boolean var2);

    public boolean isSet(@NotNull String var1);

    @Nullable
    public String getCurrentPath();

    @NotNull
    public String getName();

    @Nullable
    public Configuration getRoot();

    @Nullable
    public ConfigurationSection getParent();

    @Nullable
    public Object get(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public Object get(@NotNull String var1, @Nullable Object var2);

    public void set(@NotNull String var1, @Nullable Object var2);

    @NotNull
    public ConfigurationSection createSection(@NotNull String var1);

    @NotNull
    public ConfigurationSection createSection(@NotNull String var1, @NotNull Map<?, ?> var2);

    @Nullable
    public String getString(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public String getString(@NotNull String var1, @Nullable String var2);

    public boolean isString(@NotNull String var1);

    public int getInt(@NotNull String var1);

    public int getInt(@NotNull String var1, int var2);

    public boolean isInt(@NotNull String var1);

    public boolean getBoolean(@NotNull String var1);

    public boolean getBoolean(@NotNull String var1, boolean var2);

    public boolean isBoolean(@NotNull String var1);

    public double getDouble(@NotNull String var1);

    public double getDouble(@NotNull String var1, double var2);

    public boolean isDouble(@NotNull String var1);

    public long getLong(@NotNull String var1);

    public long getLong(@NotNull String var1, long var2);

    public boolean isLong(@NotNull String var1);

    @Nullable
    public List<?> getList(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public List<?> getList(@NotNull String var1, @Nullable List<?> var2);

    public boolean isList(@NotNull String var1);

    @NotNull
    public List<String> getStringList(@NotNull String var1);

    @NotNull
    public List<Integer> getIntegerList(@NotNull String var1);

    @NotNull
    public List<Boolean> getBooleanList(@NotNull String var1);

    @NotNull
    public List<Double> getDoubleList(@NotNull String var1);

    @NotNull
    public List<Float> getFloatList(@NotNull String var1);

    @NotNull
    public List<Long> getLongList(@NotNull String var1);

    @NotNull
    public List<Byte> getByteList(@NotNull String var1);

    @NotNull
    public List<Character> getCharacterList(@NotNull String var1);

    @NotNull
    public List<Short> getShortList(@NotNull String var1);

    @NotNull
    public List<Map<?, ?>> getMapList(@NotNull String var1);

    @Nullable
    public <T> T getObject(@NotNull String var1, @NotNull Class<T> var2);

    @Contract(value="_, _, !null -> !null")
    @Nullable
    public <T> T getObject(@NotNull String var1, @NotNull Class<T> var2, @Nullable T var3);

    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String var1, @NotNull Class<T> var2);

    @Contract(value="_, _, !null -> !null")
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String var1, @NotNull Class<T> var2, @Nullable T var3);

    @Nullable
    public Vector getVector(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public Vector getVector(@NotNull String var1, @Nullable Vector var2);

    public boolean isVector(@NotNull String var1);

    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String var1, @Nullable OfflinePlayer var2);

    public boolean isOfflinePlayer(@NotNull String var1);

    @Nullable
    public ItemStack getItemStack(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public ItemStack getItemStack(@NotNull String var1, @Nullable ItemStack var2);

    public boolean isItemStack(@NotNull String var1);

    @Nullable
    public Color getColor(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public Color getColor(@NotNull String var1, @Nullable Color var2);

    public boolean isColor(@NotNull String var1);

    @Nullable
    public Location getLocation(@NotNull String var1);

    @Contract(value="_, !null -> !null")
    @Nullable
    public Location getLocation(@NotNull String var1, @Nullable Location var2);

    public boolean isLocation(@NotNull String var1);

    @Nullable
    public ConfigurationSection getConfigurationSection(@NotNull String var1);

    public boolean isConfigurationSection(@NotNull String var1);

    @Nullable
    public ConfigurationSection getDefaultSection();

    public void addDefault(@NotNull String var1, @Nullable Object var2);

    @NotNull
    public List<String> getComments(@NotNull String var1);

    @NotNull
    public List<String> getInlineComments(@NotNull String var1);

    public void setComments(@NotNull String var1, @Nullable List<String> var2);

    public void setInlineComments(@NotNull String var1, @Nullable List<String> var2);
}

