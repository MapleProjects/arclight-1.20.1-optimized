/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration;

import java.util.Map;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Configuration
extends ConfigurationSection {
    @Override
    public void addDefault(@NotNull String var1, @Nullable Object var2);

    public void addDefaults(@NotNull Map<String, Object> var1);

    public void addDefaults(@NotNull Configuration var1);

    public void setDefaults(@NotNull Configuration var1);

    @Nullable
    public Configuration getDefaults();

    @NotNull
    public ConfigurationOptions options();
}

