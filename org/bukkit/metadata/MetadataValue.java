/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MetadataValue {
    @Nullable
    public Object value();

    public int asInt();

    public float asFloat();

    public double asDouble();

    public long asLong();

    public short asShort();

    public byte asByte();

    public boolean asBoolean();

    @NotNull
    public String asString();

    @Nullable
    public Plugin getOwningPlugin();

    public void invalidate();
}

