/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Raid;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Raider
extends Monster {
    public void setRaid(@Nullable Raid var1);

    @Nullable
    public Raid getRaid();

    public int getWave();

    public void setWave(int var1);

    @Nullable
    public Block getPatrolTarget();

    public void setPatrolTarget(@Nullable Block var1);

    public boolean isPatrolLeader();

    public void setPatrolLeader(boolean var1);

    public boolean isCanJoinRaid();

    public void setCanJoinRaid(boolean var1);

    public int getTicksOutsideRaid();

    public void setTicksOutsideRaid(int var1);

    public boolean isCelebrating();

    public void setCelebrating(boolean var1);

    @NotNull
    public Sound getCelebrationSound();
}

