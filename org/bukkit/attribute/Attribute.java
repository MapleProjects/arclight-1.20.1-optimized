/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.attribute;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public enum Attribute implements Keyed
{
    GENERIC_MAX_HEALTH("generic.max_health"),
    GENERIC_FOLLOW_RANGE("generic.follow_range"),
    GENERIC_KNOCKBACK_RESISTANCE("generic.knockback_resistance"),
    GENERIC_MOVEMENT_SPEED("generic.movement_speed"),
    GENERIC_FLYING_SPEED("generic.flying_speed"),
    GENERIC_ATTACK_DAMAGE("generic.attack_damage"),
    GENERIC_ATTACK_KNOCKBACK("generic.attack_knockback"),
    GENERIC_ATTACK_SPEED("generic.attack_speed"),
    GENERIC_ARMOR("generic.armor"),
    GENERIC_ARMOR_TOUGHNESS("generic.armor_toughness"),
    GENERIC_LUCK("generic.luck"),
    HORSE_JUMP_STRENGTH("horse.jump_strength"),
    ZOMBIE_SPAWN_REINFORCEMENTS("zombie.spawn_reinforcements");

    private final NamespacedKey key;

    private Attribute(String key) {
        this.key = NamespacedKey.minecraft(key);
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }
}

