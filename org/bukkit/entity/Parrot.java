/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public interface Parrot
extends Tameable,
Sittable {
    @NotNull
    public Variant getVariant();

    public void setVariant(@NotNull Variant var1);

    public boolean isDancing();

    public static enum Variant {
        RED,
        BLUE,
        GREEN,
        CYAN,
        GRAY;

    }
}

