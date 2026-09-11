/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.PacketDistributor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.forge;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={PacketDistributor.class})
public class PacketDistributorMixin {
    @Overwrite(remap=false)
    private Consumer<Packet<?>> playerConsumer(Supplier<ServerPlayer> entityPlayerMPSupplier) {
        return p -> {
            ServerPlayer entity = (ServerPlayer)entityPlayerMPSupplier.get();
            if (entity.f_8906_ != null && entity.f_8906_.f_9742_ != null) {
                entity.f_8906_.f_9742_.m_129512_(p);
            }
        };
    }
}

