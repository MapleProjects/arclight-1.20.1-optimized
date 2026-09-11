/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin.messaging;

import java.util.Set;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface PluginMessageRecipient {
    public void sendPluginMessage(@NotNull Plugin var1, @NotNull String var2, @NotNull byte[] var3);

    @NotNull
    public Set<String> getListeningPluginChannels();
}

