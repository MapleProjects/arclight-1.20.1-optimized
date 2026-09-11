/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.potion;

import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;

public enum PotionType {
    UNCRAFTABLE(null, false, false),
    WATER(null, false, false),
    MUNDANE(null, false, false),
    THICK(null, false, false),
    AWKWARD(null, false, false),
    NIGHT_VISION(PotionEffectType.NIGHT_VISION, false, true),
    INVISIBILITY(PotionEffectType.INVISIBILITY, false, true),
    JUMP(PotionEffectType.JUMP, true, true),
    FIRE_RESISTANCE(PotionEffectType.FIRE_RESISTANCE, false, true),
    SPEED(PotionEffectType.SPEED, true, true),
    SLOWNESS(PotionEffectType.SLOW, true, true),
    WATER_BREATHING(PotionEffectType.WATER_BREATHING, false, true),
    INSTANT_HEAL(PotionEffectType.HEAL, true, false),
    INSTANT_DAMAGE(PotionEffectType.HARM, true, false),
    POISON(PotionEffectType.POISON, true, true),
    REGEN(PotionEffectType.REGENERATION, true, true),
    STRENGTH(PotionEffectType.INCREASE_DAMAGE, true, true),
    WEAKNESS(PotionEffectType.WEAKNESS, false, true),
    LUCK(PotionEffectType.LUCK, false, false),
    TURTLE_MASTER(PotionEffectType.SLOW, true, true),
    SLOW_FALLING(PotionEffectType.SLOW_FALLING, false, true);

    private final PotionEffectType effect;
    private final boolean upgradeable;
    private final boolean extendable;

    private PotionType(PotionEffectType effect, boolean upgradeable, boolean extendable) {
        this.effect = effect;
        this.upgradeable = upgradeable;
        this.extendable = extendable;
    }

    @Nullable
    public PotionEffectType getEffectType() {
        return this.effect;
    }

    public boolean isInstant() {
        return this.effect != null && this.effect.isInstant();
    }

    public boolean isUpgradeable() {
        return this.upgradeable;
    }

    public boolean isExtendable() {
        return this.extendable;
    }

    public int getMaxLevel() {
        return this.upgradeable ? 2 : 1;
    }

    @Deprecated
    @Nullable
    public static PotionType getByEffect(@Nullable PotionEffectType effectType) {
        if (effectType == null) {
            return WATER;
        }
        PotionType[] potionTypeArray = PotionType.values();
        int n = potionTypeArray.length;
        int n2 = 0;
        while (n2 < n) {
            PotionType type = potionTypeArray[n2];
            if (effectType.equals(type.effect)) {
                return type;
            }
            ++n2;
        }
        return null;
    }
}

