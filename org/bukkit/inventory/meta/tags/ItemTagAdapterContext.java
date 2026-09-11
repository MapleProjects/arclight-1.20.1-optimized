/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta.tags;

import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.jetbrains.annotations.NotNull;

@Deprecated
public interface ItemTagAdapterContext {
    @NotNull
    public CustomItemTagContainer newTagContainer();
}

