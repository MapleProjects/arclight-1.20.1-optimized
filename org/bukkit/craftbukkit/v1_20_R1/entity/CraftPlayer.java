/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.google.common.io.BaseEncoding
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Pair
 *  io.netty.buffer.Unpooled
 *  it.unimi.dsi.fastutil.shorts.ShortArraySet
 *  it.unimi.dsi.fastutil.shorts.ShortSet
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.SectionPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.PlayerChatMessage
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket
 *  net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundClearTitlesPacket
 *  net.minecraft.network.protocol.game.ClientboundCustomChatCompletionsPacket
 *  net.minecraft.network.protocol.game.ClientboundCustomChatCompletionsPacket$Action
 *  net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket
 *  net.minecraft.network.protocol.game.ClientboundMapItemDataPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket$Action
 *  net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket
 *  net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket
 *  net.minecraft.network.protocol.game.ClientboundSetBorderSizePacket
 *  net.minecraft.network.protocol.game.ClientboundSetBorderWarningDelayPacket
 *  net.minecraft.network.protocol.game.ClientboundSetBorderWarningDistancePacket
 *  net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket
 *  net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket
 *  net.minecraft.network.protocol.game.ClientboundSetExperiencePacket
 *  net.minecraft.network.protocol.game.ClientboundSetHealthPacket
 *  net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket
 *  net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket
 *  net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket
 *  net.minecraft.network.protocol.game.ClientboundSoundEntityPacket
 *  net.minecraft.network.protocol.game.ClientboundSoundPacket
 *  net.minecraft.network.protocol.game.ClientboundStopSoundPacket
 *  net.minecraft.network.protocol.game.ClientboundSystemChatPacket
 *  net.minecraft.network.protocol.game.ClientboundTabListPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.PlayerAdvancements
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.ChunkMap$TrackedEntity
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  net.minecraft.server.players.StoredUserEntry
 *  net.minecraft.server.players.UserWhiteListEntry
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.food.FoodData
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.entity.SignText
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.BorderChangeListener
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.saveddata.maps.MapDecoration
 *  net.minecraft.world.level.saveddata.maps.MapDecoration$Type
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData$MapPatch
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.BaseEncoding;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Pair;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.shorts.ShortArraySet;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.ref.WeakReference;
import java.lang.runtime.ObjectMethods;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundClearTitlesPacket;
import net.minecraft.network.protocol.game.ClientboundCustomChatCompletionsPacket;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderSizePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDelayPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDistancePacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.StoredUserEntry;
import net.minecraft.server.players.UserWhiteListEntry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.Vec3;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.ban.IpBanList;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
import org.bukkit.craftbukkit.v1_20_R1.CraftEffect;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.CraftOfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftSound;
import org.bukkit.craftbukkit.v1_20_R1.CraftStatistic;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorldBorder;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancement;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancementProgress;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.conversations.ConversationTracker;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.map.CraftMapView;
import org.bukkit.craftbukkit.v1_20_R1.map.RenderData;
import org.bukkit.craftbukkit.v1_20_R1.profile.CraftPlayerProfile;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.event.player.PlayerHideEntityEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShowEntityEvent;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapCursor;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.spigotmc.AsyncCatcher;

