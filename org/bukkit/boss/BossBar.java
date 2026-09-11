/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.boss;

import java.util.List;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BossBar {
    @NotNull
    public String getTitle();

    public void setTitle(@Nullable String var1);

    @NotNull
    public BarColor getColor();

    public void setColor(@NotNull BarColor var1);

    @NotNull
    public BarStyle getStyle();

    public void setStyle(@NotNull BarStyle var1);

    public void removeFlag(@NotNull BarFlag var1);

    public void addFlag(@NotNull BarFlag var1);

    public boolean hasFlag(@NotNull BarFlag var1);

    public void setProgress(double var1);

    public double getProgress();

    public void addPlayer(@NotNull Player var1);

    public void removePlayer(@NotNull Player var1);

    public void removeAll();

    @NotNull
    public List<Player> getPlayers();

    public void setVisible(boolean var1);

    public boolean isVisible();

    @Deprecated
    public void show();

    @Deprecated
    public void hide();
}

