/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.world;

import java.util.List;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PortalCreateEvent
extends WorldEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final List<BlockState> blocks;
    private final Entity entity;
    private final CreateReason reason;

    @Deprecated
    public PortalCreateEvent(@NotNull List<BlockState> blocks, @NotNull World world, @NotNull CreateReason reason) {
        this(blocks, world, null, reason);
    }

    public PortalCreateEvent(@NotNull List<BlockState> blocks, @NotNull World world, @Nullable Entity entity, @NotNull CreateReason reason) {
        super(world);
        this.blocks = blocks;
        this.entity = entity;
        this.reason = reason;
    }

    @NotNull
    public List<BlockState> getBlocks() {
        return this.blocks;
    }

    @Nullable
    public Entity getEntity() {
        return this.entity;
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
    public CreateReason getReason() {
        return this.reason;
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

    public static enum CreateReason {
        FIRE,
        NETHER_PAIR,
        END_PLATFORM;

    }
}

