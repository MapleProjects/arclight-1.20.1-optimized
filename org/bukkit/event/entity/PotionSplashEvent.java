/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

public class PotionSplashEvent
extends ProjectileHitEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Map<LivingEntity, Double> affectedEntities;

    public PotionSplashEvent(@NotNull ThrownPotion potion, @NotNull Map<LivingEntity, Double> affectedEntities) {
        super(potion);
        this.affectedEntities = affectedEntities;
    }

    @Override
    @NotNull
    public ThrownPotion getEntity() {
        return (ThrownPotion)this.entity;
    }

    @NotNull
    public ThrownPotion getPotion() {
        return this.getEntity();
    }

    @NotNull
    public Collection<LivingEntity> getAffectedEntities() {
        return new ArrayList<LivingEntity>(this.affectedEntities.keySet());
    }

    public double getIntensity(@NotNull LivingEntity entity) {
        Double intensity = this.affectedEntities.get(entity);
        return intensity != null ? intensity : 0.0;
    }

    public void setIntensity(@NotNull LivingEntity entity, double intensity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"You must specify a valid entity.");
        if (intensity <= 0.0) {
            this.affectedEntities.remove(entity);
        } else {
            this.affectedEntities.put(entity, Math.min(intensity, 1.0));
        }
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

