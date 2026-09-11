/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockExpEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBreakEvent
extends BlockExpEvent
implements Cancellable {
    private final Player player;
    private boolean dropItems;
    private boolean cancel;

    public BlockBreakEvent(@NotNull Block theBlock, @NotNull Player player) {
        super(theBlock, 0);
        this.player = player;
        this.dropItems = true;
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    public void setDropItems(boolean dropItems) {
        this.dropItems = dropItems;
    }

    public boolean isDropItems() {
        return this.dropItems;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}

