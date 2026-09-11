/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.api.ArclightServer
 *  io.izzel.arclight.api.TickingTracker
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraftforge.eventbus.EventBus
 *  net.minecraftforge.eventbus.api.IEventBus
 */
package io.izzel.arclight.common.mod.server.api;

import io.izzel.arclight.api.ArclightServer;
import io.izzel.arclight.api.TickingTracker;
import io.izzel.arclight.api.Unsafe;
import io.izzel.arclight.common.mod.server.api.DefaultTickingTracker;
import io.izzel.arclight.common.mod.util.PluginEventHandler;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public class DefaultArclightServer
implements ArclightServer {
    private final TickingTracker tickingTracker = new DefaultTickingTracker();

    public void registerForgeEvent(Plugin plugin, IEventBus bus, Object target) {
        try {
            if (bus instanceof EventBus) {
                PluginEventHandler.register(plugin, (EventBus)bus, target);
            } else {
                bus.register(target);
            }
        }
        catch (Throwable t) {
            Unsafe.throwException((Throwable)t);
        }
    }

    public TickingTracker getTickingTracker() {
        return this.tickingTracker;
    }
}

