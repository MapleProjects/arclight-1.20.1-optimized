/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.block.TileState;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.NotNull;

public interface Banner
extends TileState {
    @NotNull
    public DyeColor getBaseColor();

    public void setBaseColor(@NotNull DyeColor var1);

    @NotNull
    public List<Pattern> getPatterns();

    public void setPatterns(@NotNull List<Pattern> var1);

    public void addPattern(@NotNull Pattern var1);

    @NotNull
    public Pattern getPattern(int var1);

    @NotNull
    public Pattern removePattern(int var1);

    public void setPattern(int var1, @NotNull Pattern var2);

    public int numberOfPatterns();
}

