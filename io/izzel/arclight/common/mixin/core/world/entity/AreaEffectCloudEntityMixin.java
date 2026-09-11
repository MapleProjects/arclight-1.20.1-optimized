/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.entity.AreaEffectCloudEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={AreaEffectCloud.class})
public abstract class AreaEffectCloudEntityMixin
extends EntityMixin
implements AreaEffectCloudEntityBridge {
    @Shadow
    private boolean f_19690_;
    @Shadow
    @Final
    private static EntityDataAccessor<Integer> f_19698_;
    @Shadow
    public List<MobEffectInstance> f_19685_;
    @Shadow
    private Potion f_19701_;
    @Shadow
    public int f_19688_;
    @Shadow
    private int f_19687_;
    @Shadow
    public float f_19693_;
    @Shadow
    @Final
    private Map<Entity, Integer> f_19686_;
    @Shadow
    public int f_19689_;
    @Shadow
    public float f_19692_;
    @Shadow
    public int f_19691_;

    @Shadow
    public abstract void m_19722_(Potion var1);

    @Shadow
    public abstract boolean m_19747_();

    @Shadow
    public abstract float m_19743_();

    @Shadow
    public abstract ParticleOptions m_19745_();

    @Shadow
    public abstract int m_19744_();

    @Shadow
    protected abstract void m_19730_(boolean var1);

    @Shadow
    public abstract void m_19712_(float var1);

    @Shadow
    @Nullable
    public abstract net.minecraft.world.entity.LivingEntity m_19749_();

    @Override
    @Overwrite
    public void m_8119_() {
        block22: {
            ArrayList list;
            float f;
            block23: {
                boolean flag1;
                boolean flag;
                block21: {
                    float f1;
                    int i;
                    super.m_8119_();
                    flag = this.m_19747_();
                    f = this.m_19743_();
                    if (!this.m_9236_().f_46443_) break block21;
                    if (flag && this.f_19796_.m_188499_()) {
                        return;
                    }
                    ParticleOptions particleoptions = this.m_19745_();
                    if (flag) {
                        i = 2;
                        f1 = 0.2f;
                    } else {
                        i = Mth.m_14167_((float)((float)Math.PI * f * f));
                        f1 = f;
                    }
                    for (int j = 0; j < i; ++j) {
                        double d7;
                        double d6;
                        double d5;
                        float f2 = this.f_19796_.m_188501_() * ((float)Math.PI * 2);
                        float f3 = Mth.m_14116_((float)this.f_19796_.m_188501_()) * f1;
                        double d0 = this.m_20185_() + (double)(Mth.m_14089_((float)f2) * f3);
                        double d2 = this.m_20186_();
                        double d4 = this.m_20189_() + (double)(Mth.m_14031_((float)f2) * f3);
                        if (particleoptions.m_6012_() != ParticleTypes.f_123811_) {
                            if (flag) {
                                d5 = 0.0;
                                d6 = 0.0;
                                d7 = 0.0;
                            } else {
                                d5 = (0.5 - this.f_19796_.m_188500_()) * 0.15;
                                d6 = 0.01f;
                                d7 = (0.5 - this.f_19796_.m_188500_()) * 0.15;
                            }
                        } else {
                            int k = flag && this.f_19796_.m_188499_() ? 0xFFFFFF : this.m_19744_();
                            d5 = (float)(k >> 16 & 0xFF) / 255.0f;
                            d6 = (float)(k >> 8 & 0xFF) / 255.0f;
                            d7 = (float)(k & 0xFF) / 255.0f;
                        }
                        this.m_9236_().m_7107_(particleoptions, d0, d2, d4, d5, d6, d7);
                    }
                    break block22;
                }
                if (this.f_19797_ >= this.f_19688_ + this.f_19687_) {
                    this.m_146870_();
                    return;
                }
                boolean bl = flag1 = this.f_19797_ < this.f_19688_;
                if (flag != flag1) {
                    this.m_19730_(flag1);
                }
                if (flag1) {
                    return;
                }
                if (this.f_19693_ != 0.0f) {
                    if ((f += this.f_19693_) < 0.5f) {
                        this.m_146870_();
                        return;
                    }
                    this.m_19712_(f);
                }
                if (this.f_19797_ % 5 != 0) break block22;
                this.f_19686_.entrySet().removeIf(p_146784_ -> this.f_19797_ >= (Integer)p_146784_.getValue());
                list = Lists.newArrayList();
                for (MobEffectInstance mobeffectinstance : this.f_19701_.m_43488_()) {
                    list.add(new MobEffectInstance(mobeffectinstance.m_19544_(), mobeffectinstance.m_19557_() / 4, mobeffectinstance.m_19564_(), mobeffectinstance.m_19571_(), mobeffectinstance.m_19572_()));
                }
                list.addAll(this.f_19685_);
                if (!list.isEmpty()) break block23;
                this.f_19686_.clear();
                break block22;
            }
            List list1 = this.m_9236_().m_45976_(net.minecraft.world.entity.LivingEntity.class, this.m_20191_());
            if (list1.isEmpty()) break block22;
            ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
            for (net.minecraft.world.entity.LivingEntity livingentity : list1) {
                double d1;
                double d8;
                double d3;
                if (this.f_19686_.containsKey(livingentity) || !livingentity.m_5801_() || !((d3 = (d8 = livingentity.m_20185_() - this.m_20185_()) * d8 + (d1 = livingentity.m_20189_() - this.m_20189_()) * d1) <= (double)(f * f))) continue;
                entities.add(((LivingEntityBridge)livingentity).bridge$getBukkitEntity());
            }
            AreaEffectCloudApplyEvent event = CraftEventFactory.callAreaEffectCloudApplyEvent((AreaEffectCloud)this, entities);
            if (!event.isCancelled()) {
                for (LivingEntity entity : event.getAffectedEntities()) {
                    if (!(entity instanceof CraftLivingEntity)) continue;
                    net.minecraft.world.entity.LivingEntity livingentity = ((CraftLivingEntity)entity).getHandle();
                    this.f_19686_.put((Entity)livingentity, this.f_19797_ + this.f_19689_);
                    for (MobEffectInstance mobeffectinstance1 : list) {
                        if (mobeffectinstance1.m_19544_().m_8093_()) {
                            mobeffectinstance1.m_19544_().m_19461_((Entity)((AreaEffectCloud)this), (Entity)this.m_19749_(), livingentity, mobeffectinstance1.m_19564_(), 0.5);
                            continue;
                        }
                        livingentity.m_147207_(new MobEffectInstance(mobeffectinstance1), (Entity)((AreaEffectCloud)this));
                    }
                    if (this.f_19692_ != 0.0f) {
                        if ((f += this.f_19692_) < 0.5f) {
                            this.m_146870_();
                            return;
                        }
                        this.m_19712_(f);
                    }
                    if (this.f_19691_ == 0) continue;
                    this.f_19687_ += this.f_19691_;
                    if (this.f_19687_ > 0) continue;
                    this.m_146870_();
                    return;
                }
            }
        }
    }

    public void refreshEffects() {
        if (!this.f_19690_) {
            this.m_20088_().m_135381_(f_19698_, (Object)PotionUtils.m_43564_((Collection)PotionUtils.m_43561_((Potion)this.f_19701_, this.f_19685_)));
        }
    }

    public String getPotionType() {
        return BuiltInRegistries.f_256980_.m_7981_((Object)this.f_19701_).toString();
    }

    public void setPotionType(String string) {
        this.m_19722_((Potion)BuiltInRegistries.f_256980_.m_7745_(new ResourceLocation(string)));
    }

    @Override
    public void bridge$refreshEffects() {
        this.refreshEffects();
    }
}

