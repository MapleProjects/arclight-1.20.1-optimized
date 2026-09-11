/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin.messaging;

import com.google.common.collect.ImmutableSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.ChannelNameTooLongException;
import org.bukkit.plugin.messaging.ChannelNotRegisteredException;
import org.bukkit.plugin.messaging.MessageTooLargeException;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.plugin.messaging.PluginMessageListenerRegistration;
import org.bukkit.plugin.messaging.ReservedChannelException;
import org.jetbrains.annotations.NotNull;

public class StandardMessenger
implements Messenger {
    private final Map<String, Set<PluginMessageListenerRegistration>> incomingByChannel = new HashMap<String, Set<PluginMessageListenerRegistration>>();
    private final Map<Plugin, Set<PluginMessageListenerRegistration>> incomingByPlugin = new HashMap<Plugin, Set<PluginMessageListenerRegistration>>();
    private final Map<String, Set<Plugin>> outgoingByChannel = new HashMap<String, Set<Plugin>>();
    private final Map<Plugin, Set<String>> outgoingByPlugin = new HashMap<Plugin, Set<String>>();
    private final Object incomingLock = new Object();
    private final Object outgoingLock = new Object();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void addToOutgoing(@NotNull Plugin plugin, @NotNull String channel) {
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<Plugin> plugins = this.outgoingByChannel.get(channel);
            Set<String> channels = this.outgoingByPlugin.get(plugin);
            if (plugins == null) {
                plugins = new HashSet<Plugin>();
                this.outgoingByChannel.put(channel, plugins);
            }
            if (channels == null) {
                channels = new HashSet<String>();
                this.outgoingByPlugin.put(plugin, channels);
            }
            plugins.add(plugin);
            channels.add(channel);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeFromOutgoing(@NotNull Plugin plugin, @NotNull String channel) {
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<Plugin> plugins = this.outgoingByChannel.get(channel);
            Set<String> channels = this.outgoingByPlugin.get(plugin);
            if (plugins != null) {
                plugins.remove(plugin);
                if (plugins.isEmpty()) {
                    this.outgoingByChannel.remove(channel);
                }
            }
            if (channels != null) {
                channels.remove(channel);
                if (channels.isEmpty()) {
                    this.outgoingByChannel.remove(channel);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeFromOutgoing(@NotNull Plugin plugin) {
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<String> channels = this.outgoingByPlugin.get(plugin);
            if (channels != null) {
                String[] toRemove = channels.toArray(new String[channels.size()]);
                this.outgoingByPlugin.remove(plugin);
                String[] stringArray = toRemove;
                int n = toRemove.length;
                int n2 = 0;
                while (n2 < n) {
                    String channel = stringArray[n2];
                    this.removeFromOutgoing(plugin, channel);
                    ++n2;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void addToIncoming(@NotNull PluginMessageListenerRegistration registration) {
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(registration.getChannel());
            if (registrations == null) {
                registrations = new HashSet<PluginMessageListenerRegistration>();
                this.incomingByChannel.put(registration.getChannel(), registrations);
            } else if (registrations.contains(registration)) {
                throw new IllegalArgumentException("This registration already exists");
            }
            registrations.add(registration);
            registrations = this.incomingByPlugin.get(registration.getPlugin());
            if (registrations == null) {
                registrations = new HashSet<PluginMessageListenerRegistration>();
                this.incomingByPlugin.put(registration.getPlugin(), registrations);
            } else if (registrations.contains(registration)) {
                throw new IllegalArgumentException("This registration already exists");
            }
            registrations.add(registration);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeFromIncoming(@NotNull PluginMessageListenerRegistration registration) {
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(registration.getChannel());
            if (registrations != null) {
                registrations.remove(registration);
                if (registrations.isEmpty()) {
                    this.incomingByChannel.remove(registration.getChannel());
                }
            }
            if ((registrations = this.incomingByPlugin.get(registration.getPlugin())) != null) {
                registrations.remove(registration);
                if (registrations.isEmpty()) {
                    this.incomingByPlugin.remove(registration.getPlugin());
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeFromIncoming(@NotNull Plugin plugin, @NotNull String channel) {
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                PluginMessageListenerRegistration[] toRemove;
                PluginMessageListenerRegistration[] pluginMessageListenerRegistrationArray = toRemove = registrations.toArray(new PluginMessageListenerRegistration[registrations.size()]);
                int n = toRemove.length;
                int n2 = 0;
                while (n2 < n) {
                    PluginMessageListenerRegistration registration = pluginMessageListenerRegistrationArray[n2];
                    if (registration.getChannel().equals(channel)) {
                        this.removeFromIncoming(registration);
                    }
                    ++n2;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void removeFromIncoming(@NotNull Plugin plugin) {
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                PluginMessageListenerRegistration[] toRemove = registrations.toArray(new PluginMessageListenerRegistration[registrations.size()]);
                this.incomingByPlugin.remove(plugin);
                PluginMessageListenerRegistration[] pluginMessageListenerRegistrationArray = toRemove;
                int n = toRemove.length;
                int n2 = 0;
                while (n2 < n) {
                    PluginMessageListenerRegistration registration = pluginMessageListenerRegistrationArray[n2];
                    this.removeFromIncoming(registration);
                    ++n2;
                }
            }
        }
    }

    @Override
    public boolean isReservedChannel(@NotNull String channel) {
        return (channel = StandardMessenger.validateAndCorrectChannel(channel)).contains("minecraft") && !channel.equals("minecraft:brand");
    }

    @Override
    public void registerOutgoingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (this.isReservedChannel(channel = StandardMessenger.validateAndCorrectChannel(channel))) {
            throw new ReservedChannelException(channel);
        }
        this.addToOutgoing(plugin, channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        this.removeFromOutgoing(plugin, channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        this.removeFromOutgoing(plugin);
    }

    @Override
    @NotNull
    public PluginMessageListenerRegistration registerIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (this.isReservedChannel(channel = StandardMessenger.validateAndCorrectChannel(channel))) {
            throw new ReservedChannelException(channel);
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }
        PluginMessageListenerRegistration result = new PluginMessageListenerRegistration(this, plugin, channel, listener);
        this.addToIncoming(result);
        return result;
    }

    @Override
    public void unregisterIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        this.removeFromIncoming(new PluginMessageListenerRegistration(this, plugin, channel, listener));
    }

    @Override
    public void unregisterIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        this.removeFromIncoming(plugin, channel);
    }

    @Override
    public void unregisterIncomingPluginChannel(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        this.removeFromIncoming(plugin);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<String> getOutgoingChannels() {
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<String> keys = this.outgoingByChannel.keySet();
            return ImmutableSet.copyOf(keys);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<String> getOutgoingChannels(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<String> channels = this.outgoingByPlugin.get(plugin);
            if (channels != null) {
                return ImmutableSet.copyOf(channels);
            }
            return ImmutableSet.of();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<String> getIncomingChannels() {
        Object object = this.incomingLock;
        synchronized (object) {
            Set<String> keys = this.incomingByChannel.keySet();
            return ImmutableSet.copyOf(keys);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<String> getIncomingChannels(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                ImmutableSet.Builder builder = ImmutableSet.builder();
                for (PluginMessageListenerRegistration registration : registrations) {
                    builder.add((Object)registration.getChannel());
                }
                return builder.build();
            }
            return ImmutableSet.of();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                return ImmutableSet.copyOf(registrations);
            }
            return ImmutableSet.of();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull String channel) {
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(channel);
            if (registrations != null) {
                return ImmutableSet.copyOf(registrations);
            }
            return ImmutableSet.of();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                ImmutableSet.Builder builder = ImmutableSet.builder();
                for (PluginMessageListenerRegistration registration : registrations) {
                    if (!registration.getChannel().equals(channel)) continue;
                    builder.add((Object)registration);
                }
                return builder.build();
            }
            return ImmutableSet.of();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isRegistrationValid(@NotNull PluginMessageListenerRegistration registration) {
        if (registration == null) {
            throw new IllegalArgumentException("Registration cannot be null");
        }
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(registration.getPlugin());
            if (registrations != null) {
                return registrations.contains(registration);
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean isIncomingChannelRegistered(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        Object object = this.incomingLock;
        synchronized (object) {
            Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
            if (registrations != null) {
                for (PluginMessageListenerRegistration registration : registrations) {
                    if (!registration.getChannel().equals(channel)) continue;
                    return true;
                }
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isOutgoingChannelRegistered(@NotNull Plugin plugin, @NotNull String channel) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        Object object = this.outgoingLock;
        synchronized (object) {
            Set<String> channels = this.outgoingByPlugin.get(plugin);
            if (channels != null) {
                return channels.contains(channel);
            }
            return false;
        }
    }

    @Override
    public void dispatchIncomingMessage(@NotNull Player source, @NotNull String channel, @NotNull byte[] message) {
        if (source == null) {
            throw new IllegalArgumentException("Player source cannot be null");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        Set<PluginMessageListenerRegistration> registrations = this.getIncomingChannelRegistrations(channel);
        for (PluginMessageListenerRegistration registration : registrations) {
            try {
                registration.getListener().onPluginMessageReceived(channel, source, message);
            }
            catch (Throwable t) {
                registration.getPlugin().getLogger().log(Level.WARNING, String.format("Plugin %s generated an exception whilst handling plugin message", registration.getPlugin().getDescription().getFullName()), t);
            }
        }
    }

    @Deprecated
    public static void validateChannel(@NotNull String channel) {
        StandardMessenger.validateAndCorrectChannel(channel);
    }

    @Deprecated
    @NotNull
    public static String validateAndCorrectChannel(@NotNull String channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }
        if (channel.equals("BungeeCord")) {
            return "bungeecord:main";
        }
        if (channel.equals("bungeecord:main")) {
            return "BungeeCord";
        }
        if (channel.length() > 64) {
            throw new ChannelNameTooLongException(channel);
        }
        if (channel.indexOf(58) == -1) {
            throw new IllegalArgumentException("Channel must contain : separator (attempted to use " + channel + ")");
        }
        if (!channel.toLowerCase(Locale.ROOT).equals(channel)) {
            throw new IllegalArgumentException("Channel must be entirely lowercase (attempted to use " + channel + ")");
        }
        return channel;
    }

    public static void validatePluginMessage(@NotNull Messenger messenger, @NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {
        if (messenger == null) {
            throw new IllegalArgumentException("Messenger cannot be null");
        }
        if (source == null) {
            throw new IllegalArgumentException("Plugin source cannot be null");
        }
        if (!source.isEnabled()) {
            throw new IllegalArgumentException("Plugin must be enabled to send messages");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if (!messenger.isOutgoingChannelRegistered(source, channel)) {
            throw new ChannelNotRegisteredException(channel);
        }
        if (message.length > 32766) {
            throw new MessageTooLargeException(message);
        }
        StandardMessenger.validateChannel(channel);
    }
}

