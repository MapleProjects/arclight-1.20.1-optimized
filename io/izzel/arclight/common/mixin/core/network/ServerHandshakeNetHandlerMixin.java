/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.mojang.authlib.properties.Property
 *  com.mojang.util.UUIDTypeAdapter
 *  net.minecraft.SharedConstants
 *  net.minecraft.network.Connection
 *  net.minecraft.network.ConnectionProtocol
 *  net.minecraft.network.PacketListener
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.handshake.ClientIntentionPacket
 *  net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket
 *  net.minecraft.network.protocol.status.ServerStatus
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.network.ServerHandshakePacketListenerImpl
 *  net.minecraft.server.network.ServerLoginPacketListenerImpl
 *  net.minecraft.server.network.ServerStatusPacketListenerImpl
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.apache.logging.log4j.LogManager
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network;

import com.google.gson.Gson;
import com.mojang.authlib.properties.Property;
import com.mojang.util.UUIDTypeAdapter;
import io.izzel.arclight.common.bridge.core.network.NetworkManagerBridge;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.HashMap;
import net.minecraft.SharedConstants;
import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ClientboundLoginDisconnectPacket;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerHandshakePacketListenerImpl;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ServerHandshakePacketListenerImpl.class})
public class ServerHandshakeNetHandlerMixin {
    private static final Gson gson = new Gson();
    private static final HashMap<InetAddress, Long> throttleTracker = new HashMap();
    private static int throttleCounter = 0;
    @Shadow
    @Final
    private Connection f_9966_;
    @Shadow
    @Final
    private MinecraftServer f_9965_;
    @Shadow
    @Final
    private static Component f_9964_;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Overwrite
    public void m_7322_(ClientIntentionPacket packetIn) {
        if (!ServerLifecycleHooks.handleServerLogin((ClientIntentionPacket)packetIn, (Connection)this.f_9966_)) {
            return;
        }
        ((NetworkManagerBridge)this.f_9966_).bridge$setHostname(packetIn.f_134721_ + ":" + packetIn.f_134722_);
        switch (packetIn.m_134735_()) {
            case LOGIN: {
                this.f_9966_.m_129498_(ConnectionProtocol.LOGIN);
                try {
                    long currentTime = System.currentTimeMillis();
                    long connectionThrottle = Bukkit.getServer().getConnectionThrottle();
                    InetAddress address = ((InetSocketAddress)this.f_9966_.m_129523_()).getAddress();
                    HashMap<InetAddress, Long> hashMap = throttleTracker;
                    synchronized (hashMap) {
                        if (throttleTracker.containsKey(address) && !"127.0.0.1".equals(address.getHostAddress()) && currentTime - throttleTracker.get(address) < connectionThrottle) {
                            throttleTracker.put(address, currentTime);
                            MutableComponent component = Component.m_237115_((String)"Connection throttled! Please wait before reconnecting.");
                            this.f_9966_.m_129512_((Packet)new ClientboundLoginDisconnectPacket((Component)component));
                            this.f_9966_.m_129507_((Component)component);
                            return;
                        }
                        throttleTracker.put(address, currentTime);
                        if (++throttleCounter > 200) {
                            throttleCounter = 0;
                            throttleTracker.entrySet().removeIf(entry -> (Long)entry.getValue() > connectionThrottle);
                        }
                    }
                }
                catch (Throwable t) {
                    LogManager.getLogger().debug("Failed to check connection throttle", t);
                }
                if (packetIn.m_134738_() > SharedConstants.m_183709_().m_132495_()) {
                    MutableComponent component = Component.m_237115_((String)MessageFormat.format(SpigotConfig.outdatedServerMessage.replaceAll("'", "''"), SharedConstants.m_183709_().m_132493_()));
                    this.f_9966_.m_129512_((Packet)new ClientboundLoginDisconnectPacket((Component)component));
                    this.f_9966_.m_129507_((Component)component);
                    break;
                }
                if (packetIn.m_134738_() < SharedConstants.m_183709_().m_132495_()) {
                    MutableComponent component = Component.m_237115_((String)MessageFormat.format(SpigotConfig.outdatedClientMessage.replaceAll("'", "''"), SharedConstants.m_183709_().m_132493_()));
                    this.f_9966_.m_129512_((Packet)new ClientboundLoginDisconnectPacket((Component)component));
                    this.f_9966_.m_129507_((Component)component);
                    break;
                }
                this.f_9966_.m_129505_((PacketListener)new ServerLoginPacketListenerImpl(this.f_9965_, this.f_9966_));
                if (!SpigotConfig.bungee) break;
                String[] split = packetIn.f_134721_.split("\u0000");
                if (split.length != 3 && split.length != 4) {
                    MutableComponent component = Component.m_237113_((String)"If you wish to use IP forwarding, please enable it in your BungeeCord config as well!");
                    this.f_9966_.m_129512_((Packet)new ClientboundLoginDisconnectPacket((Component)component));
                    this.f_9966_.m_129507_((Component)component);
                    return;
                }
                packetIn.f_134721_ = split[0];
                this.f_9966_.f_129469_ = new InetSocketAddress(split[1], ((InetSocketAddress)this.f_9966_.m_129523_()).getPort());
                ((NetworkManagerBridge)this.f_9966_).bridge$setSpoofedUUID(UUIDTypeAdapter.fromString((String)split[2]));
                if (split.length != 4) break;
                ((NetworkManagerBridge)this.f_9966_).bridge$setSpoofedProfile((Property[])gson.fromJson(split[3], Property[].class));
                break;
            }
            case STATUS: {
                ServerStatus serverstatus = this.f_9965_.m_129928_();
                if (this.f_9965_.m_6373_() && serverstatus != null) {
                    this.f_9966_.m_129498_(ConnectionProtocol.STATUS);
                    this.f_9966_.m_129505_((PacketListener)new ServerStatusPacketListenerImpl(serverstatus, this.f_9966_));
                    break;
                }
                this.f_9966_.m_129507_(f_9964_);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Invalid intention " + String.valueOf(packetIn.m_134735_()));
            }
        }
    }
}

