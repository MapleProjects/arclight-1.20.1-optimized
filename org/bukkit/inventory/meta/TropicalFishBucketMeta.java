/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface TropicalFishBucketMeta
extends ItemMeta {
    @NotNull
    public DyeColor getPatternColor();

    public void setPatternColor(@NotNull DyeColor var1);

    @NotNull
    public DyeColor getBodyColor();

    public void setBodyColor(@NotNull DyeColor var1);

    @NotNull
    public TropicalFish.Pattern getPattern();

    public void setPattern(@NotNull TropicalFish.Pattern var1);

    public boolean hasVariant();

    @Override
    @NotNull
    public TropicalFishBucketMeta clone();
}

