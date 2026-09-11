/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.MerchantContainer
 *  net.minecraft.world.inventory.MerchantMenu
 *  net.minecraft.world.item.trading.Merchant
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryMerchant;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={MerchantMenu.class})
public abstract class MerchantContainerMixin
extends AbstractContainerMenuMixin {
    @Shadow
    @Final
    private Merchant f_40027_;
    @Shadow
    @Final
    private MerchantContainer f_40028_;
    private CraftInventoryView bukkitEntity = null;
    private Inventory playerInventory;

    @Inject(method={"<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/item/trading/Merchant;)V"}, at={@At(value="RETURN")})
    public void arclight$init(int id, Inventory playerInventoryIn, Merchant merchantIn, CallbackInfo ci) {
        this.playerInventory = playerInventoryIn;
    }

    @Inject(method={"playTradeSound"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$returnIfFail(CallbackInfo ci) {
        if (!(this.f_40027_ instanceof Entity)) {
            ci.cancel();
        }
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity == null) {
            this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), new CraftInventoryMerchant(this.f_40027_, this.f_40028_), (AbstractContainerMenu)this);
        }
        return this.bukkitEntity;
    }
}

