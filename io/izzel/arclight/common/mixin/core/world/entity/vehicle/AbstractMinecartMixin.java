/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.util.Mth
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.AbstractMinecart$Type
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.extensions.IForgeAbstractMinecart
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.vehicle;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.extensions.IForgeAbstractMinecart;
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
import org.bukkit.util.Vector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractMinecart.class})
public abstract class AbstractMinecartMixin
extends EntityMixin
implements IForgeAbstractMinecart {
    @Shadow
    private int f_38070_;
    @Shadow
    private double f_38071_;
    @Shadow
    private double f_38072_;
    @Shadow
    private double f_38073_;
    @Shadow
    private double f_38074_;
    @Shadow
    private double f_38075_;
    @Shadow
    private boolean f_38068_;
    @Shadow
    private boolean f_287786_;
    public boolean slowWhenEmpty = true;
    private double derailedX = 0.5;
    private double derailedY = 0.5;
    private double derailedZ = 0.5;
    private double flyingX = 0.95;
    private double flyingY = 0.95;
    private double flyingZ = 0.95;
    public double maxSpeed = 0.4;
    private transient Location arclight$prevLocation;

    @Shadow
    public abstract void m_38160_(int var1);

    @Shadow
    public abstract int m_38177_();

    @Shadow
    public abstract void m_38154_(int var1);

    @Shadow
    public abstract void m_38109_(float var1);

    @Shadow
    public abstract float m_38169_();

    @Shadow
    public abstract void m_7617_(DamageSource var1);

    @Shadow
    public abstract int m_38176_();

    @Shadow
    protected abstract void m_6401_(BlockPos var1, BlockState var2);

    @Shadow
    public abstract void m_6025_(int var1, int var2, int var3, boolean var4);

    @Shadow
    public abstract AbstractMinecart.Type m_6064_();

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<?> type, Level worldIn, CallbackInfo ci) {
        this.slowWhenEmpty = true;
        this.derailedX = 0.5;
        this.derailedY = 0.5;
        this.derailedZ = 0.5;
        this.flyingX = 0.95;
        this.flyingY = 0.95;
        this.flyingZ = 0.95;
        this.maxSpeed = 0.4;
    }

    @Override
    @Overwrite
    public boolean m_6469_(DamageSource source, float amount) {
        boolean flag;
        if (this.m_9236_().f_46443_ || this.m_213877_()) {
            return true;
        }
        if (this.m_6673_(source)) {
            return false;
        }
        Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
        CraftEntity passenger = source.m_7639_() == null ? null : ((EntityBridge)source.m_7639_()).bridge$getBukkitEntity();
        VehicleDamageEvent event = new VehicleDamageEvent(vehicle, passenger, amount);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }
        amount = (float)event.getDamage();
        this.m_38160_(-this.m_38177_());
        this.m_38154_(10);
        this.m_5834_();
        this.m_38109_(this.m_38169_() + amount * 10.0f);
        this.m_146852_(GameEvent.f_223706_, source.m_7639_());
        boolean bl = flag = source.m_7639_() instanceof Player && ((Player)source.m_7639_()).m_150110_().f_35937_;
        if (flag || this.m_38169_() > 40.0f) {
            VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, passenger);
            Bukkit.getPluginManager().callEvent(destroyEvent);
            if (destroyEvent.isCancelled()) {
                this.m_38109_(40.0f);
                return true;
            }
            this.m_20153_();
            if (flag && !this.m_8077_()) {
                this.m_146870_();
            } else {
                this.m_7617_(source);
            }
        }
        return true;
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void arclight$storePrevLocation(CallbackInfo ci) {
        this.arclight$prevLocation = new Location(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), this.m_146909_());
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/AbstractMinecart;handleNetherPortal()V"))
    private void arclight$skipHandleNetherPortal(AbstractMinecart instance) {
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/vehicle/AbstractMinecart;setRot(FF)V")})
    private void arclight$fireVehicleEvents(CallbackInfo ci) {
        CraftWorld bworld = ((WorldBridge)this.m_9236_()).bridge$getWorld();
        Location from = this.arclight$prevLocation;
        this.arclight$prevLocation = null;
        from.setWorld(bworld);
        Location to = new Location(bworld, this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), this.m_146909_());
        Vehicle vehicle = (Vehicle)((Object)this.getBukkitEntity());
        Bukkit.getPluginManager().callEvent(new VehicleUpdateEvent(vehicle));
        if (!from.equals(to)) {
            Bukkit.getPluginManager().callEvent(new VehicleMoveEvent(vehicle, from, to));
        }
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;startRiding(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$fireCollisionEventsRiding(Entity that, Entity self) {
        VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent((Vehicle)((Object)this.getBukkitEntity()), ((EntityBridge)that).bridge$getBukkitEntity());
        Bukkit.getPluginManager().callEvent(collisionEvent);
        if (collisionEvent.isCancelled()) {
            return false;
        }
        return that.m_20329_((Entity)this);
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;push(Lnet/minecraft/world/entity/Entity;)V"))
    private void arclight$fireCollisionEventsPush(Entity that, Entity self) {
        VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent((Vehicle)((Object)this.getBukkitEntity()), ((EntityBridge)that).bridge$getBukkitEntity());
        Bukkit.getPluginManager().callEvent(collisionEvent);
        if (collisionEvent.isCancelled()) {
            return;
        }
        that.m_7334_((Entity)this);
    }

    @Overwrite
    protected double m_7097_() {
        return this.maxSpeed;
    }

    @Overwrite
    protected void m_38163_() {
        double d0 = this.m_7097_();
        Vec3 vec3d = this.m_20184_();
        this.m_20334_(Mth.m_14008_((double)vec3d.f_82479_, (double)(-d0), (double)d0), vec3d.f_82480_, Mth.m_14008_((double)vec3d.f_82481_, (double)(-d0), (double)d0));
        if (this.f_19861_) {
            this.m_20256_(new Vec3(this.m_20184_().f_82479_ * this.derailedX, this.m_20184_().f_82480_ * this.derailedY, this.m_20184_().f_82481_ * this.derailedZ));
        }
        this.m_6478_(MoverType.SELF, this.m_20184_());
        if (!this.f_19861_) {
            this.m_20256_(new Vec3(this.m_20184_().f_82479_ * this.flyingX, this.m_20184_().f_82480_ * this.flyingY, this.m_20184_().f_82481_ * this.flyingZ));
        }
    }

    @Redirect(method={"applyNaturalSlowdown"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/AbstractMinecart;isVehicle()Z"))
    private boolean arclight$slowWhenEmpty(AbstractMinecart abstractMinecartEntity) {
        return this.m_20160_() || !this.slowWhenEmpty;
    }

    @Inject(method={"push"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/vehicle/AbstractMinecart;hasPassenger(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$vehicleCollide(Entity entityIn, CallbackInfo ci) {
        if (!this.m_20363_(entityIn)) {
            VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent((Vehicle)((Object)this.getBukkitEntity()), ((EntityBridge)entityIn).bridge$getBukkitEntity());
            Bukkit.getPluginManager().callEvent(collisionEvent);
            if (collisionEvent.isCancelled()) {
                ci.cancel();
            }
        }
    }

    public Vector getFlyingVelocityMod() {
        return new Vector(this.flyingX, this.flyingY, this.flyingZ);
    }

    public void setFlyingVelocityMod(Vector flying) {
        this.flyingX = flying.getX();
        this.flyingY = flying.getY();
        this.flyingZ = flying.getZ();
    }

    public Vector getDerailedVelocityMod() {
        return new Vector(this.derailedX, this.derailedY, this.derailedZ);
    }

    public void setDerailedVelocityMod(Vector derailed) {
        this.derailedX = derailed.getX();
        this.derailedY = derailed.getY();
        this.derailedZ = derailed.getZ();
    }
}

