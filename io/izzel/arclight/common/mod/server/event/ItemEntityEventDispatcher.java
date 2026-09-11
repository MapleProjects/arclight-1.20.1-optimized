/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.event.entity.item.ItemExpireEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package io.izzel.arclight.common.mod.server.event;

import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;

public class ItemEntityEventDispatcher {
    @SubscribeEvent(receiveCanceled=true)
    public void onExpire(ItemExpireEvent event) {
        event.setCanceled(CraftEventFactory.callItemDespawnEvent(event.getEntity()).isCancelled());
    }
}

