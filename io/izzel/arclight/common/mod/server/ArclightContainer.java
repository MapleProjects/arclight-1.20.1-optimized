/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.server;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

public class ArclightContainer {
    public static InventoryView createInvView(AbstractContainerMenu container) {
        Player containerOwner = ArclightCaptures.getContainerOwner();
        CraftInventory viewing = ArclightContainer.createInv(containerOwner, container);
        return new CraftInventoryView(((PlayerEntityBridge)containerOwner).bridge$getBukkitEntity(), viewing, container);
    }

    public static CraftInventory createInv(Player containerOwner, AbstractContainerMenu container) {
        return new CraftInventory(new ContainerInvWrapper(container, containerOwner));
    }

    public static SimpleContainer copyOf(SimpleContainer container) {
        SimpleContainer copy = new SimpleContainer(container.m_6643_());
        for (int slot = 0; slot < container.m_6643_(); ++slot) {
            copy.f_19147_.set(slot, (Object)((ItemStack)container.f_19147_.get(slot)).m_41777_());
        }
        return copy;
    }

    private static class ContainerInvWrapper
    implements Container,
    IInventoryBridge {
        private final AbstractContainerMenu container;
        private InventoryHolder owner;
        private final List<HumanEntity> viewers = new ArrayList<HumanEntity>();

        public ContainerInvWrapper(AbstractContainerMenu container, Player owner) {
            this.container = container;
            this.owner = ((PlayerEntityBridge)owner).bridge$getBukkitEntity();
        }

        public int m_6643_() {
            return this.container.f_38841_.size();
        }

        public boolean m_7983_() {
            for (Slot slot : this.container.f_38839_) {
                if (slot.m_7993_().m_41619_()) continue;
                return false;
            }
            return true;
        }

        @NotNull
        public ItemStack m_8020_(int index) {
            if (index >= this.m_6643_()) {
                return ItemStack.f_41583_;
            }
            return this.container.m_38853_(index).m_7993_();
        }

        @NotNull
        public ItemStack m_7407_(int index, int count) {
            if (index >= this.m_6643_()) {
                return ItemStack.f_41583_;
            }
            return this.container.m_38853_(index).m_6201_(count);
        }

        @NotNull
        public ItemStack m_8016_(int index) {
            if (index >= this.m_6643_()) {
                return ItemStack.f_41583_;
            }
            return this.container.m_38853_(index).m_6201_(Integer.MAX_VALUE);
        }

        public void m_6836_(int index, @NotNull ItemStack stack) {
            if (index >= this.m_6643_()) {
                return;
            }
            this.container.m_38853_(index).m_5852_(stack);
        }

        public int m_6893_() {
            if (this.m_6643_() <= 0) {
                return 0;
            }
            return this.container.m_38853_(0).m_6641_();
        }

        public void m_6596_() {
        }

        public boolean m_6542_(@NotNull Player player) {
            return this.container.m_6875_(player);
        }

        public void m_6211_() {
            for (Slot slot : this.container.f_38839_) {
                slot.m_6201_(Integer.MAX_VALUE);
            }
        }

        @Override
        public List<ItemStack> getContents() {
            this.container.m_38946_();
            return this.container.f_38841_.subList(0, this.m_6643_());
        }

        @Override
        public void onOpen(CraftHumanEntity who) {
            this.viewers.add(who);
        }

        @Override
        public void onClose(CraftHumanEntity who) {
            this.viewers.remove(who);
        }

        @Override
        public List<HumanEntity> getViewers() {
            return this.viewers;
        }

        @Override
        public InventoryHolder getOwner() {
            return this.owner;
        }

        @Override
        public void setOwner(InventoryHolder owner) {
            this.owner = owner;
        }

        @Override
        public void setMaxStackSize(int size) {
        }

        @Override
        public Location getLocation() {
            if (this.container instanceof PosContainerBridge) {
                return ((PosContainerBridge)this.container).bridge$getWorldLocation();
            }
            return null;
        }

        @Override
        public Recipe<?> getCurrentRecipe() {
            return null;
        }

        @Override
        public void setCurrentRecipe(Recipe<?> recipe) {
        }
    }
}

