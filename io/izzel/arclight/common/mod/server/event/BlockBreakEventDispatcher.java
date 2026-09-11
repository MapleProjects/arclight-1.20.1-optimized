/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraftforge.event.level.BlockEvent$BreakEvent
 *  net.minecraftforge.event.level.BlockEvent$FarmlandTrampleEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package io.izzel.arclight.common.mod.server.event;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityInteractEvent;

public class BlockBreakEventDispatcher {
    @SubscribeEvent(receiveCanceled=true)
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (DistValidate.isValid(event.getLevel())) {
            CraftBlock craftBlock = CraftBlock.at(event.getLevel(), event.getPos());
            BlockBreakEvent breakEvent = new BlockBreakEvent((Block)craftBlock, ((ServerPlayerEntityBridge)event.getPlayer()).bridge$getBukkitEntity());
            ArclightCaptures.captureBlockBreakPlayer(breakEvent);
            breakEvent.setCancelled(event.isCanceled());
            breakEvent.setExpToDrop(event.getExpToDrop());
            Bukkit.getPluginManager().callEvent(breakEvent);
            event.setCanceled(breakEvent.isCancelled());
            event.setExpToDrop(breakEvent.getExpToDrop());
        }
    }

    @SubscribeEvent
    public void onFarmlandBreak(BlockEvent.FarmlandTrampleEvent event) {
        Event cancellable;
        if (!DistValidate.isValid(event.getLevel())) {
            return;
        }
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, event.getPos(), null, null, null);
        } else {
            cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at(event.getLevel(), event.getPos()));
            Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
        }
        if (cancellable.isCancelled()) {
            event.setCanceled(true);
            return;
        }
        if (!CraftEventFactory.callEntityChangeBlockEvent(entity, event.getPos(), Blocks.f_50493_.m_49966_())) {
            event.setCanceled(true);
        }
    }
}

