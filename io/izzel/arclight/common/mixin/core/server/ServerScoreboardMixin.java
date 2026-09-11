/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.server.ServerScoreboard
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.players.PlayerList
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.server;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerScoreboard.class})
public class ServerScoreboardMixin {
    @Redirect(method={"startTrackingObjective"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;getPlayers()Ljava/util/List;"))
    private List<ServerPlayer> arclight$filterAdd(PlayerList playerList) {
        return this.filterPlayer(playerList.m_11314_());
    }

    @Redirect(method={"stopTrackingObjective"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;getPlayers()Ljava/util/List;"))
    private List<ServerPlayer> arclight$filterRemove(PlayerList playerList) {
        return this.filterPlayer(playerList.m_11314_());
    }

    @Redirect(method={"*"}, require=11, at=@At(value="INVOKE", target="Lnet/minecraft/server/players/PlayerList;broadcastAll(Lnet/minecraft/network/protocol/Packet;)V"))
    private void arclight$sendToOwner(PlayerList playerList, Packet<?> packetIn) {
        for (ServerPlayer entity : this.filterPlayer(playerList.m_11314_())) {
            entity.f_8906_.m_9829_(packetIn);
        }
    }

    private List<ServerPlayer> filterPlayer(List<ServerPlayer> list) {
        return list.stream().filter(it -> ((ServerPlayerEntityBridge)it).bridge$getBukkitEntity().getScoreboard().getHandle() == this).collect(Collectors.toList());
    }
}

