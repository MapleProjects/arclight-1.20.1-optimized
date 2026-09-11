/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.Holder$Reference
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.commands.SummonCommand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.commands.SummonCommand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={SummonCommand.class})
public class SummonCommandMixin {
    @Inject(method={"createEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;tryAddFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)Z")})
    private static void arclight$summonReason(CommandSourceStack source, Holder.Reference<EntityType<?>> p_270277_, Vec3 p_270366_, CompoundTag p_270197_, boolean p_270947_, CallbackInfoReturnable<Entity> cir) {
        ((ServerWorldBridge)source.m_81372_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.COMMAND);
    }
}

