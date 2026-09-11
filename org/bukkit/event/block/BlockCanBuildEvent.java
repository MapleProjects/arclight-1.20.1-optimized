/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockCanBuildEvent
extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    protected boolean buildable;
    protected BlockData blockData;
    private final Player player;

    @Deprecated
    public BlockCanBuildEvent(@NotNull Block block, @NotNull BlockData type, boolean canBuild) {
        this(block, null, type, canBuild);
    }

    public BlockCanBuildEvent(@NotNull Block block, @Nullable Player player, @NotNull BlockData type, boolean canBuild) {
        super(block);
        this.player = player;
        this.buildable = canBuild;
        this.blockData = type;
    }

    public boolean isBuildable() {
        return this.buildable;
    }

    public void setBuildable(boolean cancel) {
        this.buildable = cancel;
    }

    @NotNull
    public Material getMaterial() {
        return this.blockData.getMaterial();
    }

    @NotNull
    public BlockData getBlockData() {
        return this.blockData;
    }

    @Nullable
    public Player getPlayer() {
        return this.player;
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

