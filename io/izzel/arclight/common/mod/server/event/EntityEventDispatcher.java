/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.tools.collection.XmapList
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraftforge.event.entity.EntityStruckByLightningEvent
 *  net.minecraftforge.event.entity.living.AnimalTameEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.eventbus.api.EventPriority
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package io.izzel.arclight.common.mod.server.event;

import io.izzel.arclight.common.mod.server.event.ArclightEventFactory;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.tools.collection.XmapList;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class EntityEventDispatcher {
    @SubscribeEvent(receiveCanceled=true)
    public void onLivingDeath(LivingDropsEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            return;
        }
        LivingEntity livingEntity = event.getEntity();
        ArrayList drops = event.getDrops();
        if (!(drops instanceof ArrayList)) {
            drops = new ArrayList(drops);
        }
        XmapList itemStackList = XmapList.create((List)drops, ItemStack.class, entity -> CraftItemStack.asCraftMirror(entity.m_32055_()), itemStack -> {
            ItemEntity itemEntity = new ItemEntity(livingEntity.m_9236_(), livingEntity.m_20185_(), livingEntity.m_20186_(), livingEntity.m_20189_(), CraftItemStack.asNMSCopy(itemStack));
            itemEntity.m_32060_();
            return itemEntity;
        });
        ArclightEventFactory.callEntityDeathEvent(livingEntity, (List<ItemStack>)itemStackList);
        if (drops.isEmpty()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityTame(AnimalTameEvent event) {
        event.setCanceled(CraftEventFactory.callEntityTameEvent((Mob)event.getAnimal(), event.getTamer()).isCancelled());
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLightningBolt(EntityStruckByLightningEvent event) {
        ArclightCaptures.captureDamageEventEntity((Entity)event.getLightning());
    }
}

