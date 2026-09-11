/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Functions
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private static final DamageModifier[] MODIFIERS = DamageModifier.values();
    private static final Function<? super Double, Double> ZERO = Functions.constant((Object)-0.0);
    private final Map<DamageModifier, Double> modifiers;
    private final Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions;
    private final Map<DamageModifier, Double> originals;
    private boolean cancelled;
    private final DamageCause cause;

    public EntityDamageEvent(@NotNull Entity damagee, @NotNull DamageCause cause, double damage) {
        this(damagee, cause, new EnumMap<DamageModifier, Double>((Map<DamageModifier, Double>)ImmutableMap.of((Object)((Object)DamageModifier.BASE), (Object)damage)), new EnumMap(ImmutableMap.of((Object)((Object)DamageModifier.BASE), ZERO)));
    }

    public EntityDamageEvent(@NotNull Entity damagee, @NotNull DamageCause cause, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
        super(damagee);
        Preconditions.checkArgument((boolean)modifiers.containsKey((Object)DamageModifier.BASE), (Object)"BASE DamageModifier missing");
        Preconditions.checkArgument((!modifiers.containsKey(null) ? 1 : 0) != 0, (Object)"Cannot have null DamageModifier");
        Preconditions.checkArgument((boolean)modifiers.values().stream().allMatch(Objects::nonNull), (Object)"Cannot have null modifier values");
        Preconditions.checkArgument((boolean)modifiers.keySet().equals(modifierFunctions.keySet()), (Object)"Must have a modifier function for each DamageModifier");
        Preconditions.checkArgument((boolean)modifierFunctions.values().stream().allMatch(Objects::nonNull), (Object)"Cannot have null modifier function");
        this.originals = new EnumMap<DamageModifier, Double>(modifiers);
        this.cause = cause;
        this.modifiers = modifiers;
        this.modifierFunctions = modifierFunctions;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public double getOriginalDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
        Double damage = this.originals.get((Object)type);
        if (damage != null) {
            return damage;
        }
        if (type == null) {
            throw new IllegalArgumentException("Cannot have null DamageModifier");
        }
        return 0.0;
    }

    public void setDamage(@NotNull DamageModifier type, double damage) throws IllegalArgumentException, UnsupportedOperationException {
        if (!this.modifiers.containsKey((Object)type)) {
            throw type == null ? new IllegalArgumentException("Cannot have null DamageModifier") : new UnsupportedOperationException((Object)((Object)type) + " is not applicable to " + this.getEntity());
        }
        this.modifiers.put(type, damage);
    }

    public double getDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Cannot have null DamageModifier");
        Double damage = this.modifiers.get((Object)type);
        return damage == null ? 0.0 : damage;
    }

    public boolean isApplicable(@NotNull DamageModifier type) throws IllegalArgumentException {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Cannot have null DamageModifier");
        return this.modifiers.containsKey((Object)type);
    }

    public double getDamage() {
        return this.getDamage(DamageModifier.BASE);
    }

    public final double getFinalDamage() {
        double damage = 0.0;
        DamageModifier[] damageModifierArray = MODIFIERS;
        int n = MODIFIERS.length;
        int n2 = 0;
        while (n2 < n) {
            DamageModifier modifier = damageModifierArray[n2];
            damage += this.getDamage(modifier);
            ++n2;
        }
        return damage;
    }

    public void setDamage(double damage) {
        double remaining = damage;
        double oldRemaining = this.getDamage(DamageModifier.BASE);
        DamageModifier[] damageModifierArray = MODIFIERS;
        int n = MODIFIERS.length;
        int n2 = 0;
        while (n2 < n) {
            DamageModifier modifier = damageModifierArray[n2];
            if (this.isApplicable(modifier)) {
                Function<? super Double, Double> modifierFunction = this.modifierFunctions.get((Object)modifier);
                double newVanilla = (Double)modifierFunction.apply((Object)remaining);
                double oldVanilla = (Double)modifierFunction.apply((Object)oldRemaining);
                double difference = oldVanilla - newVanilla;
                double old = this.getDamage(modifier);
                if (old > 0.0) {
                    this.setDamage(modifier, Math.max(0.0, old - difference));
                } else {
                    this.setDamage(modifier, Math.min(0.0, old - difference));
                }
                remaining += newVanilla;
                oldRemaining += oldVanilla;
            }
            ++n2;
        }
        this.setDamage(DamageModifier.BASE, damage);
    }

    @NotNull
    public DamageCause getCause() {
        return this.cause;
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

    public static enum DamageCause {
        KILL,
        WORLD_BORDER,
        CONTACT,
        ENTITY_ATTACK,
        ENTITY_SWEEP_ATTACK,
        PROJECTILE,
        SUFFOCATION,
        FALL,
        FIRE,
        FIRE_TICK,
        MELTING,
        LAVA,
        DROWNING,
        BLOCK_EXPLOSION,
        ENTITY_EXPLOSION,
        VOID,
        LIGHTNING,
        SUICIDE,
        STARVATION,
        POISON,
        MAGIC,
        WITHER,
        FALLING_BLOCK,
        THORNS,
        DRAGON_BREATH,
        CUSTOM,
        FLY_INTO_WALL,
        HOT_FLOOR,
        CRAMMING,
        DRYOUT,
        FREEZE,
        SONIC_BOOM;

    }

    @Deprecated
    public static enum DamageModifier {
        BASE,
        HARD_HAT,
        BLOCKING,
        ARMOR,
        RESISTANCE,
        MAGIC,
        ABSORPTION;

    }
}

