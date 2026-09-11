/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.metadata;

import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FixedMetadataValue
extends LazyMetadataValue {
    private final Object internalValue;

    public FixedMetadataValue(@NotNull Plugin owningPlugin, @Nullable Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    @Override
    public void invalidate() {
    }

    @Override
    @Nullable
    public Object value() {
        return this.internalValue;
    }
}

