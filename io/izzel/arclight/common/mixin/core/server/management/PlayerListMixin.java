/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  io.izzel.arclight.mixin.Eject
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.LayeredRegistryAccess
 *  net.minecraft.core.UUIDUtil
 *  net.minecraft.core.Vec3i
 *  net.minecraft.network.Connection
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket
 *  net.minecraft.network.protocol.game.ClientboundEntityEventPacket
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundRespawnPacket
 *  net.minecraft.network.protocol.game.ClientboundSetChunkCacheRadiusPacket
 *  net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket
 *  net.minecraft.network.protocol.game.ClientboundSetExperiencePacket
 *  net.minecraft.network.protocol.game.ClientboundSetSimulationDistancePacket
 *  net.minecraft.network.protocol.game.ClientboundSoundPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.RegistryLayer
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerLoginPacketListenerImpl
 *  net.minecraft.server.players.IpBanList
 *  net.minecraft.server.players.IpBanListEntry
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.server.players.UserBanList
 *  net.minecraft.server.players.UserBanListEntry
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.ServerStatsCounter
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.storage.LevelData
 *  net.minecraft.world.level.storage.LevelResource
 *  net.minecraft.world.level.storage.PlayerDataStorage
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server.management;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.InternalEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.NetworkManagerBridge;
import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import io.izzel.arclight.common.bridge.core.network.play.ServerPlayNetHandlerBridge;
import io.izzel.arclight.common.bridge.core.server.management.PlayerListBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.mixin.Eject;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.Vec3i;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundSetChunkCacheRadiusPacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetSimulationDistancePacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import net.minecraft.server.players.IpBanList;
import net.minecraft.server.players.IpBanListEntry;
import net.minecraft.server.players.PlayerList;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.PlayerDataStorage;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.spigotmc.SpigotConfig;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PlayerList.class})
public abstract class PlayerListMixin
implements PlayerListBridge {
    @Shadow
    @Final
    public PlayerDataStorage f_11204_;
    @Shadow
    @Final
    private UserBanList f_11198_;
    @Shadow
    @Final
    private static SimpleDateFormat f_11194_;
    @Shadow
    @Final
    private IpBanList f_11199_;
    @Shadow
    @Final
    public List<ServerPlayer> f_11196_;
    @Shadow
    @Final
    protected int f_11193_;
    @Shadow
    @Final
    private MinecraftServer f_11195_;
    @Shadow
    @Final
    private Map<UUID, ServerPlayer> f_11197_;
    private CraftServer cserver;
    private transient Location arclight$loc;
    private transient Boolean arclight$suffo;
    private transient PlayerRespawnEvent.RespawnReason arclight$respawnReason;

    @Override
    @Accessor(value="players")
    @Mutable
    public abstract void bridge$setPlayers(List<ServerPlayer> var1);

    @Override
    @Accessor(value="players")
    public abstract List<ServerPlayer> bridge$getPlayers();

    @Shadow
    public abstract boolean m_5764_(GameProfile var1);

    @Shadow
    public abstract boolean m_5765_(GameProfile var1);

    @Shadow
    protected abstract void m_6765_(ServerPlayer var1);

    @Shadow
    public abstract UserBanList m_11295_();

    @Shadow
    public abstract IpBanList m_11299_();

    @Shadow
    public abstract void m_11229_(ServerPlayer var1, ServerLevel var2);

    @Shadow
    public abstract void m_11289_(ServerPlayer var1);

    @Shadow
    public abstract void m_11292_(ServerPlayer var1);

    @Shadow
    @Nullable
    public abstract ServerPlayer m_11259_(UUID var1);

    @Shadow
    public abstract void m_240416_(Component var1, boolean var2);

    @Override
    public CraftServer bridge$getCraftServer() {
        return this.cserver;
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$loadServer(MinecraftServer minecraftServer, LayeredRegistryAccess<RegistryLayer> p_251844_, PlayerDataStorage p_203844_, int p_203845_, CallbackInfo ci) {
        this.cserver = ArclightServer.createOrLoad((DedicatedServer)minecraftServer, (PlayerList)this);
    }

    @Redirect(method={"placeNewPlayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getLevel(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/server/level/ServerLevel;"))
    private ServerLevel arclight$spawnLocationEvent(MinecraftServer minecraftServer, ResourceKey<Level> dimension, Connection netManager, ServerPlayer playerIn) {
        CraftPlayer player = ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity();
        PlayerSpawnLocationEvent event = new PlayerSpawnLocationEvent(player, player.getLocation());
        this.cserver.getPluginManager().callEvent(event);
        Location loc = event.getSpawnLocation();
        ServerLevel world = ((CraftWorld)loc.getWorld()).getHandle();
        playerIn.m_284127_(world);
        playerIn.m_19890_(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        return world;
    }

    @Redirect(method={"placeNewPlayer"}, at=@At(value="FIELD", target="Lnet/minecraft/server/players/PlayerList;viewDistance:I"))
    private int arclight$spigotViewDistance(PlayerList playerList, Connection netManager, ServerPlayer playerIn) {
        return ((WorldBridge)playerIn.m_284548_()).bridge$spigotConfig().viewDistance;
    }

    @Redirect(method={"placeNewPlayer"}, at=@At(value="FIELD", target="Lnet/minecraft/server/players/PlayerList;simulationDistance:I"))
    private int arclight$spigotSimDistance(PlayerList instance, Connection netManager, ServerPlayer playerIn) {
        return ((WorldBridge)playerIn.m_284548_()).bridge$spigotConfig().simulationDistance;
    }

    @Eject(method={"placeNewPlayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private void arclight$playerJoin(PlayerList playerList, Component component, boolean flag, CallbackInfo ci, Connection netManager, ServerPlayer playerIn) {
        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity(), CraftChatMessage.fromComponent(component));
        this.f_11196_.add(playerIn);
        this.f_11197_.put(playerIn.m_20148_(), playerIn);
        this.cserver.getPluginManager().callEvent(playerJoinEvent);
        this.f_11196_.remove(playerIn);
        if (!playerIn.f_8906_.f_9742_.m_129536_()) {
            ci.cancel();
            return;
        }
        String joinMessage = playerJoinEvent.getJoinMessage();
        if (joinMessage != null && joinMessage.length() > 0) {
            for (Component line : CraftChatMessage.fromString(joinMessage)) {
                this.f_11195_.m_6846_().m_240416_(line, flag);
            }
        }
    }

    @Redirect(method={"placeNewPlayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addNewPlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void arclight$addNewPlayer(ServerLevel instance, ServerPlayer player) {
        if (player.m_9236_() == instance && !instance.m_6907_().contains(player)) {
            instance.m_8834_(player);
        }
    }

    @ModifyVariable(method={"placeNewPlayer"}, ordinal=1, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/server/level/ServerLevel;addNewPlayer(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private ServerLevel arclight$handleWorldChanges(ServerLevel value, Connection connection, ServerPlayer player) {
        return player.m_284548_();
    }

    @Inject(method={"save"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$returnIfNotPersist(ServerPlayer playerIn, CallbackInfo ci) {
        if (!((ServerPlayerEntityBridge)playerIn).bridge$isPersist()) {
            ci.cancel();
        }
    }

    @Inject(method={"remove"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;save(Lnet/minecraft/server/level/ServerPlayer;)V")})
    private void arclight$playerQuitPre(ServerPlayer playerIn, CallbackInfo ci) {
        if (playerIn.f_36095_ != playerIn.f_36096_) {
            ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().closeInventory();
        }
        String quitMessage = ArclightCaptures.getQuitMessage();
        PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity(), (String)(quitMessage != null ? quitMessage : "\u00a7e" + playerIn.m_6302_() + " left the game"));
        this.cserver.getPluginManager().callEvent(playerQuitEvent);
        ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().disconnect(playerQuitEvent.getQuitMessage());
        ArclightCaptures.captureQuitMessage(playerQuitEvent.getQuitMessage());
        this.cserver.getScoreboardManager().removePlayer(((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity());
    }

    @Override
    public ServerPlayer bridge$canPlayerLogin(SocketAddress socketAddress, GameProfile gameProfile, ServerLoginPacketListenerImpl handler) {
        UUID uuid = UUIDUtil.m_235875_((GameProfile)gameProfile);
        ArrayList list = Lists.newArrayList();
        for (ServerPlayer entityplayer : this.f_11196_) {
            if (!entityplayer.m_20148_().equals(uuid)) continue;
            list.add(entityplayer);
        }
        for (ServerPlayer entityplayer : list) {
            this.m_6765_(entityplayer);
            entityplayer.f_8906_.m_9942_((Component)Component.m_237115_((String)"multiplayer.disconnect.duplicate_login"));
        }
        ServerPlayer entity = new ServerPlayer(this.f_11195_, this.f_11195_.m_129880_(Level.f_46428_), gameProfile);
        CraftPlayer player = ((ServerPlayerEntityBridge)entity).bridge$getBukkitEntity();
        String hostname = handler == null ? "" : ((NetworkManagerBridge)handler.f_10013_).bridge$getHostname();
        InetAddress realAddress = handler == null ? ((InetSocketAddress)socketAddress).getAddress() : ((InetSocketAddress)handler.f_10013_.f_129468_.remoteAddress()).getAddress();
        PlayerLoginEvent event = new PlayerLoginEvent(player, hostname, ((InetSocketAddress)socketAddress).getAddress(), realAddress);
        if (this.m_11295_().m_11406_(gameProfile) && !((UserBanListEntry)this.m_11295_().m_11388_((Object)gameProfile)).m_7524_()) {
            UserBanListEntry gameprofilebanentry = (UserBanListEntry)this.f_11198_.m_11388_((Object)gameProfile);
            MutableComponent chatmessage = Component.m_237110_((String)"multiplayer.disconnect.banned.reason", (Object[])new Object[]{gameprofilebanentry.m_10962_()});
            if (gameprofilebanentry.m_10961_() != null) {
                chatmessage.m_7220_((Component)Component.m_237110_((String)"multiplayer.disconnect.banned.expiration", (Object[])new Object[]{f_11194_.format(gameprofilebanentry.m_10961_())}));
            }
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, CraftChatMessage.fromComponent((Component)chatmessage));
        } else if (!this.m_5764_(gameProfile)) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, SpigotConfig.whitelistMessage);
        } else if (this.m_11299_().m_11041_(socketAddress) && !this.m_11299_().m_11043_(socketAddress).m_7524_()) {
            IpBanListEntry ipbanentry = this.f_11199_.m_11043_(socketAddress);
            MutableComponent chatmessage = Component.m_237110_((String)"multiplayer.disconnect.banned_ip.reason", (Object[])new Object[]{ipbanentry.m_10962_()});
            if (ipbanentry.m_10961_() != null) {
                chatmessage.m_7220_((Component)Component.m_237110_((String)"multiplayer.disconnect.banned_ip.expiration", (Object[])new Object[]{f_11194_.format(ipbanentry.m_10961_())}));
            }
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, CraftChatMessage.fromComponent((Component)chatmessage));
        } else if (this.f_11196_.size() >= this.f_11193_ && !this.m_5765_(gameProfile)) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, SpigotConfig.serverFullMessage);
        }
        this.cserver.getPluginManager().callEvent(event);
        if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            if (handler != null) {
                handler.m_10053_(CraftChatMessage.fromStringOrNull(event.getKickMessage()));
            }
            return null;
        }
        return entity;
    }

    public ServerPlayer respawn(ServerPlayer entityplayer, boolean flag, PlayerRespawnEvent.RespawnReason reason) {
        return this.respawn(entityplayer, this.f_11195_.m_129880_(entityplayer.m_8963_()), flag, null, true, reason);
    }

    public ServerPlayer respawn(ServerPlayer playerIn, ServerLevel worldIn, boolean flag, Location location, boolean avoidSuffocation, PlayerRespawnEvent.RespawnReason respawnReason) {
        playerIn.m_8127_();
        this.f_11196_.remove(playerIn);
        playerIn.m_284548_().m_143261_(playerIn, Entity.RemovalReason.DISCARDED);
        playerIn.revive();
        BlockPos pos = playerIn.m_8961_();
        float f = playerIn.m_8962_();
        boolean flag2 = playerIn.m_8964_();
        World fromWorld = ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().getWorld();
        playerIn.f_8944_ = false;
        boolean flag3 = false;
        if (location == null) {
            boolean isBedSpawn = false;
            ServerLevel spawnWorld = this.f_11195_.m_129880_(playerIn.m_8963_());
            if (spawnWorld != null) {
                Optional optional = pos != null ? Player.m_36130_((ServerLevel)spawnWorld, (BlockPos)pos, (float)f, (boolean)flag2, (boolean)flag) : Optional.empty();
                if (optional.isPresent()) {
                    BlockState iblockdata = spawnWorld.m_8055_(pos);
                    boolean flag4 = iblockdata.m_60713_(Blocks.f_50724_);
                    Vec3 vec3d = (Vec3)optional.get();
                    if (!iblockdata.m_204336_(BlockTags.f_13038_) && !flag4) {
                        f2 = f;
                    } else {
                        Vec3 vec3d2 = Vec3.m_82539_((Vec3i)pos).m_82546_(vec3d).m_82541_();
                        f2 = (float)Mth.m_14175_((double)(Mth.m_14136_((double)vec3d2.f_82481_, (double)vec3d2.f_82479_) * 57.2957763671875 - 90.0));
                    }
                    flag3 = !flag && flag4;
                    isBedSpawn = true;
                    location = new Location(((WorldBridge)spawnWorld).bridge$getWorld(), vec3d.f_82479_, vec3d.f_82480_, vec3d.f_82481_);
                } else if (pos != null) {
                    playerIn.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132153_, 0.0f));
                    ((ServerPlayerEntityBridge)playerIn).bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause.RESET);
                    playerIn.m_9158_(Level.f_46428_, null, 0.0f, false, false);
                }
            }
            if (location == null) {
                spawnWorld = this.f_11195_.m_129880_(Level.f_46428_);
                pos = ((ServerPlayerEntityBridge)playerIn).bridge$getSpawnPoint(spawnWorld);
                location = new Location(((WorldBridge)spawnWorld).bridge$getWorld(), (float)pos.m_123341_() + 0.5f, (float)pos.m_123342_() + 0.1f, (float)pos.m_123343_() + 0.5f);
            }
            CraftPlayer respawnPlayer = ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity();
            PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, isBedSpawn && !flag3, flag3, respawnReason);
            this.cserver.getPluginManager().callEvent(respawnEvent);
            if (((ServerPlayNetHandlerBridge)playerIn.f_8906_).bridge$isDisconnected()) {
                return playerIn;
            }
            location = respawnEvent.getRespawnLocation();
            if (!flag) {
                ((ServerPlayerEntityBridge)playerIn).bridge$reset();
            }
        } else {
            location.setWorld(((WorldBridge)worldIn).bridge$getWorld());
        }
        ServerLevel serverWorld = ((CraftWorld)location.getWorld()).getHandle();
        playerIn.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        playerIn.f_8906_.m_9953_();
        while (avoidSuffocation && !serverWorld.m_45786_((Entity)playerIn) && playerIn.m_20186_() < (double)serverWorld.m_151558_()) {
            playerIn.m_6034_(playerIn.m_20185_(), playerIn.m_20186_() + 1.0, playerIn.m_20189_());
        }
        LevelData worlddata = serverWorld.m_6106_();
        playerIn.f_8906_.m_9829_((Packet)new ClientboundRespawnPacket(serverWorld.m_220362_(), serverWorld.m_46472_(), BiomeManager.m_47877_((long)serverWorld.m_7328_()), playerIn.f_8941_.m_9290_(), playerIn.f_8941_.m_9293_(), serverWorld.m_46659_(), serverWorld.m_8584_(), (byte)(flag ? 1 : 0), playerIn.m_219759_(), playerIn.m_287157_()));
        playerIn.f_8906_.m_9829_((Packet)new ClientboundSetChunkCacheRadiusPacket(((WorldBridge)serverWorld).bridge$spigotConfig().viewDistance));
        playerIn.f_8906_.m_9829_((Packet)new ClientboundSetSimulationDistancePacket(((WorldBridge)serverWorld).bridge$spigotConfig().simulationDistance));
        playerIn.m_284127_(serverWorld);
        ((ServerPlayNetHandlerBridge)playerIn.f_8906_).bridge$teleport(new Location(((WorldBridge)serverWorld).bridge$getWorld(), playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), playerIn.m_146908_(), playerIn.m_146909_()));
        playerIn.m_20260_(false);
        playerIn.f_8906_.m_9829_((Packet)new ClientboundSetDefaultSpawnPositionPacket(serverWorld.m_220360_(), serverWorld.m_220361_()));
        playerIn.f_8906_.m_9829_((Packet)new ClientboundChangeDifficultyPacket(worlddata.m_5472_(), worlddata.m_5474_()));
        playerIn.f_8906_.m_9829_((Packet)new ClientboundSetExperiencePacket(playerIn.f_36080_, playerIn.f_36079_, playerIn.f_36078_));
        this.m_11229_(playerIn, serverWorld);
        this.m_11289_(playerIn);
        if (!((ServerPlayNetHandlerBridge)playerIn.f_8906_).bridge$isDisconnected()) {
            serverWorld.m_8845_(playerIn);
            this.f_11196_.add(playerIn);
            this.f_11197_.put(playerIn.m_20148_(), playerIn);
        }
        playerIn.m_21153_(playerIn.m_21223_());
        ForgeEventFactory.firePlayerChangedDimensionEvent((Player)playerIn, (ResourceKey)((CraftWorld)fromWorld).getHandle().f_46421_, (ResourceKey)serverWorld.f_46421_);
        if (flag3) {
            playerIn.f_8906_.m_9829_((Packet)new ClientboundSoundPacket((Holder)SoundEvents.f_12377_, SoundSource.BLOCKS, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), 1.0f, 1.0f, serverWorld.f_46441_.m_188505_()));
        }
        this.m_11292_(playerIn);
        playerIn.m_6885_();
        for (Object o1 : playerIn.m_21220_()) {
            MobEffectInstance mobEffect = (MobEffectInstance)o1;
            playerIn.f_8906_.m_9829_((Packet)new ClientboundUpdateMobEffectPacket(playerIn.m_19879_(), mobEffect));
        }
        playerIn.m_9209_(((CraftWorld)fromWorld).getHandle());
        if (fromWorld != location.getWorld()) {
            PlayerChangedWorldEvent event = new PlayerChangedWorldEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity(), fromWorld);
            Bukkit.getPluginManager().callEvent(event);
        }
        if (((ServerPlayNetHandlerBridge)playerIn.f_8906_).bridge$isDisconnected()) {
            this.m_6765_(playerIn);
        }
        return playerIn;
    }

    @Overwrite
    public ServerPlayer m_11236_(ServerPlayer playerIn, boolean conqueredEnd) {
        Location location = this.arclight$loc;
        this.arclight$loc = null;
        boolean avoidSuffocation = this.arclight$suffo == null || this.arclight$suffo != false;
        this.arclight$suffo = null;
        PlayerRespawnEvent.RespawnReason respawnReason = this.arclight$respawnReason == null ? PlayerRespawnEvent.RespawnReason.DEATH : this.arclight$respawnReason;
        this.arclight$respawnReason = null;
        playerIn.m_8127_();
        this.f_11196_.remove(playerIn);
        playerIn.m_284548_().m_143261_(playerIn, Entity.RemovalReason.DISCARDED);
        BlockPos pos = playerIn.m_8961_();
        float f = playerIn.m_8962_();
        boolean flag2 = playerIn.m_8964_();
        World fromWorld = ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().getWorld();
        playerIn.f_8944_ = false;
        boolean flag3 = false;
        ServerLevel spawnWorld = this.f_11195_.m_129880_(playerIn.m_8963_());
        if (location == null) {
            boolean isBedSpawn = false;
            if (spawnWorld != null) {
                Optional optional = pos != null ? Player.m_36130_((ServerLevel)spawnWorld, (BlockPos)pos, (float)f, (boolean)flag2, (boolean)conqueredEnd) : Optional.empty();
                if (optional.isPresent()) {
                    BlockState iblockdata = spawnWorld.m_8055_(pos);
                    boolean flag4 = iblockdata.m_60713_(Blocks.f_50724_);
                    Vec3 vec3d = (Vec3)optional.get();
                    if (!iblockdata.m_204336_(BlockTags.f_13038_) && !flag4) {
                        f2 = f;
                    } else {
                        Vec3 vec3d2 = Vec3.m_82539_((Vec3i)pos).m_82546_(vec3d).m_82541_();
                        f2 = (float)Mth.m_14175_((double)(Mth.m_14136_((double)vec3d2.f_82481_, (double)vec3d2.f_82479_) * 57.2957763671875 - 90.0));
                    }
                    flag3 = !flag2 && flag4;
                    isBedSpawn = true;
                    location = new Location(((WorldBridge)spawnWorld).bridge$getWorld(), vec3d.f_82479_, vec3d.f_82480_, vec3d.f_82481_);
                } else if (pos != null) {
                    playerIn.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132153_, 0.0f));
                    ((ServerPlayerEntityBridge)playerIn).bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause.RESET);
                    playerIn.m_9158_(Level.f_46428_, null, 0.0f, false, false);
                }
            }
            if (location == null) {
                spawnWorld = this.f_11195_.m_129880_(Level.f_46428_);
                pos = ((ServerPlayerEntityBridge)playerIn).bridge$getSpawnPoint(spawnWorld);
                location = new Location(((WorldBridge)spawnWorld).bridge$getWorld(), (float)pos.m_123341_() + 0.5f, (float)pos.m_123342_() + 0.1f, (float)pos.m_123343_() + 0.5f);
            }
            CraftPlayer respawnPlayer = ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity();
            PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, isBedSpawn && !flag3, flag3, respawnReason);
            this.cserver.getPluginManager().callEvent(respawnEvent);
            if (((ServerPlayNetHandlerBridge)playerIn.f_8906_).bridge$isDisconnected()) {
                return playerIn;
            }
            location = respawnEvent.getRespawnLocation();
            if (!conqueredEnd) {
                ((ServerPlayerEntityBridge)playerIn).bridge$reset();
            }
        } else {
            location.setWorld(((WorldBridge)spawnWorld).bridge$getWorld());
        }
        ServerLevel serverWorld = ((CraftWorld)location.getWorld()).getHandle();
        ServerPlayer serverplayerentity = new ServerPlayer(this.f_11195_, serverWorld, playerIn.m_36316_());
        ((InternalEntityBridge)playerIn).internal$getBukkitEntity().setHandle((Entity)serverplayerentity);
        ((EntityBridge)serverplayerentity).bridge$setBukkitEntity(((InternalEntityBridge)playerIn).internal$getBukkitEntity());
        if (playerIn instanceof Mob) {
            ((Mob)playerIn).m_21455_(true, false);
        }
        playerIn.f_8906_.f_9743_ = serverplayerentity;
        serverplayerentity.f_8906_ = playerIn.f_8906_;
        serverplayerentity.m_9015_(playerIn, conqueredEnd);
        serverplayerentity.m_9158_(playerIn.m_8963_(), playerIn.m_8961_(), playerIn.m_8962_(), playerIn.m_8964_(), false);
        if (!conqueredEnd) {
            serverplayerentity.m_150109_().m_36006_(playerIn.m_150109_());
            serverplayerentity.f_36078_ = playerIn.f_36078_;
            serverplayerentity.f_36079_ = playerIn.f_36079_;
            serverplayerentity.f_36080_ = playerIn.f_36080_;
            serverplayerentity.m_36397_(playerIn.m_36344_());
        }
        serverplayerentity.m_20234_(playerIn.m_19879_());
        serverplayerentity.m_36163_(playerIn.m_5737_());
        for (String s : playerIn.m_19880_()) {
            serverplayerentity.m_20049_(s);
        }
        serverplayerentity.m_7678_(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        serverplayerentity.f_8906_.m_9953_();
        while (avoidSuffocation && !serverWorld.m_45786_((Entity)serverplayerentity) && serverplayerentity.m_20186_() < (double)serverWorld.m_151558_()) {
            serverplayerentity.m_6034_(serverplayerentity.m_20185_(), serverplayerentity.m_20186_() + 1.0, serverplayerentity.m_20189_());
        }
        LevelData iworldinfo = serverplayerentity.m_9236_().m_6106_();
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundRespawnPacket(serverplayerentity.m_9236_().m_220362_(), serverplayerentity.m_9236_().m_46472_(), BiomeManager.m_47877_((long)serverplayerentity.m_284548_().m_7328_()), serverplayerentity.f_8941_.m_9290_(), serverplayerentity.f_8941_.m_9293_(), serverplayerentity.m_284548_().m_46659_(), serverplayerentity.m_284548_().m_8584_(), (byte)(conqueredEnd ? 1 : 0), serverplayerentity.m_219759_(), serverplayerentity.m_287157_()));
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundSetChunkCacheRadiusPacket(((WorldBridge)serverWorld).bridge$spigotConfig().viewDistance));
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundSetSimulationDistancePacket(((WorldBridge)serverWorld).bridge$spigotConfig().simulationDistance));
        serverplayerentity.m_284127_(serverWorld);
        ((ServerPlayNetHandlerBridge)serverplayerentity.f_8906_).bridge$teleport(new Location(((WorldBridge)serverWorld).bridge$getWorld(), serverplayerentity.m_20185_(), serverplayerentity.m_20186_(), serverplayerentity.m_20189_(), serverplayerentity.m_146908_(), serverplayerentity.m_146909_()));
        serverplayerentity.m_20260_(false);
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundSetDefaultSpawnPositionPacket(serverWorld.m_220360_(), serverWorld.m_220361_()));
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundChangeDifficultyPacket(iworldinfo.m_5472_(), iworldinfo.m_5474_()));
        serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundSetExperiencePacket(serverplayerentity.f_36080_, serverplayerentity.f_36079_, serverplayerentity.f_36078_));
        this.m_11229_(serverplayerentity, serverWorld);
        this.m_11289_(serverplayerentity);
        if (!((ServerPlayNetHandlerBridge)serverplayerentity.f_8906_).bridge$isDisconnected()) {
            serverWorld.m_8845_(serverplayerentity);
            this.f_11196_.add(serverplayerentity);
            this.f_11197_.put(serverplayerentity.m_20148_(), serverplayerentity);
        }
        serverplayerentity.m_143429_();
        serverplayerentity.m_21153_(serverplayerentity.m_21223_());
        ForgeEventFactory.firePlayerRespawnEvent((Player)serverplayerentity, (boolean)conqueredEnd);
        if (flag2) {
            serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundSoundPacket((Holder)SoundEvents.f_12377_, SoundSource.BLOCKS, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), 1.0f, 1.0f, serverWorld.f_46441_.m_188505_()));
        }
        this.m_11292_(serverplayerentity);
        serverplayerentity.m_6885_();
        for (Object o1 : serverplayerentity.m_21220_()) {
            MobEffectInstance mobEffect = (MobEffectInstance)o1;
            serverplayerentity.f_8906_.m_9829_((Packet)new ClientboundUpdateMobEffectPacket(serverplayerentity.m_19879_(), mobEffect));
        }
        serverplayerentity.m_9209_(((CraftWorld)fromWorld).getHandle());
        if (fromWorld != location.getWorld()) {
            PlayerChangedWorldEvent event = new PlayerChangedWorldEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)serverplayerentity).bridge$getBukkitEntity(), fromWorld);
            Bukkit.getPluginManager().callEvent(event);
        }
        if (((ServerPlayNetHandlerBridge)serverplayerentity.f_8906_).bridge$isDisconnected()) {
            this.m_6765_(serverplayerentity);
        }
        return serverplayerentity;
    }

    public void broadcastAll(Packet<?> packet, Player entityhuman) {
        for (ServerPlayer entityplayer : this.f_11196_) {
            if (entityhuman instanceof ServerPlayer && !((ServerPlayerEntityBridge)entityplayer).bridge$getBukkitEntity().canSee(((ServerPlayerEntityBridge)entityhuman).bridge$getBukkitEntity())) continue;
            entityplayer.f_8906_.m_9829_(packet);
        }
    }

    public void broadcastAll(Packet<?> packet, Level world) {
        for (int i = 0; i < world.m_6907_().size(); ++i) {
            ((ServerPlayer)world.m_6907_().get((int)i)).f_8906_.m_9829_(packet);
        }
    }

    @Inject(method={"sendPlayerPermissionLevel(Lnet/minecraft/server/level/ServerPlayer;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getCommands()Lnet/minecraft/commands/Commands;")})
    private void arclight$calculatePerms(ServerPlayer player, int permLevel, CallbackInfo ci) {
        ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity().recalculatePermissions();
    }

    @Redirect(method={"sendAllPlayerInfo"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerPlayer;resetSentInfo()V"))
    private void arclight$useScaledHealth(ServerPlayer playerEntity) {
        ((ServerPlayerEntityBridge)playerEntity).bridge$getBukkitEntity().updateScaledHealth();
        ((SynchedEntityDataBridge)playerEntity.m_20088_()).bridge$refresh(playerEntity);
        int i = playerEntity.m_9236_().m_46469_().m_46207_(GameRules.f_46145_) ? 22 : 23;
        playerEntity.f_8906_.m_9829_((Packet)new ClientboundEntityEventPacket((Entity)playerEntity, (byte)i));
        float immediateRespawn = playerEntity.m_9236_().m_46469_().m_46207_(GameRules.f_46156_) ? 1.0f : 0.0f;
        playerEntity.f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132164_, immediateRespawn));
    }

    public void broadcastMessage(Component[] components) {
        for (Component component : components) {
            this.m_240416_(component, false);
        }
    }

    @Override
    public void bridge$sendMessage(Component[] components) {
        this.broadcastMessage(components);
    }

    public ServerStatsCounter getPlayerStats(ServerPlayer entityhuman) {
        ServerStatsCounter serverstatisticmanager = entityhuman.m_8951_();
        return serverstatisticmanager == null ? this.getPlayerStats(entityhuman.m_20148_(), entityhuman.m_7755_().getString()) : serverstatisticmanager;
    }

    public ServerStatsCounter getPlayerStats(UUID uuid, String displayName) {
        ServerPlayer entityhuman = this.m_11259_(uuid);
        ServerStatsCounter serverstatisticmanager = entityhuman == null ? null : entityhuman.m_8951_();
        ServerStatsCounter serverStatisticsManager = serverstatisticmanager;
        if (serverstatisticmanager == null) {
            File file2;
            File file = this.f_11195_.m_129843_(LevelResource.f_78175_).toFile();
            File file1 = new File(file, String.valueOf(uuid) + ".json");
            if (!file1.exists() && (file2 = new File(file, displayName + ".json")).exists() && file2.isFile()) {
                file2.renameTo(file1);
            }
            serverstatisticmanager = new ServerStatsCounter(this.f_11195_, file1);
        }
        return serverstatisticmanager;
    }
}

