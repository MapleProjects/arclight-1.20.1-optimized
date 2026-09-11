/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.PacketListener
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.PacketUtils
 *  net.minecraft.server.RunningOnDifferentThreadException
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  net.minecraft.util.thread.BlockableEventLoop
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.protocol;

import io.izzel.arclight.common.bridge.core.network.play.ServerPlayNetHandlerBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.server.RunningOnDifferentThreadException;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.thread.BlockableEventLoop;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={PacketUtils.class})
public class PacketThreadUtilMixin {
    @Shadow
    @Final
    private static Logger f_131354_;

    @Overwrite
    public static <T extends PacketListener> void m_131363_(Packet<T> packetIn, T processor, BlockableEventLoop<?> executor) throws RunningOnDifferentThreadException {
        if (!executor.m_18695_()) {
            executor.m_201446_(() -> {
                if (((MinecraftServerBridge)((CraftServer)Bukkit.getServer()).getServer()).bridge$hasStopped() || processor instanceof ServerGamePacketListenerImpl && ((ServerPlayNetHandlerBridge)processor).bridge$processedDisconnect()) {
                    return;
                }
                if (processor.m_6198_()) {
                    try {
                        packetIn.m_5797_(processor);
                    }
                    catch (Exception exception) {
                        if (processor.m_201767_()) {
                            throw exception;
                        }
                        f_131354_.error("Failed to handle packet {}, suppressing error", (Object)packetIn, (Object)exception);
                    }
                } else {
                    f_131354_.debug("Ignoring packet due to disconnection: " + String.valueOf(packetIn));
                }
            });
            throw RunningOnDifferentThreadException.f_136017_;
        }
        if (((MinecraftServerBridge)((CraftServer)Bukkit.getServer()).getServer()).bridge$hasStopped() || processor instanceof ServerGamePacketListenerImpl && ((ServerPlayNetHandlerBridge)processor).bridge$processedDisconnect()) {
            throw RunningOnDifferentThreadException.f_136017_;
        }
    }
}

