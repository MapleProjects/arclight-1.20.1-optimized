/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface AxolotlBucketMeta
extends ItemMeta {
    @NotNull
    public Axolotl.Variant getVariant();

    public void setVariant(@NotNull Axolotl.Variant var1);

    public boolean hasVariant();

    @Override
    @NotNull
    public AxolotlBucketMeta clone();
}

