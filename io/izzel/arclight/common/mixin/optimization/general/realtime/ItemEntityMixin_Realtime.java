/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.item.ItemEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general.realtime;

import io.izzel.arclight.common.mod.ArclightConstants;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemEntity.class})
public class ItemEntityMixin_Realtime {
    @Shadow
    public int f_31986_;
    @Shadow
    public int f_31985_;
    private int lastTick = ArclightConstants.currentTick - 1;

    @Inject(method={"tick"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;tick()V")})
    private void arclight$useWallTime(CallbackInfo ci) {
        int elapsedTicks = ArclightConstants.currentTick - this.lastTick - 1;
        if (elapsedTicks < 0) {
            elapsedTicks = 0;
        }
        if (this.f_31986_ > 0 && this.f_31986_ != Short.MAX_VALUE && elapsedTicks > 0) {
            this.f_31986_ -= elapsedTicks;
        }
        if (this.f_31985_ != Short.MIN_VALUE) {
            this.f_31985_ += elapsedTicks;
        }
        this.lastTick = ArclightConstants.currentTick;
    }
}

