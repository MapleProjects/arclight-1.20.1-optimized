/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.animal.horse.SkeletonHorse
 *  net.minecraft.world.entity.animal.horse.SkeletonTrapGoal
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.goal;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.SkeletonTrapGoal;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SkeletonTrapGoal.class})
public class SkeletonTrapGoalMixin {
    @Shadow
    @Final
    private SkeletonHorse f_30925_;

    @Inject(method={"*"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")}, slice={@Slice(to=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/horse/SkeletonTrapGoal;createHorse(Lnet/minecraft/world/DifficultyInstance;)Lnet/minecraft/world/entity/animal/horse/AbstractHorse;"))})
    private void arclight$thunder(CallbackInfo ci) {
        ((WorldBridge)this.f_30925_.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.TRAP);
    }

    @Redirect(method={"*"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$addHorse(ServerLevel world, Entity entityIn) {
        ((ServerWorldBridge)world).bridge$strikeLightning((LightningBolt)entityIn, LightningStrikeEvent.Cause.TRAP);
        return true;
    }

    @Inject(method={"*"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/horse/SkeletonTrapGoal;createHorse(Lnet/minecraft/world/DifficultyInstance;)Lnet/minecraft/world/entity/animal/horse/AbstractHorse;"))})
    private void arclight$jockey(CallbackInfo ci) {
        ((WorldBridge)this.f_30925_.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.JOCKEY);
    }
}

