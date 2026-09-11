/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;

public interface Axolotl
extends Animals {
    public boolean isPlayingDead();

    public void setPlayingDead(boolean var1);

    @NotNull
    public Variant getVariant();

    public void setVariant(@NotNull Variant var1);

    public static enum Variant {
        LUCY,
        WILD,
        GOLD,
        CYAN,
        BLUE;

    }
}

