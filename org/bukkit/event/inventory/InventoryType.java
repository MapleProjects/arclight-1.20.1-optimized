/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import org.jetbrains.annotations.NotNull;

public enum InventoryType {
    CHEST(27, "Chest"),
    DISPENSER(9, "Dispenser"),
    DROPPER(9, "Dropper"),
    FURNACE(3, "Furnace"),
    WORKBENCH(10, "Crafting"),
    CRAFTING(5, "Crafting", false),
    ENCHANTING(2, "Enchanting"),
    BREWING(5, "Brewing"),
    PLAYER(41, "Player"),
    CREATIVE(9, "Creative", false),
    MERCHANT(3, "Villager", false),
    ENDER_CHEST(27, "Ender Chest"),
    ANVIL(3, "Repairing"),
    SMITHING(4, "Upgrade Gear"),
    BEACON(1, "container.beacon"),
    HOPPER(5, "Item Hopper"),
    SHULKER_BOX(27, "Shulker Box"),
    BARREL(27, "Barrel"),
    BLAST_FURNACE(3, "Blast Furnace"),
    LECTERN(1, "Lectern"),
    SMOKER(3, "Smoker"),
    LOOM(4, "Loom"),
    CARTOGRAPHY(3, "Cartography Table"),
    GRINDSTONE(3, "Repair & Disenchant"),
    STONECUTTER(2, "Stonecutter"),
    COMPOSTER(1, "Composter", false),
    CHISELED_BOOKSHELF(6, "Chiseled Bookshelf", false),
    JUKEBOX(1, "Jukebox", false),
    SMITHING_NEW(4, "Upgrade Gear");

    private final int size;
    private final String title;
    private final boolean isCreatable;

    private InventoryType(int defaultSize, String defaultTitle) {
        this(defaultSize, defaultTitle, true);
    }

    private InventoryType(int defaultSize, String defaultTitle, boolean isCreatable) {
        this.size = defaultSize;
        this.title = defaultTitle;
        this.isCreatable = isCreatable;
    }

    public int getDefaultSize() {
        return this.size;
    }

    @NotNull
    public String getDefaultTitle() {
        return this.title;
    }

    public boolean isCreatable() {
        return this.isCreatable;
    }

    public static enum SlotType {
        RESULT,
        CRAFTING,
        ARMOR,
        CONTAINER,
        QUICKBAR,
        OUTSIDE,
        FUEL;

    }
}

