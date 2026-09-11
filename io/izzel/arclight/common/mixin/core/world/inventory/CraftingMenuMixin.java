/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.inventory.CraftingMenu
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.RepairItemRecipe
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.CraftingInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.ContainerBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.Optional;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftingMenu.class})
public abstract class CraftingMenuMixin
extends AbstractContainerMenuMixin
implements PosContainerBridge {
    @Mutable
    @Shadow
    @Final
    private CraftingContainer f_39348_;
    @Shadow
    @Final
    private ResultContainer f_39349_;
    private CraftInventoryView bukkitEntity;
    private Inventory playerInventory;
    private static transient boolean arclight$isRepair;

    @Override
    @Accessor(value="access")
    public abstract ContainerLevelAccess bridge$getWorldPos();

    @Inject(method={"stillValid"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$unreachable(Player playerIn, CallbackInfoReturnable<Boolean> cir) {
        if (!this.bridge$isCheckReachable()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"slotsChanged"}, at={@At(value="HEAD")})
    public void arclight$capture(Container inventoryIn, CallbackInfo ci) {
        ArclightCaptures.captureWorkbenchContainer((AbstractContainerMenu)((CraftingMenu)this));
    }

    @Redirect(method={"slotChangedCraftingGrid"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Optional;isPresent()Z"))
    private static boolean arclight$testRepair(Optional<?> optional) {
        arclight$isRepair = optional.orElse(null) instanceof RepairItemRecipe;
        return optional.isPresent();
    }

    @ModifyVariable(method={"slotChangedCraftingGrid"}, ordinal=0, at=@At(value="INVOKE", target="Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private static ItemStack arclight$preCraft(ItemStack stack, AbstractContainerMenu container, Level level, Player player, CraftingContainer craftingContainer, ResultContainer resultContainer) {
        return CraftEventFactory.callPreCraftEvent((Container)craftingContainer, (Container)resultContainer, stack, ((ContainerBridge)container).bridge$getBukkitView(), arclight$isRepair);
    }

    @Inject(method={"<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V"}, at={@At(value="RETURN")})
    public void arclight$init(int i, Inventory playerInventory, ContainerLevelAccess callable, CallbackInfo ci) {
        ((CraftingInventoryBridge)this.f_39348_).bridge$setOwner(playerInventory.f_35978_);
        ((CraftingInventoryBridge)this.f_39348_).bridge$setResultInventory((Container)this.f_39349_);
        this.playerInventory = playerInventory;
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryCrafting inventory = new CraftInventoryCrafting((Container)this.f_39348_, (Container)this.f_39349_);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }
}

