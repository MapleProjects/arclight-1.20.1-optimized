/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BannerMeta
extends ItemMeta {
    @Deprecated
    @Nullable
    public DyeColor getBaseColor();

    @Deprecated
    public void setBaseColor(@Nullable DyeColor var1);

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

