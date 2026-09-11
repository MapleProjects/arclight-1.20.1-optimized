/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.server.ConsoleInput
 *  net.minecraft.server.dedicated.DedicatedServer
 *  net.minecraft.server.rcon.RconConsoleSource
 *  net.minecrell.terminalconsole.TerminalConsoleAppender
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server.dedicated;

import io.izzel.arclight.common.mixin.core.server.MinecraftServerMixin;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.BukkitRegistry;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.ConsoleInput;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.rcon.RconConsoleSource;
import net.minecrell.terminalconsole.TerminalConsoleAppender;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.command.CraftRemoteConsoleCommandSender;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoadOrder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={DedicatedServer.class})
public abstract class DedicatedServerMixin
extends MinecraftServerMixin {
    @Shadow
    @Final
    public RconConsoleSource f_139602_;

    public DedicatedServerMixin(String name) {
        super(name);
    }

    @Inject(method={"initServer"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/server/dedicated/DedicatedServer;setPlayerList(Lnet/minecraft/server/players/PlayerList;)V")})
    public void arclight$loadPlugins(CallbackInfoReturnable<Boolean> cir) {
        BukkitRegistry.unlockRegistries();
        ((CraftServer)Bukkit.getServer()).loadPlugins();
        ((CraftServer)Bukkit.getServer()).enablePlugins(PluginLoadOrder.STARTUP);
        BukkitRegistry.lockRegistries();
    }

    @Inject(method={"initServer"}, at={@At(value="FIELD", target="Lnet/minecraft/server/dedicated/DedicatedServerProperties;enableRcon:Z")})
    public void arclight$setRcon(CallbackInfoReturnable<Boolean> cir) {
        this.remoteConsole = new CraftRemoteConsoleCommandSender(this.f_139602_);
    }

    @Redirect(method={"handleConsoleInputs"}, at=@At(value="INVOKE", target="Lnet/minecraft/commands/Commands;performPrefixedCommand(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;)I"))
    private int arclight$serverCommandEvent(Commands commands, CommandSourceStack source, String command) {
        if (command.isEmpty()) {
            return 0;
        }
        ServerCommandEvent event = new ServerCommandEvent(this.console, command);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.server.dispatchServerCommand(this.console, new ConsoleInput(event.getCommand(), source));
        }
        return 0;
    }

    @Overwrite
    public String m_7261_(String command) {
        this.f_139602_.m_11512_();
        this.m_18709_(() -> {
            RemoteServerCommandEvent event = new RemoteServerCommandEvent(this.remoteConsole, command);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            this.server.dispatchServerCommand(this.remoteConsole, new ConsoleInput(event.getCommand(), this.f_139602_.m_11514_()));
        });
        return this.f_139602_.m_11513_();
    }

    @Inject(method={"onServerExit"}, at={@At(value="RETURN")})
    public void arclight$exitNow(CallbackInfo ci) {
        try {
            TerminalConsoleAppender.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Thread exitThread = new Thread(this::arclight$exit, "Exit Thread");
        exitThread.setDaemon(true);
        exitThread.start();
    }

    private void arclight$exit() {
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> threads = new ArrayList<String>();
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread.isDaemon() || thread.getName().equals("DestroyJavaVM")) continue;
            threads.add(thread.getName());
        }
        if (!threads.isEmpty()) {
            ArclightMod.LOGGER.debug("Threads {} not shutting down", (Object)String.join((CharSequence)", ", threads));
            ArclightMod.LOGGER.info("{} threads not shutting down correctly, force exiting", (Object)threads.size());
        }
        System.exit(0);
    }

    @Overwrite
    public String m_7138_() {
        StringBuilder result = new StringBuilder();
        Plugin[] plugins = this.server.getPluginManager().getPlugins();
        result.append(this.server.getName());
        result.append(" on Bukkit ");
        result.append(this.server.getBukkitVersion());
        if (plugins.length > 0 && this.server.getQueryPlugins()) {
            result.append(": ");
            for (int i = 0; i < plugins.length; ++i) {
                if (i > 0) {
                    result.append("; ");
                }
                result.append(plugins[i].getDescription().getName());
                result.append(" ");
                result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
            }
        }
        return result.toString();
    }
}

