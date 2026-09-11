/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory.tags;

import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedCustomTagContainer;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
import org.bukkit.persistence.PersistentDataAdapterContext;

public final class DeprecatedItemAdapterContext
implements ItemTagAdapterContext {
    private final PersistentDataAdapterContext context;

    public DeprecatedItemAdapterContext(PersistentDataAdapterContext context) {
        this.context = context;
    }

    @Override
    public CustomItemTagContainer newTagContainer() {
        return new DeprecatedCustomTagContainer(this.context.newPersistentDataContainer());
    }
}

