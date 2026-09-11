/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.common.util.BlockSnapshot
 *  net.minecraftforge.event.level.BlockEvent$EntityMultiPlaceEvent
 *  net.minecraftforge.event.level.BlockEvent$EntityPlaceEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 */
package io.izzel.arclight.common.mod.server.event;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.ArclightBlockSnapshot;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.ArrayList;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceEventDispatcher {
    @SubscribeEvent(receiveCanceled=true)
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ServerPlayerEntityBridge) {
            ServerPlayerEntityBridge playerEntity = (ServerPlayerEntityBridge)entity;
            CraftPlayer player = playerEntity.bridge$getBukkitEntity();
            Direction direction = ArclightCaptures.getPlaceEventDirection();
            if (direction != null && DistValidate.isValid(event.getLevel())) {
                EquipmentSlot bukkitHand;
                ItemStack bukkitStack;
                InteractionHand hand = ArclightCaptures.getPlaceEventHand(InteractionHand.MAIN_HAND);
                ArclightBlockSnapshot placedBlock = ArclightBlockSnapshot.fromBlockSnapshot(event.getBlockSnapshot(), true);
                CraftBlock againstBlock = CraftBlock.at(event.getLevel(), event.getPos().m_121945_(direction.m_122424_()));
                if (hand == InteractionHand.MAIN_HAND) {
                    bukkitStack = player.getInventory().getItemInMainHand();
                    bukkitHand = EquipmentSlot.HAND;
                } else {
                    bukkitStack = player.getInventory().getItemInOffHand();
                    bukkitHand = EquipmentSlot.OFF_HAND;
                }
                BlockPlaceEvent placeEvent = new BlockPlaceEvent(placedBlock, placedBlock.getState(), againstBlock, bukkitStack, player, !event.isCanceled(), bukkitHand);
                placeEvent.setCancelled(event.isCanceled());
                Bukkit.getPluginManager().callEvent(placeEvent);
                event.setCanceled(placeEvent.isCancelled() || !placeEvent.canBuild());
            }
        }
    }

    @SubscribeEvent(receiveCanceled=true)
    public void onMultiPlace(BlockEvent.EntityMultiPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ServerPlayerEntityBridge) {
            ServerPlayerEntityBridge playerEntity = (ServerPlayerEntityBridge)entity;
            CraftPlayer player = playerEntity.bridge$getBukkitEntity();
            Direction direction = ArclightCaptures.getPlaceEventDirection();
            if (direction != null && DistValidate.isValid(event.getLevel())) {
                InteractionHand hand = ArclightCaptures.getPlaceEventHand(InteractionHand.MAIN_HAND);
                ArrayList<BlockState> placedBlocks = new ArrayList<BlockState>(event.getReplacedBlockSnapshots().size());
                for (BlockSnapshot snapshot : event.getReplacedBlockSnapshots()) {
                    placedBlocks.add(ArclightBlockSnapshot.fromBlockSnapshot(snapshot, true).getState());
                }
                CraftBlock againstBlock = CraftBlock.at(event.getLevel(), event.getPos().m_121945_(direction.m_122424_()));
                ItemStack bukkitStack = hand == InteractionHand.MAIN_HAND ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();
                BlockMultiPlaceEvent placeEvent = new BlockMultiPlaceEvent(placedBlocks, againstBlock, bukkitStack, player, !event.isCanceled());
                placeEvent.setCancelled(event.isCanceled());
                Bukkit.getPluginManager().callEvent(placeEvent);
                event.setCanceled(placeEvent.isCancelled() || !placeEvent.canBuild());
            }
        }
    }
}

