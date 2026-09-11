/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class BlockEvent
extends Event {
    protected Block block;

    public BlockEvent(@NotNull Block theBlock) {
        this.block = theBlock;
    }

    @NotNull
    public final Block getBlock() {
        return this.block;
    }
}

