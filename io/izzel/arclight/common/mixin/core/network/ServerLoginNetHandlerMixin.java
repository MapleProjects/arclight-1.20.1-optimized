/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  io.izzel.arclight.i18n.ArclightConfig
 *  javax.annotation.Nullable
 *  net.minecraft.DefaultUncaughtExceptionHandler
 *  net.minecraft.core.UUIDUtil
 *  net.minecraft.network.Connection
 *  net.minecraft.network.PacketSendListener
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundDisconnectPacket
 *  net.minecraft.network.protocol.login.ClientboundGameProfilePacket
 *  net.minecraft.network.protocol.login.ClientboundHelloPacket
 *  net.minecraft.network.protocol.login.ClientboundLoginCompressionPacket
 *  net.minecraft.network.protocol.login.ServerboundHelloPacket
 *  net.minecraft.network.protocol.login.ServerboundKeyPacket
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerLoginPacketListenerImpl
 *  net.minecraft.server.network.ServerLoginPacketListenerImpl$State
 *  net.minecraft.util.Crypt
 *  net.minecraft.util.CryptException
 *  net.minecraftforge.fml.util.thread.SidedThreadGroups
 *  org.apache.commons.lang3.Validate
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.izzel.arclight.common.bridge.core.network.NetworkManagerBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.bridge.core.server.management.PlayerListBridge;
import io.izzel.arclight.i18n.ArclightConfig;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import net.minecraft.DefaultUncaughtExceptionHandler;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.Connection;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.login.ClientboundGameProfilePacket;
import net.minecraft.network.protocol.login.ClientboundHelloPacket;
import net.minecraft.network.protocol.login.ClientboundLoginCompressionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import net.minecraft.network.protocol.login.ServerboundKeyPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import net.minecraft.util.Crypt;
import net.minecraft.util.CryptException;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.util.Waitable;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ServerLoginPacketListenerImpl.class})
public abstract class ServerLoginNetHandlerMixin {
    @Shadow
    private ServerLoginPacketListenerImpl.State f_10019_;
    @Shadow
    @Final
    private MinecraftServer f_10018_;
    @Shadow
    @Final
    public Connection f_10013_;
    @Shadow
    @Final
    private static AtomicInteger f_10014_;
    @Shadow
    private GameProfile f_10021_;
    @Shadow
    @Final
    private static Logger f_10015_;
    @Shadow
    private ServerPlayer f_10024_;
    @Shadow
    @Final
    private byte[] f_252396_;

    @Shadow
    protected abstract GameProfile m_10038_(GameProfile var1);

    @Shadow
    public abstract void m_10053_(Component var1);

    @Shadow
    public abstract String m_10056_();

    public void disconnect(String s) {
        this.m_10053_((Component)Component.m_237113_((String)s));
    }

    @Overwrite
    public void m_10055_() {
        ServerPlayer entity;
        if (!this.f_10018_.m_129797_()) {
            // empty if block
        }
        if ((entity = ((PlayerListBridge)this.f_10018_.m_6846_()).bridge$canPlayerLogin(this.f_10013_.m_129523_(), this.f_10021_, (ServerLoginPacketListenerImpl)this)) != null) {
            this.f_10019_ = ServerLoginPacketListenerImpl.State.ACCEPTED;
            if (this.f_10018_.m_6328_() >= 0 && !this.f_10013_.m_129531_()) {
                this.f_10013_.m_243124_((Packet)new ClientboundLoginCompressionPacket(this.f_10018_.m_6328_()), PacketSendListener.m_243092_(() -> this.f_10013_.m_129484_(this.f_10018_.m_6328_(), true)));
            }
            this.f_10013_.m_129512_((Packet)new ClientboundGameProfilePacket(this.f_10021_));
            ServerPlayer serverplayerentity = this.f_10018_.m_6846_().m_11259_(this.f_10021_.getId());
            try {
                if (serverplayerentity != null) {
                    this.f_10019_ = ServerLoginPacketListenerImpl.State.DELAY_ACCEPT;
                    this.f_10024_ = entity;
                } else {
                    this.f_10018_.m_6846_().m_11261_(this.f_10013_, entity);
                }
            }
            catch (Exception exception) {
                f_10015_.error("Couldn't place player in world", (Throwable)exception);
                MutableComponent chatmessage = Component.m_237115_((String)"multiplayer.disconnect.invalid_player_data");
                this.f_10013_.m_129512_((Packet)new ClientboundDisconnectPacket((Component)chatmessage));
                this.f_10013_.m_129507_((Component)chatmessage);
            }
        }
    }

