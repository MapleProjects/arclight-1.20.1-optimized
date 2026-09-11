/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.item;

import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Explosive;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PrimedTnt.class})
public abstract class PrimedTntMixin
extends EntityMixin {
    public float yield;
    public boolean isIncendiary;

    @Shadow
    public abstract int m_32100_();

    @Shadow
    public abstract void m_32085_(int var1);

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends PrimedTnt> type, Level worldIn, CallbackInfo ci) {
        this.yield = 4.0f;
        this.isIncendiary = false;
    }

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/entity/LivingEntity;)V"}, at={@At(value="RETURN")})
    private void arclight$init(Level worldIn, double x, double y, double z, LivingEntity igniter, CallbackInfo ci) {
        this.yield = 4.0f;
        this.isIncendiary = false;
    }

    @Override
    @Overwrite
    public void m_8119_() {
        if (!this.m_20068_()) {
            this.m_20256_(this.m_20184_().m_82520_(0.0, -0.04, 0.0));
        }
        this.m_6478_(MoverType.SELF, this.m_20184_());
        this.m_20256_(this.m_20184_().m_82490_(0.98));
        if (this.f_19861_) {
            this.m_20256_(this.m_20184_().m_82542_(0.7, -0.5, 0.7));
        }
        int i = this.m_32100_() - 1;
        this.m_32085_(i);
        if (i <= 0) {
            if (!this.m_9236_().f_46443_) {
                this.m_32103_();
            }
            this.m_146870_();
        } else {
            this.m_20073_();
            if (this.m_9236_().f_46443_) {
                this.m_9236_().m_7106_((ParticleOptions)ParticleTypes.f_123762_, this.m_20185_(), this.m_20186_() + 0.5, this.m_20189_(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Overwrite
    protected void m_32103_() {
        ExplosionPrimeEvent event = new ExplosionPrimeEvent((Explosive)((Object)this.getBukkitEntity()));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.m_9236_().m_255391_((Entity)((PrimedTnt)this), this.m_20185_(), this.m_20227_(0.0625), this.m_20189_(), event.getRadius(), event.getFire(), Level.ExplosionInteraction.TNT);
        }
    }
}

