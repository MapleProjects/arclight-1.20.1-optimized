/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.item.enchantment.BindingCurseEnchantment
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.VanishingCurseEnchantment
 */
package org.bukkit.craftbukkit.v1_20_R1.enchantments;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.BindingCurseEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.VanishingCurseEnchantment;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class CraftEnchantment
extends org.bukkit.enchantments.Enchantment {
    private final Enchantment target;

    public CraftEnchantment(Enchantment target) {
        super(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256876_.m_7981_((Object)target)));
        this.target = target;
    }

    @Override
    public int getMaxLevel() {
        return this.target.m_6586_();
    }

    @Override
    public int getStartLevel() {
        return this.target.m_44702_();
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        switch (this.target.f_44672_) {
            case ARMOR: {
                return EnchantmentTarget.ARMOR;
            }
            case ARMOR_FEET: {
                return EnchantmentTarget.ARMOR_FEET;
            }
            case ARMOR_HEAD: {
                return EnchantmentTarget.ARMOR_HEAD;
            }
            case ARMOR_LEGS: {
                return EnchantmentTarget.ARMOR_LEGS;
            }
            case ARMOR_CHEST: {
                return EnchantmentTarget.ARMOR_TORSO;
            }
            case DIGGER: {
                return EnchantmentTarget.TOOL;
            }
            case WEAPON: {
                return EnchantmentTarget.WEAPON;
            }
            case BOW: {
                return EnchantmentTarget.BOW;
            }
            case FISHING_ROD: {
                return EnchantmentTarget.FISHING_ROD;
            }
            case BREAKABLE: {
                return EnchantmentTarget.BREAKABLE;
            }
            case WEARABLE: {
                return EnchantmentTarget.WEARABLE;
            }
            case TRIDENT: {
                return EnchantmentTarget.TRIDENT;
            }
            case CROSSBOW: {
                return EnchantmentTarget.CROSSBOW;
            }
            case VANISHABLE: {
                return EnchantmentTarget.VANISHABLE;
            }
        }
        return null;
    }

    @Override
    public boolean isTreasure() {
        return this.target.m_6591_();
    }

    @Override
    public boolean isCursed() {
        return this.target instanceof BindingCurseEnchantment || this.target instanceof VanishingCurseEnchantment;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return this.target.m_6081_(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public String getName() {
        switch (BuiltInRegistries.f_256876_.m_7447_((Object)this.target)) {
            case 0: {
                return "PROTECTION_ENVIRONMENTAL";
            }
            case 1: {
                return "PROTECTION_FIRE";
            }
            case 2: {
                return "PROTECTION_FALL";
            }
            case 3: {
                return "PROTECTION_EXPLOSIONS";
            }
            case 4: {
                return "PROTECTION_PROJECTILE";
            }
            case 5: {
                return "OXYGEN";
            }
            case 6: {
                return "WATER_WORKER";
            }
            case 7: {
                return "THORNS";
            }
            case 8: {
                return "DEPTH_STRIDER";
            }
            case 9: {
                return "FROST_WALKER";
            }
            case 10: {
                return "BINDING_CURSE";
            }
            case 11: {
                return "SOUL_SPEED";
            }
            case 12: {
                return "SWIFT_SNEAK";
            }
            case 13: {
                return "DAMAGE_ALL";
            }
            case 14: {
                return "DAMAGE_UNDEAD";
            }
            case 15: {
                return "DAMAGE_ARTHROPODS";
            }
            case 16: {
                return "KNOCKBACK";
            }
            case 17: {
                return "FIRE_ASPECT";
            }
            case 18: {
                return "LOOT_BONUS_MOBS";
            }
            case 19: {
                return "SWEEPING_EDGE";
            }
            case 20: {
                return "DIG_SPEED";
            }
            case 21: {
                return "SILK_TOUCH";
            }
            case 22: {
                return "DURABILITY";
            }
            case 23: {
                return "LOOT_BONUS_BLOCKS";
            }
            case 24: {
                return "ARROW_DAMAGE";
            }
            case 25: {
                return "ARROW_KNOCKBACK";
            }
            case 26: {
                return "ARROW_FIRE";
            }
            case 27: {
                return "ARROW_INFINITE";
            }
            case 28: {
                return "LUCK";
            }
            case 29: {
                return "LURE";
            }
            case 30: {
                return "LOYALTY";
            }
            case 31: {
                return "IMPALING";
            }
            case 32: {
                return "RIPTIDE";
            }
            case 33: {
                return "CHANNELING";
            }
            case 34: {
                return "MULTISHOT";
            }
            case 35: {
                return "QUICK_CHARGE";
            }
            case 36: {
                return "PIERCING";
            }
            case 37: {
                return "MENDING";
            }
            case 38: {
                return "VANISHING_CURSE";
            }
        }
        return "UNKNOWN_ENCHANT_" + BuiltInRegistries.f_256876_.m_7447_((Object)this.target);
    }

    public static Enchantment getRaw(org.bukkit.enchantments.Enchantment enchantment) {
        if (enchantment instanceof EnchantmentWrapper) {
            enchantment = ((EnchantmentWrapper)enchantment).getEnchantment();
        }
        if (enchantment instanceof CraftEnchantment) {
            return ((CraftEnchantment)enchantment).target;
        }
        return null;
    }

    @Override
    public boolean conflictsWith(org.bukkit.enchantments.Enchantment other) {
        if (other instanceof EnchantmentWrapper) {
            other = ((EnchantmentWrapper)other).getEnchantment();
        }
        if (!(other instanceof CraftEnchantment)) {
            return false;
        }
        CraftEnchantment ench = (CraftEnchantment)other;
        return !this.target.m_44695_(ench.target);
    }

    public Enchantment getHandle() {
        return this.target;
    }
}

