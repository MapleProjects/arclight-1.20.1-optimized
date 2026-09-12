/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.eventbus.api.IEventBus
 *  org.bukkit.plugin.Plugin
 */
package io.izzel.arclight.api;

import io.izzel.arclight.api.ArclightServer;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.TickingTracker;
import java.util.Objects;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class Arclight {
    private static ArclightServer server;

    public static ArclightVersion getVersion() {
        return Arclight.getServer().getVersion();
    }

    public static void registerForgeEvent(Plugin plugin, IEventBus eventBus, Object target) {
        Arclight.getServer().registerForgeEvent(plugin, eventBus, target);
    }

    public static TickingTracker getTickingTracker() {
        return Arclight.getServer().getTickingTracker();
    }

    private static ArclightServer getServer() {
        return Objects.requireNonNull(server, "Server not set!");
    }

    public static void setServer(ArclightServer server) {
        Objects.requireNonNull(server, "server");
        if (Arclight.server != null) {
            throw new IllegalStateException("Server already set!");
        }
        Arclight.server = server;
    }
}

