/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerShearEntityEvent
extends PlayerEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private final Entity what;
    private final ItemStack item;
    private final EquipmentSlot hand;

    public PlayerShearEntityEvent(@NotNull Player who, @NotNull Entity what, @NotNull ItemStack item, @NotNull EquipmentSlot hand) {
        super(who);
        this.what = what;
        this.item = item;
        this.hand = hand;
    }

    @Deprecated
    public PlayerShearEntityEvent(@NotNull Player who, @NotNull Entity what) {
        this(who, what, new ItemStack(Material.SHEARS), EquipmentSlot.HAND);
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public Entity getEntity() {
        return this.what;
    }

    @NotNull
    public ItemStack getItem() {
        return this.item.clone();
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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

