/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SignChangeEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final Player player;
    private final String[] lines;
    private final Side side;

    @Deprecated
    public SignChangeEvent(@NotNull Block theBlock, @NotNull Player thePlayer, @NotNull String[] theLines) {
        this(theBlock, thePlayer, theLines, Side.FRONT);
    }

    public SignChangeEvent(@NotNull Block theBlock, @NotNull Player thePlayer, @NotNull String[] theLines, @NotNull Side side) {
        super(theBlock);
        this.player = thePlayer;
        this.lines = theLines;
        this.side = side;
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public String[] getLines() {
        return this.lines;
    }

    @Nullable
    public String getLine(int index) throws IndexOutOfBoundsException {
        return this.lines[index];
    }

    public void setLine(int index, @Nullable String line) throws IndexOutOfBoundsException {
        this.lines[index] = line;
    }

    @NotNull
    public Side getSide() {
        return this.side;
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