    private static boolean arclight$validUsernameCheck(String name) {
        String regex = ArclightConfig.spec().getCompat().getValidUsernameRegex();
        return !regex.isBlank() && name.matches(regex);
    }

    @Overwrite
    public void m_5990_(ServerboundHelloPacket packetIn) {
        Validate.validState((this.f_10019_ == ServerLoginPacketListenerImpl.State.HELLO ? 1 : 0) != 0, (String)"Unexpected hello packet", (Object[])new Object[0]);
        Validate.validState((this.f_10019_ == ServerLoginPacketListenerImpl.State.HELLO ? 1 : 0) != 0, (String)"Unexpected hello packet", (Object[])new Object[0]);
        Validate.validState((ServerLoginNetHandlerMixin.arclight$validUsernameCheck(packetIn.f_238040_()) || ServerLoginPacketListenerImpl.m_203792_((String)packetIn.f_238040_()) ? 1 : 0) != 0, (String)"Invalid characters in username", (Object[])new Object[0]);
        GameProfile gameprofile = this.f_10018_.m_236731_();
        if (gameprofile != null && packetIn.f_238040_().equalsIgnoreCase(gameprofile.getName())) {
            this.f_10021_ = gameprofile;
            this.f_10019_ = ServerLoginPacketListenerImpl.State.NEGOTIATING;
        } else {
            this.f_10021_ = new GameProfile(null, packetIn.f_238040_());
            if (this.f_10018_.m_129797_() && !this.f_10013_.m_129531_()) {
                this.f_10019_ = ServerLoginPacketListenerImpl.State.KEY;
                this.f_10013_.m_129512_((Packet)new ClientboundHelloPacket("", this.f_10018_.m_129790_().getPublic().getEncoded(), this.f_252396_));
            } else {
                class Handler
                extends Thread {
                    Handler() {
                        super((ThreadGroup)SidedThreadGroups.SERVER, "User Authenticator #" + f_10014_.incrementAndGet());
                    }

                    @Override
                    public void run() {
                        try {
                            ServerLoginNetHandlerMixin.this.initUUID();
                            ServerLoginNetHandlerMixin.this.arclight$preLogin();
                        }
                        catch (Exception ex) {
                            ServerLoginNetHandlerMixin.this.disconnect("Failed to verify username!");
                            f_10015_.warn("Exception verifying {} ", (Object)ServerLoginNetHandlerMixin.this.f_10021_.getName(), (Object)ex);
                        }
                    }
                }
                new Handler().start();
            }
        }
    }

    public void initUUID() {
        UUID uuid = ((NetworkManagerBridge)this.f_10013_).bridge$getSpoofedUUID() != null ? ((NetworkManagerBridge)this.f_10013_).bridge$getSpoofedUUID() : UUIDUtil.m_235879_((String)this.f_10021_.getName());
        this.f_10021_ = new GameProfile(uuid, this.f_10021_.getName());
        if (((NetworkManagerBridge)this.f_10013_).bridge$getSpoofedProfile() != null) {
            for (Property property : ((NetworkManagerBridge)this.f_10013_).bridge$getSpoofedProfile()) {
                this.f_10021_.getProperties().put((Object)property.getName(), (Object)property);
            }
        }
    }

