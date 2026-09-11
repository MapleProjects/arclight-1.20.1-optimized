/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration.file;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemoryConfigurationOptions;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FileConfigurationOptions
extends MemoryConfigurationOptions {
    private List<String> header = Collections.emptyList();
    private List<String> footer = Collections.emptyList();
    private boolean parseComments = true;

    protected FileConfigurationOptions(@NotNull MemoryConfiguration configuration) {
        super(configuration);
    }

    @Override
    @NotNull
    public FileConfiguration configuration() {
        return (FileConfiguration)super.configuration();
    }

    @Override
    @NotNull
    public FileConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    @NotNull
    public FileConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    @NotNull
    public List<String> getHeader() {
        return this.header;
    }

    @Deprecated
    @NotNull
    public String header() {
        StringBuilder stringHeader = new StringBuilder();
        for (String line : this.header) {
            stringHeader.append(line == null ? "\n" : String.valueOf(line) + "\n");
        }
        return stringHeader.toString();
    }

    @NotNull
    public FileConfigurationOptions setHeader(@Nullable List<String> value) {
        this.header = value == null ? Collections.emptyList() : Collections.unmodifiableList(value);
        return this;
    }

    @Deprecated
    @NotNull
    public FileConfigurationOptions header(@Nullable String value) {
        this.header = value == null ? Collections.emptyList() : Collections.unmodifiableList(Arrays.asList(value.split("\\n")));
        return this;
    }

    @NotNull
    public List<String> getFooter() {
        return this.footer;
    }

    @NotNull
    public FileConfigurationOptions setFooter(@Nullable List<String> value) {
        this.footer = value == null ? Collections.emptyList() : Collections.unmodifiableList(value);
        return this;
    }

    public boolean parseComments() {
        return this.parseComments;
    }

    @NotNull
    public MemoryConfigurationOptions parseComments(boolean value) {
        this.parseComments = value;
        return this;
    }

    @Deprecated
    public boolean copyHeader() {
        return this.parseComments;
    }

    @Deprecated
    @NotNull
    public FileConfigurationOptions copyHeader(boolean value) {
        this.parseComments = value;
        return this;
    }
}

