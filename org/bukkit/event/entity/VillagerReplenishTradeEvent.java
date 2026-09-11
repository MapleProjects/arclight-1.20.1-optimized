/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

public class VillagerReplenishTradeEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private MerchantRecipe recipe;

    public VillagerReplenishTradeEvent(@NotNull AbstractVillager what, @NotNull MerchantRecipe recipe) {
        super(what);
        this.recipe = recipe;
    }

    @NotNull
    public MerchantRecipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(@NotNull MerchantRecipe recipe) {
        this.recipe = recipe;
    }

    @Deprecated
    public int getBonus() {
        return this.recipe.getUses();
    }

    @Deprecated
    public void setBonus(int bonus) {
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
    public AbstractVillager getEntity() {
        return (AbstractVillager)super.getEntity();
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

