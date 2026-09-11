/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.status.ServerStatus
 *  net.minecraft.network.protocol.status.ServerStatus$Favicon
 *  net.minecraft.network.protocol.status.ServerStatus$Players
 *  net.minecraft.network.protocol.status.ServerStatus$Version
 *  net.minecraftforge.network.ServerStatusPing
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.network.protocol.status;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraftforge.network.ServerStatusPing;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={ServerStatus.class})
public class ServerStatusMixin {
    public void arclight$constructor(Component description, Optional<ServerStatus.Players> players, Optional<ServerStatus.Version> version, Optional<ServerStatus.Favicon> favicon, boolean enforcesSecureChat, Optional<ServerStatusPing> forgeData) {
        throw new RuntimeException();
    }

    public void arclight$constructor(Component description, Optional<ServerStatus.Players> players, Optional<ServerStatus.Version> version, Optional<ServerStatus.Favicon> favicon, boolean enforcesSecureChat) {
        this.arclight$constructor(description, players, version, favicon, enforcesSecureChat, Optional.empty());
    }
}

