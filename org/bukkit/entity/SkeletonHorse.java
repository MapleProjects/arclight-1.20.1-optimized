/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.AbstractHorse;

public interface SkeletonHorse
extends AbstractHorse {
    public boolean isTrapped();

    public void setTrapped(boolean var1);

    public int getTrapTime();

    public void setTrapTime(int var1);
}

