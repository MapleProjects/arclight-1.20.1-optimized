/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.behavior.BehaviorControl
 *  net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid
 *  net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={StopAttackingIfTargetInvalid.class})
public abstract class StopAttackingIfTargetInvalidMixin {
    @Shadow
    private static boolean m_258032_(LivingEntity p_259416_, Optional<Long> p_259377_) {
        return false;
    }

    @Overwrite
    public static <E extends Mob> BehaviorControl<E> m_257811_(Predicate<LivingEntity> p_260357_, BiConsumer<E, LivingEntity> p_259568_, boolean p_260319_) {
        return BehaviorBuilder.m_258034_(p_258801_ -> p_258801_.group((App)p_258801_.m_257495_(MemoryModuleType.f_26372_), (App)p_258801_.m_257492_(MemoryModuleType.f_26326_)).apply((Applicative)p_258801_, (p_258787_, p_258788_) -> (p_258795_, p_258796_, p_258797_) -> {
            LivingEntity livingentity = (LivingEntity)p_258801_.m_258051_(p_258787_);
            if (!(!p_258796_.m_6779_(livingentity) || p_260319_ && StopAttackingIfTargetInvalidMixin.m_258032_(p_258796_, p_258801_.m_257828_(p_258788_)) || !livingentity.m_6084_() || livingentity.m_9236_() != p_258796_.m_9236_() || p_260357_.test(livingentity))) {
                return true;
            }
            LivingEntity old = p_258796_.m_6274_().m_21952_(MemoryModuleType.f_26372_).orElse(null);
            EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)p_258796_, null, old != null && !old.m_6084_() ? EntityTargetEvent.TargetReason.TARGET_DIED : EntityTargetEvent.TargetReason.FORGOT_TARGET);
            if (event.isCancelled()) {
                return false;
            }
            if (((EntityTargetEvent)event).getTarget() == null) {
                p_258787_.m_257971_();
                return true;
            }
            livingentity = ((CraftLivingEntity)((EntityTargetEvent)event).getTarget()).getHandle();
            p_259568_.accept(p_258796_, livingentity);
            p_258787_.m_257971_();
            return true;
        }));
    }
}

