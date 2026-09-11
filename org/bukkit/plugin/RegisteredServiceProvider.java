/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.NotNull;

public class RegisteredServiceProvider<T>
implements Comparable<RegisteredServiceProvider<?>> {
    private Class<T> service;
    private Plugin plugin;
    private T provider;
    private ServicePriority priority;

    public RegisteredServiceProvider(@NotNull Class<T> service, @NotNull T provider, @NotNull ServicePriority priority, @NotNull Plugin plugin) {
        this.service = service;
        this.plugin = plugin;
        this.provider = provider;
        this.priority = priority;
    }

    @NotNull
    public Class<T> getService() {
        return this.service;
    }

    @NotNull
    public Plugin getPlugin() {
        return this.plugin;
    }

    @NotNull
    public T getProvider() {
        return this.provider;
    }

    @NotNull
    public ServicePriority getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(@NotNull RegisteredServiceProvider<?> other) {
        if (this.priority.ordinal() == other.getPriority().ordinal()) {
            return 0;
        }
        return this.priority.ordinal() < other.getPriority().ordinal() ? 1 : -1;
    }
}

