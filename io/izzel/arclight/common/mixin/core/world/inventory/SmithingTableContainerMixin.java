/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.inventory.SmithingMenu
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.util.IWorldPosCallableBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.ItemCombinerMixin;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventorySmithing;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={SmithingMenu.class})
public abstract class SmithingTableContainerMixin
extends ItemCombinerMixin {
    private CraftInventoryView bukkitEntity;

    @Redirect(method={"createResult"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void arclight$prepareSmithing(ResultContainer craftResultInventory, int index, ItemStack stack) {
        CraftEventFactory.callPrepareSmithingEvent(this.getBukkitView(), stack);
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventorySmithing inventory = new CraftInventorySmithing(((IWorldPosCallableBridge)this.f_39770_).bridge$getLocation(), this.f_39769_, this.f_39768_);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.f_39771_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)((SmithingMenu)this));
        return this.bukkitEntity;
    }
}

