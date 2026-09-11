/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$Pos
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$PosRot
 *  net.minecraft.network.protocol.game.ClientboundMoveEntityPacket$Rot
 *  net.minecraft.network.protocol.game.ClientboundRotateHeadPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
 *  net.minecraft.network.protocol.game.ClientboundSetPassengersPacket
 *  net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket
 *  net.minecraft.network.protocol.game.VecDeltaCodec
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.network.syncher.SynchedEntityData$DataValue
 *  net.minecraft.server.level.ServerEntity
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerPlayerConnection
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.MapItem
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.server.level;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.ServerEntityBridge;
import io.izzel.arclight.common.mod.ArclightConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.network.protocol.game.VecDeltaCodec;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ServerEntity.class})
public abstract class ServerEntityMixin
implements ServerEntityBridge {
    @Shadow
    @Final
    private Entity f_8510_;
    @Shadow
    private List<Entity> f_8523_;
    @Shadow
    @Final
    private Consumer<Packet<?>> f_8513_;
    @Shadow
    private int f_8521_;
    @Shadow
    @Final
    private ServerLevel f_8509_;
    @Shadow
    @Final
    private int f_8511_;
    @Shadow
    private int f_8517_;
    @Shadow
    private int f_8518_;
    @Shadow
    @Final
    private VecDeltaCodec f_214995_;
    @Shadow
    private boolean f_8524_;
    @Shadow
    private int f_8522_;
    @Shadow
    private boolean f_8525_;
    @Shadow
    @Final
    private boolean f_8512_;
    @Shadow
    private Vec3 f_8520_;
    @Shadow
    private int f_8519_;
    @Shadow
    @Nullable
    private List<SynchedEntityData.DataValue<?>> f_263120_;
    private Set<ServerPlayerConnection> trackedPlayers;
    @Unique
    private int lastTick;
    @Unique
    private int lastUpdate;
    @Unique
    private int lastPosUpdate;
    @Unique
    private int lastMapUpdate;

    @Shadow
    protected abstract void m_8543_();

    @Shadow
    protected abstract void m_8538_(Packet<?> var1);

    @Shadow
    private static Stream<Entity> m_277180_(List<Entity> p_277592_, List<Entity> p_277658_) {
        return null;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(ServerLevel serverWorld, Entity entity, int updateFrequency, boolean sendVelocityUpdates, Consumer<Packet<?>> packetConsumer, CallbackInfo ci) {
        this.trackedPlayers = new HashSet<ServerPlayerConnection>();
        this.lastTick = ArclightConstants.currentTick - 1;
        this.lastMapUpdate = -1;
        this.lastPosUpdate = -1;
        this.lastUpdate = -1;
    }

    public void arclight$constructor(ServerLevel serverWorld, Entity entity, int updateFrequency, boolean sendVelocityUpdates, Consumer<Packet<?>> packetConsumer) {
        throw new NullPointerException();
    }

    public void arclight$constructor(ServerLevel serverWorld, Entity entity, int updateFrequency, boolean sendVelocityUpdates, Consumer<Packet<?>> packetConsumer, Set<ServerPlayerConnection> set) {
        this.arclight$constructor(serverWorld, entity, updateFrequency, sendVelocityUpdates, packetConsumer);
        this.trackedPlayers = set;
    }

    @Override
    public void bridge$setTrackedPlayers(Set<ServerPlayerConnection> trackedPlayers) {
        this.trackedPlayers = trackedPlayers;
    }

    @Overwrite
    public void m_8533_() {
        int elapsedTicks;
        List list = this.f_8510_.m_20197_();
        if (!list.equals(this.f_8523_)) {
            this.m_8538_((Packet<?>)new ClientboundSetPassengersPacket(this.f_8510_));
            ServerEntityMixin.m_277180_(list, this.f_8523_).forEach(p_289307_ -> {
                if (p_289307_ instanceof ServerPlayer) {
                    ServerPlayer serverplayer1 = (ServerPlayer)p_289307_;
                    serverplayer1.f_8906_.m_9774_(serverplayer1.m_20185_(), serverplayer1.m_20186_(), serverplayer1.m_20189_(), serverplayer1.m_146908_(), serverplayer1.m_146909_());
                }
            });
            this.f_8523_ = list;
        }
        if ((elapsedTicks = ArclightConstants.currentTick - this.lastTick) < 0) {
            elapsedTicks = 0;
        }
        this.lastTick = ArclightConstants.currentTick;
        Entity entity = this.f_8510_;
        if (entity instanceof ItemFrame) {
            MapItemSavedData mapdata;
            ItemFrame itemFrame = (ItemFrame)entity;
            ItemStack itemstack = itemFrame.m_31822_();
            if (this.f_8521_ / 10 != this.lastMapUpdate && itemstack.m_41720_() instanceof MapItem && (mapdata = MapItem.m_42853_((ItemStack)itemstack, (Level)this.f_8509_)) != null) {
                for (ServerPlayerConnection connection : this.trackedPlayers) {
                    ServerPlayer serverplayerentity = connection.m_142253_();
                    mapdata.m_77918_((Player)serverplayerentity, itemstack);
                    Packet ipacket = ((MapItem)itemstack.m_41720_()).m_7233_(itemstack, (Level)this.f_8509_, (Player)serverplayerentity);
                    if (ipacket == null) continue;
                    serverplayerentity.f_8906_.m_9829_(ipacket);
                }
            }
            this.m_8543_();
        }
        if (this.f_8521_ / this.f_8511_ != this.lastUpdate || this.f_8510_.f_19812_ || this.f_8510_.m_20088_().m_135352_()) {
            if (this.f_8510_.m_20159_()) {
                boolean flag2;
                int i1 = Mth.m_14143_((float)(this.f_8510_.m_146908_() * 256.0f / 360.0f));
                int l1 = Mth.m_14143_((float)(this.f_8510_.m_146909_() * 256.0f / 360.0f));
                boolean bl = flag2 = Math.abs(i1 - this.f_8517_) >= 1 || Math.abs(l1 - this.f_8518_) >= 1;
                if (flag2) {
                    this.f_8513_.accept((Packet<?>)new ClientboundMoveEntityPacket.Rot(this.f_8510_.m_19879_(), (byte)i1, (byte)l1, this.f_8510_.m_20096_()));
                    this.f_8517_ = i1;
                    this.f_8518_ = l1;
                }
                this.f_214995_.m_238033_(this.f_8510_.m_213870_());
                this.m_8543_();
                this.f_8524_ = true;
            } else {
                Vec3 vector3d1;
                double d0;
                this.f_8522_ += elapsedTicks;
                int l = Mth.m_14143_((float)(this.f_8510_.m_146908_() * 256.0f / 360.0f));
                int k1 = Mth.m_14143_((float)(this.f_8510_.m_146909_() * 256.0f / 360.0f));
                Vec3 vector3d = this.f_8510_.m_213870_();
                boolean flag3 = this.f_214995_.m_238031_(vector3d).m_82556_() >= 7.62939453125E-6;
                ClientboundTeleportEntityPacket ipacket1 = null;
                boolean flag4 = flag3 || this.f_8521_ / 60 != this.lastPosUpdate;
                boolean flag = Math.abs(l - this.f_8517_) >= 1 || Math.abs(k1 - this.f_8518_) >= 1;
                boolean pos = false;
                boolean rot = false;
                if (this.f_8521_ > 0 || this.f_8510_ instanceof AbstractArrow) {
                    boolean flag1;
                    long i = this.f_214995_.m_238025_(vector3d);
                    long j = this.f_214995_.m_238027_(vector3d);
                    long k = this.f_214995_.m_238029_(vector3d);
                    boolean bl = flag1 = i < -32768L || i > 32767L || j < -32768L || j > 32767L || k < -32768L || k > 32767L;
                    if (!flag1 && this.f_8522_ <= 400 && !this.f_8524_ && this.f_8525_ == this.f_8510_.m_20096_()) {
                        if (!(flag4 && flag || this.f_8510_ instanceof AbstractArrow)) {
                            if (flag4) {
                                ipacket1 = new ClientboundMoveEntityPacket.Pos(this.f_8510_.m_19879_(), (short)i, (short)j, (short)k, this.f_8510_.m_20096_());
                                pos = true;
                            } else if (flag) {
                                ipacket1 = new ClientboundMoveEntityPacket.Rot(this.f_8510_.m_19879_(), (byte)l, (byte)k1, this.f_8510_.m_20096_());
                                rot = true;
                            }
                        } else {
                            ipacket1 = new ClientboundMoveEntityPacket.PosRot(this.f_8510_.m_19879_(), (short)i, (short)j, (short)k, (byte)l, (byte)k1, this.f_8510_.m_20096_());
                            rot = true;
                            pos = true;
                        }
                    } else {
                        this.f_8525_ = this.f_8510_.m_20096_();
                        this.f_8522_ = 0;
                        ipacket1 = new ClientboundTeleportEntityPacket(this.f_8510_);
                        rot = true;
                        pos = true;
                    }
                }
                if ((this.f_8512_ || this.f_8510_.f_19812_ || this.f_8510_ instanceof LivingEntity && ((LivingEntity)this.f_8510_).m_21255_()) && this.f_8521_ > 0 && ((d0 = (vector3d1 = this.f_8510_.m_20184_()).m_82557_(this.f_8520_)) > 1.0E-7 || d0 > 0.0 && vector3d1.m_82556_() == 0.0)) {
                    this.f_8520_ = vector3d1;
                    this.f_8513_.accept((Packet<?>)new ClientboundSetEntityMotionPacket(this.f_8510_.m_19879_(), this.f_8520_));
                }
                if (ipacket1 != null) {
                    this.f_8513_.accept((Packet<?>)ipacket1);
                }
                this.m_8543_();
                if (pos) {
                    this.f_214995_.m_238033_(vector3d);
                }
                if (rot) {
                    this.f_8517_ = l;
                    this.f_8518_ = k1;
                }
                this.f_8524_ = false;
            }
            int j1 = Mth.m_14143_((float)(this.f_8510_.m_6080_() * 256.0f / 360.0f));
            if (Math.abs(j1 - this.f_8519_) >= 1) {
                this.f_8513_.accept((Packet<?>)new ClientboundRotateHeadPacket(this.f_8510_, (byte)j1));
                this.f_8519_ = j1;
            }
            this.f_8510_.f_19812_ = false;
        }
        this.lastUpdate = this.f_8521_ / this.f_8511_;
        this.lastPosUpdate = this.f_8521_ / 60;
        this.lastMapUpdate = this.f_8521_ / 10;
        this.f_8521_ += elapsedTicks;
        if (this.f_8510_.f_19864_) {
            boolean cancelled = false;
            if (this.f_8510_ instanceof ServerPlayer) {
                CraftPlayer player = ((ServerPlayerEntityBridge)this.f_8510_).bridge$getBukkitEntity();
                Vector velocity = player.getVelocity();
                PlayerVelocityEvent event = new PlayerVelocityEvent((org.bukkit.entity.Player)player, velocity.clone());
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    cancelled = true;
                } else if (!velocity.equals(event.getVelocity())) {
                    player.setVelocity(event.getVelocity());
                }
            }
            if (!cancelled) {
                this.m_8538_((Packet<?>)new ClientboundSetEntityMotionPacket(this.f_8510_));
            }
            this.f_8510_.f_19864_ = false;
        }
    }

    @Overwrite
    public void m_289200_(ServerPlayer player, Consumer<Packet<?>> consumer) {
        Mob entityinsentient;
        if (this.f_8510_.m_213877_()) {
            return;
        }
        Packet packet = this.f_8510_.m_5654_();
        this.f_8519_ = Mth.m_14143_((float)(this.f_8510_.m_6080_() * 256.0f / 360.0f));
        consumer.accept(packet);
        if (this.f_263120_ != null) {
            consumer.accept((Packet<?>)new ClientboundSetEntityDataPacket(this.f_8510_.m_19879_(), this.f_263120_));
        }
        boolean flag = this.f_8512_;
        EquipmentSlot[] equipmentSlotArray = this.f_8510_;
        if (equipmentSlotArray instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)equipmentSlotArray;
            Collection collection = livingEntity.m_21204_().m_22170_();
            if (this.f_8510_.m_19879_() == player.m_19879_()) {
                ((ServerPlayerEntityBridge)this.f_8510_).bridge$getBukkitEntity().injectScaledMaxHealth(collection, false);
            }
            if (!collection.isEmpty()) {
                consumer.accept((Packet<?>)new ClientboundUpdateAttributesPacket(this.f_8510_.m_19879_(), collection));
            }
            if (livingEntity.m_21255_()) {
                flag = true;
            }
        }
        this.f_8520_ = this.f_8510_.m_20184_();
        if (flag && !(this.f_8510_ instanceof LivingEntity)) {
            consumer.accept((Packet<?>)new ClientboundSetEntityMotionPacket(this.f_8510_.m_19879_(), this.f_8520_));
        }
        if (this.f_8510_ instanceof LivingEntity) {
            ArrayList list = Lists.newArrayList();
            for (EquipmentSlot enumitemslot : EquipmentSlot.values()) {
                ItemStack itemstack = ((LivingEntity)this.f_8510_).m_6844_(enumitemslot);
                if (itemstack.m_41619_()) continue;
                list.add(Pair.of((Object)enumitemslot, (Object)itemstack.m_41777_()));
            }
            if (!list.isEmpty()) {
                consumer.accept((Packet<?>)new ClientboundSetEquipmentPacket(this.f_8510_.m_19879_(), (List)list));
            }
            ((LivingEntity)this.f_8510_).m_21315_();
        }
        if (this.f_8510_ instanceof ServerPlayer) {
            consumer.accept((Packet<?>)new ClientboundRotateHeadPacket(this.f_8510_, (byte)Mth.m_14143_((float)(this.f_8510_.m_6080_() * 256.0f / 360.0f))));
        }
        if (!this.f_8510_.m_20197_().isEmpty()) {
            consumer.accept((Packet<?>)new ClientboundSetPassengersPacket(this.f_8510_));
        }
        if (this.f_8510_.m_20159_()) {
            consumer.accept((Packet<?>)new ClientboundSetPassengersPacket(this.f_8510_.m_20202_()));
        }
        if (this.f_8510_ instanceof Mob && (entityinsentient = (Mob)this.f_8510_).m_21523_()) {
            consumer.accept((Packet<?>)new ClientboundSetEntityLinkPacket((Entity)entityinsentient, entityinsentient.m_21524_()));
        }
    }

    @Inject(method={"sendDirtyEntityData"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/server/level/ServerEntity;broadcastAndSend(Lnet/minecraft/network/protocol/Packet;)V")})
    private void arclight$sendScaledHealth(CallbackInfo ci, SynchedEntityData entitydatamanager, List<SynchedEntityData.DataValue<?>> list, Set<AttributeInstance> set) {
        Entity entity = this.f_8510_;
        if (entity instanceof ServerPlayerEntityBridge) {
            ServerPlayerEntityBridge player = (ServerPlayerEntityBridge)entity;
            player.bridge$getBukkitEntity().injectScaledMaxHealth(set, false);
        }
    }
}

