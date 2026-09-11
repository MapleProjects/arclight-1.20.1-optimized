/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Enemy;
import org.bukkit.entity.Flying;

public interface Ghast
extends Flying,
Enemy {
    public boolean isCharging();

    public void setCharging(boolean var1);
}

