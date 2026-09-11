/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent$ILivingTargetType
 *  net.minecraftforge.event.entity.living.LivingChangeTargetEvent$LivingTargetType
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.LivingEntityMixin;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.mixin.Eject;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Mob.class})
public abstract class MobMixin
extends LivingEntityMixin
implements MobEntityBridge {
    @Shadow
    public boolean f_21353_;
    @Shadow
    private LivingEntity f_21362_;
    @Shadow
    @Final
    public float[] f_21347_;
    @Shadow
    @Final
    public float[] f_21348_;
    public boolean aware;
    protected transient boolean arclight$targetSuccess = false;
    private transient EntityTargetEvent.TargetReason arclight$reason;
    private transient boolean arclight$fireEvent;
    private transient ItemEntity arclight$item;
    private transient EntityTransformEvent.TransformReason arclight$transform;

    @Shadow
    public abstract boolean m_6785_(double var1);

    @Shadow
    @Nullable
    public abstract LivingEntity m_5448_();

    @Shadow
    protected abstract ResourceLocation m_7582_();

    @Override
    @Shadow
    public abstract ItemStack m_6844_(EquipmentSlot var1);

    @Shadow
    public abstract boolean m_7252_(ItemStack var1);

    @Shadow
    protected abstract float m_21519_(EquipmentSlot var1);

    @Override
    @Shadow
    public abstract void m_8061_(EquipmentSlot var1, ItemStack var2);

    @Shadow
    @Nullable
    public abstract net.minecraft.world.entity.Entity m_21524_();

    @Shadow
    public abstract boolean m_21532_();

    @Shadow
    protected void m_8024_() {
    }

    @Shadow
    public abstract boolean m_21525_();

    @Shadow
    protected abstract boolean m_7808_(ItemStack var1, ItemStack var2);

    @Shadow
    protected abstract void m_21468_(EquipmentSlot var1, ItemStack var2);

    @Shadow
    @Nullable
    public abstract <T extends Mob> T m_21406_(EntityType<T> var1, boolean var2);

    @Shadow
    @Nullable
    protected abstract SoundEvent m_7515_();

    @Override
    public void bridge$setAware(boolean aware) {
        this.aware = aware;
    }

    @Inject(method={"setCanPickUpLoot"}, at={@At(value="HEAD")})
    public void arclight$setPickupLoot(boolean canPickup, CallbackInfo ci) {
        this.bukkitPickUpLoot = canPickup;
    }

    @Overwrite
    public boolean m_21531_() {
        return this.bukkitPickUpLoot;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends Mob> type, Level worldIn, CallbackInfo ci) {
        this.aware = true;
    }

    public SoundEvent getAmbientSound0() {
        return this.m_7515_();
    }

    @Overwrite
    public void m_6710_(@Nullable LivingEntity livingEntity) {
        boolean fireEvent = this.arclight$fireEvent;
        this.arclight$fireEvent = false;
        EntityTargetEvent.TargetReason reason = this.arclight$reason == null ? EntityTargetEvent.TargetReason.UNKNOWN : this.arclight$reason;
        this.arclight$reason = null;
        if (this.m_5448_() == livingEntity) {
            this.arclight$targetSuccess = false;
            return;
        }
        if (fireEvent) {
            if (reason == EntityTargetEvent.TargetReason.UNKNOWN && this.m_5448_() != null && livingEntity == null) {
                reason = this.m_5448_().m_6084_() ? EntityTargetEvent.TargetReason.FORGOT_TARGET : EntityTargetEvent.TargetReason.TARGET_DIED;
            }
            if (Bukkit.getPluginManager().getPlugins().length > 0) {
                CraftLivingEntity ctarget = null;
                if (livingEntity != null) {
                    ctarget = ((LivingEntityBridge)livingEntity).bridge$getBukkitEntity();
                }
                EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent((Entity)this.getBukkitEntity(), ctarget, reason);
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    this.arclight$targetSuccess = false;
                    return;
                }
                livingEntity = event.getTarget() != null ? ((CraftLivingEntity)event.getTarget()).getHandle() : null;
            }
            LivingChangeTargetEvent changeTargetEvent = ForgeHooks.onLivingChangeTarget((LivingEntity)(Object)this, (LivingEntity)livingEntity, (LivingChangeTargetEvent.ILivingTargetType)LivingChangeTargetEvent.LivingTargetType.MOB_TARGET);
            if (changeTargetEvent.isCanceled()) {
                this.arclight$targetSuccess = false;
                return;
            }
            livingEntity = changeTargetEvent.getNewTarget();
        }
        this.f_21362_ = livingEntity;
        this.arclight$targetSuccess = true;
    }

    public boolean setTarget(LivingEntity livingEntity, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        this.bridge$pushGoalTargetReason(reason, fireEvent);
        this.m_6710_(livingEntity);
        return this.arclight$targetSuccess;
    }

    @Override
    public boolean bridge$lastGoalTargetResult() {
        return this.arclight$targetSuccess;
    }

    @Override
    public boolean bridge$setGoalTarget(LivingEntity livingEntity, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        return this.setTarget(livingEntity, reason, fireEvent);
    }

    @Override
    public void bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        this.arclight$reason = fireEvent ? reason : null;
        this.arclight$fireEvent = fireEvent;
    }

    @Inject(method={"addAdditionalSaveData"}, at={@At(value="HEAD")})
    private void arclight$setAware(CompoundTag compound, CallbackInfo ci) {
        compound.m_128379_("Bukkit.Aware", this.aware);
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="HEAD")})
    private void arclight$readAware(CompoundTag compound, CallbackInfo ci) {
        if (compound.m_128441_("Bukkit.Aware")) {
            this.aware = compound.m_128471_("Bukkit.Aware");
        }
    }

    @Redirect(method={"readAdditionalSaveData"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;setCanPickUpLoot(Z)V"))
    public void arclight$setIfTrue(Mob mobEntity, boolean canPickup) {
        if (canPickup) {
            mobEntity.m_21553_(true);
        }
    }

    @Inject(method={"serverAiStep"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$unaware(CallbackInfo ci) {
        if (!this.aware) {
            ++this.f_20891_;
            ci.cancel();
        }
    }

    @Inject(method={"pickUpItem"}, at={@At(value="HEAD")})
    private void arclight$captureItemEntity(ItemEntity itemEntity, CallbackInfo ci) {
        this.arclight$item = itemEntity;
    }

    @Override
    public void bridge$captureItemDrop(ItemEntity itemEntity) {
        this.arclight$item = itemEntity;
    }

    @Overwrite
    public ItemStack m_255207_(ItemStack stack) {
        boolean canPickup;
        ItemEntity itemEntity = this.arclight$item;
        this.arclight$item = null;
        EquipmentSlot equipmentslottype = MobMixin.m_147233_(stack);
        ItemStack itemstack = this.m_6844_(equipmentslottype);
        boolean flag = this.m_7808_(stack, itemstack);
        if (equipmentslottype.m_254934_() && !flag) {
            equipmentslottype = EquipmentSlot.MAINHAND;
            itemstack = this.m_6844_(equipmentslottype);
            flag = itemstack.m_41619_();
        }
        boolean bl = canPickup = flag && this.m_7252_(stack);
        if (itemEntity != null) {
            boolean bl2 = canPickup = !CraftEventFactory.callEntityPickupItemEvent((net.minecraft.world.entity.Entity)(Object)this, itemEntity, 0, !canPickup).isCancelled();
        }
        if (canPickup) {
            double d0 = this.m_21519_(equipmentslottype);
            if (!itemstack.m_41619_() && (double)Math.max(this.f_19796_.m_188501_() - 0.1f, 0.0f) < d0) {
                this.forceDrops = true;
                this.m_19983_(itemstack);
                this.forceDrops = false;
            }
            if (equipmentslottype.m_254934_() && stack.m_41613_() > 1) {
                ItemStack itemstack1 = stack.m_255036_(1);
                this.m_21468_(equipmentslottype, itemstack1);
                return itemstack1;
            }
            this.m_21468_(equipmentslottype, stack);
            return stack;
        }
        return ItemStack.f_41583_;
    }

    @Inject(method={"interact"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;dropLeash(ZZ)V")})
    private void arclight$unleash(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (CraftEventFactory.callPlayerUnleashEntityEvent((Mob)(Object)this, player, hand).isCancelled()) {
            ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundSetEntityLinkPacket((net.minecraft.world.entity.Entity)(Object)this, this.m_21524_()));
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    @Inject(method={"checkAndHandleImportantInteractions"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;setLeashedTo(Lnet/minecraft/world/entity/Entity;Z)V")})
    private void arclight$leash(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (CraftEventFactory.callPlayerLeashEntityEvent((Mob)(Object)this, (net.minecraft.world.entity.Entity)player, player, hand).isCancelled()) {
            ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundSetEntityLinkPacket((net.minecraft.world.entity.Entity)(Object)this, this.m_21524_()));
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    @Inject(method={"tickLeash"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;dropLeash(ZZ)V")})
    public void arclight$unleash2(CallbackInfo ci) {
        Bukkit.getPluginManager().callEvent(new EntityUnleashEvent(this.getBukkitEntity(), this.m_6084_() ? EntityUnleashEvent.UnleashReason.HOLDER_GONE : EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH));
    }

    @Inject(method={"dropLeash"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Mob;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")})
    public void arclight$leashDropPost(boolean sendPacket, boolean dropLead, CallbackInfo ci) {
        this.forceDrops = false;
    }

    @Inject(method={"dropLeash"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")})
    public void arclight$leashDropPre(boolean sendPacket, boolean dropLead, CallbackInfo ci) {
        this.forceDrops = true;
    }

    @Inject(method={"restoreLeashFromSave"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Mob;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$leashRestorePost(CallbackInfo ci) {
        this.forceDrops = false;
    }

    @Inject(method={"restoreLeashFromSave"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$leashRestorePre(CallbackInfo ci) {
        this.forceDrops = true;
    }

    @Inject(method={"startRiding"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;dropLeash(ZZ)V")})
    private void arclight$unleashRide(net.minecraft.world.entity.Entity entityIn, boolean force, CallbackInfoReturnable<Boolean> cir) {
        Bukkit.getPluginManager().callEvent(new EntityUnleashEvent(this.getBukkitEntity(), EntityUnleashEvent.UnleashReason.UNKNOWN));
    }

    @Inject(method={"removeAfterChangingDimensions"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Mob;dropLeash(ZZ)V")})
    private void arclight$unleashDead(CallbackInfo ci) {
        Bukkit.getPluginManager().callEvent(new EntityUnleashEvent(this.getBukkitEntity(), EntityUnleashEvent.UnleashReason.UNKNOWN));
    }

    @Eject(method={"convertTo"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$copySpawn(Level world, net.minecraft.world.entity.Entity entityIn, CallbackInfoReturnable<Mob> cir) {
        EntityTransformEvent.TransformReason transformReason = this.arclight$transform == null ? EntityTransformEvent.TransformReason.UNKNOWN : this.arclight$transform;
        this.arclight$transform = null;
        if (CraftEventFactory.callEntityTransformEvent((LivingEntity)(Object)this, (LivingEntity)entityIn, transformReason).isCancelled()) {
            cir.setReturnValue(null);
            return false;
        }
        return world.m_7967_(entityIn);
    }

    @Inject(method={"convertTo"}, at={@At(value="RETURN")})
    private <T extends Mob> void arclight$cleanReason(EntityType<T> p_233656_1_, boolean p_233656_2_, CallbackInfoReturnable<T> cir) {
        ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(null);
        this.arclight$transform = null;
    }

    public <T extends Mob> T convertTo(EntityType<T> entityType, boolean flag, EntityTransformEvent.TransformReason transformReason, CreatureSpawnEvent.SpawnReason spawnReason) {
        ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(spawnReason);
        this.bridge$pushTransformReason(transformReason);
        return this.m_21406_(entityType, flag);
    }

    @Override
    public void bridge$pushTransformReason(EntityTransformEvent.TransformReason transformReason) {
        this.arclight$transform = transformReason;
    }

    @Redirect(method={"doHurtTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    public void arclight$attackCombust(net.minecraft.world.entity.Entity entity, int seconds) {
        EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), seconds);
        Bukkit.getPluginManager().callEvent(combustEvent);
        if (!combustEvent.isCancelled()) {
            ((EntityBridge)entity).bridge$setOnFire(combustEvent.getDuration(), false);
        }
    }

    @Override
    public ResourceLocation bridge$getLootTable() {
        return this.m_7582_();
    }

    @Override
    public boolean bridge$isPersistenceRequired() {
        return this.f_21353_;
    }

    public void setPersistenceRequired(boolean value) {
        this.f_21353_ = value;
    }

    @Override
    public void bridge$setPersistenceRequired(boolean value) {
        this.setPersistenceRequired(value);
    }
}

