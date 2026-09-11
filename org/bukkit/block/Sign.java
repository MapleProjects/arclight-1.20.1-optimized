/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.DyeColor;
import org.bukkit.block.TileState;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

public interface Sign
extends TileState,
Colorable {
    @Deprecated
    @NotNull
    public String[] getLines();

    @Deprecated
    @NotNull
    public String getLine(int var1) throws IndexOutOfBoundsException;

    @Deprecated
    public void setLine(int var1, @NotNull String var2) throws IndexOutOfBoundsException;

    @Deprecated
    public boolean isEditable();

    @Deprecated
    public void setEditable(boolean var1);

    public boolean isWaxed();

    public void setWaxed(boolean var1);

    @Deprecated
    public boolean isGlowingText();

    @Deprecated
    public void setGlowingText(boolean var1);

    @Override
    @Deprecated
    @NotNull
    public DyeColor getColor();

    @Override
    @Deprecated
    public void setColor(@NotNull DyeColor var1);

    @NotNull
    public SignSide getSide(@NotNull Side var1);
}

