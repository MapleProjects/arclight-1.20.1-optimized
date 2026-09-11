/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.BlockUtil
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.PositionImpl
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.tags.FluidTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageSources
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntityDimensions
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.portal.PortalInfo
 *  net.minecraft.world.level.portal.PortalShape
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.scores.Team
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.ITeleporter
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.block.PortalInfoBridge;
import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.InternalEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import io.izzel.arclight.common.bridge.core.world.TeleporterBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.server.BukkitRegistry;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.PositionImpl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ITeleporter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftPortalEvent;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityAirChangeEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntityPoseChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.projectiles.ProjectileSource;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={Entity.class})
public abstract class EntityMixin
implements InternalEntityBridge,
EntityBridge,
ICommandSourceBridge {
    @Shadow
    private float f_19857_;
    @Shadow
    protected int f_19851_;
    @Shadow
    private float f_19858_;
    @Shadow
    public int f_19831_;
    @Shadow
    public boolean f_19862_;
    @Shadow
    public int f_19797_;
    @Shadow
    private Entity f_19824_;
    @Shadow
    @Final
    protected SynchedEntityData f_19804_;
    @Shadow
    @Final
    private static EntityDataAccessor<Integer> f_19832_;
    @Shadow
    @Final
    protected RandomSource f_19796_;
    @Shadow
    public int f_19802_;
    @Shadow
    protected boolean f_19861_;
    @Shadow
    public float f_19789_;
    @Shadow
    public float f_19787_;
    @Shadow
    public float f_19867_;
    @Shadow
    protected UUID f_19820_;
    @Shadow
    public double f_19854_;
    @Shadow
    public double f_19855_;
    @Shadow
    public double f_19856_;
    @Shadow
    public float f_19859_;
    @Shadow
    protected BlockPos f_19819_;
    @Shadow
    protected boolean f_19803_;
    @Shadow
    public ImmutableList<Entity> f_19823_;
    private static final int CURRENT_LEVEL = 2;
    public boolean persist = true;
    public boolean generation;
    public boolean valid;
    public ProjectileSource projectileSource;
    public boolean lastDamageCancelled;
    public boolean persistentInvisibility = false;
    public BlockPos lastLavaContact;
    public int maxAirTicks = this.getDefaultMaxAirSupply();
    public boolean visibleByDefault = true;
    private CraftEntity bukkitEntity;
    private transient PositionImpl arclight$tpPos;

    @Shadow
    public abstract Level m_9236_();

    @Shadow
    public abstract float m_146908_();

    @Shadow
    public abstract float m_146909_();

    @Shadow
    public abstract void m_146922_(float var1);

    @Shadow
    public abstract void m_146926_(float var1);

    @Shadow
    public abstract Pose m_20089_();

    @Shadow
    public abstract String m_6302_();

    @Shadow
    protected abstract void m_20157_();

    @Shadow
    public abstract boolean m_5825_();

    @Shadow
    public abstract boolean m_6469_(DamageSource var1, float var2);

    @Shadow
    public abstract void m_20254_(int var1);

    @Shadow
    protected abstract Vec3 m_20272_(Vec3 var1);

    @Shadow
    public abstract boolean m_6069_();

    @Shadow
    public abstract boolean m_6084_();

    @Shadow
    public abstract void m_19877_();

    @Shadow
    @Nullable
    public abstract MinecraftServer m_20194_();

    @Shadow
    public abstract Vec3 m_20184_();

    @Shadow
    public abstract EntityType<?> m_6095_();

    @Shadow
    public abstract float m_20205_();

    @Shadow
    public abstract float m_20206_();

    @Shadow
    public abstract boolean m_20145_();

    @Shadow
    public abstract boolean m_6673_(DamageSource var1);

    @Shadow
    public abstract void m_5496_(SoundEvent var1, float var2, float var3);

    @Shadow
    public abstract void m_6021_(double var1, double var3, double var5);

    @Shadow
    @Nullable
    public abstract ItemEntity m_19983_(ItemStack var1);

    @Shadow
    public abstract SynchedEntityData m_20088_();

    @Shadow
    public void m_8119_() {
    }

    @Shadow
    public abstract AABB m_20191_();

    @Shadow(remap=false)
    public abstract Collection<ItemEntity> captureDrops();

    @Shadow(remap=false)
    public abstract Collection<ItemEntity> captureDrops(Collection<ItemEntity> var1);

    @Shadow
    public abstract BlockPos m_20183_();

    @Shadow
    public abstract boolean m_20069_();

    @Shadow
    public abstract boolean m_20159_();

    @Shadow
    public abstract boolean m_20142_();

    @Shadow
    public abstract boolean m_7307_(Entity var1);

    @Shadow
    public abstract void m_20256_(Vec3 var1);

    @Shadow
    public abstract double m_20280_(Entity var1);

    @Shadow
    protected abstract void m_5834_();

    @Shadow
    public abstract void m_20153_();

    @Shadow
    public abstract boolean m_8077_();

    @Shadow
    public abstract void m_6034_(double var1, double var3, double var5);

    @Shadow
    protected abstract void m_19915_(float var1, float var2);

    @Shadow
    public abstract boolean m_20068_();

    @Shadow
    protected abstract void m_20101_();

    @Shadow
    public abstract boolean m_20160_();

    @Shadow
    public abstract boolean m_20363_(Entity var1);

    @Shadow
    public abstract void m_20334_(double var1, double var3, double var5);

    @Shadow
    public abstract void m_6478_(MoverType var1, Vec3 var2);

    @Shadow
    @Nullable
    public abstract Entity m_20202_();

    @Shadow
    @Nullable
    public abstract Team m_5647_();

    @Shadow
    public abstract void m_20095_();

    @Shadow
    public abstract void m_20115_(int var1, boolean var2);

    @Shadow
    public abstract void m_7678_(double var1, double var3, double var5, float var7, float var8);

    @Shadow
    public abstract int m_19879_();

    @Shadow
    @Nullable
    public abstract Component m_7770_();

    @Shadow
    public abstract void m_19970_(net.minecraft.world.entity.LivingEntity var1, Entity var2);

    @Shadow
    @Nullable
    public abstract Entity m_5489_(ServerLevel var1);

    @Shadow
    public abstract boolean m_20365_(Entity var1);

    @Shadow
    public abstract boolean m_20147_();

    @Shadow
    public abstract double m_20185_();

    @Shadow
    public abstract double m_20189_();

    @Shadow
    public abstract double m_20186_();

    @Shadow
    public abstract double m_20188_();

    @Shadow
    public abstract Vec3 m_20182_();

    @Shadow(remap=false)
    public abstract void revive();

    @Shadow
    public abstract boolean m_6094_();

    @Shadow
    protected abstract void m_6089_();

    @Shadow
    protected abstract Vec3 m_7643_(Direction.Axis var1, BlockUtil.FoundRectangle var2);

    @Shadow
    public abstract EntityDimensions m_6972_(Pose var1);

    @Shadow
    protected abstract boolean m_20073_();

    @Shadow
    public abstract boolean m_20077_();

    @Shadow
    public abstract void m_20093_();

    @Shadow
    public abstract boolean m_20067_();

    @Shadow
    public abstract void m_6842_(boolean var1);

    @Shadow
    public abstract boolean m_213877_();

    @Shadow
    public void m_142687_(Entity.RemovalReason p_146834_) {
    }

    @Shadow
    public abstract void m_146870_();

    @Shadow
    protected abstract void m_146912_();

    @Shadow
    public abstract double m_20227_(double var1);

    @Shadow
    public abstract void m_146852_(GameEvent var1, @org.jetbrains.annotations.Nullable Entity var2);

    @Shadow
    public abstract void m_146917_(int var1);

    @Shadow
    public abstract void m_146868_(boolean var1);

    @Shadow
    public abstract int m_6062_();

    @Shadow
    public abstract int m_20146_();

    @Shadow
    public abstract void m_146850_(GameEvent var1);

    @Shadow
    protected abstract SoundEvent m_5501_();

    @Shadow
    protected abstract SoundEvent m_5509_();

    @Shadow
    protected abstract SoundEvent m_5508_();

    @Shadow
    public abstract boolean m_6144_();

    @Shadow
    public abstract DamageSources m_269291_();

    @Shadow
    @Nullable
    public abstract Entity m_146895_();

    @Shadow
    public abstract boolean m_264318_(ServerLevel var1, double var2, double var4, double var6, Set<RelativeMovement> var8, float var9, float var10);

    @Shadow
    public abstract boolean m_5833_();

    @Shadow
    public abstract SoundSource m_5720_();

    @Shadow
    public abstract int m_287157_();

    @Shadow
    public abstract void m_146871_();

    @Shadow
    protected abstract void m_284535_(Level var1);

    public CraftEntity getBukkitEntity() {
        return this.internal$getBukkitEntity();
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.internal$getBukkitEntity();
    }

    @Override
    public CraftEntity bridge$getBukkitEntity() {
        return this.internal$getBukkitEntity();
    }

    @Override
    public void bridge$setBukkitEntity(CraftEntity bukkitEntity) {
        this.bukkitEntity = bukkitEntity;
    }

    @Override
    public CraftEntity internal$getBukkitEntity() {
        if (this.bukkitEntity == null) {
            this.bukkitEntity = CraftEntity.getEntity((CraftServer)Bukkit.getServer(), (Entity)this);
        }
        return this.bukkitEntity;
    }

    public float getBukkitYaw() {
        return this.m_146908_();
    }

    @Override
    public float bridge$getBukkitYaw() {
        return this.getBukkitYaw();
    }

    public boolean isChunkLoaded() {
        return this.m_9236_().m_7232_((int)Math.floor(this.m_20185_()) >> 4, (int)Math.floor(this.m_20189_()) >> 4);
    }

    @Override
    public boolean bridge$isChunkLoaded() {
        return this.isChunkLoaded();
    }

    public int getDefaultMaxAirSupply() {
        return 300;
    }

    public SoundEvent getSwimSound0() {
        return this.m_5501_();
    }

    public SoundEvent getSwimSplashSound0() {
        return this.m_5509_();
    }

    public SoundEvent getSwimHighSpeedSplashSound0() {
        return this.m_5508_();
    }

    @Inject(method={"getMaxAirSupply"}, cancellable=true, at={@At(value="RETURN")})
    private void arclight$useBukkitMaxAir(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((Object)this.maxAirTicks);
    }

    @Inject(method={"setPose"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/network/syncher/SynchedEntityData;set(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)V")})
    public void arclight$setPose$EntityPoseChangeEvent(Pose poseIn, CallbackInfo callbackInfo) {
        if (poseIn == this.m_20089_()) {
            callbackInfo.cancel();
            return;
        }
        EntityPoseChangeEvent event = new EntityPoseChangeEvent(this.internal$getBukkitEntity(), BukkitRegistry.toBukkitPose(poseIn));
        if (this.valid) {
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @Inject(method={"setRot"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$infCheck(float yaw, float pitch, CallbackInfo ci) {
        if (Float.isNaN(yaw)) {
            this.f_19857_ = 0.0f;
            ci.cancel();
        }
        if (yaw == Float.POSITIVE_INFINITY || yaw == Float.NEGATIVE_INFINITY) {
            if (this instanceof Player) {
                Bukkit.getLogger().warning(this.m_6302_() + " was caught trying to crash the server with an invalid yaw");
                ((CraftPlayer)this.getBukkitEntity()).kickPlayer("Infinite yaw (Are you hacking?)");
            }
            this.f_19857_ = 0.0f;
            ci.cancel();
        }
        if (Float.isNaN(pitch)) {
            this.f_19858_ = 0.0f;
            ci.cancel();
        }
        if (pitch == Float.POSITIVE_INFINITY || pitch == Float.NEGATIVE_INFINITY) {
            if (this instanceof Player) {
                Bukkit.getLogger().warning(this.m_6302_() + " was caught trying to crash the server with an invalid pitch");
                ((CraftPlayer)this.getBukkitEntity()).kickPlayer("Infinite pitch (Are you hacking?)");
            }
            this.f_19858_ = 0.0f;
            ci.cancel();
        }
    }

    @Override
    public boolean bridge$isPersist() {
        return this.persist;
    }

    @Override
    public void bridge$setPersist(boolean persist) {
        this.persist = persist;
    }

    public boolean isValid() {
        return this.valid;
    }

    @Override
    public boolean bridge$isValid() {
        return this.isValid();
    }

    @Override
    public void bridge$setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public ProjectileSource bridge$getProjectileSource() {
        return this.projectileSource;
    }

    @Override
    public void bridge$setProjectileSource(ProjectileSource projectileSource) {
        this.projectileSource = projectileSource;
    }

    @Override
    public boolean bridge$isLastDamageCancelled() {
        return this.lastDamageCancelled;
    }

    @Override
    public void bridge$setLastDamageCancelled(boolean cancelled) {
        this.lastDamageCancelled = cancelled;
    }

    public void postTick() {
        if (!(this instanceof ServerPlayer)) {
            this.m_20157_();
        }
    }

    @Override
    public void bridge$postTick() {
        this.postTick();
    }

    @Redirect(method={"baseTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;handleNetherPortal()V"))
    public void arclight$baseTick$moveToPostTick(Entity entity) {
        if (this instanceof ServerPlayer) {
            this.m_20157_();
        }
    }

    @Redirect(method={"updateFluidHeightAndDoFluidPushing(Ljava/util/function/Predicate;)V"}, remap=false, at=@At(value="INVOKE", remap=true, target="Lnet/minecraft/world/level/material/FluidState;getFlow(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 arclight$setLava(FluidState instance, BlockGetter level, BlockPos pos) {
        if (instance.m_76152_().m_205067_(FluidTags.f_13132_)) {
            this.lastLavaContact = pos.m_7949_();
        }
        return instance.m_76179_(level, pos);
    }

    @Redirect(method={"baseTick"}, at=@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/entity/Entity;isInLava()Z"))
    private boolean arclight$resetLava(Entity instance) {
        boolean ret = instance.m_20077_();
        if (!ret) {
            this.lastLavaContact = null;
        }
        return ret;
    }

    @Redirect(method={"lavaHurt"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    public void arclight$setOnFireFromLava$bukkitEvent(Entity entity, int seconds) {
        CraftBlock damager = this.lastLavaContact == null ? null : CraftBlock.at((LevelAccessor)this.m_9236_(), this.lastLavaContact);
        CraftEventFactory.blockDamage = damager;
        if (this instanceof net.minecraft.world.entity.LivingEntity && this.f_19831_ <= 0) {
            CraftEntity damagee = this.getBukkitEntity();
            EntityCombustByBlockEvent combustEvent = new EntityCombustByBlockEvent(damager, damagee, 15);
            Bukkit.getPluginManager().callEvent(combustEvent);
            if (!combustEvent.isCancelled()) {
                this.m_20254_(combustEvent.getDuration());
            }
        } else {
            this.m_20254_(15);
        }
    }

    @Inject(method={"lavaHurt"}, at={@At(value="RETURN")})
    private void arclight$resetBlockDamage(CallbackInfo ci) {
        CraftEventFactory.blockDamage = null;
    }

    public void setSecondsOnFire(int seconds, boolean callEvent) {
        if (callEvent) {
            EntityCombustEvent event = new EntityCombustEvent(this.getBukkitEntity(), seconds);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            seconds = event.getDuration();
        }
        this.m_20254_(seconds);
    }

    @Override
    public void bridge$setOnFire(int tick, boolean callEvent) {
        this.setSecondsOnFire(tick, callEvent);
    }

    @ModifyArg(method={"move"}, index=1, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/Block;stepOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/Entity;)V"))
    private BlockPos arclight$captureBlockWalk(BlockPos pos) {
        ArclightCaptures.captureDamageEventBlock(pos);
        return pos;
    }

    @Inject(method={"move"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/block/Block;stepOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/Entity;)V")})
    private void arclight$resetBlockWalk(MoverType typeIn, Vec3 pos, CallbackInfo ci) {
        ArclightCaptures.captureDamageEventBlock(null);
    }

    @Inject(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;onGround()Z")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/Block;updateEntityAfterFallOn(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/world/entity/Entity;)V"))})
    private void arclight$move$blockCollide(MoverType typeIn, Vec3 pos, CallbackInfo ci) {
        CraftEntity craftEntity;
        if (this.f_19862_ && (craftEntity = this.bridge$getBukkitEntity()) instanceof Vehicle) {
            Vehicle vehicle = (Vehicle)((Object)craftEntity);
            Block block = ((WorldBridge)this.m_9236_()).bridge$getWorld().getBlockAt(Mth.m_14107_((double)this.m_20185_()), Mth.m_14107_((double)this.m_20186_()), Mth.m_14107_((double)this.m_20189_()));
            Vec3 vec3d = this.m_20272_(pos);
            if (pos.f_82479_ > vec3d.f_82479_) {
                block = block.getRelative(BlockFace.EAST);
            } else if (vec3d.f_82479_ < vec3d.f_82479_) {
                block = block.getRelative(BlockFace.WEST);
            } else if (pos.f_82481_ > vec3d.f_82481_) {
                block = block.getRelative(BlockFace.SOUTH);
            } else if (pos.f_82481_ < vec3d.f_82481_) {
                block = block.getRelative(BlockFace.NORTH);
            }
            if (block.getType() != Material.AIR) {
                VehicleBlockCollisionEvent event = new VehicleBlockCollisionEvent(vehicle, block);
                Bukkit.getPluginManager().callEvent(event);
            }
        }
    }

    @Inject(method={"absMoveTo(DDDFF)V"}, at={@At(value="RETURN")})
    private void arclight$loadChunk(double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        if (this.valid) {
            this.m_9236_().m_6325_((int)Math.floor(this.m_20185_()) >> 4, (int)Math.floor(this.m_20189_()) >> 4);
        }
    }

    public boolean canCollideWith(Entity entity) {
        return this.m_6094_();
    }

    @Override
    public boolean bridge$canCollideWith(Entity entity) {
        return this.canCollideWith(entity);
    }

    @Inject(method={"saveAsPassenger"}, cancellable=true, at={@At(value="INVOKE_ASSIGN", target="Lnet/minecraft/world/entity/Entity;getEncodeId()Ljava/lang/String;")})
    public void arclight$writeUnlessRemoved$persistCheck(CompoundTag compound, CallbackInfoReturnable<Boolean> cir) {
        if (!this.persist) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"saveWithoutId"}, at={@At(value="INVOKE_ASSIGN", ordinal=1, target="Lnet/minecraft/nbt/CompoundTag;put(Ljava/lang/String;Lnet/minecraft/nbt/Tag;)Lnet/minecraft/nbt/Tag;")})
    public void arclight$writeWithoutTypeId$InfiniteValueCheck(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        if (Float.isNaN(this.m_146908_())) {
            this.f_19857_ = 0.0f;
        }
        if (Float.isNaN(this.m_146909_())) {
            this.f_19858_ = 0.0f;
        }
    }

    @Inject(method={"saveWithoutId"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, ordinal=0, target="Lnet/minecraft/nbt/CompoundTag;putUUID(Ljava/lang/String;Ljava/util/UUID;)V")})
    public void arclight$writeWithoutTypeId$CraftBukkitNBT(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        compound.m_128356_("WorldUUIDLeast", ((WorldBridge)this.m_9236_()).bridge$getWorld().getUID().getLeastSignificantBits());
        compound.m_128356_("WorldUUIDMost", ((WorldBridge)this.m_9236_()).bridge$getWorld().getUID().getMostSignificantBits());
        compound.m_128405_("Bukkit.updateLevel", 2);
        compound.m_128405_("Spigot.ticksLived", this.f_19797_);
        if (!this.persist) {
            compound.m_128379_("Bukkit.persist", this.persist);
        }
        if (!this.visibleByDefault) {
            compound.m_128379_("Bukkit.visibleByDefault", this.visibleByDefault);
        }
        if (this.persistentInvisibility) {
            compound.m_128379_("Bukkit.invisible", this.persistentInvisibility);
        }
        if (this.maxAirTicks != this.getDefaultMaxAirSupply()) {
            compound.m_128405_("Bukkit.MaxAirSupply", this.m_6062_());
        }
    }

    @Inject(method={"saveWithoutId"}, at={@At(value="RETURN")})
    public void arclight$writeWithoutTypeId$StoreBukkitValues(CompoundTag compound, CallbackInfoReturnable<CompoundTag> cir) {
        if (this.bukkitEntity != null) {
            this.bukkitEntity.storeBukkitValues(compound);
        }
    }

    private static boolean isLevelAtLeast(CompoundTag tag, int level) {
        return tag.m_128441_("Bukkit.updateLevel") && tag.m_128451_("Bukkit.updateLevel") >= level;
    }

    @Inject(method={"load"}, at={@At(value="RETURN")})
    public void arclight$read$ReadBukkitValues(CompoundTag compound, CallbackInfo ci) {
        EntityMixin entityMixin = this;
        if (entityMixin instanceof net.minecraft.world.entity.LivingEntity) {
            net.minecraft.world.entity.LivingEntity entity = (net.minecraft.world.entity.LivingEntity)entityMixin;
            this.f_19797_ = compound.m_128451_("Spigot.ticksLived");
        }
        this.persist = !compound.m_128441_("Bukkit.persist") || compound.m_128471_("Bukkit.persist");
        boolean bl = this.visibleByDefault = !compound.m_128441_("Bukkit.visibleByDefault") || compound.m_128471_("Bukkit.visibleByDefault");
        if (this instanceof ServerPlayer) {
            Server server = Bukkit.getServer();
            World bworld = null;
            String worldName = compound.m_128461_("world");
            if (compound.m_128441_("WorldUUIDMost") && compound.m_128441_("WorldUUIDLeast")) {
                UUID uid = new UUID(compound.m_128454_("WorldUUIDMost"), compound.m_128454_("WorldUUIDLeast"));
                bworld = server.getWorld(uid);
            } else {
                bworld = server.getWorld(worldName);
            }
            if (bworld == null) {
                bworld = ((WorldBridge)((CraftServer)server).getServer().m_129880_(Level.f_46428_)).bridge$getWorld();
            }
            ((ServerPlayer)this).m_284127_(bworld == null ? null : ((CraftWorld)bworld).getHandle());
        }
        this.getBukkitEntity().readBukkitValues(compound);
        if (compound.m_128441_("Bukkit.invisible")) {
            boolean bukkitInvisible = compound.m_128471_("Bukkit.invisible");
            this.m_6842_(bukkitInvisible);
            this.persistentInvisibility = bukkitInvisible;
        }
        if (compound.m_128441_("Bukkit.MaxAirSupply")) {
            this.maxAirTicks = compound.m_128451_("Bukkit.MaxAirSupply");
        }
    }

    @Inject(method={"setInvisible"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$preventVisible(boolean invisible, CallbackInfo ci) {
        if (this.persistentInvisibility) {
            ci.cancel();
        }
    }

    @Redirect(method={"spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;"}, at=@At(value="INVOKE", remap=false, ordinal=0, target="Lnet/minecraft/world/entity/Entity;captureDrops()Ljava/util/Collection;"))
    public Collection<ItemEntity> arclight$forceDrops(Entity entity) {
        Collection drops = entity.captureDrops();
        if (this instanceof LivingEntityBridge && ((LivingEntityBridge)((Object)this)).bridge$isForceDrops()) {
            drops = null;
        }
        return drops;
    }

    @Inject(method={"spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    public void arclight$entityDropItem(ItemStack stack, float offsetY, CallbackInfoReturnable<ItemEntity> cir, ItemEntity itementity) {
        EntityDropItemEvent event = new EntityDropItemEvent(this.getBukkitEntity(), (Item)((Object)((EntityBridge)itementity).bridge$getBukkitEntity()));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue(null);
        }
    }

    @Redirect(method={"startRiding(Lnet/minecraft/world/entity/Entity;Z)Z"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;addPassenger(Lnet/minecraft/world/entity/Entity;)V"))
    private void arclight$startRiding(Entity entity, Entity pPassenger) {
        if (!((EntityBridge)entity).bridge$addPassenger(pPassenger)) {
            this.f_19824_ = null;
        }
    }

    @Redirect(method={"removeVehicle"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;removePassenger(Lnet/minecraft/world/entity/Entity;)V"))
    private void arclight$stopRiding(Entity entity, Entity passenger) {
        if (!((EntityBridge)entity).bridge$removePassenger(passenger)) {
            this.f_19824_ = entity;
        }
    }

    @Override
    public List<Entity> bridge$getPassengers() {
        return this.f_19823_;
    }

    @Override
    public boolean bridge$addPassenger(Entity entity) {
        return this.addPassenger(entity);
    }

    public boolean addPassenger(Entity entity) {
        Event event;
        Entity orig;
        if (entity.m_20202_() != this) {
            throw new IllegalStateException("Use x.startRiding(y), not y.addPassenger(x)");
        }
        Preconditions.checkState((!((EntityBridge)entity).bridge$getPassengers().contains(this) ? 1 : 0) != 0, (String)"Circular entity riding! %s %s", (Object)this, (Object)entity);
        CraftEntity craft = (CraftEntity)((EntityBridge)entity).bridge$getBukkitEntity().getVehicle();
        Entity entity2 = orig = craft == null ? null : craft.getHandle();
        if (this.getBukkitEntity() instanceof Vehicle && ((EntityBridge)entity).bridge$getBukkitEntity() instanceof LivingEntity) {
            CraftEntity craftn;
            Entity n;
            event = new VehicleEnterEvent((Vehicle)((Object)this.getBukkitEntity()), ((EntityBridge)entity).bridge$getBukkitEntity());
            if (this.valid) {
                Bukkit.getPluginManager().callEvent(event);
            }
            Entity entity3 = n = (craftn = (CraftEntity)((EntityBridge)entity).bridge$getBukkitEntity().getVehicle()) == null ? null : craftn.getHandle();
            if (((VehicleEnterEvent)event).isCancelled() || n != orig) {
                return false;
            }
        }
        event = new EntityMountEvent(((EntityBridge)entity).bridge$getBukkitEntity(), this.getBukkitEntity());
        if (this.valid) {
            Bukkit.getPluginManager().callEvent(event);
        }
        if (((EntityMountEvent)event).isCancelled()) {
            return false;
        }
        if (this.f_19823_.isEmpty()) {
            this.f_19823_ = ImmutableList.of((Object)entity);
        } else {
            ArrayList list = Lists.newArrayList(this.f_19823_);
            if (!this.m_9236_().f_46443_ && entity instanceof Player && !(this.m_146895_() instanceof Player)) {
                list.add(0, entity);
            } else {
                list.add(entity);
            }
            this.f_19823_ = ImmutableList.copyOf((Collection)list);
        }
        this.m_146852_(GameEvent.f_268500_, entity);
        return true;
    }

    @Override
    public boolean bridge$removePassenger(Entity entity) {
        return this.removePassenger(entity);
    }

    public boolean removePassenger(Entity entity) {
        Event event;
        Entity orig;
        if (entity.m_20202_() == this) {
            throw new IllegalStateException("Use x.stopRiding(y), not y.removePassenger(x)");
        }
        CraftEntity craft = (CraftEntity)((EntityBridge)entity).bridge$getBukkitEntity().getVehicle();
        Entity entity2 = orig = craft == null ? null : craft.getHandle();
        if (this.getBukkitEntity() instanceof Vehicle && ((EntityBridge)entity).bridge$getBukkitEntity() instanceof LivingEntity) {
            CraftEntity craftn;
            Entity n;
            event = new VehicleExitEvent((Vehicle)((Object)this.getBukkitEntity()), (LivingEntity)((Object)((EntityBridge)entity).bridge$getBukkitEntity()));
            if (this.valid) {
                Bukkit.getPluginManager().callEvent(event);
            }
            Entity entity3 = n = (craftn = (CraftEntity)((EntityBridge)entity).bridge$getBukkitEntity().getVehicle()) == null ? null : craftn.getHandle();
            if (((VehicleExitEvent)event).isCancelled() || n != orig) {
                return false;
            }
        }
        event = new EntityDismountEvent(((EntityBridge)entity).bridge$getBukkitEntity(), this.getBukkitEntity());
        if (this.valid) {
            Bukkit.getPluginManager().callEvent(event);
        }
        if (((EntityDismountEvent)event).isCancelled()) {
            return false;
        }
        this.f_19823_ = this.f_19823_.size() == 1 && this.f_19823_.get(0) == entity ? ImmutableList.of() : (ImmutableList)this.f_19823_.stream().filter(entity1 -> entity1 != entity).collect(ImmutableList.toImmutableList());
        entity.f_19851_ = 60;
        this.m_146852_(GameEvent.f_268533_, entity);
        return true;
    }

    @Inject(method={"handleNetherPortal"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;changeDimension(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/entity/Entity;")})
    public void arclight$changeDimension(CallbackInfo ci) {
        if (this instanceof ServerPlayerEntityBridge) {
            ((ServerPlayerEntityBridge)((Object)this)).bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL);
        }
    }

    @Inject(method={"setSwimming"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$setSwimming$EntityToggleSwimEvent(boolean flag, CallbackInfo ci) {
        if (this.isValid() && this.m_6069_() != flag && this instanceof net.minecraft.world.entity.LivingEntity && CraftEventFactory.callToggleSwimEvent((net.minecraft.world.entity.LivingEntity)this, flag).isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"setAirSupply"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$setAir$EntityAirChangeEvent(int air, CallbackInfo ci) {
        EntityAirChangeEvent event = new EntityAirChangeEvent(this.getBukkitEntity(), air);
        if (this.valid) {
            event.getEntity().getServer().getPluginManager().callEvent(event);
        }
        if (event.isCancelled() && this.m_20146_() != -1) {
            ci.cancel();
            ((SynchedEntityDataBridge)this.m_20088_()).bridge$markDirty(f_19832_);
            return;
        }
        this.f_19804_.m_135381_(f_19832_, (Object)event.getAmount());
    }

    @Redirect(method={"thunderHit"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    public void arclight$onStruckByLightning$EntityCombustByEntityEvent0(Entity entity, int seconds) {
        CraftEntity thisBukkitEntity = this.getBukkitEntity();
        CraftEntity stormBukkitEntity = ((EntityBridge)entity).bridge$getBukkitEntity();
        PluginManager pluginManager = Bukkit.getPluginManager();
        EntityCombustByEntityEvent entityCombustEvent = new EntityCombustByEntityEvent(stormBukkitEntity, thisBukkitEntity, 8);
        pluginManager.callEvent(entityCombustEvent);
        if (!entityCombustEvent.isCancelled()) {
            this.m_20254_(entityCombustEvent.getDuration());
        }
    }

    @Redirect(method={"thunderHit"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    public boolean arclight$onStruckByLightning$EntityCombustByEntityEvent1(Entity entity, DamageSource source, float amount) {
        CraftEntity thisBukkitEntity = this.getBukkitEntity();
        CraftEntity stormBukkitEntity = ((EntityBridge)entity).bridge$getBukkitEntity();
        PluginManager pluginManager = Bukkit.getPluginManager();
        if (thisBukkitEntity instanceof Hanging) {
            HangingBreakByEntityEvent hangingEvent = new HangingBreakByEntityEvent((Hanging)((Object)thisBukkitEntity), stormBukkitEntity);
            pluginManager.callEvent(hangingEvent);
            if (hangingEvent.isCancelled()) {
                return false;
            }
        }
        if (this.m_5825_()) {
            return false;
        }
        CraftEventFactory.entityDamage = entity;
        if (!this.m_6469_(this.m_269291_().m_269548_(), amount)) {
            CraftEventFactory.entityDamage = null;
            return false;
        }
        return true;
    }

    @Override
    public void bridge$setRideCooldown(int rideCooldown) {
        this.f_19851_ = rideCooldown;
    }

    @Override
    public int bridge$getRideCooldown() {
        return this.f_19851_;
    }

    public Entity teleportTo(ServerLevel world, PositionImpl blockPos) {
        this.arclight$tpPos = blockPos;
        return this.m_5489_(world);
    }

    public boolean teleportTo(ServerLevel worldserver, double d0, double d1, double d2, Set<RelativeMovement> set, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        return this.m_264318_(worldserver, d0, d1, d2, set, f, f1);
    }

    @Override
    public Entity bridge$teleportTo(ServerLevel world, PositionImpl blockPos) {
        return this.teleportTo(world, blockPos);
    }

    @Overwrite(remap=false)
    @Nullable
    public Entity changeDimension(ServerLevel server, ITeleporter teleporter) {
        if (!ForgeHooks.onTravelToDimension((Entity)((Entity)this), (ResourceKey)server.m_46472_())) {
            return null;
        }
        if (this.m_9236_() instanceof ServerLevel && !this.m_213877_()) {
            ServerLevel world;
            PortalInfo portalinfo;
            this.m_9236_().m_46473_().m_6180_("changeDimension");
            if (server == null) {
                return null;
            }
            this.m_9236_().m_46473_().m_6180_("reposition");
            PositionImpl bukkitPos = this.arclight$tpPos;
            this.arclight$tpPos = null;
            PortalInfo portalInfo = portalinfo = bukkitPos == null ? teleporter.getPortalInfo((Entity)this, server, this::m_7937_) : new PortalInfo(new Vec3(bukkitPos.m_7096_(), bukkitPos.m_7098_(), bukkitPos.m_7094_()), Vec3.f_82478_, this.f_19857_, this.f_19858_);
            if (portalinfo == null) {
                return null;
            }
            ServerLevel serverLevel = world = ((PortalInfoBridge)portalinfo).bridge$getWorld() == null ? server : ((PortalInfoBridge)portalinfo).bridge$getWorld();
            if (world == this.m_9236_()) {
                this.m_7678_(portalinfo.f_77676_.f_82479_, portalinfo.f_77676_.f_82480_, portalinfo.f_77676_.f_82481_, portalinfo.f_77678_, this.m_146909_());
                this.m_20256_(portalinfo.f_77677_);
                return (Entity)this;
            }
            this.m_19877_();
            Entity transportedEntity = teleporter.placeEntity((Entity)this, (ServerLevel)this.m_9236_(), world, this.m_146908_(), spawnPortal -> {
                this.m_9236_().m_46473_().m_6182_("reloading");
                Entity entity = this.m_6095_().m_20615_((Level)world);
                if (entity != null) {
                    entity.m_20361_((Entity)this);
                    entity.m_7678_(portalinfo.f_77676_.f_82479_, portalinfo.f_77676_.f_82480_, portalinfo.f_77676_.f_82481_, portalinfo.f_77678_, entity.m_146909_());
                    entity.m_20256_(portalinfo.f_77677_);
                    world.m_143334_(entity);
                    if (((WorldBridge)world).bridge$getTypeKey() == LevelStem.f_63973_ && Level.f_46430_ != null) {
                        ArclightCaptures.captureEndPortalEntity((Entity)this, spawnPortal);
                        ServerLevel.m_8617_((ServerLevel)world);
                    }
                }
                return entity;
            });
            this.m_6089_();
            this.m_9236_().m_46473_().m_7238_();
            ((ServerLevel)this.m_9236_()).m_8886_();
            world.m_8886_();
            this.m_9236_().m_46473_().m_7238_();
            return transportedEntity;
        }
        return null;
    }

    @Inject(method={"restoreFrom"}, at={@At(value="HEAD")})
    private void arclight$forwardHandle(Entity entityIn, CallbackInfo ci) {
        ((InternalEntityBridge)entityIn).internal$getBukkitEntity().setHandle((Entity)this);
        this.bridge$setBukkitEntity(((InternalEntityBridge)entityIn).internal$getBukkitEntity());
        if (entityIn instanceof Mob) {
            ((Mob)entityIn).m_21455_(true, false);
        }
    }

    @Nullable
    @Overwrite
    protected PortalInfo m_7937_(ServerLevel world) {
        boolean flag1;
        if (world == null) {
            return null;
        }
        boolean flag = ((WorldBridge)this.m_9236_()).bridge$getTypeKey() == LevelStem.f_63973_ && ((WorldBridge)world).bridge$getTypeKey() == LevelStem.f_63971_;
        boolean bl = flag1 = ((WorldBridge)world).bridge$getTypeKey() == LevelStem.f_63973_;
        if (!flag && !flag1) {
            boolean flag2;
            boolean bl2 = flag2 = ((WorldBridge)world).bridge$getTypeKey() == LevelStem.f_63972_;
            if (this.m_9236_().m_46472_() != Level.f_46429_ && !flag2) {
                return null;
            }
            WorldBorder worldborder = world.m_6857_();
            double d0 = DimensionType.m_63908_((DimensionType)this.m_9236_().m_6042_(), (DimensionType)world.m_6042_());
            BlockPos blockpos1 = worldborder.m_187569_(this.m_20185_() * d0, this.m_20186_(), this.m_20189_() * d0);
            CraftPortalEvent event = this.callPortalEvent((Entity)this, world, new PositionImpl((double)blockpos1.m_123341_(), (double)blockpos1.m_123342_(), (double)blockpos1.m_123343_()), PlayerTeleportEvent.TeleportCause.NETHER_PORTAL, flag2 ? 16 : 128, 16);
            if (event == null) {
                return null;
            }
            ServerLevel worldFinal = world = ((CraftWorld)event.getTo().getWorld()).getHandle();
            blockpos1 = worldFinal.m_6857_().m_187569_(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ());
            return this.getExitPortal(world, blockpos1, flag2, worldborder, event.getSearchRadius(), event.getCanCreatePortal(), event.getCreationRadius()).map(result -> {
                Vec3 vector3d;
                Direction.Axis direction$axis;
                BlockState blockstate = this.m_9236_().m_8055_(this.f_19819_);
                if (blockstate.m_61138_((Property)BlockStateProperties.f_61364_)) {
                    direction$axis = (Direction.Axis)blockstate.m_61143_((Property)BlockStateProperties.f_61364_);
                    BlockUtil.FoundRectangle teleportationrepositioner$result = BlockUtil.m_124334_((BlockPos)this.f_19819_, (Direction.Axis)direction$axis, (int)21, (Direction.Axis)Direction.Axis.Y, (int)21, pos -> this.m_9236_().m_8055_(pos) == blockstate);
                    vector3d = this.m_7643_(direction$axis, teleportationrepositioner$result);
                } else {
                    direction$axis = Direction.Axis.X;
                    vector3d = new Vec3(0.5, 0.0, 0.0);
                }
                ArclightCaptures.captureCraftPortalEvent(event);
                return PortalShape.m_257966_((ServerLevel)worldFinal, (BlockUtil.FoundRectangle)result, (Direction.Axis)direction$axis, (Vec3)vector3d, (Entity)((Entity)this), (Vec3)this.m_20184_(), (float)this.m_146908_(), (float)this.m_146909_());
            }).orElse(null);
        }
        BlockPos blockpos = flag1 ? ServerLevel.f_8562_ : world.m_5452_(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, world.m_220360_());
        CraftPortalEvent event = this.callPortalEvent((Entity)this, world, new PositionImpl((double)blockpos.m_123341_() + 0.5, (double)blockpos.m_123342_(), (double)blockpos.m_123343_() + 0.5), PlayerTeleportEvent.TeleportCause.END_PORTAL, 0, 0);
        if (event == null) {
            return null;
        }
        PortalInfo portalInfo = new PortalInfo(new Vec3(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ()), this.m_20184_(), this.m_146908_(), this.m_146909_());
        ((PortalInfoBridge)portalInfo).bridge$setWorld(((CraftWorld)event.getTo().getWorld()).getHandle());
        ((PortalInfoBridge)portalInfo).bridge$setPortalEventInfo(event);
        return portalInfo;
    }

    protected CraftPortalEvent callPortalEvent(Entity entity, ServerLevel exitWorldServer, PositionImpl exitPosition, PlayerTeleportEvent.TeleportCause cause, int searchRadius, int creationRadius) {
        CraftEntity bukkitEntity = ((EntityBridge)entity).bridge$getBukkitEntity();
        Location enter = bukkitEntity.getLocation();
        Location exit = new Location(((WorldBridge)exitWorldServer).bridge$getWorld(), exitPosition.m_7096_(), exitPosition.m_7098_(), exitPosition.m_7094_());
        EntityPortalEvent event = new EntityPortalEvent(bukkitEntity, enter, exit, searchRadius);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled() || event.getTo() == null || event.getTo().getWorld() == null || !entity.m_6084_()) {
            return null;
        }
        return new CraftPortalEvent(event);
    }

    protected Optional<BlockUtil.FoundRectangle> getExitPortal(ServerLevel serverWorld, BlockPos pos, boolean flag, WorldBorder worldborder, int searchRadius, boolean canCreatePortal, int createRadius) {
        return ((TeleporterBridge)serverWorld.m_8871_()).bridge$findPortal(pos, worldborder, searchRadius);
    }
}

