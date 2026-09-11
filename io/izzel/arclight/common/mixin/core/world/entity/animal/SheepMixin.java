/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.inventory.ResultContainer
 *  net.minecraft.world.item.DyeColor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.inventory.CraftingInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.DyeColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={net.minecraft.world.entity.animal.Sheep.class})
public abstract class SheepMixin
extends AnimalMixin {
    @Inject(method={"shear"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Sheep;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$forceDrop(CallbackInfo ci) {
        this.forceDrops = true;
    }

    @Inject(method={"shear"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/animal/Sheep;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$forceDropReset(CallbackInfo ci) {
        this.forceDrops = false;
    }

    @Inject(method={"ate"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$regrow(CallbackInfo ci) {
        SheepRegrowWoolEvent event = new SheepRegrowWoolEvent((Sheep)((Object)this.getBukkitEntity()));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"makeContainer"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private static void arclight$resultInv(DyeColor color, DyeColor color1, CallbackInfoReturnable<CraftingContainer> cir, CraftingContainer craftingInventory) {
        ((CraftingInventoryBridge)craftingInventory).bridge$setResultInventory((Container)new ResultContainer());
    }
}

