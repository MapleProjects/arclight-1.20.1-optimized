/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;

public interface Rabbit
extends Animals {
    @NotNull
    public Type getRabbitType();

    public void setRabbitType(@NotNull Type var1);

    public static enum Type {
        BROWN,
        WHITE,
        BLACK,
        BLACK_AND_WHITE,
        GOLD,
        SALT_AND_PEPPER,
        THE_KILLER_BUNNY;

    }
}

