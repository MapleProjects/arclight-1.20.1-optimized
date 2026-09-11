/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Cow;
import org.jetbrains.annotations.NotNull;

public interface MushroomCow
extends Cow {
    @NotNull
    public Variant getVariant();

    public void setVariant(@NotNull Variant var1);

    public static enum Variant {
        RED,
        BROWN;

    }
}

