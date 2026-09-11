/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.metadata;

import org.bukkit.World;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;

public class WorldMetadataStore
extends MetadataStoreBase<World>
implements MetadataStore<World> {
    @Override
    protected String disambiguate(World world, String metadataKey) {
        return String.valueOf(world.getUID().toString()) + ":" + metadataKey;
    }
}

