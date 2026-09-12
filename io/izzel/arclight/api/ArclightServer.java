/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.eventbus.api.IEventBus
 *  org.bukkit.plugin.Plugin
 */
package io.izzel.arclight.api;

import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.TickingTracker;
import net.minecraftforge.eventbus.api.IEventBus;
import org.bukkit.plugin.Plugin;

public interface ArclightServer {
    default public ArclightVersion getVersion() {
        return ArclightVersion.current();
    }

    public void registerForgeEvent(Plugin var1, IEventBus var2, Object var3);

    public TickingTracker getTickingTracker();
}

