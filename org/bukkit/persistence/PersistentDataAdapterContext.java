/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.persistence;

import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public interface PersistentDataAdapterContext {
    @NotNull
    public PersistentDataContainer newPersistentDataContainer();
}

