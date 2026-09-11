/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.inventory.MerchantContainer
 *  net.minecraft.world.inventory.PlayerEnderChestContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.ComposterBlock$EmptyContainer
 *  net.minecraft.world.level.block.ComposterBlock$InputContainer
 *  net.minecraft.world.level.block.ComposterBlock$OutputContainer
 *  net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.BarrelBlockEntity
 *  net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 *  net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity
 *  net.minecraft.world.level.block.entity.DispenserBlockEntity
 *  net.minecraft.world.level.block.entity.DropperBlockEntity
 *  net.minecraft.world.level.block.entity.Hopper
 *  net.minecraft.world.level.block.entity.JukeboxBlockEntity
 *  net.minecraft.world.level.block.entity.LecternBlockEntity$1
 *  net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
 *  net.minecraft.world.level.block.entity.SmokerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAnvil;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryBeacon;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCartography;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCustom;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryEnchanting;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryGrindstone;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryLoom;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventorySmithing;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryStonecutter;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.InventoryIterator;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLegacy;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class CraftInventory
implements Inventory {
    protected final Container inventory;

    public CraftInventory(Container inventory) {
        this.inventory = inventory;
    }

    public Container getInventory() {
        return this.inventory;
    }

    @Override
    public int getSize() {
        return this.getInventory().m_6643_();
    }

    @Override
    public ItemStack getItem(int index) {
        net.minecraft.world.item.ItemStack item = this.getInventory().m_8020_(index);
        return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
    }

    protected ItemStack[] asCraftMirror(List<net.minecraft.world.item.ItemStack> mcItems) {
        int size = mcItems.size();
        ItemStack[] items = new ItemStack[size];
        int i = 0;
        while (i < size) {
            net.minecraft.world.item.ItemStack mcItem = mcItems.get(i);
            items[i] = mcItem.m_41619_() ? null : CraftItemStack.asCraftMirror(mcItem);
            ++i;
        }
        return items;
    }

    @Override
    public ItemStack[] getStorageContents() {
        return this.getContents();
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        this.setContents(items);
    }

    @Override
    public ItemStack[] getContents() {
        List mcItems = this.getInventory().getContents();
        return this.asCraftMirror(mcItems);
    }

    @Override
    public void setContents(ItemStack[] items) {
        Preconditions.checkArgument((items.length <= this.getSize() ? 1 : 0) != 0, (String)"Invalid inventory size (%s); expected %s or less", (int)items.length, (int)this.getSize());
        int i = 0;
        while (i < this.getSize()) {
            if (i >= items.length) {
                this.setItem(i, null);
            } else {
                this.setItem(i, items[i]);
            }
            ++i;
        }
    }

    @Override
    public void setItem(int index, ItemStack item) {
        this.getInventory().m_6836_(index, CraftItemStack.asNMSCopy(item));
    }

    @Override
    public boolean contains(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        ItemStack[] itemStackArray = this.getStorageContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack item = itemStackArray[n2];
            if (item != null && item.getType() == material) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item) {
        if (item == null) {
            return false;
        }
        ItemStack[] itemStackArray = this.getStorageContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack i = itemStackArray[n2];
            if (item.equals(i)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean contains(Material material, int amount) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        if (amount <= 0) {
            return true;
        }
        ItemStack[] itemStackArray = this.getStorageContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack item = itemStackArray[n2];
            if (item != null && item.getType() == material && (amount -= item.getAmount()) <= 0) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        if (item == null) {
            return false;
        }
        if (amount <= 0) {
            return true;
        }
        ItemStack[] itemStackArray = this.getStorageContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack i = itemStackArray[n2];
            if (item.equals(i) && --amount <= 0) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        if (item == null) {
            return false;
        }
        if (amount <= 0) {
            return true;
        }
        ItemStack[] itemStackArray = this.getStorageContents();
        int n = itemStackArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack i = itemStackArray[n2];
            if (item.isSimilar(i) && (amount -= i.getAmount()) <= 0) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public HashMap<Integer, ItemStack> all(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        ItemStack[] inventory = this.getStorageContents();
        int i = 0;
        while (i < inventory.length) {
            ItemStack item = inventory[i];
            if (item != null && item.getType() == material) {
                slots.put(i, item);
            }
            ++i;
        }
        return slots;
    }

    public HashMap<Integer, ItemStack> all(ItemStack item) {
        HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
        if (item != null) {
            ItemStack[] inventory = this.getStorageContents();
            int i = 0;
            while (i < inventory.length) {
                if (item.equals(inventory[i])) {
                    slots.put(i, inventory[i]);
                }
                ++i;
            }
        }
        return slots;
    }

    @Override
    public int first(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        ItemStack[] inventory = this.getStorageContents();
        int i = 0;
        while (i < inventory.length) {
            ItemStack item = inventory[i];
            if (item != null && item.getType() == material) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public int first(ItemStack item) {
        return this.first(item, true);
    }

    private int first(ItemStack item, boolean withAmount) {
        if (item == null) {
            return -1;
        }
        ItemStack[] inventory = this.getStorageContents();
        int i = 0;
        while (i < inventory.length) {
            if (inventory[i] != null && (withAmount ? item.equals(inventory[i]) : item.isSimilar(inventory[i]))) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public int firstEmpty() {
        ItemStack[] inventory = this.getStorageContents();
        int i = 0;
        while (i < inventory.length) {
            if (inventory[i] == null) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.inventory.m_7983_();
    }

    public int firstPartial(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        ItemStack[] inventory = this.getStorageContents();
        int i = 0;
        while (i < inventory.length) {
            ItemStack item = inventory[i];
            if (item != null && item.getType() == material && item.getAmount() < item.getMaxStackSize()) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    private int firstPartial(ItemStack item) {
        ItemStack[] inventory = this.getStorageContents();
        CraftItemStack filteredItem = CraftItemStack.asCraftCopy(item);
        if (item == null) {
            return -1;
        }
        int i = 0;
        while (i < inventory.length) {
            ItemStack cItem = inventory[i];
            if (cItem != null && cItem.getAmount() < cItem.getMaxStackSize() && cItem.isSimilar(filteredItem)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack ... items) {
        Preconditions.checkArgument((items != null ? 1 : 0) != 0, (Object)"items cannot be null");
        HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();
        int i = 0;
        while (i < items.length) {
            ItemStack item = items[i];
            Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
            while (true) {
                int maxAmount;
                int partialAmount;
                int firstPartial;
                if ((firstPartial = this.firstPartial(item)) == -1) {
                    int firstFree = this.firstEmpty();
                    if (firstFree == -1) {
                        leftover.put(i, item);
                        break;
                    }
                    if (item.getAmount() > this.getMaxItemStack()) {
                        CraftItemStack stack = CraftItemStack.asCraftCopy(item);
                        stack.setAmount(this.getMaxItemStack());
                        this.setItem(firstFree, stack);
                        item.setAmount(item.getAmount() - this.getMaxItemStack());
                        continue;
                    }
                    this.setItem(firstFree, item);
                    break;
                }
                ItemStack partialItem = this.getItem(firstPartial);
                int amount = item.getAmount();
                if (amount + (partialAmount = partialItem.getAmount()) <= (maxAmount = partialItem.getMaxStackSize())) {
                    partialItem.setAmount(amount + partialAmount);
                    this.setItem(firstPartial, partialItem);
                    break;
                }
                partialItem.setAmount(maxAmount);
                this.setItem(firstPartial, partialItem);
                item.setAmount(amount + partialAmount - maxAmount);
            }
            ++i;
        }
        return leftover;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack ... items) {
        Preconditions.checkArgument((items != null ? 1 : 0) != 0, (Object)"items cannot be null");
        HashMap<Integer, ItemStack> leftover = new HashMap<Integer, ItemStack>();
        int i = 0;
        while (i < items.length) {
            ItemStack item = items[i];
            Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
            int toDelete = item.getAmount();
            do {
                int first;
                if ((first = this.first(item, false)) == -1) {
                    item.setAmount(toDelete);
                    leftover.put(i, item);
                    break;
                }
                ItemStack itemStack = this.getItem(first);
                int amount = itemStack.getAmount();
                if (amount <= toDelete) {
                    toDelete -= amount;
                    this.clear(first);
                    continue;
                }
                itemStack.setAmount(amount - toDelete);
                this.setItem(first, itemStack);
                toDelete = 0;
            } while (toDelete > 0);
            ++i;
        }
        return leftover;
    }

    private int getMaxItemStack() {
        return this.getInventory().m_6893_();
    }

    @Override
    public void remove(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        material = CraftLegacy.fromLegacy(material);
        ItemStack[] items = this.getStorageContents();
        int i = 0;
        while (i < items.length) {
            if (items[i] != null && items[i].getType() == material) {
                this.clear(i);
            }
            ++i;
        }
    }

    @Override
    public void remove(ItemStack item) {
        ItemStack[] items = this.getStorageContents();
        int i = 0;
        while (i < items.length) {
            if (items[i] != null && items[i].equals(item)) {
                this.clear(i);
            }
            ++i;
        }
    }

    @Override
    public void clear(int index) {
        this.setItem(index, null);
    }

    @Override
    public void clear() {
        int i = 0;
        while (i < this.getSize()) {
            this.clear(i);
            ++i;
        }
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return new InventoryIterator(this);
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        if (index < 0) {
            index += this.getSize() + 1;
        }
        return new InventoryIterator(this, index);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.inventory.getViewers();
    }

    @Override
    public InventoryType getType() {
        if (this.inventory instanceof CraftingContainer) {
            return this.inventory.m_6643_() >= 9 ? InventoryType.WORKBENCH : InventoryType.CRAFTING;
        }
        if (this.inventory instanceof net.minecraft.world.entity.player.Inventory) {
            return InventoryType.PLAYER;
        }
        if (this.inventory instanceof DropperBlockEntity) {
            return InventoryType.DROPPER;
        }
        if (this.inventory instanceof DispenserBlockEntity) {
            return InventoryType.DISPENSER;
        }
        if (this.inventory instanceof BlastFurnaceBlockEntity) {
            return InventoryType.BLAST_FURNACE;
        }
        if (this.inventory instanceof SmokerBlockEntity) {
            return InventoryType.SMOKER;
        }
        if (this.inventory instanceof AbstractFurnaceBlockEntity) {
            return InventoryType.FURNACE;
        }
        if (this instanceof CraftInventoryEnchanting) {
            return InventoryType.ENCHANTING;
        }
        if (this.inventory instanceof BrewingStandBlockEntity) {
            return InventoryType.BREWING;
        }
        if (this.inventory instanceof CraftInventoryCustom.MinecraftInventory) {
            return ((CraftInventoryCustom.MinecraftInventory)this.inventory).getType();
        }
        if (this.inventory instanceof PlayerEnderChestContainer) {
            return InventoryType.ENDER_CHEST;
        }
        if (this.inventory instanceof MerchantContainer) {
            return InventoryType.MERCHANT;
        }
        if (this instanceof CraftInventoryBeacon) {
            return InventoryType.BEACON;
        }
        if (this instanceof CraftInventoryAnvil) {
            return InventoryType.ANVIL;
        }
        if (this instanceof CraftInventorySmithing) {
            return InventoryType.SMITHING;
        }
        if (this.inventory instanceof Hopper) {
            return InventoryType.HOPPER;
        }
        if (this.inventory instanceof ShulkerBoxBlockEntity) {
            return InventoryType.SHULKER_BOX;
        }
        if (this.inventory instanceof BarrelBlockEntity) {
            return InventoryType.BARREL;
        }
        if (this.inventory instanceof LecternBlockEntity.1) {
            return InventoryType.LECTERN;
        }
        if (this.inventory instanceof ChiseledBookShelfBlockEntity) {
            return InventoryType.CHISELED_BOOKSHELF;
        }
        if (this instanceof CraftInventoryLoom) {
            return InventoryType.LOOM;
        }
        if (this instanceof CraftInventoryCartography) {
            return InventoryType.CARTOGRAPHY;
        }
        if (this instanceof CraftInventoryGrindstone) {
            return InventoryType.GRINDSTONE;
        }
        if (this instanceof CraftInventoryStonecutter) {
            return InventoryType.STONECUTTER;
        }
        if (this.inventory instanceof ComposterBlock.EmptyContainer || this.inventory instanceof ComposterBlock.InputContainer || this.inventory instanceof ComposterBlock.OutputContainer) {
            return InventoryType.COMPOSTER;
        }
        if (this.inventory instanceof JukeboxBlockEntity) {
            return InventoryType.JUKEBOX;
        }
        return InventoryType.CHEST;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.inventory.getOwner();
    }

    @Override
    public int getMaxStackSize() {
        return this.inventory.m_6893_();
    }

    @Override
    public void setMaxStackSize(int size) {
        this.inventory.setMaxStackSize(size);
    }

    public int hashCode() {
        return this.inventory.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof CraftInventory && ((CraftInventory)obj).inventory.equals(this.inventory);
    }

    @Override
    public Location getLocation() {
        return this.inventory.getLocation();
    }
}

