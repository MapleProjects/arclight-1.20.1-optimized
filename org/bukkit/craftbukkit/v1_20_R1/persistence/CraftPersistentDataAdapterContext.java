/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.persistence;

import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.persistence.PersistentDataAdapterContext;

public final class CraftPersistentDataAdapterContext
implements PersistentDataAdapterContext {
    private final CraftPersistentDataTypeRegistry registry;

    public CraftPersistentDataAdapterContext(CraftPersistentDataTypeRegistry registry) {
        this.registry = registry;
    }

    @Override
    public CraftPersistentDataContainer newPersistentDataContainer() {
        return new CraftPersistentDataContainer(this.registry);
    }
}

