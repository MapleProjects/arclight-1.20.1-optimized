/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.vehicle.Boat
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.vehicle;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Boat.class})
public abstract class BoatMixin
extends EntityMixin {
    public double maxSpeed = 0.4;
    public double occupiedDeceleration = 0.2;
    public double unoccupiedDeceleration = -1.0;
    public boolean landBoats = false;
    private Location lastLocation;

    @Shadow
    public abstract float m_38384_();

    @Shadow
    public abstract void m_38311_(float var1);

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/Boat;setHurtDir(I)V")})
    private void arclight$damageVehicle(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
        CraftEntity attacker = source.m_7639_() == null ? null : ((EntityBridge)source.m_7639_()).bridge$getBukkitEntity();
        VehicleDamageEvent event = new VehicleDamageEvent(vehicle, attacker, amount);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/entity/vehicle/Boat;getDamage()F")})
    private void arclight$destroyVehicle(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.m_38384_() > 40.0f) {
            Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
            CraftEntity attacker = source.m_7639_() == null ? null : ((EntityBridge)source.m_7639_()).bridge$getBukkitEntity();
            VehicleDestroyEvent event = new VehicleDestroyEvent(vehicle, attacker);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                this.m_38311_(40.0f);
                cir.setReturnValue((Object)true);
            }
        }
    }

    @Inject(method={"push"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;push(Lnet/minecraft/world/entity/Entity;)V")})
    private void arclight$collideVehicle(Entity entityIn, CallbackInfo ci) {
        if (this.m_20365_(entityIn)) {
            VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)((Object)this.getBukkitEntity()), ((EntityBridge)entityIn).bridge$getBukkitEntity());
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/Boat;tickBubbleColumn()V")})
    private void arclight$updateVehicle(CallbackInfo ci) {
        CraftWorld bworld = ((WorldBridge)this.m_9236_()).bridge$getWorld();
        Location to = new Location(bworld, this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), this.m_146909_());
        Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
        Bukkit.getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
        if (this.lastLocation != null && !this.lastLocation.equals(to)) {
            VehicleMoveEvent event = new VehicleMoveEvent(vehicle, this.lastLocation, to);
            Bukkit.getPluginManager().callEvent(event);
        }
        this.lastLocation = vehicle.getLocation();
    }

    @Redirect(method={"checkFallDamage"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/Boat;isRemoved()Z"))
    private boolean arclight$breakVehicle(Boat boatEntity) {
        if (!boatEntity.m_213877_()) {
            Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
            VehicleDestroyEvent event = new VehicleDestroyEvent(vehicle, null);
            Bukkit.getPluginManager().callEvent(event);
            return event.isCancelled();
        }
        return true;
    }
}

