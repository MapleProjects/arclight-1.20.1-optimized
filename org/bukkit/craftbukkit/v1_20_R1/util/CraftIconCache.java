/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import org.bukkit.util.CachedServerIcon;

public class CraftIconCache
implements CachedServerIcon {
    public final byte[] value;

    public CraftIconCache(byte[] value) {
        this.value = value;
    }
}

