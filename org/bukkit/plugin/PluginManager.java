/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import java.io.File;
import java.util.Set;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.UnknownDependencyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PluginManager {
    public void registerInterface(@NotNull Class<? extends PluginLoader> var1) throws IllegalArgumentException;

    @Nullable
    public Plugin getPlugin(@NotNull String var1);

    @NotNull
    public Plugin[] getPlugins();

    public boolean isPluginEnabled(@NotNull String var1);

    @Contract(value="null -> false")
    public boolean isPluginEnabled(@Nullable Plugin var1);

    @Nullable
    public Plugin loadPlugin(@NotNull File var1) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;

    @NotNull
    public Plugin[] loadPlugins(@NotNull File var1);

    public void disablePlugins();

    public void clearPlugins();

    public void callEvent(@NotNull Event var1) throws IllegalStateException;

    public void registerEvents(@NotNull Listener var1, @NotNull Plugin var2);

    public void registerEvent(@NotNull Class<? extends Event> var1, @NotNull Listener var2, @NotNull EventPriority var3, @NotNull EventExecutor var4, @NotNull Plugin var5);

    public void registerEvent(@NotNull Class<? extends Event> var1, @NotNull Listener var2, @NotNull EventPriority var3, @NotNull EventExecutor var4, @NotNull Plugin var5, boolean var6);

    public void enablePlugin(@NotNull Plugin var1);

    public void disablePlugin(@NotNull Plugin var1);

    @Nullable
    public Permission getPermission(@NotNull String var1);

    public void addPermission(@NotNull Permission var1);

    public void removePermission(@NotNull Permission var1);

    public void removePermission(@NotNull String var1);

    @NotNull
    public Set<Permission> getDefaultPermissions(boolean var1);

    public void recalculatePermissionDefaults(@NotNull Permission var1);

    public void subscribeToPermission(@NotNull String var1, @NotNull Permissible var2);

    public void unsubscribeFromPermission(@NotNull String var1, @NotNull Permissible var2);

    @NotNull
    public Set<Permissible> getPermissionSubscriptions(@NotNull String var1);

    public void subscribeToDefaultPerms(boolean var1, @NotNull Permissible var2);

    public void unsubscribeFromDefaultPerms(boolean var1, @NotNull Permissible var2);

    @NotNull
    public Set<Permissible> getDefaultPermSubscriptions(boolean var1);

    @NotNull
    public Set<Permission> getPermissions();

    public boolean useTimings();
}

