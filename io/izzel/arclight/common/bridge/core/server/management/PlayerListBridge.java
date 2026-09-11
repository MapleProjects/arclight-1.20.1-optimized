/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerLoginPacketListenerImpl
 */
package io.izzel.arclight.common.bridge.core.server.management;

import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;

public interface PlayerListBridge {
    public void bridge$setPlayers(List<ServerPlayer> var1);

    public List<ServerPlayer> bridge$getPlayers();

    public CraftServer bridge$getCraftServer();

    public ServerPlayer bridge$canPlayerLogin(SocketAddress var1, GameProfile var2, ServerLoginPacketListenerImpl var3);

    public void bridge$sendMessage(Component[] var1);
}

