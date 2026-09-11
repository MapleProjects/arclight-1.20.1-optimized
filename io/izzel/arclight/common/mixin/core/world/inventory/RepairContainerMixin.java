/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.AnvilMenu
 *  net.minecraft.world.inventory.DataSlot
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.util.IWorldPosCallableBridge;
import io.izzel.arclight.common.mixin.core.world.inventory.ItemCombinerMixin;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAnvil;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AnvilMenu.class})
public abstract class RepairContainerMixin
extends ItemCombinerMixin {
    @Shadow
    @Final
    public DataSlot f_39002_;
    @Shadow
    public int f_39000_;
    @Shadow
    public String f_39001_;
    public int cancelThisBySettingCostToMaximum = 40;
    public int maximumRenameCostThreshold = 40;
    public int maximumAllowedRenameCost = 39;
    public int maximumRepairCost = 40;
    private CraftInventoryView bukkitEntity;

    @Shadow
    public static int m_39025_(int oldRepairCost) {
        return 0;
    }

    @Redirect(method={"createResult"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/inventory/ResultContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void arclight$callInventoryEvent(ResultContainer instance, int slot, ItemStack itemStack) {
        CraftEventFactory.callPrepareAnvilEvent(this.getBukkitView(), itemStack);
    }

    @Inject(method={"createResult"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/inventory/AnvilMenu;broadcastChanges()V")})
    private void arclight$sendData(CallbackInfo ci) {
        this.m_150429_();
    }

    @ModifyConstant(method={"createResult"}, constant={@Constant(intValue=40)}, require=0)
    private int arclight$maximumRepairCost(int raw) {
        return raw - 40 + this.maximumRepairCost;
    }

    @ModifyConstant(method={"createResult"}, constant={@Constant(intValue=39)}, require=0)
    private int arclight$maximumRenameCost(int raw) {
        return raw - 40 + this.maximumRepairCost;
    }

    @Override
    public CraftInventoryView getBukkitView() {
        if (this.bukkitEntity != null) {
            return this.bukkitEntity;
        }
        CraftInventoryAnvil inventory = new CraftInventoryAnvil(((IWorldPosCallableBridge)this.f_39770_).bridge$getLocation(), this.f_39769_, (Container)this.f_39768_, (AnvilMenu)this);
        this.bukkitEntity = new CraftInventoryView(((PlayerEntityBridge)this.f_39771_).bridge$getBukkitEntity(), inventory, (AbstractContainerMenu)this);
        return this.bukkitEntity;
    }
}

