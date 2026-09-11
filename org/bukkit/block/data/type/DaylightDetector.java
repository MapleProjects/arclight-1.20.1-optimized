/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.AnaloguePowerable;

public interface DaylightDetector
extends AnaloguePowerable {
    public boolean isInverted();

    public void setInverted(boolean var1);
}

