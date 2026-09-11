/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerArmorStandManipulateEvent
extends PlayerInteractEntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack playerItem;
    private final ItemStack armorStandItem;
    private final EquipmentSlot slot;

    public PlayerArmorStandManipulateEvent(@NotNull Player who, @NotNull ArmorStand clickedEntity, @NotNull ItemStack playerItem, @NotNull ItemStack armorStandItem, @NotNull EquipmentSlot slot, @NotNull EquipmentSlot hand) {
        super(who, clickedEntity, hand);
        this.playerItem = playerItem;
        this.armorStandItem = armorStandItem;
        this.slot = slot;
    }

    @Deprecated
    public PlayerArmorStandManipulateEvent(@NotNull Player who, @NotNull ArmorStand clickedEntity, @NotNull ItemStack playerItem, @NotNull ItemStack armorStandItem, @NotNull EquipmentSlot slot) {
        this(who, clickedEntity, playerItem, armorStandItem, slot, EquipmentSlot.HAND);
    }

    @NotNull
    public ItemStack getPlayerItem() {
        return this.playerItem;
    }

    @NotNull
    public ItemStack getArmorStandItem() {
        return this.armorStandItem;
    }

    @NotNull
    public EquipmentSlot getSlot() {
        return this.slot;
    }

    @Override
    @NotNull
    public EquipmentSlot getHand() {
        return super.getHand();
    }

    @Override
    @NotNull
    public ArmorStand getRightClicked() {
        return (ArmorStand)this.clickedEntity;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

