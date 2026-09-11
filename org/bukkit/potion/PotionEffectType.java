/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.potion;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.bukkit.Color;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectTypeWrapper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PotionEffectType
implements Keyed {
    public static final PotionEffectType SPEED = new PotionEffectTypeWrapper(1, "speed");
    public static final PotionEffectType SLOW = new PotionEffectTypeWrapper(2, "slowness");
    public static final PotionEffectType FAST_DIGGING = new PotionEffectTypeWrapper(3, "haste");
    public static final PotionEffectType SLOW_DIGGING = new PotionEffectTypeWrapper(4, "mining_fatigue");
    public static final PotionEffectType INCREASE_DAMAGE = new PotionEffectTypeWrapper(5, "strength");
    public static final PotionEffectType HEAL = new PotionEffectTypeWrapper(6, "instant_health");
    public static final PotionEffectType HARM = new PotionEffectTypeWrapper(7, "instant_damage");
    public static final PotionEffectType JUMP = new PotionEffectTypeWrapper(8, "jump_boost");
    public static final PotionEffectType CONFUSION = new PotionEffectTypeWrapper(9, "nausea");
    public static final PotionEffectType REGENERATION = new PotionEffectTypeWrapper(10, "regeneration");
    public static final PotionEffectType DAMAGE_RESISTANCE = new PotionEffectTypeWrapper(11, "resistance");
    public static final PotionEffectType FIRE_RESISTANCE = new PotionEffectTypeWrapper(12, "fire_resistance");
    public static final PotionEffectType WATER_BREATHING = new PotionEffectTypeWrapper(13, "water_breathing");
    public static final PotionEffectType INVISIBILITY = new PotionEffectTypeWrapper(14, "invisibility");
    public static final PotionEffectType BLINDNESS = new PotionEffectTypeWrapper(15, "blindness");
    public static final PotionEffectType NIGHT_VISION = new PotionEffectTypeWrapper(16, "night_vision");
    public static final PotionEffectType HUNGER = new PotionEffectTypeWrapper(17, "hunger");
    public static final PotionEffectType WEAKNESS = new PotionEffectTypeWrapper(18, "weakness");
    public static final PotionEffectType POISON = new PotionEffectTypeWrapper(19, "poison");
    public static final PotionEffectType WITHER = new PotionEffectTypeWrapper(20, "wither");
    public static final PotionEffectType HEALTH_BOOST = new PotionEffectTypeWrapper(21, "health_boost");
    public static final PotionEffectType ABSORPTION = new PotionEffectTypeWrapper(22, "absorption");
    public static final PotionEffectType SATURATION = new PotionEffectTypeWrapper(23, "saturation");
    public static final PotionEffectType GLOWING = new PotionEffectTypeWrapper(24, "glowing");
    public static final PotionEffectType LEVITATION = new PotionEffectTypeWrapper(25, "levitation");
    public static final PotionEffectType LUCK = new PotionEffectTypeWrapper(26, "luck");
    public static final PotionEffectType UNLUCK = new PotionEffectTypeWrapper(27, "unluck");
    public static final PotionEffectType SLOW_FALLING = new PotionEffectTypeWrapper(28, "slow_falling");
    public static final PotionEffectType CONDUIT_POWER = new PotionEffectTypeWrapper(29, "conduit_power");
    public static final PotionEffectType DOLPHINS_GRACE = new PotionEffectTypeWrapper(30, "dolphins_grace");
    public static final PotionEffectType BAD_OMEN = new PotionEffectTypeWrapper(31, "bad_omen");
    public static final PotionEffectType HERO_OF_THE_VILLAGE = new PotionEffectTypeWrapper(32, "hero_of_the_village");
    public static final PotionEffectType DARKNESS = new PotionEffectTypeWrapper(33, "darkness");
    private final int id;
    private final NamespacedKey key;
    private static final PotionEffectType[] byId = new PotionEffectType[34];
    private static final Map<String, PotionEffectType> byName = new HashMap<String, PotionEffectType>();
    private static final Map<NamespacedKey, PotionEffectType> byKey = new HashMap<NamespacedKey, PotionEffectType>();
    private static boolean acceptingNew = true;

    protected PotionEffectType(int id, @NotNull NamespacedKey key) {
        this.id = id;
        this.key = key;
    }

    @NotNull
    public PotionEffect createEffect(int duration, int amplifier) {
        return new PotionEffect(this, this.isInstant() ? 1 : (int)((double)duration * this.getDurationModifier()), amplifier);
    }

    @Deprecated
    public abstract double getDurationModifier();

    @Deprecated
    public int getId() {
        return this.id;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @NotNull
    public abstract String getName();

    public abstract boolean isInstant();

    @NotNull
    public abstract Color getColor();

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof PotionEffectType)) {
            return false;
        }
        PotionEffectType other = (PotionEffectType)obj;
        return this.id == other.id;
    }

    public int hashCode() {
        return this.id;
    }

    public String toString() {
        return "PotionEffectType[" + this.id + ", " + this.getName() + "]";
    }

    @Contract(value="null -> null")
    @Nullable
    public static PotionEffectType getByKey(@Nullable NamespacedKey key) {
        return byKey.get(key);
    }

    @Deprecated
    @Nullable
    public static PotionEffectType getById(int id) {
        if (id >= byId.length || id < 0) {
            return null;
        }
        return byId[id];
    }

    @Nullable
    public static PotionEffectType getByName(@NotNull String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"name cannot be null");
        return byName.get(name.toLowerCase(Locale.ENGLISH));
    }

    public static void registerPotionEffectType(@NotNull PotionEffectType type) {
        if (byId[type.id] != null || byName.containsKey(type.getName().toLowerCase(Locale.ENGLISH)) || byKey.containsKey(type.key)) {
            throw new IllegalArgumentException("Cannot set already-set type");
        }
        if (!acceptingNew) {
            throw new IllegalStateException("No longer accepting new potion effect types (can only be done by the server implementation)");
        }
        PotionEffectType.byId[type.id] = type;
        byName.put(type.getName().toLowerCase(Locale.ENGLISH), type);
        byKey.put(type.key, type);
    }

    public static void stopAcceptingRegistrations() {
        acceptingNew = false;
    }

    @NotNull
    public static PotionEffectType[] values() {
        return Arrays.copyOfRange(byId, 1, byId.length);
    }
}

