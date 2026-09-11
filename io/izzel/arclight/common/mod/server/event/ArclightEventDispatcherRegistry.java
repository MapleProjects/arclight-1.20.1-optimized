/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 */
package io.izzel.arclight.common.mod.server.event;

import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.event.BlockBreakEventDispatcher;
import io.izzel.arclight.common.mod.server.event.BlockPlaceEventDispatcher;
import io.izzel.arclight.common.mod.server.event.EntityEventDispatcher;
import io.izzel.arclight.common.mod.server.event.EntityPotionEffectEventDispatcher;
import io.izzel.arclight.common.mod.server.event.EntityTeleportEventDispatcher;
import io.izzel.arclight.common.mod.server.event.ItemEntityEventDispatcher;
import io.izzel.arclight.common.mod.server.event.WorldEventDispatcher;
import net.minecraftforge.common.MinecraftForge;

public abstract class ArclightEventDispatcherRegistry {
    public static void registerAllEventDispatchers() {
        MinecraftForge.EVENT_BUS.register((Object)new BlockBreakEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new BlockPlaceEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new EntityPotionEffectEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new EntityEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new EntityTeleportEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new ItemEntityEventDispatcher());
        MinecraftForge.EVENT_BUS.register((Object)new WorldEventDispatcher());
        ArclightMod.LOGGER.info("registry.forge-event");
    }
}

