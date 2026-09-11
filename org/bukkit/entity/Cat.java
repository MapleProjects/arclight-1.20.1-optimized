/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public interface Cat
extends Tameable,
Sittable {
    @NotNull
    public Type getCatType();

    public void setCatType(@NotNull Type var1);

    @NotNull
    public DyeColor getCollarColor();

    public void setCollarColor(@NotNull DyeColor var1);

    public static enum Type {
        TABBY,
        BLACK,
        RED,
        SIAMESE,
        BRITISH_SHORTHAIR,
        CALICO,
        PERSIAN,
        RAGDOLL,
        WHITE,
        JELLIE,
        ALL_BLACK;

    }
}

