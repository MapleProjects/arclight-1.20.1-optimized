/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface CommandBlock
extends Directional {
    public boolean isConditional();

    public void setConditional(boolean var1);
}

