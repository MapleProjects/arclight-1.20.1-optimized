/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityBreedEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final LivingEntity mother;
    private final LivingEntity father;
    private final LivingEntity breeder;
    private final ItemStack bredWith;
    private int experience;
    private boolean cancel;

    public EntityBreedEvent(@NotNull LivingEntity child, @NotNull LivingEntity mother, @NotNull LivingEntity father, @Nullable LivingEntity breeder, @Nullable ItemStack bredWith, int experience) {
        super(child);
        Preconditions.checkArgument((child != null ? 1 : 0) != 0, (Object)"Cannot have null child");
        Preconditions.checkArgument((mother != null ? 1 : 0) != 0, (Object)"Cannot have null mother");
        Preconditions.checkArgument((father != null ? 1 : 0) != 0, (Object)"Cannot have null father");
        this.mother = mother;
        this.father = father;
        this.breeder = breeder;
        this.bredWith = bredWith;
        this.setExperience(experience);
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }

    @NotNull
    public LivingEntity getMother() {
        return this.mother;
    }

    @NotNull
    public LivingEntity getFather() {
        return this.father;
    }

    @Nullable
    public LivingEntity getBreeder() {
        return this.breeder;
    }

    @Nullable
    public ItemStack getBredWith() {
        return this.bredWith;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        Preconditions.checkArgument((experience >= 0 ? 1 : 0) != 0, (Object)"Experience cannot be negative");
        this.experience = experience;
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

