/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Monster;

public interface PiglinAbstract
extends Monster,
Ageable {
    public boolean isImmuneToZombification();

    public void setImmuneToZombification(boolean var1);

    public int getConversionTime();

    public void setConversionTime(int var1);

    public boolean isConverting();

    @Deprecated
    public boolean isBaby();

    @Deprecated
    public void setBaby(boolean var1);
}

