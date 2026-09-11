/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

public class CraftInventoryCustom
extends CraftInventory {
    public CraftInventoryCustom(InventoryHolder owner, InventoryType type) {
        super(new MinecraftInventory(owner, type));
    }

    public CraftInventoryCustom(InventoryHolder owner, InventoryType type, String title) {
        super(new MinecraftInventory(owner, type, title));
    }

    public CraftInventoryCustom(InventoryHolder owner, int size) {
        super(new MinecraftInventory(owner, size));
    }

    public CraftInventoryCustom(InventoryHolder owner, int size, String title) {
        super(new MinecraftInventory(owner, size, title));
    }

    static class MinecraftInventory
    implements Container {
        private final NonNullList<ItemStack> items;
        private int maxStack = 64;
        private final List<HumanEntity> viewers;
        private final String title;
        private InventoryType type;
        private final InventoryHolder owner;

        public MinecraftInventory(InventoryHolder owner, InventoryType type) {
            this(owner, type.getDefaultSize(), type.getDefaultTitle());
            this.type = type;
        }

        public MinecraftInventory(InventoryHolder owner, InventoryType type, String title) {
            this(owner, type.getDefaultSize(), title);
            this.type = type;
        }

        public MinecraftInventory(InventoryHolder owner, int size) {
            this(owner, size, "Chest");
        }

        public MinecraftInventory(InventoryHolder owner, int size, String title) {
            Preconditions.checkArgument((title != null ? 1 : 0) != 0, (Object)"title cannot be null");
            this.items = NonNullList.m_122780_((int)size, (Object)ItemStack.f_41583_);
            this.title = title;
            this.viewers = new ArrayList<HumanEntity>();
            this.owner = owner;
            this.type = InventoryType.CHEST;
        }

        public int m_6643_() {
            return this.items.size();
        }

        public ItemStack m_8020_(int i) {
            return (ItemStack)this.items.get(i);
        }

        public ItemStack m_7407_(int i, int j) {
            ItemStack result;
            ItemStack stack = this.m_8020_(i);
            if (stack == ItemStack.f_41583_) {
                return stack;
            }
            if (stack.m_41613_() <= j) {
                this.m_6836_(i, ItemStack.f_41583_);
                result = stack;
            } else {
                result = CraftItemStack.copyNMSStack(stack, j);
                stack.m_41774_(j);
            }
            this.m_6596_();
            return result;
        }

        public ItemStack m_8016_(int i) {
            ItemStack result;
            ItemStack stack = this.m_8020_(i);
            if (stack == ItemStack.f_41583_) {
                return stack;
            }
            if (stack.m_41613_() <= 1) {
                this.m_6836_(i, null);
                result = stack;
            } else {
                result = CraftItemStack.copyNMSStack(stack, 1);
                stack.m_41774_(1);
            }
            return result;
        }

        public void m_6836_(int i, ItemStack itemstack) {
            this.items.set(i, (Object)itemstack);
            if (itemstack != ItemStack.f_41583_ && this.m_6893_() > 0 && itemstack.m_41613_() > this.m_6893_()) {
                itemstack.m_41764_(this.m_6893_());
            }
        }

        public int m_6893_() {
            return this.maxStack;
        }

        public void setMaxStackSize(int size) {
            this.maxStack = size;
        }

        public void m_6596_() {
        }

        public boolean m_6542_(Player entityhuman) {
            return true;
        }

        public List<ItemStack> getContents() {
            return this.items;
        }

        public void onOpen(CraftHumanEntity who) {
            this.viewers.add(who);
        }

        public void onClose(CraftHumanEntity who) {
            this.viewers.remove(who);
        }

        public List<HumanEntity> getViewers() {
            return this.viewers;
        }

        public InventoryType getType() {
            return this.type;
        }

        public InventoryHolder getOwner() {
            return this.owner;
        }

        public boolean m_7013_(int i, ItemStack itemstack) {
            return true;
        }

        public void m_5856_(Player entityHuman) {
        }

        public void m_5785_(Player entityHuman) {
        }

        public void m_6211_() {
            this.items.clear();
        }

        public Location getLocation() {
            return null;
        }

        public String getTitle() {
            return this.title;
        }

        public boolean m_7983_() {
            ItemStack itemstack;
            Iterator iterator = this.items.iterator();
            do {
                if (iterator.hasNext()) continue;
                return true;
            } while ((itemstack = (ItemStack)iterator.next()).m_41619_());
            return false;
        }
    }
}

