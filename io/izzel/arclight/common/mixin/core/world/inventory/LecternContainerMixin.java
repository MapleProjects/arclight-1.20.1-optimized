/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.LecternMenu
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.LecternContainerBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.LecternMenu;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryLectern;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LecternMenu.class})
public abstract class LecternContainerMixin
extends AbstractContainerMenuMixin
implements LecternContainerBridge {
    @Shadow
    @Final
    private Container f_39819_;
    private CraftInventoryView bukkitEntity;
    private Inventory playerInventory;

    public void arclight$constructor(int i) {
        throw new RuntimeException();
    }

    public void arclight$constructor(int i, Container inventory, ContainerData intArray) {
        throw new RuntimeException();
    }

    public void arclight$constructor(int i, Inventory playerInventory) {
        this.arclight$constructor(i);
        this.playerInventory = playerInventory;
    }

    public void arclight$constructor(int i, Container inventory, ContainerData intArray, Inventory playerInventory) {
        this.arclight$constructor(i, inventory, intArray);
        this.playerInventory = playerInventory;
    }

    @Inject(method={"clickMenuButton"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/Container;removeItemNoUpdate(I)Lnet/minecraft/world/item/ItemStack;")})
    public void arclight$takeBook(Player playerIn, int id, CallbackInfoReturnable<Boolean> cir) {
        PlayerTakeLecternBookEvent event = new PlayerTakeLecternBookEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), ((CraftInventoryLectern)this.getBukkitView().getTopInventory()).getHolder());
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"stillValid"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$unreachable(Player playerIn, CallbackInfoReturnable<Boolean> cir) {
        if (!this.bridge$isCheckReachable()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryLectern inventory = new CraftInventoryLectern(this.f_39819_);
        this.bukkitEntity = new CraftInventoryView(((ServerPlayerEntityBridge)this.playerInventory.f_35978_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }

    @Override
    public void bridge$setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}

