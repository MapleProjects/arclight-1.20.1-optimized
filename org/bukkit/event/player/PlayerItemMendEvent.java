/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerItemMendEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack item;
    private final EquipmentSlot slot;
    private final ExperienceOrb experienceOrb;
    private int repairAmount;
    private boolean cancelled;

    public PlayerItemMendEvent(@NotNull Player who, @NotNull ItemStack item, @NotNull EquipmentSlot slot, @NotNull ExperienceOrb experienceOrb, int repairAmount) {
        super(who);
        this.item = item;
        this.slot = slot;
        this.experienceOrb = experienceOrb;
        this.repairAmount = repairAmount;
    }

    @Deprecated
    public PlayerItemMendEvent(@NotNull Player who, @NotNull ItemStack item, @NotNull ExperienceOrb experienceOrb, int repairAmount) {
        this(who, item, null, experienceOrb, repairAmount);
    }

    @NotNull
    public ItemStack getItem() {
        return this.item;
    }

    @NotNull
    public EquipmentSlot getSlot() {
        return this.slot;
    }

    @NotNull
    public ExperienceOrb getExperienceOrb() {
        return this.experienceOrb;
    }

    public int getRepairAmount() {
        return this.repairAmount;
    }

    public void setRepairAmount(int amount) {
        this.repairAmount = amount;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

