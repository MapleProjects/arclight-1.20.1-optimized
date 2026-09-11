/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.TransientCraftingContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.CraftingInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={TransientCraftingContainer.class})
public abstract class CraftingInventoryMixin
implements CraftingInventoryBridge,
Container {
    @Shadow
    @Final
    private NonNullList<ItemStack> f_286951_;
    @Shadow
    @Final
    public AbstractContainerMenu f_286998_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private Recipe<?> currentRecipe;
    public Container resultInventory;
    private Player owner;
    private InventoryHolder bukkitOwner;
    private int maxStack = 64;

    public void arclight$constructor(AbstractContainerMenu eventHandlerIn, int width, int height) {
        throw new RuntimeException();
    }

    public void arclight$constructor(AbstractContainerMenu eventHandlerIn, int width, int height, Player owner) {
        this.arclight$constructor(eventHandlerIn, width, height);
        this.owner = owner;
    }

    public InventoryType getInvType() {
        return this.f_286951_.size() == 4 ? InventoryType.CRAFTING : InventoryType.WORKBENCH;
    }

    @Override
    public void bridge$setResultInventory(Container resultInventory) {
        this.resultInventory = resultInventory;
    }

    @Override
    public void bridge$setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public List<ItemStack> getContents() {
        return this.f_286951_;
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
        if (this.bukkitOwner == null) {
            this.bukkitOwner = this.owner == null ? null : ((PlayerEntityBridge)this.owner).bridge$getBukkitEntity();
        }
        return this.bukkitOwner;
    }

    @Override
    public void setOwner(InventoryHolder owner) {
        this.bukkitOwner = owner;
    }

    public int m_6893_() {
        if (this.maxStack == 0) {
            this.maxStack = 64;
        }
        return this.maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
        ((IInventoryBridge)this.resultInventory).setMaxStackSize(size);
    }

    @Override
    public Location getLocation() {
        return this.f_286998_ instanceof PosContainerBridge ? ((PosContainerBridge)this.f_286998_).bridge$getWorldLocation() : ((PlayerEntityBridge)this.owner).bridge$getBukkitEntity().getLocation();
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return this.currentRecipe;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
        this.currentRecipe = recipe;
    }
}

