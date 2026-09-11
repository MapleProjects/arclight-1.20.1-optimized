/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.CompoundContainer
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={CompoundContainer.class})
public abstract class CompoundContainerMixin
implements IInventoryBridge,
Container {
    @Shadow
    @Final
    public Container f_18910_;
    @Shadow
    @Final
    public Container f_18911_;
    private List<HumanEntity> transactions = new ArrayList<HumanEntity>();

    @Override
    public List<ItemStack> getContents() {
        int size = this.m_6643_();
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>(size);
        for (int i = 0; i < size; ++i) {
            ret.add(this.m_8020_(i));
        }
        return ret;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        ((IInventoryBridge)this.f_18910_).onOpen(who);
        ((IInventoryBridge)this.f_18911_).onOpen(who);
        this.transactions.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        ((IInventoryBridge)this.f_18910_).onClose(who);
        ((IInventoryBridge)this.f_18911_).onClose(who);
        this.transactions.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.transactions;
    }

    @Override
    public InventoryHolder getOwner() {
        return null;
    }

    @Override
    public void setOwner(InventoryHolder owner) {
    }

    public int m_6893_() {
        return Math.min(this.f_18910_.m_6893_(), this.f_18911_.m_6893_());
    }

    @Override
    public void setMaxStackSize(int size) {
        ((IInventoryBridge)this.f_18910_).setMaxStackSize(size);
        ((IInventoryBridge)this.f_18911_).setMaxStackSize(size);
    }

    @Override
    public Location getLocation() {
        return ((IInventoryBridge)this.f_18910_).getLocation();
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }
}

