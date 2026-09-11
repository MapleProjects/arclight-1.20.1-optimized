/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockIgniteEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final IgniteCause cause;
    private final Entity ignitingEntity;
    private final Block ignitingBlock;
    private boolean cancel;

    public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @Nullable Entity ignitingEntity) {
        this(theBlock, cause, ignitingEntity, null);
    }

    public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @NotNull Block ignitingBlock) {
        this(theBlock, cause, null, ignitingBlock);
    }

    public BlockIgniteEvent(@NotNull Block theBlock, @NotNull IgniteCause cause, @Nullable Entity ignitingEntity, @Nullable Block ignitingBlock) {
        super(theBlock);
        this.cause = cause;
        this.ignitingEntity = ignitingEntity;
        this.ignitingBlock = ignitingBlock;
        this.cancel = false;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public IgniteCause getCause() {
        return this.cause;
    }

    @Nullable
    public Player getPlayer() {
        if (this.ignitingEntity instanceof Player) {
            return (Player)this.ignitingEntity;
        }
        return null;
    }

    @Nullable
    public Entity getIgnitingEntity() {
        return this.ignitingEntity;
    }

    @Nullable
    public Block getIgnitingBlock() {
        return this.ignitingBlock;
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

    public static enum IgniteCause {
        LAVA,
        FLINT_AND_STEEL,
        SPREAD,
        LIGHTNING,
        FIREBALL,
        ENDER_CRYSTAL,
        EXPLOSION,
        ARROW;

    }
}

