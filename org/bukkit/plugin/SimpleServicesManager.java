/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableSet
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleServicesManager
implements ServicesManager {
    private final Map<Class<?>, List<RegisteredServiceProvider<?>>> providers = new HashMap();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> void register(@NotNull Class<T> service, @NotNull T provider, @NotNull Plugin plugin, @NotNull ServicePriority priority) {
        RegisteredServiceProvider<T> registeredProvider = null;
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            int position;
            List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
            if (registered == null) {
                registered = new ArrayList();
                this.providers.put(service, registered);
            }
            if ((position = Collections.binarySearch(registered, registeredProvider = new RegisteredServiceProvider<T>(service, provider, priority, plugin))) < 0) {
                registered.add(-(position + 1), registeredProvider);
            } else {
                registered.add(position, registeredProvider);
            }
        }
        Bukkit.getServer().getPluginManager().callEvent(new ServiceRegisterEvent(registeredProvider));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unregisterAll(@NotNull Plugin plugin) {
        ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<ServiceUnregisterEvent>();
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
            try {
                while (it.hasNext()) {
                    Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
                    Iterator<RegisteredServiceProvider<?>> it2 = entry.getValue().iterator();
                    try {
                        while (it2.hasNext()) {
                            RegisteredServiceProvider<?> registered = it2.next();
                            if (!registered.getPlugin().equals(plugin)) continue;
                            it2.remove();
                            unregisteredEvents.add(new ServiceUnregisterEvent(registered));
                        }
                    }
                    catch (NoSuchElementException noSuchElementException) {
                        // empty catch block
                    }
                    if (entry.getValue().size() != 0) continue;
                    it.remove();
                }
            }
            catch (NoSuchElementException noSuchElementException) {
                // empty catch block
            }
        }
        for (ServiceUnregisterEvent event : unregisteredEvents) {
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unregister(@NotNull Class<?> service, @NotNull Object provider) {
        ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<ServiceUnregisterEvent>();
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
            try {
                while (it.hasNext()) {
                    Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
                    if (entry.getKey() != service) continue;
                    Iterator<RegisteredServiceProvider<?>> it2 = entry.getValue().iterator();
                    try {
                        while (it2.hasNext()) {
                            RegisteredServiceProvider<?> registered = it2.next();
                            if (registered.getProvider() != provider) continue;
                            it2.remove();
                            unregisteredEvents.add(new ServiceUnregisterEvent(registered));
                        }
                    }
                    catch (NoSuchElementException noSuchElementException) {
                        // empty catch block
                    }
                    if (entry.getValue().size() != 0) continue;
                    it.remove();
                }
            }
            catch (NoSuchElementException noSuchElementException) {
                // empty catch block
            }
        }
        for (ServiceUnregisterEvent event : unregisteredEvents) {
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unregister(@NotNull Object provider) {
        ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<ServiceUnregisterEvent>();
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
            try {
                while (it.hasNext()) {
                    Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
                    Iterator<RegisteredServiceProvider<?>> it2 = entry.getValue().iterator();
                    try {
                        while (it2.hasNext()) {
                            RegisteredServiceProvider<?> registered = it2.next();
                            if (!registered.getProvider().equals(provider)) continue;
                            it2.remove();
                            unregisteredEvents.add(new ServiceUnregisterEvent(registered));
                        }
                    }
                    catch (NoSuchElementException noSuchElementException) {
                        // empty catch block
                    }
                    if (entry.getValue().size() != 0) continue;
                    it.remove();
                }
            }
            catch (NoSuchElementException noSuchElementException) {
                // empty catch block
            }
        }
        for (ServiceUnregisterEvent event : unregisteredEvents) {
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nullable
    public <T> T load(@NotNull Class<T> service) {
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            List<RegisteredServiceProvider<?>> registered;
            block4: {
                registered = this.providers.get(service);
                if (registered != null) break block4;
                return null;
            }
            return service.cast(registered.get(0).getProvider());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Nullable
    public <T> RegisteredServiceProvider<T> getRegistration(@NotNull Class<T> service) {
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            List<RegisteredServiceProvider<?>> registered;
            block4: {
                registered = this.providers.get(service);
                if (registered != null) break block4;
                return null;
            }
            return registered.get(0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public List<RegisteredServiceProvider<?>> getRegistrations(@NotNull Plugin plugin) {
        ImmutableList.Builder ret = ImmutableList.builder();
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            for (List<RegisteredServiceProvider<?>> registered : this.providers.values()) {
                for (RegisteredServiceProvider<?> provider : registered) {
                    if (!provider.getPlugin().equals(plugin)) continue;
                    ret.add(provider);
                }
            }
        }
        return ret.build();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public <T> List<RegisteredServiceProvider<T>> getRegistrations(@NotNull Class<T> service) {
        ImmutableList.Builder ret;
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
            if (registered == null) {
                return ImmutableList.of();
            }
            ret = ImmutableList.builder();
            for (RegisteredServiceProvider<?> provider : registered) {
                ret.add(provider);
            }
        }
        return ret.build();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public Set<Class<?>> getKnownServices() {
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            return ImmutableSet.copyOf(this.providers.keySet());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> boolean isProvidedFor(@NotNull Class<T> service) {
        Map<Class<?>, List<RegisteredServiceProvider<?>>> map = this.providers;
        synchronized (map) {
            return this.providers.containsKey(service);
        }
    }
}

