/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.GrindstoneMenu
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryGrindstone;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GrindstoneMenu.class})
public abstract class GrindstoneContainerMixin
extends AbstractContainerMenuMixin
implements PosContainerBridge {
    @Shadow
    @Final
    Container f_39560_;
    @Shadow
    @Final
    private Container f_39559_;
    @Shadow
    @Final
    private ContainerLevelAccess f_39561_;
    private CraftInventoryView bukkitEntity = null;
    private Inventory playerInventory;

    @Inject(method={"<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V"}, at={@At(value="RETURN")})
    public void arclight$init(int windowIdIn, Inventory playerInventory, ContainerLevelAccess worldPosCallableIn, CallbackInfo ci) {
        this.playerInventory = playerInventory;
    }

    @Redirect(method={"createResult"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/Container;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void arclight$prepareEvent(Container instance, int i, ItemStack itemStack) {
        CraftEventFactory.callPrepareGrindstoneEvent(this.getBukkitView(), itemStack);
    }

    @Inject(method={"createResult"}, at={@At(value="INVOKE", ordinal=3, target="Lnet/minecraft/world/inventory/GrindstoneMenu;broadcastChanges()V")})
    private void arclight$sync(CallbackInfo ci) {
        this.m_150429_();
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryGrindstone inventory = new CraftInventoryGrindstone(this.f_39560_, this.f_39559_);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }

    @Override
    public ContainerLevelAccess bridge$getWorldPos() {
        return this.f_39561_;
    }
}

