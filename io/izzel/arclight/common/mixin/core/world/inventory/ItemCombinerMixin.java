/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.ItemCombinerMenu
 *  net.minecraft.world.inventory.ResultContainer
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.mixin.core.world.inventory.AbstractContainerMenuMixin;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemCombinerMenu.class})
public abstract class ItemCombinerMixin
extends AbstractContainerMenuMixin {
    @Shadow
    @Final
    protected ContainerLevelAccess f_39770_;
    @Shadow
    @Final
    @Mutable
    protected Container f_39769_;
    @Shadow
    @Final
    protected ResultContainer f_39768_;
    @Shadow
    @Final
    protected Player f_39771_;

    @Inject(method={"stillValid"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$unreachable(Player playerIn, CallbackInfoReturnable<Boolean> cir) {
        if (!this.bridge$isCheckReachable()) {
            cir.setReturnValue((Object)true);
        }
    }
}

