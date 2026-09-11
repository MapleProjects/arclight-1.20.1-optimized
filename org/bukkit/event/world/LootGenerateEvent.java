/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.world;

import java.util.Collection;
import java.util.List;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootGenerateEvent
extends WorldEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Entity entity;
    private final InventoryHolder inventoryHolder;
    private final LootTable lootTable;
    private final LootContext lootContext;
    private final List<ItemStack> loot;
    private final boolean plugin;

    public LootGenerateEvent(@NotNull World world, @Nullable Entity entity, @Nullable InventoryHolder inventoryHolder, @NotNull LootTable lootTable, @NotNull LootContext lootContext, @NotNull List<ItemStack> items, boolean plugin) {
        super(world);
        this.entity = entity;
        this.inventoryHolder = inventoryHolder;
        this.lootTable = lootTable;
        this.lootContext = lootContext;
        this.loot = items;
        this.plugin = plugin;
    }

    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    @Nullable
    public InventoryHolder getInventoryHolder() {
        return this.inventoryHolder;
    }

    @NotNull
    public LootTable getLootTable() {
        return this.lootTable;
    }

    @NotNull
    public LootContext getLootContext() {
        return this.lootContext;
    }

    public void setLoot(@Nullable Collection<ItemStack> loot) {
        this.loot.clear();
        if (loot != null) {
            this.loot.addAll(loot);
        }
    }

    @NotNull
    public List<ItemStack> getLoot() {
        return this.loot;
    }

    public boolean isPlugin() {
        return this.plugin;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
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

