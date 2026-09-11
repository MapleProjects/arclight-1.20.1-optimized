/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityPotionEffectEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private final PotionEffect oldEffect;
    private final PotionEffect newEffect;
    private final Cause cause;
    private final Action action;
    private boolean override;

    @Contract(value="_, null, null, _, _, _ -> fail")
    public EntityPotionEffectEvent(@NotNull LivingEntity livingEntity, @Nullable PotionEffect oldEffect, @Nullable PotionEffect newEffect, @NotNull Cause cause, @NotNull Action action, boolean override) {
        super(livingEntity);
        this.oldEffect = oldEffect;
        this.newEffect = newEffect;
        this.cause = cause;
        this.action = action;
        this.override = override;
    }

    @Nullable
    public PotionEffect getOldEffect() {
        return this.oldEffect;
    }

    @Nullable
    public PotionEffect getNewEffect() {
        return this.newEffect;
    }

    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    @NotNull
    public Action getAction() {
        return this.action;
    }

    @NotNull
    public PotionEffectType getModifiedType() {
        return this.oldEffect == null ? (this.newEffect == null ? null : this.newEffect.getType()) : this.oldEffect.getType();
    }

    public boolean isOverride() {
        return this.override;
    }

    public void setOverride(boolean override) {
        this.override = override;
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

    public static enum Action {
        ADDED,
        CHANGED,
        CLEARED,
        REMOVED;

    }

    public static enum Cause {
        AREA_EFFECT_CLOUD,
        ARROW,
        ATTACK,
        AXOLOTL,
        BEACON,
        COMMAND,
        CONDUIT,
        CONVERSION,
        DEATH,
        DOLPHIN,
        EXPIRATION,
        FOOD,
        ILLUSION,
        MILK,
        PATROL_CAPTAIN,
        PLUGIN,
        POTION_DRINK,
        POTION_SPLASH,
        SPIDER_SPAWN,
        TOTEM,
        TURTLE_HELMET,
        UNKNOWN,
        VILLAGER_TRADE,
        WARDEN,
        WITHER_ROSE;

    }
}

