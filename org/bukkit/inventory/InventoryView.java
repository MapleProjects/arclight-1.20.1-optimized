/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class InventoryView {
    public static final int OUTSIDE = -999;

    @NotNull
    public abstract Inventory getTopInventory();

    @NotNull
    public abstract Inventory getBottomInventory();

    @NotNull
    public abstract HumanEntity getPlayer();

    @NotNull
    public abstract InventoryType getType();

    public void setItem(int slot, @Nullable ItemStack item) {
        Inventory inventory = this.getInventory(slot);
        if (inventory != null) {
            inventory.setItem(this.convertSlot(slot), item);
        } else if (item != null) {
            this.getPlayer().getWorld().dropItemNaturally(this.getPlayer().getLocation(), item);
        }
    }

    @Nullable
    public ItemStack getItem(int slot) {
        Inventory inventory = this.getInventory(slot);
        return inventory == null ? null : inventory.getItem(this.convertSlot(slot));
    }

    public final void setCursor(@Nullable ItemStack item) {
        this.getPlayer().setItemOnCursor(item);
    }

    @Nullable
    public final ItemStack getCursor() {
        return this.getPlayer().getItemOnCursor();
    }

    @Nullable
    public final Inventory getInventory(int rawSlot) {
        if (rawSlot == -999 || rawSlot == -1) {
            return null;
        }
        Preconditions.checkArgument((rawSlot >= 0 ? 1 : 0) != 0, (String)"Negative, non outside slot %s", (int)rawSlot);
        Preconditions.checkArgument((rawSlot < this.countSlots() ? 1 : 0) != 0, (String)"Slot %s greater than inventory slot count", (int)rawSlot);
        if (rawSlot < this.getTopInventory().getSize()) {
            return this.getTopInventory();
        }
        return this.getBottomInventory();
    }

    public final int convertSlot(int rawSlot) {
        int numInTop = this.getTopInventory().getSize();
        if (rawSlot < numInTop) {
            return rawSlot;
        }
        int slot = rawSlot - numInTop;
        if (this.getType() == InventoryType.CRAFTING || this.getType() == InventoryType.CREATIVE) {
            if (slot < 4) {
                return 39 - slot;
            }
            if (slot > 39) {
                return slot;
            }
            slot -= 4;
        }
        slot = slot >= 27 ? (slot -= 27) : (slot += 9);
        return slot;
    }

    @NotNull
    public final InventoryType.SlotType getSlotType(int slot) {
        InventoryType.SlotType type = InventoryType.SlotType.CONTAINER;
        if (slot >= 0 && slot < this.getTopInventory().getSize()) {
            switch (this.getType()) {
                case FURNACE: 
                case BLAST_FURNACE: 
                case SMOKER: {
                    if (slot == 2) {
                        type = InventoryType.SlotType.RESULT;
                        break;
                    }
                    if (slot == 1) {
                        type = InventoryType.SlotType.FUEL;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case BREWING: {
                    if (slot == 3) {
                        type = InventoryType.SlotType.FUEL;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case ENCHANTING: {
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case WORKBENCH: 
                case CRAFTING: {
                    if (slot == 0) {
                        type = InventoryType.SlotType.RESULT;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case BEACON: {
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case MERCHANT: 
                case ANVIL: 
                case SMITHING: 
                case CARTOGRAPHY: 
                case GRINDSTONE: {
                    if (slot == 2) {
                        type = InventoryType.SlotType.RESULT;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case STONECUTTER: {
                    if (slot == 1) {
                        type = InventoryType.SlotType.RESULT;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                    break;
                }
                case LOOM: 
                case SMITHING_NEW: {
                    if (slot == 3) {
                        type = InventoryType.SlotType.RESULT;
                        break;
                    }
                    type = InventoryType.SlotType.CRAFTING;
                }
            }
        } else if (slot < 0) {
            type = InventoryType.SlotType.OUTSIDE;
        } else if (this.getType() == InventoryType.CRAFTING) {
            if (slot < 9) {
                type = InventoryType.SlotType.ARMOR;
            } else if (slot > 35) {
                type = InventoryType.SlotType.QUICKBAR;
            }
        } else if (slot >= this.countSlots() - 14) {
            type = InventoryType.SlotType.QUICKBAR;
        }
        return type;
    }

    public final void close() {
        this.getPlayer().closeInventory();
    }

    public final int countSlots() {
        return this.getTopInventory().getSize() + this.getBottomInventory().getSize();
    }

    public final boolean setProperty(@NotNull Property prop, int value) {
        return this.getPlayer().setWindowProperty(prop, value);
    }

    @NotNull
    public abstract String getTitle();

    @NotNull
    public abstract String getOriginalTitle();

    public abstract void setTitle(@NotNull String var1);

    public static enum Property {
        BREW_TIME(0, InventoryType.BREWING),
        FUEL_TIME(1, InventoryType.BREWING),
        BURN_TIME(0, InventoryType.FURNACE),
        TICKS_FOR_CURRENT_FUEL(1, InventoryType.FURNACE),
        COOK_TIME(2, InventoryType.FURNACE),
        TICKS_FOR_CURRENT_SMELTING(3, InventoryType.FURNACE),
        ENCHANT_BUTTON1(0, InventoryType.ENCHANTING),
        ENCHANT_BUTTON2(1, InventoryType.ENCHANTING),
        ENCHANT_BUTTON3(2, InventoryType.ENCHANTING),
        ENCHANT_XP_SEED(3, InventoryType.ENCHANTING),
        ENCHANT_ID1(4, InventoryType.ENCHANTING),
        ENCHANT_ID2(5, InventoryType.ENCHANTING),
        ENCHANT_ID3(6, InventoryType.ENCHANTING),
        ENCHANT_LEVEL1(7, InventoryType.ENCHANTING),
        ENCHANT_LEVEL2(8, InventoryType.ENCHANTING),
        ENCHANT_LEVEL3(9, InventoryType.ENCHANTING),
        LEVELS(0, InventoryType.BEACON),
        PRIMARY_EFFECT(1, InventoryType.BEACON),
        SECONDARY_EFFECT(2, InventoryType.BEACON),
        REPAIR_COST(0, InventoryType.ANVIL),
        BOOK_PAGE(0, InventoryType.LECTERN);

        int id;
        InventoryType style;

        private Property(int id, InventoryType appliesTo) {
            this.id = id;
            this.style = appliesTo;
        }

        @NotNull
        public InventoryType getType() {
            return this.style;
        }

        @Deprecated
        public int getId() {
            return this.id;
        }
    }
}

