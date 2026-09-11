/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Monster;

public interface Guardian
extends Monster {
    public boolean setLaser(boolean var1);

    public boolean hasLaser();

    public int getLaserDuration();

    public void setLaserTicks(int var1);

    public int getLaserTicks();

    @Deprecated
    public boolean isElder();

    @Deprecated
    public void setElder(boolean var1);

    public boolean isMoving();
}

