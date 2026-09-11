/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.potion;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.bukkit.Color;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SerializableAs(value="PotionEffect")
public class PotionEffect
implements ConfigurationSerializable {
    public static final int INFINITE_DURATION = -1;
    private static final String AMPLIFIER = "amplifier";
    private static final String DURATION = "duration";
    private static final String TYPE = "effect";
    private static final String AMBIENT = "ambient";
    private static final String PARTICLES = "has-particles";
    private static final String ICON = "has-icon";
    private final int amplifier;
    private final int duration;
    private final PotionEffectType type;
    private final boolean ambient;
    private final boolean particles;
    private final boolean icon;

    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"effect type cannot be null");
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
    }

    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {
        this(type, duration, amplifier, ambient, particles, particles);
    }

    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient) {
        this(type, duration, amplifier, ambient, true);
    }

    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
        this(type, duration, amplifier, true);
    }

    public PotionEffect(@NotNull Map<String, Object> map) {
        this(PotionEffect.getEffectType(map), PotionEffect.getInt(map, DURATION), PotionEffect.getInt(map, AMPLIFIER), PotionEffect.getBool(map, AMBIENT, false), PotionEffect.getBool(map, PARTICLES, true), PotionEffect.getBool(map, ICON, PotionEffect.getBool(map, PARTICLES, true)));
    }

    @NotNull
    private static PotionEffectType getEffectType(@NotNull Map<?, ?> map) {
        int type = PotionEffect.getInt(map, TYPE);
        PotionEffectType effect = PotionEffectType.getById(type);
        if (effect != null) {
            return effect;
        }
        throw new NoSuchElementException(map + " does not contain " + TYPE);
    }

    private static int getInt(@NotNull Map<?, ?> map, @NotNull Object key) {
        Object num = map.get(key);
        if (num instanceof Integer) {
            return (Integer)num;
        }
        throw new NoSuchElementException(map + " does not contain " + key);
    }

    private static boolean getBool(@NotNull Map<?, ?> map, @NotNull Object key, boolean def) {
        Object bool = map.get(key);
        if (bool instanceof Boolean) {
            return (Boolean)bool;
        }
        return def;
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return ImmutableMap.builder().put((Object)TYPE, (Object)this.type.getId()).put((Object)DURATION, (Object)this.duration).put((Object)AMPLIFIER, (Object)this.amplifier).put((Object)AMBIENT, (Object)this.ambient).put((Object)PARTICLES, (Object)this.particles).put((Object)ICON, (Object)this.icon).build();
    }

    public boolean apply(@NotNull LivingEntity entity) {
        return entity.addPotionEffect(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PotionEffect)) {
            return false;
        }
        PotionEffect that = (PotionEffect)obj;
        return this.type.equals(that.type) && this.ambient == that.ambient && this.amplifier == that.amplifier && this.duration == that.duration && this.particles == that.particles && this.icon == that.icon;
    }

    public int getAmplifier() {
        return this.amplifier;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean isInfinite() {
        return this.duration == -1;
    }

    public boolean isShorterThan(@NotNull PotionEffect other) {
        return !this.isInfinite() && (this.duration < other.duration || other.isInfinite());
    }

    @NotNull
    public PotionEffectType getType() {
        return this.type;
    }

    public boolean isAmbient() {
        return this.ambient;
    }

    public boolean hasParticles() {
        return this.particles;
    }

    @Deprecated
    @Nullable
    @Contract(value="-> null")
    public Color getColor() {
        return null;
    }

    public boolean hasIcon() {
        return this.icon;
    }

    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.type.hashCode();
        hash = hash * 31 + this.amplifier;
        hash = hash * 31 + this.duration;
        hash ^= 0x22222222 >> (this.ambient ? 1 : -1);
        hash ^= 0x22222222 >> (this.particles ? 1 : -1);
        return hash ^= 0x22222222 >> (this.icon ? 1 : -1);
    }

    public String toString() {
        return String.valueOf(this.type.getName()) + (this.ambient ? ":(" : ":") + this.duration + "t-x" + this.amplifier + (this.ambient ? ")" : "");
    }
}

