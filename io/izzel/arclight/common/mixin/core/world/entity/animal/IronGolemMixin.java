/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.IronGolem
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.IronGolem;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={IronGolem.class})
public abstract class IronGolemMixin
extends PathfinderMobMixin {
    @Inject(method={"doPush"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/IronGolem;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V")})
    private void arclight$targetReason(Entity entityIn, CallbackInfo ci) {
        this.bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.COLLISION, true);
    }
}

