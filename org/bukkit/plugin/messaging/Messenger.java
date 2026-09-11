/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin.messaging;

import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.plugin.messaging.PluginMessageListenerRegistration;
import org.jetbrains.annotations.NotNull;

public interface Messenger {
    public static final int MAX_MESSAGE_SIZE = 32766;
    public static final int MAX_CHANNEL_SIZE = 64;

    public boolean isReservedChannel(@NotNull String var1);

    public void registerOutgoingPluginChannel(@NotNull Plugin var1, @NotNull String var2);

    public void unregisterOutgoingPluginChannel(@NotNull Plugin var1, @NotNull String var2);

    public void unregisterOutgoingPluginChannel(@NotNull Plugin var1);

    @NotNull
    public PluginMessageListenerRegistration registerIncomingPluginChannel(@NotNull Plugin var1, @NotNull String var2, @NotNull PluginMessageListener var3);

    public void unregisterIncomingPluginChannel(@NotNull Plugin var1, @NotNull String var2, @NotNull PluginMessageListener var3);

    public void unregisterIncomingPluginChannel(@NotNull Plugin var1, @NotNull String var2);

    public void unregisterIncomingPluginChannel(@NotNull Plugin var1);

    @NotNull
    public Set<String> getOutgoingChannels();

    @NotNull
    public Set<String> getOutgoingChannels(@NotNull Plugin var1);

    @NotNull
    public Set<String> getIncomingChannels();

    @NotNull
    public Set<String> getIncomingChannels(@NotNull Plugin var1);

    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin var1);

    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull String var1);

    @NotNull
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin var1, @NotNull String var2);

    public boolean isRegistrationValid(@NotNull PluginMessageListenerRegistration var1);

    public boolean isIncomingChannelRegistered(@NotNull Plugin var1, @NotNull String var2);

    public boolean isOutgoingChannelRegistered(@NotNull Plugin var1, @NotNull String var2);

    public void dispatchIncomingMessage(@NotNull Player var1, @NotNull String var2, @NotNull byte[] var3);
}

