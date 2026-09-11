/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.goal.BreedGoal
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.level.GameRules
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.BabyEntitySpawnEvent
 *  net.minecraftforge.eventbus.api.Event
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.passive.AnimalEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.passive.FoxEntityBridge;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityBreedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets={"net.minecraft.world.entity.animal.Fox$FoxBreedGoal"})
public abstract class Fox_BreedGoalMixin
extends BreedGoal {
    public Fox_BreedGoalMixin(Animal animal, double speedIn) {
        super(animal, speedIn);
    }

    @Overwrite
    protected void m_8026_() {
        ServerLevel serverworld = (ServerLevel)this.f_25114_;
        Fox foxentity = (Fox)this.f_25113_.m_142606_(serverworld, (AgeableMob)this.f_25115_);
        BabyEntitySpawnEvent event = new BabyEntitySpawnEvent((Mob)this.f_25113_, (Mob)this.f_25115_, (AgeableMob)foxentity);
        boolean cancelled = MinecraftForge.EVENT_BUS.post((Event)event);
        foxentity = (Fox)event.getChild();
        if (cancelled) {
            this.f_25113_.m_146762_(6000);
            this.f_25115_.m_146762_(6000);
            this.f_25113_.m_27594_();
            this.f_25115_.m_27594_();
            return;
        }
        if (foxentity != null) {
            ServerPlayer serverplayerentity = this.f_25113_.m_27592_();
            ServerPlayer serverplayerentity1 = this.f_25115_.m_27592_();
            ServerPlayer serverplayerentity2 = serverplayerentity;
            if (serverplayerentity != null) {
                ((FoxEntityBridge)foxentity).bridge$addTrustedUUID(serverplayerentity.m_20148_());
            } else {
                serverplayerentity2 = serverplayerentity1;
            }
            if (serverplayerentity1 != null && serverplayerentity != serverplayerentity1) {
                ((FoxEntityBridge)foxentity).bridge$addTrustedUUID(serverplayerentity1.m_20148_());
            }
            int experience = this.f_25113_.m_217043_().m_188503_(7) + 1;
            EntityBreedEvent entityBreedEvent = CraftEventFactory.callEntityBreedEvent((LivingEntity)foxentity, (LivingEntity)this.f_25113_, (LivingEntity)this.f_25115_, (LivingEntity)serverplayerentity, ((AnimalEntityBridge)this.f_25113_).bridge$getBreedItem(), experience);
            if (entityBreedEvent.isCancelled()) {
                return;
            }
            experience = entityBreedEvent.getExperience();
            if (serverplayerentity2 != null) {
                serverplayerentity2.m_36220_(Stats.f_12937_);
                CriteriaTriggers.f_10581_.m_147278_(serverplayerentity2, this.f_25113_, this.f_25115_, (AgeableMob)foxentity);
            }
            this.f_25113_.m_146762_(6000);
            this.f_25115_.m_146762_(6000);
            this.f_25113_.m_27594_();
            this.f_25115_.m_27594_();
            foxentity.m_146762_(-24000);
            foxentity.m_7678_(this.f_25113_.m_20185_(), this.f_25113_.m_20186_(), this.f_25113_.m_20189_(), 0.0f, 0.0f);
            serverworld.m_47205_((Entity)foxentity);
            this.f_25114_.m_7605_((Entity)this.f_25113_, (byte)18);
            if (this.f_25114_.m_46469_().m_46207_(GameRules.f_46135_) && experience > 0) {
                this.f_25114_.m_7967_((Entity)new ExperienceOrb(this.f_25114_, this.f_25113_.m_20185_(), this.f_25113_.m_20186_(), this.f_25113_.m_20189_(), experience));
            }
        }
    }
}

