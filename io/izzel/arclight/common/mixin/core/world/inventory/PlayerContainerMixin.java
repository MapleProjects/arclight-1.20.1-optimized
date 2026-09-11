/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.inventory.ResultContainer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.CraftingInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={InventoryMenu.class})
public abstract class PlayerContainerMixin
extends AbstractContainerMenuMixin {
    @Shadow
    @Final
    private CraftingContainer f_39701_;
    @Shadow
    @Final
    private ResultContainer f_39702_;
    private CraftInventoryView bukkitEntity;
    private Inventory playerInventory;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void arclight$init(Inventory playerInventory, boolean localWorld, Player playerIn, CallbackInfo ci) {
        this.playerInventory = playerInventory;
        ((CraftingInventoryBridge)this.f_39701_).bridge$setOwner(playerInventory.f_35978_);
        ((CraftingInventoryBridge)this.f_39701_).bridge$setResultInventory((Container)this.f_39702_);
        this.setTitle((Component)Component.m_237115_((String)"container.crafting"));
    }

    @Inject(method={"slotsChanged"}, at={@At(value="HEAD")})
    public void arclight$captureContainer(Container inventoryIn, CallbackInfo ci) {
        ArclightCaptures.captureWorkbenchContainer((AbstractContainerMenu)this);
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryCrafting inventory = new CraftInventoryCrafting((Container)this.f_39701_, (Container)this.f_39702_);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }
}

