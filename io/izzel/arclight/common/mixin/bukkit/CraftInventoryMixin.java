/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.event.inventory.InventoryType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftInventory.class}, remap=false)
public class CraftInventoryMixin {
    @Shadow
    @Final
    protected Container inventory;

    @Inject(method={"getType"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$lecternType(CallbackInfoReturnable<InventoryType> cir) {
        if (this.inventory.getClass().getDeclaringClass() == LecternBlockEntity.class) {
            cir.setReturnValue((Object)InventoryType.LECTERN);
        }
    }
}