@DelegateDeserialization(value=CraftOfflinePlayer.class)
public class CraftPlayer
extends CraftHumanEntity
implements org.bukkit.entity.Player {
    private long firstPlayed = 0L;
    private long lastPlayed = 0L;
    private boolean hasPlayedBefore = false;
    private final ConversationTracker conversationTracker = new ConversationTracker();
    private final Set<String> channels = new HashSet<String>();
    private final Map<UUID, Set<WeakReference<Plugin>>> invertedVisibilityEntities = new HashMap<UUID, Set<WeakReference<Plugin>>>();
    private static final WeakHashMap<Plugin, WeakReference<Plugin>> pluginWeakReferences = new WeakHashMap();
    private int hash = 0;
    private double health = 20.0;
    private boolean scaledHealth = false;
    private double healthScale = 20.0;
    private CraftWorldBorder clientWorldBorder = null;
    private BorderChangeListener clientWorldBorderListener = this.createWorldBorderListener();
    private Component playerListHeader;
    private Component playerListFooter;
    private final Player.Spigot spigot = new Player.Spigot(){

        @Override
        public InetSocketAddress getRawAddress() {
            return (InetSocketAddress)CraftPlayer.this.getHandle().f_8906_.getRawAddress();
        }

        @Override
        public void respawn() {
            if (CraftPlayer.this.getHealth() <= 0.0 && CraftPlayer.this.isOnline()) {
                CraftPlayer.this.server.getServer().m_6846_().respawn(CraftPlayer.this.getHandle(), false, PlayerRespawnEvent.RespawnReason.PLUGIN);
            }
        }

        @Override
        public Set<org.bukkit.entity.Player> getHiddenPlayers() {
            HashSet<org.bukkit.entity.Player> ret = new HashSet<org.bukkit.entity.Player>();
            for (org.bukkit.entity.Player player : CraftPlayer.this.getServer().getOnlinePlayers()) {
                if (CraftPlayer.this.canSee(player)) continue;
                ret.add(player);
            }
            return Collections.unmodifiableSet(ret);
        }

        @Override
        public void sendMessage(BaseComponent component) {
            this.sendMessage(new BaseComponent[]{component});
        }

        @Override
        public void sendMessage(BaseComponent ... components) {
            this.sendMessage(ChatMessageType.SYSTEM, components);
        }

        @Override
        public void sendMessage(UUID sender, BaseComponent component) {
            this.sendMessage(ChatMessageType.CHAT, sender, component);
        }

        @Override
        public void sendMessage(UUID sender, BaseComponent ... components) {
            this.sendMessage(ChatMessageType.CHAT, sender, components);
        }

        @Override
        public void sendMessage(ChatMessageType position, BaseComponent component) {
            this.sendMessage(position, new BaseComponent[]{component});
        }

        @Override
        public void sendMessage(ChatMessageType position, BaseComponent ... components) {
            this.sendMessage(position, (UUID)null, components);
        }

        @Override
        public void sendMessage(ChatMessageType position, UUID sender, BaseComponent component) {
            this.sendMessage(position, sender, new BaseComponent[]{component});
        }

        @Override
        public void sendMessage(ChatMessageType position, UUID sender, BaseComponent ... components) {
            if (CraftPlayer.this.getHandle().f_8906_ == null) {
                return;
            }
            CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSystemChatPacket(components, position == ChatMessageType.ACTION_BAR));
        }
    };

    public CraftPlayer(CraftServer server, ServerPlayer entity) {
        super(server, (Player)entity);
        this.firstPlayed = System.currentTimeMillis();
    }

    public GameProfile getProfile() {
        return this.getHandle().m_36316_();
    }

    @Override
    public boolean isOp() {
        return this.server.getHandle().m_11303_(this.getProfile());
    }

    @Override
    public void setOp(boolean value) {
        if (value == this.isOp()) {
            return;
        }
        if (value) {
            this.server.getHandle().m_5749_(this.getProfile());
        } else {
            this.server.getHandle().m_5750_(this.getProfile());
        }
        this.perm.recalculatePermissions();
    }

    @Override
    public boolean isOnline() {
        return this.server.getPlayer(this.getUniqueId()) != null;
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return new CraftPlayerProfile(this.getProfile());
    }

    @Override
    public InetSocketAddress getAddress() {
        if (this.getHandle().f_8906_ == null) {
            return null;
        }
        SocketAddress addr = this.getHandle().f_8906_.m_264262_();
        if (addr instanceof InetSocketAddress) {
            return (InetSocketAddress)addr;
        }
        return null;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        if (ignorePose) {
            return 1.62;
        }
        return this.getEyeHeight();
    }

    @Override
    public void sendRawMessage(String message) {
        this.sendRawMessage(null, message);
    }

    @Override
    public void sendRawMessage(UUID sender, String message) {
        Preconditions.checkArgument((message != null ? 1 : 0) != 0, (Object)"message cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        Component[] componentArray = CraftChatMessage.fromString(message);
        int n = componentArray.length;
        int n2 = 0;
        while (n2 < n) {
            Component component = componentArray[n2];
            this.getHandle().m_213846_(component);
            ++n2;
        }
    }

    @Override
    public void sendMessage(String message) {
        if (!this.conversationTracker.isConversingModaly()) {
            this.sendRawMessage(message);
        }
    }

    @Override
    public void sendMessage(String ... messages) {
        String[] stringArray = messages;
        int n = messages.length;
        int n2 = 0;
        while (n2 < n) {
            String message = stringArray[n2];
            this.sendMessage(message);
            ++n2;
        }
    }

    @Override
    public void sendMessage(UUID sender, String message) {
        if (!this.conversationTracker.isConversingModaly()) {
            this.sendRawMessage(sender, message);
        }
    }

    @Override
    public void sendMessage(UUID sender, String ... messages) {
        String[] stringArray = messages;
        int n = messages.length;
        int n2 = 0;
        while (n2 < n) {
            String message = stringArray[n2];
            this.sendMessage(sender, message);
            ++n2;
        }
    }

    @Override
    public String getDisplayName() {
        return this.getHandle().displayName;
    }

    @Override
    public void setDisplayName(String name) {
        this.getHandle().displayName = name == null ? this.getName() : name;
    }

    @Override
    public String getPlayerListName() {
        return this.getHandle().listName == null ? this.getName() : CraftChatMessage.fromComponent(this.getHandle().listName);
    }

    @Override
    public void setPlayerListName(String name) {
        if (name == null) {
            name = this.getName();
        }
        this.getHandle().listName = name.equals(this.getName()) ? null : CraftChatMessage.fromStringOrNull(name);
        for (ServerPlayer player : this.server.getHandle().f_11196_) {
            if (!player.getBukkitEntity().canSee(this)) continue;
            player.f_8906_.m_9829_((Packet)new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME, this.getHandle()));
        }
    }

    @Override
    public String getPlayerListHeader() {
        return this.playerListHeader == null ? null : CraftChatMessage.fromComponent(this.playerListHeader);
    }

    @Override
    public String getPlayerListFooter() {
        return this.playerListFooter == null ? null : CraftChatMessage.fromComponent(this.playerListFooter);
    }

    @Override
    public void setPlayerListHeader(String header) {
        this.playerListHeader = CraftChatMessage.fromStringOrNull(header, true);
        this.updatePlayerListHeaderFooter();
    }

    @Override
    public void setPlayerListFooter(String footer) {
        this.playerListFooter = CraftChatMessage.fromStringOrNull(footer, true);
        this.updatePlayerListHeaderFooter();
    }

    @Override
    public void setPlayerListHeaderFooter(String header, String footer) {
        this.playerListHeader = CraftChatMessage.fromStringOrNull(header, true);
        this.playerListFooter = CraftChatMessage.fromStringOrNull(footer, true);
        this.updatePlayerListHeaderFooter();
    }

    private void updatePlayerListHeaderFooter() {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundTabListPacket packet = new ClientboundTabListPacket((Component)(this.playerListHeader == null ? Component.m_237119_() : this.playerListHeader), (Component)(this.playerListFooter == null ? Component.m_237119_() : this.playerListFooter));
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OfflinePlayer)) {
            return false;
        }
        OfflinePlayer other = (OfflinePlayer)obj;
        if (this.getUniqueId() == null || other.getUniqueId() == null) {
            return false;
        }
        boolean uuidEquals = this.getUniqueId().equals(other.getUniqueId());
        boolean idEquals = true;
        if (other instanceof CraftPlayer) {
            boolean bl = idEquals = this.getEntityId() == ((CraftPlayer)other).getEntityId();
        }
        return uuidEquals && idEquals;
    }

    @Override
    public void kickPlayer(String message) {
        AsyncCatcher.catchOp("player kick");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.disconnect(message == null ? "" : message);
    }

    @Override
    public void setCompassTarget(Location loc) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetDefaultSpawnPositionPacket(CraftLocation.toBlockPosition(loc), loc.getYaw()));
    }

    @Override
    public Location getCompassTarget() {
        return this.getHandle().compassTarget;
    }

    @Override
    public void chat(String msg) {
        Preconditions.checkArgument((msg != null ? 1 : 0) != 0, (Object)"msg cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.chat(msg, PlayerChatMessage.m_247306_((String)msg), false);
    }

    @Override
    public boolean performCommand(String command) {
        Preconditions.checkArgument((command != null ? 1 : 0) != 0, (Object)"command cannot be null");
        return this.server.dispatchCommand(this, command);
    }

    @Override
    public void playNote(Location loc, byte instrument, byte note) {
        this.playNote(loc, Instrument.getByType(instrument), new Note(note));
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((instrument != null ? 1 : 0) != 0, (Object)"Instrument cannot be null");
        Preconditions.checkArgument((note != null ? 1 : 0) != 0, (Object)"Note cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        String instrumentName = switch (instrument.ordinal()) {
            case 0 -> "harp";
            case 1 -> "basedrum";
            case 2 -> "snare";
            case 3 -> "hat";
            case 4 -> "bass";
            case 5 -> "flute";
            case 6 -> "bell";
            case 7 -> "guitar";
            case 8 -> "chime";
            case 9 -> "xylophone";
            case 10 -> "iron_xylophone";
            case 11 -> "cow_bell";
            case 12 -> "didgeridoo";
            case 13 -> "bit";
            case 14 -> "banjo";
            case 15 -> "pling";
            case 16 -> "xylophone";
            default -> null;
        };
        float f = (float)Math.pow(2.0, ((double)note.getId() - 12.0) / 12.0);
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSoundPacket(BuiltInRegistries.f_256894_.m_263177_((Object)CraftSound.getSoundEffect("block.note_block." + instrumentName)), SoundSource.RECORDS, (double)loc.getBlockX(), (double)loc.getBlockY(), (double)loc.getBlockZ(), 3.0f, f, this.getHandle().m_217043_().m_188505_()));
    }

    @Override
    public void playSound(Location loc, Sound sound, float volume, float pitch) {
        this.playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Location loc, String sound, float volume, float pitch) {
        this.playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Location loc, Sound sound, SoundCategory category, float volume, float pitch) {
        if (loc == null || sound == null || category == null || this.getHandle().f_8906_ == null) {
            return;
        }
        this.playSound0(loc, (Holder<SoundEvent>)BuiltInRegistries.f_256894_.m_263177_((Object)CraftSound.getSoundEffect(sound)), SoundSource.valueOf((String)category.name()), volume, pitch);
    }

    @Override
    public void playSound(Location loc, String sound, SoundCategory category, float volume, float pitch) {
        if (loc == null || sound == null || category == null || this.getHandle().f_8906_ == null) {
            return;
        }
        this.playSound0(loc, (Holder<SoundEvent>)Holder.m_205709_((Object)SoundEvent.m_262824_((ResourceLocation)new ResourceLocation(sound))), SoundSource.valueOf((String)category.name()), volume, pitch);
    }

    private void playSound0(Location loc, Holder<SoundEvent> soundEffectHolder, SoundSource categoryNMS, float volume, float pitch) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundSoundPacket packet = new ClientboundSoundPacket(soundEffectHolder, categoryNMS, loc.getX(), loc.getY(), loc.getZ(), volume, pitch, this.getHandle().m_217043_().m_188505_());
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void playSound(Entity entity, Sound sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Entity entity, String sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    @Override
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch) {
        CraftEntity craftEntity;
        Entity entity2 = entity;
        if (!(entity2 instanceof CraftEntity) || (craftEntity = (CraftEntity)entity2) != (CraftEntity)entity2 || sound == null || category == null || this.getHandle().f_8906_ == null) {
            return;
        }
        this.playSound0(entity, (Holder<SoundEvent>)BuiltInRegistries.f_256894_.m_263177_((Object)CraftSound.getSoundEffect(sound)), SoundSource.valueOf((String)category.name()), volume, pitch);
    }

    @Override
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch) {
        CraftEntity craftEntity;
        Entity entity2 = entity;
        if (!(entity2 instanceof CraftEntity) || (craftEntity = (CraftEntity)entity2) != (CraftEntity)entity2 || sound == null || category == null || this.getHandle().f_8906_ == null) {
            return;
        }
        this.playSound0(entity, (Holder<SoundEvent>)Holder.m_205709_((Object)SoundEvent.m_262824_((ResourceLocation)new ResourceLocation(sound))), SoundSource.valueOf((String)category.name()), volume, pitch);
    }

    private void playSound0(Entity entity, Holder<SoundEvent> soundEffectHolder, SoundSource categoryNMS, float volume, float pitch) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        Preconditions.checkArgument((soundEffectHolder != null ? 1 : 0) != 0, (Object)"Holder of SoundEffect cannot be null");
        Preconditions.checkArgument((categoryNMS != null ? 1 : 0) != 0, (Object)"SoundCategory cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        if (!(entity instanceof CraftEntity var6_7)) {
            return;
        }
        ClientboundSoundEntityPacket packet = new ClientboundSoundEntityPacket(soundEffectHolder, categoryNMS, craftEntity.getHandle(), volume, pitch, this.getHandle().m_217043_().m_188505_());
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void stopSound(Sound sound) {
        this.stopSound(sound, null);
    }

    @Override
    public void stopSound(String sound) {
        this.stopSound(sound, null);
    }

    @Override
    public void stopSound(Sound sound, SoundCategory category) {
        this.stopSound(sound.getKey().getKey(), category);
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundStopSoundPacket(new ResourceLocation(sound), category == null ? SoundSource.MASTER : SoundSource.valueOf((String)category.name())));
    }

    @Override
    public void stopSound(SoundCategory category) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundStopSoundPacket(null, SoundSource.valueOf((String)category.name())));
    }

    @Override
    public void stopAllSounds() {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundStopSoundPacket(null, null));
    }

    @Override
    public void playEffect(Location loc, Effect effect, int data) {
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"Effect cannot be null");
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        int packetData = effect.getId();
        ClientboundLevelEventPacket packet = new ClientboundLevelEventPacket(packetData, CraftLocation.toBlockPosition(loc), data, false);
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        Preconditions.checkArgument((effect != null ? 1 : 0) != 0, (Object)"Effect cannot be null");
        if (data != null) {
            Preconditions.checkArgument((effect.getData() != null ? 1 : 0) != 0, (String)"Effect.%s does not have a valid Data", (Object)((Object)effect));
            Preconditions.checkArgument((boolean)effect.getData().isAssignableFrom(data.getClass()), (String)"%s data cannot be used for the %s effect", (Object)data.getClass().getName(), (Object)((Object)effect));
        } else {
            Preconditions.checkArgument((effect.getData() == null || effect == Effect.ELECTRIC_SPARK ? 1 : 0) != 0, (String)"Wrong kind of data for the %s effect", (Object)((Object)effect));
        }
        int datavalue = CraftEffect.getDataValue(effect, data);
        this.playEffect(loc, effect, datavalue);
    }

    @Override
    public boolean breakBlock(Block block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"Block cannot be null");
        Preconditions.checkArgument((boolean)block.getWorld().equals(this.getWorld()), (Object)"Cannot break blocks across worlds");
        return this.getHandle().f_8941_.m_9280_(new BlockPos(block.getX(), block.getY(), block.getZ()));
    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundBlockUpdatePacket packet = new ClientboundBlockUpdatePacket(CraftLocation.toBlockPosition(loc), CraftMagicNumbers.getBlock(material, data));
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void sendBlockChange(Location loc, BlockData block) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundBlockUpdatePacket packet = new ClientboundBlockUpdatePacket(CraftLocation.toBlockPosition(loc), ((CraftBlockData)block).getState());
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void sendBlockChanges(Collection<org.bukkit.block.BlockState> blocks) {
        Preconditions.checkArgument((blocks != null ? 1 : 0) != 0, (Object)"blocks must not be null");
        if (this.getHandle().f_8906_ == null || blocks.isEmpty()) {
            return;
        }
        HashMap<SectionPos, ChunkSectionChanges> changes = new HashMap<SectionPos, ChunkSectionChanges>();
        for (org.bukkit.block.BlockState blockState : blocks) {
            CraftBlockState cstate = (CraftBlockState)blockState;
            BlockPos blockPosition = cstate.getPosition();
            SectionPos sectionPosition = SectionPos.m_123199_((BlockPos)blockPosition);
            ChunkSectionChanges sectionChanges = changes.computeIfAbsent(sectionPosition, ignore -> new ChunkSectionChanges());
            sectionChanges.positions().add(SectionPos.m_123218_((BlockPos)blockPosition));
            sectionChanges.blockData().add(cstate.getHandle());
        }
        for (Map.Entry entry : changes.entrySet()) {
            ChunkSectionChanges chunkChanges = (ChunkSectionChanges)entry.getValue();
            ClientboundSectionBlocksUpdatePacket packet = new ClientboundSectionBlocksUpdatePacket((SectionPos)entry.getKey(), chunkChanges.positions(), (BlockState[])chunkChanges.blockData().toArray(BlockState[]::new));
            this.getHandle().f_8906_.m_9829_((Packet)packet);
        }
    }

    @Override
    public void sendBlockChanges(Collection<org.bukkit.block.BlockState> blocks, boolean suppressLightUpdates) {
        this.sendBlockChanges(blocks);
    }

    @Override
    public void sendBlockDamage(Location loc, float progress) {
        this.sendBlockDamage(loc, progress, this.getEntityId());
    }

    @Override
    public void sendBlockDamage(Location loc, float progress, Entity source) {
        Preconditions.checkArgument((source != null ? 1 : 0) != 0, (Object)"source must not be null");
        this.sendBlockDamage(loc, progress, source.getEntityId());
    }

    @Override
    public void sendBlockDamage(Location loc, float progress, int sourceId) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"loc must not be null");
        Preconditions.checkArgument(((double)progress >= 0.0 && (double)progress <= 1.0 ? 1 : 0) != 0, (Object)"progress must be between 0.0 and 1.0 (inclusive)");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        int stage = (int)(9.0f * progress);
        if (progress == 0.0f) {
            stage = -1;
        }
        ClientboundBlockDestructionPacket packet = new ClientboundBlockDestructionPacket(sourceId, CraftLocation.toBlockPosition(loc), stage);
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void sendSignChange(Location loc, String[] lines) {
        this.sendSignChange(loc, lines, DyeColor.BLACK);
    }

    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor) {
        this.sendSignChange(loc, lines, dyeColor, false);
    }

    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor, boolean hasGlowingText) {
        Preconditions.checkArgument((loc != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((dyeColor != null ? 1 : 0) != 0, (Object)"DyeColor cannot be null");
        if (lines == null) {
            lines = new String[4];
        }
        Preconditions.checkArgument((lines.length >= 4 ? 1 : 0) != 0, (String)"Must have at least 4 lines (%s)", (int)lines.length);
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        Component[] components = CraftSign.sanitizeLines(lines);
        SignBlockEntity sign = new SignBlockEntity(CraftLocation.toBlockPosition(loc), Blocks.f_50095_.m_49966_());
        SignText text = sign.m_277142_();
        text = text.m_276901_(net.minecraft.world.item.DyeColor.m_41053_((int)dyeColor.getWoolData()));
        text = text.m_277132_(hasGlowingText);
        int i = 0;
        while (i < components.length) {
            text = text.m_276913_(i, components[i]);
            ++i;
        }
        sign.m_276956_(text, true);
        this.getHandle().f_8906_.m_9829_((Packet)sign.m_58483_());
    }

    @Override
    public void sendBlockUpdate(@NotNull Location location, @NotNull TileState tileState) throws IllegalArgumentException {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location can not be null");
        Preconditions.checkArgument((tileState != null ? 1 : 0) != 0, (Object)"TileState can not be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        CraftBlockEntityState craftState = (CraftBlockEntityState)tileState;
        this.getHandle().f_8906_.m_9829_(craftState.getUpdatePacket(location));
    }

    @Override
    public void sendEquipmentChange(LivingEntity entity, EquipmentSlot slot, ItemStack item) {
        this.sendEquipmentChange(entity, Map.of(slot, item));
    }

    @Override
    public void sendEquipmentChange(LivingEntity entity, Map<EquipmentSlot, ItemStack> items) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        Preconditions.checkArgument((items != null ? 1 : 0) != 0, (Object)"items cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ArrayList<Pair> equipment = new ArrayList<Pair>(items.size());
        for (Map.Entry<EquipmentSlot, ItemStack> entry : items.entrySet()) {
            EquipmentSlot slot = entry.getKey();
            Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"Cannot set null EquipmentSlot");
            equipment.add(new Pair((Object)CraftEquipmentSlot.getNMS(slot), (Object)CraftItemStack.asNMSCopy(entry.getValue())));
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetEquipmentPacket(entity.getEntityId(), equipment));
    }

    @Override
    public WorldBorder getWorldBorder() {
        return this.clientWorldBorder;
    }

    @Override
    public void setWorldBorder(WorldBorder border) {
        net.minecraft.world.level.border.WorldBorder newWorldBorder;
        CraftWorldBorder craftBorder = (CraftWorldBorder)border;
        if (border != null && !craftBorder.isVirtual() && !craftBorder.getWorld().equals(this.getWorld())) {
            throw new UnsupportedOperationException("Cannot set player world border to that of another world");
        }
        if (this.clientWorldBorder != null) {
            this.clientWorldBorder.getHandle().m_156096_(this.clientWorldBorderListener);
        }
        if (craftBorder == null || !craftBorder.isVirtual()) {
            this.clientWorldBorder = null;
            newWorldBorder = ((CraftWorldBorder)this.getWorld().getWorldBorder()).getHandle();
        } else {
            this.clientWorldBorder = craftBorder;
            this.clientWorldBorder.getHandle().m_61929_(this.clientWorldBorderListener);
            newWorldBorder = this.clientWorldBorder.getHandle();
        }
        ServerGamePacketListenerImpl connection = this.getHandle().f_8906_;
        connection.m_9829_((Packet)new ClientboundSetBorderSizePacket(newWorldBorder));
        connection.m_9829_((Packet)new ClientboundSetBorderLerpSizePacket(newWorldBorder));
        connection.m_9829_((Packet)new ClientboundSetBorderCenterPacket(newWorldBorder));
        connection.m_9829_((Packet)new ClientboundSetBorderWarningDelayPacket(newWorldBorder));
        connection.m_9829_((Packet)new ClientboundSetBorderWarningDistancePacket(newWorldBorder));
    }

    private BorderChangeListener createWorldBorderListener() {
        return new BorderChangeListener(){

            public void m_6312_(net.minecraft.world.level.border.WorldBorder border, double size) {
                CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetBorderSizePacket(border));
            }

            public void m_6689_(net.minecraft.world.level.border.WorldBorder border, double size, double newSize, long time) {
                CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetBorderLerpSizePacket(border));
            }

            public void m_7721_(net.minecraft.world.level.border.WorldBorder border, double x, double z) {
                CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetBorderCenterPacket(border));
            }

            public void m_5904_(net.minecraft.world.level.border.WorldBorder border, int warningTime) {
                CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetBorderWarningDelayPacket(border));
            }

            public void m_5903_(net.minecraft.world.level.border.WorldBorder border, int warningBlocks) {
                CraftPlayer.this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetBorderWarningDistancePacket(border));
            }

            public void m_6315_(net.minecraft.world.level.border.WorldBorder border, double damage) {
            }

            public void m_6313_(net.minecraft.world.level.border.WorldBorder border, double blocks) {
            }
        };
    }

    public boolean hasClientWorldBorder() {
        return this.clientWorldBorder != null;
    }

    @Override
    public void sendMap(MapView map) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        RenderData data = ((CraftMapView)map).render(this);
        ArrayList<MapDecoration> icons = new ArrayList<MapDecoration>();
        for (MapCursor cursor : data.cursors) {
            if (!cursor.isVisible()) continue;
            icons.add(new MapDecoration(MapDecoration.Type.m_77854_((byte)cursor.getRawType()), cursor.getX(), cursor.getY(), cursor.getDirection(), CraftChatMessage.fromStringOrNull(cursor.getCaption())));
        }
        ClientboundMapItemDataPacket packet = new ClientboundMapItemDataPacket(map.getId(), map.getScale().getValue(), map.isLocked(), icons, new MapItemSavedData.MapPatch(0, 0, 128, 128, data.buffer));
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void sendHurtAnimation(float yaw) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        float actualYaw = yaw + 90.0f;
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundHurtAnimationPacket(this.getEntityId(), actualYaw));
    }

    @Override
    public void addCustomChatCompletions(Collection<String> completions) {
        this.sendCustomChatCompletionPacket(completions, ClientboundCustomChatCompletionsPacket.Action.ADD);
    }

    @Override
    public void removeCustomChatCompletions(Collection<String> completions) {
        this.sendCustomChatCompletionPacket(completions, ClientboundCustomChatCompletionsPacket.Action.REMOVE);
    }

    @Override
    public void setCustomChatCompletions(Collection<String> completions) {
        this.sendCustomChatCompletionPacket(completions, ClientboundCustomChatCompletionsPacket.Action.SET);
    }

    private void sendCustomChatCompletionPacket(Collection<String> completions, ClientboundCustomChatCompletionsPacket.Action action) {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundCustomChatCompletionsPacket packet = new ClientboundCustomChatCompletionsPacket(action, new ArrayList<String>(completions));
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        throw new UnsupportedOperationException("Cannot set rotation of players. Consider teleporting instead.");
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location");
        Preconditions.checkArgument((location.getWorld() != null ? 1 : 0) != 0, (Object)"location.world");
        location.checkFinite();
        ServerPlayer entity = this.getHandle();
        if (this.getHealth() == 0.0 || entity.m_213877_()) {
            return false;
        }
        if (entity.f_8906_ == null) {
            return false;
        }
        if (entity.m_20160_()) {
            return false;
        }
        Location from = this.getLocation();
        Location to = location;
        PlayerTeleportEvent event = new PlayerTeleportEvent(this, from, to, cause);
        this.server.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }
        entity.m_8127_();
        if (this.isSleeping()) {
            this.wakeup(false);
        }
        from = event.getFrom();
        to = event.getTo();
        ServerLevel fromWorld = ((CraftWorld)from.getWorld()).getHandle();
        ServerLevel toWorld = ((CraftWorld)to.getWorld()).getHandle();
        if (this.getHandle().f_36096_ != this.getHandle().f_36095_) {
            this.getHandle().m_6915_();
        }
        if (fromWorld == toWorld) {
            entity.f_8906_.teleport(to);
        } else {
            this.server.getHandle().respawn(entity, toWorld, true, to, true, null);
        }
        return true;
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.getHandle().m_20260_(sneak);
    }

    @Override
    public boolean isSneaking() {
        return this.getHandle().m_6144_();
    }

    @Override
    public boolean isSprinting() {
        return this.getHandle().m_20142_();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.getHandle().m_6858_(sprinting);
    }

    @Override
    public void loadData() {
        this.server.getHandle().f_11204_.m_78435_((Player)this.getHandle());
    }

    @Override
    public void saveData() {
        this.server.getHandle().f_11204_.m_78433_((Player)this.getHandle());
    }

    @Override
    @Deprecated
    public void updateInventory() {
        this.getHandle().f_36096_.m_150429_();
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        this.getHandle().fauxSleeping = isSleeping;
        ((CraftWorld)this.getWorld()).getHandle().m_8878_();
    }

    @Override
    public boolean isSleepingIgnored() {
        return this.getHandle().fauxSleeping;
    }

    @Override
    public Location getBedSpawnLocation() {
        Optional spawnLoc;
        ServerLevel world = this.getHandle().f_8924_.m_129880_(this.getHandle().m_8963_());
        BlockPos bed = this.getHandle().m_8961_();
        if (world != null && bed != null && (spawnLoc = Player.m_36130_((ServerLevel)world, (BlockPos)bed, (float)this.getHandle().m_8962_(), (boolean)this.getHandle().m_8964_(), (boolean)true)).isPresent()) {
            Vec3 vec = (Vec3)spawnLoc.get();
            return CraftLocation.toBukkit(vec, (World)world.getWorld(), this.getHandle().m_8962_(), 0.0f);
        }
        return null;
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        this.setBedSpawnLocation(location, false);
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean override) {
        if (location == null) {
            this.getHandle().setRespawnPosition(null, null, 0.0f, override, false, PlayerSpawnChangeEvent.Cause.PLUGIN);
        } else {
            this.getHandle().setRespawnPosition(((CraftWorld)location.getWorld()).getHandle().m_46472_(), CraftLocation.toBlockPosition(location), location.getYaw(), override, false, PlayerSpawnChangeEvent.Cause.PLUGIN);
        }
    }

    @Override
    public Location getBedLocation() {
        Preconditions.checkState((boolean)this.isSleeping(), (Object)"Not sleeping");
        BlockPos bed = this.getHandle().m_8961_();
        return CraftLocation.toBukkit(bed, this.getWorld());
    }

    @Override
    public boolean hasDiscoveredRecipe(NamespacedKey recipe) {
        Preconditions.checkArgument((recipe != null ? 1 : 0) != 0, (Object)"recipe cannot be null");
        return this.getHandle().m_8952_().m_12711_(CraftNamespacedKey.toMinecraft(recipe));
    }

    @Override
    public Set<NamespacedKey> getDiscoveredRecipes() {
        ImmutableSet.Builder bukkitRecipeKeys = ImmutableSet.builder();
        this.getHandle().m_8952_().f_12680_.forEach(key -> {
            ImmutableSet.Builder builder2 = bukkitRecipeKeys.add((Object)CraftNamespacedKey.fromMinecraft(key));
        });
        return bukkitRecipeKeys.build();
    }

    @Override
    public void incrementStatistic(Statistic statistic) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic);
    }

    @Override
    public void decrementStatistic(Statistic statistic) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic);
    }

    @Override
    public int getStatistic(Statistic statistic) {
        return CraftStatistic.getStatistic(this.getHandle().m_8951_(), statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, int amount) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, int newValue) {
        CraftStatistic.setStatistic(this.getHandle().m_8951_(), statistic, newValue);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic, material);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic, material);
    }

    @Override
    public int getStatistic(Statistic statistic, Material material) {
        return CraftStatistic.getStatistic(this.getHandle().m_8951_(), statistic, material);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic, material, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int amount) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic, material, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int newValue) {
        CraftStatistic.setStatistic(this.getHandle().m_8951_(), statistic, material, newValue);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic, entityType);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic, entityType);
    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) {
        return CraftStatistic.getStatistic(this.getHandle().m_8951_(), statistic, entityType);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) {
        CraftStatistic.incrementStatistic(this.getHandle().m_8951_(), statistic, entityType, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {
        CraftStatistic.decrementStatistic(this.getHandle().m_8951_(), statistic, entityType, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {
        CraftStatistic.setStatistic(this.getHandle().m_8951_(), statistic, entityType, newValue);
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        this.getHandle().timeOffset = time;
        this.getHandle().relativeTime = relative;
    }

    @Override
    public long getPlayerTimeOffset() {
        return this.getHandle().timeOffset;
    }

    @Override
    public long getPlayerTime() {
        return this.getHandle().getPlayerTime();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.getHandle().relativeTime;
    }

    @Override
    public void resetPlayerTime() {
        this.setPlayerTime(0L, true);
    }

    @Override
    public void setPlayerWeather(WeatherType type) {
        this.getHandle().setPlayerWeather(type, true);
    }

    @Override
    public WeatherType getPlayerWeather() {
        return this.getHandle().getPlayerWeather();
    }

    @Override
    public int getExpCooldown() {
        return this.getHandle().f_36101_;
    }

    @Override
    public void setExpCooldown(int ticks) {
        this.getHandle().f_36101_ = CraftEventFactory.callPlayerXpCooldownEvent((Player)this.getHandle(), ticks, PlayerExpCooldownChangeEvent.ChangeReason.PLUGIN).getNewCooldown();
    }

    @Override
    public void resetPlayerWeather() {
        this.getHandle().resetPlayerWeather();
    }

    @Override
    public boolean isBanned() {
        return ((ProfileBanList)this.server.getBanList(BanList.Type.PROFILE)).isBanned(this.getPlayerProfile());
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Date expires, String source) {
        return this.ban(reason, expires, source, true);
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Instant expires, String source) {
        return this.ban(reason, expires != null ? Date.from(expires) : null, source);
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Duration duration, String source) {
        return this.ban(reason, duration != null ? Instant.now().plus(duration) : null, source);
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Date expires, String source, boolean kickPlayer) {
        BanEntry<PlayerProfile> banEntry = ((ProfileBanList)this.server.getBanList(BanList.Type.PROFILE)).addBan(this.getPlayerProfile(), reason, expires, source);
        if (kickPlayer) {
            this.kickPlayer(reason);
        }
        return banEntry;
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Instant instant, String source, boolean kickPlayer) {
        return this.ban(reason, instant != null ? Date.from(instant) : null, source, kickPlayer);
    }

    @Override
    public BanEntry<PlayerProfile> ban(String reason, Duration duration, String source, boolean kickPlayer) {
        return this.ban(reason, duration != null ? Instant.now().plus(duration) : null, source, kickPlayer);
    }

    @Override
    public BanEntry<InetAddress> banIp(String reason, Date expires, String source, boolean kickPlayer) {
        Preconditions.checkArgument((this.getAddress() != null ? 1 : 0) != 0, (Object)"The Address of this Player is null");
        BanEntry<InetAddress> banEntry = ((IpBanList)this.server.getBanList(BanList.Type.IP)).addBan(this.getAddress().getAddress(), reason, expires, source);
        if (kickPlayer) {
            this.kickPlayer(reason);
        }
        return banEntry;
    }

    @Override
    public BanEntry<InetAddress> banIp(String reason, Instant instant, String source, boolean kickPlayer) {
        return this.banIp(reason, instant != null ? Date.from(instant) : null, source, kickPlayer);
    }

    @Override
    public BanEntry<InetAddress> banIp(String reason, Duration duration, String source, boolean kickPlayer) {
        return this.banIp(reason, duration != null ? Instant.now().plus(duration) : null, source, kickPlayer);
    }

    @Override
    public boolean isWhitelisted() {
        return this.server.getHandle().m_11305_().m_11453_(this.getProfile());
    }

    @Override
    public void setWhitelisted(boolean value) {
        if (value) {
            this.server.getHandle().m_11305_().m_11381_((StoredUserEntry)new UserWhiteListEntry(this.getProfile()));
        } else {
            this.server.getHandle().m_11305_().m_11393_((Object)this.getProfile());
        }
    }

    @Override
    public void setGameMode(GameMode mode) {
        Preconditions.checkArgument((mode != null ? 1 : 0) != 0, (Object)"GameMode cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().m_143403_(GameType.m_46393_((int)mode.getValue()));
    }

    @Override
    public GameMode getGameMode() {
        return GameMode.getByValue(this.getHandle().f_8941_.m_9290_().m_46392_());
    }

    @Override
    public GameMode getPreviousGameMode() {
        GameType previousGameMode = this.getHandle().f_8941_.m_9293_();
        return previousGameMode == null ? null : GameMode.getByValue(previousGameMode.m_46392_());
    }

    @Override
    public void giveExp(int exp) {
        this.getHandle().m_6756_(exp);
    }

    @Override
    public void giveExpLevels(int levels) {
        this.getHandle().m_6749_(levels);
    }

    @Override
    public float getExp() {
        return this.getHandle().f_36080_;
    }

    @Override
    public void setExp(float exp) {
        Preconditions.checkArgument(((double)exp >= 0.0 && (double)exp <= 1.0 ? 1 : 0) != 0, (String)"Experience progress must be between 0.0 and 1.0 (%s)", (Object)Float.valueOf(exp));
        this.getHandle().f_36080_ = exp;
        this.getHandle().f_8920_ = -1;
    }

    @Override
    public int getLevel() {
        return this.getHandle().f_36078_;
    }

    @Override
    public void setLevel(int level) {
        Preconditions.checkArgument((level >= 0 ? 1 : 0) != 0, (String)"Experience level must not be negative (%s)", (int)level);
        this.getHandle().f_36078_ = level;
        this.getHandle().f_8920_ = -1;
    }

    @Override
    public int getTotalExperience() {
        return this.getHandle().f_36079_;
    }

    @Override
    public void setTotalExperience(int exp) {
        Preconditions.checkArgument((exp >= 0 ? 1 : 0) != 0, (String)"Total experience points must not be negative (%s)", (int)exp);
        this.getHandle().f_36079_ = exp;
    }

    @Override
    public void sendExperienceChange(float progress) {
        this.sendExperienceChange(progress, this.getLevel());
    }

    @Override
    public void sendExperienceChange(float progress, int level) {
        Preconditions.checkArgument(((double)progress >= 0.0 && (double)progress <= 1.0 ? 1 : 0) != 0, (String)"Experience progress must be between 0.0 and 1.0 (%s)", (Object)Float.valueOf(progress));
        Preconditions.checkArgument((level >= 0 ? 1 : 0) != 0, (String)"Experience level must not be negative (%s)", (int)level);
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        ClientboundSetExperiencePacket packet = new ClientboundSetExperiencePacket(progress, this.getTotalExperience(), level);
        this.getHandle().f_8906_.m_9829_((Packet)packet);
    }

    @Nullable
    private static WeakReference<Plugin> getPluginWeakReference(@Nullable Plugin plugin) {
        return plugin == null ? null : pluginWeakReferences.computeIfAbsent(plugin, WeakReference::new);
    }

    @Override
    @Deprecated
    public void hidePlayer(org.bukkit.entity.Player player) {
        this.hideEntity0(null, player);
    }

    @Override
    public void hidePlayer(Plugin plugin, org.bukkit.entity.Player player) {
        this.hideEntity(plugin, player);
    }

    @Override
    public void hideEntity(Plugin plugin, Entity entity) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        Preconditions.checkArgument((boolean)plugin.isEnabled(), (String)"Plugin (%s) cannot be disabled", (Object)plugin.getName());
        this.hideEntity0(plugin, entity);
    }

    private void hideEntity0(@Nullable Plugin plugin, Entity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity hidden cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        if (this.equals(entity)) {
            return;
        }
        boolean shouldHide = entity.isVisibleByDefault() ? this.addInvertedVisibility(plugin, entity) : this.removeInvertedVisibility(plugin, entity);
        if (shouldHide) {
            this.untrackAndHideEntity(entity);
        }
    }

    private boolean addInvertedVisibility(@Nullable Plugin plugin, Entity entity) {
        Set<WeakReference<Plugin>> invertedPlugins = this.invertedVisibilityEntities.get(entity.getUniqueId());
        if (invertedPlugins != null) {
            invertedPlugins.add(CraftPlayer.getPluginWeakReference(plugin));
            return false;
        }
        invertedPlugins = new HashSet<WeakReference<Plugin>>();
        invertedPlugins.add(CraftPlayer.getPluginWeakReference(plugin));
        this.invertedVisibilityEntities.put(entity.getUniqueId(), invertedPlugins);
        return true;
    }

    private void untrackAndHideEntity(Entity entity) {
        ChunkMap tracker = ((ServerLevel)this.getHandle().m_9236_()).m_7726_().f_8325_;
        net.minecraft.world.entity.Entity other = ((CraftEntity)entity).getHandle();
        ChunkMap.TrackedEntity entry = (ChunkMap.TrackedEntity)tracker.f_140150_.get(other.m_19879_());
        if (entry != null) {
            entry.m_140485_(this.getHandle());
        }
        if (other instanceof ServerPlayer) {
            ServerPlayer otherPlayer = (ServerPlayer)other;
            if (otherPlayer.sentListPacket) {
                this.getHandle().f_8906_.m_9829_((Packet)new ClientboundPlayerInfoRemovePacket(List.of(otherPlayer.m_20148_())));
            }
        }
        this.server.getPluginManager().callEvent(new PlayerHideEntityEvent((org.bukkit.entity.Player)this, entity));
    }

    void resetAndHideEntity(Entity entity) {
        if (this.equals(entity)) {
            return;
        }
        if (this.invertedVisibilityEntities.remove(entity.getUniqueId()) == null) {
            this.untrackAndHideEntity(entity);
        }
    }

    @Override
    @Deprecated
    public void showPlayer(org.bukkit.entity.Player player) {
        this.showEntity0(null, player);
    }

    @Override
    public void showPlayer(Plugin plugin, org.bukkit.entity.Player player) {
        this.showEntity(plugin, player);
    }

    @Override
    public void showEntity(Plugin plugin, Entity entity) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        this.showEntity0(plugin, entity);
    }

    private void showEntity0(@Nullable Plugin plugin, Entity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity show cannot be null");
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        if (this.equals(entity)) {
            return;
        }
        boolean shouldShow = entity.isVisibleByDefault() ? this.removeInvertedVisibility(plugin, entity) : this.addInvertedVisibility(plugin, entity);
        if (shouldShow) {
            this.trackAndShowEntity(entity);
        }
    }

    private boolean removeInvertedVisibility(@Nullable Plugin plugin, Entity entity) {
        Set<WeakReference<Plugin>> invertedPlugins = this.invertedVisibilityEntities.get(entity.getUniqueId());
        if (invertedPlugins == null) {
            return false;
        }
        invertedPlugins.remove(CraftPlayer.getPluginWeakReference(plugin));
        if (!invertedPlugins.isEmpty()) {
            return false;
        }
        this.invertedVisibilityEntities.remove(entity.getUniqueId());
        return true;
    }

    private void trackAndShowEntity(Entity entity) {
        ChunkMap.TrackedEntity entry;
        ChunkMap tracker = ((ServerLevel)this.getHandle().m_9236_()).m_7726_().f_8325_;
        net.minecraft.world.entity.Entity other = ((CraftEntity)entity).getHandle();
        if (other instanceof ServerPlayer) {
            ServerPlayer otherPlayer = (ServerPlayer)other;
            this.getHandle().f_8906_.m_9829_((Packet)ClientboundPlayerInfoUpdatePacket.m_247122_(List.of(otherPlayer)));
        }
        if ((entry = (ChunkMap.TrackedEntity)tracker.f_140150_.get(other.m_19879_())) != null && !entry.f_140475_.contains(this.getHandle().f_8906_)) {
            entry.m_140497_(this.getHandle());
        }
        this.server.getPluginManager().callEvent(new PlayerShowEntityEvent((org.bukkit.entity.Player)this, entity));
    }

    void resetAndShowEntity(Entity entity) {
        if (this.equals(entity)) {
            return;
        }
        if (this.invertedVisibilityEntities.remove(entity.getUniqueId()) == null) {
            this.trackAndShowEntity(entity);
        }
    }

    public void onEntityRemove(net.minecraft.world.entity.Entity entity) {
        this.invertedVisibilityEntities.remove(entity.m_20148_());
    }

    @Override
    public boolean canSee(org.bukkit.entity.Player player) {
        return this.canSee((Entity)player);
    }

    @Override
    public boolean canSee(Entity entity) {
        return this.equals(entity) || entity.isVisibleByDefault() ^ this.invertedVisibilityEntities.containsKey(entity.getUniqueId());
    }

    public boolean canSee(UUID uuid) {
        Entity entity = this.getServer().getPlayer(uuid);
        if (entity == null) {
            entity = this.getServer().getEntity(uuid);
        }
        return entity != null ? this.canSee(entity) : false;
    }

    @Override
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("name", this.getName());
        return result;
    }

    @Override
    public org.bukkit.entity.Player getPlayer() {
        return this;
    }

    public ServerPlayer getHandle() {
        return (ServerPlayer)this.entity;
    }

    public void setHandle(ServerPlayer entity) {
        super.setHandle((Player)entity);
    }

    @Override
    public String toString() {
        return "CraftPlayer{name=" + this.getName() + '}';
    }

    @Override
    public int hashCode() {
        if (this.hash == 0 || this.hash == 485) {
            this.hash = 485 + (this.getUniqueId() != null ? this.getUniqueId().hashCode() : 0);
        }
        return this.hash;
    }

    @Override
    public long getFirstPlayed() {
        return this.firstPlayed;
    }

    @Override
    public long getLastPlayed() {
        return this.lastPlayed;
    }

    @Override
    public boolean hasPlayedBefore() {
        return this.hasPlayedBefore;
    }

    public void setFirstPlayed(long firstPlayed) {
        this.firstPlayed = firstPlayed;
    }

    public void readExtraData(CompoundTag nbttagcompound) {
        this.hasPlayedBefore = true;
        if (nbttagcompound.m_128441_("bukkit")) {
            CompoundTag data = nbttagcompound.m_128469_("bukkit");
            if (data.m_128441_("firstPlayed")) {
                this.firstPlayed = data.m_128454_("firstPlayed");
                this.lastPlayed = data.m_128454_("lastPlayed");
            }
            if (data.m_128441_("newExp")) {
                ServerPlayer handle = this.getHandle();
                handle.newExp = data.m_128451_("newExp");
                handle.newTotalExp = data.m_128451_("newTotalExp");
                handle.newLevel = data.m_128451_("newLevel");
                handle.expToDrop = data.m_128451_("expToDrop");
                handle.keepLevel = data.m_128471_("keepLevel");
            }
        }
    }

    public void setExtraData(CompoundTag nbttagcompound) {
        if (!nbttagcompound.m_128441_("bukkit")) {
            nbttagcompound.m_128365_("bukkit", (Tag)new CompoundTag());
        }
        CompoundTag data = nbttagcompound.m_128469_("bukkit");
        ServerPlayer handle = this.getHandle();
        data.m_128405_("newExp", handle.newExp);
        data.m_128405_("newTotalExp", handle.newTotalExp);
        data.m_128405_("newLevel", handle.newLevel);
        data.m_128405_("expToDrop", handle.expToDrop);
        data.m_128379_("keepLevel", handle.keepLevel);
        data.m_128356_("firstPlayed", this.getFirstPlayed());
        data.m_128356_("lastPlayed", System.currentTimeMillis());
        data.m_128359_("lastKnownName", handle.m_6302_());
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return this.conversationTracker.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        this.conversationTracker.abandonConversation(conversation, new ConversationAbandonedEvent(conversation, new ManuallyAbandonedConversationCanceller()));
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        this.conversationTracker.abandonConversation(conversation, details);
    }

    @Override
    public void acceptConversationInput(String input) {
        this.conversationTracker.acceptConversationInput(input);
    }

    @Override
    public boolean isConversing() {
        return this.conversationTracker.isConversing();
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message);
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        if (this.channels.contains(channel)) {
            channel = StandardMessenger.validateAndCorrectChannel(channel);
            ClientboundCustomPayloadPacket packet = new ClientboundCustomPayloadPacket(new ResourceLocation(channel), new FriendlyByteBuf(Unpooled.wrappedBuffer((byte[])message)));
            this.getHandle().f_8906_.m_9829_((Packet)packet);
        }
    }

    @Override
    public void setTexturePack(String url) {
        this.setResourcePack(url);
    }

    @Override
    public void setResourcePack(String url) {
        this.setResourcePack(url, null);
    }

    @Override
    public void setResourcePack(String url, byte[] hash) {
        this.setResourcePack(url, hash, false);
    }

    @Override
    public void setResourcePack(String url, byte[] hash, String prompt) {
        this.setResourcePack(url, hash, prompt, false);
    }

    @Override
    public void setResourcePack(String url, byte[] hash, boolean force) {
        this.setResourcePack(url, hash, null, force);
    }

    @Override
    public void setResourcePack(String url, byte[] hash, String prompt, boolean force) {
        Preconditions.checkArgument((url != null ? 1 : 0) != 0, (Object)"Resource pack URL cannot be null");
        if (hash != null) {
            Preconditions.checkArgument((hash.length == 20 ? 1 : 0) != 0, (String)"Resource pack hash should be 20 bytes long but was %s", (int)hash.length);
            this.getHandle().m_143408_(url, BaseEncoding.base16().lowerCase().encode(hash), force, CraftChatMessage.fromStringOrNull(prompt, true));
        } else {
            this.getHandle().m_143408_(url, "", force, CraftChatMessage.fromStringOrNull(prompt, true));
        }
    }

    public void addChannel(String channel) {
        Preconditions.checkState((this.channels.size() < 128 ? 1 : 0) != 0, (String)"Cannot register channel '%s'. Too many channels registered!", (Object)channel);
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        if (this.channels.add(channel)) {
            this.server.getPluginManager().callEvent(new PlayerRegisterChannelEvent((org.bukkit.entity.Player)this, channel));
        }
    }

    public void removeChannel(String channel) {
        if (this.channels.remove(channel = StandardMessenger.validateAndCorrectChannel(channel))) {
            this.server.getPluginManager().callEvent(new PlayerUnregisterChannelEvent((org.bukkit.entity.Player)this, channel));
        }
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return ImmutableSet.copyOf(this.channels);
    }

    public void sendSupportedChannels() {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        Set<String> listening = this.server.getMessenger().getIncomingChannels();
        if (!listening.isEmpty()) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            for (String channel : listening) {
                try {
                    stream.write(channel.getBytes("UTF8"));
                    stream.write(0);
                }
                catch (IOException ex) {
                    Logger.getLogger(CraftPlayer.class.getName()).log(Level.SEVERE, "Could not send Plugin Channel REGISTER to " + this.getName(), ex);
                }
            }
            this.getHandle().f_8906_.m_9829_((Packet)new ClientboundCustomPayloadPacket(new ResourceLocation("register"), new FriendlyByteBuf(Unpooled.wrappedBuffer((byte[])stream.toByteArray()))));
        }
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.server.getPlayerMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.server.getPlayerMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.server.getPlayerMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.server.getPlayerMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        AbstractContainerMenu container = this.getHandle().f_36096_;
        if (container.getBukkitView().getType() != prop.getType()) {
            return false;
        }
        container.m_7511_(prop.getId(), value);
        return true;
    }

    public void disconnect(String reason) {
        this.conversationTracker.abandonAllConversations();
        this.perm.clearPermissions();
    }

    @Override
    public boolean isFlying() {
        return this.getHandle().m_150110_().f_35935_;
    }

    @Override
    public void setFlying(boolean value) {
        if (!this.getAllowFlight()) {
            Preconditions.checkArgument((!value ? 1 : 0) != 0, (Object)"Player is not allowed to fly (check #getAllowFlight())");
        }
        this.getHandle().m_150110_().f_35935_ = value;
        this.getHandle().m_6885_();
    }

    @Override
    public boolean getAllowFlight() {
        return this.getHandle().m_150110_().f_35936_;
    }

    @Override
    public void setAllowFlight(boolean value) {
        if (this.isFlying() && !value) {
            this.getHandle().m_150110_().f_35935_ = false;
        }
        this.getHandle().m_150110_().f_35936_ = value;
        this.getHandle().m_6885_();
    }

    @Override
    public int getNoDamageTicks() {
        if (this.getHandle().f_8921_ > 0) {
            return Math.max(this.getHandle().f_8921_, this.getHandle().f_19802_);
        }
        return this.getHandle().f_19802_;
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        super.setNoDamageTicks(ticks);
        this.getHandle().f_8921_ = ticks;
    }

    @Override
    public void setFlySpeed(float value) {
        this.validateSpeed(value);
        ServerPlayer player = this.getHandle();
        player.m_150110_().f_35939_ = value / 2.0f;
        player.m_6885_();
    }

    @Override
    public void setWalkSpeed(float value) {
        this.validateSpeed(value);
        ServerPlayer player = this.getHandle();
        player.m_150110_().f_35940_ = value / 2.0f;
        player.m_6885_();
        this.getHandle().m_21051_(Attributes.f_22279_).m_22100_((double)player.m_150110_().f_35940_);
    }

    @Override
    public float getFlySpeed() {
        return this.getHandle().m_150110_().f_35939_ * 2.0f;
    }

    @Override
    public float getWalkSpeed() {
        return this.getHandle().m_150110_().f_35940_ * 2.0f;
    }

    private void validateSpeed(float value) {
        Preconditions.checkArgument((value <= 1.0f && value >= -1.0f ? 1 : 0) != 0, (String)"Speed value (%s) need to be between -1f and 1f", (Object)Float.valueOf(value));
    }

    @Override
    public void setMaxHealth(double amount) {
        super.setMaxHealth(amount);
        this.health = Math.min(this.health, amount);
        this.getHandle().m_9233_();
    }

    @Override
    public void resetMaxHealth() {
        super.resetMaxHealth();
        this.getHandle().m_9233_();
    }

    @Override
    public CraftScoreboard getScoreboard() {
        return this.server.getScoreboardManager().getPlayerBoard(this);
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) {
        Preconditions.checkArgument((scoreboard != null ? 1 : 0) != 0, (Object)"Scoreboard cannot be null");
        Preconditions.checkState((this.getHandle().f_8906_ != null ? 1 : 0) != 0, (Object)"Cannot set scoreboard yet (invalid player connection)");
        this.server.getScoreboardManager().setPlayerBoard(this, scoreboard);
    }

    @Override
    public void setHealthScale(double value) {
        Preconditions.checkArgument((value > 0.0 ? 1 : 0) != 0, (String)"Health value (%s) must be greater than 0", (Object)value);
        this.healthScale = value;
        this.scaledHealth = true;
        this.updateScaledHealth();
    }

    @Override
    public double getHealthScale() {
        return this.healthScale;
    }

    @Override
    public void setHealthScaled(boolean scale) {
        this.scaledHealth = scale;
        if (this.scaledHealth != this.scaledHealth) {
            this.updateScaledHealth();
        }
    }

    @Override
    public boolean isHealthScaled() {
        return this.scaledHealth;
    }

    public float getScaledHealth() {
        return (float)(this.isHealthScaled() ? this.getHealth() * this.getHealthScale() / this.getMaxHealth() : this.getHealth());
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    public void setRealHealth(double health) {
        this.health = health;
    }

    public void updateScaledHealth() {
        this.updateScaledHealth(true);
    }

    public void updateScaledHealth(boolean sendHealth) {
        AttributeMap attributemapserver = this.getHandle().m_21204_();
        Collection set = attributemapserver.m_22170_();
        this.injectScaledMaxHealth(set, true);
        if (this.getHandle().f_8906_ != null) {
            this.getHandle().f_8906_.m_9829_((Packet)new ClientboundUpdateAttributesPacket(this.getHandle().m_19879_(), set));
            if (sendHealth) {
                this.sendHealthUpdate();
            }
        }
        this.getHandle().m_20088_().m_135381_(net.minecraft.world.entity.LivingEntity.f_20961_, (Object)Float.valueOf(this.getScaledHealth()));
        this.getHandle().maxHealthCache = this.getMaxHealth();
    }

    @Override
    public void sendHealthUpdate(double health, int foodLevel, float saturation) {
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundSetHealthPacket((float)health, foodLevel, saturation));
    }

    @Override
    public void sendHealthUpdate() {
        FoodData foodData = this.getHandle().m_36324_();
        this.sendHealthUpdate(this.getScaledHealth(), foodData.m_38702_(), foodData.m_38722_());
    }

    public void injectScaledMaxHealth(Collection<AttributeInstance> collection, boolean force) {
        double healthMod;
        if (!this.scaledHealth && !force) {
            return;
        }
        for (AttributeInstance genericInstance : collection) {
            if (genericInstance.m_22099_() != Attributes.f_22276_) continue;
            collection.remove(genericInstance);
            break;
        }
        AttributeInstance dummy = new AttributeInstance(Attributes.f_22276_, attribute -> {});
        double d = healthMod = this.scaledHealth ? this.healthScale : this.getMaxHealth();
        if (healthMod >= 3.4028234663852886E38 || healthMod <= 0.0) {
            healthMod = 20.0;
            this.getServer().getLogger().warning(String.valueOf(this.getName()) + " tried to crash the server with a large health attribute");
        }
        dummy.m_22100_(healthMod);
        collection.add(dummy);
    }

    @Override
    public Entity getSpectatorTarget() {
        net.minecraft.world.entity.Entity followed = this.getHandle().m_8954_();
        return followed == this.getHandle() ? null : followed.getBukkitEntity();
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        Preconditions.checkArgument((this.getGameMode() == GameMode.SPECTATOR ? 1 : 0) != 0, (Object)"Player must be in spectator mode");
        this.getHandle().m_9213_(entity == null ? null : ((CraftEntity)entity).getHandle());
    }

    @Override
    public void sendTitle(String title, String subtitle) {
        this.sendTitle(title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        ClientboundSetTitlesAnimationPacket times = new ClientboundSetTitlesAnimationPacket(fadeIn, stay, fadeOut);
        this.getHandle().f_8906_.m_9829_((Packet)times);
        if (title != null) {
            ClientboundSetTitleTextPacket packetTitle = new ClientboundSetTitleTextPacket(CraftChatMessage.fromString(title)[0]);
            this.getHandle().f_8906_.m_9829_((Packet)packetTitle);
        }
        if (subtitle != null) {
            ClientboundSetSubtitleTextPacket packetSubtitle = new ClientboundSetSubtitleTextPacket(CraftChatMessage.fromString(subtitle)[0]);
            this.getHandle().f_8906_.m_9829_((Packet)packetSubtitle);
        }
    }

    @Override
    public void resetTitle() {
        ClientboundClearTitlesPacket packetReset = new ClientboundClearTitlesPacket(true);
        this.getHandle().f_8906_.m_9829_((Packet)packetReset);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
        this.spawnParticle(particle, x, y, z, count, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
        this.spawnParticle(particle, x, y, z, count, 0.0, 0.0, 0.0, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1.0, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        if (data != null) {
            Preconditions.checkArgument((boolean)particle.getDataType().isInstance(data), (String)"data (%s) should be %s", data.getClass(), particle.getDataType());
        }
        ClientboundLevelParticlesPacket packetplayoutworldparticles = new ClientboundLevelParticlesPacket(CraftParticle.toNMS(particle, data), true, (double)((float)x), (double)((float)y), (double)((float)z), (float)offsetX, (float)offsetY, (float)offsetZ, (float)extra, count);
        this.getHandle().f_8906_.m_9829_((Packet)packetplayoutworldparticles);
    }

    @Override
    public org.bukkit.advancement.AdvancementProgress getAdvancementProgress(Advancement advancement) {
        Preconditions.checkArgument((advancement != null ? 1 : 0) != 0, (Object)"advancement");
        CraftAdvancement craft = (CraftAdvancement)advancement;
        PlayerAdvancements data = this.getHandle().m_8960_();
        AdvancementProgress progress = data.m_135996_(craft.getHandle());
        return new CraftAdvancementProgress(craft, data, progress);
    }

    @Override
    public int getClientViewDistance() {
        return this.getHandle().clientViewDistance == null ? Bukkit.getViewDistance() : this.getHandle().clientViewDistance;
    }

    @Override
    public int getPing() {
        return this.getHandle().f_8943_;
    }

    @Override
    public String getLocale() {
        return this.getHandle().locale;
    }

    @Override
    public void updateCommands() {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8924_.m_129892_().m_82095_(this.getHandle());
    }

    @Override
    public void openBook(ItemStack book) {
        Preconditions.checkArgument((book != null ? 1 : 0) != 0, (Object)"ItemStack cannot be null");
        Preconditions.checkArgument((book.getType() == Material.WRITTEN_BOOK ? 1 : 0) != 0, (String)"ItemStack Material (%s) must be Material.WRITTEN_BOOK", (Object)book.getType());
        ItemStack hand = this.getInventory().getItemInMainHand();
        this.getInventory().setItemInMainHand(book);
        this.getHandle().m_6986_(CraftItemStack.asNMSCopy(book), InteractionHand.MAIN_HAND);
        this.getInventory().setItemInMainHand(hand);
    }

    @Override
    public void openSign(Sign sign) {
        this.openSign(sign, Side.FRONT);
    }

    @Override
    public void openSign(@NotNull Sign sign, @NotNull Side side) {
        CraftSign.openSign(sign, this, side);
    }

    @Override
    public void showDemoScreen() {
        if (this.getHandle().f_8906_ == null) {
            return;
        }
        this.getHandle().f_8906_.m_9829_((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.f_132158_, 0.0f));
    }

    @Override
    public boolean isAllowingServerListings() {
        return this.getHandle().m_184128_();
    }

    @Override
    public Player.Spigot spigot() {
        return this.spigot;
    }

    private static final class ChunkSectionChanges
    extends Record {
        private final ShortSet positions;
        private final List<BlockState> blockData;

        public ChunkSectionChanges() {
            this((ShortSet)new ShortArraySet(), new ArrayList());
        }

        public ShortSet positions() {
            return this.positions;
        }

        public List<BlockState> blockData() {
            return this.blockData;
        }

        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{ChunkSectionChanges.class, "positions;blockData", "positions", "blockData"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{ChunkSectionChanges.class, "positions;blockData", "positions", "blockData"}, this);
        }

        @Override
        public final boolean equals(Object object) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{ChunkSectionChanges.class, "positions;blockData", "positions", "blockData"}, this, object);
        }

        private ChunkSectionChanges(ShortSet shortSet, List list) {
            this.positions = shortSet;
            this.blockData = list;
        }
    }
}

