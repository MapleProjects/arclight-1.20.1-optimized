/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HopperInventorySearchEvent
extends BlockEvent {
    private static final HandlerList handlers = new HandlerList();
    private Inventory inventory;
    private final ContainerType containerType;
    private final Block searchBlock;

    public HopperInventorySearchEvent(@NotNull Inventory inventory, @NotNull ContainerType containerType, @NotNull Block hopper, @NotNull Block searchBlock) {
        super(hopper);
        this.inventory = inventory;
        this.containerType = containerType;
        this.searchBlock = searchBlock;
    }

    public void setInventory(@Nullable Inventory inventory) {
        this.inventory = inventory;
    }

    @Nullable
    public Inventory getInventory() {
        return this.inventory;
    }

    @NotNull
    public ContainerType getContainerType() {
        return this.containerType;
    }

    @NotNull
    public Block getSearchBlock() {
        return this.searchBlock;
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

    public static enum ContainerType {
        SOURCE,
        DESTINATION;

    }
}

