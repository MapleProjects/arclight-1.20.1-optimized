/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMaps
 *  net.minecraft.ChatFormatting
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.commands.CommandRuntimeException
 *  net.minecraft.commands.CommandSigningContext
 *  net.minecraft.commands.CommandSigningContext$SignedArguments
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.Connection
 *  net.minecraft.network.PacketListener
 *  net.minecraft.network.PacketSendListener
 *  net.minecraft.network.chat.ChatType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Component$Serializer
 *  net.minecraft.network.chat.LastSeenMessages
 *  net.minecraft.network.chat.LastSeenMessages$Update
 *  net.minecraft.network.chat.OutgoingChatMessage
 *  net.minecraft.network.chat.PlayerChatMessage
 *  net.minecraft.network.chat.SignableCommand
 *  net.minecraft.network.chat.SignedMessageChain$DecodeException
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.PacketUtils
 *  net.minecraft.network.protocol.game.ClientboundAddEntityPacket
 *  net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket
 *  net.minecraft.network.protocol.game.ClientboundDisconnectPacket
 *  net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
 *  net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket
 *  net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
 *  net.minecraft.network.protocol.game.ClientboundSystemChatPacket
 *  net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket
 *  net.minecraft.network.protocol.game.ServerboundChatCommandPacket
 *  net.minecraft.network.protocol.game.ServerboundChatPacket
 *  net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket
 *  net.minecraft.network.protocol.game.ServerboundContainerClickPacket
 *  net.minecraft.network.protocol.game.ServerboundContainerClosePacket
 *  net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket
 *  net.minecraft.network.protocol.game.ServerboundEditBookPacket
 *  net.minecraft.network.protocol.game.ServerboundInteractPacket
 *  net.minecraft.network.protocol.game.ServerboundInteractPacket$Handler
 *  net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
 *  net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket
 *  net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket
 *  net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ServerboundPlayerActionPacket
 *  net.minecraft.network.protocol.game.ServerboundPlayerActionPacket$Action
 *  net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket
 *  net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket$Action
 *  net.minecraft.network.protocol.game.ServerboundResourcePackPacket
 *  net.minecraft.network.protocol.game.ServerboundSelectTradePacket
 *  net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket
 *  net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket
 *  net.minecraft.network.protocol.game.ServerboundSignUpdatePacket
 *  net.minecraft.network.protocol.game.ServerboundSwingPacket
 *  net.minecraft.network.protocol.game.ServerboundTeleportToEntityPacket
 *  net.minecraft.network.protocol.game.ServerboundUseItemOnPacket
 *  net.minecraft.network.protocol.game.ServerboundUseItemPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.FilteredText
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  net.minecraft.server.network.ServerGamePacketListenerImpl$EntityInteraction
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.util.FutureChain
 *  net.minecraft.util.Mth
 *  net.minecraft.util.StringUtil
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MoverType
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.animal.allay.Allay
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.ChatVisiblity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ClickType
 *  net.minecraft.world.inventory.MerchantMenu
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.ClipContext$Fluid
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult$Type
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.entity.living.LivingSwapItemsEvent$Hands
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.network;

