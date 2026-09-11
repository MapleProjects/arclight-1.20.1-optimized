/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.inventory.FurnaceResultSlot
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.tileentity.AbstractFurnaceTileEntityBridge;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={FurnaceResultSlot.class})
public class FurnaceResultSlotMixin {
    @Shadow
    private int f_39540_;

    @Redirect(method={"checkTakeAchievements(Lnet/minecraft/world/item/ItemStack;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;awardUsedRecipesAndPopExperience(Lnet/minecraft/server/level/ServerPlayer;)V"))
    public void arclight$furnaceDropExp(AbstractFurnaceBlockEntity furnace, ServerPlayer player, ItemStack stack) {
        ((AbstractFurnaceTileEntityBridge)furnace).bridge$dropExp(player, stack, this.f_39540_);
    }
}

