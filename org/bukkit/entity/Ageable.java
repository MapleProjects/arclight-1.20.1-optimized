/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Creature;

public interface Ageable
extends Creature {
    public int getAge();

    public void setAge(int var1);

    @Deprecated
    public void setAgeLock(boolean var1);

    @Deprecated
    public boolean getAgeLock();

    public void setBaby();

    public void setAdult();

    public boolean isAdult();

    @Deprecated
    public boolean canBreed();

    @Deprecated
    public void setBreed(boolean var1);
}

