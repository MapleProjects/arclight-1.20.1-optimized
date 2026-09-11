/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.boss;

import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DragonBattle {
    @Nullable
    public EnderDragon getEnderDragon();

    @NotNull
    public BossBar getBossBar();

    @Nullable
    public Location getEndPortalLocation();

    public boolean generateEndPortal(boolean var1);

    public boolean hasBeenPreviouslyKilled();

    public void initiateRespawn();

    public boolean initiateRespawn(@Nullable Collection<EnderCrystal> var1);

    @NotNull
    public RespawnPhase getRespawnPhase();

    public boolean setRespawnPhase(@NotNull RespawnPhase var1);

    public void resetCrystals();

    public static enum RespawnPhase {
        START,
        PREPARING_TO_SUMMON_PILLARS,
        SUMMONING_PILLARS,
        SUMMONING_DRAGON,
        END,
        NONE;

    }
}

