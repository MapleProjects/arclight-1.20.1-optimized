/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Enemy;
import org.bukkit.entity.Flying;

public interface Phantom
extends Flying,
Enemy {
    public int getSize();

    public void setSize(int var1);
}

