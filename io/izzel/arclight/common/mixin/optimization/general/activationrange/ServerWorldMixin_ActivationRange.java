/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange;

import io.izzel.arclight.common.bridge.optimization.EntityBridge_ActivationRange;
import io.izzel.arclight.i18n.ArclightConfig;
import java.util.function.BooleanSupplier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spigotmc.ActivationRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ServerLevel.class})
public class ServerWorldMixin_ActivationRange {
    @Unique
    private static final boolean arclight$applyInactive = ArclightConfig.spec().getOptimization().useActivationAndTrackingRange();

    @Inject(method={"tick"}, at={@At(value="FIELD", target="Lnet/minecraft/server/level/ServerLevel;entityTickList:Lnet/minecraft/world/level/entity/EntityTickList;")})
    private void activationRange$activateEntity(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        ActivationRange.activateEntities((Level)((ServerLevel)this));
    }

    @Inject(method={"tickNonPassenger"}, cancellable=true, at={@At(value="HEAD")})
    private void activationRange$inactiveTick(Entity entityIn, CallbackInfo ci) {
        if (arclight$applyInactive && !ActivationRange.checkIfActive(entityIn)) {
            ++entityIn.f_19797_;
            if (entityIn.canUpdate()) {
                ((EntityBridge_ActivationRange)entityIn).bridge$inactiveTick();
            }
            ci.cancel();
        }
    }
}

