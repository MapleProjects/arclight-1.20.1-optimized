/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public interface Zombie
extends Monster,
Ageable {
    @Deprecated
    public boolean isBaby();

    @Deprecated
    public void setBaby(boolean var1);

    @Deprecated
    public boolean isVillager();

    @Deprecated
    @Contract(value="_ -> fail")
    public void setVillager(boolean var1);

    @Deprecated
    @Contract(value="_ -> fail")
    public void setVillagerProfession(Villager.Profession var1);

    @Deprecated
    @Nullable
    @Contract(value="-> null")
    public Villager.Profession getVillagerProfession();

    public boolean isConverting();

    public int getConversionTime();

    public void setConversionTime(int var1);

    public boolean canBreakDoors();

    public void setCanBreakDoors(boolean var1);
}

