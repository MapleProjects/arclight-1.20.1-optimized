/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.Ageable;

public interface Breedable
extends Ageable {
    @Override
    public void setAgeLock(boolean var1);

    @Override
    public boolean getAgeLock();

    @Override
    public boolean canBreed();

    @Override
    public void setBreed(boolean var1);
}

