/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ai.behavior.PrepareRamNearestTarget
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.PrepareRamNearestTarget;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={PrepareRamNearestTarget.class})
public class PrepareRamNearestTargetMixin {
    @Redirect(method={"start(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/PathfinderMob;J)V"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V"))
    private void arclight$targetEvent(Optional<LivingEntity> instance, Consumer<LivingEntity> action, ServerLevel level, PathfinderMob mob) {
        instance.ifPresent(entity -> {
            EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)mob, entity, entity instanceof ServerPlayer ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY);
            if (event.isCancelled() || event.getTarget() == null) {
                return;
            }
            LivingEntity newEntity = ((CraftLivingEntity)event.getTarget()).getHandle();
            action.accept(newEntity);
        });
    }
}

