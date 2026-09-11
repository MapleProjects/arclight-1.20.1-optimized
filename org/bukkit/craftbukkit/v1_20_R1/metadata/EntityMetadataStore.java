/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;

public class EntityMetadataStore
extends MetadataStoreBase<Entity>
implements MetadataStore<Entity> {
    @Override
    protected String disambiguate(Entity entity, String metadataKey) {
        return String.valueOf(entity.getUniqueId().toString()) + ":" + metadataKey;
    }
}

