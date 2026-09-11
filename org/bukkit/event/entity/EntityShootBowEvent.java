/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityShootBowEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack bow;
    private final ItemStack consumable;
    private Entity projectile;
    private final EquipmentSlot hand;
    private final float force;
    private boolean consumeItem;
    private boolean cancelled;

    public EntityShootBowEvent(@NotNull LivingEntity shooter, @Nullable ItemStack bow, @Nullable ItemStack consumable, @NotNull Entity projectile, @NotNull EquipmentSlot hand, float force, boolean consumeItem) {
        super(shooter);
        this.bow = bow;
        this.consumable = consumable;
        this.projectile = projectile;
        this.hand = hand;
        this.force = force;
        this.consumeItem = consumeItem;
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }

    @Nullable
    public ItemStack getBow() {
        return this.bow;
    }

    @Nullable
    public ItemStack getConsumable() {
        return this.consumable;
    }

    @NotNull
    public Entity getProjectile() {
        return this.projectile;
    }

    public void setProjectile(@NotNull Entity projectile) {
        this.projectile = projectile;
    }

    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    public float getForce() {
        return this.force;
    }

    public void setConsumeItem(boolean consumeItem) {
        this.consumeItem = consumeItem;
    }

    public boolean shouldConsumeItem() {
        return this.consumeItem;
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

