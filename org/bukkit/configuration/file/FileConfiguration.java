/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Preconditions
 *  com.google.common.io.Files
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration.file;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FileConfiguration
extends MemoryConfiguration {
    public FileConfiguration() {
    }

    public FileConfiguration(@Nullable Configuration defaults) {
        super(defaults);
    }

    public void save(@NotNull File file) throws IOException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        Files.createParentDirs((File)file);
        String data = this.saveToString();
        try (OutputStreamWriter writer = new OutputStreamWriter((OutputStream)new FileOutputStream(file), Charsets.UTF_8);){
            writer.write(data);
        }
    }

    public void save(@NotNull String file) throws IOException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        this.save(new File(file));
    }

    @NotNull
    public abstract String saveToString();

    public void load(@NotNull File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        FileInputStream stream = new FileInputStream(file);
        this.load(new InputStreamReader((InputStream)stream, Charsets.UTF_8));
    }

    public void load(@NotNull Reader reader) throws IOException, InvalidConfigurationException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader input = reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);){
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        }
        this.loadFromString(builder.toString());
    }

    public void load(@NotNull String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        this.load(new File(file));
    }

    public abstract void loadFromString(@NotNull String var1) throws InvalidConfigurationException;

    @Deprecated
    @NotNull
    protected String buildHeader() {
        return "";
    }

    @Override
    @NotNull
    public FileConfigurationOptions options() {
        if (this.options == null) {
            this.options = new FileConfigurationOptions(this);
        }
        return (FileConfigurationOptions)this.options;
    }
}

