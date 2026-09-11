/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryDragEvent
extends InventoryInteractEvent {
    private static final HandlerList handlers = new HandlerList();
    private final DragType type;
    private final Map<Integer, ItemStack> addedItems;
    private final Set<Integer> containerSlots;
    private final ItemStack oldCursor;
    private ItemStack newCursor;

    public InventoryDragEvent(@NotNull InventoryView what, @Nullable ItemStack newCursor, @NotNull ItemStack oldCursor, boolean right, @NotNull Map<Integer, ItemStack> slots) {
        super(what);
        Preconditions.checkArgument((oldCursor != null ? 1 : 0) != 0);
        Preconditions.checkArgument((slots != null ? 1 : 0) != 0);
        this.type = right ? DragType.SINGLE : DragType.EVEN;
        this.newCursor = newCursor;
        this.oldCursor = oldCursor;
        this.addedItems = slots;
        ImmutableSet.Builder b = ImmutableSet.builder();
        for (Integer slot : slots.keySet()) {
            b.add((Object)what.convertSlot(slot));
        }
        this.containerSlots = b.build();
    }

    @NotNull
    public Map<Integer, ItemStack> getNewItems() {
        return Collections.unmodifiableMap(this.addedItems);
    }

    @NotNull
    public Set<Integer> getRawSlots() {
        return this.addedItems.keySet();
    }

    @NotNull
    public Set<Integer> getInventorySlots() {
        return this.containerSlots;
    }

    @Nullable
    public ItemStack getCursor() {
        return this.newCursor;
    }

    public void setCursor(@Nullable ItemStack newCursor) {
        this.newCursor = newCursor;
    }

    @NotNull
    public ItemStack getOldCursor() {
        return this.oldCursor.clone();
    }

    @NotNull
    public DragType getType() {
        return this.type;
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

