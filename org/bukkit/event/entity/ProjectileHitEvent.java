/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectileHitEvent
extends EntityEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Entity hitEntity;
    private final Block hitBlock;
    private final BlockFace hitFace;
    private boolean cancel = false;

    public ProjectileHitEvent(@NotNull Projectile projectile) {
        this(projectile, null, null);
    }

    public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity) {
        this(projectile, hitEntity, null);
    }

    public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Block hitBlock) {
        this(projectile, null, hitBlock);
    }

    public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock) {
        this(projectile, hitEntity, hitBlock, null);
    }

    public ProjectileHitEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable BlockFace hitFace) {
        super(projectile);
        this.hitEntity = hitEntity;
        this.hitBlock = hitBlock;
        this.hitFace = hitFace;
    }

    @Override
    @NotNull
    public Projectile getEntity() {
        return (Projectile)this.entity;
    }

    @Nullable
    public Block getHitBlock() {
        return this.hitBlock;
    }

    @Nullable
    public BlockFace getHitBlockFace() {
        return this.hitFace;
    }

    @Nullable
    public Entity getHitEntity() {
        return this.hitEntity;
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

