/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.Art;
import org.bukkit.entity.Hanging;
import org.jetbrains.annotations.NotNull;

public interface Painting
extends Hanging {
    @NotNull
    public Art getArt();

    public boolean setArt(@NotNull Art var1);

    public boolean setArt(@NotNull Art var1, boolean var2);
}

