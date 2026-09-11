/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.potion;

import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class PotionEffectTypeWrapper
extends PotionEffectType {
    protected PotionEffectTypeWrapper(int id, @NotNull String name) {
        super(id, NamespacedKey.minecraft(name));
    }

    @Override
    public double getDurationModifier() {
        return this.getType().getDurationModifier();
    }

    @Override
    @NotNull
    public String getName() {
        return this.getType().getName();
    }

    @NotNull
    public PotionEffectType getType() {
        return PotionEffectType.getById(this.getId());
    }

    @Override
    public boolean isInstant() {
        return this.getType().isInstant();
    }

    @Override
    @NotNull
    public Color getColor() {
        return this.getType().getColor();
    }
}

