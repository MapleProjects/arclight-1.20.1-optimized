/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Fish;
import org.jetbrains.annotations.NotNull;

public interface TropicalFish
extends Fish {
    @NotNull
    public DyeColor getPatternColor();

    public void setPatternColor(@NotNull DyeColor var1);

    @NotNull
    public DyeColor getBodyColor();

    public void setBodyColor(@NotNull DyeColor var1);

    @NotNull
    public Pattern getPattern();

    public void setPattern(@NotNull Pattern var1);

    public static enum Pattern {
        KOB,
        SUNSTREAK,
        SNOOPER,
        DASHER,
        BRINELY,
        SPOTTY,
        FLOPPER,
        STRIPEY,
        GLITTER,
        BLOCKFISH,
        BETTY,
        CLAYFISH;

    }
}

