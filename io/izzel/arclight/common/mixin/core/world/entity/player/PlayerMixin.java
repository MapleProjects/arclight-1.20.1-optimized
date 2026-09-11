/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Either
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.GlobalPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stat
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.Unit
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.EquipmentSlot$Type
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.MobType
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Abilities
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.player.Player$BedSleepingProblem
 *  net.minecraft.world.food.FoodData
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.InventoryMenu
 *  net.minecraft.world.inventory.PlayerEnderChestContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.scores.Scoreboard
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.ToolActions
 *  net.minecraftforge.common.extensions.IForgePlayer
 *  net.minecraftforge.entity.PartEntity
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.CriticalHitEvent
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.player;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.util.DamageSourceBridge;
import io.izzel.arclight.common.bridge.core.util.FoodStatsBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.LivingEntityMixin;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.extensions.IForgePlayer;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftVector;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;
import org.spigotmc.SpigotWorldConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={Player.class})
public abstract class PlayerMixin
extends LivingEntityMixin
implements PlayerEntityBridge,
IForgePlayer {
    @Shadow
    @Final
    private Abilities f_36077_;
    @Shadow
    private long f_36109_;
    @Shadow
    public int f_36078_;
    @Shadow
    @Final
    private Inventory f_36093_;
    @Shadow
    public AbstractContainerMenu f_36096_;
    @Shadow
    @Final
    public InventoryMenu f_36095_;
    @Shadow
    public float f_36080_;
    @Shadow
    public int f_36079_;
    @Shadow
    protected FoodData f_36097_;
    @Shadow
    protected PlayerEnderChestContainer f_36094_;
    @Shadow
    public int f_36110_;
    public boolean fauxSleeping;
    public int oldLevel;
    protected transient boolean arclight$forceSleep;
    private EntityExhaustionEvent.ExhaustionReason arclight$exhaustReason;

    @Override
    @Shadow
    public abstract String m_6302_();

    @Shadow
    public abstract float m_36403_(float var1);

    @Shadow
    public abstract void m_36334_();

    @Override
    @Shadow
    public abstract SoundSource m_5720_();

    @Shadow
    public abstract float m_6113_();

    @Shadow
    public abstract void m_36346_();

    @Shadow
    public abstract void m_5704_(Entity var1);

    @Shadow
    public abstract void m_5700_(Entity var1);

    @Shadow
    public abstract void m_36222_(ResourceLocation var1, int var2);

    @Shadow
    public abstract void m_36399_(float var1);

    @Shadow
    public abstract void m_36364_(CompoundTag var1);

    @Shadow
    public abstract void m_36362_(CompoundTag var1);

    @Shadow
    public abstract CompoundTag m_36332_();

    @Shadow
    public abstract CompoundTag m_36331_();

    @Shadow
    public abstract void m_36246_(Stat<?> var1);

    @Shadow
    public abstract void m_36220_(ResourceLocation var1);

    @Shadow
    public abstract Component m_5446_();

    @Shadow
    public abstract HumanoidArm m_5737_();

    @Shadow
    protected boolean m_6107_() {
        return false;
    }

    @Shadow
    public abstract Scoreboard m_36329_();

    @Shadow
    public abstract Either<Player.BedSleepingProblem, Unit> m_7720_(BlockPos var1);

    @Shadow
    public abstract GameProfile m_36316_();

    @Shadow
    public abstract Inventory m_150109_();

    @Shadow
    public abstract Abilities m_150110_();

    @Shadow
    public abstract void m_219749_(Optional<GlobalPos> var1);

    @Shadow
    public abstract Optional<GlobalPos> m_219759_();

    @Shadow
    public abstract void m_7311_(int var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(CallbackInfo ci) {
        this.oldLevel = -1;
        ((FoodStatsBridge)this.f_36097_).bridge$setEntityHuman((Player)this);
        ((IInventoryBridge)this.f_36094_).setOwner(this.getBukkitEntity());
    }

    @Override
    public boolean bridge$isFauxSleeping() {
        return this.fauxSleeping;
    }

    @Inject(method={"turtleHelmetTick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")})
    private void arclight$turtleHelmet(CallbackInfo ci) {
        this.bridge$pushEffectCause(EntityPotionEffectEvent.Cause.TURTLE_HELMET);
    }

    @Inject(method={"aiStep"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;heal(F)V")})
    private void arclight$healByRegen(CallbackInfo ci) {
        this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.REGEN);
    }

    @Inject(method={"drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN", ordinal=1)})
    private void arclight$playerDropItem(net.minecraft.world.item.ItemStack droppedItem, boolean dropAround, boolean traceItem, CallbackInfoReturnable<ItemEntity> cir, double d0, ItemEntity itemEntity) {
        org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)this.getBukkitEntity());
        Item drop = (Item)((Object)((EntityBridge)itemEntity).bridge$getBukkitEntity());
        PlayerDropItemEvent event = new PlayerDropItemEvent(player, drop);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ItemStack cur = player.getInventory().getItemInHand();
            if (traceItem && (cur == null || cur.getAmount() == 0)) {
                player.getInventory().setItemInHand(drop.getItemStack());
            } else if (traceItem && cur.isSimilar(drop.getItemStack()) && cur.getAmount() < cur.getMaxStackSize() && drop.getItemStack().getAmount() == 1) {
                cur.setAmount(cur.getAmount() + 1);
                player.getInventory().setItemInHand(cur);
            } else {
                player.getInventory().addItem(drop.getItemStack());
            }
            cir.setReturnValue(null);
        }
    }

    @Override
    @Overwrite
    public boolean m_6469_(DamageSource source, float amount) {
        boolean damaged;
        if (!ForgeHooks.onPlayerAttack((LivingEntity)((Player)this), (DamageSource)source, (float)amount)) {
            return false;
        }
        if (this.m_6673_(source)) {
            return false;
        }
        if (this.f_36077_.f_35934_ && !source.m_269533_(DamageTypeTags.f_268738_)) {
            return false;
        }
        this.f_20891_ = 0;
        if (this.m_21223_() <= 0.0f) {
            return false;
        }
        if (source.m_7986_()) {
            if (this.m_9236_().m_46791_() == Difficulty.PEACEFUL) {
                return false;
            }
            if (this.m_9236_().m_46791_() == Difficulty.EASY) {
                amount = Math.min(amount / 2.0f + 1.0f, amount);
            }
            if (this.m_9236_().m_46791_() == Difficulty.HARD) {
                amount = amount * 3.0f / 2.0f;
            }
        }
        if (damaged = super.m_6469_(source, amount)) {
            this.m_36328_();
        }
        return damaged;
    }

    @Overwrite
    public boolean m_7099_(Player entityhuman) {
        Team team;
        if (entityhuman instanceof ServerPlayer) {
            ServerPlayer thatPlayer = (ServerPlayer)entityhuman;
            team = ((ServerPlayerEntityBridge)thatPlayer).bridge$getBukkitEntity().getScoreboard().getPlayerTeam(((ServerPlayerEntityBridge)thatPlayer).bridge$getBukkitEntity());
            if (team == null || team.allowFriendlyFire()) {
                return true;
            }
        } else {
            OfflinePlayer thisPlayer = Bukkit.getOfflinePlayer(entityhuman.m_6302_());
            team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(thisPlayer);
            if (team == null || team.allowFriendlyFire()) {
                return true;
            }
        }
        if (this instanceof ServerPlayer) {
            return !team.hasPlayer(((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity());
        }
        return !team.hasPlayer(Bukkit.getOfflinePlayer(this.m_6302_()));
    }

    @Inject(method={"actuallyHurt"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$damageEntityCustom(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        this.damageEntity0(damageSrc, damageAmount);
        ci.cancel();
    }

    @Overwrite
    public void m_5706_(Entity entity) {
        if (!ForgeHooks.onPlayerAttackTarget((Player)((Player)this), (Entity)entity)) {
            return;
        }
        if (entity.m_6097_() && !entity.m_7313_((Entity)((Player)this))) {
            float f = (float)this.m_21133_(Attributes.f_22281_);
            float f2 = entity instanceof LivingEntity ? EnchantmentHelper.m_44833_((net.minecraft.world.item.ItemStack)this.m_21205_(), (MobType)((LivingEntity)entity).m_6336_()) : EnchantmentHelper.m_44833_((net.minecraft.world.item.ItemStack)this.m_21205_(), (MobType)MobType.f_21640_);
            float f3 = this.m_36403_(0.5f);
            f2 *= f3;
            if ((f *= 0.2f + f3 * f3 * 0.8f) > 0.0f || f2 > 0.0f) {
                boolean flag = f3 > 0.9f;
                boolean flag2 = false;
                float i = (float)this.m_21133_(Attributes.f_22282_);
                i += (float)EnchantmentHelper.m_44894_((LivingEntity)((Player)this));
                if (this.m_20142_() && flag) {
                    this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12314_, this.m_5720_(), 1.0f, 1.0f);
                    i += 1.0f;
                    flag2 = true;
                }
                boolean flag3 = flag && this.f_19789_ > 0.0f && !this.f_19861_ && !this.m_6147_() && !this.m_20069_() && !this.m_21023_(MobEffects.f_19610_) && !this.m_20159_() && entity instanceof LivingEntity;
                flag3 = flag3 && !this.m_20142_();
                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit((Player)((Player)this), (Entity)entity, (boolean)flag3, (float)(flag3 ? 1.5f : 1.0f));
                boolean bl = flag3 = hitResult != null;
                if (flag3) {
                    f *= hitResult.getDamageModifier();
                }
                f += f2;
                boolean flag4 = false;
                double d0 = this.f_19787_ - this.f_19867_;
                if (flag && !flag3 && !flag2 && this.f_19861_ && d0 < (double)this.m_6113_()) {
                    net.minecraft.world.item.ItemStack itemstack = this.m_21120_(InteractionHand.MAIN_HAND);
                    flag4 = itemstack.canPerformAction(ToolActions.SWORD_SWEEP);
                }
                float f4 = 0.0f;
                boolean flag5 = false;
                int j = EnchantmentHelper.m_44914_((LivingEntity)((Player)this));
                if (entity instanceof LivingEntity) {
                    f4 = ((LivingEntity)entity).m_21223_();
                    if (j > 0 && !entity.m_6060_()) {
                        EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), 1);
                        Bukkit.getPluginManager().callEvent(combustEvent);
                        if (!combustEvent.isCancelled()) {
                            flag5 = true;
                            ((EntityBridge)entity).bridge$setOnFire(combustEvent.getDuration(), false);
                        }
                    }
                }
                Vec3 vec3d = entity.m_20184_();
                boolean flag6 = entity.m_6469_(this.m_269291_().m_269075_((Player)this), f);
                if (flag6) {
                    if (i > 0.0f) {
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity)entity).m_147240_((double)(i * 0.5f), (double)Mth.m_14031_((float)(this.m_146908_() * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(this.m_146908_() * ((float)Math.PI / 180)))));
                        } else {
                            entity.m_5997_((double)(-Mth.m_14031_((float)(this.m_146908_() * ((float)Math.PI / 180))) * i * 0.5f), 0.1, (double)(Mth.m_14089_((float)(this.m_146908_() * ((float)Math.PI / 180))) * i * 0.5f));
                        }
                        this.m_20256_(this.m_20184_().m_82542_(0.6, 1.0, 0.6));
                        this.m_6858_(false);
                    }
                    if (flag4) {
                        float f5 = 1.0f + EnchantmentHelper.m_44821_((LivingEntity)((Player)this)) * f;
                        List list = this.m_9236_().m_45976_(LivingEntity.class, this.m_21120_(InteractionHand.MAIN_HAND).getSweepHitBox((Player)this, entity));
                        double entityReachSq = Mth.m_144952_((double)this.getEntityReach());
                        for (LivingEntity entityliving : list) {
                            if (entityliving == this || entityliving == entity || this.m_7307_((Entity)entityliving) || entityliving instanceof ArmorStand && ((ArmorStand)entityliving).m_31677_() || !(this.m_20280_((Entity)entityliving) < entityReachSq) || !entityliving.m_6469_(((DamageSourceBridge)this.m_269291_().m_269075_((Player)this)).bridge$sweep(), f5)) continue;
                            entityliving.m_147240_((double)0.4f, (double)Mth.m_14031_((float)(this.m_146908_() * ((float)Math.PI / 180))), (double)(-Mth.m_14089_((float)(this.m_146908_() * ((float)Math.PI / 180)))));
                        }
                        this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12317_, this.m_5720_(), 1.0f, 1.0f);
                        this.m_36346_();
                    }
                    if (entity instanceof ServerPlayer && entity.f_19864_) {
                        boolean cancelled = false;
                        CraftPlayer player = ((ServerPlayerEntityBridge)entity).bridge$getBukkitEntity();
                        Vector velocity = CraftVector.toBukkit(vec3d);
                        PlayerVelocityEvent event = new PlayerVelocityEvent((org.bukkit.entity.Player)player, velocity.clone());
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            cancelled = true;
                        } else if (!velocity.equals(event.getVelocity())) {
                            player.setVelocity(event.getVelocity());
                        }
                        if (!cancelled) {
                            ((ServerPlayer)entity).f_8906_.m_9829_((Packet)new ClientboundSetEntityMotionPacket(entity));
                            entity.f_19864_ = false;
                            entity.m_20256_(vec3d);
                        }
                    }
                    if (flag3) {
                        this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12313_, this.m_5720_(), 1.0f, 1.0f);
                        this.m_5704_(entity);
                    }
                    if (!flag3 && !flag4) {
                        if (flag) {
                            this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12316_, this.m_5720_(), 1.0f, 1.0f);
                        } else {
                            this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12318_, this.m_5720_(), 1.0f, 1.0f);
                        }
                    }
                    if (f2 > 0.0f) {
                        this.m_5700_(entity);
                    }
                    this.m_21335_(entity);
                    if (entity instanceof LivingEntity) {
                        EnchantmentHelper.m_44823_((LivingEntity)((LivingEntity)entity), (Entity)((Player)this));
                    }
                    EnchantmentHelper.m_44896_((LivingEntity)((Player)this), (Entity)entity);
                    net.minecraft.world.item.ItemStack itemstack2 = this.m_21205_();
                    Entity object = entity;
                    if (entity instanceof PartEntity) {
                        object = ((PartEntity)entity).getParent();
                    }
                    if (!this.m_9236_().f_46443_ && !itemstack2.m_41619_() && object instanceof LivingEntity) {
                        net.minecraft.world.item.ItemStack copy = itemstack2.m_41777_();
                        itemstack2.m_41640_((LivingEntity)object, (Player)this);
                        if (itemstack2.m_41619_()) {
                            ForgeEventFactory.onPlayerDestroyItem((Player)((Player)this), (net.minecraft.world.item.ItemStack)copy, (InteractionHand)InteractionHand.MAIN_HAND);
                            this.m_21008_(InteractionHand.MAIN_HAND, net.minecraft.world.item.ItemStack.f_41583_);
                        }
                    }
                    if (entity instanceof LivingEntity) {
                        float f6 = f4 - ((LivingEntity)entity).m_21223_();
                        this.m_36222_(Stats.f_12928_, Math.round(f6 * 10.0f));
                        if (j > 0) {
                            EntityCombustByEntityEvent combustEvent2 = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), j * 4);
                            Bukkit.getPluginManager().callEvent(combustEvent2);
                            if (!combustEvent2.isCancelled()) {
                                ((EntityBridge)entity).bridge$setOnFire(combustEvent2.getDuration(), false);
                            }
                        }
                        if (this.m_9236_() instanceof ServerLevel && f6 > 2.0f) {
                            int k = (int)((double)f6 * 0.5);
                            ((ServerLevel)this.m_9236_()).m_8767_((ParticleOptions)ParticleTypes.f_123798_, entity.m_20185_(), entity.m_20186_() + (double)(entity.m_20206_() * 0.5f), entity.m_20189_(), k, 0.1, 0.0, 0.1, 0.2);
                        }
                    }
                    this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.ATTACK);
                    this.m_36399_(((WorldBridge)this.m_9236_()).bridge$spigotConfig().combatExhaustion);
                } else {
                    this.m_9236_().m_6263_(null, this.m_20185_(), this.m_20186_(), this.m_20189_(), SoundEvents.f_12315_, this.m_5720_(), 1.0f, 1.0f);
                    if (flag5) {
                        entity.m_20095_();
                    }
                    if (this instanceof ServerPlayerEntityBridge) {
                        ((ServerPlayerEntityBridge)((Object)this)).bridge$getBukkitEntity().updateInventory();
                    }
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos at, boolean force) {
        this.arclight$forceSleep = force;
        try {
            Either<Player.BedSleepingProblem, Unit> either = this.m_7720_(at);
            return either;
        }
        finally {
            this.arclight$forceSleep = false;
        }
    }

    @Override
    public Either<Player.BedSleepingProblem, Unit> bridge$trySleep(BlockPos at, boolean force) {
        return this.startSleepInBed(at, force);
    }

    @Inject(method={"stopSleepInBed"}, at={@At(value="FIELD", target="Lnet/minecraft/world/entity/player/Player;sleepCounter:I")})
    private void arclight$wakeup(boolean flag, boolean flag1, CallbackInfo ci) {
        BlockPos blockPos = this.m_21257_().orElse(null);
        CraftHumanEntity craftHumanEntity = this.bridge$getBukkitEntity();
        if (craftHumanEntity instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player)((Object)craftHumanEntity);
            Block bed = blockPos != null ? CraftBlock.at((LevelAccessor)this.m_9236_(), blockPos) : ((WorldBridge)this.m_9236_()).bridge$getWorld().getBlockAt(player.getLocation());
            PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed, true);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @ModifyArg(method={"jumpFromGround"}, index=0, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
    private float arclight$exhaustInfo(float f) {
        SpigotWorldConfig config = ((WorldBridge)this.m_9236_()).bridge$spigotConfig();
        if (config != null) {
            if (this.m_20142_()) {
                f = config.jumpSprintExhaustion;
                this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.JUMP_SPRINT);
            } else {
                f = config.jumpWalkExhaustion;
                this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.JUMP);
            }
        }
        return f;
    }

    @Redirect(method={"travel"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;setSharedFlag(IZ)V"))
    private void arclight$toggleGlide(Player playerEntity, int flag, boolean set) {
        if (playerEntity.m_20291_(flag) != set && !CraftEventFactory.callToggleGlideEvent((LivingEntity)((Player)this), set).isCancelled()) {
            playerEntity.m_20115_(flag, set);
        }
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause1(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.SWIM);
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause2(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.WALK_UNDERWATER);
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=2, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause3(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.WALK_ON_WATER);
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=3, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause4(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.SPRINT);
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=4, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause5(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.CROUCH);
    }

    @Inject(method={"checkMovementStatistics"}, at={@At(value="INVOKE", ordinal=5, target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$exhauseCause6(double p_36379_, double p_36380_, double p_36381_, CallbackInfo ci) {
        this.bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.WALK);
    }

    @Inject(method={"startFallFlying"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$startGlidingEvent(CallbackInfo ci) {
        if (CraftEventFactory.callToggleGlideEvent((LivingEntity)((Player)this), true).isCancelled()) {
            this.m_20115_(7, true);
            this.m_20115_(7, false);
            ci.cancel();
        }
    }

    @Inject(method={"stopFallFlying"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$stopGlidingEvent(CallbackInfo ci) {
        if (CraftEventFactory.callToggleGlideEvent((LivingEntity)((Player)this), false).isCancelled()) {
            ci.cancel();
        }
    }

    @Overwrite
    protected void m_36328_() {
        if (this.f_36109_ + 20L < this.m_9236_().m_46467_()) {
            if (this.respawnEntityOnShoulder(this.m_36331_())) {
                this.m_36362_(new CompoundTag());
            }
            if (this.respawnEntityOnShoulder(this.m_36332_())) {
                this.m_36364_(new CompoundTag());
            }
        }
    }

    private boolean respawnEntityOnShoulder(CompoundTag nbttagcompound) {
        return this.m_9236_().f_46443_ || nbttagcompound.m_128456_() || EntityType.m_20642_((CompoundTag)nbttagcompound, (Level)this.m_9236_()).map(entity -> {
            if (entity instanceof TamableAnimal) {
                ((TamableAnimal)entity).m_21816_(this.f_19820_);
            }
            entity.m_6034_(this.m_20185_(), this.m_20186_() + (double)0.7f, this.m_20189_());
            return ((ServerWorldBridge)this.m_9236_()).bridge$addEntitySerialized((Entity)entity, CreatureSpawnEvent.SpawnReason.SHOULDER_ENTITY);
        }).orElse(true) != false;
    }

    @Override
    public CraftHumanEntity getBukkitEntity() {
        return (CraftHumanEntity)this.internal$getBukkitEntity();
    }

    @Override
    public CraftHumanEntity bridge$getBukkitEntity() {
        return (CraftHumanEntity)this.internal$getBukkitEntity();
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, net.minecraft.world.item.ItemStack stack, boolean silent) {
        this.m_181122_(stack);
        if (slot == EquipmentSlot.MAINHAND) {
            this.equipEventAndSound(slot, (net.minecraft.world.item.ItemStack)this.f_36093_.f_35974_.set(this.f_36093_.f_35977_, (Object)stack), stack, silent);
        } else if (slot == EquipmentSlot.OFFHAND) {
            this.equipEventAndSound(slot, (net.minecraft.world.item.ItemStack)this.f_36093_.f_35976_.set(0, (Object)stack), stack, silent);
        } else if (slot.m_20743_() == EquipmentSlot.Type.ARMOR) {
            this.equipEventAndSound(slot, (net.minecraft.world.item.ItemStack)this.f_36093_.f_35975_.set(slot.m_20749_(), (Object)stack), stack, silent);
        }
    }

    @Redirect(method={"causeFoodExhaustion"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/food/FoodData;addExhaustion(F)V"))
    private void arclight$exhaustEvent(FoodData foodData, float amount) {
        EntityExhaustionEvent.ExhaustionReason reason = this.arclight$exhaustReason == null ? EntityExhaustionEvent.ExhaustionReason.UNKNOWN : this.arclight$exhaustReason;
        this.arclight$exhaustReason = null;
        EntityExhaustionEvent event = CraftEventFactory.callPlayerExhaustionEvent((Player)this, reason, amount);
        if (!event.isCancelled()) {
            this.f_36097_.m_38703_(event.getExhaustion());
        }
    }

    public void applyExhaustion(float f, EntityExhaustionEvent.ExhaustionReason reason) {
        this.bridge$pushExhaustReason(reason);
        this.m_36399_(f);
    }

    @Override
    public void bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason reason) {
        this.arclight$exhaustReason = reason;
    }
}

