/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.projectile.ThrownExperienceBottle
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.mixin.core.world.entity.projectile.ThrowableItemProjectileMixin;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.ExpBottleEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ThrownExperienceBottle.class})
public abstract class ThrownExperienceBottleMixin
extends ThrowableItemProjectileMixin {
    @Override
    @Overwrite
    protected void m_6532_(HitResult result) {
        super.m_6532_(result);
        if (!this.m_9236_().f_46443_) {
            int i = 3 + this.m_9236_().f_46441_.m_188503_(5) + this.m_9236_().f_46441_.m_188503_(5);
            ExpBottleEvent event = CraftEventFactory.callExpBottleEvent((Entity)((ThrownExperienceBottle)this), i);
            i = event.getExperience();
            if (event.getShowEffect()) {
                this.m_9236_().m_46796_(2002, this.m_20183_(), PotionUtils.m_43559_((Potion)Potions.f_43599_));
            }
            ExperienceOrb.m_147082_((ServerLevel)((ServerLevel)this.m_9236_()), (Vec3)this.m_20182_(), (int)i);
            this.m_146870_();
        }
    }
}

