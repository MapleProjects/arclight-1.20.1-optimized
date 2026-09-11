/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.Slime
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.MobMixin;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={net.minecraft.world.entity.monster.Slime.class})
public abstract class SlimeMixin
extends MobMixin {
    private transient List<LivingEntity> arclight$slimes;

    @Shadow
    public abstract int m_33632_();

    @Shadow
    public abstract EntityType<? extends net.minecraft.world.entity.monster.Slime> m_6095_();

    @Override
    @Overwrite(remap=false)
    public void m_142687_(Entity.RemovalReason p_149847_) {
        int i = this.m_33632_();
        if (!this.m_9236_().f_46443_ && i > 1 && this.m_21224_()) {
            float f2;
            float f1;
            int l;
            Component itextcomponent = this.m_7770_();
            boolean flag = this.m_21525_();
            float f = (float)i / 4.0f;
            int j = i / 2;
            int k = 2 + this.f_19796_.m_188503_(3);
            SlimeSplitEvent event = new SlimeSplitEvent((Slime)((Object)this.getBukkitEntity()), k);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled() || event.getCount() <= 0) {
                super.m_142687_(p_149847_);
                return;
            }
            k = event.getCount();
            this.arclight$slimes = new ArrayList<LivingEntity>(k);
            for (l = 0; l < k; ++l) {
                f1 = ((float)(l % 2) - 0.5f) * f;
                f2 = ((float)(l / 2) - 0.5f) * f;
                net.minecraft.world.entity.monster.Slime slimeentity = (net.minecraft.world.entity.monster.Slime)this.m_6095_().m_20615_(this.m_9236_());
                if (slimeentity == null) continue;
                if (this.m_21532_()) {
                    slimeentity.m_21530_();
                }
                slimeentity.m_6593_(itextcomponent);
                slimeentity.m_21557_(flag);
                slimeentity.m_20331_(this.m_20147_());
                slimeentity.m_7839_(j, true);
                slimeentity.m_7678_(this.m_20185_() + (double)f1, this.m_20186_() + 0.5, this.m_20189_() + (double)f2, this.f_19796_.m_188501_() * 360.0f, 0.0f);
                this.arclight$slimes.add((LivingEntity)slimeentity);
            }
            if (CraftEventFactory.callEntityTransformEvent((LivingEntity)((net.minecraft.world.entity.monster.Slime)this), this.arclight$slimes, EntityTransformEvent.TransformReason.SPLIT).isCancelled()) {
                super.m_142687_(p_149847_);
                this.arclight$slimes = null;
                return;
            }
            for (l = 0; l < this.arclight$slimes.size(); ++l) {
                f1 = ((float)(l % 2) - 0.5f) * f;
                f2 = ((float)(l / 2) - 0.5f) * f;
                net.minecraft.world.entity.monster.Slime living = (net.minecraft.world.entity.monster.Slime)this.arclight$slimes.get(l);
                ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.SLIME_SPLIT);
                this.m_9236_().m_7967_((Entity)living);
            }
            this.arclight$slimes = null;
        }
        super.m_142687_(p_149847_);
    }
}

