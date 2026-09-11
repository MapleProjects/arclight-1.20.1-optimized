/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.SharedConstants
 *  net.minecraft.network.Connection
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.status.ClientboundStatusResponsePacket
 *  net.minecraft.network.protocol.status.ServerStatus
 *  net.minecraft.network.protocol.status.ServerStatus$Favicon
 *  net.minecraft.network.protocol.status.ServerStatus$Players
 *  net.minecraft.network.protocol.status.ServerStatus$Version
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerStatusPacketListenerImpl
 *  net.minecraftforge.network.ServerStatusPing
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.network;

import io.izzel.arclight.common.mod.util.ArclightPingEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.minecraft.SharedConstants;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.status.ClientboundStatusResponsePacket;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerStatusPacketListenerImpl;
import net.minecraftforge.network.ServerStatusPing;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.spigotmc.SpigotConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerStatusPacketListenerImpl.class})
public class ServerStatusNetHandlerMixin {
    @Redirect(method={"handleStatusRequest"}, at=@At(value="INVOKE", target="Lnet/minecraft/network/Connection;send(Lnet/minecraft/network/protocol/Packet;)V"))
    private void arclight$handleServerPing(Connection networkManager, Packet<?> packetIn) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        Object[] players = server.m_6846_().f_11196_.toArray();
        ArclightPingEvent event = new ArclightPingEvent(networkManager, server);
        Bukkit.getPluginManager().callEvent(event);
        List<Object> profiles = new ArrayList(players.length);
        Object[] array = players;
        int length = players.length;
        for (int i = 0; i < length; ++i) {
            ServerPlayer player = (ServerPlayer)array[i];
            if (player == null) continue;
            if (player.m_184128_()) {
                profiles.add(player.m_36316_());
                continue;
            }
            profiles.add(MinecraftServer.f_195496_);
        }
        if (!server.m_183306_() && !profiles.isEmpty()) {
            Collections.shuffle(profiles);
            profiles = profiles.subList(0, Math.min(profiles.size(), SpigotConfig.playerSample));
        }
        ServerStatus.Players playerSample = new ServerStatus.Players(event.getMaxPlayers(), profiles.size(), server.m_183306_() ? Collections.emptyList() : profiles);
        ServerStatus ping = new ServerStatus(CraftChatMessage.fromString(event.getMotd(), true)[0], Optional.of(playerSample), Optional.of(new ServerStatus.Version(server.getServerModName() + " " + server.m_7630_(), SharedConstants.m_183709_().m_132495_())), event.icon.value != null ? Optional.of(new ServerStatus.Favicon(event.icon.value)) : Optional.empty(), server.m_214005_(), Optional.of(new ServerStatusPing()));
        networkManager.m_129512_((Packet)new ClientboundStatusResponsePacket(ping));
    }
}

