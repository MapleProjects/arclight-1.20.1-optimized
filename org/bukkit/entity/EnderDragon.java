/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.Boss;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EnderDragon
extends ComplexLivingEntity,
Boss,
Mob,
Enemy {
    @NotNull
    public Phase getPhase();

    public void setPhase(@NotNull Phase var1);

    @Nullable
    public DragonBattle getDragonBattle();

    public int getDeathAnimationTicks();

    public static enum Phase {
        CIRCLING,
        STRAFING,
        FLY_TO_PORTAL,
        LAND_ON_PORTAL,
        LEAVE_PORTAL,
        BREATH_ATTACK,
        SEARCH_FOR_BREATH_ATTACK_TARGET,
        ROAR_BEFORE_ATTACK,
        CHARGE_PLAYER,
        DYING,
        HOVER;

    }
}

