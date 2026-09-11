/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.metadata;

import java.util.List;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface MetadataStore<T> {
    public void setMetadata(@NotNull T var1, @NotNull String var2, @NotNull MetadataValue var3);

    @NotNull
    public List<MetadataValue> getMetadata(@NotNull T var1, @NotNull String var2);

    public boolean hasMetadata(@NotNull T var1, @NotNull String var2);

    public void removeMetadata(@NotNull T var1, @NotNull String var2, @NotNull Plugin var3);

    public void invalidateAll(@NotNull Plugin var1);
}

