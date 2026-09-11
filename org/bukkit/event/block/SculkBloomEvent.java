/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;

public class SculkBloomEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private int charge;

    public SculkBloomEvent(@NotNull Block theBlock, int charge) {
        super(theBlock);
        this.charge = charge;
    }

    public int getCharge() {
        return this.charge;
    }

    public void setCharge(int charge) {
        Preconditions.checkArgument((charge >= 0 && charge <= 1000 ? 1 : 0) != 0, (Object)(String.valueOf(charge) + " is not in range [0, 1000]"));
        this.charge = charge;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

