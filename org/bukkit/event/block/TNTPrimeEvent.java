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
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TNTPrimeEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final PrimeCause igniteCause;
    private final Entity primingEntity;
    private final Block primingBlock;

    public TNTPrimeEvent(@NotNull Block block, @NotNull PrimeCause igniteCause, @Nullable Entity primingEntity, @Nullable Block primingBlock) {
        super(block);
        this.igniteCause = igniteCause;
        this.primingEntity = primingEntity;
        this.primingBlock = primingBlock;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    public PrimeCause getCause() {
        return this.igniteCause;
    }

    @Nullable
    public Entity getPrimingEntity() {
        return this.primingEntity;
    }

    @Nullable
    public Block getPrimingBlock() {
        return this.primingBlock;
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

    public static enum PrimeCause {
        FIRE,
        REDSTONE,
        PLAYER,
        EXPLOSION,
        PROJECTILE,
        BLOCK_BREAK,
        DISPENSER;

    }
}

