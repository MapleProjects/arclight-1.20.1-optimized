/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;

public interface ExplosiveMinecart
extends Minecart {
    public void setFuseTicks(int var1);

    public int getFuseTicks();

    public void ignite();

    public boolean isIgnited();

    public void explode();

    public void explode(double var1);
}

