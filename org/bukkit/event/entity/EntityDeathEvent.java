/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EntityDeathEvent
extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<ItemStack> drops;
    private int dropExp = 0;

    public EntityDeathEvent(@NotNull LivingEntity entity, @NotNull List<ItemStack> drops) {
        this(entity, drops, 0);
    }

    public EntityDeathEvent(@NotNull LivingEntity what, @NotNull List<ItemStack> drops, int droppedExp) {
        super(what);
        this.drops = drops;
        this.dropExp = droppedExp;
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }

    public int getDroppedExp() {
        return this.dropExp;
    }

    public void setDroppedExp(int exp) {
        this.dropExp = exp;
    }

    @NotNull
    public List<ItemStack> getDrops() {
        return this.drops;
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

