/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ZombieVillager
extends Zombie {
    @Override
    public void setVillagerProfession(@Nullable Villager.Profession var1);

    @Override
    @Nullable
    public Villager.Profession getVillagerProfession();

    @NotNull
    public Villager.Type getVillagerType();

    public void setVillagerType(@NotNull Villager.Type var1);

    @Override
    public boolean isConverting();

    @Override
    public int getConversionTime();

    @Override
    public void setConversionTime(int var1);

    @Nullable
    public OfflinePlayer getConversionPlayer();

    public void setConversionPlayer(@Nullable OfflinePlayer var1);
}

