/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.sign;

import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

public interface SignSide
extends Colorable {
    @NotNull
    public String[] getLines();

    @NotNull
    public String getLine(int var1) throws IndexOutOfBoundsException;

    public void setLine(int var1, @NotNull String var2) throws IndexOutOfBoundsException;

    public boolean isGlowingText();

    public void setGlowingText(boolean var1);
}

