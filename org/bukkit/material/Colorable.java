/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.UndefinedNullability;
import org.jetbrains.annotations.Nullable;

public interface Colorable {
    @Nullable
    public DyeColor getColor();

    public void setColor(@UndefinedNullability(value="defined by subclass") @UndefinedNullability(value="defined by subclass") DyeColor var1);
}

