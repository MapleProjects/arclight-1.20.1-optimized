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

public interface Metadatable {
    public void setMetadata(@NotNull String var1, @NotNull MetadataValue var2);

    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String var1);

    public boolean hasMetadata(@NotNull String var1);

    public void removeMetadata(@NotNull String var1, @NotNull Plugin var2);
}

