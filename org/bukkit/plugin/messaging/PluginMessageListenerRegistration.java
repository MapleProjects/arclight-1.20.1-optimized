/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin.messaging;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public final class PluginMessageListenerRegistration {
    private final Messenger messenger;
    private final Plugin plugin;
    private final String channel;
    private final PluginMessageListener listener;

    public PluginMessageListenerRegistration(@NotNull Messenger messenger, @NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
        if (messenger == null) {
            throw new IllegalArgumentException("Messenger cannot be null!");
        }
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null!");
        }
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null!");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null!");
        }
        this.messenger = messenger;
        this.plugin = plugin;
        this.channel = channel;
        this.listener = listener;
    }

    @NotNull
    public String getChannel() {
        return this.channel;
    }

    @NotNull
    public PluginMessageListener getListener() {
        return this.listener;
    }

    @NotNull
    public Plugin getPlugin() {
        return this.plugin;
    }

    public boolean isValid() {
        return this.messenger.isRegistrationValid(this);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PluginMessageListenerRegistration other = (PluginMessageListenerRegistration)obj;
        if (this.messenger != other.messenger && !this.messenger.equals(other.messenger)) {
            return false;
        }
        if (this.plugin != other.plugin && !this.plugin.equals(other.plugin)) {
            return false;
        }
        if (!this.channel.equals(other.channel)) {
            return false;
        }
        return this.listener == other.listener || this.listener.equals(other.listener);
    }

    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.messenger.hashCode();
        hash = 53 * hash + this.plugin.hashCode();
        hash = 53 * hash + this.channel.hashCode();
        hash = 53 * hash + this.listener.hashCode();
        return hash;
    }
}

