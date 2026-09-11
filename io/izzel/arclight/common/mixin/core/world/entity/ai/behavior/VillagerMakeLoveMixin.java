/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.behavior.VillagerMakeLove
 *  net.minecraft.world.entity.npc.Villager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.VillagerMakeLove;
import net.minecraft.world.entity.npc.Villager;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={VillagerMakeLove.class})
public class VillagerMakeLoveMixin {
    @Redirect(method={"breed"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/npc/Villager;getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/npc/Villager;"))
    private Villager arclight$entityBreed(Villager lona, ServerLevel world, AgeableMob anonymous) {
        Villager child = lona.m_142606_(world, anonymous);
        if (child != null && !CraftEventFactory.callEntityBreedEvent((LivingEntity)child, (LivingEntity)lona, (LivingEntity)anonymous, null, null, 0).isCancelled()) {
            ((WorldBridge)world).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.BREEDING);
            return child;
        }
        return null;
    }
}

