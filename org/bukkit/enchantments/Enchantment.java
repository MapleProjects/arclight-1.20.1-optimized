/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.enchantments;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Enchantment
implements Keyed {
    public static final Enchantment PROTECTION_ENVIRONMENTAL = new EnchantmentWrapper("protection");
    public static final Enchantment PROTECTION_FIRE = new EnchantmentWrapper("fire_protection");
    public static final Enchantment PROTECTION_FALL = new EnchantmentWrapper("feather_falling");
    public static final Enchantment PROTECTION_EXPLOSIONS = new EnchantmentWrapper("blast_protection");
    public static final Enchantment PROTECTION_PROJECTILE = new EnchantmentWrapper("projectile_protection");
    public static final Enchantment OXYGEN = new EnchantmentWrapper("respiration");
    public static final Enchantment WATER_WORKER = new EnchantmentWrapper("aqua_affinity");
    public static final Enchantment THORNS = new EnchantmentWrapper("thorns");
    public static final Enchantment DEPTH_STRIDER = new EnchantmentWrapper("depth_strider");
    public static final Enchantment FROST_WALKER = new EnchantmentWrapper("frost_walker");
    public static final Enchantment BINDING_CURSE = new EnchantmentWrapper("binding_curse");
    public static final Enchantment DAMAGE_ALL = new EnchantmentWrapper("sharpness");
    public static final Enchantment DAMAGE_UNDEAD = new EnchantmentWrapper("smite");
    public static final Enchantment DAMAGE_ARTHROPODS = new EnchantmentWrapper("bane_of_arthropods");
    public static final Enchantment KNOCKBACK = new EnchantmentWrapper("knockback");
    public static final Enchantment FIRE_ASPECT = new EnchantmentWrapper("fire_aspect");
    public static final Enchantment LOOT_BONUS_MOBS = new EnchantmentWrapper("looting");
    public static final Enchantment SWEEPING_EDGE = new EnchantmentWrapper("sweeping");
    public static final Enchantment DIG_SPEED = new EnchantmentWrapper("efficiency");
    public static final Enchantment SILK_TOUCH = new EnchantmentWrapper("silk_touch");
    public static final Enchantment DURABILITY = new EnchantmentWrapper("unbreaking");
    public static final Enchantment LOOT_BONUS_BLOCKS = new EnchantmentWrapper("fortune");
    public static final Enchantment ARROW_DAMAGE = new EnchantmentWrapper("power");
    public static final Enchantment ARROW_KNOCKBACK = new EnchantmentWrapper("punch");
    public static final Enchantment ARROW_FIRE = new EnchantmentWrapper("flame");
    public static final Enchantment ARROW_INFINITE = new EnchantmentWrapper("infinity");
    public static final Enchantment LUCK = new EnchantmentWrapper("luck_of_the_sea");
    public static final Enchantment LURE = new EnchantmentWrapper("lure");
    public static final Enchantment LOYALTY = new EnchantmentWrapper("loyalty");
    public static final Enchantment IMPALING = new EnchantmentWrapper("impaling");
    public static final Enchantment RIPTIDE = new EnchantmentWrapper("riptide");
    public static final Enchantment CHANNELING = new EnchantmentWrapper("channeling");
    public static final Enchantment MULTISHOT = new EnchantmentWrapper("multishot");
    public static final Enchantment QUICK_CHARGE = new EnchantmentWrapper("quick_charge");
    public static final Enchantment PIERCING = new EnchantmentWrapper("piercing");
    public static final Enchantment MENDING = new EnchantmentWrapper("mending");
    public static final Enchantment VANISHING_CURSE = new EnchantmentWrapper("vanishing_curse");
    public static final Enchantment SOUL_SPEED = new EnchantmentWrapper("soul_speed");
    public static final Enchantment SWIFT_SNEAK = new EnchantmentWrapper("swift_sneak");
    private static final Map<NamespacedKey, Enchantment> byKey = new HashMap<NamespacedKey, Enchantment>();
    private static final Map<String, Enchantment> byName = new HashMap<String, Enchantment>();
    private static boolean acceptingNew = true;
    private final NamespacedKey key;

    public Enchantment(@NotNull NamespacedKey key) {
        this.key = key;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    @Deprecated
    @NotNull
    public abstract String getName();

    public abstract int getMaxLevel();

    public abstract int getStartLevel();

    @NotNull
    public abstract EnchantmentTarget getItemTarget();

    public abstract boolean isTreasure();

    @Deprecated
    public abstract boolean isCursed();

    public abstract boolean conflictsWith(@NotNull Enchantment var1);

    public abstract boolean canEnchantItem(@NotNull ItemStack var1);

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Enchantment)) {
            return false;
        }
        Enchantment other = (Enchantment)obj;
        return this.key.equals(other.key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public String toString() {
        return "Enchantment[" + this.key + ", " + this.getName() + "]";
    }

    public static void registerEnchantment(@NotNull Enchantment enchantment) {
        if (byKey.containsKey(enchantment.key) || byName.containsKey(enchantment.getName())) {
            throw new IllegalArgumentException("Cannot set already-set enchantment");
        }
        if (!Enchantment.isAcceptingRegistrations()) {
            throw new IllegalStateException("No longer accepting new enchantments (can only be done by the server implementation)");
        }
        byKey.put(enchantment.key, enchantment);
        byName.put(enchantment.getName(), enchantment);
    }

    public static boolean isAcceptingRegistrations() {
        return acceptingNew;
    }

    public static void stopAcceptingRegistrations() {
        acceptingNew = false;
    }

    @Contract(value="null -> null")
    @Nullable
    public static Enchantment getByKey(@Nullable NamespacedKey key) {
        return byKey.get(key);
    }

    @Deprecated
    @Contract(value="null -> null")
    @Nullable
    public static Enchantment getByName(@Nullable String name) {
        return byName.get(name);
    }

    @NotNull
    public static Enchantment[] values() {
        return byName.values().toArray(new Enchantment[byName.size()]);
    }
}