import com.mojang.brigadier.ParseResults;
import com.mojang.datafixers.util.Pair;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.ContainerBridge;
import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import io.izzel.arclight.common.bridge.core.network.play.ServerPlayNetHandlerBridge;
import io.izzel.arclight.common.bridge.core.network.play.TimestampedPacket;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.bridge.core.server.management.PlayerInteractionManagerBridge;
import io.izzel.arclight.common.bridge.core.server.management.PlayerListBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.ArclightConstants;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.stream.Collectors;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSigningContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.LastSeenMessages;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.chat.SignableCommand;
import net.minecraft.network.chat.SignedMessageChain;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.network.protocol.game.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ServerboundEditBookPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundResourcePackPacket;
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundTeleportToEntityPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.FilteredText;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.minecraft.util.FutureChain;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingSwapItemsEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.LazyPlayerSet;
import org.bukkit.craftbukkit.v1_20_R1.util.Waitable;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRecipeBookClickEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.slf4j.Logger;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ServerGamePacketListenerImpl.class})
public abstract class ServerPlayNetHandlerMixin
implements ServerPlayNetHandlerBridge {
    @Shadow
    @Final
    private MinecraftServer f_9745_;
    @Shadow
    public ServerPlayer f_9743_;
    @Shadow
    @Final
    public Connection f_9742_;
    @Shadow
    private net.minecraft.world.entity.Entity f_9759_;
    @Shadow
    private double f_9760_;
    @Shadow
    private double f_9761_;
    @Shadow
    private double f_9762_;
    @Shadow
    private double f_9763_;
    @Shadow
    private double f_9764_;
    @Shadow
    private double f_9765_;
    @Shadow
    private boolean f_9738_;
    @Shadow
    private int f_9740_;
    @Shadow
    private int f_9741_;
    @Shadow
    private Vec3 f_9766_;
    @Shadow
    private int f_9746_;
    @Shadow
    private int f_9735_;
    @Shadow
    private double f_9753_;
    @Shadow
    private double f_9754_;
    @Shadow
    private double f_9755_;
    @Shadow
    @Final
    private static Logger f_9744_;
    @Shadow
    private double f_9756_;
    @Shadow
    private double f_9757_;
    @Shadow
    private double f_9758_;
    @Shadow
    private boolean f_9736_;
    @Shadow
    private int f_9767_;
    @Shadow
    private int f_9750_;
    @Shadow
    private int f_9751_;
    @Shadow
    @Final
    @Mutable
    private FutureChain f_241681_;
    private static final int SURVIVAL_PLACE_DISTANCE_SQUARED = 36;
    private static final int CREATIVE_PLACE_DISTANCE_SQUARED = 49;
    private CraftServer cserver;
    public boolean processedDisconnect;
    private int allowedPlayerTicks;
    private int dropCount;
    private int lastTick;
    private volatile int lastBookTick;
    private int lastDropTick;
    private double lastPosX;
    private double lastPosY;
    private double lastPosZ;
    private float lastPitch;
    private float lastYaw;
    private boolean justTeleported;
    private boolean hasMoved;
    private int limitedPackets;
    private long lastLimitedPacket = -1L;
    private static final ResourceLocation CUSTOM_REGISTER;
    private static final ResourceLocation CUSTOM_UNREGISTER;
    private transient PlayerTeleportEvent.TeleportCause arclight$cause;

    @Shadow
    public abstract void m_7026_(Component var1);

    @Shadow
    protected abstract boolean m_9956_();

    @Shadow
    public abstract void m_9953_();

    @Shadow
    public abstract void m_9774_(double var1, double var3, double var5, float var7, float var8);

    @Shadow
    public abstract void m_9829_(Packet<?> var1);

    @Shadow
    protected abstract boolean m_9793_(net.minecraft.world.entity.Entity var1);

    @Shadow
    private static double m_143609_(double p_143610_) {
        return 0.0;
    }

    @Shadow
    private static double m_143653_(double p_143654_) {
        return 0.0;
    }

    @Shadow
    private static boolean m_143663_(double p_143664_, double p_143665_, double p_143666_, float p_143667_, float p_143668_) {
        return false;
    }

    @Shadow
    protected abstract void m_143634_(List<FilteredText> var1, UnaryOperator<String> var2, ItemStack var3);

    @Shadow
    public abstract void m_215201_(int var1);

    @Shadow
    private static boolean m_215214_(String p_215215_) {
        return false;
    }

    @Shadow
    protected abstract CompletableFuture<FilteredText> m_243132_(String var1);

    @Shadow
    protected abstract ParseResults<CommandSourceStack> m_242658_(String var1);

    @Shadow
    protected abstract void m_215251_();

    @Shadow
    protected abstract Optional<LastSeenMessages> m_247189_(String var1, Instant var2, LastSeenMessages.Update var3);

    @Shadow
    protected abstract PlayerChatMessage m_247340_(ServerboundChatPacket var1, LastSeenMessages var2) throws SignedMessageChain.DecodeException;

    @Shadow
    protected abstract void m_246889_(SignedMessageChain.DecodeException var1);

    @Shadow
    protected abstract Map<String, PlayerChatMessage> m_246206_(ServerboundChatCommandPacket var1, SignableCommand<?> var2, LastSeenMessages var3) throws SignedMessageChain.DecodeException;

    @Shadow
    protected abstract boolean m_288208_(LevelReader var1, AABB var2, double var3, double var5, double var7);

    public CraftPlayer getCraftPlayer() {
        return this.f_9743_ == null ? null : ((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity();
    }

    @Override
    public boolean bridge$processedDisconnect() {
        return this.processedDisconnect;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(MinecraftServer server, Connection networkManagerIn, ServerPlayer playerIn, CallbackInfo ci) {
        this.cserver = (CraftServer)Bukkit.getServer();
        this.allowedPlayerTicks = 1;
        this.dropCount = 0;
        this.lastPosX = Double.MAX_VALUE;
        this.lastPosY = Double.MAX_VALUE;
        this.lastPosZ = Double.MAX_VALUE;
        this.lastPitch = Float.MAX_VALUE;
        this.lastYaw = Float.MAX_VALUE;
        this.justTeleported = false;
        this.f_241681_ = new FutureChain((Executor)ArclightServer.getChatExecutor());
    }

    @Overwrite
    public void m_9942_(Component textComponent) {
        this.disconnect(CraftChatMessage.fromComponent(textComponent));
    }

    public void disconnect(final String s) {
        if (this.processedDisconnect) {
            return;
        }
        if (!this.cserver.isPrimaryThread()) {
            Waitable<Object> waitable = new Waitable<Object>(){

                @Override
                protected Object evaluate() {
                    ServerPlayNetHandlerMixin.this.disconnect(s);
                    return null;
                }
            };
            ((MinecraftServerBridge)this.f_9745_).bridge$queuedProcess(waitable);
            try {
                waitable.get();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        String leaveMessage = String.valueOf(ChatFormatting.YELLOW) + this.f_9743_.m_6302_() + " left the game.";
        PlayerKickEvent event = new PlayerKickEvent(this.getCraftPlayer(), s, leaveMessage);
        if (this.cserver.getServer().m_130010_()) {
            this.cserver.getPluginManager().callEvent(event);
        }
        if (event.isCancelled()) {
            return;
        }
        ArclightCaptures.captureQuitMessage(event.getLeaveMessage());
        Component textComponent = CraftChatMessage.fromString(event.getReason(), true)[0];
        this.f_9742_.m_243124_((Packet)new ClientboundDisconnectPacket(textComponent), PacketSendListener.m_243092_(() -> this.f_9742_.m_129507_(textComponent)));
        this.m_7026_(textComponent);
        this.f_9742_.m_129540_();
        this.f_9745_.m_18709_(() -> ((Connection)this.f_9742_).m_129541_());
    }

    @Override
    public void bridge$disconnect(String str) {
        this.disconnect(str);
    }

    @Overwrite
    public void m_5659_(ServerboundMoveVehiclePacket packetplayinvehiclemove) {
        PacketUtils.m_131359_((Packet)packetplayinvehiclemove, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (ServerPlayNetHandlerMixin.m_143663_(packetplayinvehiclemove.m_134199_(), packetplayinvehiclemove.m_134202_(), packetplayinvehiclemove.m_134203_(), packetplayinvehiclemove.m_134204_(), packetplayinvehiclemove.m_134205_())) {
            this.m_9942_((Component)Component.m_237115_((String)"multiplayer.disconnect.invalid_vehicle_movement"));
        } else {
            net.minecraft.world.entity.Entity entity = this.f_9743_.m_20201_();
            if (entity != this.f_9743_ && entity.m_6688_() == this.f_9743_ && entity == this.f_9759_) {
                LivingEntity entityliving;
                ServerLevel worldserver = this.f_9743_.m_284548_();
                double d0 = entity.m_20185_();
                double d2 = entity.m_20186_();
                double d3 = entity.m_20189_();
                double d4 = packetplayinvehiclemove.m_134199_();
                double d5 = packetplayinvehiclemove.m_134202_();
                double d6 = packetplayinvehiclemove.m_134203_();
                float f = packetplayinvehiclemove.m_134204_();
                float f2 = packetplayinvehiclemove.m_134205_();
                double d7 = d4 - this.f_9760_;
                double d8 = d5 - this.f_9761_;
                double d9 = d6 - this.f_9762_;
                double d10 = entity.m_20184_().m_82556_();
                double d11 = d7 * d7 + d8 * d8 + d9 * d9;
                this.allowedPlayerTicks += (int)(System.currentTimeMillis() / 50L - (long)this.lastTick);
                this.allowedPlayerTicks = Math.max(this.allowedPlayerTicks, 1);
                this.lastTick = (int)(System.currentTimeMillis() / 50L);
                ++this.f_9740_;
                int i = this.f_9740_ - this.f_9741_;
                if (i > Math.max(this.allowedPlayerTicks, 5)) {
                    f_9744_.debug(this.f_9743_.m_6302_() + " is sending move packets too frequently (" + i + " packets since last tick)");
                    i = 1;
                }
                this.allowedPlayerTicks = d11 > 0.0 ? --this.allowedPlayerTicks : 20;
                double speed = this.f_9743_.m_150110_().f_35935_ ? (double)(this.f_9743_.m_150110_().f_35939_ * 20.0f) : (double)(this.f_9743_.m_150110_().f_35940_ * 10.0f);
                speed *= 2.0;
                if (d11 - d10 > Math.max(100.0, Math.pow((double)(10.0f * (float)i) * speed, 2.0)) && !this.m_9956_()) {
                    f_9744_.warn("{} (vehicle of {}) moved too quickly! {},{},{}", new Object[]{entity.m_7755_().getString(), this.f_9743_.m_7755_().getString(), d7, d8, d9});
                    this.f_9742_.m_129512_((Packet)new ClientboundMoveVehiclePacket(entity));
                    return;
                }
                boolean flag = worldserver.m_45756_(entity, entity.m_20191_().m_82406_(0.0625));
                d7 = d4 - this.f_9763_;
                d8 = d5 - this.f_9764_ - 1.0E-6;
                d9 = d6 - this.f_9765_;
                boolean flag1 = entity.f_201939_;
                if (entity instanceof LivingEntity && (entityliving = (LivingEntity)entity).m_6147_()) {
                    entityliving.m_183634_();
                }
                entity.m_6478_(MoverType.PLAYER, new Vec3(d7, d8, d9));
                double d12 = d8;
                d7 = d4 - entity.m_20185_();
                d8 = d5 - entity.m_20186_();
                if (d8 > -0.5 || d8 < 0.5) {
                    d8 = 0.0;
                }
                d9 = d6 - entity.m_20189_();
                d11 = d7 * d7 + d8 * d8 + d9 * d9;
                boolean flag2 = false;
                if (d11 > SpigotConfig.movedWronglyThreshold) {
                    flag2 = true;
                    f_9744_.warn("{} (vehicle of {}) moved wrongly! {}", new Object[]{entity.m_7755_().getString(), this.f_9743_.m_7755_().getString(), Math.sqrt(d11)});
                }
                Location curPos = this.getCraftPlayer().getLocation();
                entity.m_19890_(d4, d5, d6, f, f2);
                this.f_9743_.m_19890_(d4, d5, d6, this.f_9743_.m_146908_(), this.f_9743_.m_146909_());
                boolean flag3 = worldserver.m_45756_(entity, entity.m_20191_().m_82406_(0.0625));
                if (flag && (flag2 || !flag3)) {
                    entity.m_19890_(d0, d2, d3, f, f2);
                    this.f_9743_.m_19890_(d0, d2, d3, this.f_9743_.m_146908_(), this.f_9743_.m_146909_());
                    this.f_9742_.m_129512_((Packet)new ClientboundMoveVehiclePacket(entity));
                    return;
                }
                CraftPlayer player = this.getCraftPlayer();
                if (!this.hasMoved) {
                    this.lastPosX = curPos.getX();
                    this.lastPosY = curPos.getY();
                    this.lastPosZ = curPos.getZ();
                    this.lastYaw = curPos.getYaw();
                    this.lastPitch = curPos.getPitch();
                    this.hasMoved = true;
                }
                Location from = new Location(player.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch);
                Location to = player.getLocation().clone();
                to.setX(packetplayinvehiclemove.m_134199_());
                to.setY(packetplayinvehiclemove.m_134202_());
                to.setZ(packetplayinvehiclemove.m_134203_());
                to.setYaw(packetplayinvehiclemove.m_134204_());
                to.setPitch(packetplayinvehiclemove.m_134205_());
                double delta = Math.pow(this.lastPosX - to.getX(), 2.0) + Math.pow(this.lastPosY - to.getY(), 2.0) + Math.pow(this.lastPosZ - to.getZ(), 2.0);
                float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch());
                if ((delta > 0.00390625 || deltaAngle > 10.0f) && !((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
                    this.lastPosX = to.getX();
                    this.lastPosY = to.getY();
                    this.lastPosZ = to.getZ();
                    this.lastYaw = to.getYaw();
                    this.lastPitch = to.getPitch();
                    Location oldTo = to.clone();
                    PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
                    this.cserver.getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        this.bridge$teleport(from);
                        return;
                    }
                    if (!oldTo.equals(event.getTo()) && !event.isCancelled()) {
                        ((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                        return;
                    }
                    if (!from.equals(this.getCraftPlayer().getLocation()) && this.justTeleported) {
                        this.justTeleported = false;
                        return;
                    }
                }
                this.f_9743_.m_284548_().m_7726_().m_8385_(this.f_9743_);
                this.f_9743_.m_36378_(this.f_9743_.m_20185_() - d0, this.f_9743_.m_20186_() - d2, this.f_9743_.m_20189_() - d3);
                this.f_9738_ = d11 >= -0.03125 && !flag1 && !this.f_9745_.m_129915_() && !entity.m_20068_() && this.m_9793_(entity);
                this.f_9763_ = entity.m_20185_();
                this.f_9764_ = entity.m_20186_();
                this.f_9765_ = entity.m_20189_();
            }
        }
    }

    @Inject(method={"handleAcceptTeleportPacket"}, at={@At(value="FIELD", shift=At.Shift.AFTER, target="Lnet/minecraft/server/network/ServerGamePacketListenerImpl;awaitingPositionFromClient:Lnet/minecraft/world/phys/Vec3;")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;isChangingDimension()Z"))})
    private void arclight$updateLoc(ServerboundAcceptTeleportationPacket packetIn, CallbackInfo ci) {
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isValid()) {
            this.f_9743_.m_284548_().m_7726_().m_8385_(this.f_9743_);
        }
    }

    @Inject(method={"handleAcceptTeleportPacket"}, cancellable=true, at={@At(value="FIELD", target="Lnet/minecraft/server/network/ServerGamePacketListenerImpl;awaitingTeleport:I")})
    private void arclight$confirm(ServerboundAcceptTeleportationPacket packetIn, CallbackInfo ci) {
        if (this.f_9766_ == null) {
            ci.cancel();
        }
    }

    @Inject(method={"handleSelectTrade"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/inventory/MerchantMenu;setSelectionHint(I)V")})
    private void arclight$tradeSelect(ServerboundSelectTradePacket packetIn, CallbackInfo ci, int i, AbstractContainerMenu container) {
        TradeSelectEvent event = CraftEventFactory.callTradeSelectEvent(this.f_9743_, i, (MerchantMenu)container);
        if (event.isCancelled()) {
            ((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity().updateInventory();
            ci.cancel();
        }
    }

    @Inject(method={"handleEditBook"}, at={@At(value="HEAD")})
    private void arclight$editBookSpam(ServerboundEditBookPacket packetIn, CallbackInfo ci) {
        if (this.lastBookTick == 0) {
            this.lastBookTick = ArclightConstants.currentTick - 20;
        }
        if (this.lastBookTick + 20 > ArclightConstants.currentTick) {
            this.disconnect("Book edited too quickly!");
            return;
        }
        this.lastBookTick = ArclightConstants.currentTick;
    }

    @Overwrite
    private void m_9812_(List<FilteredText> list, int slot) {
        ItemStack old = this.f_9743_.m_150109_().m_8020_(slot);
        if (old.m_150930_(Items.f_42614_)) {
            ItemStack itemstack = old.m_41777_();
            this.m_143634_(list, UnaryOperator.identity(), itemstack);
            CraftEventFactory.handleEditBookEvent(this.f_9743_, slot, old, itemstack);
        }
    }

    @Overwrite
    private void m_215208_(FilteredText text, List<FilteredText> list, int slot) {
        ItemStack old = this.f_9743_.m_150109_().m_8020_(slot);
        if (old.m_150930_(Items.f_42614_)) {
            ItemStack itemStack = new ItemStack((ItemLike)Items.f_42615_);
            CompoundTag compoundtag = old.m_41783_();
            if (compoundtag != null) {
                itemStack.m_41751_(compoundtag.m_6426_());
            }
            itemStack.m_41700_("author", (Tag)StringTag.m_129297_((String)this.f_9743_.m_7755_().getString()));
            if (this.f_9743_.m_143387_()) {
                itemStack.m_41700_("title", (Tag)StringTag.m_129297_((String)text.m_243113_()));
            } else {
                itemStack.m_41700_("filtered_title", (Tag)StringTag.m_129297_((String)text.m_243113_()));
                itemStack.m_41700_("title", (Tag)StringTag.m_129297_((String)text.f_215168_()));
            }
            this.m_143634_(list, p_143659_ -> Component.Serializer.m_130703_((Component)Component.m_237113_((String)p_143659_)), itemStack);
            this.f_9743_.m_150109_().m_6836_(slot, CraftEventFactory.handleEditBookEvent(this.f_9743_, slot, old, itemStack));
        }
    }

    @Overwrite
    public void m_7185_(ServerboundMovePlayerPacket packetplayinflying) {
        PacketUtils.m_131359_((Packet)packetplayinflying, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (ServerPlayNetHandlerMixin.m_143663_(packetplayinflying.m_134129_(0.0), packetplayinflying.m_134140_(0.0), packetplayinflying.m_134146_(0.0), packetplayinflying.m_134131_(0.0f), packetplayinflying.m_134142_(0.0f))) {
            this.m_9942_((Component)Component.m_237115_((String)"multiplayer.disconnect.invalid_player_movement"));
        } else {
            ServerLevel worldserver = this.f_9743_.m_284548_();
            if (!this.f_9743_.f_8944_ && !((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
                if (this.f_9746_ == 0) {
                    this.m_9953_();
                }
                if (this.f_9766_ != null) {
                    if (this.f_9746_ - this.f_9735_ > 20) {
                        this.f_9735_ = this.f_9746_;
                        this.m_9774_(this.f_9766_.f_82479_, this.f_9766_.f_82480_, this.f_9766_.f_82481_, this.f_9743_.m_146908_(), this.f_9743_.m_146909_());
                    }
                    this.allowedPlayerTicks = 20;
                } else {
                    this.f_9735_ = this.f_9746_;
                    double d0 = ServerPlayNetHandlerMixin.m_143609_(packetplayinflying.m_134129_(this.f_9743_.m_20185_()));
                    double d1 = ServerPlayNetHandlerMixin.m_143653_(packetplayinflying.m_134140_(this.f_9743_.m_20186_()));
                    double d2 = ServerPlayNetHandlerMixin.m_143609_(packetplayinflying.m_134146_(this.f_9743_.m_20189_()));
                    float f = Mth.m_14177_((float)packetplayinflying.m_134131_(this.f_9743_.m_146908_()));
                    float f1 = Mth.m_14177_((float)packetplayinflying.m_134142_(this.f_9743_.m_146909_()));
                    if (this.f_9743_.m_20159_()) {
                        this.f_9743_.m_19890_(this.f_9743_.m_20185_(), this.f_9743_.m_20186_(), this.f_9743_.m_20189_(), f, f1);
                        this.f_9743_.m_284548_().m_7726_().m_8385_(this.f_9743_);
                        this.allowedPlayerTicks = 20;
                    } else {
                        double prevX = this.f_9743_.m_20185_();
                        double prevY = this.f_9743_.m_20186_();
                        double prevZ = this.f_9743_.m_20189_();
                        float prevYaw = this.f_9743_.m_146908_();
                        float prevPitch = this.f_9743_.m_146909_();
                        double d3 = this.f_9743_.m_20185_();
                        double d4 = this.f_9743_.m_20186_();
                        double d5 = this.f_9743_.m_20189_();
                        double d6 = this.f_9743_.m_20186_();
                        double d7 = d0 - this.f_9753_;
                        double d8 = d1 - this.f_9754_;
                        double d9 = d2 - this.f_9755_;
                        double d10 = this.f_9743_.m_20184_().m_82556_();
                        double d11 = d7 * d7 + d8 * d8 + d9 * d9;
                        if (this.f_9743_.m_5803_()) {
                            if (d11 > 1.0) {
                                this.m_9774_(this.f_9743_.m_20185_(), this.f_9743_.m_20186_(), this.f_9743_.m_20189_(), f, f1);
                            }
                        } else {
                            boolean flag;
                            ++this.f_9740_;
                            int i = this.f_9740_ - this.f_9741_;
                            this.allowedPlayerTicks = (int)((long)this.allowedPlayerTicks + (System.currentTimeMillis() / 50L - (long)this.lastTick));
                            this.allowedPlayerTicks = Math.max(this.allowedPlayerTicks, 1);
                            this.lastTick = (int)(System.currentTimeMillis() / 50L);
                            if (i > Math.max(this.allowedPlayerTicks, 5)) {
                                f_9744_.debug("{} is sending move packets too frequently ({} packets since last tick)", (Object)this.f_9743_.m_7755_().getString(), (Object)i);
                                i = 1;
                            }
                            this.allowedPlayerTicks = packetplayinflying.f_134125_ || d11 > 0.0 ? --this.allowedPlayerTicks : 20;
                            double speed = this.f_9743_.m_150110_().f_35935_ ? (double)(this.f_9743_.m_150110_().f_35939_ * 20.0f) : (double)(this.f_9743_.m_150110_().f_35940_ * 10.0f);
                            if (!(this.f_9743_.m_8958_() || this.f_9743_.m_284548_().m_46469_().m_46207_(GameRules.f_46148_) && this.f_9743_.m_21255_())) {
                                float f2;
                                float f3 = f2 = this.f_9743_.m_21255_() ? 300.0f : 100.0f;
                                if (d11 - d10 > Math.max((double)f2, Math.pow(SpigotConfig.movedTooQuicklyMultiplier * (double)i * speed, 2.0)) && !this.m_9956_()) {
                                    f_9744_.warn("{} moved too quickly! {},{},{}", new Object[]{this.f_9743_.m_7755_().getString(), d7, d8, d9});
                                    this.m_9774_(this.f_9743_.m_20185_(), this.f_9743_.m_20186_(), this.f_9743_.m_20189_(), this.f_9743_.m_146908_(), this.f_9743_.m_146909_());
                                    return;
                                }
                            }
                            AABB axisalignedbb = this.f_9743_.m_20191_();
                            d7 = d0 - this.f_9756_;
                            d8 = d1 - this.f_9757_;
                            d9 = d2 - this.f_9758_;
                            boolean bl = flag = d8 > 0.0;
                            if (this.f_9743_.m_20096_() && !packetplayinflying.m_134139_() && flag) {
                                this.f_9743_.m_6135_();
                            }
                            this.f_9743_.m_6478_(MoverType.PLAYER, new Vec3(d7, d8, d9));
                            this.f_9743_.f_19861_ = packetplayinflying.m_134139_();
                            double d12 = d8;
                            d7 = d0 - this.f_9743_.m_20185_();
                            d8 = d1 - this.f_9743_.m_20186_();
                            if (d8 > -0.5 || d8 < 0.5) {
                                d8 = 0.0;
                            }
                            d9 = d2 - this.f_9743_.m_20189_();
                            d11 = d7 * d7 + d8 * d8 + d9 * d9;
                            boolean flag1 = false;
                            if (!this.f_9743_.m_8958_() && d11 > SpigotConfig.movedWronglyThreshold && !this.f_9743_.m_5803_() && !this.f_9743_.f_8941_.m_9295_() && this.f_9743_.f_8941_.m_9290_() != GameType.SPECTATOR) {
                                flag1 = true;
                                f_9744_.warn("{} moved wrongly!", (Object)this.f_9743_.m_7755_().getString());
                            }
                            if (!this.f_9743_.f_19794_ && !this.f_9743_.m_5803_() && (flag1 && worldserver.m_45756_((net.minecraft.world.entity.Entity)this.f_9743_, axisalignedbb) || this.m_288208_((LevelReader)worldserver, axisalignedbb, d0, d1, d2))) {
                                this.internalTeleport(d3, d4, d5, f, f1, Collections.emptySet());
                                this.f_9743_.m_289599_(this.f_9743_.m_20185_() - d3, this.f_9743_.m_20186_() - d4, this.f_9743_.m_20189_() - d5, packetplayinflying.m_134139_());
                            } else {
                                this.f_9743_.m_19890_(prevX, prevY, prevZ, prevYaw, prevPitch);
                                CraftPlayer player = this.getCraftPlayer();
                                Location from = new Location(player.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch);
                                Location to = player.getLocation().clone();
                                if (packetplayinflying.f_134124_) {
                                    to.setX(packetplayinflying.f_134118_);
                                    to.setY(packetplayinflying.f_134119_);
                                    to.setZ(packetplayinflying.f_134120_);
                                }
                                if (packetplayinflying.f_134125_) {
                                    to.setYaw(packetplayinflying.f_134121_);
                                    to.setPitch(packetplayinflying.f_134122_);
                                }
                                double delta = Math.pow(this.lastPosX - to.getX(), 2.0) + Math.pow(this.lastPosY - to.getY(), 2.0) + Math.pow(this.lastPosZ - to.getZ(), 2.0);
                                float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch());
                                if ((delta > 0.00390625 || deltaAngle > 10.0f) && !((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
                                    this.lastPosX = to.getX();
                                    this.lastPosY = to.getY();
                                    this.lastPosZ = to.getZ();
                                    this.lastYaw = to.getYaw();
                                    this.lastPitch = to.getPitch();
                                    if (from.getX() != Double.MAX_VALUE) {
                                        Location oldTo = to.clone();
                                        PlayerMoveEvent event = new PlayerMoveEvent(player, from, to);
                                        this.cserver.getPluginManager().callEvent(event);
                                        if (event.isCancelled()) {
                                            this.teleport(from);
                                            return;
                                        }
                                        if (!oldTo.equals(event.getTo()) && !event.isCancelled()) {
                                            this.getCraftPlayer().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                                            return;
                                        }
                                        if (!from.equals(this.getCraftPlayer().getLocation()) && this.justTeleported) {
                                            this.justTeleported = false;
                                            return;
                                        }
                                    }
                                }
                                this.f_9743_.m_19890_(d0, d1, d2, f, f1);
                                this.f_9736_ = d12 >= -0.03125 && this.f_9743_.f_8941_.m_9290_() != GameType.SPECTATOR && !this.f_9745_.m_129915_() && !this.f_9743_.m_150110_().f_35936_ && !this.f_9743_.m_21023_(MobEffects.f_19620_) && !this.f_9743_.m_21255_() && this.m_9793_((net.minecraft.world.entity.Entity)this.f_9743_) && !this.f_9743_.m_21209_();
                                this.f_9743_.m_284548_().m_7726_().m_8385_(this.f_9743_);
                                this.f_9743_.m_289599_(this.f_9743_.m_20185_() - d3, this.f_9743_.m_20186_() - d4, this.f_9743_.m_20189_() - d5, packetplayinflying.m_134139_());
                                this.f_9743_.m_289603_(packetplayinflying.m_134139_(), new Vec3(this.f_9743_.m_20185_() - d3, this.f_9743_.m_20186_() - d4, this.f_9743_.m_20189_() - d5));
                                if (flag) {
                                    this.f_9743_.m_183634_();
                                }
                                this.f_9743_.m_36378_(this.f_9743_.m_20185_() - d3, this.f_9743_.m_20186_() - d4, this.f_9743_.m_20189_() - d5);
                                this.f_9756_ = this.f_9743_.m_20185_();
                                this.f_9757_ = this.f_9743_.m_20186_();
                                this.f_9758_ = this.f_9743_.m_20189_();
                            }
                        }
                    }
                }
            }
        }
    }

    @Overwrite
    public void m_7502_(ServerboundPlayerActionPacket packetplayinblockdig) {
        PacketUtils.m_131359_((Packet)packetplayinblockdig, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        BlockPos blockposition = packetplayinblockdig.m_134281_();
        this.f_9743_.m_9243_();
        ServerboundPlayerActionPacket.Action packetplayinblockdig_enumplayerdigtype = packetplayinblockdig.m_134285_();
        switch (packetplayinblockdig_enumplayerdigtype) {
            case SWAP_ITEM_WITH_OFFHAND: {
                if (!this.f_9743_.m_5833_()) {
                    ItemStack offhandStack = this.f_9743_.m_21120_(InteractionHand.OFF_HAND);
                    LivingSwapItemsEvent.Hands event = ForgeHooks.onLivingSwapHandItems((LivingEntity)this.f_9743_);
                    if (event.isCanceled()) {
                        return;
                    }
                    ItemStack itemstack = event.getItemSwappedToMainHand();
                    ItemStack originMainHand = event.getItemSwappedToOffHand();
                    CraftItemStack mainHand = CraftItemStack.asCraftMirror(itemstack);
                    CraftItemStack offHand = CraftItemStack.asCraftMirror(originMainHand);
                    PlayerSwapHandItemsEvent swapItemsEvent = new PlayerSwapHandItemsEvent(this.getCraftPlayer(), mainHand.clone(), offHand.clone());
                    this.cserver.getPluginManager().callEvent(swapItemsEvent);
                    if (swapItemsEvent.isCancelled()) {
                        return;
                    }
                    if (swapItemsEvent.getOffHandItem().equals(offHand)) {
                        this.f_9743_.m_21008_(InteractionHand.OFF_HAND, originMainHand);
                    } else {
                        this.f_9743_.m_21008_(InteractionHand.OFF_HAND, CraftItemStack.asNMSCopy(swapItemsEvent.getOffHandItem()));
                    }
                    if (swapItemsEvent.getMainHandItem().equals(mainHand)) {
                        this.f_9743_.m_21008_(InteractionHand.MAIN_HAND, itemstack);
                    } else {
                        this.f_9743_.m_21008_(InteractionHand.MAIN_HAND, CraftItemStack.asNMSCopy(swapItemsEvent.getMainHandItem()));
                    }
                    this.f_9743_.m_5810_();
                }
                return;
            }
            case DROP_ITEM: {
                if (!this.f_9743_.m_5833_()) {
                    if (this.lastDropTick != ArclightConstants.currentTick) {
                        this.dropCount = 0;
                        this.lastDropTick = ArclightConstants.currentTick;
                    } else {
                        ++this.dropCount;
                        if (this.dropCount >= 20) {
                            f_9744_.warn(this.f_9743_.m_6302_() + " dropped their items too quickly!");
                            this.disconnect("You dropped your items too quickly (Hacking?)");
                            return;
                        }
                    }
                    this.f_9743_.m_182294_(false);
                }
                return;
            }
            case DROP_ALL_ITEMS: {
                if (!this.f_9743_.m_5833_()) {
                    this.f_9743_.m_182294_(true);
                }
                return;
            }
            case RELEASE_USE_ITEM: {
                this.f_9743_.m_21253_();
                return;
            }
            case START_DESTROY_BLOCK: 
            case ABORT_DESTROY_BLOCK: 
            case STOP_DESTROY_BLOCK: {
                this.f_9743_.f_8941_.m_214168_(blockposition, packetplayinblockdig_enumplayerdigtype, packetplayinblockdig.m_134284_(), this.f_9743_.m_9236_().m_151558_(), packetplayinblockdig.m_237987_());
                this.f_9743_.f_8906_.m_215201_(packetplayinblockdig.m_237987_());
                return;
            }
        }
        throw new IllegalArgumentException("Invalid player action");
    }

    @Inject(method={"handleUseItemOn"}, cancellable=true, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/server/level/ServerPlayer;serverLevel()Lnet/minecraft/server/level/ServerLevel;")})
    private void arclight$frozenUseItem(ServerboundUseItemOnPacket packetIn, CallbackInfo ci) {
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            ci.cancel();
        }
        if (!this.checkLimit(((TimestampedPacket)packetIn).bridge$timestamp())) {
            ci.cancel();
        }
    }

    private boolean checkLimit(long timestamp) {
        if (this.lastLimitedPacket != -1L && timestamp - this.lastLimitedPacket < 30L && this.limitedPackets++ >= 4) {
            return false;
        }
        if (this.lastLimitedPacket == -1L || timestamp - this.lastLimitedPacket >= 30L) {
            this.lastLimitedPacket = timestamp;
            this.limitedPackets = 0;
            return true;
        }
        return true;
    }

    @Overwrite
    public void m_5760_(ServerboundUseItemPacket packet) {
        PacketUtils.m_131359_((Packet)packet, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        if (!this.checkLimit(((TimestampedPacket)packet).bridge$timestamp())) {
            return;
        }
        this.m_215201_(packet.m_238013_());
        ServerLevel worldserver = this.f_9743_.m_284548_();
        InteractionHand enumhand = packet.m_134717_();
        ItemStack itemstack = this.f_9743_.m_21120_(enumhand);
        this.f_9743_.m_9243_();
        if (!itemstack.m_41619_() && itemstack.m_246617_(worldserver.m_246046_())) {
            boolean cancelled;
            float f1 = this.f_9743_.m_146909_();
            float f2 = this.f_9743_.m_146908_();
            double d0 = this.f_9743_.m_20185_();
            double d2 = this.f_9743_.m_20186_() + (double)this.f_9743_.m_20192_();
            double d3 = this.f_9743_.m_20189_();
            Vec3 vec3d = new Vec3(d0, d2, d3);
            float f3 = Mth.m_14089_((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
            float f4 = Mth.m_14031_((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
            float f5 = -Mth.m_14089_((float)(-f1 * ((float)Math.PI / 180)));
            float f6 = Mth.m_14031_((float)(-f1 * ((float)Math.PI / 180)));
            float f7 = f4 * f5;
            float f8 = f3 * f5;
            double d4 = this.f_9743_.f_8941_.m_9290_() == GameType.CREATIVE ? 5.0 : 4.5;
            Vec3 vec3d2 = vec3d.m_82520_((double)f7 * d4, (double)f6 * d4, (double)f8 * d4);
            BlockHitResult movingobjectposition = this.f_9743_.m_9236_().m_45547_(new ClipContext(vec3d, vec3d2, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, (net.minecraft.world.entity.Entity)this.f_9743_));
            if (movingobjectposition == null || movingobjectposition.m_6662_() != HitResult.Type.BLOCK) {
                PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent((Player)this.f_9743_, Action.RIGHT_CLICK_AIR, itemstack, enumhand);
                cancelled = event.useItemInHand() == Event.Result.DENY;
            } else if (((PlayerInteractionManagerBridge)this.f_9743_.f_8941_).bridge$isFiredInteract()) {
                ((PlayerInteractionManagerBridge)this.f_9743_.f_8941_).bridge$setFiredInteract(false);
                cancelled = ((PlayerInteractionManagerBridge)this.f_9743_.f_8941_).bridge$getInteractResult();
            } else {
                BlockHitResult movingobjectpositionblock = movingobjectposition;
                PlayerInteractEvent event2 = CraftEventFactory.callPlayerInteractEvent((Player)this.f_9743_, Action.RIGHT_CLICK_BLOCK, movingobjectpositionblock.m_82425_(), movingobjectpositionblock.m_82434_(), itemstack, true, enumhand, movingobjectpositionblock.m_82450_());
                boolean bl = cancelled = event2.useItemInHand() == Event.Result.DENY;
            }
            if (cancelled) {
                ((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity().updateInventory();
                return;
            }
            itemstack = this.f_9743_.m_21120_(enumhand);
            if (itemstack.m_41619_()) {
                return;
            }
            InteractionResult actionresulttype = this.f_9743_.f_8941_.m_6261_(this.f_9743_, (net.minecraft.world.level.Level)worldserver, itemstack, enumhand);
            if (actionresulttype.m_19080_()) {
                this.f_9743_.m_21011_(enumhand, true);
            }
        }
    }

    @Inject(method={"handleTeleportToEntityPacket"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;teleportTo(Lnet/minecraft/server/level/ServerLevel;DDDFF)V")})
    private void arclight$spectateTeleport(ServerboundTeleportToEntityPacket packetIn, CallbackInfo ci) {
        ((ServerPlayerEntityBridge)this.f_9743_).bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause.SPECTATE);
    }

    @Inject(method={"handleResourcePackResponse"}, at={@At(value="RETURN")})
    private void arclight$handleResourcePackStatus(ServerboundResourcePackPacket packetIn, CallbackInfo ci) {
        this.cserver.getPluginManager().callEvent(new PlayerResourcePackStatusEvent((org.bukkit.entity.Player)this.getCraftPlayer(), PlayerResourcePackStatusEvent.Status.values()[packetIn.f_134406_.ordinal()]));
    }

    @Inject(method={"onDisconnect"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$returnIfProcessed(Component reason, CallbackInfo ci) {
        if (this.processedDisconnect) {
            ci.cancel();
        } else {
            this.processedDisconnect = true;
        }
    }

    @Redirect(method={"onDisconnect"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    public void arclight$captureQuit(PlayerList instance, Component p_240618_, boolean p_240644_) {
    }

    @Inject(method={"onDisconnect"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/server/players/PlayerList;remove(Lnet/minecraft/server/level/ServerPlayer;)V")})
    public void arclight$processQuit(Component reason, CallbackInfo ci) {
        String quitMessage = ArclightCaptures.getQuitMessage();
        if (quitMessage != null && quitMessage.length() > 0) {
            ((PlayerListBridge)this.f_9745_.m_6846_()).bridge$sendMessage(CraftChatMessage.fromString(quitMessage));
        }
    }

    @Inject(method={"send(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketSendListener;)V"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$updateCompassTarget(Packet<?> packetIn, PacketSendListener futureListeners, CallbackInfo ci) {
        if (packetIn == null || this.processedDisconnect) {
            ci.cancel();
            return;
        }
        if (packetIn instanceof ClientboundSetDefaultSpawnPositionPacket) {
            ClientboundSetDefaultSpawnPositionPacket packet6 = (ClientboundSetDefaultSpawnPositionPacket)packetIn;
            ((ServerPlayerEntityBridge)this.f_9743_).bridge$setCompassTarget(new Location(this.getCraftPlayer().getWorld(), packet6.f_133111_.m_123341_(), packet6.f_133111_.m_123342_(), packet6.f_133111_.m_123343_()));
        }
    }

    @Overwrite
    public void m_7798_(ServerboundSetCarriedItemPacket packet) {
        PacketUtils.m_131359_((Packet)packet, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        if (packet.m_134498_() >= 0 && packet.m_134498_() < net.minecraft.world.entity.player.Inventory.m_36059_()) {
            PlayerItemHeldEvent event = new PlayerItemHeldEvent(this.getCraftPlayer(), this.f_9743_.m_150109_().f_35977_, packet.m_134498_());
            this.cserver.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                this.m_9829_((Packet<?>)new ClientboundSetCarriedItemPacket(this.f_9743_.m_150109_().f_35977_));
                this.f_9743_.m_9243_();
                return;
            }
            if (this.f_9743_.m_150109_().f_35977_ != packet.m_134498_() && this.f_9743_.m_7655_() == InteractionHand.MAIN_HAND) {
                this.f_9743_.m_5810_();
            }
            this.f_9743_.m_150109_().f_35977_ = packet.m_134498_();
            this.f_9743_.m_9243_();
        } else {
            f_9744_.warn("{} tried to set an invalid carried item", (Object)this.f_9743_.m_7755_().getString());
            this.disconnect("Invalid hotbar selection (Hacking?)");
        }
    }

    @Overwrite
    public void m_7388_(ServerboundChatPacket packet) {
        if (this.f_9745_.m_129918_()) {
            return;
        }
        if (ServerPlayNetHandlerMixin.m_215214_(packet.f_133827_())) {
            this.m_9942_((Component)Component.m_237115_((String)"multiplayer.disconnect.illegal_characters"));
        } else {
            Optional<LastSeenMessages> optional = this.m_247189_(packet.f_133827_(), packet.f_237950_(), packet.f_241662_());
            if (optional.isPresent()) {
                PlayerChatMessage playerchatmessage;
                try {
                    playerchatmessage = this.m_247340_(packet, optional.get());
                }
                catch (SignedMessageChain.DecodeException e) {
                    this.m_246889_(e);
                    return;
                }
                CompletableFuture<FilteredText> completablefuture = this.m_243132_(playerchatmessage.m_245728_());
                CompletableFuture completablefuture1 = ForgeHooks.getServerChatSubmittedDecorator().m_236961_(this.f_9743_, playerchatmessage.m_245692_());
                this.f_241681_.m_241849_(executor -> CompletableFuture.allOf(completablefuture, completablefuture1).thenAcceptAsync(ovoid -> {
                    PlayerChatMessage playerchatmessage1 = playerchatmessage.m_241956_((Component)completablefuture1.join()).m_243072_(((FilteredText)completablefuture.join()).f_243010_());
                    this.m_243086_(playerchatmessage1);
                }, (Executor)ArclightServer.getChatExecutor()));
            }
        }
    }

    @Inject(method={"*"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/network/ServerGamePacketListenerImpl;performChatCommand(Lnet/minecraft/network/protocol/game/ServerboundChatCommandPacket;Lnet/minecraft/network/chat/LastSeenMessages;)V")})
    private void arclight$rejectIfDisconnect(CallbackInfo ci) {
        if (this.f_9743_.m_9232_()) {
            ci.cancel();
        }
    }

    @Overwrite
    private void m_246958_(ServerboundChatCommandPacket packet, LastSeenMessages lastseenmessages) {
        Map map;
        Object command = "/" + packet.f_237922_();
        f_9744_.info(this.f_9743_.m_6302_() + " issued server command: " + (String)command);
        PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(this.getCraftPlayer(), (String)command, new LazyPlayerSet(this.f_9745_));
        this.cserver.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        command = event.getMessage().substring(1);
        ParseResults parseresults = this.m_242658_((String)command);
        try {
            map = packet.f_237922_().equals(command) ? this.m_246206_(packet, SignableCommand.m_246497_(parseresults), lastseenmessages) : Collections.emptyMap();
        }
        catch (SignedMessageChain.DecodeException e) {
            this.m_246889_(e);
            return;
        }
        CommandSigningContext.SignedArguments arguments = new CommandSigningContext.SignedArguments(map);
        parseresults = Commands.m_242611_(parseresults, stack -> stack.m_230893_((CommandSigningContext)arguments));
        this.f_9745_.m_129892_().m_242674_(parseresults, (String)command);
    }

    @Inject(method={"tryHandleChat"}, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/server/network/ServerGamePacketListenerImpl;unpackAndApplyLastSeen(Lnet/minecraft/network/chat/LastSeenMessages$Update;)Ljava/util/Optional;")})
    private void arclight$deadMenTellNoTales(String p_242372_, Instant p_242311_, LastSeenMessages.Update p_242217_, CallbackInfoReturnable<Optional<LastSeenMessages>> cir) {
        if (this.f_9743_.m_213877_()) {
            this.m_9829_((Packet<?>)new ClientboundSystemChatPacket((Component)Component.m_237115_((String)"chat.disabled.options").m_130940_(ChatFormatting.RED), false));
            cir.setReturnValue(Optional.empty());
        }
    }

    public void chat(String s, final PlayerChatMessage original, boolean async) {
        if (s.isEmpty() || this.f_9743_.m_9241_() == ChatVisiblity.HIDDEN) {
            return;
        }
        ServerGamePacketListenerImpl handler = (ServerGamePacketListenerImpl)this;
        OutgoingChatMessage outgoing = OutgoingChatMessage.m_247282_((PlayerChatMessage)original);
        if (!async && s.startsWith("/")) {
            this.handleCommand(s);
        } else if (this.f_9743_.m_9241_() != ChatVisiblity.SYSTEM) {
            final CraftPlayer thisPlayer = this.getCraftPlayer();
            AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(async, thisPlayer, s, new LazyPlayerSet(this.f_9745_));
            final String originalFormat = event.getFormat();
            final String originalMessage = event.getMessage();
            this.cserver.getPluginManager().callEvent(event);
            if (PlayerChatEvent.getHandlerList().getRegisteredListeners().length != 0) {
                final PlayerChatEvent queueEvent = new PlayerChatEvent(thisPlayer, event.getMessage(), event.getFormat(), event.getRecipients());
                queueEvent.setCancelled(event.isCancelled());
                class SyncChat
                extends Waitable<Object> {
                    SyncChat() {
                    }

                    @Override
                    protected Object evaluate() {
                        Bukkit.getPluginManager().callEvent(queueEvent);
                        if (queueEvent.isCancelled()) {
                            return null;
                        }
                        String message = String.format(queueEvent.getFormat(), queueEvent.getPlayer().getDisplayName(), queueEvent.getMessage());
                        if (((LazyPlayerSet)queueEvent.getRecipients()).isLazy()) {
                            if (!SpigotConfig.bungee && originalFormat.equals(queueEvent.getFormat()) && originalMessage.equals(queueEvent.getMessage()) && queueEvent.getPlayer().getName().equalsIgnoreCase(queueEvent.getPlayer().getDisplayName())) {
                                ServerPlayNetHandlerMixin.this.f_9745_.m_6846_().m_243049_(original, ServerPlayNetHandlerMixin.this.f_9743_, ChatType.m_240980_((ResourceKey)ChatType.f_130598_, (net.minecraft.world.entity.Entity)ServerPlayNetHandlerMixin.this.f_9743_));
                                return null;
                            }
                            for (ServerPlayer recipient : ServerPlayNetHandlerMixin.this.f_9745_.m_6846_().f_11196_) {
                                ((ServerPlayerEntityBridge)recipient).bridge$getBukkitEntity().sendMessage(ServerPlayNetHandlerMixin.this.f_9743_.m_20148_(), message);
                            }
                        } else {
                            for (org.bukkit.entity.Player player2 : queueEvent.getRecipients()) {
                                player2.sendMessage(thisPlayer.getUniqueId(), message);
                            }
                        }
                        Bukkit.getConsoleSender().sendMessage(message);
                        return null;
                    }
                }
                SyncChat waitable = new SyncChat();
                if (async) {
                    ((MinecraftServerBridge)this.f_9745_).bridge$queuedProcess(waitable);
                } else {
                    waitable.run();
                }
                try {
                    waitable.get();
                    return;
                }
                catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    return;
                }
                catch (ExecutionException e) {
                    throw new RuntimeException("Exception processing chat event", e.getCause());
                }
            }
            if (event.isCancelled()) {
                return;
            }
            s = String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage());
            if (((LazyPlayerSet)event.getRecipients()).isLazy()) {
                if (!SpigotConfig.bungee && originalFormat.equals(event.getFormat()) && originalMessage.equals(event.getMessage()) && event.getPlayer().getName().equalsIgnoreCase(event.getPlayer().getDisplayName())) {
                    this.f_9745_.m_6846_().m_243049_(original, this.f_9743_, ChatType.m_240980_((ResourceKey)ChatType.f_130598_, (net.minecraft.world.entity.Entity)this.f_9743_));
                    return;
                }
                for (ServerPlayer recipient : this.f_9745_.m_6846_().f_11196_) {
                    ((ServerPlayerEntityBridge)recipient).bridge$getBukkitEntity().sendMessage(this.f_9743_.m_20148_(), s);
                }
            } else {
                for (org.bukkit.entity.Player recipient : event.getRecipients()) {
                    recipient.sendMessage(this.f_9743_.m_20148_(), s);
                }
            }
            Bukkit.getConsoleSender().sendMessage(s);
        }
    }

    private void handleCommand(String s) {
        if (SpigotConfig.logCommands) {
            f_9744_.info(this.f_9743_.m_6302_() + " issued server command: " + s);
        }
        CraftPlayer player = this.getCraftPlayer();
        PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, s, new LazyPlayerSet(this.f_9745_));
        this.cserver.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        try {
            this.cserver.dispatchCommand(event.getPlayer(), event.getMessage().substring(1));
        }
        catch (CommandRuntimeException ex) {
            player.sendMessage(String.valueOf((Object)ChatColor.RED) + "An internal error occurred while attempting to perform this command");
            java.util.logging.Logger.getLogger(ServerGamePacketListenerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Overwrite
    private void m_243086_(PlayerChatMessage playerchatmessage) {
        String s = playerchatmessage.m_245728_();
        if (s.isEmpty()) {
            f_9744_.warn(this.f_9743_.m_6302_() + " tried to send an empty message");
        } else if (this.getCraftPlayer().isConversing()) {
            String conversationInput = s;
            ((MinecraftServerBridge)this.f_9745_).bridge$queuedProcess(() -> this.getCraftPlayer().acceptConversationInput(conversationInput));
        } else if (this.f_9743_.m_9241_() == ChatVisiblity.SYSTEM) {
            this.m_9829_((Packet<?>)new ClientboundSystemChatPacket((Component)Component.m_237115_((String)"chat.cannotSend").m_130940_(ChatFormatting.RED), false));
        } else {
            this.chat(s, playerchatmessage, true);
        }
        this.m_215251_();
    }

    @Overwrite
    public void m_7953_(ServerboundSwingPacket packet) {
        PacketUtils.m_131359_((Packet)packet, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        this.f_9743_.m_9243_();
        float f1 = this.f_9743_.m_146909_();
        float f2 = this.f_9743_.m_146908_();
        double d0 = this.f_9743_.m_20185_();
        double d2 = this.f_9743_.m_20186_() + (double)this.f_9743_.m_20192_();
        double d3 = this.f_9743_.m_20189_();
        double d4 = this.f_9743_.getBlockReach();
        Location origin = new Location(((WorldBridge)this.f_9743_.m_9236_()).bridge$getWorld(), d0, d2, d3, f1, f2);
        RayTraceResult result = ((WorldBridge)this.f_9743_.m_9236_()).bridge$getWorld().rayTrace(origin, origin.getDirection(), d4, FluidCollisionMode.NEVER, false, 0.1, entity -> {
            net.minecraft.world.entity.Entity handle = ((CraftEntity)entity).getHandle();
            return handle != this.f_9743_ && ((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity().canSee((Entity)entity) && !handle.m_5833_() && handle.m_6087_() && !handle.m_20365_((net.minecraft.world.entity.Entity)this.f_9743_);
        });
        if (result == null) {
            CraftEventFactory.callPlayerInteractEvent((Player)this.f_9743_, Action.LEFT_CLICK_AIR, this.f_9743_.m_150109_().m_36056_(), InteractionHand.MAIN_HAND);
        }
        PlayerAnimationEvent event = new PlayerAnimationEvent((org.bukkit.entity.Player)this.getCraftPlayer(), packet.m_134674_() == InteractionHand.MAIN_HAND ? PlayerAnimationType.ARM_SWING : PlayerAnimationType.OFF_ARM_SWING);
        this.cserver.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        this.f_9743_.m_6674_(packet.m_134674_());
    }

    @Inject(method={"handlePlayerCommand"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;resetLastActionTime()V")})
    private void arclight$toggleAction(ServerboundPlayerCommandPacket packetIn, CallbackInfo ci) {
        if (this.f_9743_.m_213877_()) {
            ci.cancel();
            return;
        }
        if (packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.PRESS_SHIFT_KEY || packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.RELEASE_SHIFT_KEY) {
            PlayerToggleSneakEvent event = new PlayerToggleSneakEvent(this.getCraftPlayer(), packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.PRESS_SHIFT_KEY);
            this.cserver.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        } else if (packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.START_SPRINTING || packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.STOP_SPRINTING) {
            PlayerToggleSprintEvent e2 = new PlayerToggleSprintEvent(this.getCraftPlayer(), packetIn.m_134320_() == ServerboundPlayerCommandPacket.Action.START_SPRINTING);
            this.cserver.getPluginManager().callEvent(e2);
            if (e2.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Overwrite
    public void m_6946_(ServerboundInteractPacket packetIn) {
        PacketUtils.m_131359_((Packet)packetIn, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        final ServerLevel world = this.f_9743_.m_284548_();
        final net.minecraft.world.entity.Entity entity = packetIn.m_179603_(world);
        if (entity == this.f_9743_ && !this.f_9743_.m_5833_()) {
            this.disconnect("Cannot interact with self!");
            return;
        }
        this.f_9743_.m_9243_();
        this.f_9743_.m_20260_(packetIn.m_134061_());
        if (entity != null) {
            if (!world.m_6857_().m_61937_(entity.m_20183_())) {
                return;
            }
            class Handler
            implements ServerboundInteractPacket.Handler {
                Handler() {
                }

                private void performInteraction(InteractionHand hand, ServerGamePacketListenerImpl.EntityInteraction interaction, PlayerInteractEntityEvent event) {
                    ItemStack stack = ServerPlayNetHandlerMixin.this.f_9743_.m_21120_(hand);
                    if (!stack.m_246617_(world.m_246046_())) {
                        return;
                    }
                    ItemStack itemstack = stack.m_41777_();
                    ItemStack itemInHand = ServerPlayNetHandlerMixin.this.f_9743_.m_21120_(hand);
                    boolean triggerLeashUpdate = itemInHand != null && itemInHand.m_41720_() == Items.f_42655_ && entity instanceof Mob;
                    Item origItem = ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_() == null ? null : ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_().m_41720_();
                    ServerPlayNetHandlerMixin.this.cserver.getPluginManager().callEvent(event);
                    if (entity instanceof Bucketable && entity instanceof LivingEntity && origItem != null && origItem.m_5456_() == Items.f_42447_ && (event.isCancelled() || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_() == null || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_().m_41720_() != origItem)) {
                        ServerPlayNetHandlerMixin.this.m_9829_((Packet<?>)new ClientboundAddEntityPacket((net.minecraft.world.entity.Entity)((LivingEntity)entity)));
                        ServerPlayNetHandlerMixin.this.f_9743_.f_36096_.m_150429_();
                    }
                    if (triggerLeashUpdate && (event.isCancelled() || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_() == null || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_().m_41720_() != origItem)) {
                        ServerPlayNetHandlerMixin.this.m_9829_((Packet<?>)new ClientboundSetEntityLinkPacket(entity, ((Mob)entity).m_21524_()));
                    }
                    if (event.isCancelled() || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_() == null || ServerPlayNetHandlerMixin.this.f_9743_.m_150109_().m_36056_().m_41720_() != origItem) {
                        ((SynchedEntityDataBridge)entity.m_20088_()).bridge$refresh(ServerPlayNetHandlerMixin.this.f_9743_);
                        if (entity instanceof Allay) {
                            ServerPlayNetHandlerMixin.this.m_9829_((Packet<?>)new ClientboundSetEquipmentPacket(entity.m_19879_(), Arrays.stream(net.minecraft.world.entity.EquipmentSlot.values()).map(slot -> Pair.of((Object)slot, (Object)((LivingEntity)entity).m_6844_(slot).m_41777_())).collect(Collectors.toList())));
                            ServerPlayNetHandlerMixin.this.f_9743_.f_36096_.m_150429_();
                        }
                    }
                    if (event.isCancelled()) {
                        return;
                    }
                    InteractionResult enuminteractionresult = interaction.m_143694_(ServerPlayNetHandlerMixin.this.f_9743_, entity, hand);
                    if (!itemInHand.m_41619_() && itemInHand.m_41613_() <= -1) {
                        ServerPlayNetHandlerMixin.this.f_9743_.f_36096_.m_150429_();
                    }
                    if (enuminteractionresult.m_19077_()) {
                        CriteriaTriggers.f_10565_.m_61494_(ServerPlayNetHandlerMixin.this.f_9743_, itemstack, entity);
                        if (enuminteractionresult.m_19080_()) {
                            ServerPlayNetHandlerMixin.this.f_9743_.m_21011_(hand, true);
                        }
                    }
                }

                public void m_142299_(InteractionHand hand) {
                    this.performInteraction(hand, Player::m_36157_, new PlayerInteractEntityEvent(ServerPlayNetHandlerMixin.this.getCraftPlayer(), ((EntityBridge)entity).bridge$getBukkitEntity(), hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND));
                }

                public void m_142143_(InteractionHand hand, Vec3 vec) {
                    this.performInteraction(hand, (player, e, h) -> {
                        InteractionResult onInteractEntityAtResult = ForgeHooks.onInteractEntityAt((Player)player, (net.minecraft.world.entity.Entity)entity, (Vec3)vec, (InteractionHand)hand);
                        if (onInteractEntityAtResult != null) {
                            return onInteractEntityAtResult;
                        }
                        return e.m_7111_((Player)player, vec, h);
                    }, new PlayerInteractAtEntityEvent(ServerPlayNetHandlerMixin.this.getCraftPlayer(), ((EntityBridge)entity).bridge$getBukkitEntity(), new Vector(vec.f_82479_, vec.f_82480_, vec.f_82481_), hand == InteractionHand.OFF_HAND ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND));
                }

                public void m_141994_() {
                    if (!(entity instanceof ItemEntity || entity instanceof ExperienceOrb || entity instanceof AbstractArrow || entity == ServerPlayNetHandlerMixin.this.f_9743_ && !ServerPlayNetHandlerMixin.this.f_9743_.m_5833_())) {
                        ItemStack itemInHand = ServerPlayNetHandlerMixin.this.f_9743_.m_21205_();
                        if (!itemInHand.m_246617_(world.m_246046_())) {
                            return;
                        }
                        if (ServerPlayNetHandlerMixin.this.f_9743_.canReach(entity, 3.0)) {
                            ServerPlayNetHandlerMixin.this.f_9743_.m_5706_(entity);
                        }
                        if (!itemInHand.m_41619_() && itemInHand.m_41613_() <= -1) {
                            ServerPlayNetHandlerMixin.this.f_9743_.f_36096_.m_150429_();
                        }
                    } else {
                        ServerPlayNetHandlerMixin.this.m_9942_((Component)Component.m_237115_((String)"multiplayer.disconnect.invalid_entity_attacked"));
                        f_9744_.warn("Player {} tried to attack an invalid entity", (Object)ServerPlayNetHandlerMixin.this.f_9743_.m_7755_().getString());
                    }
                }
            }
            packetIn.m_179617_((ServerboundInteractPacket.Handler)new Handler());
        }
    }

    @Inject(method={"handleContainerClose"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;doCloseContainer()V")})
    private void arclight$invClose(ServerboundContainerClosePacket packetIn, CallbackInfo ci) {
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            ci.cancel();
        }
    }

    @Overwrite
    public void m_5914_(ServerboundContainerClickPacket packet) {
        PacketUtils.m_131359_((Packet)packet, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            return;
        }
        this.f_9743_.m_9243_();
        if (this.f_9743_.f_36096_.f_38840_ == packet.m_133959_() && this.f_9743_.f_36096_.m_6875_((Player)this.f_9743_)) {
            boolean cancelled = this.f_9743_.m_5833_();
            if (!this.f_9743_.f_36096_.m_6875_((Player)this.f_9743_)) {
                f_9744_.debug("Player {} interacted with invalid menu {}", (Object)this.f_9743_, (Object)this.f_9743_.f_36096_);
            } else {
                boolean flag = packet.m_182741_() != this.f_9743_.f_36096_.m_182424_();
                this.f_9743_.f_36096_.m_150443_();
                if (packet.m_133962_() < -1 && packet.m_133962_() != -999) {
                    return;
                }
                ArclightCaptures.captureContainerOwner((Player)this.f_9743_);
                InventoryView inventory = ((ContainerBridge)this.f_9743_.f_36096_).bridge$getBukkitView();
                ArclightCaptures.resetContainerOwner();
                InventoryType.SlotType type = inventory.getSlotType(packet.m_133962_());
                org.bukkit.event.inventory.ClickType click = org.bukkit.event.inventory.ClickType.UNKNOWN;
                InventoryAction action = InventoryAction.UNKNOWN;
                ItemStack itemstack = ItemStack.f_41583_;
                switch (packet.m_133966_()) {
                    case PICKUP: {
                        if (packet.m_133963_() == 0) {
                            click = org.bukkit.event.inventory.ClickType.LEFT;
                        } else if (packet.m_133963_() == 1) {
                            click = org.bukkit.event.inventory.ClickType.RIGHT;
                        }
                        if (packet.m_133963_() != 0 && packet.m_133963_() != 1) break;
                        action = InventoryAction.NOTHING;
                        if (packet.m_133962_() == -999) {
                            if (this.f_9743_.f_36096_.m_142621_().m_41619_()) break;
                            action = packet.m_133963_() == 0 ? InventoryAction.DROP_ALL_CURSOR : InventoryAction.DROP_ONE_CURSOR;
                            break;
                        }
                        if (packet.m_133962_() < 0) {
                            action = InventoryAction.NOTHING;
                            break;
                        }
                        Slot slot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                        if (slot == null) break;
                        ItemStack clickedItem = slot.m_7993_();
                        ItemStack cursor = this.f_9743_.f_36096_.m_142621_();
                        if (clickedItem.m_41619_()) {
                            if (cursor.m_41619_()) break;
                            action = packet.m_133963_() == 0 ? InventoryAction.PLACE_ALL : InventoryAction.PLACE_ONE;
                            break;
                        }
                        if (!slot.m_8010_((Player)this.f_9743_)) break;
                        if (cursor.m_41619_()) {
                            action = packet.m_133963_() == 0 ? InventoryAction.PICKUP_ALL : InventoryAction.PICKUP_HALF;
                            break;
                        }
                        if (slot.m_5857_(cursor)) {
                            if (ItemStack.m_150942_((ItemStack)clickedItem, (ItemStack)cursor)) {
                                int toPlace = packet.m_133963_() == 0 ? cursor.m_41613_() : 1;
                                toPlace = Math.min(toPlace, clickedItem.m_41741_() - clickedItem.m_41613_());
                                if ((toPlace = Math.min(toPlace, slot.f_40218_.m_6893_() - clickedItem.m_41613_())) == 1) {
                                    action = InventoryAction.PLACE_ONE;
                                    break;
                                }
                                if (toPlace == cursor.m_41613_()) {
                                    action = InventoryAction.PLACE_ALL;
                                    break;
                                }
                                if (toPlace < 0) {
                                    action = toPlace != -1 ? InventoryAction.PICKUP_SOME : InventoryAction.PICKUP_ONE;
                                    break;
                                }
                                if (toPlace == 0) break;
                                action = InventoryAction.PLACE_SOME;
                                break;
                            }
                            if (cursor.m_41613_() > slot.m_6641_()) break;
                            action = InventoryAction.SWAP_WITH_CURSOR;
                            break;
                        }
                        if (!ItemStack.m_150942_((ItemStack)cursor, (ItemStack)clickedItem) || clickedItem.m_41613_() < 0 || clickedItem.m_41613_() + cursor.m_41613_() > cursor.m_41741_()) break;
                        action = InventoryAction.PICKUP_ALL;
                        break;
                    }
                    case QUICK_MOVE: {
                        if (packet.m_133963_() == 0) {
                            click = org.bukkit.event.inventory.ClickType.SHIFT_LEFT;
                        } else if (packet.m_133963_() == 1) {
                            click = org.bukkit.event.inventory.ClickType.SHIFT_RIGHT;
                        }
                        if (packet.m_133963_() != 0 && packet.m_133963_() != 1) break;
                        if (packet.m_133962_() < 0) {
                            action = InventoryAction.NOTHING;
                            break;
                        }
                        Slot slot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                        if (slot != null && slot.m_8010_((Player)this.f_9743_) && slot.m_6657_()) {
                            action = InventoryAction.MOVE_TO_OTHER_INVENTORY;
                            break;
                        }
                        action = InventoryAction.NOTHING;
                        break;
                    }
                    case SWAP: {
                        if ((packet.m_133963_() < 0 || packet.m_133963_() >= 9) && packet.m_133963_() != 40) break;
                        click = packet.m_133963_() == 40 ? org.bukkit.event.inventory.ClickType.SWAP_OFFHAND : org.bukkit.event.inventory.ClickType.NUMBER_KEY;
                        Slot clickedSlot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                        if (clickedSlot.m_8010_((Player)this.f_9743_)) {
                            boolean canCleanSwap;
                            ItemStack hotbar = this.f_9743_.m_150109_().m_8020_(packet.m_133963_());
                            boolean bl = canCleanSwap = hotbar.m_41619_() || clickedSlot.f_40218_ == this.f_9743_.m_150109_() && clickedSlot.m_5857_(hotbar);
                            if (clickedSlot.m_6657_()) {
                                if (canCleanSwap) {
                                    action = InventoryAction.HOTBAR_SWAP;
                                    break;
                                }
                                action = InventoryAction.HOTBAR_MOVE_AND_READD;
                                break;
                            }
                            if (!clickedSlot.m_6657_() && !hotbar.m_41619_() && clickedSlot.m_5857_(hotbar)) {
                                action = InventoryAction.HOTBAR_SWAP;
                                break;
                            }
                            action = InventoryAction.NOTHING;
                            break;
                        }
                        action = InventoryAction.NOTHING;
                        break;
                    }
                    case CLONE: {
                        Slot slot;
                        if (packet.m_133963_() == 2) {
                            click = org.bukkit.event.inventory.ClickType.MIDDLE;
                            if (packet.m_133962_() < 0) {
                                action = InventoryAction.NOTHING;
                                break;
                            }
                            slot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                            if (slot != null && slot.m_6657_() && this.f_9743_.m_150110_().f_35937_ && this.f_9743_.f_36096_.m_142621_().m_41619_()) {
                                action = InventoryAction.CLONE_STACK;
                                break;
                            }
                            action = InventoryAction.NOTHING;
                            break;
                        }
                        click = org.bukkit.event.inventory.ClickType.UNKNOWN;
                        action = InventoryAction.UNKNOWN;
                        break;
                    }
                    case THROW: {
                        Slot slot;
                        if (packet.m_133962_() >= 0) {
                            if (packet.m_133963_() == 0) {
                                click = org.bukkit.event.inventory.ClickType.DROP;
                                slot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                                if (slot != null && slot.m_6657_() && slot.m_8010_((Player)this.f_9743_) && !slot.m_7993_().m_41619_() && slot.m_7993_().m_41720_() != Item.m_41439_((Block)Blocks.f_50016_)) {
                                    action = InventoryAction.DROP_ONE_SLOT;
                                    break;
                                }
                                action = InventoryAction.NOTHING;
                                break;
                            }
                            if (packet.m_133963_() != 1) break;
                            click = org.bukkit.event.inventory.ClickType.CONTROL_DROP;
                            slot = this.f_9743_.f_36096_.m_38853_(packet.m_133962_());
                            if (slot != null && slot.m_6657_() && slot.m_8010_((Player)this.f_9743_) && !slot.m_7993_().m_41619_() && slot.m_7993_().m_41720_() != Item.m_41439_((Block)Blocks.f_50016_)) {
                                action = InventoryAction.DROP_ALL_SLOT;
                                break;
                            }
                            action = InventoryAction.NOTHING;
                            break;
                        }
                        click = org.bukkit.event.inventory.ClickType.LEFT;
                        if (packet.m_133963_() == 1) {
                            click = org.bukkit.event.inventory.ClickType.RIGHT;
                        }
                        action = InventoryAction.NOTHING;
                        break;
                    }
                    case QUICK_CRAFT: {
                        this.f_9743_.f_36096_.m_150399_(packet.m_133962_(), packet.m_133963_(), packet.m_133966_(), (Player)this.f_9743_);
                        break;
                    }
                    case PICKUP_ALL: {
                        click = org.bukkit.event.inventory.ClickType.DOUBLE_CLICK;
                        action = InventoryAction.NOTHING;
                        if (packet.m_133962_() < 0 || this.f_9743_.f_36096_.m_142621_().m_41619_()) break;
                        ItemStack cursor = this.f_9743_.f_36096_.m_142621_();
                        action = InventoryAction.NOTHING;
                        if (!inventory.getTopInventory().contains(CraftMagicNumbers.getMaterial(cursor.m_41720_())) && !inventory.getBottomInventory().contains(CraftMagicNumbers.getMaterial(cursor.m_41720_()))) break;
                        action = InventoryAction.COLLECT_TO_CURSOR;
                        break;
                    }
                }
                if (packet.m_133966_() != ClickType.QUICK_CRAFT) {
                    org.bukkit.inventory.ItemStack result;
                    Recipe recipe;
                    InventoryClickEvent event = click == org.bukkit.event.inventory.ClickType.NUMBER_KEY ? new InventoryClickEvent(inventory, type, packet.m_133962_(), click, action, packet.m_133963_()) : new InventoryClickEvent(inventory, type, packet.m_133962_(), click, action);
                    Inventory top = inventory.getTopInventory();
                    if (packet.m_133962_() == 0 && top instanceof CraftingInventory && (recipe = ((CraftingInventory)top).getRecipe()) != null) {
                        event = click == org.bukkit.event.inventory.ClickType.NUMBER_KEY ? new CraftItemEvent(recipe, inventory, type, packet.m_133962_(), click, action, packet.m_133963_()) : new CraftItemEvent(recipe, inventory, type, packet.m_133962_(), click, action);
                    }
                    if (packet.m_133962_() == 3 && top instanceof SmithingInventory && (result = ((SmithingInventory)top).getResult()) != null) {
                        event = click == org.bukkit.event.inventory.ClickType.NUMBER_KEY ? new SmithItemEvent(inventory, type, packet.m_133962_(), click, action, packet.m_133963_()) : new SmithItemEvent(inventory, type, packet.m_133962_(), click, action);
                    }
                    event.setCancelled(cancelled);
                    AbstractContainerMenu oldContainer = this.f_9743_.f_36096_;
                    this.cserver.getPluginManager().callEvent(event);
                    if (this.f_9743_.f_36096_ != oldContainer) {
                        return;
                    }
                    block9 : switch (event.getResult()) {
                        case ALLOW: 
                        case DEFAULT: {
                            this.f_9743_.f_36096_.m_150399_(packet.m_133962_(), packet.m_133963_(), packet.m_133966_(), (Player)this.f_9743_);
                            break;
                        }
                        case DENY: {
                            switch (action) {
                                case PICKUP_ALL: 
                                case MOVE_TO_OTHER_INVENTORY: 
                                case HOTBAR_MOVE_AND_READD: 
                                case HOTBAR_SWAP: 
                                case COLLECT_TO_CURSOR: 
                                case UNKNOWN: {
                                    this.f_9743_.f_36096_.m_150429_();
                                    break block9;
                                }
                                case PICKUP_SOME: 
                                case PICKUP_HALF: 
                                case PICKUP_ONE: 
                                case PLACE_ALL: 
                                case PLACE_SOME: 
                                case PLACE_ONE: 
                                case SWAP_WITH_CURSOR: {
                                    this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(-1, -1, this.f_9743_.f_36095_.m_182425_(), this.f_9743_.f_36096_.m_142621_()));
                                    this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(this.f_9743_.f_36096_.f_38840_, this.f_9743_.f_36095_.m_182425_(), packet.m_133962_(), this.f_9743_.f_36096_.m_38853_(packet.m_133962_()).m_7993_()));
                                    break block9;
                                }
                                case DROP_ALL_SLOT: 
                                case DROP_ONE_SLOT: {
                                    this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(this.f_9743_.f_36096_.f_38840_, this.f_9743_.f_36095_.m_182425_(), packet.m_133962_(), this.f_9743_.f_36096_.m_38853_(packet.m_133962_()).m_7993_()));
                                    break block9;
                                }
                                case DROP_ALL_CURSOR: 
                                case DROP_ONE_CURSOR: 
                                case CLONE_STACK: {
                                    this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(-1, -1, this.f_9743_.f_36095_.m_182425_(), this.f_9743_.f_36096_.m_142621_()));
                                    break block9;
                                }
                            }
                        }
                    }
                    if (event instanceof CraftItemEvent || event instanceof SmithItemEvent) {
                        this.f_9743_.f_36096_.m_150429_();
                    }
                }
                for (Int2ObjectMap.Entry entry : Int2ObjectMaps.fastIterable((Int2ObjectMap)packet.m_179582_())) {
                    this.f_9743_.f_36096_.m_182414_(entry.getIntKey(), (ItemStack)entry.getValue());
                }
                this.f_9743_.f_36096_.m_150422_(packet.m_179581_());
                this.f_9743_.f_36096_.m_150444_();
                if (flag) {
                    this.f_9743_.f_36096_.m_182423_();
                } else {
                    this.f_9743_.f_36096_.m_38946_();
                }
            }
        }
    }

    @Redirect(method={"handlePlaceRecipe"}, at=@At(value="INVOKE", target="Lnet/minecraft/network/protocol/game/ServerboundPlaceRecipePacket;getRecipe()Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation arclight$recipeBookClick(ServerboundPlaceRecipePacket instance) {
        ResourceLocation location = instance.m_134252_();
        Recipe recipe = this.cserver.getRecipe(CraftNamespacedKey.fromMinecraft(location));
        if (recipe == null) {
            return location;
        }
        PlayerRecipeBookClickEvent event = CraftEventFactory.callRecipeBookClickEvent(this.f_9743_, recipe, instance.m_134253_());
        Recipe recipe2 = event.getRecipe();
        if (recipe2 instanceof Keyed) {
            Keyed keyed = (Keyed)((Object)recipe2);
            return CraftNamespacedKey.toMinecraft(keyed.getKey());
        }
        return location;
    }

    @Inject(method={"handleContainerButtonClick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;resetLastActionTime()V")})
    private void arclight$noEnchant(ServerboundContainerButtonClickPacket packetIn, CallbackInfo ci) {
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            ci.cancel();
        }
    }

    @Overwrite
    public void m_5964_(ServerboundSetCreativeModeSlotPacket packetplayinsetcreativeslot) {
        PacketUtils.m_131359_((Packet)packetplayinsetcreativeslot, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (this.f_9743_.f_8941_.m_9295_()) {
            boolean flag3;
            boolean flag = packetplayinsetcreativeslot.m_134561_() < 0;
            ItemStack itemstack = packetplayinsetcreativeslot.m_134564_();
            CompoundTag nbttagcompound = BlockItem.m_186336_((ItemStack)itemstack);
            if (!itemstack.m_41619_() && nbttagcompound != null && nbttagcompound.m_128441_("x") && nbttagcompound.m_128441_("y") && nbttagcompound.m_128441_("z")) {
                BlockEntity blockentity;
                BlockPos blockpos = BlockEntity.m_187472_((CompoundTag)nbttagcompound);
                if (this.f_9743_.m_9236_().m_46749_(blockpos) && (blockentity = this.f_9743_.m_9236_().m_7702_(blockpos)) != null) {
                    blockentity.m_187476_(itemstack);
                }
            }
            boolean flag2 = packetplayinsetcreativeslot.m_134561_() >= 1 && packetplayinsetcreativeslot.m_134561_() <= 45;
            boolean bl = flag3 = itemstack.m_41619_() || itemstack.m_41773_() >= 0 && itemstack.m_41613_() <= 64 && !itemstack.m_41619_();
            if (flag || flag2 && !ItemStack.m_41728_((ItemStack)this.f_9743_.f_36095_.m_38853_(packetplayinsetcreativeslot.m_134561_()).m_7993_(), (ItemStack)packetplayinsetcreativeslot.m_134564_())) {
                InventoryView inventory = ((ContainerBridge)this.f_9743_.f_36095_).bridge$getBukkitView();
                org.bukkit.inventory.ItemStack item = CraftItemStack.asBukkitCopy(packetplayinsetcreativeslot.m_134564_());
                InventoryType.SlotType type = InventoryType.SlotType.QUICKBAR;
                if (flag) {
                    type = InventoryType.SlotType.OUTSIDE;
                } else if (packetplayinsetcreativeslot.m_134561_() < 36) {
                    type = packetplayinsetcreativeslot.m_134561_() >= 5 && packetplayinsetcreativeslot.m_134561_() < 9 ? InventoryType.SlotType.ARMOR : InventoryType.SlotType.CONTAINER;
                }
                InventoryCreativeEvent event = new InventoryCreativeEvent(inventory, type, flag ? -999 : packetplayinsetcreativeslot.m_134561_(), item);
                this.cserver.getPluginManager().callEvent(event);
                itemstack = CraftItemStack.asNMSCopy(event.getCursor());
                switch (event.getResult()) {
                    case ALLOW: {
                        flag3 = true;
                        break;
                    }
                    case DEFAULT: {
                        break;
                    }
                    case DENY: {
                        if (packetplayinsetcreativeslot.m_134561_() >= 0) {
                            this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(this.f_9743_.f_36095_.f_38840_, this.f_9743_.f_36095_.m_182425_(), packetplayinsetcreativeslot.m_134561_(), this.f_9743_.f_36095_.m_38853_(packetplayinsetcreativeslot.m_134561_()).m_7993_()));
                            this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(-1, this.f_9743_.f_36095_.m_182425_(), -1, ItemStack.f_41583_));
                        }
                        return;
                    }
                }
            }
            if (flag2 && flag3) {
                this.f_9743_.f_36095_.m_38853_(packetplayinsetcreativeslot.m_134561_()).m_269060_(itemstack);
                this.f_9743_.f_36095_.m_38946_();
            } else if (flag && flag3 && this.f_9751_ < 200) {
                this.f_9751_ += 20;
                this.f_9743_.m_36176_(itemstack, true);
            }
        }
    }

    @Inject(method={"updateSignText"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$updateSignText(ServerboundSignUpdatePacket p_9923_, List<FilteredText> p_9924_, CallbackInfo ci) {
        if (((ServerPlayerEntityBridge)this.f_9743_).bridge$isMovementBlocked()) {
            ci.cancel();
        }
    }

    @Overwrite
    public void m_6828_(ServerboundPlayerAbilitiesPacket packet) {
        PacketUtils.m_131359_((Packet)packet, (PacketListener)((ServerGamePacketListenerImpl)this), (ServerLevel)this.f_9743_.m_284548_());
        if (this.f_9743_.m_150110_().f_35936_ && this.f_9743_.m_150110_().f_35935_ != packet.m_134264_()) {
            PlayerToggleFlightEvent event = new PlayerToggleFlightEvent(this.getCraftPlayer(), packet.m_134264_());
            this.cserver.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                this.f_9743_.m_150110_().f_35935_ = packet.m_134264_();
            } else {
                this.f_9743_.m_6885_();
            }
        }
    }

    @Inject(method={"handleCustomPayload"}, at={@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/network/NetworkHooks;onCustomPayload(Lnet/minecraftforge/network/ICustomPacket;Lnet/minecraft/network/Connection;)Z")})
    private void arclight$customPayload(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        int readerIndex = packet.f_133981_.readerIndex();
        byte[] buf = new byte[packet.f_133981_.readableBytes()];
        packet.f_133981_.readBytes(buf);
        packet.f_133981_.readerIndex(readerIndex);
        ServerLifecycleHooks.getCurrentServer().m_201446_(() -> {
            if (((MinecraftServerBridge)ServerLifecycleHooks.getCurrentServer()).bridge$hasStopped() || this.bridge$processedDisconnect()) {
                return;
            }
            if (this.f_9742_.m_129536_()) {
                if (packet.f_133980_.equals((Object)CUSTOM_REGISTER)) {
                    try {
                        String channels = new String(buf, StandardCharsets.UTF_8);
                        for (String channel : channels.split("\u0000")) {
                            if (StringUtil.m_14408_((String)channel)) continue;
                            this.getCraftPlayer().addChannel(channel);
                        }
                    }
                    catch (Exception ex) {
                        f_9744_.error("Couldn't register custom payload", (Throwable)ex);
                        this.disconnect("Invalid payload REGISTER!");
                    }
                } else if (packet.f_133980_.equals((Object)CUSTOM_UNREGISTER)) {
                    try {
                        String channels = new String(buf, StandardCharsets.UTF_8);
                        for (String channel : channels.split("\u0000")) {
                            if (StringUtil.m_14408_((String)channel)) continue;
                            this.getCraftPlayer().removeChannel(channel);
                        }
                    }
                    catch (Exception ex) {
                        f_9744_.error("Couldn't unregister custom payload", (Throwable)ex);
                        this.disconnect("Invalid payload UNREGISTER!");
                    }
                } else {
                    try {
                        this.cserver.getMessenger().dispatchIncomingMessage(((ServerPlayerEntityBridge)this.f_9743_).bridge$getBukkitEntity(), packet.f_133980_.toString(), buf);
                    }
                    catch (Exception ex) {
                        f_9744_.error("Couldn't dispatch custom payload", (Throwable)ex);
                        this.disconnect("Invalid custom payload!");
                    }
                }
            }
        });
    }

    public final boolean isDisconnected() {
        return !((ServerPlayerEntityBridge)this.f_9743_).bridge$isJoining() && !this.f_9742_.m_129536_();
    }

    @Override
    public boolean bridge$isDisconnected() {
        return this.isDisconnected();
    }

    @Overwrite
    public void m_9780_(double x, double y, double z, float yaw, float pitch, Set<RelativeMovement> relativeSet) {
        Location to;
        PlayerTeleportEvent.TeleportCause cause = this.arclight$cause == null ? PlayerTeleportEvent.TeleportCause.UNKNOWN : this.arclight$cause;
        this.arclight$cause = null;
        CraftPlayer player = this.getCraftPlayer();
        Location from = player.getLocation();
        if (!from.equals(to = new Location(this.getCraftPlayer().getWorld(), x, y, z, yaw, pitch))) {
            PlayerTeleportEvent event = new PlayerTeleportEvent(player, from.clone(), to.clone(), cause);
            this.cserver.getPluginManager().callEvent(event);
            if (event.isCancelled() || !to.equals(event.getTo())) {
                relativeSet.clear();
                to = event.isCancelled() ? event.getFrom() : event.getTo();
                x = to.getX();
                y = to.getY();
                z = to.getZ();
                yaw = to.getYaw();
                pitch = to.getPitch();
            }
        }
        if (Float.isNaN(yaw)) {
            yaw = 0.0f;
        }
        if (Float.isNaN(pitch)) {
            pitch = 0.0f;
        }
        this.internalTeleport(x, y, z, yaw, pitch, relativeSet);
    }

    public void teleport(double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) {
        this.teleport(d0, d1, d2, f, f1, Collections.emptySet(), cause);
    }

    public void teleport(double d0, double d1, double d2, float f, float f1, Set<RelativeMovement> set, PlayerTeleportEvent.TeleportCause cause) {
        this.bridge$pushTeleportCause(cause);
        this.m_9780_(d0, d1, d2, f, f1, set);
    }

    private void internalTeleport(double d0, double d1, double d2, float f, float f1, Set<RelativeMovement> set) {
        if (Float.isNaN(f)) {
            f = 0.0f;
        }
        if (Float.isNaN(f1)) {
            f1 = 0.0f;
        }
        this.justTeleported = true;
        double d3 = set.contains(RelativeMovement.X) ? this.f_9743_.m_20185_() : 0.0;
        double d4 = set.contains(RelativeMovement.Y) ? this.f_9743_.m_20186_() : 0.0;
        double d5 = set.contains(RelativeMovement.Z) ? this.f_9743_.m_20189_() : 0.0;
        float f2 = set.contains(RelativeMovement.Y_ROT) ? this.f_9743_.m_146908_() : 0.0f;
        float f3 = set.contains(RelativeMovement.X_ROT) ? this.f_9743_.m_146909_() : 0.0f;
        this.f_9766_ = new Vec3(d0, d1, d2);
        if (++this.f_9767_ == Integer.MAX_VALUE) {
            this.f_9767_ = 0;
        }
        this.lastPosX = this.f_9766_.f_82479_;
        this.lastPosY = this.f_9766_.f_82480_;
        this.lastPosZ = this.f_9766_.f_82481_;
        this.lastYaw = f;
        this.lastPitch = f1;
        this.f_9735_ = this.f_9746_;
        this.f_9743_.m_19890_(d0, d1, d2, f, f1);
        this.f_9743_.f_8906_.m_9829_((Packet)new ClientboundPlayerPositionPacket(d0 - d3, d1 - d4, d2 - d5, f - f2, f1 - f3, set, this.f_9767_));
    }

    public void teleport(Location dest) {
        this.internalTeleport(dest.getX(), dest.getY(), dest.getZ(), dest.getYaw(), dest.getPitch(), Collections.emptySet());
    }

    @Override
    public void bridge$pushTeleportCause(PlayerTeleportEvent.TeleportCause cause) {
        this.arclight$cause = cause;
    }

    @Override
    public void bridge$teleport(Location dest) {
        this.teleport(dest);
    }

    public SocketAddress getRawAddress() {
        return this.f_9742_.f_129468_.remoteAddress();
    }

    static {
        CUSTOM_REGISTER = new ResourceLocation("register");
        CUSTOM_UNREGISTER = new ResourceLocation("unregister");
    }
}

