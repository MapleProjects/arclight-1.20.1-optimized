/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration.file;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class YamlConfigurationOptions
extends FileConfigurationOptions {
    private int indent = 2;
    private int width = 80;

    protected YamlConfigurationOptions(@NotNull YamlConfiguration configuration) {
        super(configuration);
    }

    @Override
    @NotNull
    public YamlConfiguration configuration() {
        return (YamlConfiguration)super.configuration();
    }

    @Override
    @NotNull
    public YamlConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    @NotNull
    public YamlConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    @Override
    @NotNull
    public YamlConfigurationOptions setHeader(@Nullable List<String> value) {
        super.setHeader(value);
        return this;
    }

    @Override
    @Deprecated
    @NotNull
    public YamlConfigurationOptions header(@Nullable String value) {
        super.header(value);
        return this;
    }

    @Override
    @NotNull
    public YamlConfigurationOptions setFooter(@Nullable List<String> value) {
        super.setFooter(value);
        return this;
    }

    @Override
    @NotNull
    public YamlConfigurationOptions parseComments(boolean value) {
        super.parseComments(value);
        return this;
    }

    @Override
    @Deprecated
    @NotNull
    public YamlConfigurationOptions copyHeader(boolean value) {
        super.copyHeader(value);
        return this;
    }

    public int indent() {
        return this.indent;
    }

    @NotNull
    public YamlConfigurationOptions indent(int value) {
        Preconditions.checkArgument((value >= 2 ? 1 : 0) != 0, (Object)"Indent must be at least 2 characters");
        Preconditions.checkArgument((value <= 9 ? 1 : 0) != 0, (Object)"Indent cannot be greater than 9 characters");
        this.indent = value;
        return this;
    }

    public int width() {
        return this.width;
    }

    @NotNull
    public YamlConfigurationOptions width(int value) {
        this.width = value;
        return this;
    }
}

