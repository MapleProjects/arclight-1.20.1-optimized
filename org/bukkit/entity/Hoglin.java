/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Enemy;

public interface Hoglin
extends Animals,
Enemy {
    public boolean isImmuneToZombification();

    public void setImmuneToZombification(boolean var1);

    public boolean isAbleToBeHunted();

    public void setIsAbleToBeHunted(boolean var1);

    public int getConversionTime();

    public void setConversionTime(int var1);

    public boolean isConverting();
}

