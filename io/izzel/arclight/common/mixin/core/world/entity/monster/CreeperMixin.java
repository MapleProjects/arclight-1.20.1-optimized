/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.AreaEffectCloud
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.monster.CreeperEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import java.util.Collection;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={Creeper.class})
public abstract class CreeperMixin
extends PathfinderMobMixin
implements CreeperEntityBridge {
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> f_32274_;
    @Shadow
    public int f_32272_;
    @Shadow
    private int f_32270_;

    @Shadow
    protected abstract void m_32316_();

    @Shadow
    public abstract boolean m_7090_();

    @Inject(method={"thunderHit"}, cancellable=true, at={@At(value="FIELD", target="Lnet/minecraft/world/entity/monster/Creeper;entityData:Lnet/minecraft/network/syncher/SynchedEntityData;")})
    private void arclight$lightningBolt(ServerLevel world, LightningBolt lightningBolt, CallbackInfo ci) {
        if (CraftEventFactory.callCreeperPowerEvent((Entity)((Creeper)this), (Entity)lightningBolt, CreeperPowerEvent.PowerCause.LIGHTNING).isCancelled()) {
            ci.cancel();
        }
    }

    @Overwrite
    public void m_32315_() {
        if (!this.m_9236_().f_46443_) {
            float f = this.m_7090_() ? 2.0f : 1.0f;
            ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), (float)this.f_32272_ * f, false);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.f_20890_ = true;
                this.m_9236_().m_255391_((Entity)((Creeper)this), this.m_20185_(), this.m_20186_(), this.m_20189_(), event.getRadius(), event.getFire(), Level.ExplosionInteraction.MOB);
                this.m_146870_();
                this.m_32316_();
            } else {
                this.f_32270_ = 0;
            }
        }
    }

    @Inject(method={"spawnLingeringCloud"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$creeperCloud(CallbackInfo ci, Collection<MobEffectInstance> collection, AreaEffectCloud areaeffectcloudentity) {
        areaeffectcloudentity.m_19718_((LivingEntity)((Creeper)this));
        ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.EXPLOSION);
    }

    public void setPowered(boolean power) {
        this.f_19804_.m_135381_(f_32274_, (Object)power);
    }

    @Override
    public void bridge$setPowered(boolean power) {
        this.setPowered(power);
    }
}

