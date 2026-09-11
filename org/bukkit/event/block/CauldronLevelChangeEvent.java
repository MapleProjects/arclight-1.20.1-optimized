/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CauldronLevelChangeEvent
extends BlockEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Entity entity;
    private final ChangeReason reason;
    private final BlockState newState;

    public CauldronLevelChangeEvent(@NotNull Block block, @Nullable Entity entity, @NotNull ChangeReason reason, @NotNull BlockState newBlock) {
        super(block);
        this.entity = entity;
        this.reason = reason;
        this.newState = newBlock;
    }

    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    @NotNull
    public ChangeReason getReason() {
        return this.reason;
    }

    @NotNull
    public BlockState getNewState() {
        return this.newState;
    }

    @Deprecated
    public int getOldLevel() {
        BlockData oldBlock = this.getBlock().getBlockData();
        return oldBlock instanceof Levelled ? ((Levelled)oldBlock).getLevel() : (oldBlock.getMaterial() == Material.CAULDRON ? 0 : 3);
    }

    @Deprecated
    public int getNewLevel() {
        BlockData newBlock = this.newState.getBlockData();
        return newBlock instanceof Levelled ? ((Levelled)newBlock).getLevel() : (newBlock.getMaterial() == Material.CAULDRON ? 0 : 3);
    }

    @Deprecated
    public void setNewLevel(int newLevel) {
        Preconditions.checkArgument((newLevel >= 0 && newLevel <= 3 ? 1 : 0) != 0, (String)"Cauldron level out of bounds 0 <= %s <= 3", (int)newLevel);
        if (newLevel == 0) {
            this.newState.setType(Material.CAULDRON);
        } else if (this.newState.getBlockData() instanceof Levelled) {
            ((Levelled)this.newState.getBlockData()).setLevel(newLevel);
        }
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

    public static enum ChangeReason {
        BUCKET_FILL,
        BUCKET_EMPTY,
        BOTTLE_FILL,
        BOTTLE_EMPTY,
        BANNER_WASH,
        ARMOR_WASH,
        SHULKER_WASH,
        EXTINGUISH,
        EVAPORATE,
        NATURAL_FILL,
        UNKNOWN;

    }
}

