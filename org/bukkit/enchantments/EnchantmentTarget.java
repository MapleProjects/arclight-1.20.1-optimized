/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.enchantments;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum EnchantmentTarget {
    ALL{

        @Override
        public boolean includes(@NotNull Material item) {
            EnchantmentTarget[] enchantmentTargetArray = EnchantmentTarget.values();
            int n = enchantmentTargetArray.length;
            int n2 = 0;
            while (n2 < n) {
                EnchantmentTarget target = enchantmentTargetArray[n2];
                if (target != this && target.includes(item)) {
                    return true;
                }
                ++n2;
            }
            return false;
        }
    }
    ,
    ARMOR{

        @Override
        public boolean includes(@NotNull Material item) {
            return ARMOR_FEET.includes(item) || ARMOR_LEGS.includes(item) || ARMOR_HEAD.includes(item) || ARMOR_TORSO.includes(item);
        }
    }
    ,
    ARMOR_FEET{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.LEATHER_BOOTS) || item.equals(Material.CHAINMAIL_BOOTS) || item.equals(Material.IRON_BOOTS) || item.equals(Material.DIAMOND_BOOTS) || item.equals(Material.GOLDEN_BOOTS) || item.equals(Material.NETHERITE_BOOTS);
        }
    }
    ,
    ARMOR_LEGS{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.LEATHER_LEGGINGS) || item.equals(Material.CHAINMAIL_LEGGINGS) || item.equals(Material.IRON_LEGGINGS) || item.equals(Material.DIAMOND_LEGGINGS) || item.equals(Material.GOLDEN_LEGGINGS) || item.equals(Material.NETHERITE_LEGGINGS);
        }
    }
    ,
    ARMOR_TORSO{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.LEATHER_CHESTPLATE) || item.equals(Material.CHAINMAIL_CHESTPLATE) || item.equals(Material.IRON_CHESTPLATE) || item.equals(Material.DIAMOND_CHESTPLATE) || item.equals(Material.GOLDEN_CHESTPLATE) || item.equals(Material.NETHERITE_CHESTPLATE);
        }
    }
    ,
    ARMOR_HEAD{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.LEATHER_HELMET) || item.equals(Material.CHAINMAIL_HELMET) || item.equals(Material.DIAMOND_HELMET) || item.equals(Material.IRON_HELMET) || item.equals(Material.GOLDEN_HELMET) || item.equals(Material.TURTLE_HELMET) || item.equals(Material.NETHERITE_HELMET);
        }
    }
    ,
    WEAPON{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.WOODEN_SWORD) || item.equals(Material.STONE_SWORD) || item.equals(Material.IRON_SWORD) || item.equals(Material.DIAMOND_SWORD) || item.equals(Material.GOLDEN_SWORD) || item.equals(Material.NETHERITE_SWORD);
        }
    }
    ,
    TOOL{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.WOODEN_SHOVEL) || item.equals(Material.STONE_SHOVEL) || item.equals(Material.IRON_SHOVEL) || item.equals(Material.DIAMOND_SHOVEL) || item.equals(Material.GOLDEN_SHOVEL) || item.equals(Material.NETHERITE_SHOVEL) || item.equals(Material.WOODEN_PICKAXE) || item.equals(Material.STONE_PICKAXE) || item.equals(Material.IRON_PICKAXE) || item.equals(Material.DIAMOND_PICKAXE) || item.equals(Material.GOLDEN_PICKAXE) || item.equals(Material.NETHERITE_PICKAXE) || item.equals(Material.WOODEN_AXE) || item.equals(Material.STONE_AXE) || item.equals(Material.IRON_AXE) || item.equals(Material.DIAMOND_AXE) || item.equals(Material.GOLDEN_AXE) || item.equals(Material.NETHERITE_AXE) || item.equals(Material.WOODEN_HOE) || item.equals(Material.STONE_HOE) || item.equals(Material.IRON_HOE) || item.equals(Material.DIAMOND_HOE) || item.equals(Material.GOLDEN_HOE) || item.equals(Material.NETHERITE_HOE);
        }
    }
    ,
    BOW{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.BOW);
        }
    }
    ,
    FISHING_ROD{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.FISHING_ROD);
        }
    }
    ,
    BREAKABLE{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.getMaxDurability() > 0 && item.getMaxStackSize() == 1;
        }
    }
    ,
    WEARABLE{

        @Override
        public boolean includes(@NotNull Material item) {
            return ARMOR.includes(item) || item.equals(Material.ELYTRA) || item.equals(Material.CARVED_PUMPKIN) || item.equals(Material.SKELETON_SKULL) || item.equals(Material.WITHER_SKELETON_SKULL) || item.equals(Material.ZOMBIE_HEAD) || item.equals(Material.PIGLIN_HEAD) || item.equals(Material.PLAYER_HEAD) || item.equals(Material.CREEPER_HEAD) || item.equals(Material.DRAGON_HEAD) || item.equals(Material.SHIELD);
        }
    }
    ,
    TRIDENT{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.TRIDENT);
        }
    }
    ,
    CROSSBOW{

        @Override
        public boolean includes(@NotNull Material item) {
            return item.equals(Material.CROSSBOW);
        }
    }
    ,
    VANISHABLE{

        @Override
        public boolean includes(@NotNull Material item) {
            return BREAKABLE.includes(item) || WEARABLE.includes(item) && !item.equals(Material.ELYTRA) || item.equals(Material.COMPASS);
        }
    };


    private EnchantmentTarget() {
    }

    public abstract boolean includes(@NotNull Material var1);

    public boolean includes(@NotNull ItemStack item) {
        return this.includes(item.getType());
    }

    /* synthetic */ EnchantmentTarget(String string, int n, EnchantmentTarget enchantmentTarget) {
        this();
    }
}

