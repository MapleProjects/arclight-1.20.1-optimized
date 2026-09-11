/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface Damageable
extends ItemMeta {
    public boolean hasDamage();

    public int getDamage();

    public void setDamage(int var1);

    @Override
    @NotNull
    public Damageable clone();
}

