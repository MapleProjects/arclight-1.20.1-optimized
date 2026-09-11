/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FoodLevelChangeEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private int level;
    private final ItemStack item;

    public FoodLevelChangeEvent(@NotNull HumanEntity what, int level) {
        this(what, level, null);
    }

    public FoodLevelChangeEvent(@NotNull HumanEntity what, int level, @Nullable ItemStack item) {
        super(what);
        this.level = level;
        this.item = item;
    }

    @Override
    @NotNull
    public HumanEntity getEntity() {
        return (HumanEntity)this.entity;
    }

    @Nullable
    public ItemStack getItem() {
        return this.item == null ? null : this.item.clone();
    }

    public int getFoodLevel() {
        return this.level;
    }

    public void setFoodLevel(int level) {
        if (level < 0) {
            level = 0;
        }
        this.level = level;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
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

