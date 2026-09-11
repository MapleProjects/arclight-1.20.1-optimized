/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.behavior.BehaviorControl
 *  net.minecraft.world.entity.ai.behavior.StartAttacking
 *  net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent$ILivingTargetType
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent$LivingTargetType
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.StartAttacking;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={StartAttacking.class})
public class StartAttackingMixin {
    @Overwrite
    public static <E extends Mob> BehaviorControl<E> m_257741_(Predicate<E> p_259618_, Function<E, Optional<? extends LivingEntity>> p_259435_) {
        return BehaviorBuilder.m_258034_(p_258782_ -> p_258782_.group((App)p_258782_.m_258080_(MemoryModuleType.f_26372_), (App)p_258782_.m_257492_(MemoryModuleType.f_26326_)).apply((Applicative)p_258782_, (p_258778_, p_258779_) -> (p_258773_, p_258774_, p_258775_) -> {
            if (!p_259618_.test(p_258774_)) {
                return false;
            }
            Optional optional = (Optional)p_259435_.apply(p_258774_);
            if (optional.isEmpty()) {
                return false;
            }
            LivingEntity livingentity = (LivingEntity)optional.get();
            if (!p_258774_.m_6779_(livingentity)) {
                return false;
            }
            LivingChangeTargetEvent changeTargetEvent = ForgeHooks.onLivingChangeTarget((LivingEntity)p_258774_, (LivingEntity)livingentity, (LivingChangeTargetEvent.ILivingTargetType)LivingChangeTargetEvent.LivingTargetType.BEHAVIOR_TARGET);
            if (changeTargetEvent.isCanceled()) {
                return false;
            }
            EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)p_258774_, livingentity, livingentity instanceof ServerPlayer ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY);
            if (event.isCancelled()) {
                return false;
            }
            if (((EntityTargetEvent)event).getTarget() == null) {
                p_258778_.m_257971_();
                return true;
            }
            livingentity = ((CraftLivingEntity)((EntityTargetEvent)event).getTarget()).getHandle();
            p_258778_.m_257512_((Object)changeTargetEvent.getNewTarget());
            p_258779_.m_257971_();
            return true;
        }));
    }
}

