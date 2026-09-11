/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.enchantment;

import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PrepareItemEnchantEvent
extends InventoryEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block table;
    private final ItemStack item;
    private final EnchantmentOffer[] offers;
    private final int bonus;
    private boolean cancelled;
    private final Player enchanter;

    public PrepareItemEnchantEvent(@NotNull Player enchanter, @NotNull InventoryView view, @NotNull Block table, @NotNull ItemStack item, @NotNull EnchantmentOffer[] offers, int bonus) {
        super(view);
        this.enchanter = enchanter;
        this.table = table;
        this.item = item;
        this.offers = offers;
        this.bonus = bonus;
    }

    @NotNull
    public Player getEnchanter() {
        return this.enchanter;
    }

    @NotNull
    public Block getEnchantBlock() {
        return this.table;
    }

    @NotNull
    public ItemStack getItem() {
        return this.item;
    }

    @NotNull
    public int[] getExpLevelCostsOffered() {
        int[] levelOffers = new int[this.offers.length];
        int i = 0;
        while (i < this.offers.length) {
            levelOffers[i] = this.offers[i] != null ? this.offers[i].getCost() : 0;
            ++i;
        }
        return levelOffers;
    }

    @NotNull
    public EnchantmentOffer[] getOffers() {
        return this.offers;
    }

    public int getEnchantmentBonus() {
        return this.bonus;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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

