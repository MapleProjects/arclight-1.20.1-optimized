/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin.messaging;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PluginMessageListener {
    public void onPluginMessageReceived(@NotNull String var1, @NotNull Player var2, @NotNull byte[] var3);
}

