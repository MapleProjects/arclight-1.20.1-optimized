/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory.tags;

import java.util.Objects;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedContainerTagType;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedItemAdapterContext;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedItemTagType;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataContainer;

public final class DeprecatedCustomTagContainer
implements CustomItemTagContainer {
    private final PersistentDataContainer wrapped;

    public DeprecatedCustomTagContainer(PersistentDataContainer wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public <T, Z> void setCustomTag(NamespacedKey key, ItemTagType<T, Z> type, Z value) {
        if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
            this.wrapped.set(key, new DeprecatedContainerTagType<Z>(type), value);
        } else {
            this.wrapped.set(key, new DeprecatedItemTagType<T, Z>(type), value);
        }
    }

    @Override
    public <T, Z> boolean hasCustomTag(NamespacedKey key, ItemTagType<T, Z> type) {
        if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
            return this.wrapped.has(key, new DeprecatedContainerTagType<Z>(type));
        }
        return this.wrapped.has(key, new DeprecatedItemTagType<T, Z>(type));
    }

    @Override
    public <T, Z> Z getCustomTag(NamespacedKey key, ItemTagType<T, Z> type) {
        if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
            return this.wrapped.get(key, new DeprecatedContainerTagType<Z>(type));
        }
        return this.wrapped.get(key, new DeprecatedItemTagType<T, Z>(type));
    }

    @Override
    public void removeCustomTag(NamespacedKey key) {
        this.wrapped.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return this.wrapped.isEmpty();
    }

    @Override
    public ItemTagAdapterContext getAdapterContext() {
        return new DeprecatedItemAdapterContext(this.wrapped.getAdapterContext());
    }

    public PersistentDataContainer getWrapped() {
        return this.wrapped;
    }
}

