/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.persistence;

import java.util.Set;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PersistentDataContainer {
    public <T, Z> void set(@NotNull NamespacedKey var1, @NotNull PersistentDataType<T, Z> var2, @NotNull Z var3);

    public <T, Z> boolean has(@NotNull NamespacedKey var1, @NotNull PersistentDataType<T, Z> var2);

    @Nullable
    public <T, Z> Z get(@NotNull NamespacedKey var1, @NotNull PersistentDataType<T, Z> var2);

    @NotNull
    public <T, Z> Z getOrDefault(@NotNull NamespacedKey var1, @NotNull PersistentDataType<T, Z> var2, @NotNull Z var3);

    @NotNull
    public Set<NamespacedKey> getKeys();

    public void remove(@NotNull NamespacedKey var1);

    public boolean isEmpty();

    @NotNull
    public PersistentDataAdapterContext getAdapterContext();
}

