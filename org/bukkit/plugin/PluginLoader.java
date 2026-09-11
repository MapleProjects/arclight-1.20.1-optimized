/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.UnknownDependencyException;
import org.jetbrains.annotations.NotNull;

public interface PluginLoader {
    @NotNull
    public Plugin loadPlugin(@NotNull File var1) throws InvalidPluginException, UnknownDependencyException;

    @NotNull
    public PluginDescriptionFile getPluginDescription(@NotNull File var1) throws InvalidDescriptionException;

    @NotNull
    public Pattern[] getPluginFileFilters();

    @NotNull
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener var1, @NotNull Plugin var2);

    public void enablePlugin(@NotNull Plugin var1);

    public void disablePlugin(@NotNull Plugin var1);
}

