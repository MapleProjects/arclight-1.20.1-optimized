/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.inventory.HorseInventory;
import org.jetbrains.annotations.NotNull;

public interface Horse
extends AbstractHorse {
    @NotNull
    public Color getColor();

    public void setColor(@NotNull Color var1);

    @NotNull
    public Style getStyle();

    public void setStyle(@NotNull Style var1);

    @Deprecated
    public boolean isCarryingChest();

    @Deprecated
    public void setCarryingChest(boolean var1);

    @Override
    @NotNull
    public HorseInventory getInventory();

    public static enum Color {
        WHITE,
        CREAMY,
        CHESTNUT,
        BROWN,
        BLACK,
        GRAY,
        DARK_BROWN;

    }

    public static enum Style {
        NONE,
        WHITE,
        WHITEFIELD,
        WHITE_DOTS,
        BLACK_DOTS;

    }

    @Deprecated
    public static enum Variant {
        HORSE,
        DONKEY,
        MULE,
        UNDEAD_HORSE,
        SKELETON_HORSE,
        LLAMA,
        CAMEL;

    }
}

