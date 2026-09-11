/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Either
 *  io.izzel.arclight.mixin.Eject
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.FloatTag
 *  net.minecraft.nbt.IntTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.tags.EntityTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.CombatTracker
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.WalkAnimationState
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Equipable
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Added
 *  net.minecraftforge.event.entity.living.MobEffectEvent$Expired
 *  net.minecraftforge.event.entity.living.ShieldBlockEvent
 *  net.minecraftforge.eventbus.api.Event
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import io.izzel.arclight.mixin.Eject;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.WalkAnimationState;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.Event;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeMap;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={LivingEntity.class})
public abstract class LivingEntityMixin
extends EntityMixin
implements LivingEntityBridge {
    @Shadow
    protected int f_20889_;
    @Shadow
    public Player f_20888_;
    @Shadow
    public int f_20919_;
    @Shadow
    protected boolean f_20890_;
    @Shadow
    public boolean f_20948_;
    @Shadow
    @Final
    public static EntityDataAccessor<Float> f_20961_;
    @Shadow
    protected int f_20891_;
    @Shadow
    public float f_20898_;
    @Shadow
    public int f_20917_;
    @Shadow
    public int f_20916_;
    @Shadow
    private DamageSource f_20958_;
    @Shadow
    private long f_20930_;
    @Shadow
    @Final
    private AttributeMap f_20943_;
    @Shadow
    protected net.minecraft.world.item.ItemStack f_20935_;
    @Shadow
    protected int f_20897_;
    @Shadow
    @Nullable
    public LivingEntity f_20949_;
    @Shadow
    public CombatTracker f_20944_;
    @Shadow
    @Final
    private static EntityDataAccessor<Integer> f_20962_;
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> f_20963_;
    @Shadow
    @Final
    public Map<MobEffect, MobEffectInstance> f_20945_;
    @Shadow
    @Final
    public static EntityDataAccessor<Integer> f_20940_;
    @Shadow
    @Final
    public WalkAnimationState f_267362_;
    @Shadow
    public int f_20926_;
    public int expToDrop;
    public boolean forceDrops;
    public CraftAttributeMap craftAttributes;
    public boolean collides;
    public boolean bukkitPickUpLoot;
    public Set<UUID> collidableExemptions = new HashSet<UUID>();
    private boolean isTickingEffects = false;
    private List<Map.Entry<Either<MobEffectInstance, MobEffect>, EntityPotionEffectEvent.Cause>> effectsToProcess = Lists.newArrayList();
    private transient EntityPotionEffectEvent.Action arclight$action;
    private transient boolean arclight$damageResult;
    private transient EntityRegainHealthEvent.RegainReason arclight$regainReason;
    private transient EntityPotionEffectEvent.Cause arclight$cause;

    @Shadow
    public abstract float m_21233_();

    @Shadow
    public abstract void m_5634_(float var1);

    @Shadow
    public abstract float m_21223_();

    @Shadow
    public abstract void m_21153_(float var1);

    @Shadow
    public abstract float m_6080_();

    @Shadow
    protected abstract boolean m_6149_();

    @Shadow
    protected abstract boolean m_6124_();

    @Shadow
    public abstract AttributeInstance m_21051_(Attribute var1);

    @Shadow
    public abstract boolean m_21195_(MobEffect var1);

    @Shadow
    public abstract boolean m_21219_();

    @Shadow
    public abstract boolean m_21023_(MobEffect var1);

    @Shadow
    public abstract boolean m_5803_();

    @Shadow
    public abstract void m_5796_();

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_6844_(EquipmentSlot var1);

    @Shadow
    public abstract boolean m_21275_(DamageSource var1);

    @Shadow
    protected abstract void m_7909_(float var1);

    @Shadow
    protected abstract void m_6728_(LivingEntity var1);

    @Shadow
    public abstract void m_6703_(@Nullable LivingEntity var1);

    @Shadow
    @Nullable
    protected abstract SoundEvent m_5592_();

    @Shadow
    protected abstract float m_6121_();

    @Shadow
    public abstract float m_6100_();

    @Shadow
    public abstract void m_6667_(DamageSource var1);

    @Shadow
    protected abstract void m_6677_(DamageSource var1);

    @Shadow
    protected abstract float m_21161_(DamageSource var1, float var2);

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_21120_(InteractionHand var1);

    @Shadow
    @Nullable
    public abstract MobEffectInstance m_21124_(MobEffect var1);

    @Shadow
    protected abstract float m_6515_(DamageSource var1, float var2);

    @Shadow
    public abstract float m_6103_();

    @Shadow
    public abstract void m_7911_(float var1);

    @Shadow
    public abstract CombatTracker m_21231_();

    @Shadow
    public abstract boolean m_6147_();

    @Shadow
    public abstract void m_7938_(Entity var1, int var2);

    @Shadow
    protected abstract void m_6668_(DamageSource var1);

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_21205_();

    @Shadow
    public abstract void m_6858_(boolean var1);

    @Shadow
    public abstract void m_21335_(Entity var1);

    @Shadow
    public abstract void m_21008_(InteractionHand var1, net.minecraft.world.item.ItemStack var2);

    @Shadow
    @Nullable
    public abstract LivingEntity m_21232_();

    @Shadow
    public abstract Collection<MobEffectInstance> m_21220_();

    @Shadow
    public abstract void m_21317_(int var1);

    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_21206_();

    @Shadow
    public abstract RandomSource m_217043_();

    @Shadow
    public abstract Optional<BlockPos> m_21257_();

    @Shadow
    protected abstract void m_7285_(MobEffectInstance var1);

    @Shadow
    protected abstract void m_8034_();

    @Shadow
    public abstract boolean m_7301_(MobEffectInstance var1);

    @Shadow
    @Nullable
    public abstract MobEffectInstance m_6234_(@Nullable MobEffect var1);

    @Shadow
    protected abstract void m_21268_(@Nullable LivingEntity var1);

    @Shadow
    public abstract double m_21133_(Attribute var1);

    @Shadow
    protected abstract void m_6472_(DamageSource var1, float var2);

    @Shadow
    public abstract boolean m_21224_();

    @Shadow
    public abstract int m_21234_();

    @Shadow
    public abstract void m_8061_(EquipmentSlot var1, net.minecraft.world.item.ItemStack var2);

    @Shadow
    protected abstract void m_141973_(MobEffectInstance var1, boolean var2, @org.jetbrains.annotations.Nullable Entity var3);

    @Shadow
    protected abstract void m_142540_(MobEffectInstance var1, @org.jetbrains.annotations.Nullable Entity var2);

    @Shadow
    public abstract void m_147240_(double var1, double var3, double var5);

    @Shadow
    public abstract boolean m_6779_(LivingEntity var1);

    @Shadow
    public abstract boolean m_142582_(Entity var1);

    @Shadow
    protected abstract void m_142642_(DamageSource var1, float var2);

    @Shadow
    public abstract void m_5810_();

    @Shadow
    protected abstract boolean m_213772_(EquipmentSlot var1);

    @Shadow
    protected abstract void m_181122_(net.minecraft.world.item.ItemStack var1);

    @Shadow
    public abstract boolean m_217046_();

    @Shadow
    public abstract int m_213860_();

    @Shadow
    @Nullable
    protected abstract SoundEvent m_7975_(DamageSource var1);

    @Shadow
    protected abstract SoundEvent m_5896_(int var1);

    @Shadow
    protected abstract SoundEvent m_7838_(net.minecraft.world.item.ItemStack var1);

    @Shadow
    public abstract SoundEvent m_7866_(net.minecraft.world.item.ItemStack var1);

    @Shadow
    public abstract InteractionHand m_7655_();

    @Shadow
    public abstract void m_269405_(double var1, double var3);

    @Shadow
    public static EquipmentSlot m_147233_(net.minecraft.world.item.ItemStack p_147234_) {
        return null;
    }

    @Shadow
    protected abstract void m_6475_(DamageSource var1, float var2);

    @Shadow
    protected abstract void m_147239_();

    @Redirect(method={"<init>"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"))
    private void arclight$muteHealth(LivingEntity entity, float health) {
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends LivingEntity> type, Level worldIn, CallbackInfo ci) {
        this.collides = true;
        this.craftAttributes = new CraftAttributeMap(this.f_20943_);
        this.f_19804_.m_135381_(f_20961_, (Object)Float.valueOf((float)this.m_21133_(Attributes.f_22276_)));
    }

    public SoundEvent getHurtSound0(DamageSource damagesource) {
        return this.m_7975_(damagesource);
    }

    public SoundEvent getDeathSound0() {
        return this.m_5592_();
    }

    public SoundEvent getFallDamageSound0(int fallHeight) {
        return this.m_5896_(fallHeight);
    }

    public SoundEvent getDrinkingSound0(net.minecraft.world.item.ItemStack itemstack) {
        return this.m_7838_(itemstack);
    }

    public SoundEvent getEatingSound0(net.minecraft.world.item.ItemStack itemstack) {
        return this.m_7866_(itemstack);
    }

    @Redirect(method={"dropAllDeathLoot"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;dropExperience()V"))
    private void arclight$dropLater(LivingEntity livingEntity) {
    }

    @Inject(method={"dropAllDeathLoot"}, at={@At(value="RETURN")})
    private void arclight$dropLast(DamageSource damageSourceIn, CallbackInfo ci) {
        this.m_21226_();
    }

    @Overwrite
    protected void m_21226_() {
        if (!(this instanceof EnderDragon)) {
            int reward = ForgeEventFactory.getExperienceDrop((LivingEntity)((LivingEntity)this), (Player)this.f_20888_, (int)this.expToDrop);
            ExperienceOrb.m_147082_((ServerLevel)((ServerLevel)this.m_9236_()), (Vec3)this.m_20182_(), (int)reward);
            this.bridge$setExpToDrop(0);
        }
    }

    @Overwrite
    protected void m_21217_() {
        this.isTickingEffects = true;
        Iterator<MobEffect> iterator = this.f_20945_.keySet().iterator();
        try {
            while (iterator.hasNext()) {
                MobEffect effect = iterator.next();
                MobEffectInstance mobEffectInstance = this.f_20945_.get(effect);
                if (!mobEffectInstance.m_19552_((LivingEntity)this, () -> this.m_141973_(effectinstance, true, null))) {
                    EntityPotionEffectEvent event;
                    if (this.m_9236_().f_46443_ || MinecraftForge.EVENT_BUS.post((Event)new MobEffectEvent.Expired((LivingEntity)this, mobEffectInstance)) || (event = CraftEventFactory.callEntityPotionEffectChangeEvent((LivingEntity)this, mobEffectInstance, null, EntityPotionEffectEvent.Cause.EXPIRATION)).isCancelled()) continue;
                    iterator.remove();
                    this.m_7285_(mobEffectInstance);
                    continue;
                }
                if (mobEffectInstance.m_19557_() % 600 != 0) continue;
                this.m_141973_(mobEffectInstance, false, null);
            }
        }
        catch (ConcurrentModificationException effect) {
            // empty catch block
        }
        this.isTickingEffects = false;
        for (Map.Entry entry : this.effectsToProcess) {
            Either either = (Either)entry.getKey();
            EntityPotionEffectEvent.Cause cause = (EntityPotionEffectEvent.Cause)((Object)entry.getValue());
            this.bridge$pushEffectCause(cause);
            if (either.left().isPresent()) {
                this.addEffect((MobEffectInstance)either.left().get(), cause);
                continue;
            }
            this.removeEffect((MobEffect)either.right().get(), cause);
        }
        this.effectsToProcess.clear();
        if (this.f_20948_) {
            if (!this.m_9236_().f_46443_) {
                this.m_8034_();
                this.m_147239_();
            }
            this.f_20948_ = false;
        }
        int i = (Integer)this.f_19804_.m_135370_(f_20962_);
        boolean bl = (Boolean)this.f_19804_.m_135370_(f_20963_);
        if (i > 0) {
            boolean flag = this.m_20145_() ? this.f_19796_.m_188503_(15) == 0 : this.f_19796_.m_188499_();
            if (bl) {
                flag &= this.f_19796_.m_188503_(5) == 0;
            }
            if (flag && i > 0) {
                double d0 = (double)(i >> 16 & 0xFF) / 255.0;
                double d1 = (double)(i >> 8 & 0xFF) / 255.0;
                double d2 = (double)(i >> 0 & 0xFF) / 255.0;
                this.m_9236_().m_7106_((ParticleOptions)(bl ? ParticleTypes.f_123770_ : ParticleTypes.f_123811_), this.m_20185_() + (this.f_19796_.m_188500_() - 0.5) * (double)this.m_20205_(), this.m_20186_() + this.f_19796_.m_188500_() * (double)this.m_20206_(), this.m_20189_() + (this.f_19796_.m_188500_() - 0.5) * (double)this.m_20205_(), d0, d1, d2);
            }
        }
    }

    @Overwrite
    public boolean m_147207_(MobEffectInstance effectInstanceIn, Entity entity) {
        EntityPotionEffectEvent event;
        EntityPotionEffectEvent.Cause cause = this.bridge$getEffectCause().orElse(EntityPotionEffectEvent.Cause.UNKNOWN);
        if (this.isTickingEffects) {
            this.effectsToProcess.add(Maps.immutableEntry((Object)Either.left((Object)effectInstanceIn), (Object)((Object)cause)));
            return true;
        }
        if (!this.m_7301_(effectInstanceIn)) {
            return false;
        }
        MobEffectInstance effectinstance = this.f_20945_.get(effectInstanceIn.m_19544_());
        boolean override = false;
        if (effectinstance != null) {
            override = new MobEffectInstance(effectinstance).m_19558_(effectInstanceIn);
        }
        if ((event = CraftEventFactory.callEntityPotionEffectChangeEvent((LivingEntity)this, effectinstance, effectInstanceIn, cause, override)).isCancelled()) {
            return false;
        }
        MinecraftForge.EVENT_BUS.post((Event)new MobEffectEvent.Added((LivingEntity)this, effectinstance, effectInstanceIn, entity));
        if (effectinstance == null) {
            this.f_20945_.put(effectInstanceIn.m_19544_(), effectInstanceIn);
            this.m_142540_(effectInstanceIn, entity);
            return true;
        }
        if (event.isOverride()) {
            effectinstance.m_19558_(effectInstanceIn);
            this.m_141973_(effectinstance, true, entity);
            return true;
        }
        return false;
    }

    public MobEffectInstance c(@Nullable MobEffect potioneffectin, EntityPotionEffectEvent.Cause cause) {
        this.bridge$pushEffectCause(cause);
        return this.m_6234_(potioneffectin);
    }

    @Inject(method={"removeEffectNoUpdate"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$clearActive(MobEffect effect, CallbackInfoReturnable<MobEffectInstance> cir) {
        EntityPotionEffectEvent.Cause cause = this.bridge$getEffectCause().orElse(EntityPotionEffectEvent.Cause.UNKNOWN);
        if (this.isTickingEffects) {
            this.effectsToProcess.add(Maps.immutableEntry((Object)Either.right((Object)effect), (Object)((Object)cause)));
            cir.setReturnValue(null);
            return;
        }
        MobEffectInstance effectInstance = this.f_20945_.get(effect);
        if (effectInstance == null) {
            cir.setReturnValue(null);
            return;
        }
        EntityPotionEffectEvent event = CraftEventFactory.callEntityPotionEffectChangeEvent((LivingEntity)this, effectInstance, null, cause);
        if (event.isCancelled()) {
            cir.setReturnValue(null);
        }
    }

    @Override
    public boolean bridge$canPickUpLoot() {
        return this.bukkitPickUpLoot;
    }

    @Override
    public float getBukkitYaw() {
        return this.m_6080_();
    }

    public int getExpReward() {
        if (this.m_9236_() instanceof ServerLevel && !this.m_217046_() && (this.m_6124_() || this.f_20889_ > 0 && this.m_6149_() && this.m_9236_().m_46469_().m_46207_(GameRules.f_46135_))) {
            int exp = this.m_213860_();
            return ForgeEventFactory.getExperienceDrop((LivingEntity)((LivingEntity)this), (Player)this.f_20888_, (int)exp);
        }
        return 0;
    }

    @Override
    public int bridge$getExpReward() {
        return this.getExpReward();
    }

    @Override
    public void bridge$setExpToDrop(int amount) {
        this.expToDrop = amount;
    }

    @Override
    public int bridge$getExpToDrop() {
        return this.expToDrop;
    }

    @Override
    public boolean bridge$isForceDrops() {
        return this.forceDrops;
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="HEAD")})
    public void arclight$readMaxHealth(CompoundTag compound, CallbackInfo ci) {
        if (compound.m_128441_("Bukkit.MaxHealth")) {
            Tag nbtbase = compound.m_128423_("Bukkit.MaxHealth");
            if (nbtbase.m_7060_() == 5) {
                this.m_21051_(Attributes.f_22276_).m_22100_(((FloatTag)nbtbase).m_7061_());
            } else if (nbtbase.m_7060_() == 3) {
                this.m_21051_(Attributes.f_22276_).m_22100_(((IntTag)nbtbase).m_7061_());
            }
        }
    }

    @Inject(method={"removeAllEffects"}, at={@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/eventbus/api/IEventBus;post(Lnet/minecraftforge/eventbus/api/Event;)Z")})
    public void arclight$clearReason(CallbackInfoReturnable<Boolean> cir) {
        this.arclight$action = EntityPotionEffectEvent.Action.CLEARED;
    }

    @Override
    public EntityPotionEffectEvent.Action bridge$getAndResetAction() {
        try {
            EntityPotionEffectEvent.Action action = this.arclight$action;
            return action;
        }
        finally {
            this.arclight$action = null;
        }
    }

    @Override
    @Overwrite
    public boolean m_6084_() {
        return !this.m_213877_() && ((Float)this.f_19804_.m_135370_(f_20961_)).floatValue() > 0.0f;
    }

    @Inject(method={"getHealth"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$scaledHealth(CallbackInfoReturnable<Float> cir) {
        if (this instanceof ServerPlayerEntityBridge && ((ServerPlayerEntityBridge)((Object)this)).bridge$initialized()) {
            cir.setReturnValue((Object)Float.valueOf((float)((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity().getHealth()));
        }
    }

    @Inject(method={"setHealth"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$setScaled(float health, CallbackInfo ci) {
        if (this instanceof ServerPlayerEntityBridge && ((ServerPlayerEntityBridge)((Object)this)).bridge$initialized()) {
            CraftPlayer player = ((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity();
            double realHealth = Mth.m_14008_((double)health, (double)0.0, (double)player.getMaxHealth());
            player.setRealHealth(realHealth);
            player.updateScaledHealth(false);
            player.setRealHealth(realHealth);
            ci.cancel();
        }
    }

    @Override
    @Overwrite
    public boolean m_6469_(DamageSource source, float amount) {
        boolean flag2;
        Entity entity1;
        if (!ForgeHooks.onLivingAttack((LivingEntity)((LivingEntity)this), (DamageSource)source, (float)amount)) {
            return false;
        }
        if (this.m_6673_(source)) {
            return false;
        }
        if (this.m_9236_().f_46443_) {
            return false;
        }
        if (this.f_20890_ || this.m_213877_() || this.m_21224_()) {
            return false;
        }
        if (source.m_269533_(DamageTypeTags.f_268745_) && this.m_21023_(MobEffects.f_19607_)) {
            return false;
        }
        if (this.m_5803_() && !this.m_9236_().f_46443_) {
            this.m_5796_();
        }
        this.f_20891_ = 0;
        float f = amount;
        boolean flag = f > 0.0f && this.m_21275_(source);
        float f1 = 0.0f;
        if (source.m_269533_(DamageTypeTags.f_268419_) && this.m_6095_().m_204039_(EntityTypeTags.f_144295_)) {
            f *= 5.0f;
        }
        this.f_267362_.m_267771_(1.5f);
        boolean flag1 = true;
        if ((float)this.f_19802_ > (float)this.f_20926_ / 2.0f && !source.m_269533_(DamageTypeTags.f_273918_)) {
            if (amount <= this.f_20898_) {
                return false;
            }
            this.m_6475_(source, amount - this.f_20898_);
            if (!this.arclight$damageResult) {
                return false;
            }
            this.f_20898_ = amount;
            flag1 = false;
        } else {
            this.m_6475_(source, amount);
            if (!this.arclight$damageResult) {
                return false;
            }
            this.f_20898_ = amount;
            this.f_19802_ = 20;
            this.f_20916_ = this.f_20917_ = 10;
        }
        if (this instanceof Animal) {
            ((Animal)this).m_27594_();
            if (this instanceof TamableAnimal) {
                ((TamableAnimal)this).m_21839_(false);
            }
        }
        if ((entity1 = source.m_7639_()) != null) {
            TamableAnimal wolfentity;
            if (entity1 instanceof LivingEntity && !source.m_269533_(DamageTypeTags.f_268718_)) {
                this.m_6703_((LivingEntity)entity1);
            }
            if (entity1 instanceof Player) {
                this.f_20889_ = 100;
                this.f_20888_ = (Player)entity1;
            } else if (entity1 instanceof TamableAnimal && (wolfentity = (TamableAnimal)entity1).m_21824_()) {
                this.f_20889_ = 100;
                LivingEntity livingentity = wolfentity.m_269323_();
                this.f_20888_ = livingentity instanceof Player ? (Player)livingentity : null;
            }
        }
        if (flag1) {
            if (flag) {
                this.m_9236_().m_7605_((Entity)((LivingEntity)this), (byte)29);
            } else {
                this.m_9236_().m_269196_((Entity)((LivingEntity)this), source);
            }
            if (!(source.m_269533_(DamageTypeTags.f_268467_) || flag && !(amount > 0.0f))) {
                this.m_5834_();
            }
            if (entity1 != null && !source.m_269533_(DamageTypeTags.f_268415_)) {
                double d1 = entity1.m_20185_() - this.m_20185_();
                double d0 = entity1.m_20189_() - this.m_20189_();
                while (d1 * d1 + d0 * d0 < 1.0E-4) {
                    d1 = (Math.random() - Math.random()) * 0.01;
                    d0 = (Math.random() - Math.random()) * 0.01;
                }
                this.m_147240_(0.4f, d1, d0);
                if (!flag) {
                    this.m_269405_(d1, d0);
                }
            }
        }
        if (this.m_21224_()) {
            if (!this.m_21262_(source)) {
                SoundEvent soundevent = this.m_5592_();
                if (flag1 && soundevent != null) {
                    this.m_5496_(soundevent, this.m_6121_(), this.m_6100_());
                }
                this.m_6667_(source);
            }
        } else if (flag1) {
            this.m_6677_(source);
        }
        boolean bl = flag2 = !flag || amount > 0.0f;
        if (flag2) {
            this.f_20958_ = source;
            this.f_20930_ = this.m_9236_().m_46467_();
        }
        if (this instanceof ServerPlayer) {
            CriteriaTriggers.f_10574_.m_35174_((ServerPlayer)this, source, f, amount, flag);
            if (f1 > 0.0f && f1 < 3.4028235E37f) {
                ((ServerPlayer)this).m_36222_(Stats.f_12932_, Math.round(f1 * 10.0f));
            }
        }
        if (entity1 instanceof ServerPlayer) {
            CriteriaTriggers.f_10573_.m_60112_((ServerPlayer)entity1, (Entity)((LivingEntity)this), source, f, amount, flag);
        }
        return flag2;
    }

    @Inject(method={"actuallyHurt"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$redirectDamageEntity(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        this.damageEntity0(damageSrc, damageAmount);
        ci.cancel();
    }

    protected boolean damageEntity0(DamageSource damagesource, float f) {
        if (!this.m_6673_(damagesource)) {
            float f3;
            Function blocking;
            boolean human = this instanceof Player;
            if ((f = ForgeHooks.onLivingHurt((LivingEntity)((LivingEntity)this), (DamageSource)damagesource, (float)f)) <= 0.0f) {
                this.arclight$damageResult = true;
                return true;
            }
            float originalDamage = f;
            Function hardHat = f12 -> {
                if (damagesource.m_269533_(DamageTypeTags.f_268627_) && !this.m_6844_(EquipmentSlot.HEAD).m_41619_()) {
                    return -(f12 - f12 * 0.75);
                }
                return -0.0;
            };
            float hardHatModifier = ((Double)hardHat.apply((Object)f)).floatValue();
            f += hardHatModifier;
            boolean shieldTakesDamage = false;
            if (this.m_21275_(damagesource)) {
                ShieldBlockEvent shieldEvent = ForgeHooks.onShieldBlock((LivingEntity)((LivingEntity)this), (DamageSource)damagesource, (float)f);
                if (!shieldEvent.isCanceled()) {
                    float blocked = shieldEvent.getBlockedDamage();
                    shieldTakesDamage = shieldEvent.shieldTakesDamage();
                    blocking = f13 -> -((double)blocked);
                } else {
                    blocking = f13 -> 0.0;
                }
            } else {
                blocking = f13 -> 0.0;
            }
            float blockingModifier = ((Double)blocking.apply((Object)f)).floatValue();
            Function armor = f14 -> -(f14 - (double)this.m_21161_(damagesource, f14.floatValue()));
            float armorModifier = ((Double)armor.apply((Object)(f += blockingModifier))).floatValue();
            Function resistance = f15 -> {
                if (!damagesource.m_269533_(DamageTypeTags.f_268437_) && this.m_21023_(MobEffects.f_19606_) && !damagesource.m_269533_(DamageTypeTags.f_268630_)) {
                    int i = (this.m_21124_(MobEffects.f_19606_).m_19564_() + 1) * 5;
                    int j = 25 - i;
                    float f1 = f15.floatValue() * (float)j;
                    return -(f15 - (double)(f1 / 25.0f));
                }
                return -0.0;
            };
            float resistanceModifier = ((Double)resistance.apply((Object)(f += armorModifier))).floatValue();
            Function magic = f16 -> -(f16 - (double)this.m_6515_(damagesource, f16.floatValue()));
            float magicModifier = ((Double)magic.apply((Object)(f += resistanceModifier))).floatValue();
            Function absorption = f17 -> -Math.max(f17 - Math.max(f17 - (double)this.m_6103_(), 0.0), 0.0);
            float absorptionModifier = ((Double)absorption.apply((Object)(f += magicModifier))).floatValue();
            EntityDamageEvent event = CraftEventFactory.handleLivingEntityDamageEvent((Entity)((LivingEntity)this), damagesource, originalDamage, hardHatModifier, blockingModifier, armorModifier, resistanceModifier, magicModifier, absorptionModifier, (Function<Double, Double>)hardHat, (Function<Double, Double>)blocking, (Function<Double, Double>)armor, (Function<Double, Double>)resistance, (Function<Double, Double>)magic, (Function<Double, Double>)absorption);
            if (damagesource.m_7639_() instanceof Player) {
                ((Player)damagesource.m_7639_()).m_36334_();
            }
            if (event.isCancelled()) {
                this.arclight$damageResult = false;
                return false;
            }
            f = (float)event.getFinalDamage();
            if (event.getDamage(EntityDamageEvent.DamageModifier.RESISTANCE) < 0.0 && (f3 = (float)(-event.getDamage(EntityDamageEvent.DamageModifier.RESISTANCE))) > 0.0f && f3 < 3.4028235E37f) {
                if (this instanceof ServerPlayer) {
                    ((ServerPlayer)this).m_36222_(Stats.f_12934_, Math.round(f3 * 10.0f));
                } else if (damagesource.m_7639_() instanceof ServerPlayer) {
                    ((ServerPlayer)damagesource.m_7639_()).m_36222_(Stats.f_12930_, Math.round(f3 * 10.0f));
                }
            }
            if (damagesource.m_269533_(DamageTypeTags.f_268627_) && !this.m_6844_(EquipmentSlot.HEAD).m_41619_()) {
                this.m_142642_(damagesource, f);
            }
            if (!damagesource.m_269533_(DamageTypeTags.f_268490_)) {
                float armorDamage = (float)(event.getDamage() + event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) + event.getDamage(EntityDamageEvent.DamageModifier.HARD_HAT));
                this.m_6472_(damagesource, armorDamage);
            }
            if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) < 0.0) {
                Entity entity;
                this.m_9236_().m_7605_((Entity)this, (byte)29);
                if (shieldTakesDamage) {
                    this.m_7909_((float)(-event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING)));
                }
                if ((entity = damagesource.m_7640_()) instanceof LivingEntity) {
                    this.m_6728_((LivingEntity)entity);
                }
            }
            absorptionModifier = (float)(-event.getDamage(EntityDamageEvent.DamageModifier.ABSORPTION));
            this.m_7911_(Math.max(this.m_6103_() - absorptionModifier, 0.0f));
            float f2 = absorptionModifier;
            if (f2 > 0.0f && f2 < 3.4028235E37f && this instanceof Player) {
                ((Player)this).m_36222_(Stats.f_12933_, Math.round(f2 * 10.0f));
            }
            if (f2 > 0.0f && f2 < 3.4028235E37f && damagesource.m_7639_() instanceof Player) {
                ((Player)damagesource.m_7639_()).m_36222_(Stats.f_12929_, Math.round(f2 * 10.0f));
            }
            if ((f = ForgeHooks.onLivingDamage((LivingEntity)((LivingEntity)this), (DamageSource)damagesource, (float)f)) > 0.0f || !human) {
                if (human) {
                    ((PlayerEntityBridge)((Object)this)).bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.DAMAGED);
                    ((Player)this).m_36399_(damagesource.m_19377_());
                    if (f < 3.4028235E37f) {
                        ((Player)this).m_36222_(Stats.f_12931_, Math.round(f * 10.0f));
                    }
                }
                float f32 = this.m_21223_();
                this.m_21231_().m_289194_(damagesource, f);
                this.m_21153_(f32 - f);
                if (!human) {
                    this.m_7911_(this.m_6103_() - f);
                }
                this.m_146852_(GameEvent.f_223706_, damagesource.m_7639_());
                this.arclight$damageResult = true;
                return true;
            }
            if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) < 0.0) {
                if (this instanceof ServerPlayer) {
                    CriteriaTriggers.f_10574_.m_35174_((ServerPlayer)this, damagesource, f, originalDamage, true);
                    f2 = (float)(-event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING));
                    if (f2 > 0.0f && f2 < 3.4028235E37f) {
                        ((ServerPlayer)this).m_36222_(Stats.f_12932_, Math.round(originalDamage * 10.0f));
                    }
                }
                if (damagesource.m_7639_() instanceof ServerPlayer) {
                    CriteriaTriggers.f_10573_.m_60112_((ServerPlayer)damagesource.m_7639_(), (Entity)this, damagesource, f, originalDamage, true);
                }
                this.arclight$damageResult = false;
                return false;
            }
            this.arclight$damageResult = originalDamage > 0.0f;
            return this.arclight$damageResult;
        }
        this.arclight$damageResult = false;
        return false;
    }

    public void heal(float healAmount, EntityRegainHealthEvent.RegainReason regainReason) {
        this.bridge$pushHealReason(regainReason);
        this.m_5634_(healAmount);
    }

    @Redirect(method={"heal"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"))
    public void arclight$healEvent(LivingEntity livingEntity, float health) {
        EntityRegainHealthEvent.RegainReason regainReason = this.arclight$regainReason == null ? EntityRegainHealthEvent.RegainReason.CUSTOM : this.arclight$regainReason;
        this.arclight$regainReason = null;
        float f = this.m_21223_();
        float amount = health - f;
        EntityRegainHealthEvent event = new EntityRegainHealthEvent(this.getBukkitEntity(), amount, regainReason);
        if (this.valid) {
            Bukkit.getPluginManager().callEvent(event);
        }
        if (!event.isCancelled()) {
            this.m_21153_(this.m_21223_() + (float)event.getAmount());
        }
    }

    @Inject(method={"heal"}, at={@At(value="RETURN")})
    public void arclight$resetReason(float healAmount, CallbackInfo ci) {
        this.arclight$regainReason = null;
    }

    public boolean removeEffect(MobEffect effect, EntityPotionEffectEvent.Cause cause) {
        this.bridge$pushEffectCause(cause);
        return this.m_21195_(effect);
    }

    @Override
    public boolean bridge$removeEffect(MobEffect effect, EntityPotionEffectEvent.Cause cause) {
        return this.removeEffect(effect, cause);
    }

    public boolean addEffect(MobEffectInstance effect, EntityPotionEffectEvent.Cause cause) {
        this.bridge$pushEffectCause(cause);
        return this.m_147207_(effect, null);
    }

    public boolean removeAllEffects(EntityPotionEffectEvent.Cause cause) {
        this.bridge$pushEffectCause(cause);
        return this.m_21219_();
    }

    @Override
    public boolean bridge$removeAllEffects(EntityPotionEffectEvent.Cause cause) {
        return this.removeAllEffects(cause);
    }

    @Override
    public CraftLivingEntity getBukkitEntity() {
        return (CraftLivingEntity)this.internal$getBukkitEntity();
    }

    @Override
    public CraftLivingEntity bridge$getBukkitEntity() {
        return (CraftLivingEntity)this.internal$getBukkitEntity();
    }

    @Overwrite
    private boolean m_21262_(DamageSource damageSourceIn) {
        if (damageSourceIn.m_269533_(DamageTypeTags.f_268738_)) {
            return false;
        }
        net.minecraft.world.item.ItemStack itemstack = null;
        net.minecraft.world.item.ItemStack itemstack1 = net.minecraft.world.item.ItemStack.f_41583_;
        org.bukkit.inventory.EquipmentSlot bukkitHand = null;
        for (InteractionHand hand : InteractionHand.values()) {
            itemstack1 = this.m_21120_(hand);
            if (!itemstack1.m_150930_(Items.f_42747_) || !ForgeHooks.onLivingUseTotem((LivingEntity)((LivingEntity)this), (DamageSource)damageSourceIn, (net.minecraft.world.item.ItemStack)itemstack1, (InteractionHand)hand)) continue;
            itemstack = itemstack1.m_41777_();
            bukkitHand = CraftEquipmentSlot.getHand(hand);
            break;
        }
        EntityResurrectEvent event = new EntityResurrectEvent(this.getBukkitEntity(), bukkitHand);
        event.setCancelled(itemstack == null);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            LivingEntityMixin livingEntityMixin;
            if (!itemstack1.m_41619_()) {
                itemstack1.m_41774_(1);
            }
            if (itemstack != null && (livingEntityMixin = this) instanceof ServerPlayer) {
                ServerPlayer serverplayerentity = (ServerPlayer)livingEntityMixin;
                serverplayerentity.m_36246_(Stats.f_12982_.m_12902_((Object)Items.f_42747_));
                CriteriaTriggers.f_10551_.m_74431_(serverplayerentity, itemstack);
            }
            this.m_21153_(1.0f);
            this.removeAllEffects(EntityPotionEffectEvent.Cause.TOTEM);
            this.addEffect(new MobEffectInstance(MobEffects.f_19605_, 900, 1), EntityPotionEffectEvent.Cause.TOTEM);
            this.bridge$pushEffectCause(EntityPotionEffectEvent.Cause.TOTEM);
            this.addEffect(new MobEffectInstance(MobEffects.f_19617_, 100, 1), EntityPotionEffectEvent.Cause.TOTEM);
            this.addEffect(new MobEffectInstance(MobEffects.f_19607_, 800, 1), EntityPotionEffectEvent.Cause.TOTEM);
            this.m_9236_().m_7605_((Entity)this, (byte)35);
        }
        return !event.isCancelled();
    }

    @Inject(method={"createWitherRose"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$witherRoseDrop(LivingEntity livingEntity, CallbackInfo ci, boolean flag, ItemEntity itemEntity) {
        EntityDropItemEvent event = new EntityDropItemEvent(this.getBukkitEntity(), (Item)((Object)((EntityBridge)itemEntity).bridge$getBukkitEntity()));
        CraftEventFactory.callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Redirect(method={"createWitherRose"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean arclight$fireWitherRoseForm(Level instance, BlockPos pPos, BlockState pNewState, int pFlags) {
        return CraftEventFactory.handleBlockFormEvent(instance, pPos, pNewState, 3, (Entity)this);
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"))
    public void arclight$stopGlide(LivingEntity livingEntity, int flag, boolean set) {
        if (set != livingEntity.m_20291_(flag) && !CraftEventFactory.callToggleGlideEvent(livingEntity, set).isCancelled()) {
            livingEntity.m_20115_(flag, set);
        }
    }

    @Redirect(method={"updateFallFlying"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"))
    public void arclight$toggleGlide(LivingEntity livingEntity, int flag, boolean set) {
        if (set != livingEntity.m_20291_(flag) && !CraftEventFactory.callToggleGlideEvent(livingEntity, set).isCancelled()) {
            livingEntity.m_20115_(flag, set);
        }
    }

    @Overwrite
    public boolean m_6087_() {
        return !this.m_213877_() && this.collides;
    }

    @Override
    @Overwrite
    public boolean m_6094_() {
        return this.m_6084_() && !this.m_6147_() && this.collides;
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        return this.m_6094_() && this.collides != this.collidableExemptions.contains(entity.m_20148_());
    }

    @Eject(method={"completeUsingItem"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;finishUsingItem(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;"))
    private net.minecraft.world.item.ItemStack arclight$itemConsume(net.minecraft.world.item.ItemStack itemStack, Level worldIn, LivingEntity entityLiving, CallbackInfo ci) {
        if (this instanceof ServerPlayerEntityBridge) {
            ItemStack craftItem = CraftItemStack.asBukkitCopy(itemStack);
            PlayerItemConsumeEvent event = new PlayerItemConsumeEvent((org.bukkit.entity.Player)((Object)this.getBukkitEntity()), craftItem, CraftEquipmentSlot.getHand(this.m_7655_()));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity().updateInventory();
                ((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity().updateScaledHealth();
                ci.cancel();
                return null;
            }
            if (!craftItem.equals(event.getItem())) {
                return CraftItemStack.asNMSCopy(event.getItem()).m_41671_(worldIn, entityLiving);
            }
        }
        return itemStack.m_41671_(worldIn, entityLiving);
    }

    @Eject(method={"randomTeleport"}, at=@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/LivingEntity;teleportTo(DDD)V"))
    private void arclight$entityTeleport(LivingEntity entity, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        EntityTeleportEvent event = new EntityTeleportEvent(this.getBukkitEntity(), new Location(((WorldBridge)this.m_9236_()).bridge$getWorld(), this.m_20185_(), this.m_20186_(), this.m_20189_()), new Location(((WorldBridge)this.m_9236_()).bridge$getWorld(), x, y, z));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.m_6021_(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ());
        } else {
            this.m_6021_(this.m_20185_(), this.m_20186_(), this.m_20189_());
            cir.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"dropAllDeathLoot"}, at=@At(value="INVOKE", ordinal=0, remap=false, target="Lnet/minecraft/world/entity/LivingEntity;captureDrops(Ljava/util/Collection;)Ljava/util/Collection;"))
    private Collection<ItemEntity> arclight$captureIfNeed(LivingEntity livingEntity, Collection<ItemEntity> value) {
        Collection drops = livingEntity.captureDrops();
        return drops == null ? livingEntity.captureDrops(value) : drops;
    }

    @Redirect(method={"dropAllDeathLoot"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/Collection;forEach(Ljava/util/function/Consumer;)V"))
    private void arclight$cancelEvent(Collection<ItemEntity> collection, Consumer<ItemEntity> action) {
        if (this instanceof ServerPlayerEntityBridge) {
            this.captureDrops(collection);
        } else {
            collection.forEach(action);
        }
    }

    @Inject(method={"addEatEffect"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")})
    public void arclight$foodEffectCause(net.minecraft.world.item.ItemStack p_213349_1_, Level p_213349_2_, LivingEntity livingEntity, CallbackInfo ci) {
        ((LivingEntityBridge)livingEntity).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.FOOD);
    }

    @Override
    public void bridge$heal(float healAmount, EntityRegainHealthEvent.RegainReason regainReason) {
        this.heal(healAmount, regainReason);
    }

    @Override
    public void bridge$pushHealReason(EntityRegainHealthEvent.RegainReason regainReason) {
        this.arclight$regainReason = regainReason;
    }

    @Override
    public void bridge$pushEffectCause(EntityPotionEffectEvent.Cause cause) {
        this.arclight$cause = cause;
    }

    @Override
    public boolean bridge$addEffect(MobEffectInstance effect, EntityPotionEffectEvent.Cause cause) {
        return this.addEffect(effect, cause);
    }

    @Override
    public Optional<EntityPotionEffectEvent.Cause> bridge$getEffectCause() {
        try {
            Optional<EntityPotionEffectEvent.Cause> optional = Optional.ofNullable(this.arclight$cause);
            return optional;
        }
        finally {
            this.arclight$cause = null;
        }
    }

    @Inject(method={"setArrowCount"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$onArrowChange(int count, CallbackInfo ci) {
        if (this.arclight$callArrowCountChange(count, false)) {
            ci.cancel();
        }
    }

    public final void setArrowCount(int count, boolean reset) {
        if (this.arclight$callArrowCountChange(count, reset)) {
            return;
        }
        this.f_19804_.m_135381_(f_20940_, (Object)count);
    }

    private boolean arclight$callArrowCountChange(int newCount, boolean reset) {
        return CraftEventFactory.callArrowBodyCountChangeEvent((LivingEntity)this, this.m_21234_(), newCount, reset).isCancelled();
    }

    public void setItemSlot(EquipmentSlot slotIn, net.minecraft.world.item.ItemStack stack, boolean silent) {
        this.m_8061_(slotIn, stack);
    }

    @Override
    public void bridge$setSlot(EquipmentSlot slotIn, net.minecraft.world.item.ItemStack stack, boolean silent) {
        this.setItemSlot(slotIn, stack, silent);
    }

    protected void equipEventAndSound(EquipmentSlot slot, net.minecraft.world.item.ItemStack oldItem, net.minecraft.world.item.ItemStack newItem, boolean silent) {
        Equipable equipable;
        boolean flag;
        boolean bl = flag = newItem.m_41619_() && oldItem.m_41619_();
        if (!(flag || net.minecraft.world.item.ItemStack.m_150942_((net.minecraft.world.item.ItemStack)oldItem, (net.minecraft.world.item.ItemStack)newItem) || this.f_19803_ || (equipable = Equipable.m_269088_((net.minecraft.world.item.ItemStack)newItem)) == null || this.m_5833_() || equipable.m_40402_() != slot)) {
            if (!(this.m_9236_().m_5776_() || this.m_20067_() || silent)) {
                this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), equipable.m_150681_(), this.m_5720_(), 1.0f, 1.0f);
            }
            if (this.m_213772_(slot)) {
                this.m_146850_(GameEvent.f_157811_);
            }
        }
    }

    @Override
    public void bridge$playEquipSound(EquipmentSlot slot, net.minecraft.world.item.ItemStack oldItem, net.minecraft.world.item.ItemStack newItem, boolean silent) {
        this.equipEventAndSound(slot, oldItem, newItem, silent);
    }

    @Mixin(value={LivingEntity.class}, priority=1500)
    public static class ApotheosisCompatMixin {
        @Redirect(method={"getDamageAfterMagicAbsorb"}, require=0, at=@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"))
        public boolean arclight$mutePotion(LivingEntity livingEntity, MobEffect potionIn) {
            return false;
        }
    }

    @Mixin(value={LivingEntity.class}, priority=1500)
    public static class ObscureApiCompat {
        @Redirect(method={"getDamageAfterArmorAbsorb"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/LivingEntity;hurtArmor(Lnet/minecraft/world/damagesource/DamageSource;F)V"))
        private void arclight$muteDamageArmor(LivingEntity entity, DamageSource damageSource, float damage) {
        }
    }
}

