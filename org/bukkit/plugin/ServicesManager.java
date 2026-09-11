/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import java.util.Collection;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ServicesManager {
    public <T> void register(@NotNull Class<T> var1, @NotNull T var2, @NotNull Plugin var3, @NotNull ServicePriority var4);

    public void unregisterAll(@NotNull Plugin var1);

    public void unregister(@NotNull Class<?> var1, @NotNull Object var2);

    public void unregister(@NotNull Object var1);

    @Nullable
    public <T> T load(@NotNull Class<T> var1);

    @Nullable
    public <T> RegisteredServiceProvider<T> getRegistration(@NotNull Class<T> var1);

    @NotNull
    public List<RegisteredServiceProvider<?>> getRegistrations(@NotNull Plugin var1);

    @NotNull
    public <T> Collection<RegisteredServiceProvider<T>> getRegistrations(@NotNull Class<T> var1);

    @NotNull
    public Collection<Class<?>> getKnownServices();

    public <T> boolean isProvidedFor(@NotNull Class<T> var1);
}

