/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface FireworkMeta
extends ItemMeta {
    public void addEffect(@NotNull FireworkEffect var1) throws IllegalArgumentException;

    public void addEffects(FireworkEffect ... var1) throws IllegalArgumentException;

    public void addEffects(@NotNull Iterable<FireworkEffect> var1) throws IllegalArgumentException;

    @NotNull
    public List<FireworkEffect> getEffects();

    public int getEffectsSize();

    public void removeEffect(int var1) throws IndexOutOfBoundsException;

    public void clearEffects();

    public boolean hasEffects();

    public int getPower();

    public void setPower(int var1) throws IllegalArgumentException;

    @Override
    @NotNull
    public FireworkMeta clone();
}

