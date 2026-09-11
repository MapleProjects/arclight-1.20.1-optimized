/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.metadata;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class MetadataStoreBase<T> {
    private Map<String, Map<Plugin, MetadataValue>> metadataMap = new HashMap<String, Map<Plugin, MetadataValue>>();

    public synchronized void setMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        Preconditions.checkArgument((newMetadataValue != null ? 1 : 0) != 0, (Object)"Value cannot be null");
        Plugin owningPlugin = newMetadataValue.getOwningPlugin();
        Preconditions.checkArgument((owningPlugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        String key = this.disambiguate(subject, metadataKey);
        Map<Plugin, MetadataValue> entry = this.metadataMap.get(key);
        if (entry == null) {
            entry = new WeakHashMap<Plugin, MetadataValue>(1);
            this.metadataMap.put(key, entry);
        }
        entry.put(owningPlugin, newMetadataValue);
    }

    @NotNull
    public synchronized List<MetadataValue> getMetadata(@NotNull T subject, @NotNull String metadataKey) {
        String key = this.disambiguate(subject, metadataKey);
        if (this.metadataMap.containsKey(key)) {
            Collection<MetadataValue> values = this.metadataMap.get(key).values();
            return Collections.unmodifiableList(new ArrayList<MetadataValue>(values));
        }
        return Collections.emptyList();
    }

    public synchronized boolean hasMetadata(@NotNull T subject, @NotNull String metadataKey) {
        String key = this.disambiguate(subject, metadataKey);
        return this.metadataMap.containsKey(key);
    }

    public synchronized void removeMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        Preconditions.checkArgument((owningPlugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        String key = this.disambiguate(subject, metadataKey);
        Map<Plugin, MetadataValue> entry = this.metadataMap.get(key);
        if (entry == null) {
            return;
        }
        entry.remove(owningPlugin);
        if (entry.isEmpty()) {
            this.metadataMap.remove(key);
        }
    }

    public synchronized void invalidateAll(@NotNull Plugin owningPlugin) {
        Preconditions.checkArgument((owningPlugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        for (Map<Plugin, MetadataValue> values : this.metadataMap.values()) {
            if (!values.containsKey(owningPlugin)) continue;
            values.get(owningPlugin).invalidate();
        }
    }

    @NotNull
    protected abstract String disambiguate(@NotNull T var1, @NotNull String var2);
}

