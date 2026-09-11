/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 */
package io.izzel.arclight.common.mod.util.types;

import net.minecraft.world.effect.MobEffect;
import org.bukkit.craftbukkit.v1_20_R1.potion.CraftPotionEffectType;

public class ArclightPotionEffect
extends CraftPotionEffectType {
    private final String name;

    public ArclightPotionEffect(MobEffect handle, String name) {
        super(handle);
        this.name = name;
    }

    @Override
    public String getName() {
        String name = super.getName();
        if (name.startsWith("UNKNOWN_EFFECT_TYPE_")) {
            return this.name;
        }
        return name;
    }
}

