/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Zombie;

public interface Husk
extends Zombie {
    @Override
    public boolean isConverting();

    @Override
    public int getConversionTime();

    @Override
    public void setConversionTime(int var1);
}

