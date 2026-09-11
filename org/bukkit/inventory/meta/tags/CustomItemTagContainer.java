/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta.tags;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public interface CustomItemTagContainer {
    public <T, Z> void setCustomTag(@NotNull NamespacedKey var1, @NotNull ItemTagType<T, Z> var2, @NotNull Z var3);

    public <T, Z> boolean hasCustomTag(@NotNull NamespacedKey var1, @NotNull ItemTagType<T, Z> var2);

    @Nullable
    public <T, Z> Z getCustomTag(@NotNull NamespacedKey var1, @NotNull ItemTagType<T, Z> var2);

    public void removeCustomTag(@NotNull NamespacedKey var1);

    public boolean isEmpty();

    @NotNull
    public ItemTagAdapterContext getAdapterContext();
}

