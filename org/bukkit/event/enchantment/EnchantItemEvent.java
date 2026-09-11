/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.enchantment;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantItemEvent
extends InventoryEvent
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Block table;
    private final ItemStack item;
    private int level;
    private boolean cancelled;
    private final Map<Enchantment, Integer> enchants;
    private final Enchantment enchantmentHint;
    private final int levelHint;
    private final Player enchanter;
    private final int button;

    public EnchantItemEvent(@NotNull Player enchanter, @NotNull InventoryView view, @NotNull Block table, @NotNull ItemStack item, int level, @NotNull Map<Enchantment, Integer> enchants, @NotNull Enchantment enchantmentHint, int levelHint, int i) {
        super(view);
        this.enchanter = enchanter;
        this.table = table;
        this.item = item;
        this.level = level;
        this.enchants = new HashMap<Enchantment, Integer>(enchants);
        this.enchantmentHint = enchantmentHint;
        this.levelHint = levelHint;
        this.cancelled = false;
        this.button = i;
    }

    @NotNull
    public Player getEnchanter() {
        return this.enchanter;
    }

    @NotNull
    public Block getEnchantBlock() {
        return this.table;
    }

    @NotNull
    public ItemStack getItem() {
        return this.item;
    }

    public int getExpLevelCost() {
        return this.level;
    }

    public void setExpLevelCost(int level) {
        Preconditions.checkArgument((level > 0 ? 1 : 0) != 0, (Object)"The cost must be greater than 0!");
        this.level = level;
    }

    @NotNull
    public Map<Enchantment, Integer> getEnchantsToAdd() {
        return this.enchants;
    }

    @NotNull
    public Enchantment getEnchantmentHint() {
        return this.enchantmentHint;
    }

    public int getLevelHint() {
        return this.levelHint;
    }

    public int whichButton() {
        return this.button;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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

