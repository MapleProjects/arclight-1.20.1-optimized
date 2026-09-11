/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.Color;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LeatherArmorMeta
extends ItemMeta {
    @NotNull
    public Color getColor();

    public void setColor(@Nullable Color var1);

    @Override
    @NotNull
    public LeatherArmorMeta clone();
}

