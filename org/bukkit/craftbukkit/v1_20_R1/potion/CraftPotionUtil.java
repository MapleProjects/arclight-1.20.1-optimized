/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.ImmutableBiMap
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 */
package org.bukkit.craftbukkit.v1_20_R1.potion;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class CraftPotionUtil {
    private static final BiMap<PotionType, String> regular = ImmutableBiMap.builder().put((Object)PotionType.UNCRAFTABLE, (Object)"empty").put((Object)PotionType.WATER, (Object)"water").put((Object)PotionType.MUNDANE, (Object)"mundane").put((Object)PotionType.THICK, (Object)"thick").put((Object)PotionType.AWKWARD, (Object)"awkward").put((Object)PotionType.NIGHT_VISION, (Object)"night_vision").put((Object)PotionType.INVISIBILITY, (Object)"invisibility").put((Object)PotionType.JUMP, (Object)"leaping").put((Object)PotionType.FIRE_RESISTANCE, (Object)"fire_resistance").put((Object)PotionType.SPEED, (Object)"swiftness").put((Object)PotionType.SLOWNESS, (Object)"slowness").put((Object)PotionType.WATER_BREATHING, (Object)"water_breathing").put((Object)PotionType.INSTANT_HEAL, (Object)"healing").put((Object)PotionType.INSTANT_DAMAGE, (Object)"harming").put((Object)PotionType.POISON, (Object)"poison").put((Object)PotionType.REGEN, (Object)"regeneration").put((Object)PotionType.STRENGTH, (Object)"strength").put((Object)PotionType.WEAKNESS, (Object)"weakness").put((Object)PotionType.LUCK, (Object)"luck").put((Object)PotionType.TURTLE_MASTER, (Object)"turtle_master").put((Object)PotionType.SLOW_FALLING, (Object)"slow_falling").build();
    private static final BiMap<PotionType, String> upgradeable = ImmutableBiMap.builder().put((Object)PotionType.JUMP, (Object)"strong_leaping").put((Object)PotionType.SPEED, (Object)"strong_swiftness").put((Object)PotionType.INSTANT_HEAL, (Object)"strong_healing").put((Object)PotionType.INSTANT_DAMAGE, (Object)"strong_harming").put((Object)PotionType.POISON, (Object)"strong_poison").put((Object)PotionType.REGEN, (Object)"strong_regeneration").put((Object)PotionType.STRENGTH, (Object)"strong_strength").put((Object)PotionType.SLOWNESS, (Object)"strong_slowness").put((Object)PotionType.TURTLE_MASTER, (Object)"strong_turtle_master").build();
    private static final BiMap<PotionType, String> extendable = ImmutableBiMap.builder().put((Object)PotionType.NIGHT_VISION, (Object)"long_night_vision").put((Object)PotionType.INVISIBILITY, (Object)"long_invisibility").put((Object)PotionType.JUMP, (Object)"long_leaping").put((Object)PotionType.FIRE_RESISTANCE, (Object)"long_fire_resistance").put((Object)PotionType.SPEED, (Object)"long_swiftness").put((Object)PotionType.SLOWNESS, (Object)"long_slowness").put((Object)PotionType.WATER_BREATHING, (Object)"long_water_breathing").put((Object)PotionType.POISON, (Object)"long_poison").put((Object)PotionType.REGEN, (Object)"long_regeneration").put((Object)PotionType.STRENGTH, (Object)"long_strength").put((Object)PotionType.WEAKNESS, (Object)"long_weakness").put((Object)PotionType.TURTLE_MASTER, (Object)"long_turtle_master").put((Object)PotionType.SLOW_FALLING, (Object)"long_slow_falling").build();

    public static String fromBukkit(PotionData data) {
        String type = data.isUpgraded() ? (String)upgradeable.get((Object)data.getType()) : (data.isExtended() ? (String)extendable.get((Object)data.getType()) : (String)regular.get((Object)data.getType()));
        Preconditions.checkNotNull((Object)type, (Object)("Unknown potion type from data " + data));
        return "minecraft:" + type;
    }

    public static PotionData toBukkit(String type) {
        if (type == null) {
            return new PotionData(PotionType.UNCRAFTABLE, false, false);
        }
        if (type.startsWith("minecraft:")) {
            type = type.substring(10);
        }
        PotionType potionType = null;
        potionType = (PotionType)((Object)extendable.inverse().get((Object)type));
        if (potionType != null) {
            return new PotionData(potionType, true, false);
        }
        potionType = (PotionType)((Object)upgradeable.inverse().get((Object)type));
        if (potionType != null) {
            return new PotionData(potionType, false, true);
        }
        potionType = (PotionType)((Object)regular.inverse().get((Object)type));
        if (potionType != null) {
            return new PotionData(potionType, false, false);
        }
        return new PotionData(PotionType.UNCRAFTABLE, false, false);
    }

    public static MobEffectInstance fromBukkit(PotionEffect effect) {
        MobEffect type = MobEffect.m_19453_((int)effect.getType().getId());
        return new MobEffectInstance(type, effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles());
    }

    public static PotionEffect toBukkit(MobEffectInstance effect) {
        PotionEffectType type = PotionEffectType.getById(MobEffect.m_19459_((MobEffect)effect.m_19544_()));
        int amp = effect.m_19564_();
        int duration = effect.m_19557_();
        boolean ambient = effect.m_19571_();
        boolean particles = effect.m_19572_();
        return new PotionEffect(type, duration, amp, ambient, particles);
    }

    public static boolean equals(MobEffect mobEffect, PotionEffectType type) {
        PotionEffectType typeV = PotionEffectType.getById(MobEffect.m_19459_((MobEffect)mobEffect));
        return typeV.equals(type);
    }
}

