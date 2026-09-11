/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerBossEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.boss.wither;

import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={WitherBoss.class})
public abstract class WitherBossMixin
extends PathfinderMobMixin {
    @Shadow
    @Final
    private int[] f_31427_;
    @Shadow
    @Final
    private int[] f_31428_;
    @Shadow
    @Final
    private static TargetingConditions f_31432_;
    @Shadow
    private int f_31429_;
    @Shadow
    @Final
    public ServerBossEvent f_31430_;

    @Shadow
    public abstract int m_31502_();

    @Shadow
    public abstract void m_31510_(int var1);

    @Shadow
    protected abstract void m_31448_(int var1, double var2, double var4, double var6, boolean var8);

    @Shadow
    public abstract int m_31512_(int var1);

    @Shadow
    public abstract void m_31454_(int var1, int var2);

    @Shadow
    protected abstract void m_31457_(int var1, LivingEntity var2);

    @Override
    @Overwrite
    protected void m_8024_() {
        if (this.m_31502_() > 0) {
            int k1 = this.m_31502_() - 1;
            this.f_31430_.m_142711_(1.0f - (float)k1 / 220.0f);
            if (k1 <= 0) {
                ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), 7.0f, false);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    this.m_9236_().m_255391_((Entity)((WitherBoss)this), this.m_20185_(), this.m_20188_(), this.m_20189_(), event.getRadius(), event.getFire(), Level.ExplosionInteraction.MOB);
                }
                if (!this.m_20067_()) {
                    this.m_9236_().m_6798_(1023, this.m_20183_(), 0);
                }
            }
            this.m_31510_(k1);
            if (this.f_19797_ % 10 == 0) {
                this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.WITHER_SPAWN);
                this.m_5634_(10.0f);
            }
        } else {
            super.m_8024_();
            for (int i = 1; i < 3; ++i) {
                LivingEntity livingentity1;
                int l1;
                if (this.f_19797_ < this.f_31427_[i - 1]) continue;
                this.f_31427_[i - 1] = this.f_19797_ + 10 + this.f_19796_.m_188503_(10);
                if (this.m_9236_().m_46791_() == Difficulty.NORMAL || this.m_9236_().m_46791_() == Difficulty.HARD) {
                    int i3 = i - 1;
                    int j3 = this.f_31428_[i - 1];
                    this.f_31428_[i3] = this.f_31428_[i - 1] + 1;
                    if (j3 > 15) {
                        double d0 = Mth.m_216263_((RandomSource)this.f_19796_, (double)(this.m_20185_() - 10.0), (double)(this.m_20185_() + 10.0));
                        double d1 = Mth.m_216263_((RandomSource)this.f_19796_, (double)(this.m_20186_() - 5.0), (double)(this.m_20186_() + 5.0));
                        double d2 = Mth.m_216263_((RandomSource)this.f_19796_, (double)(this.m_20189_() - 10.0), (double)(this.m_20189_() + 10.0));
                        this.m_31448_(i + 1, d0, d1, d2, true);
                        this.f_31428_[i - 1] = 0;
                    }
                }
                if ((l1 = this.m_31512_(i)) > 0) {
                    LivingEntity livingentity = (LivingEntity)this.m_9236_().m_6815_(l1);
                    if (livingentity != null && this.m_6779_(livingentity) && !(this.m_20280_((Entity)livingentity) > 900.0) && this.m_142582_((Entity)livingentity)) {
                        this.m_31457_(i + 1, livingentity);
                        this.f_31427_[i - 1] = this.f_19797_ + 40 + this.f_19796_.m_188503_(20);
                        this.f_31428_[i - 1] = 0;
                        continue;
                    }
                    if (CraftEventFactory.callEntityTargetLivingEvent((Entity)((WitherBoss)this), livingentity, EntityTargetEvent.TargetReason.CLOSEST_ENTITY).isCancelled()) continue;
                    this.m_31454_(i, 0);
                    continue;
                }
                List list = this.m_9236_().m_45971_(LivingEntity.class, f_31432_, (LivingEntity)((WitherBoss)this), this.m_20191_().m_82377_(20.0, 8.0, 20.0));
                if (list.isEmpty() || CraftEventFactory.callEntityTargetLivingEvent((Entity)((WitherBoss)this), livingentity1 = (LivingEntity)list.get(this.f_19796_.m_188503_(list.size())), EntityTargetEvent.TargetReason.CLOSEST_ENTITY).isCancelled()) continue;
                this.m_31454_(i, livingentity1.m_19879_());
            }
            if (this.m_5448_() != null) {
                this.m_31454_(0, this.m_5448_().m_19879_());
            } else {
                this.m_31454_(0, 0);
            }
            if (this.f_31429_ > 0) {
                --this.f_31429_;
                if (this.f_31429_ == 0 && ForgeEventFactory.getMobGriefingEvent((Level)this.m_9236_(), (Entity)((WitherBoss)this))) {
                    int j1 = Mth.m_14107_((double)this.m_20186_());
                    int i2 = Mth.m_14107_((double)this.m_20185_());
                    int j2 = Mth.m_14107_((double)this.m_20189_());
                    boolean flag = false;
                    for (int j = -1; j <= 1; ++j) {
                        for (int k2 = -1; k2 <= 1; ++k2) {
                            for (int k = 0; k <= 3; ++k) {
                                int l2 = i2 + j;
                                int l = j1 + k;
                                int i1 = j2 + k2;
                                BlockPos blockpos = new BlockPos(l2, l, i1);
                                BlockState blockstate = this.m_9236_().m_8055_(blockpos);
                                if (!blockstate.canEntityDestroy((BlockGetter)this.m_9236_(), blockpos, (Entity)((WitherBoss)this)) || !ForgeEventFactory.onEntityDestroyBlock((LivingEntity)((WitherBoss)this), (BlockPos)blockpos, (BlockState)blockstate) || !CraftEventFactory.callEntityChangeBlockEvent((Entity)((WitherBoss)this), blockpos, Blocks.f_50016_.m_49966_())) continue;
                                flag = this.m_9236_().m_46953_(blockpos, true, (Entity)((WitherBoss)this)) || flag;
                            }
                        }
                    }
                    if (flag) {
                        this.m_9236_().m_5898_(null, 1022, this.m_20183_(), 0);
                    }
                }
            }
            if (this.f_19797_ % 20 == 0) {
                this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.REGEN);
                this.m_5634_(1.0f);
            }
            this.f_31430_.m_142711_(this.m_21223_() / this.m_21233_());
        }
    }
}

