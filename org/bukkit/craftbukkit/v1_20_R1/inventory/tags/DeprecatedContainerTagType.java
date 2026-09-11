/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory.tags;

import com.google.common.base.Preconditions;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedCustomTagContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedItemAdapterContext;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public final class DeprecatedContainerTagType<Z>
implements PersistentDataType<PersistentDataContainer, Z> {
    private final ItemTagType<CustomItemTagContainer, Z> deprecated;

    DeprecatedContainerTagType(ItemTagType<CustomItemTagContainer, Z> deprecated) {
        this.deprecated = deprecated;
    }

    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    public Class<Z> getComplexType() {
        return this.deprecated.getComplexType();
    }

    @Override
    public PersistentDataContainer toPrimitive(Z complex, PersistentDataAdapterContext context) {
        CustomItemTagContainer deprecated = this.deprecated.toPrimitive(complex, new DeprecatedItemAdapterContext(context));
        Preconditions.checkArgument((boolean)(deprecated instanceof DeprecatedCustomTagContainer), (String)"Could not wrap deprecated API due to foreign CustomItemTagContainer implementation %s", (Object)deprecated.getClass().getSimpleName());
        DeprecatedCustomTagContainer tagContainer = (DeprecatedCustomTagContainer)deprecated;
        PersistentDataContainer wrapped = tagContainer.getWrapped();
        Preconditions.checkArgument((boolean)(wrapped instanceof CraftPersistentDataContainer), (String)"Could not wrap deprecated API due to wrong deprecation wrapper %s", (Object)deprecated.getClass().getSimpleName());
        CraftPersistentDataContainer craftTagContainer = (CraftPersistentDataContainer)wrapped;
        return new CraftPersistentDataContainer(craftTagContainer.getRaw(), craftTagContainer.getDataTagTypeRegistry());
    }

    @Override
    public Z fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
        Preconditions.checkArgument((boolean)(primitive instanceof CraftPersistentDataContainer), (String)"Could not wrap deprecated API due to foreign PersistentMetadataContainer implementation %s", (Object)primitive.getClass().getSimpleName());
        return this.deprecated.fromPrimitive(new DeprecatedCustomTagContainer(primitive), new DeprecatedItemAdapterContext(context));
    }
}

