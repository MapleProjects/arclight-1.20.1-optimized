/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FireworkEffectMeta
extends ItemMeta {
    public void setEffect(@Nullable FireworkEffect var1);

    public boolean hasEffect();

    @Nullable
    public FireworkEffect getEffect();

    @Override
    @NotNull
    public FireworkEffectMeta clone();
}

