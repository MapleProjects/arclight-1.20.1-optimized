/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  net.minecraft.util.valueproviders.UniformInt
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.behavior.BabyFollowAdult
 *  net.minecraft.world.entity.ai.behavior.EntityTracker
 *  net.minecraft.world.entity.ai.behavior.OneShot
 *  net.minecraft.world.entity.ai.behavior.PositionTracker
 *  net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.ai.memory.WalkTarget
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import java.util.function.Function;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BabyFollowAdult;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={BabyFollowAdult.class})
public abstract class BabyFollowAdultMixin {
    @Overwrite
    public static OneShot<AgeableMob> m_257631_(UniformInt p_259321_, Function<LivingEntity, Float> p_259190_) {
        return BehaviorBuilder.m_258034_(p_258331_ -> p_258331_.group((App)p_258331_.m_257495_(MemoryModuleType.f_26331_), (App)p_258331_.m_257492_(MemoryModuleType.f_26371_), (App)p_258331_.m_258080_(MemoryModuleType.f_26370_)).apply((Applicative)p_258331_, (p_258317_, p_258318_, p_258319_) -> (p_258326_, p_258327_, p_258328_) -> {
            if (!p_258327_.m_6162_()) {
                return false;
            }
            LivingEntity ageablemob = (LivingEntity)p_258331_.m_258051_(p_258317_);
            if (p_258327_.m_19950_((Entity)ageablemob, (double)(p_259321_.m_142737_() + 1)) && !p_258327_.m_19950_((Entity)ageablemob, (double)p_259321_.m_142739_())) {
                EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)p_258327_, ageablemob, EntityTargetEvent.TargetReason.FOLLOW_LEADER);
                if (event.isCancelled()) {
                    return false;
                }
                if (event.getTarget() == null) {
                    p_258317_.m_257971_();
                    return true;
                }
                ageablemob = ((CraftLivingEntity)event.getTarget()).getHandle();
                WalkTarget walktarget = new WalkTarget((PositionTracker)new EntityTracker((Entity)ageablemob, false), ((Float)p_259190_.apply(p_258327_)).floatValue(), p_259321_.m_142739_() - 1);
                p_258318_.m_257512_((Object)new EntityTracker((Entity)ageablemob, true));
                p_258319_.m_257512_((Object)walktarget);
                return true;
            }
            return false;
        }));
    }
}

