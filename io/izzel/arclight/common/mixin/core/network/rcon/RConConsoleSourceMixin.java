/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.rcon.RconConsoleSource
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.network.rcon;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import io.izzel.arclight.common.bridge.core.network.rcon.RConConsoleSourceBridge;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.rcon.RconConsoleSource;
import org.bukkit.command.CommandSender;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RconConsoleSource.class})
public class RConConsoleSourceMixin
implements ICommandSourceBridge,
RConConsoleSourceBridge {
    @Shadow
    @Final
    private StringBuffer f_11501_;
    @Shadow
    @Final
    private MinecraftServer f_11502_;

    public CommandSender getBukkitSender() {
        return ((MinecraftServerBridge)this.f_11502_).bridge$getRemoteConsole();
    }

    public void sendMessage(String message) {
        this.f_11501_.append(message);
    }

    @Override
    public CommandSender bridge$getBukkitSender(CommandSourceStack wrapper) {
        return this.getBukkitSender();
    }

    @Override
    public void bridge$sendMessage(String message) {
        this.sendMessage(message);
    }
}