    @Overwrite
    public void m_8072_(ServerboundKeyPacket packetIn) {
        String s;
        Validate.validState((this.f_10019_ == ServerLoginPacketListenerImpl.State.KEY ? 1 : 0) != 0, (String)"Unexpected key packet", (Object[])new Object[0]);
        try {
            PrivateKey privatekey = this.f_10018_.m_129790_().getPrivate();
            if (!packetIn.m_253194_(this.f_252396_, privatekey)) {
                throw new IllegalStateException("Protocol error");
            }
            SecretKey secretKey = packetIn.m_134859_(privatekey);
            Cipher cipher = Crypt.m_13583_((int)2, (Key)secretKey);
            Cipher cipher1 = Crypt.m_13583_((int)1, (Key)secretKey);
            s = new BigInteger(Crypt.m_13590_((String)"", (PublicKey)this.f_10018_.m_129790_().getPublic(), (SecretKey)secretKey)).toString(16);
            this.f_10019_ = ServerLoginPacketListenerImpl.State.AUTHENTICATING;
            this.f_10013_.m_129495_(cipher, cipher1);
        }
        catch (CryptException cryptexception) {
            throw new IllegalStateException("Protocol error", cryptexception);
        }
        class Handler
        extends Thread {
            Handler(int i) {
                super((ThreadGroup)SidedThreadGroups.SERVER, "User Authenticator #" + i);
            }

            @Override
            public void run() {
                GameProfile gameprofile = ServerLoginNetHandlerMixin.this.f_10021_;
                try {
                    ServerLoginNetHandlerMixin.this.f_10021_ = ServerLoginNetHandlerMixin.this.f_10018_.m_129925_().hasJoinedServer(new GameProfile(null, gameprofile.getName()), s, this.getAddress());
                    if (ServerLoginNetHandlerMixin.this.f_10021_ != null) {
                        if (!ServerLoginNetHandlerMixin.this.f_10013_.m_129536_()) {
                            return;
                        }
                        ServerLoginNetHandlerMixin.this.arclight$preLogin();
                    } else if (ServerLoginNetHandlerMixin.this.f_10018_.m_129792_()) {
                        f_10015_.warn("Failed to verify username but will let them in anyway!");
                        ServerLoginNetHandlerMixin.this.f_10021_ = ServerLoginNetHandlerMixin.this.m_10038_(gameprofile);
                        ServerLoginNetHandlerMixin.this.f_10019_ = ServerLoginPacketListenerImpl.State.NEGOTIATING;
                    } else {
                        ServerLoginNetHandlerMixin.this.m_10053_((Component)Component.m_237115_((String)"multiplayer.disconnect.unverified_username"));
                        f_10015_.error("Username '{}' tried to join with an invalid session", (Object)gameprofile.getName());
                    }
                }
                catch (Exception var3) {
                    if (ServerLoginNetHandlerMixin.this.f_10018_.m_129792_()) {
                        f_10015_.warn("Authentication servers are down but will let them in anyway!");
                        ServerLoginNetHandlerMixin.this.f_10021_ = ServerLoginNetHandlerMixin.this.m_10038_(gameprofile);
                        ServerLoginNetHandlerMixin.this.f_10019_ = ServerLoginPacketListenerImpl.State.NEGOTIATING;
                    }
                    ServerLoginNetHandlerMixin.this.m_10053_((Component)Component.m_237115_((String)"multiplayer.disconnect.authservers_down"));
                    f_10015_.error("Couldn't verify username because servers are unavailable");
                }
            }

            @Nullable
            private InetAddress getAddress() {
                SocketAddress socketaddress = ServerLoginNetHandlerMixin.this.f_10013_.m_129523_();
                return ServerLoginNetHandlerMixin.this.f_10018_.m_129798_() && socketaddress instanceof InetSocketAddress ? ((InetSocketAddress)socketaddress).getAddress() : null;
            }
        }
        Handler thread = new Handler(f_10014_.incrementAndGet());
        thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new DefaultUncaughtExceptionHandler(f_10015_));
        thread.start();
    }

    void arclight$preLogin() throws Exception {
        String playerName = this.f_10021_.getName();
        InetAddress address = ((InetSocketAddress)this.f_10013_.m_129523_()).getAddress();
        UUID uniqueId = this.f_10021_.getId();
        final CraftServer craftServer = (CraftServer)Bukkit.getServer();
        AsyncPlayerPreLoginEvent asyncEvent = new AsyncPlayerPreLoginEvent(playerName, address, uniqueId);
        craftServer.getPluginManager().callEvent(asyncEvent);
        if (PlayerPreLoginEvent.getHandlerList().getRegisteredListeners().length != 0) {
            final PlayerPreLoginEvent event = new PlayerPreLoginEvent(playerName, address, uniqueId);
            if (asyncEvent.getResult() != PlayerPreLoginEvent.Result.ALLOWED) {
                event.disallow(asyncEvent.getResult(), asyncEvent.getKickMessage());
            }
            class SyncPreLogin
            extends Waitable<PlayerPreLoginEvent.Result> {
                SyncPreLogin() {
                }

                @Override
                protected PlayerPreLoginEvent.Result evaluate() {
                    craftServer.getPluginManager().callEvent(event);
                    return event.getResult();
                }
            }
            SyncPreLogin waitable = new SyncPreLogin();
            ((MinecraftServerBridge)this.f_10018_).bridge$queuedProcess(waitable);
            if (waitable.get() != PlayerPreLoginEvent.Result.ALLOWED) {
                this.disconnect(event.getKickMessage());
                return;
            }
        } else if (asyncEvent.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            this.disconnect(asyncEvent.getKickMessage());
            return;
        }
        f_10015_.info("UUID of player {} is {}", (Object)this.f_10021_.getName(), (Object)this.f_10021_.getId());
        this.f_10019_ = ServerLoginPacketListenerImpl.State.NEGOTIATING;
    }
}

