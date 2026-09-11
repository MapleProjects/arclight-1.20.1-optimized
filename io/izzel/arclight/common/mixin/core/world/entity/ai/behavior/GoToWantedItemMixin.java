/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.behavior.BehaviorControl
 *  net.minecraft.world.entity.ai.behavior.EntityTracker
 *  net.minecraft.world.entity.ai.behavior.GoToWantedItem
 *  net.minecraft.world.entity.ai.behavior.PositionTracker
 *  net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.ai.memory.WalkTarget
 *  net.minecraft.world.entity.animal.allay.Allay
 *  net.minecraft.world.entity.item.ItemEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.behavior;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.behavior.GoToWantedItem;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.item.ItemEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={GoToWantedItem.class})
public abstract class GoToWantedItemMixin<E extends LivingEntity> {
    @Overwrite
    public static <E extends LivingEntity> BehaviorControl<E> m_257684_(Predicate<E> p_259490_, float p_260346_, boolean p_259637_, int p_259054_) {
        return BehaviorBuilder.m_258034_(p_258371_ -> {
            BehaviorBuilder behaviorbuilder = p_259637_ ? p_258371_.m_257492_(MemoryModuleType.f_26370_) : p_258371_.m_258080_(MemoryModuleType.f_26370_);
            return p_258371_.group((App)p_258371_.m_257492_(MemoryModuleType.f_26371_), (App)behaviorbuilder, (App)p_258371_.m_257495_(MemoryModuleType.f_26332_), (App)p_258371_.m_257492_(MemoryModuleType.f_217781_)).apply((Applicative)p_258371_, (p_258387_, p_258388_, p_258389_, p_258390_) -> (p_258380_, p_258381_, p_258382_) -> {
                ItemEntity itementity = (ItemEntity)p_258371_.m_258051_(p_258389_);
                if (p_258371_.m_257828_(p_258390_).isEmpty() && p_259490_.test(p_258381_) && itementity.m_19950_((Entity)p_258381_, (double)p_259054_) && p_258381_.m_9236_().m_6857_().m_61937_(itementity.m_20183_())) {
                    if (p_258381_ instanceof Allay) {
                        EntityTargetEvent event = CraftEventFactory.callEntityTargetEvent((Entity)p_258381_, (Entity)itementity, EntityTargetEvent.TargetReason.CLOSEST_ENTITY);
                        if (event.isCancelled()) {
                            return false;
                        }
                        if (!(event.getTarget() instanceof ItemEntity)) {
                            p_258389_.m_257971_();
                        }
                        itementity = (ItemEntity)((CraftEntity)event.getTarget()).getHandle();
                    }
                    WalkTarget walktarget = new WalkTarget((PositionTracker)new EntityTracker((Entity)itementity, false), p_260346_, 0);
                    p_258387_.m_257512_((Object)new EntityTracker((Entity)itementity, true));
                    p_258388_.m_257512_((Object)walktarget);
                    return true;
                }
                return false;
            });
        });
    }
}

