/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.game.ServerboundMovePlayerPacket
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.optimization.general.network;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerGamePacketListenerImpl.class})
public class ServerGamePacketListenerImplMixin_Optimize {
    @Shadow
    public ServerPlayer f_9743_;

    @Redirect(method={"handleMovePlayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerChunkCache;move(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void arclight$markTrackerDirty(ServerChunkCache instance, ServerPlayer player, ServerboundMovePlayerPacket packet) {
        if (!packet.m_179683_()) {
            boolean old = ((ServerPlayerEntityBridge)this.f_9743_).bridge$isTrackerDirty();
            instance.m_8385_(player);
            ((ServerPlayerEntityBridge)this.f_9743_).bridge$setTrackerDirty(old);
        } else {
            instance.m_8385_(player);
        }
    }
}

