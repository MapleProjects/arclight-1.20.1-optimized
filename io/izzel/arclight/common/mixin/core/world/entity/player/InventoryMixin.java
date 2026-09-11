/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.player;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Inventory.class})
public abstract class InventoryMixin
implements Container,
IInventoryBridge,
PlayerInventoryBridge {
    @Shadow
    @Final
    public NonNullList<ItemStack> f_35974_;
    @Shadow
    @Final
    public NonNullList<ItemStack> f_35976_;
    @Shadow
    @Final
    public NonNullList<ItemStack> f_35975_;
    @Shadow
    @Final
    private List<NonNullList<ItemStack>> f_35979_;
    @Shadow
    @Final
    public Player f_35978_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = -1;

    @Shadow
    protected abstract boolean m_36014_(ItemStack var1, ItemStack var2);

    public int canHold(ItemStack stack) {
        int remains = stack.m_41613_();
        for (int i = 0; i < this.f_35974_.size(); ++i) {
            ItemStack slot = this.m_8020_(i);
            if (slot.m_41619_()) {
                return stack.m_41613_();
            }
            if (this.m_36014_(slot, stack)) {
                remains -= (slot.m_41741_() < this.m_6893_() ? slot.m_41741_() : this.m_6893_()) - slot.m_41613_();
            }
            if (remains > 0) continue;
            return stack.m_41613_();
        }
        ItemStack offhandItemStack = this.m_8020_(this.f_35974_.size() + this.f_35975_.size());
        if (this.m_36014_(offhandItemStack, stack)) {
            remains -= (offhandItemStack.m_41741_() < this.m_6893_() ? offhandItemStack.m_41741_() : this.m_6893_()) - offhandItemStack.m_41613_();
        }
        if (remains <= 0) {
            return stack.m_41613_();
        }
        return stack.m_41613_() - remains;
    }

    @Override
    public int bridge$canHold(ItemStack stack) {
        return this.canHold(stack);
    }

    public List<ItemStack> getArmorContents() {
        return this.f_35975_;
    }

    @Override
    public List<ItemStack> getContents() {
        ArrayList<ItemStack> combined = new ArrayList<ItemStack>(this.f_35974_.size() + this.f_35976_.size() + this.f_35975_.size());
        for (List list : this.f_35979_) {
            combined.addAll(list);
        }
        return combined;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        this.transaction.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        this.transaction.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.transaction;
    }

    @Override
    public InventoryHolder getOwner() {
        return ((PlayerEntityBridge)this.f_35978_).bridge$getBukkitEntity();
    }

    @Override
    public void setOwner(InventoryHolder owner) {
    }

    public int m_6893_() {
        if (this.maxStack != -1) {
            return this.maxStack;
        }
        return super.m_6893_();
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }

    @Override
    public Location getLocation() {
        return ((PlayerEntityBridge)this.f_35978_).bridge$getBukkitEntity().getLocation();
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

