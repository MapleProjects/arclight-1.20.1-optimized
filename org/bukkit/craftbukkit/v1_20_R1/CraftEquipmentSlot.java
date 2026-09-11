/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.EquipmentSlot
 */
package org.bukkit.craftbukkit.v1_20_R1;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;

public class CraftEquipmentSlot {
    private static final EquipmentSlot[] slots = new EquipmentSlot[org.bukkit.inventory.EquipmentSlot.values().length];
    private static final org.bukkit.inventory.EquipmentSlot[] enums = new org.bukkit.inventory.EquipmentSlot[EquipmentSlot.values().length];

    static {
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.HAND, EquipmentSlot.MAINHAND);
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.OFF_HAND, EquipmentSlot.OFFHAND);
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.FEET, EquipmentSlot.FEET);
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.LEGS, EquipmentSlot.LEGS);
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.CHEST, EquipmentSlot.CHEST);
        CraftEquipmentSlot.set(org.bukkit.inventory.EquipmentSlot.HEAD, EquipmentSlot.HEAD);
    }

    private static void set(org.bukkit.inventory.EquipmentSlot type, EquipmentSlot value) {
        CraftEquipmentSlot.slots[type.ordinal()] = value;
        CraftEquipmentSlot.enums[value.ordinal()] = type;
    }

    public static org.bukkit.inventory.EquipmentSlot getSlot(EquipmentSlot nms) {
        return enums[nms.ordinal()];
    }

    public static EquipmentSlot getNMS(org.bukkit.inventory.EquipmentSlot slot) {
        return slots[slot.ordinal()];
    }

    public static org.bukkit.inventory.EquipmentSlot getHand(InteractionHand enumhand) {
        return enumhand == InteractionHand.MAIN_HAND ? org.bukkit.inventory.EquipmentSlot.HAND : org.bukkit.inventory.EquipmentSlot.OFF_HAND;
    }

    public static InteractionHand getHand(org.bukkit.inventory.EquipmentSlot hand) {
        if (hand == org.bukkit.inventory.EquipmentSlot.HAND) {
            return InteractionHand.MAIN_HAND;
        }
        if (hand == org.bukkit.inventory.EquipmentSlot.OFF_HAND) {
            return InteractionHand.OFF_HAND;
        }
        throw new IllegalArgumentException("EquipmentSlot." + (Object)((Object)hand) + " is not a hand");
    }
}

