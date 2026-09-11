/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.enchantment.Enchantment
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.util.types;

import net.minecraft.world.item.enchantment.Enchantment;
import org.bukkit.craftbukkit.v1_20_R1.enchantments.CraftEnchantment;
import org.jetbrains.annotations.NotNull;

public class ArclightEnchantment
extends CraftEnchantment {
    private final String name;

    public ArclightEnchantment(Enchantment target, String name) {
        super(target);
        this.name = name;
    }

    @Override
    @NotNull
    public String getName() {
        String name = super.getName();
        if (name.startsWith("UNKNOWN_ENCHANT_")) {
            return this.name;
        }
        return name;
    }
}

