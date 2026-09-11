/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ArmorMeta
extends ItemMeta {
    public boolean hasTrim();

    public void setTrim(@Nullable ArmorTrim var1);

    @Nullable
    public ArmorTrim getTrim();

    @Override
    @NotNull
    public ArmorMeta clone();
}

