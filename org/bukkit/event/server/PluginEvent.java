/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.server;

import org.bukkit.event.server.ServerEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class PluginEvent
extends ServerEvent {
    private final Plugin plugin;

    public PluginEvent(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @NotNull
    public Plugin getPlugin() {
        return this.plugin;
    }
}

